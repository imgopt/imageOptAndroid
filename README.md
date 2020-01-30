# imageOptClient - Android
imageOpt client library for Android, to construct parameterized imageOpt url from plain imageSet URL created at [imageOpt Image CDN][1]

For more information please see [imageOpt Image CDN][1]

## Download

#### Gradle - Using jCenter

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

##### OR

#### Gradle - Using Jitpack

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

## Documentation

#### imageOptCallBack
This is a simple interface with one function onSuccess which is called with parameterized imageOptURL(String) as a parameter.
```
    public interface imageOptCallback{
        public void onSuccess(String imageOptUrl);
    }
```
#### constructURL
Function to construct an imageOpt URL with query parameters, given an imageSet URL created at [imageOpt Image CDN][1] and an imageView. This function waits for imageView size to be finalized, then constructs parameterized imageOpt URL using the given imageSet URL and then calls onSuccess callback with that URL.

```
/* Parameters:
 *   imageUrl : specifies the url of the imageSet to be loaded
 *   imageView : specifies the image view into which the image will loaded/displayed
 *   crop : whether or not, the image can be cropped if needed to match the requested size
 *   overrideSize : if specified this parameter will be used as image size else the size 
 *                          will be taken from image view.
 *   callback : callback.onSuccess will be called with parameterized imageOpt url
 */
```

## Usage
Code below demonstrats how to load a image from imageUrl into imageView, in this case the request is to fit the image inside imageView.
```
/* Import imageOptClient */
import com.imageopt.imageoptclient.imageOptClient;

	...

/* Use the URL of imageSet obtained from imageOpt.com, this can be directly used 
 * or fetched from backend server */
 String imageUrl = "https://p1.imageopt.net/9zt-ct/q"

// Replace imageView with any other view of type ImageView as per your needs
final ImageView imageView = findViewById(R.id.image_view);

imageOptClient.constructURL( imageUrl, imageView, false, null,
	new imageOptClient.imageOptCallback() {
		@Override
		public void onSuccess(String imageOptUrl) {
			/* Here we use Picasso to fetch, you can use any method of your choice,
			 * just make sure standard cache control is enabled */
			Picasso.get()
				.load(imageOptUrl)
				.into(imageView);
		}
});
```
Code below demonstrates how to load a fixed size image(60x40) from imageUrl into imageView, in this case the request is to crop the image to request size.
```
import com.imageopt.imageoptclient.imageOptClient;

	...
	
String imageUrl = "https://p1.imageopt.net/9zt-ct/q"
final ImageView imageView = findViewById(R.id.image_view);

imageOptClient.constructURL( imageUrl, imageView, true, new Size(60,40),
	new imageOptClient.imageOptCallback() {
		@Override
		public void onSuccess(String imageOptUrl) {
			Picasso.get()
				.load(imageOptUrl)
				.into(imageView);
		}
});
```
License
--------

    Copyright (c) 2019 imageOpt

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: https://imageopt.com
