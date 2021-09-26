# Localization


## What's this?

`Localization` is a library for multi-language application written in Kotlin.

## Requirements

* Android 5.0 (minSDK 21)

## Installation

#### MavenCentral

```
implementation 'com.cloud273:localization:1.0.2'
```
## Usage

#### Required files:

Add files to res/raw:

- English: 'localization_en.json'

- Vietnamese: 'localization_vi.json'

- And more ....

Content of file:

```json
{
    "button": "My button",
    "label": "My label",
    "cancel": "Cancel",
    "next": "Next",
    "hint": "Tap here to input"
}

```

#### Initialize:

- Setup main application and receiver in AndroidManifest.xml

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.cloud273.localization.example">
    <application
        android:name=".MyApplication" // SETUP MAIN APPLICATION 
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Example">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        
        // SETUP RECEIVER 
        <receiver
            android:name="com.cloud273.localization.LocaleChangedReceiver"
            android:enabled="true"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.LOCALE_CHANGED" />
            </intent-filter>
        </receiver>
    </application>

</manifest>

```
- Initialize list of supported language inside your main application (MyApplication):

```
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CLLocalization.initialize(this, listOf("en", "vi"))
    }
}
     
```

Note: Device language will be used

#### Change language:

- Change to target language:

```
CLLocalization.setLanguage("en")
     
```

- Change back to use device language:

```
CLLocalization.setLanguage(null)

```

- Monitor language changed for activity:

```
override fun onDestroy() {
    super.onDestroy()
    stopMonitorLanguageChanged()
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    startMonitorLanguageChanged()
}

```

#### Code:

- String

```
String localizedString = "text".localized

```

- Activity:

```
activity.lTitle = "title"

```

- Fragment:

```
fragment.lTitle = "title"

```

- TextView:

```
textView.lText = "label"

```

- Button:

```
button.lText = "button"

```

- RadioButton:

```
button.lText = "button"

```

- CheckBox:

```
checkBox.lText = "title"

```

- EditText:

```
editText.hint = "hint"

```

## Example

Please find in project

## Contribution

You are welcome to fork and submit pull requests.

## License

`Localization` is open-sourced software, licensed under the `MIT` license.
