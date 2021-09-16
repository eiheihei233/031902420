public class Test {
    public void main(String[] args) {
        String str = " 法轮工，嘻嘻fuck#￥%……%";
        String str1 = new ChineseChange().convertAll(str);
        System.out.println(str1);
    }
}
