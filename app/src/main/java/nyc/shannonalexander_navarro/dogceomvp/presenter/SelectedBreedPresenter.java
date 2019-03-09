package nyc.shannonalexander_navarro.dogceomvp.presenter;

import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nyc.shannonalexander_navarro.dogceomvp.models.Breed;
import nyc.shannonalexander_navarro.dogceomvp.network.DogClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedBreedPresenter {

    private SelectedBreedInterface selectedBreedInterface;

    public void attachPresenter(SelectedBreedInterface selectedBreedInterface){
        this.selectedBreedInterface = selectedBreedInterface;
    }

    public void detachPresenter(){
        selectedBreedInterface = null;
    }

    public void getResponse(String name){
        selectedBreedInterface.showProgressBar();

        Call<Breed> call = DogClient.createService().getDogsByBreed(name);
        call.enqueue(new Callback<Breed>() {
            @Override
            public void onResponse(Call<Breed> call, Response<Breed> response) {
               selectedBreedInterface.hideProgressBar();
                if(response.isSuccessful()){
                    Breed breed = response.body();
                    List<String> breedList = breed.getMessage();
                    selectedBreedInterface.showResponse(breedList);
                    selectedBreedInterface.cacheResponse(convertListToSet(breedList));
                    Log.d("img?", breedList.get(0));
                } else {
                    selectedBreedInterface.displayToast();
                }
            }

            @Override
            public void onFailure(Call<Breed> call, Throwable t) {
                selectedBreedInterface.displayToast();
            }
        });
    }

    private Set<String> convertListToSet(List<String> input){
        Set<String> strSet = new HashSet<>(input);
        return strSet;
    }

}
