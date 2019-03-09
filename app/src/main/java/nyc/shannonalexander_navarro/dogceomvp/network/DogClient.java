package nyc.shannonalexander_navarro.dogceomvp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogClient {

    private static final String BASE_URL = "https://dog.ceo/";
    private static DogService dogService;

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static DogService createService() {

        if (dogService == null) {
            dogService = retrofit.create(DogService.class);
        }
        return dogService;
    }
}
