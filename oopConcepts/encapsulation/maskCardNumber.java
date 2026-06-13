
class processPayments {
    private String cardNumber;
    private String userName;

    public processPayments(String cardNumber, String username) {
        this.userName = username;
        this.cardNumber = maskedCardNumber(cardNumber);
    }

    private String maskedCardNumber(String cardNumber) {
        return "****-****-****" + cardNumber.substring(cardNumber.length() - 4);
    }

    public void printDetails() {
        System.out.println("Card Number -> " + cardNumber);
        System.out.println("User Name -> " + userName);
    }
}

public class maskCardNumber {
    public static void main(String[] args) {
        processPayments p1 = new processPayments("1234567812345677", "David");
        p1.printDetails();
    }

}
