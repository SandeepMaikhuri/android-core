# FiveUtils

## About

This bundle contains utility classes we tend to re-use on many of our projects (us being [Five Agency's](http://five.agency) Android Team). We expect there'll be many more added, but in the mean time, you're free to use/re-use/upgrade all of the existing code here.

## Requirements

* Android Studio 2.2

## Importing the library

* Add this library as gradle dependency by adding this line inside your app's dependency block in build.gradle:
```
compile 'agency.five:android-core:1.0.0'
```

## Usage

Library contains number of utilities:

### Network

* **ConnectivityInformation** - utility class used to find out if device is connected to any network. **NOTE:** do not use this class to find out if device is connected to Internet.
* **InternetAddressResolver** - utility class used to check if web URL can be resolved.
* **NetworkInformation** - utility class used to find out if device is connected to Internet. It relies heavily on NetworkConnectionSurveillance. This has improved way of tracking whether device is connected on Internet and does not rely ony on Android's NetworkInfo.
* **NetworkConnectionSurveillance** - class used to monitor internet connection. It should be singleton inside the application. It will perform regular check to see whether device is connected to the Internet. When connectivity status changes, it will notify it's observers.

### Typefaced views

There are four typefaced views available in this library:

* TypefacedButton
* TypefacedEditText
* TypefacedPagerTabStrip
* TypefacedTextView

All of them can be included in layout.xml files.

* In order to apply typeface to a view, first typeface resource file (.ttf) must be included inside ```/assert/fonts``` folder and then apply this inside layout.xml:
```
<eu.fiveminutes.TypefacedTextView
 ...
 app:typeface="@string/typeface_name"/>
```

Where **typeface_name** is string resource with path for name of the typeface to be used (without /fonts prefix in its name).

### Utils

* **BluUtils** - Used to apply blur effect for bitmaps.
* **ColorUtils** - Used to make transformations on integer color values.
* **CryptoUtils** - //TODO
* **CurrentTimeProvider** - Used to get current UNIX time (both second and milliseconds).
* **DisplayUtils** - Used to find out information about device screen.
* **Foreground** - Used to track whether app is in foreground or background.
* **FragmentArguments** - //TODO
* **GeolocationUtils** - //TODO
* **ImageUtils** - Util for bitmap manipulation and loading images.
* **IntentUtils** - Util for creating common intents in Android.
* **ListUtils** - List utility used for evaluating various stuff on lists.
* **MathUtils** - Util for calculating transformed values
* **PicassoWrapper** - //TODO
* **ResourceUtils** - Util for getting resources from Android.
* **StopWatch** - //TODO
* **StringUtils** - Util for evaluating various operations on string value.

## Authors

* Tomislav Homan

* Tomislav Novoselec

* Luka Babić

## Licence

FiveUtils is available under the MIT license. See the [LICENSE](LICENSE) file for more info.
