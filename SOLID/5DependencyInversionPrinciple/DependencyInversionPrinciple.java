//High-level modules should not depend on low-level modules. Both should depend on abstractions (interfaces).

//Here, NotificationService directly depends on EmailService.

// class EmailService {
//     public void send(String message) {
//         System.out.println("Sending Email: " + message);
//     }
// }

// class NotificationService {

//     private EmailService emailService = new EmailService();

//     public void notifyUser(String message) {
//         emailService.send(message);
//     }
// }

// class Main {
//     public static void main(String[] args) {
//         NotificationService service = new NotificationService();
//         service.notifyUser("Order Placed");
//     }
// }

// Problem
// If tomorrow you want SMS or WhatsApp notifications:

// private SmsService smsService = new SmsService();

// You must modify NotificationService.
// The high-level business logic is tightly coupled to a low-level implementation.

//DIP - followed

//Depend on an interface.

interface MessageService {
    void send(String message);
}

class EmailService implements MessageService {

    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class SmsService implements MessageService {

    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class NotificationService {

    private MessageService messageService;

    public NotificationService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String message) {
        messageService.send(message);
    }
}

public class DependencyInversionPrinciple {
    public static void main(String[] args) {

        MessageService email = new EmailService();
        NotificationService service1 = new NotificationService(email);

        service1.notifyUser("Order Placed");

        MessageService sms = new SmsService();
        NotificationService service2 = new NotificationService(sms);

        service2.notifyUser("Order Delivered");
    }
}

// dependency flow
// NotificationService
// |
// v
// MessageService (Interface)
// / \
// / \
// EmailService SmsService