
class BankAccount {
    protected String ownerName;
    protected String accountNumber;
    protected double balance;

    public BankAccount(String ownerName, String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        // TODO: add amount to balance if amount > 0, return true if successful

        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        // TODO: subtract amount from balance if balance >= amount, return true

        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void displayAccount() {
        // TODO: print "ownerName (accountNumber) | Balance: $balance"
        System.out.println("Owner Name -> " + ownerName);
        System.out.println("Amount -> " + balance);
        // Hint: use String.format("$%.2f", balance) for formatting
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String ownerName, String accountNumber,
            double balance, double interestRate) {
        super(ownerName, accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        // TODO: only allow if (balance - amount) >= 100 (minimum balance rule)

        if (balance - amount >= 100) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void applyInterest() {
        // TODO: add (balance * interestRate / 100) to balance

        interestRate = balance * interestRate / 100;

        System.out.println("Interest rate -> " + interestRate);
    }
}

class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String ownerName, String accountNumber,
            double balance, double overdraftLimit) {
        super(ownerName, accountNumber, balance);
        // TODO: initialize overdraftLimit
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        // TODO: allow if (balance + overdraftLimit) >= amount

        if (balance + overdraftLimit >= 100) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class inheritencePractive {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount("Alice", "SAV-001", 1000, 2.0);
        savings.displayAccount();
        System.out.println("Withdraw $950: " + savings.withdraw(950));
        savings.applyInterest();
        savings.displayAccount();

        System.out.println();

        CheckingAccount checking = new CheckingAccount("Bob", "CHK-002", 500, 300);
        checking.displayAccount();
        System.out.println("Withdraw $700: " + checking.withdraw(700));
        checking.displayAccount();
    }

}
