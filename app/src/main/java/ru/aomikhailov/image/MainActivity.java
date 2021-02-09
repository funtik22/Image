package ru.aomikhailov.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button bStart;
    ProgressBar progressBar;
    TextView text;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStart = (Button) findViewById(R.id.btStart);
        image = (ImageView) findViewById(R.id.imageView);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadImage(image).execute("https://cdn.fishki.net/upload/post/2017/03/14/2241146/59e23a649573446e471f1f6f900b0958.jpg");
            }
        });
    }
    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public LoadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String UrlStr = urls[0];
            Bitmap BitmapImage = null;
            try {
                InputStream is = new java.net.URL(UrlStr).openStream();
                BitmapImage = BitmapFactory.decodeStream(is);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return BitmapImage;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}