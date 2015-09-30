package com.isil.mynotesormlite;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.isil.mynotesormlite.entity.NoteEntity;
import com.isil.mynotesormlite.storage.db.CRUDOperations;
import com.isil.mynotesormlite.storage.db.MyDatabase;
import com.isil.mynotesormlite.storage.dborm.NoteRepository;
import com.isil.mynotesormlite.utils.IntentUtils;
import com.isil.mynotesormlite.view.dialogs.ColorDialogFragment;
import com.isil.mynotesormlite.view.dialogs.MyDialogFragment;
import com.isil.mynotesormlite.view.dialogs.MyDialogListener;
import com.isil.mynotesormlite.view.fragments.AddNoteFragment;
import com.isil.mynotesormlite.view.fragments.DetailsFragment;
import com.isil.mynotesormlite.view.listeners.OnNoteListener;

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
    private NoteRepository noteRepository;

    private ActionBar actionBar;
    private ImageView iviPalette;
    private ImageView iviShare;
    private TextView tviTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        validateExtras();
        buildActionbar();

        crudOperations= new CRUDOperations(new MyDatabase(this));
        noteRepository= new NoteRepository(this);
        Bundle bundle= new Bundle();
        bundle.putSerializable("NOTE", noteEntity);

        changeFragment(fragmentSelected, bundle);
        events();
    }

    private void events() {
        iviPalette.setOnClickListener(actionbarOnClickListener);
        iviShare.setOnClickListener(actionbarOnClickListener);
    }
    private View.OnClickListener actionbarOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.iviPalette:
                        showDialogPalette();
                    break;
                case R.id.iviShare:
                        shareNote();
                    break;
            }
        }
    };


    private void shareNote()
    {
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append(noteEntity.getName());
        stringBuilder.append("\n");
        stringBuilder.append(noteEntity.getDescription());

        String txt = stringBuilder.toString();
        String subject= "Nota "+noteEntity.getName();

        Log.v(TAG, "txt " + txt);
        Intent shareIntent=IntentUtils.shareText(subject, txt);
        startActivity(shareIntent);

    }
    private void showDialogPalette() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ColorDialogFragment dialog = new ColorDialogFragment();
        dialog.show(fragmentManager, "color");
    }

    private void buildActionbar() {
        actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange1)));
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view= layoutInflater.inflate(R.layout.layout_detail_actionbar, null);
        actionBar.setCustomView(view);
        actionBar.setDisplayShowCustomEnabled(true);
        iviPalette=(ImageView)view.findViewById(R.id.iviPalette);
        iviShare=(ImageView)view.findViewById(R.id.iviShare);
        tviTitleBar=(TextView)view.findViewById(R.id.tviTitleBar);

        ViewGroup.LayoutParams lp = actionBar.getCustomView().getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        actionBar.getCustomView().setLayoutParams(lp);
        //android 5.0
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        actionbarEdit();

    }
    private void showButtonsActionbar(boolean b)
    {
        int visibility=(b)?(View.VISIBLE):(View.GONE);
        iviPalette.setVisibility(visibility);
        iviShare.setVisibility(visibility);
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
                actionbarAdded();
                break;

            case DETAIL_NOTE:
                fragment=detailsFragment;
                actionbarEdit();
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

    public void actionbarEdit()
    {
        tviTitleBar.setText("Nota");
       showButtonsActionbar(true);
    }
    public void actionbarAdded()
    {
        tviTitleBar.setText("Agregar Nota");
        showButtonsActionbar(false);
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
        bundle.putInt("TYPE", 100);
        bundle.putSerializable("ENTITY", noteEntity);
        myDialogFragment.setArguments(bundle);
        myDialogFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void editNote(NoteEntity noteEntity) {
        if(noteEntity!=null)
        noteRepository.update(noteEntity);

        finish();
    }

    @Override
    public void onPositiveListener(Object object, int type) {
        Log.v(TAG, "dialog positive ");
        NoteEntity noteEntity= (NoteEntity)(object);
        if(noteEntity!=null)
        {
            //crudOperations.deleteNote(noteEntity);
            noteRepository.delete(noteEntity);
            finish();
        }
    }

    @Override
    public void onNegativeListener(Object object, int type) {
        Log.v(TAG, "dialog negative");
    }
}
