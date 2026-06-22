import java.lang.reflect.Constructor;

class LoggerOne {
    private static LoggerOne one;

    private LoggerOne() {
        if (one != null) {
            throw new RuntimeException("Can not create another object of singleton class");
        }

        // to check if the code is thread safe

        System.out.println("logger instance created by thread -> " + Thread.currentThread().getName());
    }

    public static LoggerOne getObject() {
        if (one == null) {
            synchronized (LoggerOne.class) {
                if (one == null) {
                    one = new LoggerOne();
                }
            }
        }

        return one;
    }

    public void LogIntoFile(String moduleName, String level, String message) {
        System.out.println("Module -> " + moduleName);
        System.out.println("Level -> " + level);
        System.out.println("Message -> " + message);
        System.out.println("Object -> " + this.hashCode());
        System.out.println("Thread name -> " + Thread.currentThread().getName());
        System.out.println();
    }
}

// make each class run on a thread (className extends Thread and override run()
// method)

class PaymentModule extends Thread {
    // private LoggerOne one = LoggerOne.getObject();

    // public void logPaymentIntoFile() {
    // one.LogIntoFile("PaymentModule", "Info", "This is payment module");
    // }

    @Override
    public void run() {
        LoggerOne one = LoggerOne.getObject();

        one.LogIntoFile("PaymentModule", "Info", "This is payment module");
    }
}

class UserModule extends Thread {
    // private LoggerOne one = LoggerOne.getObject();

    // public void logUserIntoFile() {
    // one.LogIntoFile("UserModule", "Profile", "This is User module");
    // }

    @Override
    public void run() {
        LoggerOne one = LoggerOne.getObject();
        one.LogIntoFile("UserModule", "Profile", "This is User module");
    }
}

class OrderModule extends Thread {

    // private LoggerOne one = LoggerOne.getObject();
    // public void logOrderIntoFile() {

    // one.LogIntoFile("OrderModule", "Cart", "This is Cart Module");
    // }

    @Override
    public void run() {
        LoggerOne one = LoggerOne.getObject();
        one.LogIntoFile("OrderModule", "Cart", "This is Cart Module");
    }
}

public class SingletonPattern {
    public static void main(String[] args) {

        // PaymentModule payment = new PaymentModule();
        // UserModule user = new UserModule();
        // OrderModule order = new OrderModule();

        // payment.logPaymentIntoFile();
        // user.logUserIntoFile();
        // order.logOrderIntoFile();

        PaymentModule t1 = new PaymentModule();
        UserModule t2 = new UserModule();
        OrderModule t3 = new OrderModule();

        t1.start();
        t2.start();
        t3.start();

        LoggerOne objLoggerOne = LoggerOne.getObject();
        LoggerOne objLoggerTwo = LoggerOne.getObject();

        // breaking singleton pattern via reflection API

        // try {
        // Constructor<LoggerOne> constructor =
        // LoggerOne.class.getDeclaredConstructor();
        // constructor.setAccessible(true);
        // LoggerOne newObjectLoggerOne = constructor.newInstance();

        // System.out.println("Reflection instance hash: " +
        // newObjectLoggerOne.hashCode());
        // } catch (Exception e) {
        // System.out.println("EXCEPTION: " + e.getCause().getMessage());
        // }
    }
}
