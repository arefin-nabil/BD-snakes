package com.example.sbdfinal;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sbdfinal.homefragments.ArticleFragment;
import com.example.sbdfinal.homefragments.FaqFragment;
import com.example.sbdfinal.homefragments.HomeFragment;
import com.example.sbdfinal.homefragments.QuizFragment;
import com.example.sbdfinal.homefragments.RescuerFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerid;
    MaterialToolbar Mtoolbarid;
    TextView tooltitel;
    BottomNavigationView bottomnav;
    NavigationView navigationid;

    long backPressedTime;
    Toast backPressedToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //====================== ID will define here ========================

        tooltitel = findViewById(R.id.tooltitel);
        Mtoolbarid = findViewById(R.id.Mtoolbarid);
        drawerid = findViewById(R.id.drawerid);
        bottomnav = findViewById(R.id.bottomnav);
        navigationid = findViewById(R.id.navigationid);

        //====================== ID will define here ========================

        //_________________GDPR_______________________

        //_________________GDPR_______________________

        // Setup ActionBarDrawerToggle
        setSupportActionBar(Mtoolbarid);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                HomeActivity.this, drawerid, Mtoolbarid, R.string.open, R.string.close);
        drawerid.addDrawerListener(toggle);
        //built in menu icon
        toggle.syncState();
        boolean isDarkMode = (this.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
        if (isDarkMode) {
            toggle.getDrawerArrowDrawable().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        }else {
            toggle.getDrawerArrowDrawable().setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_ATOP);
        }
        //---------------------------------Toolbar Finish-----------------------------------

        //---------- Marquee Text for ToolBar -----------
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);
        //---------- Marquee Text for ToolBar ENDS -----------


        //=================Home page=================
        if (savedInstanceState == null) {
            loadHomeFragment();
        }
        //=================Home page=================



        //==================== Bottom Navigation =================
        bottomnav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.framelayout);

                // Begin a transaction only if we're not already on the target fragment
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (menuItem.getItemId() == R.id.home) {
                    if (!(currentFragment instanceof HomeFragment)) {
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.replace(R.id.framelayout, new HomeFragment());
                        fragmentTransaction.commit();
                    }
                }
                else if (menuItem.getItemId() == R.id.quiz) {
                    if (!(currentFragment instanceof QuizFragment)) {
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.replace(R.id.framelayout, new QuizFragment());
                        fragmentTransaction.commit();
                    }
                }
                else if (menuItem.getItemId() == R.id.rescuerlist) {
                    if (!(currentFragment instanceof RescuerFragment)) {
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.replace(R.id.framelayout, new RescuerFragment());
                        fragmentTransaction.commit();
                    }
                }
                else if (menuItem.getItemId() == R.id.faquestion) {
                    if (!(currentFragment instanceof FaqFragment)) {
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.replace(R.id.framelayout, new FaqFragment());
                        fragmentTransaction.commit();
                    }
                }
                else if (menuItem.getItemId() == R.id.article) {
                    if (!(currentFragment instanceof ArticleFragment)) {
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.replace(R.id.framelayout, new ArticleFragment());
                        fragmentTransaction.commit();

                    }
                }

                return true;
            }
        });
        //==================== Bottom Navigation =================





        //=========================== Navigation Drawer ===========================
        navigationid.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.framelayout);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (menuItem.getItemId() == R.id.home) {
                    if (!(currentFragment instanceof HomeFragment)) {
                        fragmentTransaction.replace(R.id.framelayout, new HomeFragment());
                        fragmentTransaction.commit();
                        // Update Bottom Navigation item
                        bottomnav.setSelectedItemId(R.id.home);  // Select home item in bottom navigation
                    }

                } else if (menuItem.getItemId() == R.id.faquestion) {
                    if (!(currentFragment instanceof FaqFragment)) {
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.replace(R.id.framelayout, new FaqFragment());
                        fragmentTransaction.commit();
                        // Update Bottom Navigation item
                        bottomnav.setSelectedItemId(R.id.faquestion);  // Select FAQ item in bottom navigation
                    }


                } else if (menuItem.getItemId() == R.id.article) {
                    if (!(currentFragment instanceof ArticleFragment)) {
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.replace(R.id.framelayout, new ArticleFragment());
                        fragmentTransaction.commit();
                        // Update Bottom Navigation item
                        bottomnav.setSelectedItemId(R.id.article);  // Select article item in bottom navigation
                    }

                } else if (menuItem.getItemId() == R.id.shareapp) {
                    shareApp();

                }else if (menuItem.getItemId() == R.id.aboutus) {
                    Intent intent = new Intent(HomeActivity.this, AboutAppActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                } else if (menuItem.getItemId() == R.id.exitapp) {
                    showExitDialog();

                }else if (menuItem.getItemId() == R.id.contactus) {
                    showContactUsDialog();

                }else if (menuItem.getItemId() == R.id.privacypolicy) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/nabilprivacypolicy/home"));
                    startActivity(intent);
                }else if (menuItem.getItemId() == R.id.wildlifeact) {
                    showWildlifeActDialog();
                }




                // Close drawer after selection
                drawerid.closeDrawer(GravityCompat.START);

                return false;
            }
        });
        //=========================== Navigation Drawer ===========================





        //---------- Back Button -----------
        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                // Check if the navigation drawer is open
                if (drawerid.isDrawerOpen(GravityCompat.START)) {
                    // If the drawer is open, close it
                    drawerid.closeDrawer(GravityCompat.START);
                } else {
                    // If the drawer is closed, handle back press logic
                    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.framelayout);

                    if (currentFragment instanceof HomeFragment) {  // Ensure the fragment name starts with uppercase
                        // If the user is already on the HomeFragment, show a Toast and exit on second back press
                        if (backPressedTime + 2000 > System.currentTimeMillis()) {
                            // Exit the app completely
                            finish();  // This will close the activity
                            return;
                        } else {
                            // Show Toast when back button is pressed the first time
                            backPressedToast = Toast.makeText(HomeActivity.this, "Press again to exit", Toast.LENGTH_SHORT);
                            backPressedToast.show();
                        }

                        backPressedTime = System.currentTimeMillis();
                    } else {
                        // If not on the HomeFragment, go back to the HomeFragment
                        loadHomeFragment();

                        // Update the BottomNavigationView icon to reflect the HomeFragment selection
                        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnav);
                        bottomNavigationView.setSelectedItemId(R.id.home); // Use the ID of your home item in the BottomNavigationView
                    }
                }

            }
        });
        //---------- Back Button -----------

    }//// ============ finish oncreate method ============

    //====================== Access about us from ToolBar Menu ========================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu from the XML file
        getMenuInflater().inflate(R.menu.toolmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.applogo) {
            Intent intent = new Intent(HomeActivity.this, AboutAppActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
    //====================== Access about us from ToolBar Menu ========================


    //======================== call Home Fragment ========================
    private void loadHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Ensure you use the correct class name with uppercase (HomeFragment)
        HomeFragment homeFragment = new HomeFragment();

        // Replace the current fragment with HomeFragment
        fragmentTransaction.replace(R.id.framelayout, homeFragment);
        fragmentTransaction.addToBackStack(null);  // Optionally add to back stack
        fragmentTransaction.commit();
    }
    //======================== call Home Fragment ========================




    //====================== App Share function ========================
    private void shareApp() {
        // The URL of your app on the Google Play Store
        String appPackageName = getPackageName(); // This gets your app's package name dynamically
        String appLink = "https://play.google.com/store/apps/details?id=" + appPackageName;

        // Create an Intent to share the app
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this amazing app!");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, check out this app: " + appLink);

        // Use the chooser to allow the user to choose their preferred app to share with
        Intent chooser = Intent.createChooser(shareIntent, "Share app via");
        startActivity(chooser);
    }
    //====================== App Share function ends ========================


    //====================== App Exit Alert Dialog ========================
    private void showExitDialog() {
        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        android.view.View customView = inflater.inflate(R.layout.exitdialog, null);

        AppCompatButton dialogButtonYes = customView.findViewById(R.id.yesbtn);
        AppCompatButton dialogButtonNo = customView.findViewById(R.id.nobtn);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(customView);
        AlertDialog dialog = builder.create();

        // background transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        // Disable dismiss on outside touch and back button
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);


        // dialog btn workable
        dialogButtonYes.setOnClickListener(v -> finishAffinity());
        dialogButtonNo.setOnClickListener(v -> dialog.dismiss());


        dialog.show();
    }
    //====================== App Exit Alert Dialog ========================

    //====================== contact us Alert Dialog ========================
    private void showContactUsDialog() {
        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        android.view.View customView = inflater.inflate(R.layout.contactusdialog, null);

        Button emailbutton = customView.findViewById(R.id.emailbutton);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(customView);
        AlertDialog dialog = builder.create();

        // background transparent
        // Set background transparent
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        // dialog btn workable
        emailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open email app with predefined email
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"arefinnabil256@gmail.com"}); // Recipient
                intent.putExtra(Intent.EXTRA_SUBJECT, "Default Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Hello, this is a default message body.");

                try {
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Handle the case where Gmail is not installed
                    Intent fallbackIntent = new Intent(Intent.ACTION_SENDTO);
                    fallbackIntent.setData(Uri.parse("mailto:arefinnabil256@gmail.com"));
                    startActivity(Intent.createChooser(fallbackIntent, "Send Email"));
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    //====================== contact us Alert Dialog ========================

    //====================== contact us Alert Dialog ========================
    private void showWildlifeActDialog() {
        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        android.view.View customView = inflater.inflate(R.layout.wildlifeactdialog, null);

        Button closebutton = customView.findViewById(R.id.closebutton);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(customView);
        AlertDialog dialog = builder.create();

        // background transparent
        // Set background transparent
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        // dialog btn workable
        closebutton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mccibd.org/wp-content/uploads/2021/09/Wildlife-Conservation-and-Security-Act-2012.pdf"));
            startActivity(intent);
            dialog.dismiss();

        });

        dialog.show();
    }
    //====================== contact us Alert Dialog ========================

}

