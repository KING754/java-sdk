import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;

public class FetchDemo {

    public static void main(String args[]) {
        //设置需要操作的账号的AK和SK
        String ACCESS_KEY = "Access_Key";
        String SECRET_KEY = "Secret_Key";
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        //第一种方式: 指定具体的要上传的region
        //指定存储空间所在区域，华北region1，华南region2 ，华东 region0等。具体可以Region类里寻找。
        //Region region = Region.region1();

        //第一种方式: 使用自动方式
        Region autoRegion = Region.autoRegion();
        Configuration c = new Configuration(autoRegion);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);

        //文件保存的空间名和文件名
        String bucket = "yourbucket";
        String key = "yourkey";

        //要fetch的url
        String url = "url";

        try {
            //调用fetch方法抓取文件
            bucketManager.fetch(url, bucket, key);
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
        }
    }

}
