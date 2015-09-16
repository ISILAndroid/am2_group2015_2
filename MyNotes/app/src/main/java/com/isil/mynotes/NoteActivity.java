package com.isil.mynotes;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.isil.mynotes.R;
import com.isil.mynotes.model.entity.NoteEntity;
import com.isil.mynotes.storage.db.CRUDOperations;
import com.isil.mynotes.storage.db.MyDatabase;
import com.isil.mynotes.view.dialogs.MyDialogFragment;
import com.isil.mynotes.view.dialogs.MyDialogListener;
import com.isil.mynotes.view.fragments.AddNoteFragment;
import com.isil.mynotes.view.fragments.DetailsFragment;
import com.isil.mynotes.view.listeners.OnNoteListener;

public class NoteActivity extends ActionBarActivity  implements OnNoteListener, MyDialogListener{

    public static final  int ADD_NOTE=100;
    public static final  int DETAIL_NOTE=101;
    public static final  int UPDATE_NOTE=102;
    private static final String TAG ="NoteActivity";

    private AddNoteFragment addNoteFragment= AddNoteFragment.newInstance(null,null);
    private DetailsFragment detailsFragment= DetailsFragment.newInstance(null,null);
    private int fragmentSelected= DETAIL_NOTE;
    private NoteEntity noteEntity;

    private CRUDOperations crudOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        validateExtras();

        crudOperations= new CRUDOperations(new MyDatabase(this));
        Bundle bundle= new Bundle();
        bundle.putSerializable("NOTE",noteEntity);
        changeFragment(fragmentSelected, bundle);
    }

    private void validateExtras() {
        if(getIntent().getExtras()!=null)
        {
            fragmentSelected= getIntent().getExtras().getInt("FRAGMENT",DETAIL_NOTE);
            noteEntity= (NoteEntity)getIntent().getExtras().getSerializable("NOTE");
        }
    }


    private  void changeFragment(int id,Bundle bundle)
    {
        Fragment fragment= null;
        switch (id)
        {
            case ADD_NOTE:
                fragment=addNoteFragment;
                break;


            case DETAIL_NOTE:
                fragment=detailsFragment;
                break;


            case UPDATE_NOTE:
                fragment=null;
                break;
        }

        if(fragment!=null)
        {
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
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
    public CRUDOperations getCrudOperations() {
        return crudOperations;
    }

    @Override
    public void deleteNote(NoteEntity noteEntity) {
        MyDialogFragment myDialogFragment =new MyDialogFragment();
        Bundle bundle= new Bundle();
        bundle.putString("TITLE","¿Deseas eliminar esta nota?");
        bundle.putInt("TYPE",100);
        myDialogFragment.setArguments(bundle);
        myDialogFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void onPositiveListener(Object object, int type) {
        Log.v(TAG, "dialog positive");
    }

    @Override
    public void onNegativeListener(Object object, int type) {
        Log.v(TAG, "dialog negative");
    }
}
