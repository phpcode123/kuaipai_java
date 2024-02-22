package chrome_fingerprint;

public class EditNavigator {
	
	public static String jsString() {
		
		String script = "(function() {\n" +
	            " \n" +
	            "\tdelete navigator; navigator = {};\n" +
	            "    function fakeActiveVRDisplays() { return \"Not Spoofed\"; }\n" +
	            "    function fakeAppCodeName() {\n" +
	            "      return \"Mozilla\";\n" +
	            "    }\n" +
	            "    function fakeAppName() {\n" +
	            "      return \"Netscape\";\n" +
	            "    }\n" +
	            " \n" +
	            "    function fakeAppVersion() {\n" +
	            "        return \"5.0 (Windows)\";\n" +
	            "    }\n" +
	            "    function fakeBattery() { return \"Not Spoofed\"; }\n" +
	            "    function fakeConnection() { return \"Not Spoofed\"; }\n" +
	            "    function fakeGeoLocation() { return \"Not Spoofed\"; }\n" +
	            "    function fakeHardwareConcurrency() {\n" +
	            "      return 1;\n" +
	            "    }\n" +
	            "    function fakeJavaEnabled() {\n" +
	            "      return false;\n" +
	            "    }\n" +
	            "    function fakeLanguage() {\n" +
	            "        // NOTE: TOR Browser uses American English\n" +
	            "        return \"en-US\";\n" +
	            "    }\n" +
	            "    function fakeLanguages() {\n" +
	            "        // NOTE: TOR Browser uses American English\n" +
	            "        return \"en-US,en\";\n" +
	            "    }\n" +
	            "    function fakeMimeTypes() { return \"Not Spoofed\"; }\n" +
	            "    function fakeOnLine() {\n" +
	            "      return true;\n" +
	            "    }\n" +
	            "    function fakeOscpu() {\n" +
	            "      return \"Windows NT 6.1\";\n" +
	            "    }\n" +
	            "    function fakePermissions() { return \"Not Spoofed\"; }\n" +
	            "    function fakePlatform() {\n" +
	            "      return \"Win32\";\n" +
	            "    }\n" +
	            "    function fakePlugins() {\n" +
	            "        return window.navigator.plugins;\n" +
	            "    }\n" +
	            "    function fakeProduct() {\n" +
	            "      return \"Gecko\";\n" +
	            "    }\n" +
	            "    function fakeServiceWorker() { return \"Not Spoofed\"; }\n" +
	            "    function fakeStorage() { return \"Not Spoofed\"; }\n" +
	            "    function fakeUserAgent() {\n" +
	            "      // NOTE: Current TOR User Agent as of 19 July 2017\n" +
	            "        // NOTE: This will need constant updating.\n" +
	            "        // NOTE: As TOR changes firefox versions each update,\n" +
	            "        // NOTE: Shape Shifter will need to keep up.\n" +
	            "        return \"Mozilla/5.0 (Windows NT 6.1; rv:52.0) Gecko/20100101 Firefox/52.0\";\n" +
	            "    }\n" +
	            "    function fakeBuildID() {\n" +
	            "      return \"20100101\";\n" +
	            "    }\n" +
	            " \n" +
	            "    const fakeActiveVRDisplaysValue       = fakeActiveVRDisplays();\n" +
	            "    const fakeAppCodeNameValue            = fakeAppCodeName();\n" +
	            "    const fakeAppNameValue                = fakeAppName();\n" +
	            "    const fakeAppVersionValue             = fakeAppVersion();\n" +
	            "    const fakeBatteryValue                = fakeBattery();\n" +
	            "    const fakeConnectionValue             = fakeConnection();\n" +
	            "    const fakeGeoLocationValue            = fakeGeoLocation();\n" +
	            "    const fakeHardwareConcurrencyValue    = fakeHardwareConcurrency();\n" +
	            "    const fakeJavaEnabledValue            = fakeJavaEnabled();\n" +
	            "    const fakeLanguageValue               = fakeLanguage();\n" +
	            "    const fakeLanguagesValue              = fakeLanguages();\n" +
	            "    const fakeMimeTypesValue              = fakeMimeTypes();\n" +
	            "    const fakeOnLineValue                 = fakeOnLine();\n" +
	            "    const fakeOscpuValue                  = fakeOscpu();\n" +
	            "    const fakePermissionsValue            = fakePermissions();\n" +
	            "    const fakePlatformValue               = fakePlatform();\n" +
	            "    const fakePluginsValue                = fakePlugins();\n" +
	            "    const fakeProductValue                = fakeProduct();\n" +
	            "    const fakeServiceWorkerValue          = fakeServiceWorker();\n" +
	            "    const fakeStorageValue                = fakeStorage();\n" +
	            "    const fakeUserAgentValue              = fakeUserAgent();\n" +
	            "    const fakeBuildIDValue                = fakeBuildID();\n" +
	            " \n" +
	            "    Object.defineProperties(window.navigator, {\n" +
	            "        /*\n" +
	            "        activeVRDisplays: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getActiveVRDisplays() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.activeVRDisplays\");\n" +
	            "                return fakeActiveVRDisplaysValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        */\n" +
	            " \n" +
	            "        appCodeName: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getAppCodeName() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.appCodeName\");\n" +
	            " \n" +
	            "                return fakeAppCodeNameValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "\t\t\n" +
	            "\t\twebdriver: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getWebdriver() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.appCodeName\");\n" +
	            " \n" +
	            "                return undefined;\n" +
	            "            }\n" +
	            "        },\n" +
	            "\t\t\n" +
	            "        appName: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getAppName() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.appName\");\n" +
	            " \n" +
	            "                return fakeAppNameValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        appVersion: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getAppVersion() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.appVersion\");\n" +
	            " \n" +
	            "                return fakeAppVersionValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            " \n" +
	            "        // TODO: This is getBattery() now\n" +
	            "        /*\n" +
	            "        battery: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getBattery() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.battery\");\n" +
	            "                return fakeBatteryValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        connection: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getConnection() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.connection\");\n" +
	            "                return fakeConnectionValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        geolocation: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getGeoLocation() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.geolocation\");\n" +
	            "                return fakeGeoLocationValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        */\n" +
	            " \n" +
	            "        hardwareConcurrency: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getHardwareConcurrency() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.hardwareConcurrency\");\n" +
	            " \n" +
	            "                return fakeHardwareConcurrencyValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            " \n" +
	            "        /*\n" +
	            "        javaEnabled: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            value: function getJavaEnabled() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.javaEnabled\");\n" +
	            "                return fakeJavaEnabledValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        */\n" +
	            " \n" +
	            "        language: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getLanguage() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.language\");\n" +
	            " \n" +
	            "                return fakeLanguageValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        languages: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getLanguages() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.languages\");\n" +
	            " \n" +
	            "                return fakeLanguagesValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            " \n" +
	            "        /*\n" +
	            "        mimeTypes: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getMimeTypes() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.mimeTypes\");\n" +
	            "                return fakeMimeTypesValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        */\n" +
	            " \n" +
	            "        onLine: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getOnLine() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.onLine\");\n" +
	            " \n" +
	            "                return fakeOnLineValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        oscpu: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getOscpu() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.oscpu\");\n" +
	            " \n" +
	            "                return fakeOscpuValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            " \n" +
	            "        /*\n" +
	            "        permissions: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getPermissions() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.permissions\");\n" +
	            "                return fakePermissionsValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        */\n" +
	            " \n" +
	            "        platform: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getPlatform() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.platform\");\n" +
	            " \n" +
	            "                return fakePlatformValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            " \n" +
	            "        /*\n" +
	            "        plugins: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getPlugins() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.plugins\");\n" +
	            "                return fakePluginsValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        */\n" +
	            " \n" +
	            "        product: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getProduct() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.product\");\n" +
	            " \n" +
	            "                return fakeProductValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            " \n" +
	            "        /*\n" +
	            "        serviceWorker: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getServiceWorker() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.serviceWorker\");\n" +
	            "                return fakeServiceWorkerValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        storage: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getStorage() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.storage\");\n" +
	            "                return fakeStorageValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        */\n" +
	            " \n" +
	            "        userAgent: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getUserAgent() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.userAgent\");\n" +
	            " \n" +
	            "                return fakeUserAgentValue;\n" +
	            "            }\n" +
	            "        },\n" +
	            "        buildID: {\n" +
	            "            configurable: true,\n" +
	            "            enumerable: true,\n" +
	            "            get: function getBuildID() {\n" +
	            "                console.log(\"[ALERT] \" + window.location.hostname + \" accessed property Navigator.buildID\");\n" +
	            " \n" +
	            "                return fakeBuildIDValue;\n" +
	            "            }\n" +
	            "        }\n" +
	            "    });\n" +
	            "})();";
		return script;

	}

}
