package tests;

import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Description;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.ProductItemCartPage;
import pages.SearchItemPage;

public class UITests extends TestBase {

    private SearchItemPage searchItemPage;
    private ProductItemCartPage productItemCartPage;

    @Test
    @Description("Search and add Item to the shopping card ")
    public void AddItemToCard(){
        searchItemPage = new SearchItemPage(page);

        searchItemPage.searchProductItem("Laptop");
        searchItemPage.checkSearchResult();
        searchItemPage.addProductItemToCart();
        searchItemPage.checkThatProductItemWasAdded(1);
    }

    @Test
    @Description("Search and add Item to the shopping card")
    public void AddSeveralItemsToCard() throws InterruptedException{
        String productItemsSum1 = "4770.00";
        searchItemPage = new SearchItemPage(page);
        productItemCartPage = new ProductItemCartPage(page);

        searchItemPage.searchProductItem("Laptop");
        searchItemPage.checkSearchResult();
        searchItemPage.addProductItemToCart();
        searchItemPage.addProductItemToCart();

        productItemCartPage.clickOnShoppingCartLink(1);
        productItemCartPage.changeProductItemCount("3");
        productItemCartPage.updateCartItemsSum();
        productItemCartPage.checkSummaOfProductItems(productItemsSum1);
    }

}
