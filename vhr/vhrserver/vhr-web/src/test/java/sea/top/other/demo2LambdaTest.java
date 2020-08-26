package sea.top.other;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class demo2LambdaTest {
    public static final String demo1(){
        List<Integer> collect = Lists.newArrayList("Java 8", "Lambdas", "In", "Action"
        ).stream().map(String::length).collect(Collectors.toList());
        for (Integer integer : collect) {
            System.out.println(integer);

        }
        return null;

    }

    public static void main(String[] args) {
        demo1();
    }
}
