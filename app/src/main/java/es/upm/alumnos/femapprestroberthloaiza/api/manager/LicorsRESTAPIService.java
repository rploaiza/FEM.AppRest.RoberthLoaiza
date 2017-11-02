package es.upm.alumnos.femapprestroberthloaiza.api.manager;

import es.upm.alumnos.femapprestroberthloaiza.api.models.Results;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Usuario on 26/10/2017.
 */

interface LicorsRESTAPIService {

    // https://lcboapi.com/products/?q=search&access_key=api-key&per_page=100
    @GET("/products/")
    Call<Results> getLicors(@Query("q")String search,@Query("access_key") String APIKey, @Query("per_page") int per_page);
}
