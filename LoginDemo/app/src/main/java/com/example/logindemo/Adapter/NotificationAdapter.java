package com.example.logindemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.Entity.Notification;
import com.example.logindemo.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    ArrayList<Notification> notifications;
    Context context;
    private OnNotificationListener mNotificationListener;
    public NotificationAdapter(ArrayList<Notification> notifications, Context context,OnNotificationListener mNotificationListener) {
        this.notifications = notifications;
        this.context = context;
        this.mNotificationListener=mNotificationListener;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item2,parent,false);
        return new  ViewHolder(view,mNotificationListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {

        holder.textView39.setText(notifications.get(position).getLibrian().getUsername());
        holder.textView40.setText(notifications.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView39,textView40;
       OnNotificationListener onNotificationListener;
        public ViewHolder(@NonNull View itemView,OnNotificationListener onNotificationListener) {
            super(itemView);
            textView39 = itemView.findViewById(R.id.textView39);
            textView40 = itemView.findViewById(R.id.textView40);
            itemView.setOnClickListener(this);
            this.onNotificationListener=onNotificationListener;
        }

        @Override
        public void onClick(View v) {
            onNotificationListener.onNotificationClick(getAdapterPosition());
        }
    }
    public interface OnNotificationListener {
        void onNotificationClick(int position);
    }

}
