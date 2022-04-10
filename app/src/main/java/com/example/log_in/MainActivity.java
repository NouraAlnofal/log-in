package com.example.log_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth uAuth;
    private  Button logut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uAuth= FirebaseAuth.getInstance();
        logut=findViewById(R.id.logout);

        logut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currUser=uAuth.getCurrentUser();
        if(currUser==null){
            startActivity(new Intent(MainActivity.this,login.class));
        }else{
            startActivity(new Intent(MainActivity.this,home.class));

        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this,login.class));
    }

}