package sea.top.other;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/8/22 19:58
 */
public class BreakTest {
    public static final void demo1(){
        int total=0;
        for (int i = 0; i < 10; i++) {
            if(i==4){
                break;
            }

        }
    }
    public static void main(String[] args) {
        BreakTest breakTest = new BreakTest();
        BreakTest.demo1();

    }
}
