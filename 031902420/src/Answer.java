public class Answer {

    private int line;
    private static int total = 0;
    private String fileLine;
    private String maskWord;

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
