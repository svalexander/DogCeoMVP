package nyc.shannonalexander_navarro.dogceomvp.views;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nyc.shannonalexander_navarro.dogceomvp.R;

public class BreedViewHolder extends RecyclerView.ViewHolder {

    public static final String SELECTED_BREED = "selected";
    private TextView nameTV;
    private Context context;

    public BreedViewHolder(@NonNull View itemView) {
        super(itemView);

        nameTV = itemView.findViewById(R.id.breed_name_tv);
        context = itemView.getContext();
    }

    public void bind(final String breedname) {

        nameTV.setText(breedname);
        nameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SelectedBreedActivity.class);
                intent.putExtra(SELECTED_BREED, breedname);
                context.startActivity(intent);
            }
        });
    }
}
