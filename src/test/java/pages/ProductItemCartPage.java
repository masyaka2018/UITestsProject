package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import org.testng.Assert;


public class ProductItemCartPage {

    private final Page page;

    //Selectors
    private final String searchInput = "#small-searchterms";
    private final String productItem = "//*[@class='cart'][1]";
    private final String quantityItemInput = "//*[@class='qty-input']";
    private final String updateCartSumButton = "//*[@class='button-2 update-cart-button']";
    private final String productItemsTotalSum = "//*[@class='product-subtotal']";

    public ProductItemCartPage(Page page) {
        this.page = page;
    }

    @Step("Click On Shopping cart link")
    public void clickOnShoppingCartLink(int productItemCount) {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Shopping cart ("+productItemCount+")")).click();
    }

    @Step("Change product item count")
    public void changeProductItemCount(String productItemCount) {
        page.locator(productItem+quantityItemInput).fill(productItemCount);
    }

    @Step("Change product item count")
    public void updateCartItemsSum() {
        page.locator(updateCartSumButton).click();
    }

    @Step("Check summa of product items")
    public void checkSummaOfProductItems(String productItemsSum2) {
        String productItemsSum1 = page.locator(productItem+productItemsTotalSum).textContent();
        Assert.assertEquals(productItemsSum1, productItemsSum2);
    }
}
