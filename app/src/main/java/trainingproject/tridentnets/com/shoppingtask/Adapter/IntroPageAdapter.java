package trainingproject.tridentnets.com.shoppingtask.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import trainingproject.tridentnets.com.shoppingtask.Fragment.SetOpenScreenImageFragment;

public class IntroPageAdapter extends FragmentPagerAdapter  {

    private int mImg[];

    public IntroPageAdapter(FragmentManager fm, int[] localImg) {
        super(fm);
        mImg = localImg;
    }

    @Override
    public Fragment getItem(int pos) {
        return SetOpenScreenImageFragment.getInstance(pos);
    }

    @Override
    public int getCount() {
        return mImg.length;

    }


}

