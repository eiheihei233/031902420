/*
 * 对文件进行输入
 */

import java.io.File;

public class TxtRead {

        public static File readTxtFile(String filePath){
            try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){   //判断文件是否存在
                    return file;

                }else{
                    System.out.println("找不到指定的文件");
                }
            } catch (Exception e) {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }
            return null;
        }
}
