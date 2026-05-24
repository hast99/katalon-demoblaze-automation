package pages
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

/**
 * ProductPage.groovy
 * Page Object Model untuk halaman Product & Catalog DemoBlaze
 * Author: hast99
 */
class ProductPage {

    // === LOCATORS ===
    private static final String PRODUCT_CARDS       = "//div[@class='card-block']"
    private static final String PRODUCT_TITLE       = "//div[@class='card-block']//a"
    private static final String BTN_ADD_TO_CART     = "//a[contains(@onclick,'addToCart')]"
    private static final String PRODUCT_PRICE       = "//h3[@class='price-container']"
    private static final String PRODUCT_NAME_DETAIL = "//h2[@class='name']"
    private static final String CATEGORY_PHONES     = "//a[contains(@onclick,\"byCat('phone')\")]"
    private static final String CATEGORY_LAPTOPS    = "//a[contains(@onclick,\"byCat('notebook')\")]"
    private static final String CATEGORY_MONITORS   = "//a[contains(@onclick,\"byCat('monitor')\")]"

    CommonKeywords common = new CommonKeywords()

    /**
     * Ambil semua nama produk yang tampil di halaman
     */
    def getAllProductNames() {
        WebDriver driver = DriverFactory.getWebDriver()
        WebUI.delay(2)
        List<WebElement> productElements = driver.findElements(By.xpath(PRODUCT_TITLE))
        List<String> productNames = []
        productElements.each { el ->
            productNames.add(el.getText().trim())
        }
        return productNames
    }

    /**
     * Klik produk berdasarkan nama
     */
    def clickProductByName(String productName) {
        WebDriver driver = DriverFactory.getWebDriver()
        WebUI.delay(2)
        WebElement product = driver.findElement(
            By.xpath("//a[normalize-space()='${productName}']")
        )
        product.click()
        WebUI.delay(2)
    }

    /**
     * Ambil harga produk dari halaman detail
     */
    def getProductPrice() {
        WebDriver driver = DriverFactory.getWebDriver()
        common.waitForElementVisible(PRODUCT_PRICE, 10)
        return driver.findElement(By.xpath(PRODUCT_PRICE)).getText()
    }

    /**
     * Ambil nama produk dari halaman detail
     */
    def getProductName() {
        WebDriver driver = DriverFactory.getWebDriver()
        common.waitForElementVisible(PRODUCT_NAME_DETAIL, 10)
        return driver.findElement(By.xpath(PRODUCT_NAME_DETAIL)).getText()
    }

    /**
     * Tambah produk ke cart dari halaman detail
     */
    def addToCart() {
        WebDriver driver = DriverFactory.getWebDriver()
        common.waitForElementVisible(BTN_ADD_TO_CART, 10)
        driver.findElement(By.xpath(BTN_ADD_TO_CART)).click()
        WebUI.delay(1)
        String alertText = common.acceptAlertAndGetText(5)
        return alertText
    }

    /**
     * Filter produk berdasarkan kategori
     */
    def filterByCategory(String category) {
        WebDriver driver = DriverFactory.getWebDriver()
        String categoryXpath
        switch (category.toLowerCase()) {
            case 'phones':
                categoryXpath = CATEGORY_PHONES
                break
            case 'laptops':
                categoryXpath = CATEGORY_LAPTOPS
                break
            case 'monitors':
                categoryXpath = CATEGORY_MONITORS
                break
            default:
                throw new Exception("Category '${category}' tidak dikenal. Gunakan: phones, laptops, monitors")
        }
        driver.findElement(By.xpath(categoryXpath)).click()
        WebUI.delay(2)
    }

    /**
     * Verifikasi ada produk yang tampil di halaman
     */
    def hasProducts() {
        WebDriver driver = DriverFactory.getWebDriver()
        WebUI.delay(2)
        List<WebElement> products = driver.findElements(By.xpath(PRODUCT_CARDS))
        return products.size() > 0
    }

    /**
     * Navigasi ke halaman berikutnya
     */
    def goToNextPage() {
        WebDriver driver = DriverFactory.getWebDriver()
        driver.findElement(By.id('next2')).click()
        WebUI.delay(2)
    }
}
