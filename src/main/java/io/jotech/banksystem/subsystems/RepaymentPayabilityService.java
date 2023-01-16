package io.jotech.banksystem.subsystems;

import java.math.BigDecimal;
import java.math.MathContext;

public class RepaymentPayabilityService {
    public boolean calculatePayability(BigDecimal principal, BigDecimal incomeAmount, int repaymentMonths){

        var interestRate=BigDecimal.valueOf(0.1);
        var interest=interestRate.multiply(principal).multiply(BigDecimal.valueOf((double) repaymentMonths /12));
        var repaymentAmount = principal.add(interest);

        BigDecimal installments = repaymentAmount.divide(BigDecimal.valueOf(repaymentMonths), MathContext.DECIMAL32);
        BigDecimal amountAllowed = incomeAmount.multiply(BigDecimal.valueOf(0.3));
        return amountAllowed.compareTo(installments) >= 0;
    }
}
