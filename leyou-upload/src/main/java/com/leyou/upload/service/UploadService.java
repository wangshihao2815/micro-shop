package com.leyou.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author wangshihao
 * @Date 2020/3/2
 * @Since 1.0.0
 */


public interface UploadService {

    /**
     * 上传图片，返回图片访问地址
     * @param file
     * @return
     */
    String uploadImage(MultipartFile file);
}
