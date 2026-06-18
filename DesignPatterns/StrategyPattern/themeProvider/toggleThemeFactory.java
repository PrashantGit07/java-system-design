
//abstract product
abstract class themeProvider {
    abstract void displayTheme();
}

// concrete products

class LightTheme extends themeProvider {
    @Override
    public void displayTheme() {
        System.out.println("this is light theme with dark text , background white , font style - sans");
    }
}

class DarkTheme extends themeProvider {
    @Override
    public void displayTheme() {
        System.out.println("this is dark theme with light text , background dark  , font style - serif");
    }
}

class HighContrastTheme extends themeProvider {
    @Override
    public void displayTheme() {
        System.out.println(
                "this is high contrast theme with light dark text , gradiented backgroud and font style - serial");
    }
}

// abstract factory
abstract class themeFactory {
    abstract themeProvider displayThemeBasedOnType(String dayTime);
}

class AutoTheme extends themeFactory {
    @Override
    public themeProvider displayThemeBasedOnType(String dayTime) {
        if (dayTime.equals("Day")) {
            return new LightTheme();
        } else {
            return new DarkTheme();
        }
    }
}

class ManualToggle extends themeFactory {
    @Override
    public themeProvider displayThemeBasedOnType(String type) {
        if (type.equalsIgnoreCase("Manual")) {
            return new HighContrastTheme();
        } else {
            return null;
        }
    }
}

public class toggleThemeFactory {

    public static void main(String[] args) {
        String typeOfDay1 = "Day";
        String typeOfDay2 = "Manual";

        themeFactory auThemeFactory = new AutoTheme();
        themeFactory manFactory = new ManualToggle();

        themeProvider provider1 = auThemeFactory.displayThemeBasedOnType(typeOfDay1);
        themeProvider provider2 = manFactory.displayThemeBasedOnType(typeOfDay2);

        provider1.displayTheme();
        provider2.displayTheme();

    }
}
