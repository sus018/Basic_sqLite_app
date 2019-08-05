package com.example.susmita.curd_operation;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Del extends DialogFragment {


    public Del() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d = null;
        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
        ab.setTitle("Delete");
        ab.setIcon(R.mipmap.ic_launcher);
        ab.setMessage("You want to delete?");
        ab.setPositiveButton("ok",null);
        ab.setNegativeButton("cancel",null);
        d = ab.create();
        return  d;
    }
}
