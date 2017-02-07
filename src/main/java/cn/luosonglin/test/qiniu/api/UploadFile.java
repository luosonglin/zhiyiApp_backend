package cn.luosonglin.test.qiniu.api;

import cn.luosonglin.test.qiniu.config.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Recorder;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.IOException;

/**
 * Created by luosonglin on 23/12/2016.
 */
public class UploadFile {

    //密钥配置,设置好账号的ACCESS_KEY和SECRET_KEY
    public static Auth auth = Auth.create(Config.ACCESS_KEY, Config.SECRET_KEY);

    //要上传的空间
    static String bucketname = "test";

    //上传到七牛后保存的文件名
    static String key = "haha1.png";

    //上传文件的路径
    String FilePath = "/Users/luosonglin/Documents/3.jpg";

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    Zone z = Zone.autoZone();
    Configuration c = new Configuration(z);

    //创建上传对象
    UploadManager uploadManager = new UploadManager(c);


    public static void main(String args[]) throws IOException {
//        new UploadFile().upload(getSimpleUpToken());
//        new UploadFile().upload(getCoverUpToken());
//        new UploadFile().uploadBreakpoint();

        //上传&回调
        new UploadFile().upload(getCallUpToken());
    }

    //上传
    public void upload(String uploadType) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, uploadType);
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }


    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getSimpleUpToken() {
        return auth.uploadToken(bucketname);
    }

    // 覆盖上传
    public static String getCoverUpToken(){
        //<bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
        //如果希望只能上传指定key的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1。
        //第三个参数是token的过期时间
        return auth.uploadToken(bucketname, key);
//        return auth.uploadToken(bucketname, key, 3600, new StringMap().put("insertOnly", 1));
    }

    //断点上传
    public void uploadBreakpoint() throws IOException{
        //设置断点记录文件保存在指定文件夹或的File对象
        String recordPath = "/Users/luosonglin/Documents";
        //实例化recorder对象
        Recorder recorder = new FileRecorder(recordPath);
        //实例化上传对象，并且传入一个recorder对象
        UploadManager uploadManager = new UploadManager(c, recorder);

        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getSimpleUpToken());//"path/file", "key",
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    //设置callbackUrl以及callbackBody,七牛将文件名和文件大小回调给业务服务器
    public static String getCallUpToken(){
        return auth.uploadToken(bucketname,null,3600,new StringMap()
                .put("callbackUrl","http://your.domain.com/callback")
                .put("callbackBody", "filename=$(fname)&filesize=$(fsize)"));
    }

}
