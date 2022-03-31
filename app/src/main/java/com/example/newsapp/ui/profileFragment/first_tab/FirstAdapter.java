package com.example.newsapp.ui.profileFragment.first_tab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.databinding.ItemRecylerTabFirstBinding;

import java.util.ArrayList;

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolderFirst> {
    private ArrayList<Integer> listTab;

    public FirstAdapter(ArrayList<Integer> listTab) {
        this.listTab = listTab;
    }

    @NonNull
    @Override
    public ViewHolderFirst onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderFirst(ItemRecylerTabFirstBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFirst holder, int position) {
        holder.bind(listTab.get(position));

    }

    @Override
    public int getItemCount() {
        return listTab.size();
    }

    class ViewHolderFirst extends RecyclerView.ViewHolder {
        private ItemRecylerTabFirstBinding binding;

        public ViewHolderFirst(@NonNull ItemRecylerTabFirstBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(Integer integer) {
            binding.imageTabFirst.setImageResource(integer);

        }
    }
}
