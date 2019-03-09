package nyc.shannonalexander_navarro.dogceomvp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import nyc.shannonalexander_navarro.dogceomvp.R;
import nyc.shannonalexander_navarro.dogceomvp.models.DogBreeds;
import nyc.shannonalexander_navarro.dogceomvp.presenter.BreedActivityPresenter;
import nyc.shannonalexander_navarro.dogceomvp.presenter.BreedPresenterInterface;

public class BreedActivity extends AppCompatActivity implements BreedPresenterInterface {

    private BreedActivityPresenter breedActivityPresenter;
    private BreedAdapter breedAdapter;
    private RecyclerView rv;
    private List<String> breedNames;
    private DogBreeds dogBreeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed);
        initPresenter();
        initRV();
    }

    private void initPresenter(){
        breedActivityPresenter = new BreedActivityPresenter();
    }

    private void initRV(){
        dogBreeds = new DogBreeds();
        breedNames = dogBreeds.addNames();
        rv = findViewById(R.id.breed_rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        breedAdapter = new BreedAdapter(breedNames);
        rv.setAdapter(breedAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        breedActivityPresenter.onAttachPresenter(this);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displayToast() {

    }

    @Override
    public void showResponse() {

    }

    @Override
    public void cacheResponse() {

    }
}
