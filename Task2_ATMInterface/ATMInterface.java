import java.util.Scanner;

class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {

        if (amount > 0) {
            balance += amount;
            System.out.println("Rs." + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid amount!");
        }
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount!");
        }
        else if (amount > balance) {
            System.out.println("Insufficient balance!");
        }
        else {
            balance -= amount;
            System.out.println("Rs." + amount + " withdrawn successfully.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: Rs." + balance);
    }
}

class ATM {

    private final BankAccount account;
    private final Scanner sc;

    public ATM(BankAccount account) {
        this.account = account;
        sc = new Scanner(System.in);
    }

    public void showMenu() {

        try (sc) {
            int choice;
            
            do {
                
                System.out.println("\n==============================");
                System.out.println("          ATM MENU");
                System.out.println("==============================");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");
                System.out.println("==============================");
                
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                
                switch (choice) {
                    
                    case 1 -> account.checkBalance();
                        
                    case 2 -> {
                        System.out.print("Enter amount to deposit: Rs.");
                        double depositAmount = sc.nextDouble();
                        account.deposit(depositAmount);
                    }
                        
                    case 3 -> {
                        System.out.print("Enter amount to withdraw: Rs.");
                        double withdrawAmount = sc.nextDouble();
                        account.withdraw(withdrawAmount);
                    }
                        
                    case 4 -> System.out.println("Thank you for using the ATM!");
                        
                    default -> System.out.println("Invalid choice! Please try again.");
                }
                
            } while (choice != 4);
        }
    }

    public BankAccount getAccount() {
        return account;
    }
}

public class ATMInterface {

    public static void main(String[] args) {

        BankAccount userAccount = new BankAccount(5000);

        ATM atm = new ATM(userAccount);

        atm.showMenu();
    }
}
