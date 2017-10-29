package es.upm.alumnos.femapprestroberthloaiza.database;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import es.upm.alumnos.femapprestroberthloaiza.R;
import es.upm.alumnos.femapprestroberthloaiza.api.models.Result;
import es.upm.alumnos.femapprestroberthloaiza.api.models.Results;
import es.upm.alumnos.femapprestroberthloaiza.api.manager.Key_Api;
import es.upm.alumnos.femapprestroberthloaiza.api.manager.APIManager;
import es.upm.alumnos.femapprestroberthloaiza.database.contract.RankingContract;
import es.upm.alumnos.femapprestroberthloaiza.database.contract.ResultContract;
import es.upm.alumnos.femapprestroberthloaiza.database.parcelable.RankingParce;
import es.upm.alumnos.femapprestroberthloaiza.database.parcelable.ResultParce;
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
    private static final String CATEGORY = "Beer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        this.key_api = new Key_Api();
        this.apiManager = new APIManager();
        this.results = new Results();
        this.databaseStorage = new Database(getApplicationContext());
        this.initializeApp();

        Button buttonGETLicors = (Button) findViewById(R.id.buttonGETLicors);
        Button buttonGETRankingByLicorID = (Button) findViewById(R.id.buttonGETRankingByLicorID);

        buttonGETLicors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewLicors();
            }
        });

        buttonGETRankingByLicorID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewRanking();
            }
        });


    }

    private void initializeApp() {
        this.GetLicors();
    }


    private void GetLicors() {

        DataBaseAsyncTask<Results> myTask = new DataBaseAsyncTask<Results>(this) {
            @Override
            protected void onPreExecute() {
            }

            @Override
            protected Response<Results> doInBackground(ListView... ListViews) {
                Response<Results> response = null;
                Call<Results> call = this.getDataBaseActivity().apiManager.getLicors(
                        this.getDataBaseActivity().key_api.getAPIKey(), 100);
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
        for (Result movie : this.results.getResult())
            this.databaseStorage.onInsertLicors(movie.getId(), movie.getName(),
                    movie.getTags(), movie.getPriceInCents(), movie.getPrimaryCategory(),
                    movie.getOrigin(), movie.getPackageUnitVolumeInMilliliters(),
                    movie.getAlcoholContent(), movie.getProducerName(), movie.getImageThumbUrl(),
                    movie.getVarietal(), movie.getStyle());
    }

    private void viewRanking(){
        if (this.databaseStorage.count(ResultContract.licorsTable.TABLE_NAME) > 0) {
            ArrayList<ResultParce> licors = this.databaseStorage.getLicorsByCategory(CATEGORY);

            if (this.databaseStorage.count(RankingContract.rankingTable.TABLE_NAME) == 0)
            this.onInsertRanking(licors.get(0));

            ListView ratingsList = (ListView) findViewById(R.id.list);
            ArrayList<String> ratings = new ArrayList<>();
            RankingParce ranking = this.databaseStorage.getRankingID(licors.get(0).getLicorsId());

            ratings.add(ranking.toString());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    ratings);

            ratingsList.setAdapter(arrayAdapter);
        } else {
            Toast.makeText(getApplicationContext(), R.string.strError, Toast.LENGTH_SHORT).show();
        }
    }

    private void onInsertRanking(ResultParce movie) {
        this.databaseStorage.onInsertRanking(movie.getLicorsId(), 8);
    }
}