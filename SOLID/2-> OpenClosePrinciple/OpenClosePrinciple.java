
//open close principle says a class should be open for extension , and closed for modification
//we can achieve this through interface/abstraction/polymorhphism

abstract class saveToDbStorage {
    public abstract void saveToDb();
}

// see here , we made the class saveToDbStorage abstract , now to add new
// features(suppose later we need to save data to aws s3) we do not need to
// modify it , we can just directly extend this class and make another class
// savetoS3 and override its method (polymorphism) , to write its logic

class savetoSqlDb extends saveToDbStorage {
    @Override
    public void saveToDb() {
        System.out.println("Saving to sql db");
    }
}

class saveToMongoDb extends saveToDbStorage {
    @Override
    public void saveToDb() {
        System.out.println("Saving to mongodb");
    }
}

class saveToFile extends saveToDbStorage {
    @Override
    public void saveToDb() {
        System.out.println("Saving to file");
    }
}

public class OpenClosePrinciple {
    public static void main(String[] args) {
        saveToDbStorage sd = new savetoSqlDb();

        sd.saveToDb();

        saveToDbStorage sd2 = new saveToMongoDb();
        sd2.saveToDb();

        saveToDbStorage sd3 = new saveToFile();

        sd3.saveToDb();
    }
}
