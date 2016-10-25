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

## Usage



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