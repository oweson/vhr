package sea.top.imooc.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2020/2/14 21:31
 */
public class Demo2UnmodiableList {
    public static void main(String[] args) {
        Collection<Object> objects = Collections.unmodifiableCollection(Lists.newArrayList(1,2,3));
       // objects.remove(0);
        // java.lang.UnsupportedOperationException 不可变的！！！
        // guava三种创建不可变的集合！！！
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);
        // 1 第一创建已经存在的集合
        ImmutableSet<Integer> integerImmutableSet = ImmutableSet.copyOf(integers);
        System.out.println(integerImmutableSet);
        // 2 第二种直接的创建
        ImmutableSet<Integer> immutableSet = ImmutableSet.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // 3 第三种，建造者模式创建
        ImmutableSet.Builder<Object> hi = ImmutableSet.builder().add(1).add("hi").add(Lists.newArrayList(1, 2, 3));
        System.out.println(hi);


    }
}
