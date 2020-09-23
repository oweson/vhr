package sea.top.other;

import org.junit.Test;

public class Demo1 {
    @Test
    public void demo2(){
        for (int i = 0; i < 10; i++) {
            System.out.println((Integer) i);
        }
    }
    @Test
    public void demo1() {
        // 条件运算符会做数值类型的类型提升
        Object o1 = true ? new Integer(1) : new Double(2.0);
        Object o2;
        if (true) {
            o2 = new Integer(1);
        } else {
            o2 = new Double(2.0);
        }
        System.out.println(o1);
        System.out.println(o2);

    }
    public static int addDigits(int num) {

        while(num >= 10) {
            int temp = 0;
            while(num > 0) {
                temp += num % 10;
                num /= 10;
            }
            num = temp;
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println(addDigits(1024));
    }
}
