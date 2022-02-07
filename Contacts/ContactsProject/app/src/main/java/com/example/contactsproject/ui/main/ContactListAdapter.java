package com.example.contactsproject.ui.main;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.contactsproject.R;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactsproject.Contact;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder>
{
    private int contactItemLayout;
    private List<Contact> contactList;

    public ContactListAdapter(int layoutId)
    {
        contactItemLayout = layoutId;
    }

    public void setContactList(List<Contact> contacts)
    {
        contactList = contacts;
        notifyDataSetChanged();
    }

    public List<Contact> getCList()
    {
        return contactList;
    }

    @Override
    public int getItemCount()
    {
        return contactList == null ? 0 : contactList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(contactItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition)
    {
        TextView nameOnCard = holder.nameOnCard;
        TextView phoneOnCard = holder.phoneOnCard;
        ImageView trashCanIV = holder.trashCanIV;

        int trashCanImageResource = R.drawable.trash_can;

        nameOnCard.setText(contactList.get(listPosition).getName());
        phoneOnCard.setText(contactList.get(listPosition).getPhone());
        trashCanIV.setImageResource(trashCanImageResource);
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        Application application;
        MainViewModel mViewModel;
        TextView nameOnCard;
        TextView phoneOnCard;
        ImageView trashCanIV;

        ViewHolder(View itemView)
        {
            super(itemView);
            nameOnCard = itemView.findViewById(R.id.nameOnCard);
            phoneOnCard = itemView.findViewById(R.id.phoneOnCard);
            trashCanIV = itemView.findViewById(R.id.trashCanIV);

            trashCanIV.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    mViewModel = new MainViewModel(application);

                    int position = getAdapterPosition();

                    mViewModel.deleteContact(contactList.get(position).getName());
                }
            });
        }
    }
}