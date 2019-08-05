package com.example.susmita.curd_operation;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MyDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new MyDatabase(this);
        mydb.open();
        FragmentManager mgr = getSupportFragmentManager();
        FragmentTransaction t = mgr.beginTransaction();
        MyFrag mf = new MyFrag();
        t.add(R.id.container,mf);
        t.commit();
    }
    public void onDestroy() {
        super.onDestroy();
        if (mydb != null)
            mydb.close();
    }

    public void refreshFragment() {
        FragmentManager mgr = getSupportFragmentManager();
        FragmentTransaction t = mgr.beginTransaction();
        MyFrag mf = new MyFrag();
        t.replace(R.id.container,mf);
        t.commit();
    }

}
