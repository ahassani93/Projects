package com.example.contactsproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.contactsproject.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity
{
    MainFragment mf = MainFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mf).commitNow();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_show_all_contacts:
                mf.showAllC();
                return true;
            case R.id.menu_add_contact:
                mf.addC();
                return true;
            case R.id.menu_find_contact:
                mf.findC();
                return true;
            case R.id.menu_sort_contacts_a_z:
                mf.sortCAtoZ();
                return true;
            case R.id.menu_sort_contacts_z_a:
                mf.sortCZtoA();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showToast(String message)
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));
        ImageView toastImage = (ImageView) layout.findViewById(R.id.toast_image);
        toastImage.setImageResource(R.drawable.ic_toast_error);
        TextView toastText = (TextView) layout.findViewById(R.id.toast_text);
        toastText.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}