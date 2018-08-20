package ru.ratanov.kinoman.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.ratanov.kinoman.R
import ru.ratanov.kinoman.managers.update.ForceUpdateChecker
import ru.ratanov.kinoman.managers.update.UpdateManager
import ru.ratanov.kinoman.model.TopFilm
import ru.ratanov.kinoman.view.adapters.TopAdapter
import java.net.URL

class MainActivity : AppCompatActivity(), ForceUpdateChecker.OnUpdateNeededListener {
    private val url = URL("http://80.211.0.214:8081/api/top")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gson = GsonBuilder().create()

        doAsync {
            val items = gson.fromJson<List<TopFilm>>(url.readText(), object : TypeToken<List<TopFilm>>() {}.type)
            uiThread {
                recycler_view.layoutManager = GridLayoutManager(this@MainActivity, 3)
                recycler_view.setHasFixedSize(true)
                recycler_view.adapter = TopAdapter(this@MainActivity, items)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        ForceUpdateChecker.with(this).onUpdateNeeded(this).check()
    }

    override fun onUpdateNeeded(version: String, releaseNote: String, updateUrl: String) {
        UpdateManager.showUpdateDialog(this, version, releaseNote, updateUrl)
    }

}
