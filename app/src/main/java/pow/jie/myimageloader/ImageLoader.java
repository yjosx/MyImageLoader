package pow.jie.myimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pow.jie.myimageloader.cache.ImageCache;

public class ImageLoader {
    private static final String TAG = "ImageLoader";
    private ImageCache mImageCache;
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private void updateImageView(final ImageView imageView, final Bitmap bitmap) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    public void setmImageCache(ImageCache mImageCache) {
        this.mImageCache = mImageCache;
    }

    public void displayImage(String url, ImageView imageView) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null)
            updateImageView(imageView, bitmap);
        else
            submitDownload(url, imageView);
    }

    private void submitDownload(final String imageUrl, final ImageView imageView) {
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = download(imageUrl);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(imageUrl)) {
                    updateImageView(imageView, bitmap);
                }
                mImageCache.put(imageUrl, bitmap);
            }
        });
    }

    private Bitmap download(String imgurl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imgurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
