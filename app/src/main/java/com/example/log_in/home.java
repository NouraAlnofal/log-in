package com.example.log_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity {

    private FirebaseAuth uAuth;
    private TextView uemail;
    private  Button logut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //to get user email
        uAuth= FirebaseAuth.getInstance();
        FirebaseUser curUser=uAuth.getCurrentUser();
        String id=curUser.getEmail();

        uemail=(TextView)findViewById(R.id.userEmail);
        logut=findViewById(R.id.logouth);

        logut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        uemail.setText(id);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(home.this,login.class));
    }
}