package helpers;

import com.codeborne.selenide.WebDriverProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidDriverProvider implements WebDriverProvider {

    private static final Logger LOGGER = LogManager.getLogger(SelenoidDriverProvider.class);

    @Override
    public WebDriver createDriver(DesiredCapabilities theCapabilities) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("71.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("screenResolution", "1600x1024x24");
        capabilities.setAcceptInsecureCerts(true);

        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://").toURL(),
                    capabilities
            );
            SessionId session = driver.getSessionId();
            System.out.println(session);
            LOGGER.debug("SESSION ID: " + session.toString());

            driver.setFileDetector(new LocalFileDetector());
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

