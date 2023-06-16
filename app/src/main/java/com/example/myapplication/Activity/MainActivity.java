package com.example.myapplication.Activity;

//import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.Fragment.PersonFragment;
import com.example.myapplication.Fragment.SearchFragment;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    Fragment homeFragment;
    Toolbar toolbar;
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing id.
    public static final String IDUSER_KEY = "iduser_key";
    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    public static int id_user;
    public static SharedPreferences sharedpreferences;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        homeFragment = new HomeFragment();
        loadFragment(homeFragment);


        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        id_user = sharedpreferences.getInt(IDUSER_KEY, 0);



        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.home_menu:
                    loadFragment(new HomeFragment());

                    break;
                case R.id.search_menu:
                    loadFragment(new SearchFragment());
                    break;
                case R.id.person_menu:
                    loadFragment(new PersonFragment());


                    break;
            }

            return true;
        });

//        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.home_menu);
//        badgeDrawable.setNumber(7);
//        badgeDrawable.setVisible(true);
    }

    private void loadFragment(Fragment homeFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, homeFragment);
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_logout){

            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            finish();

        } else if (id == R.id.menu_cart) {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        checklogin();

    }

    @Override
    protected void onResume() {
        checklogin();
        super.onResume();
    }

    @Override
    protected void onStop() {
        checklogin();
        super.onStop();
    }

    public void checklogin(){
        if (id_user == 0 ) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}