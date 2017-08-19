package com.example.luisfrancisco.sinfilarregional;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by LUISFRANCISCO on 18/08/2017.
 */

public class FireApp extends Application {
    public void onCreate()
    {
        super.onCreate();
        if(FirebaseApp.getApps(this).isEmpty())
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
