package com.taotao.web;

import com.taotao.commons.CookieUtils;
import com.taotao.commons.JsonUtils;
import com.taotao.commons.SystemConstants;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OrderInterceptor implements HandlerInterceptor {
    //@Value("${taotao.redis.login.token.prefix}")
    private String userTokenRedisKeyPrefix="user";
    //@Value("${taotao.sso.login_url}")
    private String ssoLoginUrl = "http://localhost:8084/user/showLogin";

    //@Value("${taotao.sso.token_url}")
    private String ssoTokenUrl = "http://127.0.0.1:6379";
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, IOException {

        // 获取Cookie中的token
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");

        // 获取用户原本想去的页面
        //String redirectUrl = HttpClient.getFullUrl(request);
        String redirectUrl = String.valueOf(request.getRequestURL());

        // 如果用户没有token，重定向到登录页，并且附加原始url
        if(StringUtils.isBlank(token)) {
            response.sendRedirect(ssoLoginUrl+"?redirect=" + redirectUrl);
            return false;
        }
        //String tokens = getTokenRedisKey(token);
       /* String rediskey = "user"+token;
        // 拿从Cookie中获取的token信息请求SSO服务，获取真正的用户信息
        //String jsonData = HttpClient.doGet(ssoTokenUrl + "/" +token);
        System.out.println(redisTemplate.opsForValue().get(rediskey));
        String jsonData = redisTemplate.opsForValue().get(rediskey);*/

        //String tokens = getTokenRedisKey(token);
        // 拿从Cookie中获取的token信息请求SSO服务，获取真正的用户信息
        //String jsonData = HttpClient.doGet(ssoTokenUrl + "/" +token);

        String jsonData = redisTemplate.opsForValue().get("user"+token);
        try {
            // 用户token正常，将token对应的用户信息保存到request作用域
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(JsonUtils.objectToJson(TaotaoResult.ok(jsonData)), TbUser.class);
            if(taotaoResult.getStatus().equals(SystemConstants.TAOTAO_RESULT_STATUS_OK)) {
                TbUser tbUser = (TbUser) taotaoResult.getData();
                request.setAttribute("user", tbUser);
                return true;
            }


            // 用户会话过期后的处理
            response.sendRedirect("<script>alert('登录信息失效，请重新登录');window.location.href='" + ssoLoginUrl + "';</script>");
            return false;
        } catch (Exception e) {
            //log.error("无法获取用户信息", e);
            e.printStackTrace();
            return false;
        }
    }
    private String getTokenRedisKey(String token) {
        return "http://localhost:8082-" + token;
    }
}

