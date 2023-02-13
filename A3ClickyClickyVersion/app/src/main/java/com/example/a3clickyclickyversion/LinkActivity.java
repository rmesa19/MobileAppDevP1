package com.example.a3clickyclickyversion;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LinkActivity extends AppCompatActivity {
    private static final String LINKS_KEY = "links";
    private RecyclerView recyclerView;
    private LinkAdapter adapter;
    private List<Link> links = new ArrayList<>();
    FloatingActionButton addLinkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        if (savedInstanceState != null) {
            links = (List<Link>) savedInstanceState.getSerializable(LINKS_KEY);
        }

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LinkAdapter(links, this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                links.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        addLinkButton = findViewById(R.id.floating_action_button);
        addLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddLinkDialog();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LINKS_KEY, (Serializable) links);
    }

    private void showAddLinkDialog() {
        final EditText nameEditText = new EditText(this);
        nameEditText.setHint("Name");
        final EditText urlEditText = new EditText(this);
        urlEditText.setHint("URL");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(nameEditText);
        layout.addView(urlEditText);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add Link")
                .setView(layout)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = nameEditText.getText().toString();
                        String url = urlEditText.getText().toString();

                        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(url)) {
                            Snackbar.make(addLinkButton, "Both name and URL are required", Snackbar.LENGTH_LONG).show();
                        } else {
                            Link link = new Link(name, url);
                            links.add(link);
                            adapter.notifyDataSetChanged();
                            Snackbar.make(addLinkButton, "Link successfully created", Snackbar.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
}