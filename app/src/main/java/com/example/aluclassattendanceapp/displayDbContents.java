package com.example.aluclassattendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class displayDbContents extends AppCompatActivity {
    Button delete, update, retrieve;
    public EditText editText, editText2;
//    public StudentDatabase myDatabase = new StudentDatabase(getBaseContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_db_contents);


        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        retrieve = findViewById(R.id.retrieve);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDatabase myDatabase = new StudentDatabase(getBaseContext());
                myDatabase.delete(editText.getText().toString());
//                myDatabase.delete(Integer.parseInt(editText.getText().toString()));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDatabase myDatabase = new StudentDatabase(getBaseContext());
                myDatabase.update(editText.getText().toString(), editText2.getText().toString());
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDatabase myDatabase = new StudentDatabase(getBaseContext());
                List<List<String>> data = myDatabase.getAllData();
                int i = 0;
                StringBuilder b = new StringBuilder();
                for (List<String> s : data) {
                    b.append("Index: ").append(i).append("\n");
                    for (String s1 : s) {
                        b.append(s1).append("\n");
                    }
                    b.append("\n\n\n");
                    i++;
                }

                alertDialogMine(b.toString());




                /*if (textView.getText().equals(" ")){
                    textView.setText(b.toString());
                    retrieve.setText("Clear");
                }else{
                    textView.setText(" ");
                    retrieve.setText("Retrieve");
                }*/

            }
        });
    }

    public void alertDialogMine(String message){
        new AlertDialog.Builder(this)
                .setTitle("View Data")
                .setMessage(message)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

}
