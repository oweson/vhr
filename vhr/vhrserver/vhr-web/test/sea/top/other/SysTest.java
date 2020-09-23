package sea.top.other;

/**
 * @author chengwanli
 * @date 2020/9/23 11:39
 */


public class SysTest {
    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            System.out.println(os + "windows");
        }
        else {
            System.out.println("linux");
        }
    }
}
