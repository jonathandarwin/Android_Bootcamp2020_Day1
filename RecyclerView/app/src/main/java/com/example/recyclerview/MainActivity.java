package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.recyclerview.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContactAdapter adapter;
    private List<Contact> contactList;
    private RecyclerView recyclerView;
    private Button btnAdd;
    private int counter = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if you sick of using findViewById, you can search for 'DataBinding'
        // DataBinding is really helpful for your productivity :)
        recyclerView = findViewById(R.id.recycler_view);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add a new model
                Contact contact = new Contact();
                contact.setId(counter);
                contact.setName("Name " + counter);
                contact.setPhone("" + counter++);

                // don't forget to add the model to our list
                contactList.add(contact);

                // There are 2 ways to update adapter
                // 1. update the adapter using updateData() -> using diffUtil (recommended, faster)
                // 2. update the adapter using notifyDataSetChanged() -> method from RecyclerView.Adapter
//                adapter.notifyDataSetChanged();
                adapter.updateData(contactList);
            }
        });

        setData();
        setAdapter();
    }

    public void setData(){
        // Set data in barbaric way
        contactList = new ArrayList<>();

        for(int i=0; i<counter; i++){
            Contact contact = new Contact();
            contact.setId(i);
            contact.setName("Name " + i);
            contact.setPhone(Integer.toString(i));

            contactList.add(contact);
        }
    }

    public void setAdapter(){
        // initiate adapter
        // we also pass the implementation of interface if the user click the item list
        adapter = new ContactAdapter(new ContactAdapter.OnItemClick() {
            @Override
            public void OnClick(Contact contact) {
                Toast.makeText(MainActivity.this, contact.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        // after initiate adapter, call this method to set the initial data
        adapter.updateData(contactList);

        // you should set the layout manager (either you want to display data in horizontal/vertical/grid etc)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // don't forget to set the adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
