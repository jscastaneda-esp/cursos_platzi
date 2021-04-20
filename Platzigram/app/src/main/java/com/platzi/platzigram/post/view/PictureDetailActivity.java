package com.platzi.platzigram.post.view;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.platzi.platzigram.PlatzigramApplication;
import com.platzi.platzigram.R;
import com.platzi.platzigram.model.Picture;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends AppCompatActivity {

    private static final String PHOTO_NAME = "JPEG_20190203_21-25-41_6157755883903133834.jpg";
    private static final String TAG = "PictureDetailActivity";

    private ImageView imgHeader;

    private PlatzigramApplication app;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Crashlytics.log("Initialize class " + TAG);

        setContentView(R.layout.activity_picture_detail);

        app = (PlatzigramApplication) getApplicationContext();
        storageReference = app.getStorageReference();

        imgHeader = (ImageView) findViewById(R.id.imgHeader);

        showToolbar(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade());
        }

        showData();

        //ImageView pictureDetail = (ImageView) findViewById(R.id.pictureDetail);
        TextView usernameDetail = (TextView) findViewById(R.id.usernameDetail);
        TextView likeNumberDetail = (TextView) findViewById(R.id.likeNumberDetail);

        Picture picture = (Picture) getIntent().getBundleExtra("picture").getSerializable("picture");
        //Picasso.get().load(picture.getPicture()).into(pictureDetail);
        usernameDetail.setText(picture.getUsername());
        likeNumberDetail.setText(String.valueOf(picture.getLikeNumber()));
    }

    private void showData() {
        storageReference.child("post-images").child(PHOTO_NAME).getDownloadUrl()
            .addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PictureDetailActivity.this, "Ocurrió un error al descargar la foto", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error Download Photo > " + e.getMessage(), e);
                    Crashlytics.logException(e);
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {

                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(imgHeader);
                }
            });
    }

    public void showToolbar(boolean upButton) {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbarDetail));
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
