import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.util.KeywordUtil
import io.appium.java_client.AppiumDriver
import io.appium.java_client.PushesFiles
import com.maybank.common.MobileDriver
import internal.GlobalVariable

import io.appium.java_client.AppiumDriver
import io.appium.java_client.InteractsWithApps
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.maybank.utils.MobileDriverUtils 

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
