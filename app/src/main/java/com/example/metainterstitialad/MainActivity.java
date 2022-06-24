package com.example.metainterstitialad;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdSettings.setTestMode(true); //Set this false for release builds

        TextView textView = findViewById(R.id.tv);

        InterstitialAd interstitialAd = new InterstitialAd(MainActivity.this, "YOUR_PLACEMENT_ID");
        AbstractAdListener adListener = new AbstractAdListener() {
            @Override
            public void onAdClicked(Ad ad) {
                super.onAdClicked(ad);
                Toast.makeText(MainActivity.this, "Ad Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                super.onAdLoaded(ad);
                interstitialAd.show();
                Toast.makeText(MainActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialDisplayed(Ad ad) {
                super.onInterstitialDisplayed(ad);
                Toast.makeText(MainActivity.this, "Ad Displayed", Toast.LENGTH_SHORT).show();
                textView.setText("Ad Displayed");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                super.onInterstitialDismissed(ad);
                Toast.makeText(MainActivity.this, "Ad Dismissed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Ad ad, AdError error) {
                super.onError(ad, error);
                textView.setText(error.getErrorMessage());
                Toast.makeText(MainActivity.this, "Error: " + error.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        InterstitialAd.InterstitialLoadAdConfig config = interstitialAd.buildLoadAdConfig().withAdListener(adListener).build();
        interstitialAd.loadAd(config);
    }
}