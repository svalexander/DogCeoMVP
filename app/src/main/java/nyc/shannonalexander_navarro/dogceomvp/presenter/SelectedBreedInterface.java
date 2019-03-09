package nyc.shannonalexander_navarro.dogceomvp.presenter;

import java.util.List;
import java.util.Set;

public interface SelectedBreedInterface {

    void showProgressBar();

    void hideProgressBar();

    void displayToast();

    void showResponse(List<String> dogImgUrls);

    void cacheResponse(Set<String> urls);

}
