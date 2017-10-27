package es.upm.alumnos.femapprestroberthloaiza.api;

import es.upm.alumnos.femapprestroberthloaiza.api.models.Licors;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Usuario on 26/10/2017.
 */

public class APIManager {
    private static final String API_BASE_URL = "https://lcboapi.com";

    private LicorsRESTAPIService apiService;

    public APIManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(LicorsRESTAPIService.class);
    }

    public Call<Licors> getLicors(String APIKey, int per_page) {
        return this.apiService.getLicors(APIKey, per_page);
    }
}
