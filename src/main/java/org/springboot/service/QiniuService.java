package org.springboot.service;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.controller.LoginController;
import org.springboot.util.ToutiaoUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by lxh on 2017/3/19.
 */

@Service
public class QiniuService {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    //构造一个带指定Zone对象的配置类
    Configuration cfg = new Configuration(Zone.zone1());
//...其他参数参考类注释

    UploadManager uploadManager = new UploadManager(cfg);
    //...生成上传凭证，然后准备上传
    String accessKey = "DTC9bbANRL5tQWQncFooG9UiyMYaupMYHsbxatgD";
    String secretKey = "LbFehG9FHjXziYhPDhmy3azUr5pI_5XJJJ01Ju9S";
    String bucket = "dlut";
    //如果是Windows情况下，格式是 D:\\qiniu\\test.png
    //String localFilePath = "/home/qiniu/test.png";
    //默认不指定key的情况下，以文件内容的hash值作为文件名
    //String key = null;
//密钥配置
    Auth auth = Auth.create(accessKey, secretKey);
    String upToken = auth.uploadToken(bucket);

    public String saveImage(MultipartFile file) throws IOException{
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf('.');
            String fileExt = file.getOriginalFilename().toLowerCase().substring(dotPos + 1);
            if (!ToutiaoUtil.isFileAllowed(fileExt)){
                return null;
            }
            String fileName = UUID.randomUUID().toString().replaceAll("_", "").toLowerCase() + "." + fileExt;
            Response response = uploadManager.put(file.getBytes(), fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            if (putRet == null){
                logger.error("七牛异常:" + response.bodyString());
                return null;
            }
            //System.out.println(putRet.key);
            //System.out.println(putRet.hash);
            return ToutiaoUtil.QINIU_IMAGE_DOMAIN + putRet.key;
        } catch (QiniuException e) {
            logger.error("七牛异常:" + e.getMessage());
            return null;
        }
    }

    public String downloadImage(String fileName) throws UnsupportedEncodingException {
        //构造私有空间的需要生成的下载的链接
        String URL = ToutiaoUtil.QINIU_IMAGE_DOMAIN + fileName;
        String downloadRUL = auth.privateDownloadUrl(URL, 3600);
        System.out.println(downloadRUL);
        return downloadRUL;
    }
}
