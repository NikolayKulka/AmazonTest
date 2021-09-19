package helpers;

import com.codeborne.selenide.Configuration;

public class DriverProvider {

    public void selectConfiguration(String runEnv) throws InterruptedException {
        if (runEnv.equals("local")) {
            setupLocalConfiguration();
        } else if (runEnv.equals("remote")) {
            setupRemoteConfiguration();
        } else {
            throw new InterruptedException("Unknown configuration");
        }
    }

    private static void setupLocalConfiguration() {
        Configuration.browser = ("chrome");
        Configuration.browserVersion = ("75");
        Configuration.holdBrowserOpen = true;
        Configuration.headless = false;
        setupBaseConfiguration();
    }

    private static void setupRemoteConfiguration() {
        /*Configuration.browser = ("helpers.SelenoidDriverProvider");*/
        setupBaseConfiguration();
    }

    private static void setupBaseConfiguration() {
        Configuration.screenshots = true;
        Configuration.timeout = 25000;
        Configuration.browserSize = ("1700x1024");
        Configuration.startMaximized = true;
    }
}
