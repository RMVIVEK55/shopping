package trainingproject.tridentnets.com.shoppingtask.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import trainingproject.tridentnets.com.shoppingtask.SampleObjFragment;
import trainingproject.tridentnets.com.shoppingtask.model.ProductModel;


public class SampleCollectionPageAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ProductModel> arrayList = new ArrayList<>();
    private HashMap<String, String> tabLayoutHeader = new HashMap<>();
    private ArrayList<ProductModel> localArrayList;
    private ProductModel model = new ProductModel();
    private ArrayList<String> nameList;
    private String mTypeOfName, mName;
    Context cc;



    public SampleCollectionPageAdapter(FragmentManager fm, ArrayList<ProductModel> aList, Context c) {

        //public SampleCollectionPageAdapter(FragmentManager fm, ArrayList<ProductModel> aList, ArrayList<ProductModel> belt, ArrayList<ProductModel> w, Context c) {
        super(fm);
        arrayList = aList;
        cc=c;

        for (int i = 0; i < aList.size(); i++) {
            tabLayoutHeader.put(aList.get(i).getCate_name(), aList.get(i).getCate_name());
        }
        Set<String> keySet = tabLayoutHeader.keySet();
        nameList = new ArrayList<>(keySet);

    }


    @Override
    public Fragment getItem(int position) {
        localArrayList = new ArrayList<>();

        mName = nameList.get(position);
        for (int x = 0; x < arrayList.size(); x++) {
            mTypeOfName = arrayList.get(x).getCate_name();
            if (mName.equals(mTypeOfName)) {
                model = new ProductModel();
                model.setId(arrayList.get(x).getId());
                model.setName((arrayList.get(x).getName()));
                model.setDesc((arrayList.get(x).getDesc()));
                model.setPrice((arrayList.get(x).getPrice()));
                model.setOffer_Price((arrayList.get(x).getOffer_Price()));
                model.setIsprice((arrayList.get(x).getIsprice()));
                model.setCurrency((arrayList.get(x).getCurrency()));
                model.setBrand_name((arrayList.get(x).getBrand_name()));
                model.setCate_name((arrayList.get(x).getCate_name()));
                model.setPer((arrayList.get(x).getPer()));
                model.setImg((arrayList.get(x).getImg()));
                localArrayList.add(model);
            }
        }
        return SampleObjFragment.getInstanceViewList(localArrayList, position);
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return nameList.get(position);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);

    }
}
