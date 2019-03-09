package nyc.shannonalexander_navarro.dogceomvp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nyc.shannonalexander_navarro.dogceomvp.R;
import nyc.shannonalexander_navarro.dogceomvp.presenter.SelectedBreedInterface;
import nyc.shannonalexander_navarro.dogceomvp.presenter.SelectedBreedPresenter;

public class SelectedBreedActivity extends AppCompatActivity implements SelectedBreedInterface {

    private static final String SHARED_PREF_KEY = "shared pref key";
    private static final String BUNDLE_KEY = "bundle key";
    private SelectedBreedPresenter selectedBreedPresenter;
    private ProgressBar progressBar;
    private SelectedBreedAdapter selectedBreedAdapter;
    private List<String> breedImgUrls = new ArrayList<>();
    private TextView breedTitleTV;
    private SharedPreferences sharedPreferences;
    private Boolean saved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_breed);
        initPresenter();
        initViews();
        setBreedTitleTV(getStringFromIntent());

        if (savedInstanceState != null) {
            saved = true;
            ArrayList<String> savedUrls = savedInstanceState.getStringArrayList(BUNDLE_KEY);
            initRV(savedUrls);
            Log.d("cached?", "caching");
        } else {
            initRV(breedImgUrls);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (sharedPreferences != null) {
            Set<String> savedSet = sharedPreferences.getStringSet(SHARED_PREF_KEY, null);
            if (savedSet != null) {
                ArrayList<String> savedUrls = new ArrayList<>(savedSet);
                outState.putStringArrayList(BUNDLE_KEY, savedUrls);
                saved = false;
            }
        }
        //2nd rotation, ie rotation back to vertical position crashes, list is null
    }

    @Override
    protected void onStart() {
        super.onStart();
        selectedBreedPresenter.attachPresenter(this);
        if (!saved) {
            selectedBreedPresenter.getResponse(getStringFromIntent());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        selectedBreedPresenter.detachPresenter();
    }

    private void initRV(List<String> urls) {
        RecyclerView selectedBreedRV = findViewById(R.id.rv_sba);
        selectedBreedRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        selectedBreedAdapter = new SelectedBreedAdapter(urls);
        selectedBreedRV.setAdapter(selectedBreedAdapter);
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar_sba);
        breedTitleTV = findViewById(R.id.breed_title);
    }

    private void initPresenter() {
        selectedBreedPresenter = new SelectedBreedPresenter();
    }

    private void setBreedTitleTV(String breedName) {
        breedTitleTV.setText(breedName);
    }

    private String getStringFromIntent() {
        Intent intent = getIntent();
        return intent.getStringExtra(BreedViewHolder.SELECTED_BREED);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayToast() {
        Toast.makeText(this, R.string.error_img, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResponse(List<String> dogImgUrls) {
        breedImgUrls = dogImgUrls;
        selectedBreedAdapter.setData(breedImgUrls);
    }


    @Override
    public void cacheResponse(Set<String> urls) {
            sharedPreferences = this.getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
            sharedPrefEditor.putStringSet(SHARED_PREF_KEY, urls);
            sharedPrefEditor.apply();
    }
}
