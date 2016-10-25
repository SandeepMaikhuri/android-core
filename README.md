# FiveUtils

## About

These are common functionalities used by Five Minutes apps. See it in use by running **demo app**. Inspired by [this guy](http://www.matkostankovic.com/repository/images/_variations/3/3/3361562781e9d349d15df3cf4461229f_medium.jpg).

This bundle contains utility classes we tend to re-use on many of our projects (us being [Five Agency's](http://five.agency) Android Team). We expect there'll be many more added, but in the mean time, you're free to use/re-use/upgrade all of the existing code here.

## Requirements

* Android Studio 2.2

## Importing the library

//TODO Add correct github link
* In your app project add this as repository as a submodule:
```
git submodule add git@bitbucket.org:fiveminutes/fiveminutes-android.git
```
* Import **fiveminutes** as library project

OR

* Add this library as gradle dependency by adding this line inside your app's dependency block in build.gradle:
```
//TODO
compile ''
```

## Examples

In order to run examples run the 'demo' module inside project you clone from:
```
git submodule add git@bitbucket.org:fiveminutes/fiveminutes-android.git
```

## Usage

Library contains number of utilities:

### Network

* ConnectivityInformation - utility class used to find out if device is connected to any network. **NOTE**: do not use this class to find out if device is connected to Internet.
* InternetAddressResolver - utility class used to check if web URL can be resolved.
* NetworkInformation - utility class used to find out if device is connected to Internet. It relies heavily on NetworkConnectionSurveillance. This has improved way of tracking whether device is connected on Internet and does not rely ony on Android's NetworkInfo.
* NetworkConnectionSurveillance - class used to monitor internet connection. It should be singleton inside the application. It will perform regular check to see whether device is connected to the Internet. When connectivity status changes, it will notify it's observers.

## Authors

* Tomislav Homan

* Tomislav Novoselec

* Luka Babić

## Licence

FiveUtils is available under the MIT license. See the [LICENSE](LICENSE) file for more info.

//old.
## Advice

* Do not push into master
* Do not put anything app-specific (_eu.fiveminutes.your-app-name_ stuff)
* Put dependencies in **fiveminutes/build.gradle**
* We like it when the code is test covered or has demo showcasing it. You can skip demo if your code is not interactive/visible to the user

## Writing tests

* Add test classes to **fiveminutes/src/androidTest**

## Adding demo

* Create activity demoing your work in **demo/src/main**
* Add that activity class to **Activities.java**