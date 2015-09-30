package com.isil.am2lesson3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.isil.am2lesson3.view.fragments.AFragment;
import com.isil.am2lesson3.view.fragments.BFragment;
import com.isil.am2lesson3.view.fragments.CFragment;
import com.isil.am2lesson3.view.fragments.DFragment;
import com.isil.am2lesson3.view.listener.OnFragmentListener;


public class P02Activity extends ActionBarActivity  implements OnFragmentListener{

    private View btnA,btnB,btnC,btnD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p02);

        btnA= findViewById(R.id.btnA);
        btnB= findViewById(R.id.btnB);
        btnC= findViewById(R.id.btnC);
        btnD= findViewById(R.id.btnD);

        //events
        btnA.setOnClickListener(btnOnClickListener);
        btnB.setOnClickListener(btnOnClickListener);
        btnC.setOnClickListener(btnOnClickListener);
        btnD.setOnClickListener(btnOnClickListener);
    }
    private View.OnClickListener btnOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btnA:
                    //selectItem(0);
                    changeFragment(null, new AFragment(), "fragmentA");
                    break;
                case R.id.btnB:
                    //selectItem(1);
                    changeFragment(null, new BFragment(), "fragmentB");
                    break;
                case R.id.btnC:
                    //selectItem(2);
                    changeFragment(null, new CFragment(), "fragmentC");
                    break;
                case R.id.btnD:
                    //selectItem(3);
                    changeFragment(null, new DFragment(), "fragmentD");
                    break;
            }
        }
    };

    private void selectItem(int position) {

        AFragment aFragment= AFragment.newInstance(null,null);
        BFragment bFragment= BFragment.newInstance(null, null);
        CFragment cFragment= CFragment.newInstance(null, null);
        DFragment dFragment= DFragment.newInstance(null, null);
        //changeFragment(null,myFragment ,"myfragment");
        switch (position)
        {
            case 0:
                changeFragment(null,aFragment ,"aFragment");
                break;
            case 1:
                changeFragment(null,bFragment ,"bFragment");
                break;
            case 2:
                changeFragment(null,cFragment ,"cFragment");
                break;

            case 3:
                changeFragment(null,dFragment ,"dFragment");
                break;
        }
    }

    private void changeFragment(Bundle args, Fragment fragment, String tag)
    {
        if (args != null) fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment, tag).commit();
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
