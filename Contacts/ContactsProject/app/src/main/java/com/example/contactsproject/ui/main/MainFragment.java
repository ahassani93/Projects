package com.example.contactsproject.ui.main;

import android.os.Bundle;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProviders;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.Observer;
import com.example.contactsproject.MainActivity;
import com.example.contactsproject.R;
import com.example.contactsproject.Contact;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainFragment extends Fragment
{
    private MainViewModel mViewModel;
    private ContactListAdapter adapter;
    private EditText contactName;
    private EditText contactPhone;

    public static MainFragment newInstance()
    {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        contactName = getView().findViewById(R.id.contactNameET);
        contactPhone = getView().findViewById(R.id.contactPhoneET);

        observerSetup();
        recyclerSetup();

    }

    private void observerSetup()
    {

        mViewModel.getAllContacts().observe(this, new Observer<List<Contact>>()
        {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts)
            {
                adapter.setContactList(contacts);
            }
        });

        mViewModel.getSearchResults().observe(this, new Observer<List<Contact>>()
        {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts)
            {
                if(contacts.size() > 0)
                {
                    adapter.setContactList(contacts);
                }
                else
                {
                    mViewModel.findContact("");
                    ((MainActivity) getActivity()).showToast("No matches found!");
                }
            }
        });
    }

    private void recyclerSetup()
    {
        RecyclerView recyclerView;
        adapter = new ContactListAdapter(R.layout.contact_card_layout);
        recyclerView = getView().findViewById(R.id.contact_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void clearFields()
    {
        contactName.setText("");
        contactPhone.setText("");
    }

    public void showAllC()
    {
        mViewModel.findContact("");
        clearFields();
    }

    public void addC()
    {
        String name = contactName.getText().toString();
        String phone = contactPhone.getText().toString();

        if(!name.equals("") && !phone.equals(""))
        {
            Contact contact = new Contact(name, phone);
            mViewModel.insertContact(contact);
            clearFields();
        }
        else
        {
            ((MainActivity) getActivity()).showToast("You must enter a name and phone number!");
        }
    }

    public void findC()
    {
        String name = contactName.getText().toString();

        if(!name.equals(""))
        {
            mViewModel.findContact(name);
        }
        else
        {
            ((MainActivity) getActivity()).showToast("You must enter a name to search for!");
        }
    }

    public void deleteC()
    {
        String name = contactName.getText().toString();
        mViewModel.deleteContact(name);
        clearFields();
    }

    public void sortCAtoZ()
    {
        Collections.sort(adapter.getCList(), new Comparator<Contact>()
        {
            public int compare(Contact obj1, Contact obj2)
            {
                return obj1.getName().compareToIgnoreCase(obj2.getName());
            }
        });

        adapter.notifyDataSetChanged();
        clearFields();
    }

    public void sortCZtoA()
    {
        Collections.sort(adapter.getCList(), new Comparator<Contact>()
        {
            public int compare(Contact obj1, Contact obj2)
            {
                return obj2.getName().compareToIgnoreCase(obj1.getName());
            }
        });

        adapter.notifyDataSetChanged();
        clearFields();
    }
}