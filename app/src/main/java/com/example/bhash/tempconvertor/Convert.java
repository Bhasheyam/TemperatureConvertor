package com.example.bhash.tempconvertor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import java.text.*;

import java.util.*;
import java.util.List;

public class Convert extends AppCompatActivity {

    private TextView t1;
    private TextView t2;
    private ListView t3;
    private RadioButton r1;
    private RadioButton r2;
    ArrayAdapter<String> adapter;
    ArrayList<String> History=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
       t1=(TextView)findViewById(R.id.ed1);
        t2=(TextView)findViewById(R.id.ed2);
        t3=(ListView)findViewById(R.id.List1);
        r1=(RadioButton)findViewById(R.id.rd1);
        r2=(RadioButton)findViewById(R.id.rd2);


        t1.setText("1.0");
        t2.setText("33.8");
    }
    public void rd1work(View boss){
     String temp=r1.getText().toString();
        Toast.makeText(this,"you selected"+temp,Toast.LENGTH_SHORT).show();
        t1.setText("1.0");
        t2.setText("33.8");

    }
    public void rd2work(View boss){
        String temp=r2.getText().toString();
        Toast.makeText(this,"you selected"+temp,Toast.LENGTH_SHORT).show();
        t1.setText("1.0");
        t2.setText("-17.2");
    }


    public void Convertit(View boss){
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        if(r1.isChecked())
        {
            double tem=ctof(Double.parseDouble(t1.getText().toString()));
            t2.setText(" "+numberFormat.format(tem));
            History.add(r1.getText().toString()+" : "+t1.getText().toString()+"->"+numberFormat.format(tem));
            Notification(History);
        }
        else
        {
            double tem=ftoc(Double.parseDouble(t1.getText().toString()));
            t2.setText(" "+numberFormat.format(tem));
            History.add(r2.getText().toString()+" : "+t1.getText().toString()+"->"+numberFormat.format(tem));
            Notification(History);
        }
    }
    public double ftoc(double d)
    {
        return (d-32.0)/1.8;
    }
    public double ctof(double d){return (d*1.8)+32.0; }
    public void Notification(ArrayList updated)
    {
        ArrayList<String> History1=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, History1);
        t3.setAdapter(adapter);
        ListIterator li = updated.listIterator(updated.size());
        while(li.hasPrevious()) {
            History1.add(li.previous().toString());
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("result", t2.getText().toString());
        outState.putStringArrayList("data",History);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        t2.setText(savedInstanceState.getString("result"));
        ArrayList<String> temp=savedInstanceState.getStringArrayList("data");
        History=temp;
        Notification(temp);


    }

}
