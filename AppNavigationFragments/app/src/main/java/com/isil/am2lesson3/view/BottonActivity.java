package com.isil.am2lesson3.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.isil.am2lesson3.R;
import com.isil.am2lesson3.view.fragments.BottomBarFragment;
import com.isil.am2lesson3.view.fragments.BoxFragment;
import com.isil.am2lesson3.view.listener.OnBoxListener;

public class BottonActivity extends ActionBarActivity  implements OnBoxListener{

    BoxFragment boxFragment;
    BottomBarFragment bottomBarFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botton);

        bottomBarFragment = (BottomBarFragment)getSupportFragmentManager().findFragmentById(R.id.bottonFragment);
        boxFragment = (BoxFragment)getSupportFragmentManager().findFragmentById(R.id.boxFragment);
        colorSelected(0);
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
    public void colorSelected(int pos) {
        boxFragment.selectedColor(pos);
    }
}
