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
                    int value = 1;
                    for (int k = 0; k < fileLength; k++) {
                        if (maskStr.toUpperCase().charAt(0) == fileStr.toUpperCase().charAt(k)) {
                            minChar = k;  //找到起始位置
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
                            break;
                        }
                    }
                    if (value == maskLength) { //样例输出
                        /*
                         * 在这里要进行文件的输出
                         */
                        Answer answer = new Answer();
                        Answer.setTotal(Answer.getTotal() + 1);
                        answer.setLine(fileNumber);
                        answer.setMaskWord(maskStr);
                        answer.setFileLine(fileStr.substring(minChar,maxChar + 1));
                    }

                } else { // 屏蔽字为中文
                    int minChar = 0; //屏蔽字的开始位置
                    int maxChar = fileLength - 1;  //屏蔽字的结束位置
                    int value = 0;
                    int value1 = 0;
                    String maskStr1 = new ChineseChange().convertAll(maskStr);
                    for (int k = 0; k < fileLength; k++) {
                        if (maskStr.charAt(0) == fileStr.charAt(k) ) {  //和屏蔽字相同,中间会有特殊字符
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
                            break;
                        }
                        if(maskStr1.toUpperCase().charAt(0) == fileStr.toUpperCase().charAt(k)){  //拼音全相同
                            minChar = k;
                            value1 = 1;
                            for (int i = 1; i < maskStr1.length() && (i + k) < fileLength; i++) {
                                if (maskStr1.toUpperCase().charAt(i) == fileStr.toUpperCase().charAt(k + i)){
                                    value1++;
                                }
                            }
                            if(value1 == maskStr1.length()) {
                                maxChar = k + maskStr1.length() - 1;
                            }
                        }
                        //拼音的缩写 未完成
                        //偏旁部首  未完成
                    }
                    if (value == maskLength || value1 == maskStr1.length()) { //样例输出
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
        return answerArrayList;
    }
}
