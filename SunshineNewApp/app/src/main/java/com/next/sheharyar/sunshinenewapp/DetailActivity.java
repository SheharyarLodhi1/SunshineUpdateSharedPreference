package com.next.sheharyar.sunshinenewapp;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    TextView mDisplayWeatherData;
    public static String TEXT_HOLDER_FOR_WEATHER;
    String dataComingFromMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDisplayWeatherData = (TextView)findViewById(R.id.tv_display_weather);

        displayDataComingFromMainActivity();
    }

    private void displayDataComingFromMainActivity() {

        Intent getData = getIntent();
        if (getData.hasExtra(Intent.EXTRA_TEXT)){
            dataComingFromMain = getData.getStringExtra(Intent.EXTRA_TEXT);
            mDisplayWeatherData.setText(dataComingFromMain);
            TEXT_HOLDER_FOR_WEATHER = mDisplayWeatherData.toString();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_settings){
//            ShareYourWeatherData shareYourWeatherData = new ShareYourWeatherData();
//            shareYourWeatherData.shareYourData(TEXT_HOLDER_FOR_WEATHER);
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        } else if (itemId == android.R.id.home){
            onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private Intent createShareForecastIntent(){
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(dataComingFromMain + FORECAST_SHARE_HASHTAG)
                .getIntent();
        return shareIntent;
    }
}
