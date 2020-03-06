# imageOptClient - Android
imageOpt client library for Android, to construct parameterized imageOpt url from plain imageSet URL created at [Image CDN][1]

For more information please see [Image CDN][1]

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
	implementation 'com.imageopt:imageoptclient:1.4.0'
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
	implementation 'com.github.imgopt:imageOptAndroid:1.4.0'
}
```

## Documentation

#### imageOptCallBack
This is a simple interface with one function onSuccess which is called with parameterized imageOptURL(Uri) as a parameter.
```
    public interface imageOptCallback{
        public void onSuccess(Uri imageOptURL);
    }
```
#### constructURL with imageView
Function to construct an imageOpt URL with query parameters, given an imageSet URL created at [Image CDN][1] and an imageView. This function waits for imageView size to be finalized, then constructs parameterized imageOpt URL using the given imageSet URL & crop parameter, and finally calls onSuccess callback with that URL.

```
/* Parameters:
 *   imageURL : specifies the url of the imageSet to be loaded
 *   imageView : specifies the image view into which the image will loaded/displayed
 *   crop : whether or not, the image can be cropped if needed to match the requested size
 *   callback : callback.onSuccess will be called with parameterized imageOpt url
 */
void constructURL(String imageURL, ImageView imageView, Boolean crop, imageOptCallback callback)
```
#### constructURL with imageView and current system locale/language
Function to construct an imageOpt URL with query parameters, given an imageSet URL created at [Image CDN][1] and an imageView. This function waits for imageView size to be finalized, then constructs parameterized imageOpt URL using the given imageSet URL, crop parameter & current locale/language of the device, and finally calls onSuccess callback with that URL.

```
/* Parameters:
 *   imageURL : specifies the url of the imageSet to be loaded
 *   imageView : specifies the image view into which the image will loaded/displayed
 *   crop : whether or not, the image can be cropped if needed to match the requested size
 *   useSystemLocale: specifies that system locale/language to be used
 *   callback : callback.onSuccess will be called with parameterized imageOpt url
 */
void constructURL(String imageURL, ImageView imageView, Boolean crop, Boolean useSystemLocale, imageOptCallback callback)
```
#### constructURL with imageView and explicit locale parameter
Function to construct an imageOpt URL with query parameters, given an imageSet URL created at [Image CDN][1] and an imageView. This function waits for imageView size to be finalized, then constructs parameterized imageOpt URL using the given imageSet URL, crop parameter & locale parameter and finally calls onSuccess callback with that URL.

```
/* Parameters:
 *   imageURL : specifies the url of the imageSet to be loaded
 *   imageView : specifies the image view into which the image will loaded/displayed
 *   crop : whether or not, the image can be cropped if needed to match the requested size
 *   locale : specifies the preferred language of the image
 *   callback : callback.onSuccess will be called with parameterized imageOpt url
 */
void constructURL(String imageURL, ImageView imageView, Boolean crop, Locale locale, imageOptCallback callback)
```
#### constructURL with imageSize
Function to construct an imageOpt URL with query parameters, given an imageSet URL created at [Image CDN][1] and imageSize. This function constructs parameterized imageOpt URL using the given imageSet URL & crop parameter, and finally returns imageOpt URL.

```
/* Parameters:
 *   imageURL : specifies the url of the imageSet to be loaded
 *   imageSize : specifies the size of the image to be loaded
 *   crop : whether or not, the image can be cropped if needed to match the requested size
 * Returns
 *   imageOptURL: imageOpt URL for the requested parameters
 */
Uri constructURL(String imageURL, Size imageSize, Boolean crop)
```
#### constructURL with imageSize and current system locale/language
Function to construct an imageOpt URL with query parameters, given an imageSet URL created at [Image CDN][1] and an imageSize. This function constructs parameterized imageOpt URL using the given imageSet URL, crop parameter & current locale/language of the device, and finally returns imageOpt URL.

```
/* Parameters:
 *   imageURL : specifies the url of the imageSet to be loaded
 *   imageSize : specifies the size of the image to be loaded
 *   crop : whether or not, the image can be cropped if needed to match the requested size
 *   useSystemLocale: specifies if system locale/language to be used
 * Returns
 *   imageOptURL: imageOpt URL for the requested parameters
 */
Uri constructURL(String imageURL, Size imageSize, Boolean crop, Boolean useSystemLocale)
```
#### constructURL with imageView and explicit locale parameter
Function to construct an imageOpt URL with query parameters, given an imageSet URL created at [Image CDN][1] and an imageSize. This function constructs parameterized imageOpt URL using the given imageSet URL, crop parameter & locale parameter and finally returns imageOpt URL.

```
/* Parameters:
 *   imageURL: specifies the url of the imageSet to be loaded
 *   imageSize : specifies the size of the image to be loaded
 *   crop : whether or not, the image can be cropped if needed to match the requested size
 *   locale : specifies the preferred language of the image
 * Returns
 *   imageOptURL: imageOpt URL for the requested parameters
 */
Uri constructURL(String imageURL, Size imageSize, Boolean crop, Locale locale)
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

imageOptClient.constructURL( imageUrl, imageView, false,
	new imageOptClient.imageOptCallback() {
		@Override
		public void onSuccess(Uri imageOptUrl) {
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
	
String imageUrl = "https://p1.imageopt.net/9zt-ct/q";
final ImageView imageView = findViewById(R.id.image_view);

Uri imageOptUri = imageOptClient.constructURL( imageUrl,
                                               new Size(60,40),
                                               true );
Picasso.get()
	.load(imageOptUri)
	.into(imageView);
```
Code below demonstrats how to load a localized image from the imageSet, in this case the first preferred language set by the user in settings will be used.
```
/* Import imageOptClient */
import com.imageopt.imageoptclient.imageOptClient;

	...

/* Use the URL of imageSet obtained from imageOpt.com, this can be directly used
 * or fetched from backend server */
 String imageUrl = "https://p1.imageopt.net/9zt-ct/q";

// Replace imageView with any other view of type ImageView as per your needs
final ImageView imageView = findViewById(R.id.image_view);

imageOptClient.constructURL( imageUrl, imageView, false, true,
	new imageOptClient.imageOptCallback() {
		@Override
		public void onSuccess(Uri imageOptUrl) {
			/* Here we use Picasso to fetch, you can use any method of your choice,
			 * just make sure standard cache control is enabled */
			Picasso.get()
				.load(imageOptUrl)
				.into(imageView);
		}
});
```
Code below demonstrats how to load a localized image from the imageSet, in this case the preferred locale is passed as a parameter.
```
/* Import imageOptClient */
import com.imageopt.imageoptclient.imageOptClient;

	...

/* Use the URL of imageSet obtained from imageOpt.com, this can be directly used
 * or fetched from backend server */
 String imageUrl = "https://p1.imageopt.net/9zt-ct/q"

// Replace imageView with any other view of type ImageView as per your needs
final ImageView imageView = findViewById(R.id.image_view);

// For example create a locale with a language code
Locale locale = new Locale("ar");

imageOptClient.constructURL( imageUrl, imageView, false, locale,
	new imageOptClient.imageOptCallback() {
		@Override
		public void onSuccess(Uri imageOptUrl) {
			/* Here we use Picasso to fetch, you can use any method of your choice,
			 * just make sure standard cache control is enabled */
			Picasso.get()
				.load(imageOptUrl)
				.into(imageView);
		}
});
```
License
--------

    Copyright (c) 2019-20 imageOpt

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
