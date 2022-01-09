package com.example.inventory.ui.preferences;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.inventory.MainActivity;
import com.example.inventory.R;

import java.lang.annotation.Native;

public class SettingsFragment extends PreferenceFragmentCompat implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {



    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
        if (pref.getKey().equals(getString(R.string.key_account))) {
            navController.navigate(R.id.action_settingsFragment_to_accountFragment);
        }
        return true;
    }
}