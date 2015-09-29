package com.isil.mynotesormlite;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.isil.mynotesormlite.entity.NoteEntity;
import com.isil.mynotesormlite.storage.dborm.NoteRepository;
import com.isil.mynotesormlite.view.adapters.NoteAdapter;

import java.util.List;

public class SearchActivity extends ActionBarActivity {

    private static final String TAG ="SearchActivity" ;
    private static final int ACTION_DETAIL=2;

    private ActionBar actionBar;
    private ListView lviNotes;
    private EditText eteSearch;

    private NoteAdapter noteAdapter;
    private NoteRepository noteRepository;
    private List<NoteEntity> lsNoteEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        loadDataORM();
    }

    private void init() {
        buildActionbar();
        ui();
        events();
    }

    private void events() {
        eteSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (noteAdapter == null) return;
                if (count < before) {
                    // We're deleting char so we need to reset the adapter data
                    noteAdapter.resetData();
                }

                noteAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        lviNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoteEntity noteEntity = (NoteEntity) adapterView.getAdapter().getItem(i);
                gotoNote(ACTION_DETAIL, noteEntity);
            }
        });
    }

    private void gotoNote(int action, NoteEntity noteEntity) {
        Intent intent= new Intent(this,NoteActivity.class);
        switch (action)
        {
            case ACTION_DETAIL:
                intent.putExtra("FRAGMENT",NoteActivity.DETAIL_NOTE);
                intent.putExtra("NOTE", noteEntity);
                startActivity(intent);
                break;
        }
    }

    private void loadDataORM()
    {
        noteRepository= new NoteRepository(this);
        lsNoteEntities= noteRepository.getRecentAll();
        noteAdapter= new NoteAdapter(this,lsNoteEntities);
        lviNotes.setAdapter(noteAdapter);
        Log.v(TAG, "lsNoteEntities " + lsNoteEntities);
    }

    private void ui() {
        lviNotes=(ListView)findViewById(R.id.lviNotes);
    }

    private void buildActionbar() {
        actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange1)));
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view= layoutInflater.inflate(R.layout.layout_search_actionbar, null);
        actionBar.setCustomView(view);
        actionBar.setDisplayShowCustomEnabled(true);
        eteSearch=(EditText)view.findViewById(R.id.eteSearch);

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
