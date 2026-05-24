import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil

import pages.SignupPage
import pages.CommonKeywords

/**
 * TC_Signup.groovy
 * Test Cases: Signup DemoBlaze
 * Author: hast99
 */

// ============================================================
// TEST DATA
// ============================================================

String BASE_URL = 'https://www.demoblaze.com'

// ============================================================
// OBJECT
// ============================================================

SignupPage signupPage = new SignupPage()

CommonKeywords common = new CommonKeywords()

// ============================================================
// TC_Signup_01
// Register New User
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Signup_01: Register New User ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

String uniqueUsername =
    SignupPage.generateUniqueUsername()

String password =
    'TestPass123!'

String alertResult =
    signupPage.signup(
        uniqueUsername,
        password
    )

assert alertResult.toLowerCase()
                  .contains('successful') :
                  "Signup gagal: ${alertResult}"

KeywordUtil.logInfo(
    "TC_Signup_01 PASSED - ${uniqueUsername}"
)

common.takeScreenshot(
    'TC_Signup_01_Success'
)

WebUI.closeBrowser()

// ============================================================
// TC_Signup_02
// Duplicate Username
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Signup_02: Duplicate Username ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

String existingUser =
    'hast_qauser'

String alertDuplicate =
    signupPage.signup(
        existingUser,
        password
    )

assert alertDuplicate.toLowerCase()
                     .contains('already exist') :
                     "Alert duplicate tidak muncul: ${alertDuplicate}"

KeywordUtil.logInfo(
    "TC_Signup_02 PASSED - ${alertDuplicate}"
)

common.takeScreenshot(
    'TC_Signup_02_Duplicate'
)

WebUI.closeBrowser()

// ============================================================
// TC_Signup_03
// Empty Username
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Signup_03: Empty Username ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

String alertEmptyUser =
    signupPage.signup(
        '',
        password
    )

assert alertEmptyUser != null &&
       !alertEmptyUser.isEmpty() :
       "Alert username kosong tidak muncul"

KeywordUtil.logInfo(
    "TC_Signup_03 PASSED - ${alertEmptyUser}"
)

common.takeScreenshot(
    'TC_Signup_03_EmptyUsername'
)

WebUI.closeBrowser()

// ============================================================
// TC_Signup_04
// Empty Password
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Signup_04: Empty Password ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

String alertEmptyPass =
    signupPage.signup(
        SignupPage.generateUniqueUsername(),
        ''
    )

assert alertEmptyPass != null &&
       !alertEmptyPass.isEmpty() :
       "Alert password kosong tidak muncul"

KeywordUtil.logInfo(
    "TC_Signup_04 PASSED - ${alertEmptyPass}"
)

common.takeScreenshot(
    'TC_Signup_04_EmptyPassword'
)

WebUI.closeBrowser()

// ============================================================
// FINISH
// ============================================================

KeywordUtil.logInfo(
    '=== Semua TC_Signup selesai ==='
)