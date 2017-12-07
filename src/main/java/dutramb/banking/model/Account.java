package dutramb.banking.model;

import dutramb.banking.exception.InvalidAmountException;
import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private Integer id;
    private Customer customer;
    private BigDecimal balance = BigDecimal.ZERO;

    public Account(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void deposit(BigDecimal amount) throws InvalidAmountException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Deposit amount is invalid=" + amount);
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) throws InvalidAmountException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Withdraw amount is invalid=" + amount);
        }
        if (amount.compareTo(this.balance) == 1) {
            throw new InvalidAmountException("Not sufficient fund. balance=" + this.balance + "; amount=" + amount);
        }
        balance = balance.subtract(amount);
    }

    @Override
    public int hashCode() {
        int hash = 3 * id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
