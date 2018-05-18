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

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by User on 4/2/2018.
 */

public class lHomePage extends AppCompatActivity {

    private landlordAdapter mLandlordAdapter;
    ArrayList<String> myEntries = new ArrayList<String>();
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landlord_home);

        context = getApplicationContext();

        Intent i = getIntent();
        final String email = i.getStringExtra("id");

        Toast.makeText(this, email,
                Toast.LENGTH_SHORT).show();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//
//        final Query[] query = {rootRef.child("newEntry").orderByChild("email").equalTo(email)};
//
//        query[0].addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
//                        // do something with the individual "issues"
//                        myEntries.add(issue.getKey());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        /////////////////////////////////////////////////

        DatabaseReference entry = rootRef.child("newEntry");

        ValueEventListener eventListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    if(ds.child("email").getValue(String.class).compareTo(email)==0) {

                        myEntries.add(ds.getKey());
                        //Toast.makeText(context, myEntries.get(0), Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        entry.addListenerForSingleValueEvent(eventListener1);

        /////////////////////////////////////////////////

        final ListView entryListView = (ListView)findViewById(R.id.list1);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(context, myEntries.get(0), Toast.LENGTH_SHORT).show();
                Toast.makeText(context,String.valueOf(myEntries.size()) , Toast.LENGTH_SHORT).show();

                Log.e("no of entries",String.valueOf(myEntries.size()));

                mLandlordAdapter = new landlordAdapter(context, myEntries);

                entryListView.setAdapter(mLandlordAdapter);

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


