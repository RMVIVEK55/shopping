package trainingproject.tridentnets.com.shoppingtask.activity;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

import trainingproject.tridentnets.com.shoppingtask.Adapter.IntroPageAdapter;
import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.utils.AppPreference;

public class IntroPagerActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private Button btnNavigateLogin;
    private int getItem;

    public static int sImg[] = {R.drawable.intro1, R.drawable.intro2, R.drawable.intro3, R.drawable.intro1};
    private CirclePageIndicator circlePageIndicator;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inrto_circle);

        initializingView();

        // calling checking app is opening first time
        isFirstTime();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("onPageScrolled pos", position + "");
            }

            @Override
            public void onPageSelected(int position) {
                if (position == sImg.length-1) {
                    btnNavigateLogin.setText(R.string.login);
                } else {
                    btnNavigateLogin.setText(R.string.ex_more);
                }
                // Toast.makeText(getApplicationContext(),Integer.toString(position),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void isFirstTime() {
        // checking app is opening first time
        if (AppPreference.getInstance(this).getBoolean(AppPreference.BooleanKeys.IS_SECOND_TIME)) {
            if (!AppPreference.getInstance(this).getBoolean(AppPreference.BooleanKeys.LOGGED_IN)) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, NavigationListActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            //    AppPreference.getInstance(this).putBoolean(AppPreference.BooleanKeys.IS_SECOND_TIME, true);
            generateSampleNotification();
            settingPagerAdapter();
            settingPagerIndicator();

        }
    }

    private void settingPagerAdapter() {

        IntroPageAdapter introPageAdapter = new IntroPageAdapter(getSupportFragmentManager(), sImg);
        viewPager.setAdapter(introPageAdapter);
        circlePageIndicator.setViewPager(viewPager);
        getItem = viewPager.getCurrentItem();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getItem == 3)
                    getItem = 0;
                viewPager.setCurrentItem(getItem++, true);
            }
        }, 200);
//        Runnable runnable=new Runnable() {
//            @Override
//            public void run() {
//if(getItem==3)
//            }
//        };
//        Timer time=new Timer();
//        time.scheduleAtFixedRate(new ViewPagerTimer(),5000,2000);
//        viewPager.setCurrentItem(getItem++,true);
    }

    private void generateSampleNotification() {
        NotificationCompat.Builder welcome = new NotificationCompat.Builder(this);
        welcome.setContentTitle("Login");
        //Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        Intent i = new Intent(IntroPagerActivity.this, LoginActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        welcome.setContentIntent(pendingIntent);
        welcome.setSmallIcon(R.drawable.app_logo);
//
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, welcome.build());
    }

    private void initializingView() {
        //creating collection view obj and initializing view
        btnNavigateLogin = (Button) findViewById(R.id.btn_login);
        viewPager = (ViewPager) findViewById(R.id.viewpager_intro);
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.vp_indicator_circle);
        Typeface typeface_maven_pro = Typeface.createFromAsset(getAssets(), "MavenPro-Regular.ttf");
        btnNavigateLogin.setTypeface(typeface_maven_pro);
        btnNavigateLogin.setOnClickListener(this);
    }


    //  method for setting indicator

    private void settingPagerIndicator() {
        final float density = getResources().getDisplayMetrics().density;
//        circlePageIndicator.setFillColor(getResources().getColor(R.color.colorAccent));
//        circlePageIndicator.setPageColor(getResources().getColor(R.color.colorPrimary));
        circlePageIndicator.setPadding(4, 4, 4, 4);
        // sets the radius(size) of the indicator
        circlePageIndicator.setRadius(4 * density);
    }


    @Override
    public void onClick(View view) {
        int v = view.getId();
        switch (v) {
            case R.id.btn_login:
                Intent i = new Intent(IntroPagerActivity.this, LoginActivity.class);
                startActivity(i);
                break;
        }

    }
//    private class ViewPagerTimer extends TimerTask{
//
//        @Override
//        public void run() {
//            IntroPagerActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if(viewPager.getCurrentItem()==0)
//                        viewPager.setCurrentItem(1);
//                    else if(viewPager.getCurrentItem()==1)
//                        viewPager.setCurrentItem(2);
//                    else if(viewPager.getCurrentItem()==2)
//                        viewPager.setCurrentItem(3);
//
//                    else
//                     viewPager.setCurrentItem(0);
//                    Log.d("viewgetCurrentItem()",viewPager.getCurrentItem()+"");
//                }
//            });
//        }
//    }
}

