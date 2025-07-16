package com.example.dbdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbdemo.ContactEdit;
import com.example.dbdemo.MainActivity;
import com.example.dbdemo.R;
import com.example.dbdemo.model.Contact;


import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.contactName.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());
        holder.iconButton.setImageResource(R.mipmap.contact);

    }



    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView contactName;
        public TextView phoneNumber;
        public ImageView iconButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            contactName = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            iconButton = itemView.findViewById(R.id.iconButton);

            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int positon = this.getBindingAdapterPosition();
            Contact contact = contactList.get(positon);
            String name;
            String number;
            int id;

            Toast.makeText(context, "Clicked-> "+ contact.getName()+"-"+contact.getPhoneNumber()+"-"+contact.getId(), Toast.LENGTH_SHORT).show();
            name = contact.getName();
            number = contact.getPhoneNumber();
            id = contact.getId();
            Intent intent = new Intent(context, ContactEdit.class);
            intent.putExtra("name",name);
            intent.putExtra("number",number);
            intent.putExtra("id",id);
            context.startActivity(intent);
        }
    }
}
