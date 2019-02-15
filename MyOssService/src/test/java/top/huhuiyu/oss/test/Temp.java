package top.huhuiyu.oss.test;

import java.io.File;
import java.util.Scanner;
import java.util.UUID;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 临时测试类
 * 
 * @author 胡辉煜
 */
public class Temp {
  public static void one() throws Exception {
    Scanner scanner = new Scanner(Temp.class.getResourceAsStream("/temp.txt"));
    while (scanner.hasNextLine()) {
      System.out.println(scanner.nextLine());
    }
    scanner.close();
  }

  public static void two() throws Exception {
    OSSClientBuilder ocb       = new OSSClientBuilder();
    OSS              ossClient = ocb.build("oss-cn-shenzhen.aliyuncs.com", "LTAIa68HmNnw4yT4", "KIhMw0SReRF8IZcxBIscnr3mN1D81T");
    PutObjectResult  putResult = ossClient.putObject("huhuiyu-common-files", "e9d69bd3-2afe-4460-a19a-3532a4c66e6c.mp3", new File("D:\\music\\4-Good Times.mp3"));
    System.out.println(String.format("ETag:%s,RequestId:%s,ClientCRC:%s,ServerCRC:%s", putResult.getETag(), putResult.getRequestId(), putResult.getClientCRC(), putResult.getServerCRC()));
    ossClient.shutdown();
  }

  public static void three() throws Exception {
    System.out.println(UUID.randomUUID().toString());
  }

  public static void main(String[] args) throws Exception {
    // Temp.one();
    // Temp.two();
    Temp.three();
  }
}
