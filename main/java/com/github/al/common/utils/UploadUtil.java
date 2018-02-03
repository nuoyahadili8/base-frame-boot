package com.github.al.common.utils;

/**
 * Created by 陈熠
 * 2017/7/29.
 */
public class UploadUtil {

//    public static String  uploadImage(String fileName, InputStream inputStream) {
//        String fileNameBak=fileName;
//        String resultImgUrl ="";
//        String endpoint ="";
//        String accessKeyId = "";
//        String accessKeySecret = "";
//        String bucket = "";
//        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//        if (!ossClient.doesBucketExist(bucket)) {
//            ossClient.createBucket(bucket);
//        }
//
//        ObjectMetadata objectMeta = new ObjectMetadata();//oss属性设置
//        //objectMeta.setContentLength(inputStream.getSize());//标记长度
//        objectMeta.setContentType("image/jpeg");// 可以在metadata中标记文件类型
//        try {
//
//            //获取上传的图片文件名
//            Long nanoTime = System.nanoTime();// 防止文件被覆盖，以纳秒生成图片名
//            String _extName = fileName.substring(fileName.indexOf("."));//获取扩展名
//            fileName = nanoTime + _extName;
//            fileName = DateUtil.getYmd()+"/"+fileName+"/"+fileNameBak;
//            ossClient.putObject(bucket,fileName, inputStream,objectMeta);
//            resultImgUrl+=fileName;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new MyException("上传失败");
//        }finally{
//            ossClient.shutdown();
//        }
//        return resultImgUrl;
//    }
}
