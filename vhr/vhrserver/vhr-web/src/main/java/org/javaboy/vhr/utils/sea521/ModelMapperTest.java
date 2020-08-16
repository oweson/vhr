package org.javaboy.vhr.utils.sea521;

import org.javaboy.vhr.domain.Card;
import org.modelmapper.ModelMapper;


public class ModelMapperTest {
    public static void main(String[] args) {
        Card card = new Card();
        card.setId(100);
        card.setCode("abc");
        Card card02 = new Card();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(card,card02);
        System.out.println(card02);
    }
}
