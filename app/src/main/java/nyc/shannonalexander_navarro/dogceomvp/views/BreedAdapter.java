package nyc.shannonalexander_navarro.dogceomvp.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.shannonalexander_navarro.dogceomvp.R;

public class BreedAdapter extends RecyclerView.Adapter<BreedViewHolder> {

    List<String> dogBreedNames;

    public BreedAdapter(List<String> breedNames) {
        dogBreedNames = breedNames;
    }

    @NonNull
    @Override
    public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.breed_item,viewGroup,false);
        return new BreedViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedViewHolder breedViewHolder, int i) {
        String breedname = dogBreedNames.get(i);
        breedViewHolder.bind(breedname);
    }

    @Override
    public int getItemCount() {
        return dogBreedNames.size();
    }
}
