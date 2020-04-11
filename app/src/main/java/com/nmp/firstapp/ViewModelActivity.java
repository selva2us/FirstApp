package com.nmp.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ViewModelActivity extends MainActivity {
    ArrayList<DataModel> dataModels;
    private ListView listView;
    private ArrayAdapter adapter;
    private MyAppAdapter myAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        listView = (ListView) findViewById(R.id.etlistview);
        dataModels= new ArrayList<>();
    }

    public void onclick(View view) {
        String sql1 = "SELECT * FROM NMI_AUDIT;";
        try {
            PreparedStatement stmt = dbConnection(sql1);
            ResultSet rs = stmt.executeQuery();
            Context context = getApplicationContext();
            CharSequence text;
            int duration = Toast.LENGTH_LONG;
            while (rs.next() == Boolean.TRUE) {
                dataModels.add(new DataModel(rs.getString("CUSTOMER_MODEL"), rs.getString("CHK_TYPE")));
            }
            myAppAdapter = new MyAppAdapter(dataModels, ViewModelActivity.this);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(myAppAdapter);
            text = "data added";
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP | Gravity.LEFT, 10, 10);
            toast.show();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel= dataModels.get(position);
                try {
                    Toast.makeText(getApplicationContext(), "selected Item Name is " + dataModel.getModel(), Toast.LENGTH_LONG).show();
                    Intent checkSheetPage = new Intent(ViewModelActivity.this, CheckSheet.class);
                    checkSheetPage.putExtra("ModelValue", dataModel.getModel());
                    startActivity(checkSheetPage);
                }catch (Exception e){

                }
            }
        });
    }
}
