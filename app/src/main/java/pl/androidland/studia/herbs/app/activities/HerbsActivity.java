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
import pl.androidland.studia.herbs.app.api.model.response.herb.HerbEntity;
import pl.androidland.studia.herbs.app.api.model.request.HerbsChoiceWrapper;
import pl.androidland.studia.herbs.app.api.rest.RestProvider;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;


public class HerbsActivity extends Activity {

    private HerbsEntityAdapter herbsEntityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herbs_activity);
        ListView lvHerbs = (ListView) findViewById(R.id.herbs_list_view);
        herbsEntityAdapter = new HerbsEntityAdapter(this, Lists.<HerbEntity> newArrayList());
        lvHerbs.setAdapter(herbsEntityAdapter);

        new FetchHerbsTask().execute();
    }

    private class FetchHerbsTask extends AsyncTask<Void, Void, Void> {
        private final RestProvider restProvider = AppBus.getRestProvider();
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
            HerbsChoiceWrapper choice = AppBus.getHerbsChoiceProvider().getHerbsChoiceWrapper();

            restProvider.getService().findHerbs(choice, new Callback<List<HerbEntity>>() {

                @Override
                public void success(List<HerbEntity> herbsEntities, Response response) {
                    fillHerbsList(herbsEntities);
                    dismissProgressBar();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(HerbsActivity.this, getString(R.string.DOESNT_FETCH_HERBS),
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

    private void fillHerbsList(List<HerbEntity> herbsEntities) {
        herbsEntityAdapter.clear();
        herbsEntityAdapter.addAll(herbsEntities);
        herbsEntityAdapter.notifyDataSetChanged();
    }
}
