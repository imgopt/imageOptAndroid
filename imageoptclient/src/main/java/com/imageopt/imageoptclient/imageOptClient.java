package com.imageopt.imageoptclient;

import android.net.Uri;
import android.util.Size;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

interface imageOptCallback{
    public void onSuccess(String imageOptUrl);
}

public class imageOptClient {
    public void constructURL(final String imageUrl, final ImageView imageView, final Boolean crop, final Size overrideSize, final imageOptCallback callback) {
        imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (imageView.getViewTreeObserver().isAlive())
                    imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                Size imageSize = (overrideSize!=null) ? overrideSize : new Size(imageView.getWidth(),imageView.getHeight());
                //Log.d("IMG", "Width:"+String.valueOf(holder.mImageView.getWidth())+" Height:"+String.valueOf(holder.mImageView.getHeight()));
                Uri uri = Uri.parse(Uri.decode(imageUrl));
                Uri.Builder builder = new Uri.Builder();
                builder.scheme(uri.getScheme())
                        .authority(uri.getAuthority())
                        .path(uri.getPath())
                        .appendQueryParameter("w", String.valueOf(imageSize.getWidth()))
                        .appendQueryParameter("h", String.valueOf(imageSize.getHeight()));
 f
                if (crop) {
                    builder.appendQueryParameter("c", "true");
                }
                String imageOptUrl = builder.build().toString();
                callback.onSuccess(imageOptUrl);
                return false;
            }
        });
    }
}
