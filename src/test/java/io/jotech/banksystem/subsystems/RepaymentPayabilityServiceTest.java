package io.jotech.banksystem.subsystems;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepaymentPayabilityServiceTest {

    RepaymentPayabilityService repaymentPayabilityService;

    @BeforeEach
    void setUp() {
        repaymentPayabilityService = new RepaymentPayabilityService();
    }

    @Test
    void calculatePayability() {
        var principle = BigDecimal.valueOf(450_000);
        var income1 = BigDecimal.valueOf(50_000);
        var income2 = BigDecimal.valueOf(300_000);
        var months = 6;

        Assertions.assertAll(
                () -> Assertions.assertFalse(repaymentPayabilityService.calculatePayability(principle,income1,months)),
                () -> Assertions.assertTrue(repaymentPayabilityService.calculatePayability(principle,income2,months))
        );
    }
}