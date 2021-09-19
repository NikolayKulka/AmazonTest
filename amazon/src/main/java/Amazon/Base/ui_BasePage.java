package Amazon.Base;

import Core.Amazon_config.amazon_ConfigPage.BaseConfig;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import data.UserEntity;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;

import static com.codeborne.selenide.Selenide.*;

public class ui_BasePage {


  public static final String Slides = "//app-rush-slide[@class[contains(.,'home-slider')]]";
  public static final String SlideActive = "//app-rush-slide[@class[contains(.,'active')]]/div";
  public static final String SlidePrev = "//i[@class='home-slider__prev']";
  public static final String Button = "//app-rush-slide[@class[contains(.,'active')]]//div[@class='home-slider__buttons-wrap']";
  public static final String aTag = "//app-rush-slide[@class[contains(.,'active')]]//div[@class='home-slider__buttons-wrap']/a";


  public static BaseConfig base = new BaseConfig();
  public static UserEntity userEntity;

  public void checkSlides() throws InterruptedException {
    SoftAssertions softAssertions = new SoftAssertions();
    userEntity = base.getUser();

    Selenide.open(userEntity.getSignInUrl());

    Coordinates x = $x(SlidePrev).getCoordinates();
    int xOffset = x.onPage().x;
    int yOffset = x.onPage().y;

    int size = $$x(Slides).size();
    for (int i = 0; i < size; i++) {
      $x(Button).shouldHave(Condition.enabled);
      swipe(i,$x(SlideActive),yOffset,xOffset);
      softAssertions.assertThat($x(aTag).getAttribute("href")).doesNotContain("404");
      $x(Button).shouldHave(Condition.visible).click();
      softAssertions.assertThat(WebDriverRunner.getWebDriver().getCurrentUrl()).doesNotContain("404");
      Selenide.back();
    }
    softAssertions.assertAll();
  }


  public void swipe(int count, WebElement source, int yOffset, int xOffset) throws InterruptedException {
    int i = 0;
    do {
      Thread.sleep(1000);
      Selenide.actions().dragAndDropBy(source, yOffset, xOffset).build().perform();
      i++;
    } while (i < count +1);
  }




}
