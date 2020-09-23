package sea.top.imooc.guava;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2020/2/14 21:04
 */
public class Demo1NpeTest {
    public static void main(String[] args) {
        // 1 低频使用
        System.out.println(Optional.empty());
        // 2 不可为空
        System.out.println(Optional.of("ha"));
        // 3 可以为空,不要调用isPresent()因为和之前的！null没有区别；
        // Optional.ofNullable("ha").isPresent();
        // map.filter,mapfilter
        Optional.ofNullable("ha").ifPresent(System.out::println);
        // 引用缺失
        Optional<String> stringOptional = Optional.ofNullable("hi");
        //  1 设置默认值
        stringOptional.orElse("npe");
        // 2 自定义引用缺丢失的提示信息
        String s = stringOptional.orElseGet(() -> {
            return "hi";
        });
        // 3 null抛出异常
//        String hi = stringOptional.orElseThrow(() -> {
//            throw new RuntimeException("hi");
//        });
        // 处理空的集合,无论是否为空就是流，不会报错！！！
        ArrayList<Integer> integerArrayList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional.ofNullable(integerArrayList).map(List::stream).
                orElseGet(Stream::empty).forEach(System.out::println);

    }
}
