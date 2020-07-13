package com.example.contactspage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.ItemEventListener {
    private ContactsAdapter adapter;
    private EditText fullNameEt;
    private ImageView addContactBtn;
    private int contactPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.rv_main_contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new ContactsAdapter(this);
        recyclerView.setAdapter(adapter);

        fullNameEt = findViewById(R.id.et_main_fullName);
        addContactBtn = findViewById(R.id.iv_main_addContact);

        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullNameEt.length() > 0){
                    if (contactPosition > -1){
                        adapter.updateContact(fullNameEt.getText().toString(), contactPosition);
                        addContactBtn.setImageResource(R.drawable.ic_add_white_24);
                        contactPosition = -1;
                    }else{
                        adapter.addNewContact(fullNameEt.getText().toString());
                        recyclerView.scrollToPosition(0);
                    }
                    fullNameEt.setText("");
                }
            }
        });


    }

    @Override
    public void onItemClick(String fullName, int postion) {
        contactPosition = postion;
        fullNameEt.setText(fullName);
        addContactBtn.setImageResource(R.drawable.ic_done_white_24);

    }
}