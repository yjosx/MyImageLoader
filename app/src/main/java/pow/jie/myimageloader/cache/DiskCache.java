package pow.jie.myimageloader.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;

public class DiskCache implements ImageCache {
    private static String cacheDir = "sdcard/cache";

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
