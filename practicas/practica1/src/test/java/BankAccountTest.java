import bank.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount(100);
    }

    @Test
    public void testWithdrawSufficientBalance() {
        // Arrange
        int withdrawAmount = 50;

        // Act
        boolean result = account.withdraw(withdrawAmount);

        // Assert
        Assertions.assertTrue(result);
        Assertions.assertEquals(50, account.getBalance());
    }

    @Test
    public void testWithdrawInsufficientBalance() {
        // Arrange
        int withdrawAmount = 150;

        // Act
        boolean result = account.withdraw(withdrawAmount);

        // Assert
        Assertions.assertFalse(result);
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void testWithdrawNegativeAmount() {
        // Arrange
        int withdrawAmount = -50;

        // Act
        Executable executable = () -> account.withdraw(withdrawAmount);

        // Assert
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void testDeposit() {
        // Arrange
        int depositAmount = 50;

        // Act
        double result = account.deposit(depositAmount);

        // Assert
        Assertions.assertEquals(150, result);
        Assertions.assertEquals(150, account.getBalance());
    }

    @Test
    public void testDepositNegativeAmount() {
        // Arrange
        int depositAmount = -50;

        // Act
        Executable executable = () -> account.deposit(depositAmount);

        // Assert
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void testPayment() {
        // Arrange
        double total_amount = 10000;
        double interest = 0.05;
        int npayments = 12;

        // Act
        double result = account.payment(total_amount, interest, npayments);

        // Assert
        Assertions.assertEquals(1128.2541002081534, result, 0.001);
    }

    @Test
    public void testPending() {
        // Arrange
        double amount = 10000;
        double inte = 0.05;
        int npayments = 12;
        int month = 6;

        // Act
        double result = account.pending(amount, inte, npayments, month);

        // Assert
        Assertions.assertEquals(5726.670386288504, result);
    }

    @Test
    public void testPaymentWithNegativeInterest() {
        // Arrange
        double totalAmount = 10000;
        double interest = -0.01;
        int npayments = 12;

        // Act
        Executable executable = () -> account.payment(totalAmount, interest, npayments);

        // Assert
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void testPendingWithNegativeInterest() {
        // Arrange
        double amount = 10000;
        double inte = -1;
        int npayments = 12;
        int month = 6;

        // Act
        Executable executable = () -> account.pending(amount, inte, npayments, month);

        // Assert
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void testPaymentWithZeroNPayments() {
        // Arrange
        double total_amount = 10000;
        double interest = 0.05;

        // Act
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.payment(total_amount, interest, 0);
        });

        // Assert
        Assertions.assertEquals("Invalid input values", exception.getMessage());
    }

    @Test
    public void testPendingWithZeroNPayments() {
        // Arrange
        double amount = 10000;
        double inte = 0.05;
        int month = 6;

        // Act
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pending(amount, inte, 0, month);
        });

        // Assert
        Assertions.assertEquals("Invalid input values", exception.getMessage());
    }

    @Test
    public void testPendingWithNegativeMonth() {
        // Arrange
        double amount = 10000;
        double inte = 0.05;
        int npayments = 12;
        int month = -1;

        // Act
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pending(amount, inte, npayments, month);
        });

        // Assert
        Assertions.assertEquals("Invalid input values", exception.getMessage());
    }

    @Test
    public void testPaymentWithNegativeTotalAmount() {
        // Arrange
        double totalAmount = -10000;
        double interest = 0.05;
        int npayments = 12;

        // Act
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.payment(totalAmount, interest, npayments);
        });

        // Assert
        Assertions.assertEquals("Invalid input values", exception.getMessage());
    }

    @Test
    public void testPendingWithNegativeTotalAmount() {
        // Arrange
        double amount = -10000;
        double inte = 0.05;
        int npayments = 12;
        int month = 6;

        // Act
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pending(amount, inte, npayments, month);
        });

        // Assert
        Assertions.assertEquals("Invalid input values", exception.getMessage());
    }

    @Test
    public void testPaymentWithNegativeInterestRate() {
        // Arrange
        double totalAmount = 10000;
        double interest = -0.05;
        int npayments = 12;

        // Act
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.payment(totalAmount, interest, npayments);
        });

        // Assert
        Assertions.assertEquals("Invalid input values", exception.getMessage());
    }

    @Test
    public void testPendingWithNegativeInterestRate() {
        // Arrange
        double amount = 10000;
        double inte = -0.05;
        int npayments = 12;
        int month = 6;

        // Act
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pending(amount, inte, npayments, month);
        });

        // Assert
        Assertions.assertEquals("Invalid input values", exception.getMessage());
    }

    @Test
    public void testPaymentWithInterestRateGreaterThanOne() {
        // Arrange
        double totalAmount = 10000;
        double interest = 1.01;
        int npayments = 12;

        // Act
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.payment(totalAmount, interest, npayments);
        });

        // Assert
        Assertions.assertEquals("Invalid input values", exception.getMessage());
    }

    @Test
    public void testPendingWithInterestRateGreaterThanOne() {
        // Arrange
        double amount = 10000;
        double inte = 1.01;
        int npayments = 12;
        int month = 6;

        // Act
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pending(amount, inte, npayments, month);
        });

        // Assert
        Assertions.assertEquals("Invalid input values", exception.getMessage());
    }
}