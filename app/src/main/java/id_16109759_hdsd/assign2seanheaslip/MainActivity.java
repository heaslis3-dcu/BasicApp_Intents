package id_16109759_hdsd.assign2seanheaslip;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int ACTIVITY_START_CAMERA =0;
    public static final String EXTRA_MESSAGE = "id_16109759_hdsd.assign2seanheaslip";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView_Display);
        textView.setText(message);
    }

    /**
     * MediaStore reference: Constants: ACTION_IMAGE_CAPTURE
     * https://developer.android.com/reference/android/provider/MediaStore.html
     * https://developer.android.com/reference/android/provider/MediaStore.html#ACTION_IMAGE_CAPTURE
     * @param view
     */
    public void captureImage(View view) {
        //Toast.makeText(this, "Camera button pressed", Toast.LENGTH_LONG).show();
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,"Recent");
        startActivityForResult(cameraIntent, ACTIVITY_START_CAMERA);
    }

    /**
     * Receive the Result: onActivityResult:
     * https://developer.android.com/training/basics/intents/result.html
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ACTIVITY_START_CAMERA && resultCode == RESULT_OK) {
            Toast.makeText(this, "Image taken", Toast.LENGTH_LONG).show();
        }
    }

    public void viewPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
    }

    //Explicit Intent
    public void openMocEmail(View arg0) {
        Intent intent = new Intent(MainActivity.this, MessageView.class);
        startActivity(intent);
    }
    /**
     * Reference for launching email application
     *https://stackoverflow.com/questions/3935009/how-to-open-gmail-compose-when-a-button-is-clicked-in-android-app
     *
     */
    public void launchEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        startActivity(emailIntent);
    }
}
