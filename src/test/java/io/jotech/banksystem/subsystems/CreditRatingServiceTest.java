package io.jotech.banksystem.subsystems;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import io.jotech.classicmodels.entity.Customer;

class CreditRatingServiceTest {

    CreditRatingService creditRatingService;

    @BeforeEach
    public  void setup() {
        creditRatingService = new CreditRatingService();
    }

    @Test
    void checkCustomerRating() {
        //given
        Customer customer1 = Customer.builder()
                .customerName("simon")
                .customerNumber(1)
                .city("Meru")
                .creditLimit(BigDecimal.valueOf(400_000))
                .build();
        Customer customer2 = Customer.builder()
                .customerName("simon")
                .customerNumber(1)
                .city("Meru")
                .creditLimit(BigDecimal.valueOf(600_000))
                .build();
        Customer customer3 = Customer.builder()
                .customerName("simon")
                .customerNumber(1)
                .city("Meru")
                .creditLimit(BigDecimal.valueOf(300_000))
                .build();

        //when
//        boolean passes = creditRatingService.checkCustomerRating(customer1);

        //then
        boolean expected = true;
//        Assertions.assertEquals(expected,passes);

        Assertions.assertAll(
                ()->assertEquals(expected,creditRatingService.checkCustomerRating(customer1)),
                ()->assertEquals(expected,creditRatingService.checkCustomerRating(customer2)),
                ()-> assertFalse(creditRatingService.checkCustomerRating(customer3))
        );
    }
}