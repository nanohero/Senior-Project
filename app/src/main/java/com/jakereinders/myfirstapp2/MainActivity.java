package com.jakereinders.myfirstapp2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickLogin(View view) {



        EditText etEmail = (EditText) findViewById(R.id.txtEmail); // get value of the username text field - R accesses all resources
        EditText etPassword = (EditText) findViewById(R.id.txtPassword);


        // Convert variables, hash the password

        String strEmail = etEmail.getText().toString();
        String strPassword = etPassword.getText().toString();

        strPassword = MD5(strPassword);

        // validate credentials
        String strScreenName = checkCredentials(strEmail, strPassword);

        if (strScreenName.matches("")){
            Toast.makeText(getApplicationContext(), "Username or Password not valid!", Toast.LENGTH_LONG).show();
            etEmail.setText("");
            etPassword.setText("");
            etEmail.requestFocus();
        } else {
            // valid user, continue with application
            Intent intent = new Intent(this, Welcome.class);

            // Add the extras to the intent
            intent.putExtra("email", strEmail);
            intent.putExtra("screen_name", strScreenName);

            startActivity(intent);
        }

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

    public void clickRegister(View view) {

        startActivity(new Intent(this, Register.class));
    }

    public String checkCredentials(String Email, String Password){


        String strScreenName = "";
        // perform validation code here
        // if verified set boolean value to true

        try {
            SQLiteDatabase ratingAppDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            String Query = "SELECT * FROM users WHERE email = '" + Email + "' AND password = '" + Password + "'";
            Cursor c = ratingAppDB.rawQuery(Query, null);
            int ciScreenName = c.getColumnIndex("screen_name");
            c.moveToFirst();
            while (c != null) {
                strScreenName = c.getString(ciScreenName);
                c.moveToNext();
                }
             c.close();
            } catch (Exception e) {
            e.printStackTrace();
        }

        return strScreenName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate the DB

    }
}
