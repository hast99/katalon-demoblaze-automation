package utils

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class CommonKeywords {

    def takeScreenshot(String fileName) {
        WebUI.takeScreenshot("Screenshots/${fileName}.png")
    }

    def waitAndGetAlertText(int timeout) {

        WebUI.waitForAlert(timeout)

        return WebUI.getAlertText()
    }
}