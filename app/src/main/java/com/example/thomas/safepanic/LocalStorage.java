package com.example.thomas.safepanic;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage
{
    Context context;
    SharedPreferences sharedPreferences;

    public LocalStorage(Context context)
    {
        this.context=context;
        sharedPreferences=context.getSharedPreferences("com.example.thomas.safepanic", Context.MODE_PRIVATE);
    }
    public void addStorage(String key, String value)
    {
        sharedPreferences.edit().putString(key, value).apply();
    }
    public String getStorage(String key)
    {
        return sharedPreferences.getString(key,null);
    }
}
