package com.example.gridviewexample.GridView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gridviewexample.MainActivity;
import com.example.gridviewexample.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    // Assume it's known
    private static final int ROW_ITEMS = 3;
    DataGridAdapter adapter;
    ArrayList<String> itemsR = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final GridView grid = (GridView) findViewById(R.id.gridView);

        itemsR.add("1 , 5 , "+ calculate(1,5));
        itemsR.add("2 , 4 , "+ calculate(2,4));
        itemsR.add("3 , 6 , "+ calculate(3,6));


        adapter = new DataGridAdapter(itemsR, ROW_ITEMS,this);

        grid.setNumColumns(ROW_ITEMS);
        grid.setAdapter(adapter);



        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.layout_form_update, null);
                final EditText data1 = alertLayout.findViewById(R.id.data1);
                final EditText data2 = alertLayout.findViewById(R.id.data2);

                AlertDialog.Builder alert = new AlertDialog.Builder(GridViewActivity.this);
                alert.setTitle("Update Data");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);

                int colcount = ROW_ITEMS;
                final int rowno = position / colcount;

                Log.e("GridView Example", "Column Count : "+colcount);
                Log.e("GridView Example", "Row No : "+rowno);

                final String data = itemsR.get(rowno);

                String[] arr = data.split(",", 3);
                data1.setText(arr[0].trim());
                data2.setText(arr[1].trim());
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<String> data = new ArrayList<>();
                        String uuc = data1.getText().toString();
                        String ros = data2.getText().toString();
                        data.add(uuc);
                        data.add(ros);
                        data.add(String.valueOf(calculate(Integer.parseInt(uuc),Integer.parseInt(ros))));

                        String updatedata = data.toString().replaceAll("[\\s\\[\\]]", "");
                        Log.e("GridView Example","Updated List : "+ new Gson().toJson(itemsR));
                        Log.e("GridView Example","List row no to update : "+ rowno);

                        itemsR.set(rowno, updatedata);

                        Log.e("GridView Example","Updated List : "+ new Gson().toJson(itemsR));

                        //reload the adapter
                        adapter = new DataGridAdapter(itemsR, ROW_ITEMS, GridViewActivity.this);
                        grid.invalidateViews();
                        grid.setAdapter(adapter);


                    }
                });

                // Showing Alert Message
                alert.show();
            }
        });
    }

    public Double calculate(int p0, int p1) {
        return Double.parseDouble(String.valueOf(p0))+Double.parseDouble(String.valueOf(p1));
    }

}
