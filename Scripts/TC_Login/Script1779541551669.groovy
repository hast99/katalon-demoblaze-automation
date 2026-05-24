import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil

import pages.LoginPage
import pages.CommonKeywords

// ============================================================
// TEST DATA
// ============================================================

String BASE_URL    = 'https://www.demoblaze.com'

// WAJIB akun yang sudah terdaftar
String VALID_USER  = 'admin'
String VALID_PASS  = 'admin'

String WRONG_PASS  = 'wrongpassword123'

// ============================================================
// OBJECT
// ============================================================

LoginPage loginPage   = new LoginPage()
CommonKeywords common = new CommonKeywords()

// ============================================================
// TC_Login_01 - Login Valid
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Login_01: Login Valid ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

loginPage.login(
    VALID_USER,
    VALID_PASS
)

boolean isLoggedIn = loginPage.isLoginSuccessful(
    VALID_USER
)

assert isLoggedIn :
    "TC_Login_01 FAILED - Welcome label tidak tampil"

KeywordUtil.logInfo(
    "TC_Login_01 PASSED - Login berhasil"
)

common.takeScreenshot(
    'TC_Login_01_PASS'
)

// ============================================================
// TC_Login_05 - Logout
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Login_05: Logout ==='
)

loginPage.logout()

boolean isLoggedOut =
    !loginPage.isUserLoggedIn()

assert isLoggedOut :
    "TC_Login_05 FAILED - User masih terdeteksi login"

KeywordUtil.logInfo(
    "TC_Login_05 PASSED - Logout berhasil"
)

common.takeScreenshot(
    'TC_Login_05_PASS'
)

WebUI.closeBrowser()

// ============================================================
// TC_Login_02 - Wrong Password
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Login_02: Wrong Password ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

loginPage.login(
    VALID_USER,
    WRONG_PASS
)

try {

    String alertText =
        common.waitAndGetAlertText(5)

    assert alertText != null

    WebUI.acceptAlert()

    KeywordUtil.logInfo(
        "TC_Login_02 PASSED"
    )

    common.takeScreenshot(
        'TC_Login_02_PASS'
    )

} catch (Exception e) {

    KeywordUtil.markFailed(
        "TC_Login_02 FAILED - ${e.message}"
    )
}

WebUI.closeBrowser()

// ============================================================
// TC_Login_03 - Username Empty
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Login_03: Username Empty ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

loginPage.login(
    '',
    VALID_PASS
)

try {

    String alertText =
        common.waitAndGetAlertText(5)

    assert alertText != null

    WebUI.acceptAlert()

    KeywordUtil.logInfo(
        "TC_Login_03 PASSED"
    )

    common.takeScreenshot(
        'TC_Login_03_PASS'
    )

} catch (Exception e) {

    KeywordUtil.markFailed(
        "TC_Login_03 FAILED - ${e.message}"
    )
}

WebUI.closeBrowser()

// ============================================================
// TC_Login_04 - All Empty
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Login_04: All Empty ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

loginPage.login('', '')

try {

    String alertText =
        common.waitAndGetAlertText(5)

    assert alertText != null

    WebUI.acceptAlert()

    KeywordUtil.logInfo(
        "TC_Login_04 PASSED"
    )

    common.takeScreenshot(
        'TC_Login_04_PASS'
    )

} catch (Exception e) {

    KeywordUtil.markFailed(
        "TC_Login_04 FAILED - ${e.message}"
    )
}

WebUI.closeBrowser()

KeywordUtil.logInfo(
    '=== Semua TC_Login selesai ==='
)