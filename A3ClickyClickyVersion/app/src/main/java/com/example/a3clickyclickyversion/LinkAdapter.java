package com.example.a3clickyclickyversion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkViewHolder> {

    private List<Link> links;
    private Context context;

    public LinkAdapter(List<Link> links, Context context) {
        this.links = links;
        this.context = context;
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link, parent, false);
        return new LinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder holder, int position) {
        Link link = links.get(position);
        holder.linkName.setText(link.getName());
        holder.linkUrl.setText(link.getUrl());
        holder.itemView.setOnClickListener(v -> {
            if (v.isPressed()) {
                // Start an intent to launch the URL in a web browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    class LinkViewHolder extends RecyclerView.ViewHolder {

        TextView linkName;
        TextView linkUrl;

        LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            linkName = itemView.findViewById(R.id.link_name);
            linkUrl = itemView.findViewById(R.id.link_url);
        }
    }
}