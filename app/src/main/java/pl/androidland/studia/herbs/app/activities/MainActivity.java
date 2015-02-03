package pl.androidland.studia.herbs.app.activities;

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
        startActivity(new Intent(this, CriteriaActivity.class));
    }

}
