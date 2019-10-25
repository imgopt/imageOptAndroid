# imageOptAndroid
imageOpt client library for Android, to construct parameterized imageOpt url from plain image url

## Download

Gradle - Using jCenter

First add jCenter repository in your project `build.gradle` file:
```groovy
repositories {
	jcenter()
}
```
And then simply add the following line to the `dependencies` section of your app module `build.gradle` file:
```
dependencies {
	implementation 'com.imageopt:imageoptclient:1.0.0'
}
```

Gradle - Using Jitpack

First add JitPack repository line in your project `build.gradle` file:
```groovy
repositories {
	maven { 
		url 'https://jitpack.io'
	}
}
```
And then simply add the following line to the `dependencies` section of your app module `build.gradle` file:

```
dependencies {
	implementation 'com.github.imgopt:imageOptAndroid:1.0.0'
}
```
