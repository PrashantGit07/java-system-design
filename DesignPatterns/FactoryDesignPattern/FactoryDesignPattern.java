
//abstract product
abstract class generateReport {
    abstract void generate();
}

// concrete product
class pdfGenerate extends generateReport {
    @Override
    public void generate() {
        System.out.println("this is pdf generator");
    }
}

class excelGenerate extends generateReport {
    @Override
    public void generate() {
        System.out.println("this is excel generator");
    }
}

// abstract factory
abstract class reportFactory {
    abstract generateReport generateReportMethod(String type);
}

// report editing factory - in future => this might first need the report before
// editing it
// it will also need to callg generate report....

// Why Abstract Factory?

// Client only knows the interface, never the concrete class.
// You can swap one factory with another anytime — no client code changes.
// New factories = new subclass. That's it.
// Keeps object creation logic separate from business logic.

class concreteReportFactory extends reportFactory {
    @Override
    generateReport generateReportMethod(String type) {
        if (type == "pdf") {
            return new pdfGenerate();
        } else {
            return new excelGenerate();
        }
    }
}

public class FactoryDesignPattern {
    public static void main(String[] args) {
        // now client will ask factory to just generate report based on provided type
        // client does not care how report is generated and where , it will now be the
        // factory who decides that how report will be printed

        concreteReportFactory reportFactory = new concreteReportFactory();

        generateReport report = reportFactory.generateReportMethod("pdf");

        report.generate();
    }
}
