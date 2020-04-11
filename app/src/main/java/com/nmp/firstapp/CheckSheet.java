package com.nmp.firstapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CheckSheet extends MainActivity {
    ArrayList<DataModel> dataModels;
    private ListView listView;
    private ArrayAdapter adapter;
    private MyAppAdapter myAppAdapter;
    final String nmiCategorySql = "SELECT * FROM NMI_CATEGORY WHERE MODEL_NAME=(?)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checksheet);
        listView = (ListView) findViewById(R.id.modelViewList);
        dataModels= new ArrayList<>();
        String tmpHolder = getIntent().getStringExtra("ModelValue");
        String categoryValue= "", modelValue = "";
        try {
            PreparedStatement stmt = dbConnection(nmiCategorySql);
            stmt.setString(1, tmpHolder);
            ResultSet rs = stmt.executeQuery();
            while (rs.next() == Boolean.TRUE) {
                dataModels.add(new DataModel(rs.getString("CATEGORY"), rs.getString("MODEL_NAME")));
            }
            myAppAdapter = new MyAppAdapter(dataModels, CheckSheet.this);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(myAppAdapter);

        }catch (Exception e){

        }
    }
}
