package com.imageopt.imageoptclient;

import android.net.Uri;
import android.util.Size;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.Locale;

public class imageOptClient {


    /* Lets first define an interface for callback, we will be using this to call back from constructURL.
     * This is a simple interface with one function onSuccess which is called with imageOptURL(String) as a parameter.
     */

    public interface imageOptCallback {
        public void onSuccess(String imageOptUrl);
    }


    /* Lets define the function constructURLWithLocale with following parameters
     * Parameters:
     *   imageUrl : specifies the url of the image set to be loaded
     *   imageView : specifies the image view into which the image will loaded/displayed
     *   crop : whether or not, the image can be cropped if needed to match the requested size
     *   overrideSize : if specified this parameter will be used as image size else the size will be taken from image view.
     *   locale: if specified this parameter is specify the preferred language of the image
     *   callback : callback.onSuccess will be called with imageOpt url
     */

    public static void constructURLWithLocale(final String imageUrl, final ImageView imageView, final Boolean crop, final Size overrideSize, final Locale locale, final imageOptCallback callback) {

        /* Since we need the width and height of the image view, we have to wait for the system to perform the layout,
         * one way to achieve this to add a pre draw listener to view tree observer and do the processing inside onPreDraw().
         */

        imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                /* We will have the correct width and height inside onPreDraw(),
                 * now since we don't want our function to get called on all pre draw events,
                 * lets remove self as a listener on first call.
                 */
                if (imageView.getViewTreeObserver().isAlive())
                    imageView.getViewTreeObserver().removeOnPreDrawListener(this);

                /* Determine the final imageSize to be used, if overrideSize is passed
                 * use that as the final size, else use imageView's size.
                 */
                Size imageSize = (overrideSize != null) ? overrideSize : new Size(imageView.getWidth(), imageView.getHeight());

                /* Parse the given imageUrl into Uri instance and create an empty instance of Uri.Builder
                 */
                Uri uri = Uri.parse(Uri.decode(imageUrl));
                Uri.Builder builder = new Uri.Builder();

                /* Using Uri.Builder construct the imageOpt URL, take the scheme,
                 * authority(domain) and path from the uri
                 * Add query parameter 'w' for width equal to image width in pixels,
                 * similarly we add query parameter 'h' for height
                 */
                builder.scheme(uri.getScheme())
                        .authority(uri.getAuthority())
                        .path(uri.getPath())
                        .appendQueryParameter("w", String.valueOf(imageSize.getWidth()))
                        .appendQueryParameter("h", String.valueOf(imageSize.getHeight()));

                /* If crop flag is true add the crop query parameter 'c'.
                 */
                if (crop) {
                    builder.appendQueryParameter("c", "true");
                }

                /* If locale is specified add language query parameter 'l'.
                 */
                if (locale != null) {
                    builder.appendQueryParameter("l", locale.getLanguage());
                }

                /* Get the final URL string from the Uri.Builder
                 * and invoke callback.onSuccess with with the imageOpt URL.
                 */
                String imageOptUrl = builder.build().toString();
                callback.onSuccess(imageOptUrl);

                return false;
            }
        });
    }

    /* Variant without language parameter
     */
    public static void constructURL(final String imageUrl, final ImageView imageView, final Boolean crop, final Size overrideSize, final imageOptCallback callback) {
        constructURLWithLocale(imageUrl, imageView, crop, overrideSize, null, callback);
    }

    /* Variant which takes users current locale
     */
    public static void constructURLAutoLocale(final String imageUrl, final ImageView imageView, final Boolean crop, final Size overrideSize, final imageOptCallback callback) {
        constructURLWithLocale(imageUrl, imageView, crop, overrideSize, Locale.getDefault(), callback);
    }

}