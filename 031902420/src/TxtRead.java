import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TxtRead {

        public static BufferedReader readTxtFile(String filePath){
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(new File(filePath)), "UTF-8")); // read encoding
                return br;

            } catch (Exception e) {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }
            return null;
        }
}
