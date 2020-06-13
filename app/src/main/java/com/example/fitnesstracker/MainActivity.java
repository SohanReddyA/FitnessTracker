package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button b,del;
    EditText cal;
    TextView name,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b= findViewById(R.id.button);
        cal= findViewById(R.id.calories);
        name = findViewById(R.id.Name);
        age = findViewById(R.id.Age);
        del = findViewById(R.id.Delete);

        DatabaseReference Cal;
        Cal = FirebaseDatabase.getInstance().getReference("Calories");
        Cal.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                if(text!=null){
                    cal.setText(text);
                }
                else{
                    cal.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference Name;
        Name = FirebaseDatabase.getInstance().getReference("Name");
        DatabaseReference Age;
        Age = FirebaseDatabase.getInstance().getReference("Age");
        Name.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);

                name.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Age.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);

                age.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                DatabaseReference Calo;
                Calo = FirebaseDatabase.getInstance().getReference("Calories");

                if(b.getText().equals("Save"))
                {
                    cal.setFocusable(false);
                    cal.setFocusableInTouchMode(false);
                    cal.setClickable(false);
                    cal.setEnabled(false);
                    Calo.setValue(cal.getText().toString());
                    b.setText("Edit");
                }
                else
                {
                    cal.setEnabled(true);
                    cal.setFocusable(true);
                    cal.setFocusableInTouchMode(true);
                    cal.setClickable(true);
                    b.setText("Save");
                }
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference Name;
                Name = FirebaseDatabase.getInstance().getReference("Name");
                Name.setValue(null);
                DatabaseReference Age;
                Age = FirebaseDatabase.getInstance().getReference("Age");
                Age.setValue(null);
                DatabaseReference Cal;
                Cal = FirebaseDatabase.getInstance().getReference("Calories");
                Cal.setValue(null);
                Intent intent = new Intent(MainActivity.this, Prof.class);
                startActivity(intent);
            }
        });
    }
}
