package com.example.delta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.badge.ExperimentalBadgeUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class DeltaActivity extends AppCompatActivity {
    protected BottomNavigationView navBar;
    protected List<Integer> cartThumbnails, savedThumbnails;
    protected List<String> cartNames, savedNames;
    protected List<Double> cartPrices, savedPrices;
    private Fragment currentFragment, home, search, saved, cart, settings, checkout;
    private BadgeDrawable navCartBadge, cartBadge;
    protected int numCartItems, numSavedItems, thumbnail;
    protected String name;
    protected double price;
    protected FragmentManager fm;
    private MaterialButton cartIcon;
    final static String MYDEBUG = "MYDEBUG";
    protected final double DISCOUNT = 10;

    /* PRODUCT DETAILS */
    @ExperimentalBadgeUtils
    public void clickProductDetails(View view){
        if (view == findViewById(R.id.product_back)) findViewById(R.id.product_details).setVisibility(View.GONE);
        else if (view == findViewById(R.id.product_save)){
            if (numSavedItems == 0){
                saved = new SavedFragment();
                savedThumbnails = new ArrayList<>(); savedNames = new ArrayList<>(); savedPrices = new ArrayList<>();
            }
            numSavedItems++;
            savedThumbnails.add(thumbnail); savedNames.add(name); savedPrices.add(price);

            Toast.makeText(this, "Item saved", Toast.LENGTH_SHORT).show();
        } else{
            if (numCartItems == 0){
                cart = new CartFragment();
                cartThumbnails = new ArrayList<>(); cartNames = new ArrayList<>(); cartPrices = new ArrayList<>();

                cartIcon = findViewById(R.id.product_cart_icon);
                cartBadge = BadgeDrawable.create(DeltaActivity.this);
                cartBadge.setHorizontalOffset(50); cartBadge.setVerticalOffset(50);
                navCartBadge = navBar.getOrCreateBadge(navBar.getMenu().getItem(2).getItemId());
                BadgeUtils.attachBadgeDrawable(cartBadge, cartIcon, findViewById(R.id.product_cart));
            }

            numCartItems++; Log.d(MYDEBUG, "add to cart -> " + numCartItems);
            cartThumbnails.add(thumbnail); cartNames.add(name); cartPrices.add(price);
            cartBadge.setNumber(numCartItems);
            navCartBadge.setNumber(numCartItems);
            findViewById(R.id.product_details).setVisibility(View.GONE);
        }
    }

    public void clickCart(View view){
        if (view == findViewById(R.id.empty_cart_button)){ Log.d(MYDEBUG, "empty cart -> home");
            currentFragment = home;
            navBar.setSelectedItemId(navBar.getMenu().getItem(0).getItemId());
            fm.beginTransaction().replace(R.id.main_fragment, currentFragment).commit();
        }
        else if (view == findViewById(R.id.add_cart_button)){ Log.d(MYDEBUG, "cart -> checkout");
            checkout = new CheckoutFragment();
            fm.beginTransaction()
                    .addToBackStack("cart")
                    .setReorderingAllowed(true)
                    .replace(R.id.main_fragment, checkout).commit();
            navBar.setVisibility(View.GONE);
        }
        else if (view == findViewById(R.id.complete_checkout_button)){ Log.d(MYDEBUG, "checkout complete purchase -> success screen");
            numCartItems=0;
            navBar.removeBadge(navBar.getMenu().getItem(2).getItemId());
            findViewById(R.id.purchase_success_screen).setVisibility(View.VISIBLE);
        }
        else { Log.d(MYDEBUG, "success screen -> empty cart");
            cart = new EmptyCartFragment();
            fm.beginTransaction().replace(R.id.main_fragment, cart).remove(checkout).commit();
            findViewById(R.id.purchase_success_screen).setVisibility(View.GONE);
            navBar.setVisibility(View.VISIBLE);
        }
    }

    public void clickDisabledFunction(View view){
        Toast.makeText(this, "Functionality disabled for demo", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(MYDEBUG, "BEGIN");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        navBar = (BottomNavigationView) findViewById(R.id.nav_bar);
        numCartItems = 0; numSavedItems = 0;

        home = new HomeFragment();
        //search = new SearchFragment();
        saved = new EmptySavedFragment();
        cart = new EmptyCartFragment();
        settings = new SettingsFragment();

        fm = getSupportFragmentManager();
        currentFragment = home;
        fm.beginTransaction().replace(R.id.main_fragment, currentFragment).commit();

        navBar.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) currentFragment = home;
            else if (id == R.id.nav_saved) currentFragment = saved;
            else if (id == R.id.nav_cart) currentFragment = cart;
            else if (id == R.id.nav_settings) currentFragment = settings;

            fm.beginTransaction().replace(R.id.main_fragment, currentFragment).commit();
            return true;
        });

        //@Override
        navBar.setOnItemReselectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home){
                if (currentFragment == home) home.getView().findViewById(R.id.body).setScrollY(0);
                else {
                    currentFragment = home;
                    fm.beginTransaction().replace(R.id.main_fragment, currentFragment).commit();
                }
            }
            //else if (id == R.id.nav_saved) Log.d(MYDEBUG, "no new saved");
            else if (id == R.id.nav_cart) cart.getView().findViewById(R.id.body).setScrollY(0);
            else if (id == R.id.nav_settings) settings.getView().findViewById(R.id.body).setScrollY(0);
        });
    }

    // Change to the new fragment: The point of this method is so that it can be called from other fragments
    public void changeFragment(Class<? extends Fragment> fragmentClass, Bundle parameters) {
        Log.d(MYDEBUG, "changeFragment: " + fragmentClass.getSimpleName());
        Fragment fragment = null;
        try {
            fragment = fragmentClass.newInstance();
            fragment.setArguments(parameters);
        } catch (Exception e) { e.printStackTrace(); }

        fm.beginTransaction()
                .addToBackStack("home")
                .setReorderingAllowed(true)
                .replace(R.id.main_fragment, fragment).commit();
    }
}