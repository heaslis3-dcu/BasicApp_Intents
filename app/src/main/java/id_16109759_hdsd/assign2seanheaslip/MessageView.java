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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MessageView extends AppCompatActivity
{
    public static final String EXTRA_MESSAGE = "id_16109759_hdsd.assign2seanheaslip";
    private static Intent sendMsgIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);
    }

    /**
     * Citation: Class contains code adapted from URL:
     * https://developer.android.com/training/basics/firstapp/starting-activity.html
     * Permission: Licence - Apache V2
     *
     * Method is called when the user taps the send button in Message View Activity
     * Data stored in the To and Subject fields is captured.
     * Data is caught in the onCreate Method of the Main Activity,
     */
    // Called when user taps the Send Button - Updated 01/11/2017
    public void sendMessage(View view)
    {
        EditText edtTxtTo = (EditText) findViewById(R.id.edTxt_To);
        EditText edtTxtSub = (EditText) findViewById(R.id.edTxt_Subject);
        sendMsgIntent = new Intent(this, MainActivity.class);
        sendMsgIntent.putExtra("emailTo", edtTxtTo.getText().toString());
        sendMsgIntent.putExtra("emailSubject", edtTxtSub.getText().toString());
        finish();
        startActivity(sendMsgIntent);
    }
}
