package xyz.gonzapico.sa_butterknife;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by gonzapico on 23/10/2017.
 */

public class VolleyHandler {
  public static final String TAG = VolleyHandler.class
      .getSimpleName();
  private static VolleyHandler mVolleyHandlerInstance;
  private static Context mContext;
  private RequestQueue mRequestQueue;
  private ImageLoader mImageLoader;

  public static synchronized VolleyHandler getInstance(Context context) {
    if (mVolleyHandlerInstance == null) {
      mVolleyHandlerInstance = new VolleyHandler(context);
    }
    return mVolleyHandlerInstance;
  }

  private void createImageLoader(){
    mImageLoader = new ImageLoader(mRequestQueue,
        new ImageLoader.ImageCache() {
          private final LruCache<String, Bitmap>
              cache = new LruCache<String, Bitmap>(20);

          @Override
          public Bitmap getBitmap(String url) {
            return cache.get(url);
          }

          @Override
          public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
          }
        });
  }

  private VolleyHandler(Context context) {
    mContext = context;
    mRequestQueue = getRequestQueue();
  }

  public RequestQueue getRequestQueue() {
    if (mRequestQueue == null) {
      // getApplicationContext() keeps you from leaking the Activity
      mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
    }
    return mRequestQueue;
  }

  public ImageLoader getImageLoader() {
    if (mImageLoader == null){
      createImageLoader();
    }
    return mImageLoader;
  }

  public <T> void addToRequestQueue(Request<T> req) {
    req.setTag(TAG);
    getRequestQueue().add(req);
  }

  public <T> void addToRequestQueue(Request<T> req,String tag) {
    req.setTag(tag);
    getRequestQueue().add(req);
  }

  public void cancelPendingRequests(Object tag) {
    if (mRequestQueue != null) {
      mRequestQueue.cancelAll(tag);
    }
  }
}
