package com.example.android.tenant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 4/14/2018.
 */

public class landlordAdapter extends ArrayAdapter<String> {

    landlordAdapter(@NonNull Context context, ArrayList<String> myEntries) {
        super(context, 0 , myEntries);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_my_home,parent,false);
        }

        String entryId = getItem(position);

        Toast.makeText(getContext(), entryId, Toast.LENGTH_SHORT).show();

        TextView entryView = (TextView) listItemView.findViewById(R.id.my_entry_id);
        entryView.setText(entryId);

        return listItemView;
    }
}