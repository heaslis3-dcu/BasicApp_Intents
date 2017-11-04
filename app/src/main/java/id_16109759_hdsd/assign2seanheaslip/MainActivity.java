/*
* * Copyright 2013 The Android Open Source Project  *
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0  *
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package id_16109759_hdsd.assign2seanheaslip;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private static final int ACTIVITY_START_CAMERA = 0;
    private static Button btn;
    private static Intent cameraIntent, viewPhotoIntent, mocEmailIntent, launchEmailIntent;
    private static String emailAdd, subjectAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Section used to disable and enable 'Send' button on Main Activity
        btn = (Button) findViewById(R.id.button_Send);
        btn.setEnabled(false);

        //Declare and initialise Message details 'To' and 'Subject',
        emailAdd = getIntent().getStringExtra("emailTo");
        subjectAdd = getIntent().getStringExtra("emailSubject");

        /**
         * Tried to use if statement to determine if the two strings are null, however,
         * ran into some trouble with this, checked the length of the null value and it is '0',
         * And could not figure out why using '||' (OR) / '&&' (AND) would still result
         */
        //TODO - check if a Try/Catch method to throw IOEXCEPTION will work here
        if (emailAdd != null && subjectAdd != null)
        {
            TextView inputReturnedData = (TextView) findViewById(R.id.textView_Display);

            inputReturnedData.setText(
                    "To: " + emailAdd
                            + "\n"
                            + "Subject: " + subjectAdd);
            btn.setEnabled(true);
            Log.i("Assign2Tag", "The Email entered is: " + emailAdd + " and Subject is: " + subjectAdd);
        }
        else
        {
            Toast.makeText(this, "Send Button Only enabled when both To & Subject is populated", Toast.LENGTH_LONG).show();
            btn.setEnabled(false);
        }
    }

    /**
     * Citation:
     * Licence include above
     * MediaStore reference: Constants: ACTION_IMAGE_CAPTURE
     * https://developer.android.com/reference/android/provider/MediaStore.html
     * https://developer.android.com/reference/android/provider/MediaStore.html#ACTION_IMAGE_CAPTURE
     *
     * @param view
     */
    public void captureImage(View view)
    {
        //Toast.makeText(this, "Camera button pressed", Toast.LENGTH_LONG).show();
        cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, "Recent");
        startActivityForResult(cameraIntent, ACTIVITY_START_CAMERA);

        //Log message
        Log.i("Assign2Tag", "The camera has opened!");
    }

    /**
     * Citation:
     * Receive the Result: onActivityResult:
     * https://developer.android.com/training/basics/intents/result.html
     * During testing - noted the onActivityResult method ran twice,
     * later discovered the only purpose of this method was to post the Toast I had added during testing
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == ACTIVITY_START_CAMERA && resultCode == RESULT_OK);
        {
            Toast.makeText(this, "Image taken", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Citation:
     * code adapted from - under Finally section
     * https://stackoverflow.com/questions/11969289
     * @param view
     */
    public void viewPhoto(View view)
    {
        viewPhotoIntent = new Intent();
        viewPhotoIntent.setType("image/*");
        viewPhotoIntent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(viewPhotoIntent.createChooser(viewPhotoIntent, "Select Picture"), 1);

        //log message
        Log.i("Assign2Tag", "The photo has been viewed");
    }

    /**
     * Explicit Intent
     * Citation:
     * code adapted from course notes section on'Intent Types' - page 72
     * @param arg0
     */
    public void openMocEmail(View arg0)
    {
        mocEmailIntent = new Intent(MainActivity.this, MessageView.class);
        startActivity(mocEmailIntent);

        //log message
        Log.i("Assign2Tag", "The MocEmail has opened!");
    }

    /**
     * Citation:
     * code adapted from reference for launching email application
     * https://stackoverflow.com/questions/3935009/
     * and adapted from reference using putExtra(Intent.EXTRA_EMAIL...etc:
     * https://stackoverflow.com/questions/8701634/
     */
    public void launchEmail(View view)
    {
        launchEmailIntent = new Intent(Intent.ACTION_SEND);
        launchEmailIntent.setType("text/html");
        emailAdd = getIntent().getStringExtra("emailTo");
        subjectAdd = getIntent().getStringExtra("emailSubject");
        launchEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAdd});
        launchEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectAdd);
        startActivity(launchEmailIntent.createChooser(launchEmailIntent, "Send Email"));

        //log message
        Log.i("Assign2Tag", "The email Intent has launched! To: " + emailAdd + " Subject: " + subjectAdd);
    }
}
