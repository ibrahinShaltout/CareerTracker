package com.example.ibrahimshaltout.test.newsfeed.post;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.IndividualDataClass;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class AddNewPostActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;

    private FirebaseAuth auth;
    private EditText inputPost;
    private TextView Number_Of_likes;

    private Button btnAddPost;


    private int spinnerItemSelcected;

    private ImageView PostImageView;
    private Button addPostImage;
    String firstName;

    MaterialBetterSpinner materialBetterSpinner;
    private String[] shareForList = {"Anyone", "Same TrackDataClass Participants"};
    ArrayAdapter<String> stringArrayAdapter;

    MultiAutoCompleteTextView hashtage_Post;

    String[] hashtageArray = {"Dwight D. Eisenhower", "John F. Kennedy", "Lyndon B. Johnson", "Richard Nixon", "Gerald Ford", "Jimmy Carter",
            "Ronald Reagan", "George H. W. Bush", "Bill Clinton", "George W. Bush", "Barack Obama"};

    final PostDataClass postDataClass = new PostDataClass();

    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String individualId = currentFirebaseUser.getUid();

        final IndividualDataClass individualDataClass = new IndividualDataClass();

        btnAddPost = (Button) findViewById(R.id.write_new_post_Button_add);
        inputPost = (EditText) findViewById(R.id.write_new_post);
        PostImageView = (ImageView) findViewById(R.id.new_post_image);
        addPostImage = (Button) findViewById(R.id.new_post_image_add);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        final String key = database.getReference("Posts").getKey();


        stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, shareForList);
        materialBetterSpinner = (MaterialBetterSpinner) findViewById(R.id.share_For_List);
        materialBetterSpinner.setAdapter(stringArrayAdapter);


//        ArrayAdapter<String> skillsadapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, skillsArray);
//        skillsList = (MultiAutoCompleteTextView) findViewById(R.id.Skills_Talents);
//        skillsList.setThreshold(1);
//        skillsList.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
//        skillsList.setAdapter(skillsadapter);

        materialBetterSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemSelcected = position;
//                    if (spinnerItemSelcected == 0) {
//                        dfdf
//                    }
//                    }
//
//
            }
        });

        addPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();

            }
        });

        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String post = inputPost.getText().toString().trim();
                final String key = database.getReference("Posts").push().getKey();
//                final String likes=Number_Of_likes.getText().toString().trim();


                FirebaseDatabase.getInstance().getReference().child("Users").child(individualId)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                DateFormat dateFormat = new SimpleDateFormat(" h:mm aa  dd/MM/YYYY ");
                                Date date = new Date(System.currentTimeMillis());
                                String strDate = dateFormat.format(date);

                                IndividualDataClass individualDataClass = dataSnapshot.getValue(IndividualDataClass.class);
                                postDataClass.setPostName(individualDataClass.user_name);
                                postDataClass.setPostData(post);
                                postDataClass.setTimeAndDate(strDate);
                                postDataClass.setUser_ID(individualId);
                                postDataClass.setNumber_of_likes(0);
                                postDataClass.setPost_ID(key);


                                FirebaseDatabase.getInstance().getReference("Posts").child(key).setValue(postDataClass);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                uploadImage(key);

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
                PostImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(String postKey) {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String imageID = UUID.randomUUID().toString();
            final StorageReference ref = storageReference.child("images/" + imageID);
            postDataClass.setImageURL(filePath.toString());
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
                        Toast.makeText(AddNewPostActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        postDataClass.setImageURL(downloadUri.toString());

                    } else {
                        Toast.makeText(AddNewPostActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
                        // Handle failures
                        // ...
                    }
                }
            });
        }
    }
}
