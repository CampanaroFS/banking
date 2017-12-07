package dutramb.banking.builders;

import java.math.BigDecimal;

import dutramb.banking.exception.InvalidAmountException;
import dutramb.banking.model.CheckingAccount;
import dutramb.banking.model.Customer;

public class CheckingAccountBuilder {

    private CheckingAccount checkingAccount;

    private CheckingAccountBuilder() {

    }

    public static CheckingAccountBuilder account() {
        CheckingAccountBuilder builder = new CheckingAccountBuilder();
        builder.checkingAccount = new CheckingAccount(new Customer(), new BigDecimal("100.00"));
        return builder;
    }

    public static CheckingAccountBuilder account(String limit) {
        CheckingAccountBuilder builder = new CheckingAccountBuilder();
        builder.checkingAccount = new CheckingAccount(new Customer(), new BigDecimal(limit));
        return builder;
    }

    public static CheckingAccountBuilder accountWithCustomer(Customer customer) {
        CheckingAccountBuilder builder = new CheckingAccountBuilder();
        builder.checkingAccount = new CheckingAccount(customer, new BigDecimal("100.00"));
        return builder;
    }

    public CheckingAccountBuilder withBalance(String amount) throws InvalidAmountException {
        this.checkingAccount.deposit(new BigDecimal(amount));
        return this;
    }

    public CheckingAccount build() {
        return this.checkingAccount;
    }
}
