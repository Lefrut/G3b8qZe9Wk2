package com.binet.G3b8qZe9Wk2

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.binet.G3b8qZe9Wk2.feature.home.HomeFragment
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<HomeFragment>(R.id.main_container)
        }
    }

    private fun setSupportActionBar() {
        val topBar = findViewById<MaterialToolbar>(R.id.top_bar)
        setSupportActionBar(topBar)
    }
}

fun Context.getAttrColor(
    @AttrRes
    attr: Int
): Int {
    val typedValue = TypedValue()
    val theme = theme
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}