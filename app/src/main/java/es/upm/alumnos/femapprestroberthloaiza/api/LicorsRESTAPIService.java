package es.upm.alumnos.femapprestroberthloaiza.api;

import es.upm.alumnos.femapprestroberthloaiza.api.models.Licors;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Usuario on 26/10/2017.
 */

interface LicorsRESTAPIService {

    //https://lcboapi.com/products/?access_key=api-key&per_page=per_page
    @GET("/products/")
    Call<Licors> getLicors(@Query("access_key") String APIKey,
                           @Query("per_page") int per_page);
}
