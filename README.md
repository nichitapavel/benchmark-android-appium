# Appium control of the benchmark app in Android

## Requirements
- [gradle 5.1](https://gradle.org/releases/)
- [oracle/open jdk => v1.8_u191](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)


## Appium
An appium server with an Android device connected and with the matrix app installed must be previously configured.  
From version 0.3 **npm install** is supported. Go to the distribution's  bin folder, there you'll find _package.json_
and _package-lock.json_ , if you have **node** and **npm** installed and configured properly just type in cmd
`npm install`.  
To run appium type `node_modules/.bin/appium` in the bin folder. Is better if you run appium in a screen session.  
The appium server (*hub*) details must be copied into **appium-conf.json** file, just change the available fields.
The *device* fields are optional as long as you use available cmd arguments, otherwise change them to your desired
values.  


## Instructions
After cloning use these commands to compile
- `./gradlew build`

Make use of the compiled app:
- go to *build/distributions/*
- extract either **.zip** or **.tar** archive
- go to *benchmark-android-appium-<version>/bin/*, here are the executables **benchmark-android-appium**/
**benchmark-android-appium.bat**
- execute by typing `./benchmark-android-appium -s 10 -m 10 -e http://192.168.26.5:5001/message` or
 `./benchmark-android-appium -s 10 -m 10 -e http://192.168.26.5:5001/message -p -d FJASDOQX --system-port 8199`
- keep in mind that **appium-conf.json** must be where you execute and that appium server must be running.

Install and run Appium server:
- go to *build/distributions/*
- extract either **.zip** or **.tar** archive
- go to *benchmark-android-appium-<version>/bin/*, here are the executables **package.json**/ and
**package-lock.json**
- install appium by typing `npm install` 
- run appium by typing `node_modules/.bin/appium`


## Arguments
The app has 6 arguments, 3 are required. These arguments are send to the Android app for control.
The `device` and `system-port` are used to define witch device will run the app.
- `-s|--size` _(Required)_ **String** type **Integer** tells the size of the matrixes
(only square matrixes are possible).
- `-m|--module` _(Required)_ **String** type **Integer** tells the maximum value of a coordinate,
i.e.: if this value is 40 you will never have in the matrix A and B a number equal or higher to 40.
- `-e|--http-endpoint` _(Required)_ **String** type **URL** `http://192.168.26.5:5001/message` tells where
devices must send HTTP requests to mark current operation.
- `-p|--print` _(Optional)_ tells if you want the matrixes to be printed, no value required.
- `-d|--device` _(Optional)_ **String** unique device identification (udid), listed with `adb devices`. Optional if
available in **appium-conf.json**.
- `--system-port` _(Optional)_ **String** type **Integer**, recommended in case multiple android devices are
simultaneously used.


## Oracle vs Open JRE
By default the generated binary uses java runtime returned from `which java`, usually this is **Open JRE**. If you want
to use Oracle JRE you must specify JAVA_HOME to that runtime, i.e.: 
`JAVA_HOME=/opt/jre1.8.0_202 ./benchmark-android-appium 10 10 true`

## Current version: 0.1
## Benchmark Android app compatibility: XXX*
