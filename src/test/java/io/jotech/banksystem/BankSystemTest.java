package io.jotech.banksystem;

import java.math.BigDecimal;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import io.jotech.banksystem.subsystems.CreditRatingService;
import io.jotech.banksystem.subsystems.InterBankPolicyService;
import io.jotech.banksystem.subsystems.RepaymentPayabilityService;
import io.jotech.banksystem.subsystems.RepaymentService;
import io.jotech.banksystem.subsystems.TransferService;
import io.jotech.classicmodels.entity.Customer;

class BankSystemTest {

    BankSystem bankSystem;

    @BeforeEach
    void setup(){
        bankSystem = new BankSystem();
    }


    @Test
    void processLoanApplication() {
        //given inputs
        BigDecimal principal = BigDecimal.valueOf(450_000);
        BigDecimal income = BigDecimal.valueOf(50_000);
        int month = 6;
        Customer customer = Customer.builder()
                .customerName("simon")
                .customerNumber(1)
                .city("Meru")
                .creditLimit(BigDecimal.valueOf(600_000))
                .build();

        //when
        boolean isProcessed = bankSystem.processLoanApplication(customer, principal, income, month);

        //then
        boolean expected = false;

        //assertion/testing
        Assertions.assertEquals(expected, isProcessed, () -> "The loan should not be processed given the principle is " + principal + " and income is" + income);

    }
}