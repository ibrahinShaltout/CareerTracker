package com.example.ibrahimshaltout.test.screens.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.IndividualDataClass;
import com.example.ibrahimshaltout.test.screens.newsfeed.post.AddNewPostActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;
    IndividualDataClass individualDataClass=new IndividualDataClass();
    StorageReference storageReference;
    DatabaseReference db;
    Toolbar toolbarTop;
    TextView profile_university, profile_college, profile_spec, profile_grade, profile_skill, profile_interest,
            profile_mobile, profile_email, profile_name;
    ImageButton profile_image_btn;
    CircleImageView profile_image;
    String profileUniversityName;
    String profileCollegeName;
    String profileSpec;
    String profileGrade;
    String profileSkill;
    String profileInterest;
    String profileMobile;
    String profileEmail;
    String profileName;
    ArrayList<String> skillList = new ArrayList<>();
    ArrayList<String> interestList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        toolbarTop = findViewById(R.id.user_profile_top_bar);
        setSupportActionBar(toolbarTop);
        toolbarTop.setTitle(" ");
        toolbarTop.setTitleMarginStart(80);
        toolbarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        profile_image_btn=(ImageButton) findViewById(R.id.profile_image_btn);
        profile_image=(CircleImageView)findViewById(R.id.profile_image);
        profile_university = findViewById(R.id.profile_university);
        profile_college = findViewById(R.id.profile_college);
        profile_spec = findViewById(R.id.profile_spec);
        profile_grade = findViewById(R.id.profile_grade);
        profile_skill = findViewById(R.id.profile_skill);
        profile_interest = findViewById(R.id.profile_interest);
        profile_mobile = findViewById(R.id.profile_mobile);
        profile_email = findViewById(R.id.profile_email);
        profile_name = findViewById(R.id.profile_name);

        final String currentID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        Glide.with(this /* context */)
                .load(individualDataClass.getImageURL())
                .into(profile_image);

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
                profile_skill.setText(profileSkill);
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
                profileName = dataSnapshot.getValue(t);
                profile_name.setText(profileName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        profile_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
                uploadImage(currentID);
            }
        });

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                profile_image.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage(String currentID) {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String imageID = UUID.randomUUID().toString();
            final StorageReference ref = storageReference.child("images/" + imageID);
            individualDataClass.setImageURL(filePath.toString());
            UploadTask uploadTask = ref.putFile(filePath);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    // Continue with the task to get the download URL
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Toast.makeText(UserProfileActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        individualDataClass.setImageURL(downloadUri.toString());

                    } else {
                        Toast.makeText(UserProfileActivity.this, "Failed " , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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

