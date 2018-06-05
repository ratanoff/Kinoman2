package ru.ratanov.kinoman;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {

    private Context context;
    private List<String> items;

    public TopAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_item, parent, false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {
        holder.bindItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class TopViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;

        public TopViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.image_view);
        }

        public void bindItem(String url) {
            Picasso.get()
                    .load(url)
                    .into(poster);
        }
    }
}