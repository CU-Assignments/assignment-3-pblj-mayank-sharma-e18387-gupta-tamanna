import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class ATMWithdrawal {
    private static final int CORRECT_PIN = 14867;
    private static double balance = 5000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
            if (pin != CORRECT_PIN) {
                throw new InvalidPinException("Error: Invalid PIN.");
            }

            System.out.print("Withdraw Amount: ");
            double amount = scanner.nextDouble();
            if (amount > balance) {
                throw new InsufficientBalanceException("Error: Insufficient balance.");
            }

            balance -= amount;
            System.out.println("Withdrawal successful! Remaining balance: " + balance);
        } catch (InvalidPinException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        } finally {
            System.out.println("Current balance: " + balance);
            scanner.close();
        }
    }
}
