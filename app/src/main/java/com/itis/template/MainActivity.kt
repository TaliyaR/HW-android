package com.itis.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.itis.template.fragment.CardFragment
import com.itis.template.fragment.HomeFragment
import com.itis.template.fragment.ListsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    navigateToFragment(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_list -> {
                    navigateToFragment(ListsFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.nav_cards -> {
                    navigateToFragment(CardFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun navigateToFragment(fragment: Fragment): Boolean {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.frame_layout, fragment)
                addToBackStack(null)
                commit()
            }
        }
        return true
    }
}
