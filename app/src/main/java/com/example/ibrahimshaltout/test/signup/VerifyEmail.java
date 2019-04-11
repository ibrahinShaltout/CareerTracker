package com.example.ibrahimshaltout.test.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibrahimshaltout.test.MainActivity;
import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.IndividualDataClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyEmail extends AppCompatActivity {

    private Button btnResendEmail,btnSkipVerify,btnDoneVerify;
    private FirebaseAuth auth;
    private EditText Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        auth = FirebaseAuth.getInstance();
        IndividualDataClass individualDataClass =new IndividualDataClass();

        btnResendEmail = (Button) findViewById(R.id.Resend_Email_button);
        btnSkipVerify = (Button) findViewById(R.id.Skip_Email_button);
        btnDoneVerify=(Button) findViewById(R.id.Done_EmailisVerified_button);

        Email = (EditText) findViewById(R.id.emailverify);
        String a= auth.getCurrentUser().getEmail();
        Email.setText(a);

        Toast.makeText(VerifyEmail.this,a,Toast.LENGTH_LONG).show();

        btnDoneVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIfEmailVerified();            }
        });

        btnResendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationEmail();
            }
        });

        btnSkipVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(VerifyEmail.this, "Your Should verify your Account", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(VerifyEmail.this, YourLocation.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void checkIfEmailVerified()
    {
        recreate();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.isEmailVerified();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
//            finish();
            Toast.makeText(VerifyEmail.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(VerifyEmail.this, YourLocation.class);
//            startActivity(intent);
//            finish();
        }
        else
        {
            Toast.makeText(VerifyEmail.this, "Your Email is not verified", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendVerificationEmail()
    {



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent
                            Toast.makeText(VerifyEmail.this, "Your Email is sent", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(VerifyEmail.this,YourLocation.class);
//                            startActivity(intent);
//                            finish();
                        } else {
                            // email not sent, so display message and restart the activity or do whatever you wish to do
                            //restart this activity
                            Toast.makeText(VerifyEmail.this, "Your Email is sent", Toast.LENGTH_SHORT).show();
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }


}