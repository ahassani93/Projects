package com.example.contactsproject;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactRoomDatabase extends RoomDatabase
{
    public abstract ContactDao contactDao();
    private static ContactRoomDatabase INSTANCE;

    static ContactRoomDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized(ContactRoomDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contacts").build();
                }
            }
        }
        return INSTANCE;
    }
}