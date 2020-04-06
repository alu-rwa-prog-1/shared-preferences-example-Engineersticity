package com.example.aluclassattendanceapp;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class mainActivityFragment extends Fragment {
    EditText email, password, student_id, student_name;
    RadioGroup gender;
    RadioButton radioFemale, radioMale;

    Button save, add;
    String genderText;

    int countGender = 0;

    public static final String MyPREFERENCES = "erasPrefs";
    public static final String Password = "phoneKey";
    public static final String Name = "emailKey";

    SharedPreferences sharedpreferences;
    private String CHANNEL_ID = "1";
    private int NOTIFICATION_ID = 1;
    private int MAX_PROGRESS = 100;
    private int CURRENT_PROGRESS = 50;

    private static final String KEY_TEXT_REPLY = "key_text_reply";
    String replyLabel = "Hello from the other side";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void DisplayNotification(View view) {
        CreateNotificationChannel();

        /*Intent landingIntent = new Intent(getContext(), confirmation.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, landingIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent replyIntent = new Intent(getContext(), confirmation.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent replyPendingIntent = PendingIntent.getActivity(getContext(), 0, replyIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent dismissIntent = new Intent(getContext(), confirmation.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent dismissPendingIntent = PendingIntent.getActivity(getContext(), 0, dismissIntent, PendingIntent.FLAG_ONE_SHOT);*/

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        Intent landingIntent = new Intent(getContext(), mainActivityFragment.class);

        PendingIntent replyPendingIntent =
                PendingIntent.getBroadcast(getContext(), 0, landingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.gender_icon,
                        "Reply", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(Objects.requireNonNull(getContext()), CHANNEL_ID);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Objects.requireNonNull(getContext()));
        builder.setSmallIcon(R.drawable.card_icon);
        builder.setContentTitle("Reply to ALU APP");
        builder.setContentText("Send A message to the facilitator");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.addAction(action);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
//        builder.setContentIntent(pendingIntent);
//        builder.addAction(R.drawable.email_icon, "REPLY", replyPendingIntent);
//        builder.addAction(R.drawable.department_icon, "DISMISS", dismissPendingIntent);

      /*  Thread thread = new Thread() {
            @Override
            public void run() {
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Objects.requireNonNull(getContext()));
                int count = 0;
                builder.setProgress(MAX_PROGRESS, count, false);
                notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
                try {
                    while (count <= 100) {
                        count = count + 5;
                        sleep(1000);
                        builder.setProgress(MAX_PROGRESS, count, false);
//                        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
                    }
                    builder.setContentText("Download Complete");
                    builder.setProgress(0,0,false);
                    notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        thread.start();*/

    }

    private void CreateNotificationChannel() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            CharSequence name = "Personal Notification";
            String description = "Include all Personal Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) Objects.requireNonNull(getActivity()).getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);


        }


    }


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
        add = view.findViewById(R.id.addButton);
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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (getContext() != null) {
                        StudentDatabase myDatabase = new StudentDatabase(getContext());
                        myDatabase.trial1(getContext(), student_name.getText().toString(), email.getText().toString(), password.getText().toString());
                        /*String emailText = myDatabase.fetchFromDB();
                        if ( emailText != null){
                            student_name.setText(emailText);
                        }*/
                    }


                } catch (NullPointerException e) {
                    System.err.println(e);
                }

                Intent i = new Intent(getActivity(), displayDbContents.class);

                startActivity(i);

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

                DisplayNotification(v);

               /* if (!((password.getText().toString().length() < 8)) && !(email.getText().toString().length() == 0)
                        && !(student_name.getText().toString().length() == 0) && (countGender > 0)) {

                    editor.putString(Password, passwordText);
                    editor.putString(Name, studentNameText);
                    editor.apply();

                    Intent intent = new Intent(getActivity(), confirmation.class);

                    intent.putExtra("NAME_KEY", studentNameText);


                    startActivity(intent);
                }*/
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
