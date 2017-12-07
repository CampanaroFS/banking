package dutramb.banking.builders;

import dutramb.banking.exception.InvalidAmountException;
import dutramb.banking.model.Customer;
import dutramb.banking.model.SavingsAccount;
import java.math.BigDecimal;

public class SavingsAccountBuilder {

    private SavingsAccount savingsAccount;

    private SavingsAccountBuilder() {

    }

    public static SavingsAccountBuilder account() {
        SavingsAccountBuilder builder = new SavingsAccountBuilder();
        builder.savingsAccount = new SavingsAccount(new Customer());
        return builder;
    }

    public SavingsAccountBuilder withBalance(String amount) throws InvalidAmountException {
        this.savingsAccount.deposit(new BigDecimal(amount));
        return this;
    }

    public SavingsAccountBuilder withInterestRate(String rate) throws InvalidAmountException {
        this.savingsAccount.setInterestRate(new BigDecimal(rate));
        return this;
    }

    public SavingsAccount build() {
        return this.savingsAccount;
    }
}
