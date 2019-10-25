# imageOptAndroid
imageOpt client library for Android, to construct parameterized imageOpt url from plain image url

## Download

### Gradle - Using jCenter

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

#### OR

### Gradle - Using Jitpack

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

## Usage
Code below demonstrats how to load an image from imageUrl into imageView.
```
/* Import imageOptClient */
import com.imageopt.imageoptclient.imageOptClient;

	...

/* Use the URL of imageSet obtained from imageOpt.com, this can be directly used 
 *or fetched from backend server */
 String imageUrl = "https://dbcaihe8155jo.cloudfront.net/9no-cq/q"

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
