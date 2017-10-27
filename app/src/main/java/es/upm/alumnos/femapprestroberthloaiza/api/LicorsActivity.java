package es.upm.alumnos.femapprestroberthloaiza.api;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import es.upm.alumnos.femapprestroberthloaiza.R;
import es.upm.alumnos.femapprestroberthloaiza.api.models.Licors;
import retrofit2.Call;
import retrofit2.Response;

public class LicorsActivity extends Activity {

    private Key_Api key_api;
    private Licors licors;
    private APIManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.key_api = new Key_Api();
        this.apiManager = new APIManager();
        this.licors = new Licors();

        Button buttonToGetGenres = (Button) findViewById(R.id.GetLicors);
        buttonToGetGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLicors();
            }
        });
    }

    private void getLicors() {
        TextView textApiService = (TextView) findViewById(R.id.tvRespuesta);
        textApiService.setMovementMethod(new ScrollingMovementMethod());

        AsyncTaskAPI<Licors> myTask = new AsyncTaskAPI<Licors>(this) {
            @Override
            protected void onPreExecute() {
            }

            @Override
            protected Response<Licors> doInBackground(TextView... TextViews) {
                Response<Licors> response = null;
                Call<Licors> call = this.getLicorsActivity().apiManager.getLicors(this.getLicorsActivity().key_api.getAPIKey(),100);
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
                        Licors licors = (Licors) response.body();
                        this.getLicorsActivity().licors = licors;
                        this.getTextView().setText(licors.toString());
                    } else {
                        try {
                            JSONObject JSONErrorObject = new JSONObject(response.errorBody()
                                    .string());
                            Log.i("MiW", JSONErrorObject.getString("status_message"));
                            Toast.makeText(getApplicationContext(), " (" + JSONErrorObject.getString("status_code")
                                    + ") " + JSONErrorObject.getString(
                                    "status_message"), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.i("MiW", e.getMessage());
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.strError, Toast.LENGTH_LONG).show();
                }
            }
        };

        myTask.execute(textApiService);
    }
}
