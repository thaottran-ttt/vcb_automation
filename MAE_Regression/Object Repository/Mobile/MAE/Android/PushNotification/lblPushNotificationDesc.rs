<?xml version="1.0" encoding="UTF-8"?>
<MobileElementEntity>
   <description></description>
   <name>lblPushNotificationDesc</name>
   <tag></tag>
   <elementGuidId>2d973d57-a15a-4658-a2f9-3764ca7231b2</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <locator>((//*[@class = 'android.widget.TextView' and contains(@text, '${text}')]//following-sibling::android.widget.TextView)[1])
|
((//*[@class = 'android.widget.TextView' and contains(@text, '${text}')]/../following-sibling::android.widget.TextView)[1])
|
((//*[@class = 'android.widget.TextView' and contains(@text, '${text}')]/../following-sibling::*//android.widget.TextView)[1])</locator>
   <locatorCollection>
      <entry>
         <key>ATTRIBUTES</key>
      </entry>
      <entry>
         <key>IMAGE</key>
         <value></value>
      </entry>
      <entry>
         <key>IOS_CLASS_CHAIN</key>
         <value></value>
      </entry>
      <entry>
         <key>ANDROID_UI_AUTOMATOR</key>
         <value>new UiSelector()</value>
      </entry>
      <entry>
         <key>NAME</key>
         <value></value>
      </entry>
      <entry>
         <key>CLASS_NAME</key>
         <value></value>
      </entry>
      <entry>
         <key>XPATH</key>
         <value>((//*[@class = 'android.widget.TextView' and contains(@text, '${text}')]//following-sibling::android.widget.TextView)[1])
|
((//*[@class = 'android.widget.TextView' and contains(@text, '${text}')]/../following-sibling::android.widget.TextView)[1])
|
((//*[@class = 'android.widget.TextView' and contains(@text, '${text}')]/../following-sibling::*//android.widget.TextView)[1])</value>
      </entry>
      <entry>
         <key>IOS_PREDICATE_STRING</key>
         <value></value>
      </entry>
      <entry>
         <key>CUSTOM</key>
         <value></value>
      </entry>
      <entry>
         <key>ANDROID_VIEWTAG</key>
         <value></value>
      </entry>
      <entry>
         <key>ID</key>
         <value></value>
      </entry>
      <entry>
         <key>ACCESSIBILITY</key>
         <value></value>
      </entry>
   </locatorCollection>
   <locatorStrategy>XPATH</locatorStrategy>
   <platform>ANDROID</platform>
</MobileElementEntity>
