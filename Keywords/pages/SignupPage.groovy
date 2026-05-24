package pages
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

/**
 * SignupPage.groovy
 * Page Object Model untuk halaman Sign Up DemoBlaze
 * Author: hast99
 */
class SignupPage {

	// === LOCATORS ===
	private static final String MODAL_SIGNUP      = "//div[@id='signInModal']"
	private static final String INPUT_USERNAME    = "sign-username"
	private static final String INPUT_PASSWORD    = "sign-password"
	private static final String BTN_SUBMIT_SIGNUP = "//button[contains(@onclick,'register()')]"

	CommonKeywords common = new CommonKeywords()

	/**
	 * Buka modal signup dari navbar
	 */
	def openSignupModal() {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.findElement(By.id('signin2')).click()
		common.waitForElementVisible(MODAL_SIGNUP, 10)
		WebUI.delay(1)
	}

	/**
	 * Isi form signup
	 */
	def fillSignupForm(String username, String password) {
		WebDriver driver = DriverFactory.getWebDriver()
		def usernameField = driver.findElement(By.id(INPUT_USERNAME))
		usernameField.clear()
		usernameField.sendKeys(username)

		def passwordField = driver.findElement(By.id(INPUT_PASSWORD))
		passwordField.clear()
		passwordField.sendKeys(password)
	}

	/**
	 * Submit form signup
	 */
	def submitSignup() {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.findElement(By.xpath(BTN_SUBMIT_SIGNUP)).click()
		WebUI.delay(2)
	}

	/**
	 * Signup lengkap dan return alert message
	 */
	def signup(String username, String password) {
		openSignupModal()
		fillSignupForm(username, password)
		submitSignup()
		String alertText = common.acceptAlertAndGetText(5)
		return alertText
	}

	/**
	 * Generate username unik berdasarkan timestamp
	 */
	static String generateUniqueUsername() {
		return "qa_user_${System.currentTimeMillis()}"
	}
}
