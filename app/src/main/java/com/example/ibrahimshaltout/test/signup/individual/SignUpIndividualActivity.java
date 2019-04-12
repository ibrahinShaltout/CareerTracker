package com.example.ibrahimshaltout.test.signup.individual;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ibrahimshaltout.test.MainActivity;
import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.IndividualDataClass;
import com.example.ibrahimshaltout.test.login.LoginActivity;
import com.example.ibrahimshaltout.test.login.ResetPasswordActivity;
import com.example.ibrahimshaltout.test.signup.VerifyEmail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignUpIndividualActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputFullName, inputPhoneNumber;
    private Button btnSignIn, btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseUser user;
    DatabaseReference userDatabaseReference;

    private String[] skillsList = {""};
    private String[] InterestsList = {""};
    private String[] experienceList = {""};
    private List inputSkillsList = new ArrayList<String>(Arrays.asList(skillsList));
    private List inputInterestsList = new ArrayList<String>(Arrays.asList(InterestsList));
    private List inputExperienceList = new ArrayList<String>(Arrays.asList(experienceList));
 @Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_individual);
     userDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users");


     auth = FirebaseAuth.getInstance();
     user = auth.getCurrentUser();

     btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputFullName = (EditText) findViewById(R.id.full_name);
        inputPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        inputPassword = (EditText) findViewById(R.id.password);

     progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullName = inputFullName.getText().toString().trim();
                final String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
              final   String verified="false";

                final String phoneNumber = inputPhoneNumber.getText().toString().trim();
//
//                if (TextUtils.isEmpty(fullName)) {
//                    inputFullName.setError("You need to enter first name ");
//                    return;
//                } else {
//                    inputFullName.setError(null);
//                }
//                if (TextUtils.isEmpty(email)) {
//                    inputEmail.setError("You need to enter an Email");
//                    return;
//                } else {
//                    inputEmail.setError(null);
//                }
//                if (TextUtils.isEmpty(password)) {
//                    inputPassword.setError("You need to enter a Password");
//                    return;
//                } else {
//                    inputPassword.setError(null);
//                }
//                if (TextUtils.isEmpty(phoneNumber)) {
//                    inputPhoneNumber.setError("You need to enter phone number");
//                    return;
//                } else {
//                    inputPhoneNumber.setError(null);
//                }
//                if (password.length() < 6) {
//                    inputPassword.setError("Password is less than 6 characters");
//                    return;
//                } else {
//                    inputPassword.setError(null);
//                }

                progressBar.setVisibility(View.VISIBLE);
 //create user
   auth.createUserWithEmailAndPassword(email, password)
           .addOnCompleteListener(SignUpIndividualActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpIndividualActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpIndividualActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    IndividualDataClass individualDataClass = new IndividualDataClass(fullName, email, phoneNumber,verified);
//                                    individualDataClass.setQualificationLevel("null");
//                                    individualDataClass.setSchoolName("null");
//                                    individualDataClass.setSchoolType("null");
//                                    individualDataClass.setUniversityName("null");
//                                    individualDataClass.setCollegeName("null");
//                                    individualDataClass.setDepSpecialization("null");
//                                    individualDataClass.setGrade("null");
//                                    individualDataClass.setDiplomaField("null");
//                                    individualDataClass.setMasterField("null");
//                                    individualDataClass.setDoctorateField("null");
//                                    individualDataClass.setInterestsList(inputInterestsList);
//                                    individualDataClass.setSkillsList(inputSkillsList);
//                                    individualDataClass.setExperience(inputExperienceList);
//                                    individualDataClass.setCompanyName("null");
//                                    individualDataClass.setJobTitle("null");
//                                    individualDataClass.setDepartment("null");
                                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance()
                                            .getCurrentUser().getUid())
                                            .setValue(individualDataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                user = auth.getCurrentUser();
                                                if (user != null) {
                                                    user.sendEmailVerification();}
                                                    Toast.makeText(SignUpIndividualActivity.this, "registration_success", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                    Intent intent = new Intent(SignUpIndividualActivity.this, VerifyEmail.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
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
                            // after email is sent just logout the user and finish this activity
//                            FirebaseAuth.getInstance().signOut();
//                                startActivity(new Intent(verifyEmail.this, LoginActivity.class));
                            finish();
                        } else {
                            // email not sent, so display message and restart the activity or do whatever you wish to do
                            //restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }
}
