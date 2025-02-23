package pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

public class SearchItemPage {

    private final Page page;

    //Selectors
    private final String searchInput = "#small-searchterms";
    private final String searchItemButton = "//*[@class='button-1 search-box-button']";
    private final String searchItemResult = ".search-results";
    private final String productItem = "//*[@class='search-results'][1]";
    private final String addProductItemButton = "//*[@class='button-2 product-box-add-to-cart-button']";
    private final String closeButton = ".close";
    private final String cartCountItem = ".cart-qty";

    public SearchItemPage(Page page) {
        this.page = page;
    }

    @Step("Search product item")
    public void searchProductItem(String productItem) {
        page.locator(searchInput).fill(productItem);
        page.locator(searchItemButton).click();
    }

    @Step("Check search result")
    public void checkSearchResult() {
        page.waitForSelector(searchItemResult);
        Assert.assertTrue(page.locator(productItem).isVisible());
    }

    @Step("Add product item to the shopping cart")
    public void addProductItemToCart() {
        page.locator(productItem+addProductItemButton).click();
        page.locator(closeButton).click();
    }

    @Step("Check that product was added to the cart")
    public void checkThatProductItemWasAdded(int countOfProductItem){
        page.waitForSelector(cartCountItem);
        String cartItemCount = page.locator(cartCountItem).textContent();
        Assert.assertEquals(cartItemCount, "("+countOfProductItem+")");
    }
}
