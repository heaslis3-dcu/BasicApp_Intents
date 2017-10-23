package id_16109759_hdsd.assign2seanheaslip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
   // private ImageView mImageView;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 11/10/2017
     * https://stackoverflow.com/questions/16799818/open-camera-using-intent
     * 23/10/2017
     * https://developer.android.com/training/camera/photobasics.html
     * Section - "Take a photo with a camera app"
     */

    //

    public void onClickOpenCamera(View v) {

        //Intent Action Image capture
        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicIntent.resolveActivity(getPackageManager()) !=null) {
            //Create the File where the phot should go
            File photoFile = null;
            try{
                photoFile = createImageFile();
            } catch (IOException ex) {
                //Error occurred while creating the File

            }
            //Continue only if the File was successfully created
            if(photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this,
                        "id_16109759_hdsd.assign2seanheaslip", photoFile);
                takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePicIntent, REQUEST_IMAGE_CAPTURE);
            }
            }
    }
    
   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }

    } */

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
