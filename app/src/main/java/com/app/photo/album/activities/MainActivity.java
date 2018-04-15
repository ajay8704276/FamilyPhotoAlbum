package com.app.photo.album.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.app.photo.album.R;
import com.app.photo.album.lifecycle.RequestCodes;
import com.app.photo.album.models.Photo;
import com.app.photo.album.repositories.RepositoryException;
import com.app.photo.album.repositories.RepositoryFactory;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure the action bar
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        // Hide the photo buttons if there is no camera
        PackageManager pm = getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Log.d(TAG, "Device does not have the camera");

            // Disable the AppBar version of the icon
            ImageButton cameraButton = findViewById(R.id.btn_camera);
            cameraButton.setVisibility(View.INVISIBLE);

            // Disable the FAB
            FloatingActionButton fabCamera = findViewById(R.id.fab_camera);
            fabCamera.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Event handler called when the camera icon is clicked
     * @param v the view that initiated this call
     */
    public void handleOnCameraIconClicked(View v) {
        Log.i(TAG, "handleOnCameraIconClicked");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent , RequestCodes.TAKE_PICTURE);
        }else {
            Log.e(TAG, "No Activity available to handle camera photo");
        }
    }

    /**
     * Callback to handle the response from startActivityForResult()
     * @param requestCode the ID of the request causing the response
     * @param resultCode the ID of the response
     * @param data the response data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RequestCodes.TAKE_PICTURE:
                try {
                    handleCameraPictureTaken(resultCode, data);
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    /**
     * Event handler called when the camera returns back to the app
     * @param resultCode the code for the response (RESULT_OK is good)
     * @param data the intent with the data block
     */
    private void handleCameraPictureTaken(int resultCode, Intent data) throws RepositoryException {
        if (resultCode != RESULT_OK) {
            Log.e(TAG, "Camera did not produce data - aborting");
        }

        Bundle extras = data.getExtras();
        Bitmap picture = (Bitmap) extras.get("data");

        // Do something with the picture
        try {
            Log.i(TAG, "Received image data from the camera");
            Photo newPhoto = new Photo();
            newPhoto.setPicture(picture);
            RepositoryFactory.getPhotosRepository().saveItem(newPhoto);
        } catch (RepositoryException err) {
            Log.e(TAG, "Cannot save new picture to repository", err);
        }
    }
}
