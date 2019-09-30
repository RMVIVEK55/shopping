package trainingproject.tridentnets.com.shoppingtask.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import trainingproject.tridentnets.com.shoppingtask.Fragment.CartFragment;
import trainingproject.tridentnets.com.shoppingtask.Fragment.FavouriteFragment;
import trainingproject.tridentnets.com.shoppingtask.Fragment.HomeFragment;
import trainingproject.tridentnets.com.shoppingtask.Fragment.ProductFragment;
import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.utils.AppPreference;
import trainingproject.tridentnets.com.shoppingtask.utils.Keys;

public class NavigationListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public ProgressDialog progress = null;
    FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private AlertDialog.Builder alertBuilder;
    private Handler handler;
    private DrawerLayout drawer;
    private Toolbar toolbar;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_list);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.tab));

        initView();

        progress.setMessage("loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.dismiss();
            }
        }, 200);

        //set fragment to layout
        initFragmentTranscation();
        Bundle bundlefromActivity = getIntent().getExtras();

        if (bundlefromActivity == null) {
            setFragment(new HomeFragment());

        } else {
            if (bundlefromActivity.getString(Keys.BUNDEL_Fragment_NAME) != null) {
                if (bundlefromActivity.getString(Keys.BUNDEL_Fragment_NAME).equals("new CartFragment()")) {
                    setFragment(new CartFragment());
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        fragmentManager.popBackStack();
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navi_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            toolbar.setTitle("Home");
            setFragment(new HomeFragment());
        } else if (id == R.id.nav_product) {
            toolbar.setTitle("Products");
            setFragment(new ProductFragment());
        } else if (id == R.id.nav_cart) {
            toolbar.setTitle("Cart");
            setFragment(new CartFragment());
        } else if (id == R.id.nav_favourite) {
            toolbar.setTitle("Favourite");
            setFragment(new FavouriteFragment());
        } else if (id == R.id.nav_setting) {
            toolbar.setTitle("Setting");
        } else if (id == R.id.nav_myaccount) {
            toolbar.setTitle("Myaccount");
            setFragment(new ProductFragment());
        } else if (id == R.id.nav_logout) {
            alertBuilder.setMessage("Are you sure logout");
            alertBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppPreference.getInstance(NavigationListActivity.this).putBoolean(AppPreference.BooleanKeys.LOGGED_IN, false);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            alertBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertBuilder.show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    void setFragment(Fragment strfragment) {
        //setting fragment to layout
        progress = new ProgressDialog(this);
        progress.setMessage("loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.dismiss();

            }
        }, 300);
        initFragmentTranscation();
        fragmentTransaction.replace(R.id.container, strfragment);
        fragmentTransaction.commit();

    }

    //intit fragmentmanager and fragmentTransaction
    private void initFragmentTranscation() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    /**
     * calling initializing view obj
     * get from intent user and mailid
     * set values header text username and emailid
     */
    private void initView() {
        alertBuilder = new AlertDialog.Builder(NavigationListActivity.this);
        handler = new Handler();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.toolbarbgcolor);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView txtMail = (TextView) header.findViewById(R.id.txt_navi_email);
        TextView txtDomain = header.findViewById(R.id.txt_header_name);

        // get from intent user and mailid
        // set values header text username and emailid
        String mailid = AppPreference.getInstance(NavigationListActivity.this).getString(AppPreference.StringKeys.MAILID);
        String domain = AppPreference.getInstance(NavigationListActivity.this).getString(AppPreference.StringKeys.DOMAIN);
        Log.d("domainnnnnnnnnnnn", domain);
        txtDomain.setText(domain);
        txtMail.setText(mailid);
        progress = new ProgressDialog(this);


    }

}
