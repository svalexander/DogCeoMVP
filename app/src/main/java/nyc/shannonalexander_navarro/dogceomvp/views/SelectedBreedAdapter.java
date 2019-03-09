package nyc.shannonalexander_navarro.dogceomvp.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.shannonalexander_navarro.dogceomvp.R;

public class SelectedBreedAdapter extends RecyclerView.Adapter<SelectedBreedViewHolder> {

    private List<String> imgUrl;

    public SelectedBreedAdapter(List<String> breedImgUrls) {
        imgUrl = breedImgUrls;
    }

    @NonNull
    @Override
    public SelectedBreedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selected_breed_item, viewGroup,false);
        return new SelectedBreedViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedBreedViewHolder selectedBreedViewHolder, int i) {
        String url = imgUrl.get(i);
        selectedBreedViewHolder.bind(url);
    }

    @Override
    public int getItemCount() {
        return imgUrl.size();
    }

    public void setData(List<String> urls){
        imgUrl = urls;
        notifyDataSetChanged();
    }
}
