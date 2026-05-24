import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil

import pages.LoginPage
import pages.ProductPage
import pages.CommonKeywords

/**
 * TC_Product.groovy
 * Test Cases: Product Catalog DemoBlaze
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
// OBJECT
// ============================================================

LoginPage loginPage     = new LoginPage()

ProductPage productPage = new ProductPage()

CommonKeywords common   = new CommonKeywords()

// ============================================================
// TC_Product_01
// Verify Product Homepage
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Product_01: Homepage Product ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

boolean hasProducts =
    productPage.hasProducts()

assert hasProducts :
    "Produk tidak tampil di homepage"

List<String> allProducts =
    productPage.getAllProductNames()

assert allProducts.size() > 0 :
    "Minimal 1 produk harus tampil"

KeywordUtil.logInfo(
    "TC_Product_01 PASSED - ${allProducts.size()} produk ditemukan"
)

common.takeScreenshot(
    'TC_Product_01_Homepage'
)

// ============================================================
// TC_Product_02
// Filter Phones
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Product_02: Filter Phones ==='
)

productPage.filterByCategory('phones')

WebUI.delay(2)

boolean hasPhones =
    productPage.hasProducts()

assert hasPhones :
    "Produk phones tidak tampil"

List<String> phoneProducts =
    productPage.getAllProductNames()

KeywordUtil.logInfo(
    "TC_Product_02 PASSED - ${phoneProducts}"
)

common.takeScreenshot(
    'TC_Product_02_Phones'
)

// ============================================================
// TC_Product_03
// Filter Laptops
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Product_03: Filter Laptops ==='
)

productPage.filterByCategory('laptops')

WebUI.delay(2)

boolean hasLaptops =
    productPage.hasProducts()

assert hasLaptops :
    "Produk laptops tidak tampil"

List<String> laptopProducts =
    productPage.getAllProductNames()

KeywordUtil.logInfo(
    "TC_Product_03 PASSED - ${laptopProducts}"
)

common.takeScreenshot(
    'TC_Product_03_Laptops'
)

// ============================================================
// TC_Product_04
// Filter Monitors
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Product_04: Filter Monitors ==='
)

productPage.filterByCategory('monitors')

WebUI.delay(2)

boolean hasMonitors =
    productPage.hasProducts()

assert hasMonitors :
    "Produk monitors tidak tampil"

KeywordUtil.logInfo(
    "TC_Product_04 PASSED"
)

common.takeScreenshot(
    'TC_Product_04_Monitors'
)

WebUI.closeBrowser()

// ============================================================
// TC_Product_05
// Product Detail
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Product_05: Product Detail ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

WebUI.delay(2)

productPage.clickProductByName(
    TARGET_PRODUCT
)

WebUI.delay(2)

String detailName =
    productPage.getProductName()

assert detailName.toLowerCase()
                 .contains(
                     TARGET_PRODUCT.toLowerCase()
                 ) :
                 "Nama produk detail tidak sesuai"

KeywordUtil.logInfo(
    "TC_Product_05 PASSED - ${detailName}"
)

// ============================================================
// TC_Product_06
// Verify Product Price
// ============================================================

String price =
    productPage.getProductPrice()

assert price != null &&
       !price.isEmpty() :
       "Harga produk tidak tampil"

KeywordUtil.logInfo(
    "TC_Product_06 PASSED - Harga: ${price}"
)

common.takeScreenshot(
    'TC_Product_05_06_Detail'
)

WebUI.closeBrowser()

// ============================================================
// TC_Product_07
// Add To Cart
// ============================================================

KeywordUtil.logInfo(
    '=== TC_Product_07: Add To Cart ==='
)

WebUI.openBrowser(BASE_URL)

WebUI.maximizeWindow()

WebUI.waitForPageLoad(3)

// LOGIN

loginPage.login(
    VALID_USER,
    VALID_PASS
)

WebUI.delay(3)

// OPEN PRODUCT

productPage.clickProductByName(
    TARGET_PRODUCT
)

WebUI.delay(2)

// ADD TO CART

String cartAlert =
    productPage.addToCart()

assert cartAlert.toLowerCase()
                 .contains('product added') :
                 "Alert add to cart gagal: ${cartAlert}"

KeywordUtil.logInfo(
    "TC_Product_07 PASSED - ${cartAlert}"
)

common.takeScreenshot(
    'TC_Product_07_AddToCart'
)

WebUI.closeBrowser()

// ============================================================
// FINISH
// ============================================================

KeywordUtil.logInfo(
    '=== Semua TC_Product selesai ==='
)