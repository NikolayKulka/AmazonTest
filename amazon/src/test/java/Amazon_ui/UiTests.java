package Amazon_ui;

import Amazon.Base.ui_BasePage;
import Amazon.StartDriver;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


public class UiTests extends StartDriver {

  ui_BasePage basePage = new ui_BasePage();


  @Tag("regression")
  @Test
  @Execution(ExecutionMode.CONCURRENT)
  public void TC_0001_Search() {
    basePage.searchAmazon();

  }
}



























