package sea.top.http;



import org.javaboy.vhr.utils.sea521.DateUtil;

import java.util.Date;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/8/8 7:46
 */
public class DateUtilsTest {
    public static void main(String[] args) {
        String s = DateUtil.beforeMinuteToNowDate(21);
        System.out.println(s);
        Date lastDayOfWeek = DateUtil.getLastDayOfWeek(new Date());
        System.out.println(lastDayOfWeek);
        Date lastDayOfLastWeek = DateUtil.getLastDayOfLastWeek(new Date());
        System.out.println(lastDayOfLastWeek);

    }
}
