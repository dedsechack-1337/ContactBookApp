package com.example.dbdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dbdemo.database.MyDbHandler;
import com.example.dbdemo.model.Contact;

public class AddNewContact extends AppCompatActivity {
    MyDbHandler db = new MyDbHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button saveBtn = findViewById(R.id.saveBtn);
        EditText nameEdit = findViewById(R.id.nameEdit);
        EditText numberEdit = findViewById(R.id.numberEdit);
        saveBtn.setOnClickListener(v->{
            Contact new_contact = new Contact();
            new_contact.setName(nameEdit.getText().toString());
            new_contact.setPhoneNumber(numberEdit.getText().toString());
            db.addContact(new_contact);
            Toast.makeText(this, "New Contact Added Successfully!", Toast.LENGTH_SHORT).show();
        });
    }
}