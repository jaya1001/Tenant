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

/**
 * Created by User on 3/31/2018.
 */

public class tenantProfile extends AppCompatActivity {

    private Button b1;
    private EditText name;
    private EditText age;
    private EditText email;
    private EditText contact;
    private EditText occupation;
    private EditText marital_status;
    private RadioGroup radioSexGroup;
    private RadioGroup radioMaritalGroup;
    private RadioButton radioMaritalButton;
    private RadioButton radioSexButton;

    private FirebaseDatabase mFirebase;
    private DatabaseReference mTenantReference;


    String name1, email1, occupation1, marital_status1, gender1;
    int age1;
    long contact1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        setContentView(R.layout.tenant_main);

        FirebaseApp.initializeApp(tenantProfile.this);
        mFirebase = FirebaseDatabase.getInstance();
        mTenantReference = mFirebase.getReference().child("tenant");

        b1 = (Button)findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText)findViewById(R.id.name);
                age = (EditText)findViewById(R.id.age);
                email = (EditText)findViewById(R.id.email);
                contact = (EditText)findViewById(R.id.contact);
                occupation = (EditText)findViewById(R.id.occup);
                marital_status = (EditText)findViewById(R.id.marital);

                radioSexGroup = (RadioGroup)findViewById(R.id.radioGrp);
                radioMaritalGroup = (RadioGroup)findViewById(R.id.radioGrp2);

                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);

                int marriedId = radioMaritalGroup.getCheckedRadioButtonId();
                radioMaritalButton = (RadioButton) findViewById(marriedId);

                name1 = name.getText().toString();
                age1 = Integer.parseInt(age.getText().toString());
                email1 = email.getText().toString();
                occupation1 = occupation.getText().toString();
                marital_status1 = radioMaritalButton.getText().toString();
                contact1 = Long.parseLong(contact.getText().toString());
                gender1 = radioSexButton.getText().toString();

                tenantDetail newTenant = new tenantDetail(name1,age1,gender1,occupation1,contact1,email1,marital_status1);

                mTenantReference.push().setValue(newTenant);
            }
        });

    }
}
