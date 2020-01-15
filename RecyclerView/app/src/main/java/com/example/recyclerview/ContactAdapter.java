package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    // this adapter contains :
    // 1. ViewHolder
    // your xml viewholder will be represented in java code

    // 2. Method onCreateViewHolder
    // this method will create a viewholder xml

    // 3. Method onBindViewHolder
    // this method will bind your data to viewholder that has been created before

    // 4. Method getItemCount()
    // this method will return the size of the list data

    // 5. Method updateData()
    // update adapter using DiffUtil (not mandatory, you can use notifyDataSetChange() instead, but diffutil is better :))


    private List<Contact> contactList = new ArrayList<>();
    private OnItemClick listener;

    public ContactAdapter(OnItemClick listener){
        this.listener = listener;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvPhone;
        LinearLayout llContactItem;

        public ContactViewHolder(View view){
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvPhone = view.findViewById(R.id.tv_phone);
            llContactItem = view.findViewById(R.id.ll_contact_item);
        }

        public void bind(final Contact contact){
            tvName.setText(contact.getName());
            tvPhone.setText(contact.getPhone());

            llContactItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(contact);
                }
            });
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 3 parameter : layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_contact_item,
                        parent,
                        false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void updateData(List<Contact> newList){
        MyDiffUtil myDiffUtil = new MyDiffUtil(contactList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(myDiffUtil);
        contactList.clear();
        contactList.addAll(newList);

        result.dispatchUpdatesTo(this);
    }

    interface OnItemClick{
        void OnClick(Contact contact);
    }
}
