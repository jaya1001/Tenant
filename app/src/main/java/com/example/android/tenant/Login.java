package com.example.android.tenant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by User on 3/8/2018.
 */

public class Login extends Fragment {

    private FirebaseAuth mAuth;
    private EditText mEmail;
    private EditText mPassword;
    private Context context;
    final ArrayList<String> landEmail = new ArrayList<String>();
    final ArrayList<String> tenantEmail = new ArrayList<String>();
    private String email;
    private String password;
    DatabaseReference rootRef ;

    private int check=-1;

    public Login(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login, container, false);

        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText) view.findViewById(R.id.email1);
        mPassword = (EditText) view.findViewById(R.id.username1);

        Button b2 = (Button)view.findViewById(R.id.button2);
        context = view.getContext();

        final Intent i1 = new Intent(context, tHomePage.class);
        final Intent i2 = new Intent(context, lHomePage.class);

        /////////////////////////////////////////////////////

        rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference landlordRef = rootRef.child("landlord");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String mail = ds.child("email").getValue(String.class);

                    landEmail.add(mail);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        landlordRef.addListenerForSingleValueEvent(eventListener);


        DatabaseReference tenantRef = rootRef.child("tenant");



        ValueEventListener eventListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String mail = ds.child("email").getValue(String.class);

                    tenantEmail.add(mail);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        tenantRef.addListenerForSingleValueEvent(eventListener1);

        /////////////////////////////////////////////////////

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                email = mEmail.getText().toString();
                password = mPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getActivity(), "Authentication successful 1",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();


                                    if(landEmail.contains(email)) {
                                        check = 1;
                                        String uid = getUserId();
                                    }

                                    else if(tenantEmail.contains(email)) {
                                        check = 2;
                                        String uid = getUserId();
                                    }

                                    if(user.isEmailVerified() && check==1)
                                    {
                                        i2.putExtra("id",email);
                                        startActivity(i2);
                                    }
                                    else if(user.isEmailVerified() && check == 2)
                                    {
                                        startActivity(i1);
                                    }
                                    else
                                    {
                                        Toast.makeText(getActivity(), "Authentication successful 2",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        return view;
    }

    public String getUserId()
    {
        String user ="" ;
        final String[] id = {""};

        if(check == 1)
        {
            user = "landlord";
        }
        else if(check == 2)
        {
            user = "tenant";
        }

        final Query[] query = {rootRef.child(user).orderByChild("email").equalTo(email)};
        query[0].addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        id[0] = issue.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return id[0];
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}