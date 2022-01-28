package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button logoutButton;
    private Button loadButton;
    private ListView listView;

    private EditText infoField;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();
        infoField=findViewById(R.id.infoField);
        loadButton = findViewById(R.id.addButton);
        logoutButton = findViewById(R.id.logoutButton);
        listView=findViewById(R.id.listView);
        ArrayList<String> arrayList = new ArrayList<>();

//        HashMap<String, Object> hMap2 = new HashMap<>();
//        hMap2.put("A","Alpha");
//        hMap2.put("B","Beta");
//        hMap2.put("C","Cupcake");

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = infoField.getText().toString();
                DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Fruits");
                dr.child(String.valueOf(rand.nextInt(100))).setValue(str);
                Toast.makeText(MainActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this,"Logged out!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this,startActivity.class));
                finish();
            }
        });
//        HashMap<String, Object> hMap = new HashMap<>();
//        hMap.put("Compiled","C");
//        hMap.put("Interpreted","Python");
//        hMap.put("Hybrid","Java");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        FirebaseDatabase.getInstance().getReference().child("Fruits");
        DatabaseReference df = FirebaseDatabase.getInstance().getReference("Fruits");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                arrayList.add(dataSnapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //FirebaseDatabase.getInstance().getReference().child("Programming languages").child("Languages").updateChildren(hMap);
//        FirebaseDatabase.getInstance().getReference().child("Example").child("n1").setValue("Name1");
//        FirebaseDatabase.getInstance().getReference().push().child("Example23").setValue("name2");


    }
}