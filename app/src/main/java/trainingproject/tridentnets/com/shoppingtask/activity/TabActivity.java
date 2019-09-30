//package trainingproject.tridentnets.com.shoppingtask.Activity;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import trainingproject.tridentnets.com.shoppingtask.R;
//
//public class TabActivity extends AppCompatActivity {
//    private FloatingActionButton fab;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_intro);
//        initview();
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    private void initview() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        SelectionAdpter adpterObj = new SelectionAdpter(getSupportFragmentManager());
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        viewPager.setAdapter(adpterObj);
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
//        tabLayout.setupWithViewPager(viewPager);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
//        fab = (FloatingActionButton) findViewById(R.id.fab);
//    }
//
//
//  private class SelectionAdpter extends FragmentPagerAdapter {
//
//       private SelectionAdpter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return ViewpageAdapter.instance(position);
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//    }
//
//    static class ViewpageAdapter extends Fragment {
//        ViewpageAdapter() {
//        }
//
//        static ViewpageAdapter instance(int i) {
//            ViewpageAdapter obj = new ViewpageAdapter();
//            Bundle bundle = new Bundle();
//            bundle.putInt("KEY", i);
//            obj.setArguments(bundle);
//            return obj;
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            View view = inflater.inflate(R.layout.singletext, container, false);
//            TextView displaytext = view.findViewById(R.id.tv_singletext);
//            displaytext.setText("TabLAyout");
//            return view;
//        }
//    }
//}
