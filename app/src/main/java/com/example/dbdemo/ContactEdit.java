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

public class ContactEdit extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        MyDbHandler db = new MyDbHandler(this);

        EditText nameEdit = findViewById(R.id.nameEdit);
        EditText numberEdit = findViewById(R.id.numberEdit);

        String name = getIntent().getStringExtra("name");
        String number = getIntent().getStringExtra("number");
        int contact_id = getIntent().getIntExtra("id",0);
        nameEdit.setText(name);
        numberEdit.setText(number);
        Button saveBtn = findViewById(R.id.saveBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
        saveBtn.setOnClickListener(v->{
            Contact edited_contact = new Contact();
            edited_contact.setId(contact_id);
            edited_contact.setName(nameEdit.getText().toString());
            edited_contact.setPhoneNumber(numberEdit.getText().toString());
            db.updateContact(edited_contact);
            Toast.makeText(this, "Contact Saved Successfully!", Toast.LENGTH_SHORT).show();

        });
        deleteBtn.setOnClickListener(v->{
            db.deleteContactById(contact_id);
            Toast.makeText(this, "Contact Deleted Successfully", Toast.LENGTH_SHORT).show();

        });


    }
}