package pl.androidland.studia.herbs.app.activities;

import android.widget.Toast;
import pl.androidland.studia.herbs.app.AppBus;
import pl.androidland.studia.herbs.app.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fetchCriteria(View view) {
        if (isInternetConnection())
            startActivity(new Intent(this, CriteriaActivity.class));
        else
            Toast.makeText(this, getString(R.string.CHECK_INTERNET_CONNECTION), Toast.LENGTH_SHORT).show();
    }

    private boolean isInternetConnection() {
        return AppBus.getConnectionDetector().isConnected();
    }

}
