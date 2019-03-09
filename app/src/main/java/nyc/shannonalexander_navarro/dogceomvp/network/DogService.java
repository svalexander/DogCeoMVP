package nyc.shannonalexander_navarro.dogceomvp.network;

import nyc.shannonalexander_navarro.dogceomvp.models.Breed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogService {

    @GET("api/breed/{breed}/images")
    Call<Breed> getDogsByBreed(@Path("breed") String breed);
}
