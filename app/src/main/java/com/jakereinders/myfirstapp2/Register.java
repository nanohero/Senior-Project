package com.jakereinders.myfirstapp2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Register extends Activity {

    public void clickChangeAvatar(View view) {

        // create an intent to carry the variables over so user does not have to re-enter everything

        Intent thisIntent = new Intent(this, ChooseAvatar.class);

        // Declare the variables
        final EditText email;
        final EditText password;
        final EditText screenName;


        // get the values
        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtPassword);
        screenName = (EditText) findViewById(R.id.txtScreenName);

        // Convert the other variables to usable Strings
        String strEmail = email.getText().toString();
        String strScreenName = screenName.getText().toString();
        String strPassword = password.getText().toString();

        // Add the extras to the intent
        thisIntent.putExtra("email", strEmail);
        thisIntent.putExtra("screen_name", strScreenName);
        thisIntent.putExtra("password", strPassword);


        startActivity(thisIntent);
    } // end method

    public void clickRegister(View view) {

        // this method is called when the Register button is clicked

        // Declare the variables
        final EditText email;
        final EditText password;
        final EditText screenName;
        ImageView avatar;

        // get the values
        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtPassword);
        screenName = (EditText) findViewById(R.id.txtScreenName);
        avatar = (ImageView) findViewById(R.id.imgAvatar);

        // Convert the variables to usable types
        String strEmail = email.getText().toString();
        String strScreenName = screenName.getText().toString();
        String strAvatar = avatar.getDrawable().toString();
        String strPassword = password.getText().toString();

        // Validation

        int errorsReported = 0;

        if (strEmail.matches("")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Error")
                    .setMessage("You need to supply a valid email address")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            email.requestFocus();
                        }
                    })
                    .setNegativeButton("", null)
                    .show();
            errorsReported++;

        } // end if

        if (strScreenName.matches("")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Error")
                    .setMessage("You need to supply a valid Screen Name")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            screenName.requestFocus();
                        }
                    })
                    .setNegativeButton("", null)
                    .show();
            errorsReported++;

        } // end if

        if (strPassword.matches("")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Error")
                    .setMessage("You need to supply a valid Password")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            password.requestFocus();
                        }
                    })
                    .setNegativeButton("", null)
                    .show();
            errorsReported++;

        } // end if


        if (errorsReported == 0) {

            // Check if the email already exists in the DB
            boolean userExists = true;
            try {
                SQLiteDatabase ratingAppDB = Register.this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
                String Query = "SELECT * FROM users WHERE email = '" + strEmail + "'";
                Cursor c = ratingAppDB.rawQuery(Query, null);
                if (c.getCount() > 0) {
                    c.close();
                } else {
                    userExists = false;
                    c.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (userExists) {
                Toast.makeText(getApplicationContext(), "That email address has already been used!", Toast.LENGTH_LONG).show();
                email.setText("");
                email.requestFocus();
            } else {
                // Toast.makeText(getApplicationContext(), "User does not exist!!", Toast.LENGTH_LONG).show();
                // hash the password
                strPassword = MD5(strPassword);


                // Create the database and table if it does not yet exist

                try {
                    // Add user to the Database
                    SQLiteDatabase ratingAppDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
                    ratingAppDB.execSQL("CREATE TABLE IF NOT EXISTS users (email VARCHAR, screen_name VARCHAR, password VARCHAR, avatar_number VARCHAR)");
                    ratingAppDB.execSQL("INSERT INTO users (email, screen_name, password, avatar_number) VALUES ('" +
                            strEmail + "', '" + strScreenName + "', '" + strPassword + "', '" + strAvatar + "')");

                    // Load the Welcome Activity and welcome the new user

                    Intent intent = new Intent(this, Welcome.class);

                    // Add the extras to the intent
                    intent.putExtra("email", strEmail);
                    intent.putExtra("screen_name", strScreenName);

                    startActivity(intent);


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } // end if user exists
        } // end if errors reported == 0
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    // function to return the int id of a drawable asset for dynamically updating the resource location

    public void setAvatarRsc(String avatar, ImageView imageView) {

        switch (avatar) {
            case "avatar1":
                imageView.setImageResource(R.drawable.avatar1);
                break;
            case "avatar2":
                imageView.setImageResource(R.drawable.avatar2);
                break;
            case "avatar3":
                imageView.setImageResource(R.drawable.avatar3);
                break;
            case "avatar4":
                imageView.setImageResource(R.drawable.avatar4);
                break;
            case "avatar5":
                imageView.setImageResource(R.drawable.avatar5);
                break;
            case "avatar6":
                imageView.setImageResource(R.drawable.avatar6);
                break;
            case "avatar7":
                imageView.setImageResource(R.drawable.avatar7);
                break;
            case "avatar8":
                imageView.setImageResource(R.drawable.avatar8);
                break;
            case "avatar9":
                imageView.setImageResource(R.drawable.avatar9);
                break;
            default:
                // no action;
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // check if there are added items in the Intent that brought us here
        String avatarChosen;
        Intent intent = getIntent();
        if (intent.hasExtra("Avatar")) {
            Bundle bd = getIntent().getExtras();
            if (!bd.getString("Avatar").equals(null)) {
                avatarChosen = bd.getString("Avatar");
                ImageView iv = (ImageView) findViewById(R.id.imgAvatar);
                setAvatarRsc(avatarChosen, iv);
            } // end if
        } //end if

        if (intent.hasExtra("email")) {
            Bundle bd = getIntent().getExtras();
            if (!bd.getString("email").equals(null)) {
                EditText etEmail = (EditText) findViewById(R.id.txtEmail);
                etEmail.setText(bd.get("email").toString());
            } // end if
        } //end if

        if (intent.hasExtra("password")) {
            Bundle bd = getIntent().getExtras();
            if (!bd.getString("password").equals(null)) {
                EditText etPassword = (EditText) findViewById(R.id.txtPassword);
                etPassword.setText(bd.get("password").toString());
            } // end if
        } //end if

        if (intent.hasExtra("screen_name")) {
            Bundle bd = getIntent().getExtras();
            if (!bd.getString("screen_name").equals(null)) {
                EditText etScreenName = (EditText) findViewById(R.id.txtScreenName);
                etScreenName.setText(bd.get("screen_name").toString());
            } // end if
        } //end if

    }
}
