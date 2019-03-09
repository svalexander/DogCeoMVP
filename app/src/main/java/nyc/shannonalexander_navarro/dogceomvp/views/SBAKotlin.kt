package nyc.shannonalexander_navarro.dogceomvp.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import nyc.shannonalexander_navarro.dogceomvp.R

class SBAKotlin(private var imgUrl: List<String>?) : RecyclerView.Adapter<SelectedBreedViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SelectedBreedViewHolder {

        val root = LayoutInflater.from(viewGroup.context).inflate(R.layout.selected_breed_item, viewGroup, false)
        return SelectedBreedViewHolder(root)
    }

    override fun onBindViewHolder(selectedBreedViewHolder: SelectedBreedViewHolder, i: Int) {
        val url = imgUrl!![i]
        selectedBreedViewHolder.bind(url)
    }

    override fun getItemCount(): Int {
        return imgUrl!!.size
    }

    fun setData(urls: List<String>) {
        imgUrl = urls
        notifyDataSetChanged()
    }
}
