import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LoggerOne implements Serializable {
    private static volatile LoggerOne one; // volatile for thread safety

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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton object");
    }

    protected Object readResolve() {
        return getObject(); // prevents new instances during deserialization , returns the same instance
    }

    public synchronized void LogIntoFile(String moduleName, String level, String message) {
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
    public static void main(String[] args) throws Exception { // Added throws Exception

        // PaymentModule payment = new PaymentModule();
        // UserModule user = new UserModule();
        // OrderModule order = new OrderModule();

        // payment.logPaymentIntoFile();
        // user.logUserIntoFile();
        // order.logOrderIntoFile();

        // PaymentModule t1 = new PaymentModule();
        // UserModule t2 = new UserModule();
        // OrderModule t3 = new OrderModule();

        // t1.start();
        // t2.start();
        // t3.start();

        // LoggerOne objLoggerOne = LoggerOne.getObject();
        // LoggerOne objLoggerTwo = LoggerOne.getObject();

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

        // using excuter serivce instead of manually creating objects
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(new PaymentModule());
        executorService.execute(new UserModule());
        executorService.execute(new OrderModule());

        // Wait for threads to finish
        Thread.sleep(1000);
        executorService.shutdown(); // Fixed: executorService instead of executor

        System.out.println("\n========== 2. SAME INSTANCE TEST ==========");
        LoggerOne obj1 = LoggerOne.getObject();
        LoggerOne obj2 = LoggerOne.getObject();
        LoggerOne obj3 = LoggerOne.getObject();

        System.out.println("All same instance? " +
                (obj1 == obj2 && obj2 == obj3));
        System.out.println("Hash: " + obj1.hashCode() + " | " +
                obj2.hashCode() + " | " + obj3.hashCode());

        System.out.println("\n========== 3. REFLECTION ATTACK TEST ==========");
        try {
            Constructor<LoggerOne> constructor = LoggerOne.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            LoggerOne brokenInstance = constructor.newInstance();
            System.out.println("REFLECTION SUCCEEDED (BAD): " + brokenInstance.hashCode());
        } catch (Exception e) {
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            System.out.println("REFLECTION BLOCKED: " + cause.getMessage());
        }

        System.out.println("\n========== 4. CLONING ATTACK TEST ==========");
        try {
            LoggerOne clone = (LoggerOne) obj1.clone();
            System.out.println("CLONE SUCCEEDED (BAD): " + clone.hashCode());
        } catch (Exception e) {
            System.out.println("CLONE BLOCKED: " + e.getMessage());
        }

        System.out.println("\n========== 5. SERIALIZATION TEST ==========");

        // Serialize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj1);
        oos.close();

        // Deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        LoggerOne deserializedObj = (LoggerOne) ois.readObject();
        ois.close();

        System.out.println("Original hash: " + obj1.hashCode());
        System.out.println("Deserialized hash: " + deserializedObj.hashCode());
        System.out.println("Same instance? " + (obj1 == deserializedObj));
    }
}