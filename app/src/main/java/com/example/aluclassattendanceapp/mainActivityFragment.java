package com.example.aluclassattendanceapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class mainActivityFragment extends Fragment {
    EditText email, password, student_id, student_name;
    RadioGroup gender;
    RadioButton radioFemale, radioMale;

    Button save;
    String genderText;

    int countGender = 0;

    public static final String MyPREFERENCES = "erasPrefs";
    public static final String Password = "phoneKey";
    public static final String Name = "emailKey";

    SharedPreferences sharedpreferences;


    public mainActivityFragment() {
        // Required empty public constructor
    }

    public void onRadioButtonClicked(View view) {
        countGender += 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);

        save = view.findViewById(R.id.saveButton);
        email = view.findViewById(R.id.student_email);
        password = view.findViewById(R.id.student_password);
        student_id = view.findViewById(R.id.student_id);
        student_name = view.findViewById(R.id.student_name);
        gender = view.findViewById(R.id.radioGender);
        radioMale = view.findViewById(R.id.radioMale);
        radioFemale = view.findViewById(R.id.radioFemale);

        radioMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });
        radioFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });


        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordText = password.getText().toString();
                String studentNameText = student_name.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                if (student_name.getText().toString().length() == 0) {
                    student_name.requestFocus();
                    student_name.setError("Student Name is required!");
                }
                if (email.getText().toString().length() == 0) {
                    email.requestFocus();
                    email.setError("Student Email is required!");
                }
                if (password.getText().toString().length() < 8) {
                    if (password.getText().toString().length() == 0) {
                        password.requestFocus();
                        password.setError("Password is Required!");
                    } else {
                        password.requestFocus();
                        password.setError("Password should be more than 8 characters!");
                    }

                }


                if (countGender < 1) {
                    radioMale.requestFocus();
                    radioMale.setError("Gender Required");
                }

                if (!((password.getText().toString().length() < 8)) && !(email.getText().toString().length() == 0)
                        && !(student_name.getText().toString().length() == 0) && (countGender > 0)) {

                    editor.putString(Password, passwordText);
                    editor.putString(Name, studentNameText);
                    editor.apply();

                    Intent intent = new Intent(getActivity(), confirmation.class);

                    intent.putExtra("NAME_KEY", studentNameText);


                    startActivity(intent);
                }
            }
        });

        if (sharedpreferences.contains(Name)) {
            student_name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Password)) {
            password.setText(sharedpreferences.getString(Password, ""));
        }


//        return inflater.inflate(R.layout.fragment_main_activity, container, false);
        return view;
    }

}
