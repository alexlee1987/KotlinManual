package com.alexlee1987.kotlinmanual.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alexlee1987.kotlinmanual.R

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.sample_content_fragment, RecyclerViewFragment())
                commit()
            }
        }
    }
}
