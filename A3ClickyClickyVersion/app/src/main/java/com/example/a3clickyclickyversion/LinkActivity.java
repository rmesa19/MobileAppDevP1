package com.example.a3clickyclickyversion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LinkActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinkAdapter adapter;
    private List<Link> links = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LinkAdapter(links);
        recyclerView.setAdapter(adapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddLinkDialog();
            }
        });
    }

    private void showAddLinkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Link");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_link, null);
        final EditText nameEditText = view.findViewById(R.id.link_name_edit_text);
        final EditText urlEditText = view.findViewById(R.id.link_url_edit_text);
        builder.setView(view);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameEditText.getText().toString().trim();
                String url = urlEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(url)) {
                    links.add(new Link(name, url));
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(LinkActivity.this, "Name and URL cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}