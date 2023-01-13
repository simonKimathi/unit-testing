package io.jotech.classicmodels;

import java.util.Arrays;

public class Calculator {
    public int sum(int... numbers){
        return Arrays.stream(numbers).sum();
    }
}
