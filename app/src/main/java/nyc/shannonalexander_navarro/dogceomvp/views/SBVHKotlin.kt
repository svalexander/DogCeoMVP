package nyc.shannonalexander_navarro.dogceomvp.views

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView

import com.squareup.picasso.Picasso

import nyc.shannonalexander_navarro.dogceomvp.R

class SBVHKotlin(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val imageView: ImageView

    init {
        imageView = itemView.findViewById(R.id.breed_iv)
    }

    fun bind(url: String) {
        Picasso.get().load(url).into(imageView)
    }


}
