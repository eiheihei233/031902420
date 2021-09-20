public class Test {
    public static void main(String[] args) {
        String str = "法轮工";
        for (int i = 0; i < str.length(); i++) {
            String str1 = str.substring(i,i+1);
            System.out.println(str1);
        }
    }
}
