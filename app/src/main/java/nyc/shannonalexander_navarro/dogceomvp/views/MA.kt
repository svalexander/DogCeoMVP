package nyc.shannonalexander_navarro.dogceomvp.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import nyc.shannonalexander_navarro.dogceomvp.R
import nyc.shannonalexander_navarro.dogceomvp.presenter.SelectedBreedInterface
import nyc.shannonalexander_navarro.dogceomvp.presenter.SelectedBreedPresenter
import java.util.*


class MA : AppCompatActivity(), SelectedBreedInterface {
    private var selectedBreedPresenter: SelectedBreedPresenter? = null
    private var progressBar: ProgressBar? = null
    private var selectedBreedAdapter: SelectedBreedAdapter? = null
    private var breedImgUrls: List<String> = ArrayList()
    private var breedTitleTV: TextView? = null
    private var sharedPreferences: SharedPreferences? = null
    private var saved: Boolean? = false

    private val stringFromIntent: String
        get() {
            val intent = intent
            return intent.getStringExtra(BreedViewHolder.SELECTED_BREED)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_breed)
        initPresenter()
        initViews()
        setBreedTitleTV(stringFromIntent)

        if (savedInstanceState != null) {
            saved = true
            val savedUrls = savedInstanceState.getStringArrayList(BUNDLE_KEY)
            initRV(savedUrls)
            Log.d("cached?", "caching")
        } else {
            initRV(breedImgUrls)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (sharedPreferences != null) {
            val savedSet = sharedPreferences!!.getStringSet(SHARED_PREF_KEY, null)
            if (savedSet != null) {
                val savedUrls = ArrayList(savedSet)
                outState.putStringArrayList(BUNDLE_KEY, savedUrls)
                saved = false
            }
        }
        //2nd rotation, ie rotation back to vertical position crashes, list is null
    }

    override fun onStart() {
        super.onStart()
        selectedBreedPresenter!!.attachPresenter(this)
//        if ((!saved)!!) {
//            selectedBreedPresenter!!.getResponse(stringFromIntent)
//        }
    }

    override fun onStop() {
        super.onStop()
        selectedBreedPresenter!!.detachPresenter()
    }

    private fun initRV(urls: List<String>?) {
        val selectedBreedRV = findViewById<RecyclerView>(R.id.rv_sba)
        selectedBreedRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        selectedBreedAdapter = SelectedBreedAdapter(urls)
        selectedBreedRV.adapter = selectedBreedAdapter
    }

    private fun initViews() {
        progressBar = findViewById(R.id.progress_bar_sba)
        breedTitleTV = findViewById(R.id.breed_title)
    }

    private fun initPresenter() {
        selectedBreedPresenter = SelectedBreedPresenter()
    }

    private fun setBreedTitleTV(breedName: String) {
        breedTitleTV!!.text = breedName
    }

    override fun showProgressBar() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar!!.visibility = View.INVISIBLE
    }

    override fun displayToast() {
        Toast.makeText(this, R.string.error_img, Toast.LENGTH_SHORT).show()
    }

    override fun showResponse(dogImgUrls: List<String>) {
        breedImgUrls = dogImgUrls
        selectedBreedAdapter!!.setData(breedImgUrls)
    }


    override fun cacheResponse(urls: Set<String>) {
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE)
        val sharedPrefEditor = sharedPreferences!!.edit()
        sharedPrefEditor.putStringSet(SHARED_PREF_KEY, urls)
        sharedPrefEditor.apply()
    }

    companion object {

        private val SHARED_PREF_KEY = "shared pref key"
        private val BUNDLE_KEY = "bundle key"
    }


}
