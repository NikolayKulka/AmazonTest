package Amazon_ui;

import Amazon.Base.ui_BasePage;
import Amazon.StartDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Epic("Slides")
@Feature("Slides")
public class UiTests extends StartDriver {

  ui_BasePage basePage = new ui_BasePage();


  @Tag("regression")
  @Test
  @Story("Check slides")
  @Description("Check slides description")
  @Execution(ExecutionMode.CONCURRENT)
  public void TC_0001_CheckSlides() throws InterruptedException {
    basePage.checkSlides();

  }
}



























