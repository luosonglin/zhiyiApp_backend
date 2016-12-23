package cn.luosonglin.test.qiniu.api;

import cn.luosonglin.test.qiniu.config.Config;
import com.qiniu.util.Auth;

/**
 * Created by luosonglin on 23/12/2016.
 */
public class DownloadFileUrl {


    //设置好账号的ACCESS_KEY和SECRET_KEY
    static String ACCESS_KEY = "r0_GHWBaOY4cbuUfQHAOsj0KoGAo_648Xc1SYCfe";
    static String SECRET_KEY = "HVQvB-vB6w5HmwWZnIttXJ_DXyqCxC0HSPT56RSG";

    //密钥配置,设置好账号的ACCESS_KEY和SECRET_KEY
    public static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    //构造私有空间的需要生成的下载的链接
    String URL = "http://oimlpcb8y.bkt.clouddn.com";

    public static void main(String args[]) {
        new DownloadFileUrl().download();
    }

    public void download() {
        //调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
        String downloadRUL = auth.privateDownloadUrl(URL, 3600);
        System.out.println(downloadRUL);
    }
}
