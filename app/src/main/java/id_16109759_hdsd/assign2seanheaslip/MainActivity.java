package id_16109759_hdsd.assign2seanheaslip;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int ACTIVITY_START_CAMERA = 0;
    public static final String EXTRA_MESSAGE = "id_16109759_hdsd.assign2seanheaslip";
    Button btn;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Section used to disable and enable 'Send' button on Main Activity
        btn = (Button) findViewById(R.id.button_Send);
        btn.setEnabled(false);

        //Declare and initialise Message details 'To' and 'Subject',
        String emailAdd = getIntent().getStringExtra("emailTo");
        String subjectAdd = getIntent().getStringExtra("emailSubject");

        /**
         * Tried to use if statement to determine if the two strings are null, however,
         * ran into some trouble with this, checked the length of the null value and it is '0',
         * And could not figure out why using '||' (OR) / '&&' (AND) would still result
         */

        //TODO - check is a Try/Catch method to throw IOEXCEPTION will work here as
        if (emailAdd != null && subjectAdd != null) {
            TextView inputReturnedData = (TextView) findViewById(R.id.textView_Display);
            inputReturnedData.setText(
                    "To: " + emailAdd
                            + "\n"
                            + "Subject: " + subjectAdd);
            btn.setEnabled(true);
        } else {
            Toast.makeText(this, "Send Button Only enabled when both To & Subject is populated", Toast.LENGTH_LONG).show();
            btn.setEnabled(false);
        }


        /* This section Replaced 1st November 2017 21:48, with reworked intent to
        *also disable send button on main activity,
        *
        *
        //Get the Intent that started this activity and extract the string
        Intent returnDataIntent = getIntent();
        String emailAdd = returnDataIntent.getStringExtra("emailTo");
        String subjectAdd = returnDataIntent.getStringExtra("emailSubject");
        if(emailAdd != null && subjectAdd != null)
        {
            TextView inputReturnedData = (TextView) findViewById(R.id.textView_Display);
            inputReturnedData.setText(
                    "To: " + emailAdd
                            + "\n"
                            +"Subject: " + subjectAdd);
        }
        else {
            Toast.makeText(this, "Send Button Only enabled when To & Subject is populated", Toast.LENGTH_LONG).show();
            btn = (Button) findViewById(R.id.button_Send);
            btn.setEnabled(false);
        }

        */
/* Turned off 01/11/2017
        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        btn = (Button) findViewById(R.id.button_Send);
        if(message== null){
    btn.setEnabled(false);
        } else {
            btn.setEnabled(true);
            //Capture the layout's TextView and set the string as its text
            TextView textView = (TextView) findViewById(R.id.textView_Display);
            textView.setText(message);
        }
        */

        //Capture the layout's TextView and set the string as its text
//        TextView textView = (TextView) findViewById(R.id.textView_Display);
//        textView.setText(message);

    }

    /**
     * MediaStore reference: Constants: ACTION_IMAGE_CAPTURE
     * https://developer.android.com/reference/android/provider/MediaStore.html
     * https://developer.android.com/reference/android/provider/MediaStore.html#ACTION_IMAGE_CAPTURE
     *
     * @param view
     */
    public void captureImage(View view) {
        //Toast.makeText(this, "Camera button pressed", Toast.LENGTH_LONG).show();
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, "Recent");
        startActivityForResult(cameraIntent, ACTIVITY_START_CAMERA);
    }

    /**
     * Receive the Result: onActivityResult:
     * https://developer.android.com/training/basics/intents/result.html
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_START_CAMERA && resultCode == RESULT_OK) {
            Toast.makeText(this, "Image taken", Toast.LENGTH_LONG).show();
        }
    }

    public void viewPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    //Explicit Intent
    public void openMocEmail(View arg0) {
        Intent intent = new Intent(MainActivity.this, MessageView.class);
        startActivity(intent);
    }

    /**
     * Reference for launching email application
     * https://stackoverflow.com/questions/3935009/how-to-open-gmail-compose-when-a-button-is-clicked-in-android-app
     * <p>
     * Reference using putExtra(Intent.EXTRA_EMAIL...etc:
     * https://stackoverflow.com/questions/8701634/send-email-intent
     */
    public void launchEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        String emailAdd = getIntent().getStringExtra("emailTo");
        String subjectAdd = getIntent().getStringExtra("emailSubject");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAdd});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectAdd);
        startActivity(emailIntent.createChooser(emailIntent, "Send Email"));
    }

/* Replaced 21:55 on 1st November 2017:
    public void launchEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        startActivity(emailIntent);
    } */
}
