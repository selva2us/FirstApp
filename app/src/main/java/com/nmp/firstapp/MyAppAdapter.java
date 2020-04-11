package com.nmp.firstapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class MyAppAdapter extends BaseAdapter {

public class ViewHolder {
    TextView textname;
    TextView textname1;
}

    public List<DataModel> modelname;
    public Context context;
    ArrayList<DataModel> arrayList;

    public MyAppAdapter(List<DataModel> apps, Context c0ntext) {
        this.modelname = apps;
        this.context = c0ntext;
        arrayList = new ArrayList<DataModel>();
        arrayList.addAll(modelname);
    }

    @Override
    public int getCount() {
        return modelname.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        MyAppAdapter.ViewHolder viewHolder = null;
        if (rowView == null) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflator.inflate(R.layout.list_contents, parent, false);
            viewHolder = new MyAppAdapter.ViewHolder();
            viewHolder.textname = (TextView) rowView.findViewById(R.id.textView1);
            viewHolder.textname1 = (TextView) rowView.findViewById(R.id.textView2);
            Button TEST = (Button) rowView.findViewById(R.id.test_btn);
            TEST.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainActivity = new Intent(context.getApplicationContext(), MainActivity.class);
                    context.startActivity(mainActivity);
                }
            });
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (MyAppAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.textname.setText(modelname.get(position).getModel() + "");
        viewHolder.textname1.setText(modelname.get(position).getType() + "");
        return rowView;
    }
}