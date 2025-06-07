package com.neurospherex.presentation.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.neurospherex.R
import com.neurospherex.data.remote.models.Course
import com.neurospherex.databinding.ActivityHomeBinding
import com.neurospherex.presentation.ui.adapter.CourseAdapter
import com.neurospherex.utils.extensions.initiateStatusBar

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var activityHomeBinding: ActivityHomeBinding
    private lateinit var courseAdapter: CourseAdapter
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        activityHomeBinding.lifecycleOwner = this
        initiateStatusBar()
        setupRecyclerView()
        setupNavigationDrawer()

        activityHomeBinding.userProfile.setOnClickListener {
            openDrawer()
        }

        activityHomeBinding.navView.getHeaderView(0).findViewById<View>(R.id.backButton).setOnClickListener {
            closeDrawer()
        }
    }

    private fun setupRecyclerView() {
        val courseList = listOf(
            Course(
                courseIcon = R.drawable.deep_learning_icon,
                courseName = "Deep Learning",
                courseDescription = "Deep Learning uses neural networks to analyze large datasets and solve complex tasks efficiently."
            ),
            Course(
                courseIcon = R.drawable.deep_learning_icon,
                courseName = "Machine Learning",
                courseDescription = "Machine Learning provides systems the ability to automatically learn and improve from experience without being explicitly programmed."
            ),
            Course(
                courseIcon = R.drawable.deep_learning_icon,
                courseName = "Real Spoof Detection",
                courseDescription = "Real Spoof Detection uses AI algorithms to distinguish between real faces and spoofing attacks like photos or masks."
            ),
            Course(
                courseIcon = R.drawable.deep_learning_icon,
                courseName = "Blur Image Recognition",
                courseDescription = "Real Spoof Detection uses AI algorithms to distinguish between real faces and spoofing attacks like photos or masks."
            )
        )

        courseAdapter = CourseAdapter(courseList)
        activityHomeBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = courseAdapter
        }
    }

    private fun setupNavigationDrawer() {
        drawerToggle = ActionBarDrawerToggle(this, activityHomeBinding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        activityHomeBinding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        activityHomeBinding.navView.setNavigationItemSelectedListener(this)
        activityHomeBinding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerOpened(drawerView: View) {}
            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerStateChanged(newState: Int) { drawerToggle.syncState() }
        })
    }

    private fun openDrawer() {
        if (!activityHomeBinding.drawerLayout.isDrawerOpen(activityHomeBinding.navView)) {
            activityHomeBinding.drawerLayout.openDrawer(activityHomeBinding.navView)
        }
    }

    private fun closeDrawer() {
        if (activityHomeBinding.drawerLayout.isDrawerOpen(activityHomeBinding.navView)) {
            activityHomeBinding.drawerLayout.closeDrawer(activityHomeBinding.navView)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.userProfile -> {}
            R.id.settings -> {}
            R.id.logout -> {}
        }

        activityHomeBinding.drawerLayout.closeDrawers()
        return true
    }
}