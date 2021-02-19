package com.example.sign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_Name = "Session";
    String SESSION_KEY = "Session_User";

    @SuppressLint("CommitPrefEdits")
    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_Name, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user){
        int i = user.getId();

        editor.putInt(SESSION_KEY, i).commit();
    }

    public int getSession(){
        return sharedPreferences.getInt(SESSION_KEY, -1);
        //return -1;
    }

    public void removeSession(){
        editor.putInt(SESSION_KEY, -1).commit();
    }
}
