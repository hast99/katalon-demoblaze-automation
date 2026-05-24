package pages

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory

import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

/**
 * LoginPage.groovy
 * Page Object Model DemoBlaze
 * Author: hast99
 */

class LoginPage {

	// =========================================================
	// LOCATORS
	// =========================================================

	private static final String BTN_LOGIN_NAV   = "login2"
	private static final String INPUT_USERNAME  = "loginusername"
	private static final String INPUT_PASSWORD  = "loginpassword"
	private static final String BTN_SUBMIT      = "//button[contains(text(),'Log in')]"
	private static final String LABEL_WELCOME   = "nameofuser"
	private static final String BTN_LOGOUT      = "logout2"

	CommonKeywords common = new CommonKeywords()

	/**
	 * Open Login Modal
	 */
	def openLoginModal() {

		WebDriver driver = DriverFactory.getWebDriver()

		driver.findElement(
				By.id(BTN_LOGIN_NAV)
				).click()

		common.waitForElementVisible(
				"//div[@id='logInModal']",
				10
				)

		WebUI.delay(2)
	}

	/**
	 * Fill Login Form
	 */
	def fillLoginForm(String username, String password) {

		WebDriver driver = DriverFactory.getWebDriver()

		def usernameField = driver.findElement(
				By.id(INPUT_USERNAME)
				)

		usernameField.clear()
		usernameField.sendKeys(username)

		def passwordField = driver.findElement(
				By.id(INPUT_PASSWORD)
				)

		passwordField.clear()
		passwordField.sendKeys(password)
	}

	/**
	 * Submit Login
	 */
	def submitLogin() {

		WebDriver driver = DriverFactory.getWebDriver()

		driver.findElement(
				By.xpath(BTN_SUBMIT)
				).click()
	}

	/**
	 * Login Action
	 */
	def login(String username, String password) {

		openLoginModal()

		fillLoginForm(username, password)

		submitLogin()

		WebUI.delay(3)
	}

	/**
	 * Verify Login Success
	 */
	boolean isLoginSuccessful(String expectedUsername) {

		try {

			WebDriver driver = DriverFactory.getWebDriver()

			String welcomeText = driver.findElement(
					By.id(LABEL_WELCOME)
					).getText()

			return welcomeText.contains(expectedUsername)
		} catch (Exception e) {

			return false
		}
	}

	/**
	 * Check User Login Status
	 */
	boolean isUserLoggedIn() {

		return common.isElementPresent(
				"//a[@id='logout2']",
				5
				)
	}

	/**
	 * Logout
	 */
	def logout() {

		WebDriver driver = DriverFactory.getWebDriver()

		driver.findElement(
				By.id(BTN_LOGOUT)
				).click()

		WebUI.delay(3)
	}
}