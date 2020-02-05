package com.example.gridviewexample.GridView;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.gridviewexample.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DataGridAdapter extends BaseAdapter {

    ArrayList<String> mItems;
    final int mCount;
    public Context context;
    int ROW_ITEMS;

    public DataGridAdapter(final ArrayList<String> items,int ROW_ITEMS, Context context) throws ParseException {
        ROW_ITEMS = ROW_ITEMS;
        this.context = context;
        mCount = items.size() * ROW_ITEMS;
        mItems = new ArrayList<>(mCount);

        for (String item : items) {
            final String[] parts = item.split(",");

            for (String part : parts) {
                part = part.replaceAll(" ", "");
                mItems.add(part);
            }

            Log.e("Mallet", "List : " + new Gson().toJson(mItems));
        }
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            // view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            view = new TextView(context);
            view.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, 90));
            ((TextView) view).setGravity(Gravity.RIGHT);
            view.setBackgroundColor(context.getResources().getColor(R.color.whitegrey));
        }

        //  final TextView text = (TextView) view.findViewById(android.R.id.text1);

        ((TextView) view).setText(mItems.get(position));


        if ((position + 1) % 3 == 0) {
            Log.e("pos", position + "");
            if (isDouble(mItems.get(position))) {
                if (Double.parseDouble("2.0") < Double.parseDouble(mItems.get(position)) && Double.parseDouble("10.0") < Double.parseDouble(mItems.get(position))) {
                    view.setBackgroundColor(context.getResources().getColor(R.color.m_red));
                } else {
                    view.setBackgroundColor(context.getResources().getColor(R.color.whitegrey));
                }
            } else {
                view.setBackgroundColor(context.getResources().getColor(R.color.whitegrey));
            }
        } else {
            view.setBackgroundColor(context.getResources().getColor(R.color.whitegrey));
        }

        return view;
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


