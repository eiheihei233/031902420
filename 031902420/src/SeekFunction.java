import java.util.ArrayList;

public class SeekFunction {

    private final ArrayList<FileLine> fileLineArrayList;
    private final ArrayList<MaskWords> maskWordsArrayList;
//    private ArrayList ansArrayList;

    public SeekFunction(ArrayList<FileLine> fileLineArrayList,ArrayList<MaskWords> maskWordsArrayList) {
        this.fileLineArrayList = fileLineArrayList;
        this.maskWordsArrayList = maskWordsArrayList;
    }

    public void seek(){
        for (FileLine fileLine : fileLineArrayList) {//fileLine 既为每一行的数据
            String fileStr = fileLine.getFileLine();  //要寻找的字符串
            int fileLength = fileLine.getFileLineLength();  //字符串长度
            int fileNumber = fileLine.getLine();  // 字符串所在行数
//            int total = 0; //屏蔽字的总数  我需要在答案的类中进行定义======================
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
                        System.out.print("Line:" + fileNumber + " <" + maskStr + "> ");
                        for (int l = minChar; l <= maxChar; l++) {
                            System.out.print(fileStr.charAt(l));
                        }
                        System.out.println();
                    }

                } else { // 屏蔽字为中文
                    int minChar = 0;
                    int maxChar = fileLength - 1;
                    int value = 1;
                    for (int k = 0; k < fileLength; k++) {
                        if (maskStr.charAt(0) == fileStr.charAt(k)) {
                            minChar = k;
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
                        System.out.print("Line:" + fileNumber + " <" + maskStr + "> ");
                        for (int l = minChar; l <= maxChar; l++) {
                            System.out.print(fileStr.charAt(l));
                        }
                        System.out.println();
                    }
                }
            }
        }
    }
}
