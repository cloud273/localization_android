# Localization


## What's this?

`Localization` is a library for multi-language application written in Kotlin.

## Requirements

* Android 5.0 (minSDK 21)

## Installation

#### MavenCentral

```
implementation 'com.cloud273:cllocalization:1.0.0'
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

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.cloud273.localization.example">
    <!--Setup application to your class: android:name=".MyApplication"-->
    <application
        android:name=".MyApplication">
        <!--Setup receiver -->
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

```kotlin
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

```kotlin
CLLocalization.setLanguage("en")
     
```

- Change back to use device language:

```kotlin
CLLocalization.setLanguage(null)

```

- Monitor language change in activity:

```kotlin
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

```kotlin
val localizedString = "text".localized

```

- Activity:

```kotlin
activity.lTitle = "title"

```

- Fragment:

```kotlin
fragment.lTitle = "title"

```

- TextView:

```kotlin
textView.lText = "label"

```

- Button:

```kotlin
button.lText = "button"

```

- RadioButton:

```kotlin
button.lText = "button"

```

- CheckBox:

```kotlin
checkBox.lText = "title"

```

- EditText:

```kotlin
editText.hint = "hint"

```

## Example

Please find in project

## Contribution

You are welcome to fork and submit pull requests.

## License

`Localization` is open-sourced software, licensed under the `MIT` license.
