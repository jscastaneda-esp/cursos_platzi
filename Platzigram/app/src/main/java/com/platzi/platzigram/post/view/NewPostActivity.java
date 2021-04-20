package com.platzi.platzigram.post.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.platzi.platzigram.PlatzigramApplication;
import com.platzi.platzigram.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class NewPostActivity extends AppCompatActivity {

    private static final String TAG = "NewPostActivity";

    private ImageView imgPhoto;
    private Button btnCreatePost;

    private String photoPath;
    private PlatzigramApplication app;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Crashlytics.log("Initialize class " + TAG);

        setContentView(R.layout.activity_new_post);

        app = (PlatzigramApplication) getApplicationContext();
        storageReference = app.getStorageReference();

        imgPhoto = (ImageView) findViewById(R.id.imgPhoto);
        btnCreatePost = (Button) findViewById(R.id.btnCreatePost);

        if (getIntent().getExtras() != null) {
            photoPath = getIntent().getStringExtra("PHOTO_PATH_TEMP");
            showPhoto();
        }

        btnCreatePost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                uploadPhoto();
            }
        });
    }

    private void showPhoto() {
        Picasso.get().load(photoPath).into(imgPhoto);
    }

    private void uploadPhoto() {
        imgPhoto.setDrawingCacheEnabled(true);
        imgPhoto.buildDrawingCache();

        Bitmap bitmap = imgPhoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] imgBytes = baos.toByteArray();
        String photoName = photoPath.substring(photoPath.lastIndexOf("/") + 1);

        StorageReference photoRef = storageReference.child("post-images").child(photoName);
        UploadTask uploadTask = photoRef.putBytes(imgBytes);
        uploadTask.addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Error Upload Photo > " + e.getMessage(), e);
                Crashlytics.logException(e);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri uri = taskSnapshot.getUploadSessionUri();
                Crashlytics.log(Log.WARN, TAG, "URL Photo > " + uri.toString());
                finish();
            }
        });
    }
}
