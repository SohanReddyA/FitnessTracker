package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Prof extends AppCompatActivity {
    Button b;
    EditText name,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        b= findViewById(R.id.Save);
        name = findViewById(R.id.Name);
        age = findViewById(R.id.Age);
        final DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("Name");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                if(text==null)
                {
                    b.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                    age.setVisibility(View.VISIBLE);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(name.getText().toString().equals("")){
                                Toast.makeText(Prof.this,"Enter a name",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                DatabaseReference Name = FirebaseDatabase.getInstance().getReference("Name");
                                Name.setValue(name.getText().toString());
                            }
                            if(age.getText().toString().equals("")){
                                Toast.makeText(Prof.this,"Enter your age",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                DatabaseReference Age = FirebaseDatabase.getInstance().getReference("Age");
                                Age.setValue(age.getText().toString());
                            }
                            if(!name.getText().toString().equals("") && !age.getText().toString().equals("")) {
                                Intent intent = new Intent(Prof.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
                else{
                    Intent intent = new Intent(Prof.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
