package com.example.android.tenant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 4/5/2018.
 */

public class tenantAdapter extends ArrayAdapter<String> {

    public tenantAdapter(@NonNull Context context, ArrayList<String> newEntries) {
        super(context, 0 , newEntries);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.all_homes,parent,false);
        }

        String entryId = getItem(position);

        TextView entryView = (TextView) listItemView.findViewById(R.id.entry_id);
        entryView.setText(entryId);

        return convertView;
    }
}
