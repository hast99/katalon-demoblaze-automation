import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil

import pages.LoginPage
import pages.ProductPage
import pages.CartPage
import pages.CommonKeywords

/**
 * TC_Cart.groovy
 * Test Cases: Cart & Checkout DemoBlaze
 * Author: hast99
 */

// ============================================================
// TEST DATA
// ============================================================

String BASE_URL       = 'https://www.demoblaze.com'
String VALID_USER     = 'admin'
String VALID_PASS     = 'admin'
String TARGET_PRODUCT = 'Samsung galaxy s6'

// ============================================================
// CHECKOUT DATA
// ============================================================

Map<String, String> validOrderData = [

    'name'    : 'Hast QA',
    'country' : 'Indonesia',
    'city'    : 'Yogyakarta',
    'card'    : '4111111111111111',
    'month'   : '12',
    'year'    : '2026'
]

// ============================================================
// OBJECT
// ============================================================

LoginPage loginPage     = new LoginPage()

ProductPage productPage = new ProductPage()

CartPage cartPage       = new CartPage()

CommonKeywords common   = new CommonKeywords()

// ============================================================
// HELPER
// ============================================================

def setupCartWithProduct = {

    WebUI.openBrowser(BASE_URL)

    WebUI.maximizeWindow()

    WebUI.waitForPageLoad(3)

    loginPage.login(
        VALID_USER,
        VALID_PASS
    )

    WebUI.delay(2)

    productPage.clickProductByName(
        TARGET_PRODUCT
    )

    WebUI.delay(2)

    productPage.addToCart()

    WebUI.delay(2)
}

// ============================================================
// TC_Cart_01 - Product In Cart
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Cart_01: Product In Cart ==='
)

setupCartWithProduct()

cartPage.openCart()

boolean isInCart =
    cartPage.isProductInCart(TARGET_PRODUCT)

assert isInCart :
    "Produk '${TARGET_PRODUCT}' tidak ditemukan di cart"

int itemCount =
    cartPage.getCartItemCount()

assert itemCount > 0 :
    "Cart harus memiliki minimal 1 item"

KeywordUtil.logInfo(
    "TC_Cart_01 PASSED - ${itemCount} item di cart"
)

common.takeScreenshot(
    'TC_Cart_01_ProductInCart'
)

WebUI.closeBrowser()

// ============================================================
// TC_Cart_02 - Verify Total Price
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Cart_02: Verify Total Price ==='
)

setupCartWithProduct()

cartPage.openCart()

String total =
    cartPage.getCartTotal()

assert total != null &&
       !total.isEmpty() &&
       total != '0' :
       "Total harga cart invalid: ${total}"

    KeywordUtil.logInfo(
    "TC_Cart_02 PASSED - Total: ${total}"
)

common.takeScreenshot(
    'TC_Cart_02_TotalPrice'
)

WebUI.closeBrowser()

// ============================================================
// TC_Cart_03 - Delete Item
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Cart_03: Delete Item ==='
)

setupCartWithProduct()

cartPage.openCart()

int itemsBefore =
    cartPage.getCartItemCount()

cartPage.deleteFirstItem()

WebUI.delay(3)

int itemsAfter =
    cartPage.getCartItemCount()

assert itemsAfter < itemsBefore :
    "Item cart tidak berkurang. Before: ${itemsBefore}, After: ${itemsAfter}"

KeywordUtil.logInfo(
    "TC_Cart_03 PASSED - Item berkurang"
)

common.takeScreenshot(
    'TC_Cart_03_DeleteItem'
)

WebUI.closeBrowser()

// ============================================================
// TC_Cart_04 - Full Checkout
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Cart_04: Full Checkout ==='
)

setupCartWithProduct()

cartPage.openCart()

boolean productInCart =
    cartPage.isProductInCart(TARGET_PRODUCT)

assert productInCart :
    "Produk tidak ada di cart sebelum checkout"

cartPage.clickPlaceOrder()

WebUI.delay(2)

cartPage.fillCheckoutForm(
    validOrderData
)

common.takeScreenshot(
    'TC_Cart_04_CheckoutForm'
)

cartPage.submitPurchase()

WebUI.delay(3)

boolean isConfirmed =
    cartPage.isOrderConfirmed()

assert isConfirmed :
    "Order confirmation tidak tampil"

String confirmText =
    cartPage.getOrderConfirmationText()

KeywordUtil.logInfo(
    "TC_Cart_04 PASSED - ${confirmText}"
)

common.takeScreenshot(
    'TC_Cart_04_OrderConfirmed'
)

cartPage.confirmOrder()

WebUI.closeBrowser()

// ============================================================
// FINISH
// ============================================================

KeywordUtil.logInfo(
    '=== Semua TC_Cart selesai ==='
)