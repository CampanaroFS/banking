package dutramb.banking.model;

import dutramb.banking.builders.SavingsAccountBuilder;
import dutramb.banking.exception.InvalidAmountException;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class SavingsAccountTest {

    @Test
    public void provideInterestSuccess() throws InvalidAmountException {
        SavingsAccount savingsAccount = SavingsAccountBuilder.account()
                .withBalance("100.00")
                .withInterestRate("0.01")
                .build();
        savingsAccount.provideInterest();
        assertTrue(new BigDecimal("101.00").compareTo(savingsAccount.getBalance()) == 0);
    }

}
