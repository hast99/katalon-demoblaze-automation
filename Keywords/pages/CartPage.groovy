package pages
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

/**
 * CartPage.groovy
 * Page Object Model untuk halaman Cart & Checkout DemoBlaze
 * Author: hast99
 */
class CartPage {

	// === LOCATORS ===
	private static final String CART_ITEMS         = "//tbody/tr"
	private static final String BTN_DELETE_ITEM    = "//a[contains(@onclick,'deleteItem')]"
	private static final String CART_TOTAL         = "totalp"
	private static final String BTN_PLACE_ORDER    = "//button[contains(@data-target,'#orderModal')]"
	private static final String MODAL_ORDER        = "//div[@id='orderModal']"
	private static final String BTN_PURCHASE       = "//button[contains(@onclick,'purchaseOrder()')]"
	private static final String ORDER_CONFIRM      = "//div[contains(@class,'sweet-alert') and contains(@class,'visible')]"
	private static final String ORDER_CONFIRM_TEXT = "//p[@class='lead text-muted ']"
	private static final String BTN_OK_CONFIRM     = "//button[@class='confirm btn btn-lg btn-primary']"

	CommonKeywords common = new CommonKeywords()

	/**
	 * Navigasi ke halaman cart via navbar
	 */
	def openCart() {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.findElement(By.id('cartur')).click()
		WebUI.delay(2)
	}

	/**
	 * Ambil semua item yang ada di cart
	 */
	def getCartItems() {
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(2)
		return driver.findElements(By.xpath(CART_ITEMS))
	}

	/**
	 * Ambil jumlah item di cart
	 */
	def getCartItemCount() {
		return getCartItems().size()
	}

	/**
	 * Ambil total harga dari cart
	 */
	def getCartTotal() {
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(1)
		try {
			return driver.findElement(By.id(CART_TOTAL)).getText()
		} catch (Exception e) {
			return '0'
		}
	}

	/**
	 * Verifikasi produk ada di cart berdasarkan nama
	 */
	def isProductInCart(String productName) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(2)
		try {
			WebElement item = driver.findElement(
					By.xpath("//tbody/tr/td[contains(text(),'${productName}')]")
					)
			return item != null
		} catch (Exception e) {
			return false
		}
	}

	/**
	 * Hapus item pertama dari cart
	 */
	def deleteFirstItem() {
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> deleteButtons = driver.findElements(By.xpath(BTN_DELETE_ITEM))
		if (deleteButtons.size() > 0) {
			deleteButtons[0].click()
			WebUI.delay(2)
		}
	}

	/**
	 * Klik tombol Place Order
	 */
	def clickPlaceOrder() {
		WebDriver driver = DriverFactory.getWebDriver()
		common.waitForElementVisible(BTN_PLACE_ORDER, 10)
		driver.findElement(By.xpath(BTN_PLACE_ORDER)).click()
		WebUI.delay(2)
	}

	/**
	 * Isi form checkout dengan data Map
	 */
	def fillCheckoutForm(Map<String, String> orderData) {
		WebDriver driver = DriverFactory.getWebDriver()
		common.waitForElementVisible(MODAL_ORDER, 10)
		driver.findElement(By.id('name')).sendKeys(orderData['name'])
		driver.findElement(By.id('country')).sendKeys(orderData['country'])
		driver.findElement(By.id('city')).sendKeys(orderData['city'])
		driver.findElement(By.id('card')).sendKeys(orderData['card'])
		driver.findElement(By.id('month')).sendKeys(orderData['month'])
		driver.findElement(By.id('year')).sendKeys(orderData['year'])
	}

	/**
	 * Klik tombol Purchase
	 */
	def submitPurchase() {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.findElement(By.xpath(BTN_PURCHASE)).click()
		WebUI.delay(3)
	}

	/**
	 * Verifikasi order confirmation tampil
	 */
	def isOrderConfirmed() {
		return common.isElementPresent(ORDER_CONFIRM, 10)
	}

	/**
	 * Ambil teks konfirmasi order
	 */
	def getOrderConfirmationText() {
		WebDriver driver = DriverFactory.getWebDriver()
		common.waitForElementVisible(ORDER_CONFIRM_TEXT, 10)
		return driver.findElement(By.xpath(ORDER_CONFIRM_TEXT)).getText()
	}

	/**
	 * Klik OK di konfirmasi order
	 */
	def confirmOrder() {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.findElement(By.xpath(BTN_OK_CONFIRM)).click()
		WebUI.delay(1)
	}
}
