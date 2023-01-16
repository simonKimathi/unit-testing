package io.jotech.banksystem;

import java.math.BigDecimal;

import io.jotech.banksystem.subsystems.CreditRatingService;
import io.jotech.banksystem.subsystems.InterBankPolicyService;
import io.jotech.banksystem.subsystems.RepaymentPayabilityService;
import io.jotech.banksystem.subsystems.RepaymentService;
import io.jotech.banksystem.subsystems.TransferService;
import io.jotech.classicmodels.entity.Customer;

public class BankSystem {
    CreditRatingService creditRatingService = new CreditRatingService();
    InterBankPolicyService interBankPolicyService = new InterBankPolicyService();
    RepaymentPayabilityService repaymentPayabilityService = new RepaymentPayabilityService();
    RepaymentService repaymentService = new RepaymentService();
    TransferService transferService = new TransferService();
    public boolean processLoanApplication(Customer customer, BigDecimal principal, BigDecimal incomeAmount, int repaymentMonths){
        if(!creditRatingService.checkCustomerRating(customer)){//check if credit score passes
            return false;
        }
        if(!repaymentPayabilityService.calculatePayability(principal,incomeAmount,repaymentMonths)){ //check affordability
            return false;
        }
        if(!interBankPolicyService.checkCompliance(customer)){ //check compliance
            return false;
        }
/*         BigDecimal installments = principal.divide(BigDecimal.valueOf(repaymentMonths));
        BigDecimal amountAllowed = incomeAmount.multiply(BigDecimal.valueOf(0.3));
        return amountAllowed.compareTo(installments) > 0; */
        transferService.makeTransfer(principal,customer);
        repaymentService.setupPaymentSchedule(customer,principal,repaymentMonths);
        return true;
    }
}
