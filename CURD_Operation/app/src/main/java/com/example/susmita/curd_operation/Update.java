package com.example.susmita.curd_operation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class Update extends DialogFragment {
    EditText et1,et2,et3;
    Button bb1;
    Dialog d = null;

    public Update() {
        // Required empty public constructor
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_update,null);
        et1 = v.findViewById(R.id.e1);
        et2 = v.findViewById(R.id.e2);
        et3 = v.findViewById(R.id.e3);
        bb1 = v.findViewById(R.id.b1);

                Bundle b = getArguments();
                final int _id = b.getInt("_id");
                String ename = b.getString("ename");
                String esal = b.getString("esal");
                String edesig = b.getString("edesig");

                et1.setText(ename);
                et2.setText(esal);
                et3.setText(edesig);

        bb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            String letest_name = et1.getText().toString().trim();
                            String letest_sal = et2.getText().toString().trim();
                            String letest_desig = et3.getText().toString().trim();
                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.mydb.update(_id, letest_name, letest_sal, letest_desig);
                            Toast.makeText(mainActivity, "DONE...", Toast.LENGTH_SHORT).show();
                            //HOW TO REFRESH DATA AUTOMETICALLY
                            mainActivity.refreshFragment();
                        d.dismiss();
                    }
                });

        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
        ad.setView(v);//REQUESTIN TO LOAD OUR OWN XML OG DIALOG
        d = ad.create();
        return d;
    }
}
