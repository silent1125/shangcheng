package com.taotao.web;

import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("pic")
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @RequestMapping("upload")
    public Map<String, Object> uploadPic(@RequestParam("uploadFile") MultipartFile picFile) {
        Map<String, Object> result = pictureService.uploadPicture(picFile);
        return result;
    }

}
