package com.example.inventory;

import android.os.Bundle;

import com.example.inventory.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.content.toolbar);
        setContentView(binding.getRoot());
        //Para hacer que la flecha no actue como home
        //getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_sort_by_size);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //setUpNavigationView();

        //Mostrar los niveles de fragment mediante la flecha en la toolbar
        Set<Integer> topLevelDestination = new HashSet<>();
        topLevelDestination.add(R.id.action_dependencyList);
        topLevelDestination.add(R.id.action_inventory);
        topLevelDestination.add(R.id.action_about);
        topLevelDestination.add(R.id.action_settings);
        topLevelDestination.add(R.id.action_products);

        //Configurar la barra de accion para que funcionecon NAVIGATIONUI
        NavigationUI.setupWithNavController(binding.navigationView, navController);
        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestination).setOpenableLayout(binding.drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this,navController, appBarConfiguration);

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
        navController.navigate(R.id.action_settings);
        binding.navigationView.getCheckedItem().setChecked(false);
        binding.navigationView.setCheckedItem(R.id.action_settings);
    }

    private void showAboutUs() {
        navController.navigate(R.id.action_about);
        binding.navigationView.getCheckedItem().setChecked(false);
        binding.navigationView.setCheckedItem(R.id.action_aboutus);
    }

    private void showProducts() {
        navController.navigate(R.id.action_dependencyList);
    }

    private void showSections() {
        navController.navigate(R.id.action_dependencyList);
    }

    private void showDependencyList() {
        navController.navigate(R.id.action_dependencyList);
    }

    private void showAddInventory() {
        binding.navigationView.getCheckedItem().setChecked(false);
        binding.navigationView.setCheckedItem(R.id.action_inventory);
        navController.navigate(R.id.action_inventory);
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
            case R.id.action_search:
                //Opcion buscar
                Log.d("Opcion search", "Search pulsado");
                return true;
            case R.id.action_about:
                //Opcion about
                Log.d("Opcion about", "About pulsado");
                return true;
            case R.id.action_settings:
                //Opcion settings
                Log.d("Opcion settings", "Settings pulsado");
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

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}