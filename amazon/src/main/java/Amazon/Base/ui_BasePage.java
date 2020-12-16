package Amazon.Base;

import Core.Amazon_config.amazon_ConfigPage.BaseConfig;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import data.UserEntity;
import junit.framework.Assert;

import static com.codeborne.selenide.Selenide.$x;

public class ui_BasePage {


  public static final String Submit = "//input[@type= 'submit']";
  public static final String searchField = "//input[@id= 'twotabsearchtextbox']";
  public static final String chooseFirstResult = "//div[@data-component-type= 's-search-result'][1]";
  public static final String selectQuantity = "//select[@name = 'quantity']";
  public static final String Quantity = "//span[@id= 'sc-subtotal-label-activecart']";
  public static final String Price = " //span[@id= 'priceblock_ourprice']|//span[@id= 'priceblock_saleprice']";
  public static final String totalPrice = "//span[@id= 'sc-subtotal-amount-buybox']/span";
  public static final String addToCart = "//input[@id= 'add-to-cart-button']";
  public static final String Cart = "//a[@id= 'nav-cart']";


  public static BaseConfig base = new BaseConfig();
  public static UserEntity userEntity;

  public void searchAmazon()  {

    userEntity = base.getUser();

    Selenide.open(userEntity.getSignInUrl());
    $x(searchField).sendKeys("hats for men");
    $x(Submit).click();
    $x(chooseFirstResult).click();
    Double priceM = Double.parseDouble($x(Price).getText().replaceAll("\\$",""));
    $x(selectQuantity).selectOptionByValue("2");
    $x(addToCart).click();
    $x(Cart).click();
    int quantityM = Integer.parseInt($x(Quantity).getText().replaceAll("[^0-9]",""));
    Double totalM = Double.parseDouble($x(totalPrice).getText().replaceAll("\\$",""));

    /**Check quantity and total price are correct after first search**/
    Assert.assertEquals(2,quantityM);
    Assert.assertEquals(priceM * quantityM ,totalM);


    $x(searchField).sendKeys("hats for women");
    $x(Submit).click();
    $x(chooseFirstResult).click();
    Double priceW = Double.parseDouble($x(Price).getText().replaceAll("\\$",""));
    $x(addToCart).click();
    $x(Cart).click();

    int quantityW = Integer.parseInt($x(Quantity).getText().replaceAll("[^0-9]",""));
    Double totalW = Double.parseDouble($x(totalPrice).getText().replaceAll("\\$",""));

    /**Check quantity and total price are correct after second search**/
    Assert.assertEquals(3,quantityW);
    Assert.assertEquals(priceW + totalM ,totalW);

    $x("//div/p/span[contains(.,'"+priceM+"')]/preceding::select[@name= 'quantity'][1]").selectOptionByValue("1");
    $x(Quantity).shouldNotBe((Condition.exactText("Subtotal (3 items):")));
    int quantityFinal = Integer.parseInt($x(Quantity).getText().replaceAll("[^0-9]",""));
    Double totalFinal = Double.parseDouble($x(totalPrice).getText().replaceAll("\\$",""));

    /**Check quantity and total price are correct after changing quantity**/
    Assert.assertEquals(2,quantityFinal);
    Assert.assertEquals(priceW + priceM ,totalFinal);

  }

}
