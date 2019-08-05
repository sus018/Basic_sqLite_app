package com.example.susmita.curd_operation;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * A simple {@link Fragment} subclass.
 */
public class MyFrag extends Fragment {
    EditText et1,et2,et3;
    Button b;
    RecyclerView rv;
    MyAdapter ma;
    LinearLayoutManager manager;
    Cursor c;
    MainActivity mainActivity ;

    @Override
    public void onResume() {
        super.onResume();
        c = mainActivity.mydb.queryEmp();
        ma.notifyDataSetChanged();
    }

    public MyFrag() {
        // Required empty public constructor
    }

       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my, container, false);
        et1 = v.findViewById(R.id.editText1);
        et2 = v.findViewById(R.id.editText2);
        et3 = v.findViewById(R.id.editText3);
        b = v.findViewById(R.id.button2);
        rv = v.findViewById(R.id.r1);
        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        ma = new MyAdapter();
        rv.setAdapter(ma);
        mainActivity = (MainActivity) getActivity();
        rv.setLayoutManager(manager);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ename = et1.getText().toString().trim();
                String esal = et2.getText().toString();
                String edesig = et3.getText().toString().trim();
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.mydb.insertEmp(ename,esal,edesig);
                //READ TABLE VALUES [ROW] INTO CURSOR
                c = mainActivity.mydb.queryEmp();
                //ESTABLISH LINKS

                //tell to adapter
                ma.notifyDataSetChanged();
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et1.requestFocus();
            }
        });


        return v;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = getActivity().getLayoutInflater().inflate(R.layout.row,parent,false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                c.moveToPosition(position);
            int eno = c.getInt(0); //get _id
            String ename = c.getString(1);//get name
            int esal = c.getInt(2); //get salary
            String edesig = c.getString(3); //get designation
            holder.tv1.setText(""+eno);
            holder.tv2.setText(ename);
            holder.tv3.setText(""+esal);
            holder.tv4.setText(edesig);
        }

        @Override
        public int getItemCount() {
            if (c !=null )
                return c.getCount();
            else
                return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv1,tv2,tv3, tv4;
            ImageView i1,i2;

            public ViewHolder(View itemView) {
                super(itemView);
                // initialize all veriable of row.xml
                tv1 = itemView.findViewById(R.id.textView);
                tv2 = itemView.findViewById(R.id.textView2);
                tv3 = itemView.findViewById(R.id.textView3);
                tv4 = itemView.findViewById(R.id.textView4);
                i1 = itemView.findViewById(R.id.im1);
                i2 = itemView.findViewById(R.id.im2);


        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = getAdapterPosition();
                c.moveToPosition(pos);
                String ename = c.getString(1);
                String esal = c.getString(2);
                String edesig = c.getString(3);
                int _id = c.getInt(0);
                //OPEN CUSTOM DIALOG
                Update u= new Update();
                Bundle b = new Bundle();
                b.putString("ename",ename);
                b.putString("esal",esal);
                b.putString("edesig",edesig);
                b.putInt("_id",_id);
                u.setArguments(b);
                u.show(getActivity().getSupportFragmentManager(),null);


            }
        });
                i2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = getAdapterPosition();
                        c.moveToPosition(pos);
                        int _id = c.getInt(0);// now we got emp id  to delete
                        mainActivity.mydb.delete(_id);
                        c = mainActivity.mydb.queryEmp();
                        ma.notifyDataSetChanged();

                    }
                });
            }
        }
    }

}
