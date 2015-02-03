package pl.androidland.studia.herbs.app.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.google.common.collect.Lists;
import pl.androidland.studia.herbs.app.AppBus;
import pl.androidland.studia.herbs.app.R;
import pl.androidland.studia.herbs.app.adapters.HerbsEntityAdapter;
import pl.androidland.studia.herbs.app.api.model.HerbsEntity;
import pl.androidland.studia.herbs.app.api.model.HerbsPriorityChoice;
import pl.androidland.studia.herbs.app.api.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;


public class HerbsActivity extends Activity {

    private ListView lvHerbs;
    private HerbsEntityAdapter herbsEntityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herbs_activity);
        lvHerbs = (ListView) findViewById(R.id.herbs_list_view);
        herbsEntityAdapter = new HerbsEntityAdapter(this, Lists.<HerbsEntity> newArrayList());
        lvHerbs.setAdapter(herbsEntityAdapter);

        new FetchHerbsTask().execute();
    }

    private class FetchHerbsTask extends AsyncTask<Void, Void, Void> {
        private final RestClient restClient = AppBus.getRestClient();
        private final ProgressDialog progressDialog = new ProgressDialog(HerbsActivity.this);

        @Override
        protected void onPreExecute() {
            this.progressDialog.setMessage(getString(R.string.TOAST_MSG_SEARCHING_HERBS));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.show();
                }
            });

        }

        @Override
        protected Void doInBackground(Void... params) {
            HerbsPriorityChoice choice = AppBus.getHerbsPreferences().getHerbsPriorityChoice();

            restClient.getService().findHerbs(choice, new Callback<List<HerbsEntity>>() {

                @Override
                public void success(List<HerbsEntity> herbsEntities, Response response) {
                    fillHerbsList(herbsEntities);
                    dismissProgressBar();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(HerbsActivity.this, "Nie udało się pobrać ziół!",
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

    private void fillHerbsList(List<HerbsEntity> herbsEntities) {
        herbsEntityAdapter.clear();
        herbsEntityAdapter.addAll(herbsEntities);
        herbsEntityAdapter.notifyDataSetChanged();
    }
}
