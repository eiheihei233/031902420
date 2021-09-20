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

                        if (maskCharString.charAt(0) == fileStr.charAt(k) ) {  //和屏蔽字相同,中间会有特殊字符
                            minChar = k;
                            value = 1;
                            for (int l = k + 1; l < fileLength; l++) {
                                if(fileStr.charAt(l) == maskStr.charAt(0)){
                                    minChar = l;
                                }
                                if(fileStr.charAt(l) == maskStr.charAt(value)){
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
