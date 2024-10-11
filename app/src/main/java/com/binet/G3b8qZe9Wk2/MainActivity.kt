package com.binet.G3b8qZe9Wk2

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.annotation.AttrRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.binet.G3b8qZe9Wk2.feature.home.HomeFragment
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnBackPressedCallback()
        setSupportActionBar()
        supportFragmentManager.commit {
            add<HomeFragment>(R.id.main_container)
        }
    }

    private fun setSupportActionBar() {
        val topBar = findViewById<MaterialToolbar>(R.id.top_bar)
        setSupportActionBar(topBar)
        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        val component = ComponentName(this, MainActivity::class.java)
        val searchableInfo = searchManager.getSearchableInfo(component)
        val submitButton = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_go_btn)
        submitButton.setImageResource(R.drawable.white_search_24)
        searchView.setSearchableInfo(searchableInfo)
        searchView.inputType = InputType.TYPE_CLASS_TEXT
        searchView.isIconified = false
        searchView.isSubmitButtonEnabled = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navigateBack()
            }

            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setOnBackPressedCallback() {
        onBackPressedDispatcher.addCallback { navigateBack() }
    }

    private fun navigateBack() {
        if (supportFragmentManager.backStackEntryCount + supportFragmentManager.fragments.size <= 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}

val Fragment.supportActionBar get() = (requireActivity() as? AppCompatActivity)?.supportActionBar


fun Context.getAttrColor(
    @AttrRes
    attr: Int
): Int {
    val typedValue = TypedValue()
    val theme = theme
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}