package com.isil.am2lesson3.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.isil.am2lesson3.R;
import com.isil.am2lesson3.view.fragments.AFragment;
import com.isil.am2lesson3.view.fragments.BFragment;
import com.isil.am2lesson3.view.fragments.CFragment;
import com.isil.am2lesson3.view.listener.OnFragmentListener;
import com.isil.am2lesson3.view.transforms.DepthPageTransformer;
import com.isil.am2lesson3.view.transforms.ZoomOutPageTransformer;

public class MyViewPagerActivity extends ActionBarActivity implements OnFragmentListener {

    private static final int NUM_PAGES = 3;

    private ViewPager mPager;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_pager);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //mPager.setPageTransformer(true, new DepthPageTransformer());
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return false;
    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void gotoAction(Object obj) {

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            Bundle data = new Bundle();
            switch(position){

                case 0:
                    AFragment fragment1 =AFragment.newInstance(null,null);
                    return fragment1;

                case 1:
                    BFragment fragment2 =BFragment.newInstance(null,null);
                    return fragment2;
                case 2:
                    CFragment fragment3 =CFragment.newInstance(null,null);
                    return fragment3;
            }

            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
