//for comiitt
package com.jakereinders.myfirstapp2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent currentIntent = getIntent();

        String strEmail = "";
        String strScreenName = "";

        if (currentIntent.hasExtra("email")) {
            Bundle bd = getIntent().getExtras();
            if (!bd.getString("email").equals(null)) {
                strEmail = bd.get("email").toString();
            }
        }

        if (currentIntent.hasExtra("screen_name")) {
            Bundle bd = getIntent().getExtras();
            if (!bd.getString("screen_name").equals(null)) {
                strScreenName = bd.get("screen_name").toString();
            }
        }

        // if for some reason the page loads without the needed variables
        if (strEmail.matches("") || strScreenName.matches("")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Error")
                    .setMessage("One or more needed values were not found.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Welcome.this, MainActivity.class));
                        }
                    })
                    .setNegativeButton("", null)
                    .show();
        } // end if

        else {
            TextView txtWelcome = (TextView) findViewById(R.id.txtWelcome);
            txtWelcome.setText("Welcome " + strScreenName);
        } // end else

    }
}
