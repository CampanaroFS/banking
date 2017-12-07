package dutramb.banking.model;

import dutramb.banking.exception.InvalidAmountException;
import java.math.BigDecimal;

public class SavingsAccount extends Account {

    private static BigDecimal INTEREST_RATE = BigDecimal.ZERO;

    public SavingsAccount(Customer customer) {
        super(customer);
    }

    public void provideInterest() throws InvalidAmountException {
        BigDecimal interest = this.getBalance().multiply(INTEREST_RATE);
        if (!interest.equals(BigDecimal.ZERO)) {
            this.deposit(interest);
        }
    }

    public static void provideInterestToAllUsers() {
    }

    public static void setInterestRate(BigDecimal newInterestRate) {
        INTEREST_RATE = newInterestRate;
    }

}
