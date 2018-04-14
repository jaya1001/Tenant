package com.example.android.tenant;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by User on 4/2/2018.
 */

public class tHomePage extends AppCompatActivity {

    private tenantAdapter mTenantAdapter;
    ArrayList<String> newEntries = new ArrayList<String>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.tenant_home);

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

        ListView entryListView = (ListView)findViewById(R.id.list);

        mTenantAdapter = new tenantAdapter(this, newEntries);

        TextView item = (TextView)findViewById(R.id.entry_id);

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                

            }
        });





    }
}
