package com.example.android.tenant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newEntry extends AppCompatActivity{

    private Button E_save;
    private EditText E_location;
    private EditText E_persons;
    private Spinner E_rent;
    private Spinner E_bhk;
    private RadioGroup E_radioPark;
    private RadioButton E_parking;

    String location,parking,rent,bhk;
    int persons;

    private FirebaseDatabase mFirebase;
    private DatabaseReference mEntryReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();

        setContentView(R.layout.new_entry);

        FirebaseApp.initializeApp(newEntry.this);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        /////////////////////////////////////////////
        final Bundle b = i.getExtras();

        /////////////////////////////////////////////////////

        mFirebase = FirebaseDatabase.getInstance();
        mEntryReference = mFirebase.getReference("newEntry");

        E_save = (Button) findViewById(R.id.e_save);
        Toast t = Toast.makeText(this,"in new entry",Toast.LENGTH_SHORT);
        E_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                E_location = (EditText) findViewById(R.id.e_location);
                E_persons = (EditText) findViewById(R.id.e_persons);
                E_radioPark = (RadioGroup) findViewById(R.id.parking);
                E_rent = (Spinner) findViewById(R.id.e_rent);
                E_bhk = (Spinner) findViewById(R.id.e_bhk);

                String x = "";
                if(b!=null){
                    x = (String) b.get("id");
                }

                int selectedParking = E_radioPark.getCheckedRadioButtonId();
                E_parking = (RadioButton) findViewById(selectedParking);

                location = E_location.getText().toString();
                persons = Integer.parseInt(E_persons.getText().toString());
                parking = E_parking.getText().toString();
                rent = String.valueOf(E_rent.getSelectedItem());
                bhk = String.valueOf(E_bhk.getSelectedItem());

                houseDetail newEntry = new houseDetail(location,persons,bhk,rent,parking,x);
                mEntryReference.push().setValue(newEntry);

            }
        });

    }
}