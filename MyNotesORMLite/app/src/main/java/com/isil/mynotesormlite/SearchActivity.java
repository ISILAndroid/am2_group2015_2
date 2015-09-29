package com.isil.mynotesormlite;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.isil.mynotesormlite.entity.NoteEntity;
import com.isil.mynotesormlite.storage.dborm.NoteRepository;
import com.isil.mynotesormlite.view.adapters.NoteAdapter;

import java.util.List;

public class SearchActivity extends ActionBarActivity {

    private static final String TAG ="SearchActivity" ;
    private ActionBar actionBar;
    private ListView lviNotes;

    private NoteAdapter noteAdapter;
    private NoteRepository noteRepository;
    private List<NoteEntity> lsNoteEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initActionbar();
        init();
        loadDataORM();
    }

    private void loadDataORM()
    {
        noteRepository= new NoteRepository(this);
        lsNoteEntities= noteRepository.getRecentAll();
        noteAdapter= new NoteAdapter(this,lsNoteEntities);
        lviNotes.setAdapter(noteAdapter);
        Log.v(TAG, "lsNoteEntities " + lsNoteEntities);
    }

    private void init() {
        lviNotes=(ListView)findViewById(R.id.lviNotes);
    }

    private void initActionbar() {
        actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange1)));
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view= layoutInflater.inflate(R.layout.layout_search_actionbar, null);
        actionBar.setCustomView(view);
        actionBar.setDisplayShowCustomEnabled(true);

        ViewGroup.LayoutParams lp = actionBar.getCustomView().getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        actionBar.getCustomView().setLayoutParams(lp);
        //android 5.0
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return false;
    }
}
