package dutramb.banking.model;

import dutramb.banking.builders.AccountBuilder;
import dutramb.banking.exception.InvalidAmountException;
import java.math.BigDecimal;
import static org.junit.Assert.*;
import org.junit.Test;

public class AccountTest {

    // deposit
    @Test(expected = InvalidAmountException.class)
    public void depositFailsIfAmountIsNull() throws InvalidAmountException {
        Account a = AccountBuilder.account().build();
        a.deposit(null);
    }

    @Test(expected = InvalidAmountException.class)
    public void depositFailsIfAmountIsLessThanZero() throws InvalidAmountException {
        Account a = AccountBuilder.account().build();
        a.deposit(new BigDecimal("-0.1"));
    }

    @Test(expected = InvalidAmountException.class)
    public void depositFailsIfAmountIsZero() throws InvalidAmountException {
        Account a = AccountBuilder.account().build();
        a.deposit(BigDecimal.ZERO);
    }

    @Test
    public void depositValidAmountsInARowSucess() throws InvalidAmountException {
        Account a = AccountBuilder.account().build();
        BigDecimal amount = new BigDecimal("2.50");
        a.deposit(amount);
        assertEquals(amount, a.getBalance());

        a.deposit(amount);
        assertEquals(new BigDecimal("5.00"), a.getBalance());
    }

    //withdraw
    @Test(expected = InvalidAmountException.class)
    public void withdrawFailsIfAmountIsNull() throws InvalidAmountException {
        Account a = AccountBuilder.account().build();
        a.withdraw(null);
    }

    @Test(expected = InvalidAmountException.class)
    public void withdrawFailsIfAmountIsLessThanZero() throws InvalidAmountException {
        Account a = AccountBuilder.account().build();
        a.withdraw(new BigDecimal("-0.1"));
    }

    @Test(expected = InvalidAmountException.class)
    public void withdrawFailsIfAmountIsZero() throws InvalidAmountException {
        Account a = AccountBuilder.account().build();
        a.withdraw(BigDecimal.ZERO);
    }

    @Test
    public void withdrawValidAmounts() throws InvalidAmountException {
        Account a = AccountBuilder.account().withBalance(new BigDecimal("100.0")).build();
        a.withdraw(new BigDecimal("25.50"));
        assertEquals(new BigDecimal("74.50"), a.getBalance());
    }

}
