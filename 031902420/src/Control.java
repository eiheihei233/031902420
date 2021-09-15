import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Control {

    public static void main(String[] args) throws IOException {
        ArrayList<Answer> answerArrayList;
        String org = "D:\\codeMade\\idea\\031902420\\org.txt";
        String words = "D:\\codeMade\\idea\\031902420\\words.txt";
        String answer = "D:\\codeMade\\idea\\031902420\\answer.txt";
        String charset = "UTF-8";
        File orgFile = TxtRead.readTxtFile(org);
        File wordsFile = TxtRead.readTxtFile(words);
        ArrayList<FileLine> fileLineArrayList = new ArrayList<>();   //用于存放文章每一行的数组
        ArrayList<MaskWords> maskWordsArrayList = new ArrayList<>(); //用于存放每一个关键字
        if(orgFile != null && wordsFile != null) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(orgFile));
            BufferedReader bufferedReader = new BufferedReader(read);
            for (int i = 0; ; i++) {   //将原文件的每一行数据分别进行存储
                String orgLine = bufferedReader.readLine();
                if (orgLine == null) break;
                FileLine fileLine = new FileLine();
                fileLine.setFileLine(orgLine);
                fileLine.setFileLineLength(orgLine.length());
                fileLine.setLine(i + 1);
                fileLineArrayList.add(fileLine);
            }
            InputStreamReader read1 = new InputStreamReader(new FileInputStream(wordsFile));
            BufferedReader bufferedReader1 = new BufferedReader(read1);
            for (;;) { //将屏蔽词的每一方数据分别进行存储
                String maskWordLine = bufferedReader1.readLine();
                if (maskWordLine == null) break;
                MaskWords maskWord = new MaskWords();
                maskWord.setMaskWords(maskWordLine);
                maskWord.setMaskWordsLength(maskWordLine.length());
                maskWordsArrayList.add(maskWord);
            }
            read.close(); //读取完毕
            SeekFunction seekFunction = new SeekFunction(fileLineArrayList, maskWordsArrayList);
            answerArrayList = seekFunction.seek();
            FileOutputStream outputStream = new FileOutputStream(answer);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
            try{
                String str1 = Integer.toString(Answer.getTotal());
                writer.write("Total: " + str1);
                writer.write("\n");
                for (int i = 0; i < answerArrayList.size(); i++) {
                    String str = "Line" + answerArrayList.get(i).getLine() + ": <" + answerArrayList.get(i).getMaskWord() + "> " + answerArrayList.get(i).getFileLine();
                    writer.write(str);
                    writer.write("\n");
                }

            } finally {
                writer.close();
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
