package com.example.android.tenant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

/**
 * Created by User on 3/8/2018.
 */

public class SignUp extends Fragment {

        private static final String TAG = "";
        private EditText mUsername;
        private EditText mEmail;
        private FirebaseAuth mAuth;
        private View view;
        private EditText mConfirmEmail;
        private RadioGroup radioPersonGroup;
        private RadioButton radioRentButton;
        private Context context;
        private FirebaseAuth.AuthStateListener mAuthListener;

        public SignUp(){

        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.sign_up, container, false);
        /* setContentView(R.layout.tenant_main); */
        mAuth = FirebaseAuth.getInstance();
        context = view.getContext();

        final Intent i1 = new Intent(context, tenantProfile.class);
        final Intent i2 = new Intent(context, landlordProfile.class);

        radioPersonGroup = (RadioGroup)view.findViewById(R.id.signGrp);

        mUsername = (EditText)view.findViewById(R.id.username);
        mEmail = (EditText)view.findViewById(R.id.email);
        mConfirmEmail = (EditText)view.findViewById(R.id.confirm_email);
        Button b1 = (Button)view.findViewById(R.id.signButton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view1) {

                int selectedId = radioPersonGroup.getCheckedRadioButtonId();
                radioRentButton = (RadioButton) view.findViewById(selectedId);

                if(mConfirmEmail.getText().toString().compareTo(mEmail.getText().toString()) == 0)
                {

                    mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(), mUsername.getText().toString())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull final Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    final FirebaseUser user = task.getResult().getUser();
                                    if (user != null) {
                                        if (!user.isEmailVerified())
                                        {
                                            sendVerificationEmail();

                                              if (radioRentButton.getText().toString().compareTo("Tenant") == 0) {
                                                    startActivity(i1);
                                                } else {
                                                    startActivity(i2);
                                                }
                                        }
                                        else {
                                            Log.d("User", "User does not exists");
                                        }
                                    }
                                else
                                    {
                                        Toast.makeText(getActivity(), "user is null", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {

                                    Toast.makeText(getActivity(), "SignUp Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }
                else
                {
                    Toast.makeText(getActivity(), "confirm the correct password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void sendVerificationEmail() {

        view.findViewById(R.id.signButton).setEnabled(false);
        final FirebaseUser user;
        user = mAuth.getCurrentUser();
        assert user != null;

        user.sendEmailVerification()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        view.findViewById(R.id.email).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(),
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}