package com.isil.mynotesormlite.view.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.isil.mynotesormlite.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emedinaa on 17/08/15.
 */
public class ColorDialogFragment extends android.support.v4.app.DialogFragment {
    /** The system calls this to get the DialogFragment's layout, regardless
     of whether it's being displayed as a dialog or an embedded fragment. */
    private List<View> viewList;

    MyDialogListener mListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.layout_color_dialog, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (MyDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        viewList= new ArrayList<>();
        viewList.add(getView().findViewById(R.id.btn1));
        viewList.add(getView().findViewById(R.id.btn2));
        viewList.add(getView().findViewById(R.id.btn3));
        viewList.add(getView().findViewById(R.id.btn4));
        viewList.add(getView().findViewById(R.id.btn5));
        viewList.add(getView().findViewById(R.id.btn6));
        viewList.add(getView().findViewById(R.id.btn7));
        viewList.add(getView().findViewById(R.id.btn8));

        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (mListener != null)
                    mListener.onPositiveListener(null, 100);
            }
        });

        int count =0;
        for (View view:viewList) {
            final  int pos =count;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    selectedColor(pos);
                }
            });
            count++;
        }
    }
    private void selectedColor(int pos)
    {
        //TODO arreglo de colores
        mListener.onPositiveListener(null, 100);
    }

}
