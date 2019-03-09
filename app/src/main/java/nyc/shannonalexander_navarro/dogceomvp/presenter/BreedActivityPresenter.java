package nyc.shannonalexander_navarro.dogceomvp.presenter;

import android.content.Intent;

public class BreedActivityPresenter {

    private BreedPresenterInterface breedPresenterInterface;

    public void onAttachPresenter(BreedPresenterInterface breedPresenterInterface) {
        this.breedPresenterInterface = breedPresenterInterface;
    }

    private void detachPresenter() {
        this.breedPresenterInterface = null;
    }

    public String getStringFromIntent(Intent intent){

        return "";
    }
}
