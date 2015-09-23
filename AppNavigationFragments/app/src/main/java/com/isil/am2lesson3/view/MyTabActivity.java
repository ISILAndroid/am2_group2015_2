package com.isil.am2lesson3.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.isil.am2lesson3.R;
import com.isil.am2lesson3.view.adapter.TabsFragmentPagerAdapter;
import com.isil.am2lesson3.view.listener.OnFragmentListener;

public class MyTabActivity extends ActionBarActivity implements OnFragmentListener
{
    private ViewPager mPager;
    private  ActionBar mActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tab);

        mActionbar = getSupportActionBar();

        mActionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mPager = (ViewPager) findViewById(R.id.pager);

        FragmentManager fm = getSupportFragmentManager();

        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mActionbar.setSelectedNavigationItem(position);
            }

        };

        mPager.setOnPageChangeListener(pageChangeListener);

        TabsFragmentPagerAdapter fragmentPagerAdapter = new TabsFragmentPagerAdapter(fm);

        mPager.setAdapter(fragmentPagerAdapter);

        mActionbar.setDisplayShowTitleEnabled(true);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                mPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
        };

        ActionBar.Tab tab = mActionbar.newTab()
                .setText("Tab1")
                .setTabListener(tabListener);

        mActionbar.addTab(tab);

        tab = mActionbar.newTab()
                .setText("Tab2")
                .setTabListener(tabListener);

        mActionbar.addTab(tab);

        tab = mActionbar.newTab()
                .setText("Tab3")
                .setTabListener(tabListener);

        mActionbar.addTab(tab);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void gotoAction(Object obj) {

    }
}
