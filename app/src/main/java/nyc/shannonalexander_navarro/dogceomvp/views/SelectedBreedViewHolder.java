package nyc.shannonalexander_navarro.dogceomvp.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import nyc.shannonalexander_navarro.dogceomvp.R;

public class SelectedBreedViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public SelectedBreedViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.breed_iv);
    }

    public void bind(String url) {
        Picasso.get().load(url).into(imageView);
    }
}
