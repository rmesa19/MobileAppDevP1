package com.example.a3clickyclickyversion;

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

    public LinkAdapter(List<Link> links) {
        this.links = links;
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
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    static class LinkViewHolder extends RecyclerView.ViewHolder {

        TextView linkName;
        TextView linkUrl;

        LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            linkName = itemView.findViewById(R.id.link_name);
            linkUrl = itemView.findViewById(R.id.link_url);
        }
    }
}