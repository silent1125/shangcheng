import com.taotao.commons.FtpUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestFtp {
    @Test
    public void testFtpClient() throws FileNotFoundException {

        String host = "images.taotao.com";
        int port = 21;
        String user = "ftpuser";
        String password = "xcvbn1161011523";
        String basePath = "/home/ftpuser";
        String filePath = "/www/images";
        String fileName = "05.jpg";
        InputStream file = new FileInputStream("E:/1/5.jpg");

        boolean ret = FtpUtil.uploadFile(host, port, user, password, basePath, filePath, fileName, file);
        Assert.assertTrue("上传失败", ret);
        System.out.println(ret);
    }

}
