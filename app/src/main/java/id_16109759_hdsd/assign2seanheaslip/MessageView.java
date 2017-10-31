package id_16109759_hdsd.assign2seanheaslip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MessageView extends AppCompatActivity {

    public static final String EXTRA_MESSAGE ="id_16109759_hdsd.assign2seanheaslip";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);
    }

    /**
     * Called when user taps the Send Button
     * Reference:
     * https://developer.android.com/training/basics/firstapp/starting-activity.html
     */

    public void sendMessage(View view) {

        //Ref# - https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this,MainActivity.class);
        EditText edtTxtTo = (EditText) findViewById(R.id.edTxt_To);
        EditText edtTxtSub = (EditText) findViewById(R.id.edTxt_Subject);
        String message = ("\n"
                + "To: " + edtTxtTo.getText().toString()
                + "\n"
                + "Subject: " + edtTxtSub.getText().toString());
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
