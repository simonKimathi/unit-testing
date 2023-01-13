package io.jotech.classicmodels;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;
    @BeforeEach
    public void inti(){
        calculator = new Calculator();
    }

    @Test
    @DisplayName("testSumNumbers")
    void testSumNumbers() {
        int[] numbers = new int[]{12,13,10};
        int expected = 35;
        int actual = calculator.sum(numbers);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

}