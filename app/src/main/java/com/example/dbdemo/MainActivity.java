package com.example.dbdemo;
/* hellow world i am amit roy */

import static kotlinx.coroutines.flow.FlowKt.collect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbdemo.adapter.RecyclerViewAdapter;
import com.example.dbdemo.database.MyDbHandler;
import com.example.dbdemo.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> contactArrayList;
    private  ArrayAdapter<String> arrayAdapter;
    MyDbHandler db = new MyDbHandler(MainActivity.this);

    @Override
    protected void onResume() {
        super.onResume();
        contactArrayList = new ArrayList<>();

        //Fetch All Contacts from database
        List<Contact>  contactsList = db.getAllContacts();
        for (Contact contact : contactsList){
            contactArrayList.add(contact);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

    }


    /*
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> arrayList = new ArrayList<>();
        ListView contactList = findViewById(R.id.contactList);

        //Fetch All Contacts from database
        List<Contact>  contacts = db.getAllContacts();
        for (Contact contact : contacts){
            arrayList.add(contact.getName()+"( "+contact.getPhoneNumber()+" )");
        }
        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        // View The contact to UI
        contactList.setAdapter(arrayAdapter);
        contactList.setAdapter(arrayAdapter);
        contactList.setOnItemClickListener((parent,view,position,id)->{
            Contact selectedItem = contacts.get(position);
            Toast.makeText(this, "Selected:  "+selectedItem.getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,ContactEdit.class);
            intent.putExtra("name",  selectedItem.getName());
            intent.putExtra("number",  selectedItem.getPhoneNumber());
            intent.putExtra("id",  selectedItem.getId());
            startActivity(intent);




        });




    }
        */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button addNewContact = findViewById(R.id.addNewContact);
        addNewContact.setOnClickListener(v->{
            Intent intent2 = new Intent(MainActivity.this,AddNewContact.class);
            startActivity(intent2);
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Creating the Contact Object

        /*

        Contact amit = new Contact("Amit","1234568979");
        //or amit.setName = "Amit"
        //amit.setPhoneNumber = "123456789456"
        //Adding a contact to the database
        db.addContact(amit);
        //Creating the Contact Object
        Contact dedsec = new Contact("Dedsec","1337133713");
        //Adding a contact to the database
        db.addContact(dedsec);
        //Creating the Contact Object
        Contact devid = new Contact("Devid","55663321233");
        //Adding a contact to the database
        db.addContact(devid);
        Log.d("db_tag","ALL INSERTED SUCCESSFULLY");

        //update amit contact
        amit.setId(1);
        amit.setName("AMIT EDITED");
        amit.setPhoneNumber("7777777777");
        int affectedRows =  db.updateContact(amit);
        Log.d("db_tag","Rows affected -> "+affectedRows);

        //Delete Contacts by id
        db.deleteContactById(7);
        db.deleteContactById(3);
//        Delete Contacts
        devid.setId(12);
        db.deleteContact(devid);
         */
        contactArrayList = new ArrayList<>();

        //Fetch All Contacts from database
         List<Contact>  contactsList = db.getAllContacts();
         for (Contact contact : contactsList){
         contactArrayList.add(contact);
         }
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,contactArrayList);
         recyclerView.setAdapter(recyclerViewAdapter);


         /*
        contactList.setOnItemClickListener((parent,view,position,id)->{
            Contact selectedItem = contacts.get(position);
            Toast.makeText(this, "Selected:  "+selectedItem.getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,ContactEdit.class);
            intent.putExtra("name",  selectedItem.getName());
            intent.putExtra("number",  selectedItem.getPhoneNumber());
            intent.putExtra("id",  selectedItem.getId());
            startActivity(intent);




        });

        */

        //get Count of all contacts
        Log.d("db_tag","You Have "+db.getContactsCount()+" Contacts");
    }
}