package sea.top.utils;

import java.util.Arrays;
import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/11/11 23:17
 */
public class Demo1ArraysTest {
    public static void main(String[] args) {
        String s = "1,2,3,4,5,6";
        List<String> stringList = Arrays.asList(s.split(","));
        for (String s1 : stringList) {
            System.out.println(s1);

        }
    }
}
