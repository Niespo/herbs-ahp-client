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
import pl.androidland.studia.herbs.app.api.model.response.HerbsCriterion;
import pl.androidland.studia.herbs.app.api.model.request.HerbsChoiceProvider;
import pl.androidland.studia.herbs.app.api.rest.RestProvider;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;
import java.util.Map;


public class CriteriaActivity extends Activity {

    private HerbsCriteriaAdapter herbsCriteriaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criteria_activity);
        ListView lvHerbsCriteria = (ListView) findViewById(R.id.herbs_criteria_list_view);
        herbsCriteriaAdapter = new HerbsCriteriaAdapter(this, Lists.<HerbsCriterion> newArrayList());
        lvHerbsCriteria.setAdapter(herbsCriteriaAdapter);
        new FetchHerbsCriteriaTask().execute();
    }

    private class FetchHerbsCriteriaTask extends AsyncTask<Void, Void, Void> {
        private final RestProvider restProvider = AppBus.getRestProvider();
        private final ProgressDialog progressDialog = new ProgressDialog(CriteriaActivity.this);

        @Override
        protected void onPreExecute() {
            this.progressDialog.setMessage(getString(R.string.CRITERIA_DOWNLOAD));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.show();
                }
            });

        }

        @Override
        protected Void doInBackground(Void... params) {
            restProvider.getService().getCriteriaList(new Callback<List<HerbsCriterion>>() {
                @Override
                public void success(List<HerbsCriterion> herbsCriterias, Response response) {
                    fillCriteriaList(herbsCriterias);
                    dismissProgressBar();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(CriteriaActivity.this, getString(R.string.DOESNT_FETCH_CRITERIA),
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
        if (!AppBus.getConnectionDetector().isConnected()) {
            Toast.makeText(this, getString(R.string.CHECK_INTERNET_CONNECTION), Toast.LENGTH_SHORT).show();
            return;
        }

        executeHerbsRequest();
    }

    private void executeHerbsRequest() {
        Map<String, Integer> preferenceMap = herbsCriteriaAdapter.getPreferencesMap();
        HerbsChoiceProvider preferences = new HerbsChoiceProvider.Builder()
                .withPreferencesChoice(preferenceMap).build();
        AppBus.setHerbsChoiceProvider(preferences);
        startActivity(new Intent(this, HerbsActivity.class));
    }
}
