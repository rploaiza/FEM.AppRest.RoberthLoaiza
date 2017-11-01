package es.upm.alumnos.femapprestroberthloaiza.provider;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import es.upm.alumnos.femapprestroberthloaiza.api.manager.APIManager;
import es.upm.alumnos.femapprestroberthloaiza.api.models.Result;
import es.upm.alumnos.femapprestroberthloaiza.api.models.Results;
import es.upm.alumnos.femapprestroberthloaiza.database.parcelable.ResultParce;
import es.upm.alumnos.femapprestroberthloaiza.database.parcelable.ApiKeyParce;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Usuario on 29/10/2017.
 */

public class APIController {

    private APIManager apiManager;

    public APIController() {
        this.apiManager = new APIManager();
    }

    public ArrayList<ResultParce> getLicors(ApiKeyParce apikey, String licorName) {

        ArrayList<ResultParce> licors = new ArrayList<>();
        licors = this.setLicors(apikey, licorName, licors, 100);
        return licors;
    }

    private ArrayList<ResultParce> setLicors(ApiKeyParce apikey, String licorName, ArrayList<ResultParce> result, int per_page) {

        Call<Results> getLicores = this.apiManager.getLicors(licorName, apikey.getApikey(), per_page);

        try {
            Response<Results> response = getLicores.execute();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    Results responseLicors = response.body();
                    for (Result licor : responseLicors.getResult()) {
                        ResultParce createAtrib = new ResultParce(licor.getId(), licor.getId(),
                                licor.getName(), licor.getTags(), licor.getPriceInCents(),
                                licor.getPrimaryCategory(), licor.getOrigin(), licor.getPackageUnitVolumeInMilliliters(),
                                licor.getAlcoholContent(), licor.getProducerName(), licor.getImageThumbUrl(),
                                licor.getVarietal(), licor.getStyle());
                        result.add(createAtrib);
                    }
                }
            }
        } catch (IOException e) {
            Log.i("MiW", e.getMessage());
        }
        return result;
    }
}
