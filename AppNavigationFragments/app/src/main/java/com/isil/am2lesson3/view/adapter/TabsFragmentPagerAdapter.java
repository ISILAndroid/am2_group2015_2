package com.isil.am2lesson3.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.isil.am2lesson3.view.fragments.AFragment;
import com.isil.am2lesson3.view.fragments.BFragment;
import com.isil.am2lesson3.view.fragments.CFragment;

public class TabsFragmentPagerAdapter extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = 3;

	public TabsFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int arg0) {
		Bundle data = new Bundle();
		switch(arg0){

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
		return PAGE_COUNT;
	}
	
}
