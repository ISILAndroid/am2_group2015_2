package com.isil.mynotesormlite;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.isil.mynotesormlite.entity.NoteEntity;
import com.isil.mynotesormlite.storage.PreferencesHelper;
import com.isil.mynotesormlite.storage.db.CRUDOperations;
import com.isil.mynotesormlite.storage.db.MyDatabase;
import com.isil.mynotesormlite.storage.dborm.NoteRepository;
import com.isil.mynotesormlite.utils.StringUtils;
import com.isil.mynotesormlite.view.adapters.NoteAdapter;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private static final String TAG ="MainActivity" ;
    private static final int ACTION_ADD=1;
    private static final int ACTION_DETAIL=2;

    private TextView tviLogout,tviUser;
    private ListView lstNotes;
    private Button btnAddNote;
    private List<NoteEntity> lsNoteEntities;
    private CRUDOperations crudOperations;
    private NoteAdapter noteAdapter;
    private NoteRepository noteRepository;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //populate();
        initActionbar();
        init();
        //loadData();
    }

    /**
     * Custom actionbar
     * 1. ¿PORQUE AL CREAR UN CUSTOMACTIONBAR , NO OCUPA TODA LA PANTALLA?
     * 2. PODEMOS COLOCAR UN COLOR DE FONDO PARA QUE APARENTE OCUPAR TODA LA PANTALLA
     * 3. PODEMOS ASIGNAR UN ARCHO QUE OCUPE TODA LA PANTALLA
     */
    private void initActionbar() {
        actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange1)));
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view= layoutInflater.inflate(R.layout.layout_actionbar, null);
        actionBar.setCustomView(view);
        actionBar.setDisplayShowCustomEnabled(true);

        ViewGroup.LayoutParams lp = actionBar.getCustomView().getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        actionBar.getCustomView().setLayoutParams(lp);
        //android 5.0
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        //eventos
        view.findViewById(R.id.iviSearch).setOnClickListener(actionbarOnClickListener);
        view.findViewById(R.id.iviLogout).setOnClickListener(actionbarOnClickListener);
        TextView tviUser=(TextView)view.findViewById(R.id.tviUser);

        //user Info
        String username = PreferencesHelper.getUserSession(this);
        if(username!=null)
        {
            //tviUser.setText("Bienvenido "+ StringUtils.firstCapitalize(username));
            tviUser.setText("Bienvenido "+ StringUtils.firstCapitalize(username));
        }
    }


    private View.OnClickListener actionbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.iviSearch)
            {
                gotoSearch();
            }else if(v.getId()==R.id.iviLogout)
            {
                logout();
            }
        }
    };

    private void gotoSearch() {
        Intent intent= new Intent(this, SearchActivity.class);
        startActivity(intent);
    }


    private void loadData() {
        crudOperations= new CRUDOperations(new MyDatabase(this));
        lsNoteEntities= crudOperations.getAllNotes();
        noteAdapter= new NoteAdapter(this,lsNoteEntities);
        lstNotes.setAdapter(noteAdapter);

    }

    private void loadDataORM() {
        noteRepository= new NoteRepository(this);
        lsNoteEntities= noteRepository.getRecentAll();
        noteAdapter= new NoteAdapter(this,lsNoteEntities);
        lstNotes.setAdapter(noteAdapter);

        Log.v(TAG, "lsNoteEntities "+lsNoteEntities);

    }

    private void populate() {

        CRUDOperations crudOperations= new CRUDOperations(new MyDatabase(this));
        crudOperations.addNote(new NoteEntity("Mi Nota","Esta es un nota ",null));
        crudOperations.addNote(new NoteEntity("Segunda Nota","Esta es la segunds nota ",null));
        crudOperations.addNote(new NoteEntity("Tercera Nota","Esta es la tercera nota ",null));
        crudOperations.addNote(new NoteEntity("Cuarta Nota","Esta es la cuarta nota ",null));
        crudOperations.addNote(new NoteEntity("Quinta Nota","Esta es la quinta nota ",null));
        crudOperations.addNote(new NoteEntity("Sexta Nota","Esta es la sexta nota ",null));

        Log.v(TAG, "populate " + crudOperations.getAllNotes());
    }

    private void init() {
        tviLogout= (TextView)findViewById(R.id.tviLogout);
        tviUser= (TextView)findViewById(R.id.tviUser);
        lstNotes= (ListView)(findViewById(R.id.lstNotes));
        btnAddNote= (Button)(findViewById(R.id.btnAddNote));

        //events
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNote(ACTION_ADD, null);
            }
        });

        lstNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoteEntity noteEntity = (NoteEntity) adapterView.getAdapter().getItem(i);
                gotoNote(ACTION_DETAIL, noteEntity);
            }
        });

        /*tviLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });*/
    }

    private void gotoNote(int action, NoteEntity noteEntity) {
        Intent intent= new Intent(this,NoteActivity.class);

        switch (action)
        {
            case ACTION_ADD:
                    intent.putExtra("FRAGMENT",NoteActivity.ADD_NOTE);
                    startActivity(intent);
                break;
            case ACTION_DETAIL:
                intent.putExtra("FRAGMENT",NoteActivity.DETAIL_NOTE);
                intent.putExtra("NOTE", noteEntity);
                startActivity(intent);
                break;
        }
    }

    private void logout() {
        PreferencesHelper.signOut(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResumen");
        //loadData();
        loadDataORM();
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
