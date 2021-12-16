package com.sisco.pathuiclone

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton
import com.sisco.pathuiclone.adapter.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(HomeFragment())
        pagerAdapter.addFragment(ExploreFragment())
        pagerAdapter.addFragment(FriendsFragment())
        pagerAdapter.addFragment(MomentsFragment())

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.selector_menu_timeline)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.selector_menu_global)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.selector_menu_friends)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.selector_menu_profile)

    }
}