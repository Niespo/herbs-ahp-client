package pl.androidland.studia.herbs.app.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.google.common.collect.Lists;
import pl.androidland.studia.herbs.app.AppBus;
import pl.androidland.studia.herbs.app.R;
import pl.androidland.studia.herbs.app.adapters.HerbsCriteriaAdapter;
import pl.androidland.studia.herbs.app.api.model.HerbsCriterion;
import pl.androidland.studia.herbs.app.api.model.HerbsPreferences;
import pl.androidland.studia.herbs.app.api.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;
import java.util.Map;


public class CriteriaActivity extends Activity {

    private HerbsCriteriaAdapter herbsCriteriaAdapter;
    private ListView lvHerbsCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criteria_activity);
        lvHerbsCriteria = (ListView) findViewById(R.id.herbs_criteria_list_view);
        herbsCriteriaAdapter = new HerbsCriteriaAdapter(this, Lists.<HerbsCriterion> newArrayList());
        lvHerbsCriteria.setAdapter(herbsCriteriaAdapter);
        new FetchHerbsCriteriaTask().execute();
    }

    private class FetchHerbsCriteriaTask extends AsyncTask<Void, Void, Void> {
        private final RestClient restClient = AppBus.getRestClient();
        private final ProgressDialog progressDialog = new ProgressDialog(CriteriaActivity.this);

        @Override
        protected void onPreExecute() {
            this.progressDialog.setMessage("Pobieram kryteria! Proszę chwilkę poczekać!");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.show();
                }
            });

        }

        @Override
        protected Void doInBackground(Void... params) {
            restClient.getService().getCriteriaList(new Callback<List<HerbsCriterion>>() {
                @Override
                public void success(List<HerbsCriterion> herbsCriterias, Response response) {
                    fillCriteriaList(herbsCriterias);
                    dismissProgressBar();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(CriteriaActivity.this, "Nie udało się pobrać kryteriów!",
                            Toast.LENGTH_SHORT).show();
                    dismissProgressBar();
                }

                private void dismissProgressBar() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                        }
                    });
                }
            });
            return null;
        }

    }

    private void fillCriteriaList(List<HerbsCriterion> herbsCriterias) {
        herbsCriteriaAdapter.clear();
        herbsCriteriaAdapter.addAll(herbsCriterias);
        herbsCriteriaAdapter.notifyDataSetChanged();
    }

    public void postHerbsCriteria(View view) {
        Map<String, Integer> preferenceMap = herbsCriteriaAdapter.getPreferencesMap();
        HerbsPreferences preferences = new HerbsPreferences.Builder()
                .withPreferencesChoice(preferenceMap).build();
        AppBus.setHerbsPreferences(preferences);
        startActivity(new Intent(this, HerbsActivity.class));
    }
}
