package com.example.android.tenant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.widget.Toast.*;

/**
 * Created by User on 4/2/2018.
 */

public class tHomePage extends AppCompatActivity {

    private tenantAdapter mTenantAdapter;
    ArrayList<String> newEntries = new ArrayList<String>();
    Context context;
    Intent i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenant_home);

        i = getIntent();

        context = getApplicationContext();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference newEntryRef = rootRef.child("newEntry");

        ValueEventListener eventListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String entryId = ds.getKey();
                    newEntries.add(entryId);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        newEntryRef.addListenerForSingleValueEvent(eventListener1);

        final ListView entryListView = (ListView)findViewById(R.id.list2);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mTenantAdapter = new tenantAdapter(context, newEntries);

                entryListView.setAdapter(mTenantAdapter);
                
                entryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                // Do something after 5s = 5000ms
            }
        }, 4000);

    }
}
