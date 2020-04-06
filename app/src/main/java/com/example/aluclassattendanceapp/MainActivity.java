package com.example.aluclassattendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
//    Button add;
//    EditText email, password, student_name;

    public static FragmentManager fragmentManager;
//    StudentDatabase myDatabase = new StudentDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* email = findViewById(R.id.student_email);
        password = findViewById(R.id.student_password);
        student_name = findViewById(R.id.student_name);
        add = findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myDatabase.trial1(student_name.getText().toString(), email.getText().toString(), password.getText().toString());
                } catch (NullPointerException e) {
                    System.err.println(e);
                }

            }
        });*/
        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            FragmentTransaction ft = fragmentManager.beginTransaction();
            mainActivityFragment fragment1 = new mainActivityFragment();

            ft.add(R.id.fragment_container, fragment1, null);
            ft.commit();
        }


    }
}
