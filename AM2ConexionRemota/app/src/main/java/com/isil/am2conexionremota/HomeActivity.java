package com.isil.am2conexionremota;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.isil.am2conexionremota.entity.SpeakerEntity;
import com.isil.am2conexionremota.user.SpeakerAddFragment;
import com.isil.am2conexionremota.user.SpeakerFragment;
import com.isil.am2conexionremota.user.SpeakerListFragment;


public class HomeActivity extends ActionBarActivity implements  OnFragmentInteractionListener{


    private SpeakerListFragment fragment;
    private SpeakerAddFragment fragmentAdd;
    private SpeakerFragment speakerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(savedInstanceState==null)
        {
            fragment= new SpeakerListFragment();
            changeFragment(fragment);
        }

    }

    private void changeFragment(Fragment f)
    {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.container, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void changeFragment(Fragment f,Bundle bundle)
    {
        f.setArguments(bundle);
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.container, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
    public void gotoAddSpeaker() {
        fragmentAdd= new SpeakerAddFragment();
        changeFragment(fragmentAdd);
    }

    @Override
    public void gotoListSpeaker() {
        fragment= new SpeakerListFragment();
        changeFragment(fragment);
    }

    @Override
    public void gotoSpeakerDetails(SpeakerEntity speakerEntity) {
        speakerFragment= new SpeakerFragment();
        Bundle bundle= new Bundle();
        bundle.putSerializable("ENTITY",speakerEntity);
        changeFragment(speakerFragment,bundle);
    }
}
