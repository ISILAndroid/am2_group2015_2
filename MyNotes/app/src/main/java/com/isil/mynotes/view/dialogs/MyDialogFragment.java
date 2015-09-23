package com.isil.mynotes.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.isil.mynotes.model.entity.NoteEntity;

/**
 * Created by emedinaa on 15/09/15.
 */
public class MyDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String title = getArguments().getString("TITLE");
        final int type = getArguments().getInt("TYPE");
        final NoteEntity noteEntity = (NoteEntity)(getArguments().getSerializable("ENTITY"));

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((MyDialogListener)getActivity()).onPositiveListener(noteEntity,type);
                            }
                        }
                )
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((MyDialogListener)getActivity()).onNegativeListener(noteEntity,type);
                            }
                        }
                )
                .create();
    }
}
