package dutramb.banking.model;

import dutramb.banking.builders.CheckingAccountBuilder;
import dutramb.banking.exception.InvalidAccountException;
import dutramb.banking.exception.InvalidAmountException;
import java.math.BigDecimal;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CheckingAccountTest {

    // withdraw - tested again because it was overridden in CheckingAccount class
    @Test(expected = InvalidAmountException.class)
    public void withdrawFailsIfAmountIsNull() throws InvalidAmountException {
        CheckingAccount a = CheckingAccountBuilder.account("100.00").build();
        a.withdraw(null);
    }

    @Test(expected = InvalidAmountException.class)
    public void withdrawFailsIfAmountIsZero() throws InvalidAmountException {
        CheckingAccount a = CheckingAccountBuilder.account().build();
        a.withdraw(BigDecimal.ZERO);
    }

    @Test
    public void withdrawValidAmountsInARow() throws InvalidAmountException {
        CheckingAccount a = CheckingAccountBuilder.account("100.00").withBalance("100.0").build();
        a.withdraw(new BigDecimal("25.50"));
        assertTrue(new BigDecimal("74.50").compareTo(a.getBalance()) == 0);
    }

    @Test(expected = InvalidAmountException.class)
    public void withdrawWithLimitExceeded() throws InvalidAmountException {
        CheckingAccount a = CheckingAccountBuilder.account("100.00").withBalance("10.0").build();
        a.withdraw(new BigDecimal("110.10"));
    }

    // transfer
    @Test(expected = InvalidAccountException.class)
    public void transferWithInvalidAccount() throws InvalidAmountException, InvalidAccountException {
        CheckingAccount a = CheckingAccountBuilder.account("100.00").withBalance("10.0").build();
        a.transferMoney(new BigDecimal("1.00"), null);
    }

    @Test(expected = InvalidAmountException.class)
    public void transferWithLimitExceeded() throws InvalidAmountException, InvalidAccountException {
        CheckingAccount sender = CheckingAccountBuilder.account("10.00").withBalance("10.0").build();
        CheckingAccount receiver = CheckingAccountBuilder.account().withBalance("10.0").build();
        sender.transferMoney(new BigDecimal("21.00"), receiver);
    }

    @Test
    public void transferSucceed() throws InvalidAmountException, InvalidAccountException {
        CheckingAccount sender = CheckingAccountBuilder.account().withBalance("10.0").build();
        CheckingAccount receiver = CheckingAccountBuilder.account().withBalance("10.0").build();
        sender.transferMoney(new BigDecimal("1.00"), receiver);
        assertTrue(new BigDecimal("9.00").compareTo(sender.getBalance()) == 0);
        assertTrue(new BigDecimal("11.00").compareTo(receiver.getBalance()) == 0);
    }
    
}
