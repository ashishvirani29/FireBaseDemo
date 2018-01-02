package com.chhelana.firebasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private LinearLayout rootView;
    private EditText etQuestion;
    private EditText etAns;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnInsert;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ListView listView;
    private UserDataAdapter userDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

        findViews();
    }

    private void findViews() {
        rootView = (LinearLayout) findViewById(0);
        etQuestion = (EditText) findViewById(R.id.etQuestion);
        etAns = (EditText) findViewById(R.id.etAns);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        listView = (ListView) findViewById(R.id.datalistview);
        btnInsert.setOnClickListener(this);

        getData();
    }

    private void getData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<UserData> userDatas = new ArrayList<UserData>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserData user = snapshot.getValue(UserData.class);
                    userDatas.add(user);
                }

                userDataAdapter = new UserDataAdapter(MainActivity.this, userDatas);
                listView.setAdapter(userDataAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnInsert) {
            if (etQuestion.length() >= 1 && etAns.length() >= 1 && etEmail.length() >= 1 && etPassword.length() >= 1) {
                String question = etQuestion.getText().toString();
                String ans = etAns.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                UserData user = new UserData();
                user.question = question;
                user.ans = ans;
                user.email = email;
                user.password = password;
                myRef.child(String.valueOf(System.currentTimeMillis())).setValue(user);
                etQuestion.setText("");
                etAns.setText("");
                etEmail.setText("");
                etPassword.setText("");
                Toast.makeText(this, "Sucsess", Toast.LENGTH_SHORT).show();
                getData();
            }
        }
    }
}
