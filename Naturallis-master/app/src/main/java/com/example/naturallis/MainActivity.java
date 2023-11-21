package com.example.naturallis;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.naturallis.Fragments.DietFragment;
import com.example.naturallis.Fragments.HomeFragment;
import com.example.naturallis.Fragments.MapFragment;
import com.example.naturallis.Fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import static java.util.Objects.requireNonNull;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    BottomNavigationView bottomNav;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requireNonNull(getSupportActionBar()).hide();

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(this);


        loadFragment(new HomeFragment());


    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.nav_home: {
                    Fragment homeFragment = HomeFragment.newInstance();
                    openFragment(homeFragment);
                }
                break;
                case R.id.nav_diet: {
                    Fragment dietFramgment = DietFragment.newInstance();
                    openFragment(dietFramgment);
                }
                break;
                case R.id.nav_map: {
                    Fragment mapFragment = MapFragment.newInstance();
                    openFragment(mapFragment);
                }
                break;
                case R.id.nav_user: {
                    Fragment userFragment = UserFragment.newInstance();
                    openFragment(userFragment);
                }
                break;
            }
            return true;
        }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.maps, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.maps, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}


