package es.upm.alumnos.femapprestroberthloaiza.database;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import es.upm.alumnos.femapprestroberthloaiza.R;
import es.upm.alumnos.femapprestroberthloaiza.api.models.Result;
import es.upm.alumnos.femapprestroberthloaiza.api.models.Results;
import es.upm.alumnos.femapprestroberthloaiza.api.manager.Key_Api;
import es.upm.alumnos.femapprestroberthloaiza.api.manager.APIManager;
import es.upm.alumnos.femapprestroberthloaiza.database.contract.ResultContract;
import es.upm.alumnos.femapprestroberthloaiza.database.contract.ApiKeyContract;
import es.upm.alumnos.femapprestroberthloaiza.database.parcelable.ApiKeyParce;
import es.upm.alumnos.femapprestroberthloaiza.database.storage.Database;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Usuario on 27/10/2017.
 */

public class DataBaseActivity extends Activity {

    private Key_Api key_api;
    private APIManager apiManager;
    private Results results;
    private Database databaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        this.key_api = new Key_Api();
        this.apiManager = new APIManager();
        this.results = new Results();
        this.databaseStorage = new Database(getApplicationContext());
        this.onCreate();

    }

    private void onCreate() {
        this.GetLicors();
        this.getApiKey();
    }


    private void GetLicors() {

        DataBaseAsyncTask<Results> myTask = new DataBaseAsyncTask<Results>(this) {
            @Override
            protected void onPreExecute() {
            }

            @Override
            protected Response<Results> doInBackground(ListView... ListViews) {
                Response<Results> response = null;
                Call<Results> call = this.getDataBaseActivity().apiManager.getLicors("Corona", this.getDataBaseActivity().key_api.getAPIKey(), 100);
                try {
                    response = call.execute();
                } catch (IOException e) {
                    Log.i("MiW", e.getMessage());
                }
                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        this.getDataBaseActivity().results = (Results) response.body();
                    viewLicors();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.strError, Toast.LENGTH_LONG).show();
                }
            }
        };
        myTask.execute();
    }


    private void viewLicors() {
        if (this.results.getResult().size() > 0) {
            if (this.databaseStorage.count(ResultContract.licorsTable.TABLE_NAME) == 0)
                this.onInsertLicors();
            ListView listLicors = (ListView) findViewById(R.id.list);
            ArrayList<String> licors = new ArrayList<>();

            for (Result licor : this.results.getResult())
                licors.add(licor.toString());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    licors);

            listLicors.setAdapter(arrayAdapter);


        }
    }

    private void onInsertLicors() {
        for (Result result : this.results.getResult())
            this.databaseStorage.onInsertLicors(result.getId(), result.getName(),
                    result.getTags(), result.getPriceInCents(), result.getPrimaryCategory(),
                    result.getOrigin(), result.getPackageUnitVolumeInMilliliters(),
                    result.getAlcoholContent(), result.getProducerName(), result.getImageThumbUrl(),
                    result.getVarietal(), result.getStyle());
    }


    private void getApiKey() {
        if (this.key_api.getAPIKey() != null) {
            if (this.databaseStorage.count(ApiKeyContract.ApiTable.TABLE_NAME) == 0)
                this.insertApiKey();

            ArrayList<String> API = new ArrayList<>();
            ApiKeyParce api_key = this.databaseStorage.getAPIKey();
            API.add(api_key.toString());
        } else {
            Toast.makeText(getApplicationContext(), R.string.strError, Toast.LENGTH_SHORT).show();
        }
    }

    private void insertApiKey() {
        this.databaseStorage.onInsertApiKey(this.key_api.getAPIKey());
    }
}