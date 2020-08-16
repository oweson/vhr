package org.javaboy.vhr.utils.sea521;

import org.javaboy.vhr.domain.Card;
import org.springframework.beans.BeanUtils;

public class BeanUtilsTest {
    public static void main(String[] args) {
        Card card = new Card();
        card.setId(100);
        card.setCode("");
        Card card02 = new Card();


        BeanUtils.copyProperties(card,card02);
        System.out.println(card02);
        // 深的拷贝！！！
        card02.setId(1024);
        System.out.println(card);
        System.out.println(card02);
    }
}
