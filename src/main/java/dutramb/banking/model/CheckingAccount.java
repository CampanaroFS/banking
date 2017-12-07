package dutramb.banking.model;

import dutramb.banking.exception.InvalidAccountException;
import dutramb.banking.exception.InvalidAmountException;
import java.math.BigDecimal;

public class CheckingAccount extends Account {

    private BigDecimal limit;

    public CheckingAccount(Customer customer, BigDecimal limit) {
        super(customer);
        this.limit = limit.negate();
    }

    @Override
    public void withdraw(BigDecimal amount) throws InvalidAmountException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new InvalidAmountException("Withdraw amount is invalid=" + amount);
        }
        BigDecimal result = this.getBalance().subtract(amount);
        if (result.compareTo(limit) == -1) {
            throw new InvalidAmountException("Withdraw denied. Account limit exceeded.");
        }

        this.setBalance(result);

    }

    public void transferMoney(BigDecimal amount, Account receiver) throws InvalidAmountException, InvalidAccountException {
        if (receiver == null) {
            throw new InvalidAccountException("Provide a valid account to transfer.");
        }

        this.withdraw(amount);
        receiver.deposit(amount);
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
