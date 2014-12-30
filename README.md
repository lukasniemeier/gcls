NHL Gamecenter Live (Chromecast)
===============================

This project implements an Android sender app, which allows casting of NHL Gamecenter Live streams
 to a Chromecast device. Since it's a personal and prototypical project, there is no guarantee for
 it to work.

**This app does not circumvent the DRM content protection of NHL Gamecenter Live in any way. You
 need a valid NHL Gamecenter Live subscription to start this app.**

Build Instructions
--------------------------------

Take the following steps to build and deploy the application:

 * Install Android SDK components
 * Clone the [Chromecast Companion Library](https://github.com/googlecast/CastCompanionLibrary-android)
 into a folder *parent* as *parent/CastCompanionLibrary* (Version 1.12)
 * Clone this repository into the folder *parent* as *parent/gcls*
 * Setup the receiver application **gcls-receiver** (see corresponding README) and retrieve an *Application ID*
 * Use *app/config.gradle.template* to create a *app/config.gradle* fitting your *Application ID* and Gamecenter Live subscription
 * Build using gradle and deploy the APK

Security Considerations
--------------------------------

Currently the app stores your Gamecenter Live subscription in plain text (see build instructions).

Referenced libraries
--------------------------------

Thanks to the authors of the following projects:

 * [retrolambda](https://github.com/orfjackal/retrolambda)
 * [gradle-retrolambda](https://github.com/evant/gradle-retrolambda)
 * [droidparts](https://github.com/yanchenko/droidparts)
 * [joda-time](https://github.com/dlew/joda-time-android)
 * [material-dialogs](https://github.com/afollestad/material-dialogs)
