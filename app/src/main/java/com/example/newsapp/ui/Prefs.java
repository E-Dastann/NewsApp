package com.example.newsapp.ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;

public class Prefs {
    private SharedPreferences preferences;


    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", MODE_PRIVATE);

    }

    public void saveBoardState() {
        preferences.edit().putBoolean("isBoardShown", true).apply();
    }

    public boolean isBoardShown() {
        return preferences.getBoolean("isBoardShown", false);
    }

    public void saveImageUri(Uri string) {
        preferences.edit().putString("avatar", String.valueOf(string)).apply();
    }


    public Uri getImageUri() {
        return Uri.parse(preferences.getString("avatar", ""));
    }

    public void editSave(String string) {
        preferences.edit().putString("puttext", string).apply();
    }

    public String getEdit() {
        return preferences.getString("puttext", "");
    }

    public void clearCash() {
        preferences.edit().remove("avatar").apply();
        preferences.edit().remove("puttext").apply();

    }
}
