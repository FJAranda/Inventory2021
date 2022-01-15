package com.example.inventory;

import android.os.Bundle;

import com.example.inventory.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class MainActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.content.toolbar);
        setContentView(binding.getRoot());
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_sort_by_size);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        setUpNavigationView();
    }

    private void setUpNavigationView() {
        binding.navigationView.setCheckedItem(R.id.action_dependencyList);
        binding.navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_inventory:
                    showAddInventory();
                    break;
                case R.id.action_dependencyList:
                    showDependencyList();
                    break;
                case R.id.action_sections:
                    showSections();
                    break;
                case R.id.action_products:
                    showProducts();
                    break;
                case R.id.action_aboutus:
                    showAboutUs();
                    break;
                case R.id.action_settings:
                    showSettings();
                    break;
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void showSettings() {
        navController.navigate(R.id.settingsFragment);
        binding.navigationView.getCheckedItem().setChecked(false);
        binding.navigationView.setCheckedItem(R.id.action_settings);
    }

    private void showAboutUs() {
        navController.navigate(R.id.aboutFragment);
        binding.navigationView.getCheckedItem().setChecked(false);
        binding.navigationView.setCheckedItem(R.id.action_aboutus);
    }

    private void showProducts() {
        navController.navigate(R.id.dependencyListFragment);
    }

    private void showSections() {
        navController.navigate(R.id.dependencyListFragment);
    }

    private void showDependencyList() {
        navController.navigate(R.id.dependencyListFragment);
    }

    private void showAddInventory() {
        binding.navigationView.getCheckedItem().setChecked(false);
        binding.navigationView.setCheckedItem(R.id.action_inventory);
        navController.navigate(R.id.addInventoryFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                //Return false indica que no se ha pulsado ninguna opcion del menu de la activity, pero que se habrá pulsado alguno modificado.
                return false;
        }
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        if (pref.getKey().equals("Account")){
            navController.navigate(R.id.action_settingsFragment_to_accountFragment);
        }
        return true;
    }
}