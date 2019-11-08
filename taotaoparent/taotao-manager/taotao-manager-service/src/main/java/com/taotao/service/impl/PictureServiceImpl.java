package com.taotao.service.impl;

import com.taotao.commons.ExceptionUtil;
import com.taotao.commons.FtpUtil;
import com.taotao.commons.IDUtils;
import com.taotao.commons.PictureResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
//直接读取properties属性配置文件
@PropertySource("classpath:/resources.properties")
public class PictureServiceImpl implements PictureService {
    //取出属性配置文件注入的属性值
    @Value("${ftp.host}")
    private String ftpHost;
    @Value("${ftp.port}")
    private Integer ftpPort;
    @Value("${ftp.username}")
    private String ftpUserName;
    @Value("${ftp.password}")
    private String ftpPassword;
    @Value("${ftp.basePath}")
    private String ftpBasePath;
    @Value("${image.domain}")
    private String imageDomain;

    @Override
    public Map<String, Object> uploadPicture(MultipartFile picFile) {



        Map<String, Object> result = new HashMap<>();

        // 获取上传的文件后缀名
        String suffixName = picFile.getOriginalFilename().substring(picFile.getOriginalFilename().lastIndexOf('.'));

        // 将图片上传到FTP服务器
        String fileName = IDUtils.genImageName() + suffixName;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        // 文件上传路径： 2019/07/26
        String filePath = sdf.format(new Date());

        StringBuilder fileUrl = new StringBuilder();
        fileUrl.append(imageDomain);
        fileUrl.append("/");
        fileUrl.append(filePath);
        fileUrl.append("/");
        fileUrl.append(fileName);

        // 返回图片访问地址


        // 上传插件支持的返回类型
        // //成功时
        //{
        //        "error" : 0,
        //        "url" : "http://www.example.com/path/to/file.ext"
        //}
        ////失败时
        //{
        //        "error" : 1,
        //        "message" : "错误信息"
        //}
        try {
            boolean flag = FtpUtil.uploadFile(ftpHost, ftpPort, ftpUserName, ftpPassword, ftpBasePath, filePath, fileName, picFile.getInputStream());
            if(!flag) {
                result.put("error", 1);
                result.put("message", "上传失败");
            } else {
                result.put("error", 0);
                result.put("url", fileUrl.toString());//把文件所在服务器的路径返回给前端
            }
        } catch (IOException e) {
            result.put("error", 1);
            result.put("message", "上传失败: " + ExceptionUtil.getStackTrace(e));
        }
        return result;

    }
}
