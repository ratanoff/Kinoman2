package ru.ratanov.kinoman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.GridLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {

    private val url = "http://kinozal.guru/top.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            val doc = Jsoup
                    .connect(url)
                    .cookies(Cookies.getCookies())
                    .get()

            val elements = doc.select("div.bx1").select("a")
                    .select("img").map {
                        val src = it.attr("src")
                        if (src.contains("poster")) {
                            "https://kinozal.guru$src"
                        } else{
                            src
                        }


            }

            println(elements.size)
            elements.forEach { println(it) }

            uiThread {
                recycler_view.layoutManager = GridLayoutManager(this@MainActivity, 2)
                recycler_view.setHasFixedSize(true)
                recycler_view.adapter = TopAdapter(this@MainActivity, elements)
            }
        }
    }
}
