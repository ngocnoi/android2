package com.example.logindemo.Adapter;
import android.content.Context;

import android.content.Intent;
import android.view.ViewGroup;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.DetailBook;
import com.example.logindemo.Entity.Book;
import com.example.logindemo.Helper.DownloadImageTask;
import com.example.logindemo.R;
import com.example.logindemo.homepage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    ArrayList<Book> books;
    Context context;
    private OnBookListener mOnBookListener;
    public BookAdapter(ArrayList<Book> books, Context context, OnBookListener onBookListener) {
        this.books = books;
        this.context = context;
        this.mOnBookListener=onBookListener;
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view,mOnBookListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        holder.imageView.setImageResource(
//                books.get(position).getCover()
//        );
        new DownloadImageTask(holder.imageView)
                .execute(books.get(position).getCover());
        holder.textView.setText(books.get(position).getBookName());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        OnBookListener onBookListener;
        public ViewHolder(@NonNull View itemView,OnBookListener onBookListener) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);
            textView=itemView.findViewById(R.id.text_view);
            itemView.setOnClickListener(this);
            this.onBookListener=onBookListener;
        }

        @Override
        public void onClick(View v) {
         onBookListener.onBookClick(getAdapterPosition());
        }
    }
    public interface OnBookListener {
     void onBookClick(int position);
    }
}
