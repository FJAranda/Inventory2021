package com.example.inventory.ui.preferences;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.inventory.R;

public class AccountFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.account_preferences, rootKey);
    }
}