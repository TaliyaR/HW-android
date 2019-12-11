package com.itis.template

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.itis.template.fragments.GoalsFragment
import com.itis.template.fragments.ReportFragment
import com.itis.template.fragments.TodayFragment
import com.itis.template.fragments.WeekFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpDrawerLayout()

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_today -> {
                    navigateToFragment(TodayFragment.newInstance())
                }
                R.id.nav_week -> {
                    navigateToFragment(WeekFragment.newInstance())
                }
                R.id.nav_report -> {
                    navigateToFragment(ReportFragment.newInstance())
                }
                R.id.nav_goals -> {
                    navigateToFragment(GoalsFragment.newInstance())
                }
                else -> {
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        when {
            drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawer(GravityCompat.START)
            else -> super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.drawer_view, menu)
        return true
    }

    private fun navigateToFragment(fragmentToNavigate: Fragment): Boolean {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragmentToNavigate)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            addToBackStack(null)
            commit()
        }
        return true
    }

    private fun setUpDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.menu_today, R.string.menu_goals)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }
}
