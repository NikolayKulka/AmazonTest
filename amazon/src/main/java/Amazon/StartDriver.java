package Amazon;

import Core.Amazon_config.amazon_ConfigPage.BaseConfig;
import com.codeborne.selenide.WebDriverRunner;
import helpers.DriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class StartDriver {

  public static DriverProvider driverProvider = new DriverProvider();
  public static BaseConfig page = new BaseConfig();

  @BeforeEach
  public void beforeSuite() throws InterruptedException {
    driverProvider.selectConfiguration(page.getRunEnv);
  }

  @AfterEach
  public void afterMethod() {
    WebDriverRunner.getWebDriver().manage().deleteAllCookies();
    WebDriverRunner.getWebDriver().close();
  }
}
