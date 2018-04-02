package com.example.android.tenant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.acl.Owner;

/**
 * Created by User on 3/31/2018.
 */

public class ownerProfile extends AppCompatActivity {

    private Button save;
    private EditText L_name;
    private EditText L_age;
    private EditText L_email;
    private EditText L_contact;
    private EditText L_occupation;
    private RadioGroup L_radioSex;
    private RadioButton L_sex;
    String name2,occupation2,email2,gender2;

    private FirebaseDatabase mFirebase;
    private DatabaseReference mOwnerReference;

    int age2;
    long contact2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        setContentView(R.layout.owner_main);

        save = (Button) findViewById(R.id.L_save);

        FirebaseApp.initializeApp(ownerProfile.this);
        mFirebase = FirebaseDatabase.getInstance();
        mOwnerReference = mFirebase.getReference().child("landlord");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                L_name = (EditText) findViewById(R.id.L_name);
                L_age = (EditText) findViewById(R.id.L_age);
                L_email = (EditText) findViewById(R.id.L_mail);
                L_contact = (EditText) findViewById(R.id.L_number);
                L_occupation = (EditText) findViewById(R.id.L_occu);
                L_radioSex = (RadioGroup) findViewById(R.id.radioSex);

                int selectedGender = L_radioSex.getCheckedRadioButtonId();
                L_sex = (RadioButton) findViewById(selectedGender);

                name2 = L_name.getText().toString();
                age2 = Integer.parseInt(L_age.getText().toString());
                email2 = L_email.getText().toString();
                contact2 = Long.parseLong(L_contact.getText().toString());
                occupation2 = L_occupation.getText().toString();
                gender2 = L_sex.getText().toString();

                landlordDetail newLandlord = new landlordDetail(name2,age2,occupation2,gender2,contact2,email2);

                mOwnerReference.push().setValue(newLandlord);
            }
        });
    }

}
