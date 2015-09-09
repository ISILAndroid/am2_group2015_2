package com.isil.p01template;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.isil.p01template.view.OnFragmentListener;
import com.isil.p01template.view.fragments.AFragment;
import com.isil.p01template.view.fragments.BFragment;


public class HomeActivity extends ActionBarActivity implements OnFragmentListener {

    private static final String TAG = "HomeActivity";

    private AFragment fragmentA;
    private BFragment fragmentB;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        fragmentManager= getSupportFragmentManager();
        fragmentA= (AFragment)fragmentManager.findFragmentById(R.id.fragmentA);
        fragmentB= (BFragment)fragmentManager.findFragmentById(R.id.fragmentB);

        Log.v(TAG, "fragment A"+fragmentA+ " fragment B "+fragmentB);
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
    public void onSendMessage(String msg) {

    }
}
