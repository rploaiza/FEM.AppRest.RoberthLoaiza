package es.upm.alumnos.femapprestroberthloaiza.api;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import es.upm.alumnos.femapprestroberthloaiza.R;
import es.upm.alumnos.femapprestroberthloaiza.api.manager.APIManager;
import es.upm.alumnos.femapprestroberthloaiza.api.manager.Key_Api;
import es.upm.alumnos.femapprestroberthloaiza.api.models.Results;
import retrofit2.Call;
import retrofit2.Response;

public class LicorsActivity extends Activity {

    private Key_Api key_api;
    private Results licors;
    private APIManager apiManager;
    private EditText getLicor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.key_api = new Key_Api();
        this.apiManager = new APIManager();
        this.licors = new Results();

        getLicor = (EditText) findViewById(R.id.GetLicor);

        Button buttonToGetGenres = (Button) findViewById(R.id.GetLicors);
        buttonToGetGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLicors(getLicor.getText().toString());
            }
        });
    }

    private void getLicors(final String licor) {
        TextView textApiService = (TextView) findViewById(R.id.tvRespuesta);
        textApiService.setMovementMethod(new ScrollingMovementMethod());

        AsyncTaskAPI<Results> myTask = new AsyncTaskAPI<Results>(this) {
            @Override
            protected void onPreExecute() {
            }

            @Override
            protected Response<Results> doInBackground(TextView... TextViews) {

                Response<Results> response = null;
                Call<Results> call = this.getLicorsActivity().apiManager.getLicors(licor, this.getLicorsActivity().key_api.getAPIKey(), 100);
                this.setTextView(TextViews[0]);

                try {
                    response = call.execute();
                } catch (IOException e) {
                    Log.i("MiW", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Results licors = (Results) response.body();
                        this.getLicorsActivity().licors = licors;
                        this.getTextView().setText(licors.toString());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.strError, Toast.LENGTH_LONG).show();
                }
            }
        };

        myTask.execute(textApiService);
    }
}
