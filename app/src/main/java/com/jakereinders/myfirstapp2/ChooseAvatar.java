package com.jakereinders.myfirstapp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ChooseAvatar extends Activity {

    public void clickChooseAvatar(View view) {
        // Checks for clicks on the individual avatar buttons, or the 'no thanks' link, handles accordingly.
        int idClicked = view.getId();

        // create a new intent to go back to previous screen
        Intent myIntent = new Intent(this, Register.class);

        Intent currentIntent = getIntent();

        if (currentIntent.hasExtra("email")) {
            Bundle bd = getIntent().getExtras();
            if (!bd.getString("email").equals(null)) {
                myIntent.putExtra("email",bd.get("email").toString());
            }
        }

        if (currentIntent.hasExtra("screen_name")) {
            Bundle bd = getIntent().getExtras();
            if (!bd.getString("screen_name").equals(null)) {
                myIntent.putExtra("screen_name",bd.get("screen_name").toString());
            }
        }

        if (currentIntent.hasExtra("password")) {
            Bundle bd = getIntent().getExtras();
            if (!bd.getString("password").equals(null)) {
                myIntent.putExtra("password",bd.get("password").toString());
            }
        }

        // use switch to determine which button was clicked, add the variable to the Intent object, and pass it to the Register screen
        switch (idClicked) {
            case R.id.btnAvatar1:
                //Log.i("button clicked","Avatar 1");
                myIntent.putExtra("Avatar","avatar1");
                break;
            case R.id.btnAvatar2:
                //Log.i("button clicked","Avatar 2");
                myIntent.putExtra("Avatar","avatar2");
                break;
            case R.id.btnAvatar3:
                //Log.i("button clicked","Avatar 3");
                myIntent.putExtra("Avatar","avatar3");
                break;
            case R.id.btnAvatar4:
                //Log.i("button clicked","Avatar 4");
                myIntent.putExtra("Avatar","avatar4");
                break;
            case R.id.btnAvatar5:
                //Log.i("button clicked","Avatar 5");
                myIntent.putExtra("Avatar","avatar5");
                break;
            case R.id.btnAvatar6:
                //Log.i("button clicked","Avatar 6");
                myIntent.putExtra("Avatar","avatar6");
                break;
            case R.id.btnAvatar7:
                //Log.i("button clicked","Avatar 7");
                myIntent.putExtra("Avatar","avatar7");
                break;
            case R.id.btnAvatar8:
                //Log.i("button clicked","Avatar 8");
                myIntent.putExtra("Avatar","avatar8");
                break;
            case R.id.btnAvatar9:
                //Log.i("button clicked","Avatar 9");
                myIntent.putExtra("Avatar","avatar9");
                break;
            case R.id.btnNoThanks:
                //Log.i("button clicked","No thanks");
                break;
            default:
                // no default set
                break;
        }

        // Activate the intent



        startActivity(myIntent);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_avatar);
    }
}
