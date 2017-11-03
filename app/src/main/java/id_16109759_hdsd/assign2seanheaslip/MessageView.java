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
