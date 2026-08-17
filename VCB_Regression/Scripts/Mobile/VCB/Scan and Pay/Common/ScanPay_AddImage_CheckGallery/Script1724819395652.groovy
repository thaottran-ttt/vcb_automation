import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.util.KeywordUtil
import io.appium.java_client.AppiumDriver
import io.appium.java_client.PushesFiles
import com.vcb.common.MobileDriver
import internal.GlobalVariable

import io.appium.java_client.AppiumDriver
import io.appium.java_client.InteractsWithApps
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.vcb.utils.MobileDriverUtils 

AppiumDriver driver = MobileDriverFactory.getDriver()

if (!(driver instanceof PushesFiles)) {
    KeywordUtil.markFailedAndStop("Current driver does not support file pushing.")
}

List<String> fileNames = ['P2P.png', 'merchant.png', 'invalid.png', 'timeout.png', 'out.png']

String basePath = RunConfiguration.getProjectDir() + qrCodeDataFilePath

for (String fileName : fileNames) {
    String localPath = new File(basePath + fileName).getCanonicalPath()
    String devicePath = "/sdcard/Pictures/${fileName}"
    KeywordUtil.logInfo("   Start pushing file: ${fileName}")
    KeywordUtil.logInfo("   Local file path : ${localPath}")
    KeywordUtil.logInfo("   Device file path: ${devicePath}")

    CustomKeywords.'com.katalon.testcloud.FileExecutor.pushFileToDevice'(devicePath, localPath)
    KeywordUtil.logInfo("File pushed successfully to device: ${devicePath}")
	MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
}

KeywordUtil.logInfo("All files have been processed.")

'Open Gallery app'
try {
	KeywordUtil.logInfo("Opening Samsung Gallery app...")
	((InteractsWithApps) driver).activateApp("com.sec.android.gallery3d")

	Mobile.delay(GlobalVariable.G_timeoutShort)

	// verify session is alive before taking screenshot
	if (driver != null && driver.getSessionId() != null) {
		Mobile.takeScreenshot()
		KeywordUtil.logInfo("Screenshot captured successfully in Gallery.")
	} else {
		KeywordUtil.markWarning("Appium session may have ended before screenshot.")
	}
} catch (Exception e) {
	KeywordUtil.markWarning("Failed to open Gallery or take screenshot: " + e.message)
}

'Return to VCB app'
try {
	KeywordUtil.logInfo("Returning to main app...")
	if (CustomKeywords.'com.vcb.common.BaseKeyword.runFromTestCloud'()) {
		((InteractsWithApps) driver).activateApp(GlobalVariable.G_appIDTestCloud)
	} else {
		((InteractsWithApps) driver).activateApp(GlobalVariable.G_appId)
	}

	MobileDriverUtils.setDriverMobile()
	KeywordUtil.logInfo("=> Returned to VCB app successfully.")
} catch (Exception e) {
	KeywordUtil.markWarning("Failed to return to main app: " + e.message)
}

