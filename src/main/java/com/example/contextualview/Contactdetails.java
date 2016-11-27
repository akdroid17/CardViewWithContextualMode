package com.example.contextualview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Contactdetails extends AppCompatActivity {

    ImageView imageView;
    TextView dName,dEmail,dMob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactdetails);
        imageView= (ImageView) findViewById(R.id.d_imageView);
        dName= (TextView) findViewById(R.id.d_name);
        dEmail= (TextView) findViewById(R.id.d_email);
        dMob= (TextView) findViewById(R.id.d_mobile);

        imageView.setImageResource(getIntent().getIntExtra("image",00));
        dName.setText("Name: "+getIntent().getStringExtra("Name"));
        dEmail.setText("Email: "+getIntent().getStringExtra("Email"));
        dMob.setText("Mob: "+getIntent().getStringExtra("Mobile"));


    }
}
