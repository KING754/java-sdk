import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;


public class BatchDemo {

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

        //创建 BatchOperations 类型的 operations 对象
        BucketManager.BatchOperations operations = new BucketManager.BatchOperations();

        //第一组源空间名、原文件名，目的空间名、目的文件名
        String bucketFrom1 = "yourbucket";
        String keyFrom1 = "srckey1";
        String bucketTo1 = "yourbucket";
        String keyTo1 = "destkey1";

        //第二组源空间名、原文件名，目的空间名、目的文件名
        String bucketFrom2 = "yourbucket";
        String keyFrom2 = "srckey2";
        String bucketTo2 = "yourbucket";
        String keyTo2 = "destkey2";


        try {
            //调用批量操作的batch方法
            Response res = bucketManager.batch(operations.move(bucketFrom1, keyFrom1, bucketTo1, keyTo1)
                    .move(bucketFrom2, keyFrom2, bucketTo2, keyTo2));

            System.out.println(res.toString());

        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
        }
    }
}
