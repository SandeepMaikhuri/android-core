# Five Minutes Library

These are common functionalities used by Five Minutes apps. See it in use by running **demo app**. Inspired by [this guy](http://www.matkostankovic.com/repository/images/_variations/3/3/3361562781e9d349d15df3cf4461229f_medium.jpg).

## Using the library

* In your app project add this as repository as a submodule: `git submodule add git@bitbucket.org:fiveminutes/fiveminutes-android.git`
* Import **fiveminutes** as library project
* Create branch: `cd fiveminutes-android && git checkout -b your-branch-name`
* While developing, put anything that may be of use to other to library

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