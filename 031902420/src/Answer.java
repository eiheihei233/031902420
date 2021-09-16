public class Answer {

    private int line;   //屏蔽字所在行数
    private static int total = 0;  //屏蔽字总数
    private String fileLine;   //屏蔽字在原文中的显示
    private String maskWord;  //屏蔽字

    public static int getTotal() {
        return total;
    }

    public static void setTotal(int total) {
        Answer.total = total;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getFileLine() {
        return fileLine;
    }

    public void setFileLine(String fileLine) {
        this.fileLine = fileLine;
    }

    public String getMaskWord() {
        return maskWord;
    }

    public void setMaskWord(String maskWord) {
        this.maskWord = maskWord;
    }
}
