package com.example.ibrahimshaltout.test.screens.Profile;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.IndividualDataClass;
import com.example.ibrahimshaltout.test.screens.SettingsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity {

    Toolbar toolbarTop;
    TextView profile_university, profile_college, profile_spec, profile_grade, profile_skill, profile_interest,
    profile_mobile, profile_email, profile_name;
    ImageButton edit_profile_btn;
    CircleImageView profile_image;
    String profileUniversityName;
    String profileCollegeName;
    String profileSpec;
    String profileGrade;
    String profileSkill;
    String profileInterest;
    String profileMobile;
    String profileEmail;
    String main_profileName;
    ArrayList<String> skillList = new ArrayList<>();
    ArrayList<String> interestList = new ArrayList<>();
    String currentID;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);



        edit_profile_btn = (ImageButton) findViewById(R.id.edit_profile_btn);
        profile_university = findViewById(R.id.profile_university);
        profile_college = findViewById(R.id.profile_college);
        profile_spec = findViewById(R.id.profile_spec);
        profile_grade = findViewById(R.id.profile_grade);
        profile_skill = findViewById(R.id.profile_skill);
        profile_interest = findViewById(R.id.profile_interest);
        profile_mobile = findViewById(R.id.profile_mobile);
        profile_email = findViewById(R.id.profile_email);
        profile_name = findViewById(R.id.profile_name);


//       setProfileData();

    } // ending OnCreate






    private void setProfileData() {

        currentID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance().getReference();
        db.child("Users").child(currentID).child("universityName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {
                };
                profileUniversityName = dataSnapshot.getValue(t);
                profile_university.setText(profileUniversityName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        db.child("Users").child(currentID).child("collegeName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {
                };
                profileCollegeName = dataSnapshot.getValue(t);
                profile_college.setText(profileCollegeName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        db.child("Users").child(currentID).child("depSpecialization").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {
                };
                profileSpec = dataSnapshot.getValue(t);
                profile_spec.setText(profileSpec);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        db.child("Users").child(currentID).child("grade").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {
                };
                profileGrade = dataSnapshot.getValue(t);
                profile_grade.setText(profileGrade);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        db.child("Users").child(currentID).child("skillsList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fetchSkillData(dataSnapshot);
//                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {
//                };
//                profileSkill = dataSnapshot.getValue(t);
                if (profileSkill != null) {
                    profile_skill.setText(profileSkill);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        db.child("Users").child(currentID).child("interestsList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fetchInterestData(dataSnapshot);
                profile_interest.setText(profileInterest);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        db.child("Users").child(currentID).child("phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {
                };
                profileMobile = dataSnapshot.getValue(t);
                profile_mobile.setText(profileMobile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        db.child("Users").child(currentID).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {
                };
                profileEmail = dataSnapshot.getValue(t);
                profile_email.setText(profileEmail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        db.child("Users").child(currentID).child("fullName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {
                };
                main_profileName = dataSnapshot.getValue(t);
                profile_name.setText(main_profileName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void fetchSkillData(DataSnapshot dataSnapshot) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };
        ArrayList<String> yourStringArray = dataSnapshot.getValue(t);
        skillList.addAll(yourStringArray);
        profileSkill = dataSnapshot.getValue(t).toString();

    }

    private void fetchInterestData(DataSnapshot dataSnapshot) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };
        ArrayList<String> yourStringArray = dataSnapshot.getValue(t);
        interestList.addAll(yourStringArray);
        profileInterest = dataSnapshot.getValue(t).toString();

    }
}

