package pow.jie.myimageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import pow.jie.myimageloader.cache.DiskCache;
import pow.jie.myimageloader.cache.MemoryCache;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        String url = "https://konachan.net/jpeg/874b27845ffa79d637da4980fe771fb8/Konachan.com%20-%20281586%202girls%20blonde_hair%20brown_hair%20chibi%20futaba_anzu%20hat%20ichihara_nina%20idolmaster%20kneehighs%20long_hair%20mitarashi_neko%20seifuku%20shorts%20skirt%20socks%20twintails.jpg";
        ImageLoader loader = new ImageLoader();
        loader.setmImageCache(new MemoryCache());
        loader.displayImage(url,imageView);
    }
}
