package dutramb.banking.builders;

import dutramb.banking.model.Account;
import dutramb.banking.model.Customer;
import java.math.BigDecimal;

public class AccountBuilder {

    private Account account;

    private AccountBuilder() {

    }

    public static AccountBuilder account() {
        AccountBuilder builder = new AccountBuilder();
        builder.account = new Account(new Customer());
        return builder;
    }

    public AccountBuilder withBalance(BigDecimal bigDecimal) {
        this.account.setBalance(bigDecimal);
        return this;
    }

    public Account build() {
        return this.account;
    }
}
