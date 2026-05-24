package pages

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

import java.time.Duration

/**
 * CommonKeywords.groovy
 * Reusable helper methods
 * Author: hast99
 */

class CommonKeywords {

    /**
     * Wait Element Visible
     */
    @Keyword
    def waitForElementVisible(String xpath, int timeoutSeconds = 10) {

        WebDriver driver = DriverFactory.getWebDriver()

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(timeoutSeconds)
        )

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath(xpath)
            )
        )
    }

    /**
     * Wait Alert and Get Text
     */
    @Keyword
    def waitAndGetAlertText(int timeoutSeconds = 5) {

        WebDriver driver = DriverFactory.getWebDriver()

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(timeoutSeconds)
        )

        wait.until(
            ExpectedConditions.alertIsPresent()
        )

        return driver.switchTo()
                     .alert()
                     .getText()
    }

    /**
     * Accept Alert
     */
    @Keyword
    def acceptAlertAndGetText(int timeoutSeconds = 5) {

        String text = waitAndGetAlertText(timeoutSeconds)

        WebUI.acceptAlert()

        return text
    }

    /**
     * Scroll and Click
     */
    @Keyword
    def scrollAndClick(String xpath) {

        WebDriver driver = DriverFactory.getWebDriver()

        WebElement element = driver.findElement(
            By.xpath(xpath)
        )

        ((org.openqa.selenium.JavascriptExecutor) driver)
            .executeScript(
                "arguments[0].scrollIntoView(true);",
                element
            )

        WebUI.delay(1)

        element.click()
    }

    /**
     * Check Element Present
     */
    @Keyword
    def isElementPresent(String xpath, int timeoutSeconds = 3) {

        try {

            WebDriver driver = DriverFactory.getWebDriver()

            WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(timeoutSeconds)
            )

            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(xpath)
                )
            )

            return true

        } catch (Exception e) {

            return false
        }
    }

    /**
     * Get Element Text
     */
    @Keyword
    def getElementText(String xpath) {

        WebDriver driver = DriverFactory.getWebDriver()

        return driver.findElement(
            By.xpath(xpath)
        ).getText()
    }

    /**
     * Screenshot
     */
    @Keyword
    def takeScreenshot(String name) {

        String timestamp = new Date().format(
            'yyyyMMdd_HHmmss'
        )

        WebUI.takeScreenshot(
            "Reports/Screenshots/${name}_${timestamp}.png"
        )
    }
}