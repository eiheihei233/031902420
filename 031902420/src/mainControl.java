import java.io.*;
import java.util.ArrayList;

public class mainControl {

    public static void main(String[] args) throws IOException {
        ArrayList<Answer> answerArrayList;
        String org = args[1] ; //待检测文件   args[1]  "D:\\codeMade\\idea\\031902420\\org.txt"
        String words = args[0]; //敏感词词汇文件  args[0]  "D:\\codeMade\\idea\\031902420\\words.txt"
        String answer = args[2]; //答案文件  args[2]  "D:\\codeMade\\idea\\031902420\\answer.txt"
        String charset = "UTF-8";
        BufferedReader orgFile = TxtRead.readTxtFile(org);
        BufferedReader wordsFile = TxtRead.readTxtFile(words);
        ArrayList<FileLine> fileLineArrayList = new ArrayList<>();   //用于存放文章每一行的数组
        ArrayList<MaskWords> maskWordsArrayList = new ArrayList<>(); //用于存放每一个关键字
        if(orgFile != null && wordsFile != null) {
            for (int i = 0; ; i++) {   //将原文件的每一行数据分别进行存储
                String orgLine = orgFile.readLine();
                if (orgLine == null) break;
                FileLine fileLine = new FileLine();
                fileLine.setFileLine(orgLine);
                fileLine.setFileLineLength(orgLine.length());
                fileLine.setLine(i + 1);
                fileLineArrayList.add(fileLine);
            }
            for (;;) { //将屏蔽词的每一方数据分别进行存储
                String maskWordLine = wordsFile.readLine();
                if (maskWordLine == null) break;
                MaskWords maskWord = new MaskWords();
                maskWord.setMaskWords(maskWordLine);
                maskWord.setMaskWordsLength(maskWordLine.length());
                maskWordsArrayList.add(maskWord);
            }
            SeekFunction seekFunction = new SeekFunction(fileLineArrayList, maskWordsArrayList);
            answerArrayList = seekFunction.seek();
            FileOutputStream outputStream = new FileOutputStream(answer);
            try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset)) {
                String str1 = Integer.toString(Answer.getTotal());
                writer.write("Total: " + str1);
                writer.write("\n");
                for (Answer value : answerArrayList) {
                    String str = "Line" + value.getLine() + ": <" + value.getMaskWord() + "> " + value.getFileLine();
                    writer.write(str);
                    writer.write("\n");
                }

            }
        }else{
            System.out.println("读取错误");
        }

    }












//
//    String file = "c:/stream.txt";
//    String charset = "UTF-8";
//    //写字符转换成字节流
//    FileOutputStream outputStream = new FileOutputStream(file);
//    OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
//            try {
//        writer.write("这是要保存的中文字符");
//    } finally {
//        writer.close();
//    }
//









//                    InputStreamReader read = new InputStreamReader(new FileInputStream(file));
//                    BufferedReader bufferedReader = new BufferedReader(read);
//                    String lineTxt = null;
//                    while((lineTxt = bufferedReader.readLine()) != null){
//                        System.out.println(lineTxt);
//                    }
//                    read.close();
}
