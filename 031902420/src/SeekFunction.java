import java.util.ArrayList;

public class SeekFunction {

    private final ArrayList<FileLine> fileLineArrayList;
    private final ArrayList<MaskWords> maskWordsArrayList;
    private final ArrayList<Answer> answerArrayList = new ArrayList<>();

    public SeekFunction(ArrayList<FileLine> fileLineArrayList,ArrayList<MaskWords> maskWordsArrayList) {
        this.fileLineArrayList = fileLineArrayList;
        this.maskWordsArrayList = maskWordsArrayList;
    }

    public ArrayList<Answer> seek(){
        for (FileLine fileLine : fileLineArrayList) {//fileLine 既为每一行的数据
            String fileStr = fileLine.getFileLine();  //要寻找的字符串
            int fileLength = fileLine.getFileLineLength();  //字符串长度
            int fileNumber = fileLine.getLine();  // 字符串所在行数
            for (MaskWords maskWord : maskWordsArrayList) { //对每一个屏蔽字进行判断
                String maskStr = maskWord.getMaskWords(); //屏蔽字的字符串
                int maskLength = maskWord.getMaskWordsLength();// 屏蔽字的长度
                if ((maskStr.charAt(0) >= 'a' && maskStr.charAt(0) <= 'z') || (maskStr.charAt(0) >= 'A' && maskStr.charAt(0) <= 'Z')) {  //屏蔽字为英文
                    int minChar = 0;
                    int maxChar = fileLength - 1;
                    int value;  // 用于存放字符匹配个数
                    for (int k = 0; k < fileLength; k++) {
                        value = 0;
                        if (maskStr.toUpperCase().charAt(value) == fileStr.toUpperCase().charAt(k)) {
                            minChar = k;  //找到起始位置
                            value = 1;
                            for (int l = k + 1; l < fileLength; l++) {
                                if(fileStr.toUpperCase().charAt(l) == maskStr.toUpperCase().charAt(0)){  // 起始位置的变动
                                    minChar = l;
                                }
                                if(fileStr.toUpperCase().charAt(l) == maskStr.toUpperCase().charAt(value)){
                                    value++;
                                }
                                if(fileStr.toUpperCase().charAt(l) == maskStr.toUpperCase().charAt(maskLength - 1)){
                                    maxChar = l;
                                    break;
                                }
                            }
                        }
                        if (value == maskLength) { //样例输出
                            Answer answer = new Answer();
                            Answer.setTotal(Answer.getTotal() + 1);
                            answer.setLine(fileNumber);
                            answer.setMaskWord(maskStr);
                            answer.setFileLine(fileStr.substring(minChar,maxChar + 1));
                            answerArrayList.add(answer);
                        }
                    }

                }else { // 屏蔽字为中文
                    int minChar = 0; //屏蔽字的开始位置
                    int maxChar = fileLength - 1;  //屏蔽字的结束位置
                    int value;   // 用于存放字符匹配个数
                    for (int k = 0; k < fileLength; k++) {
                        value = 0;
                        String maskCharString = maskStr.substring(value,value + 1); //将屏蔽字的每一个字分开
                        String maskPinYin = new ChineseChange().convertAll(maskCharString); //将关键字变成拼音
                        int flag = 0; //关键字拼音匹配个数
                        for (int i = 0; i < maskPinYin.length(); i++) {
                            if(i + k >= fileLength) break; //防止越界
                            if (maskPinYin.toLowerCase().charAt(i) == fileStr.toLowerCase().charAt(i + k)){
                                flag++;
                            }
                        }
                        //   屏蔽字和原文相同                                   屏蔽字拼音和原文相同              屏蔽字拼音缩写和原文相同
                        if (maskCharString.charAt(value) == fileStr.charAt(k) || flag == maskPinYin.length() || maskPinYin.toLowerCase().charAt(0) == fileStr.toLowerCase().charAt(k) ) {
                            minChar = k;
                            value = 1;
                            char pri = maskPinYin.charAt(0);
                            for (int l = k + 1; l < fileLength; l++) {
                                maskCharString = maskStr.substring(value,value + 1);
                                maskPinYin = new ChineseChange().convertAll(maskCharString);
                                int flag1 = 0;
                                for (int i = 0; i < maskPinYin.length(); i++) {
                                    if (i + l >= fileStr.length()) break;
                                    if (maskPinYin.toLowerCase().charAt(i) == fileStr.toLowerCase().charAt(i + l)){
                                        flag1++;
                                    }
                                }
                                if(fileStr.charAt(l) == maskStr.charAt(0) || fileStr.toLowerCase().charAt(l) == pri){
                                    minChar = l;
                                    value = 1;
                                }
                                if(fileStr.charAt(l) == maskStr.charAt(value) || maskPinYin.toLowerCase().charAt(0) == fileStr.toLowerCase().charAt(k)){
                                    value++;
                                }
                                if(fileStr.charAt(l) == maskStr.charAt(maskLength - 1)){
                                    maxChar = l;
                                    break;
                                }
                            }
                        }

                        //拼音的缩写 未完成
                        //偏旁部首  未完成
                        if (value == maskLength) { //样例输出
                            Answer answer = new Answer();
                            Answer.setTotal(Answer.getTotal() + 1);
                            answer.setLine(fileNumber);
                            answer.setMaskWord(maskStr);
                            answer.setFileLine(fileStr.substring(minChar,maxChar + 1));
                            answerArrayList.add(answer);
                        }
                    }
                }
            }
        }
        return answerArrayList;
    }
}
