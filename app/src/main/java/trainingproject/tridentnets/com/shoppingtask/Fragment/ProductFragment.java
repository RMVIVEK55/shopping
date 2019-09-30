package trainingproject.tridentnets.com.shoppingtask.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import trainingproject.tridentnets.com.shoppingtask.Adapter.SampleCollectionPageAdapter;
import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.model.ProductModel;

//layout name recycler_view_product.xmlst_item_productt.xml

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    private ArrayList<ProductModel> productList;

    private int[] mImg = {R.drawable.pik1, R.drawable.pik2, R.drawable.pik3, R.drawable.pik2, R.drawable.pik4, R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5,
            R.drawable.pik1, R.drawable.pik2, R.drawable.pik3, R.drawable.pik2, R.drawable.pik4, R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5,
            R.drawable.pik1, R.drawable.pik2, R.drawable.pik3, R.drawable.pik2, R.drawable.pik4, R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5,
            R.drawable.pik1, R.drawable.pik2, R.drawable.pik3, R.drawable.pik2, R.drawable.pik4, R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5,
            R.drawable.pik1, R.drawable.pik2, R.drawable.pik3, R.drawable.pik2, R.drawable.pik4, R.drawable.w1, R.drawable.w2, R.drawable.w3};

    private ProductModel model;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getJson();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        SampleCollectionPageAdapter pageAdapterObj = new SampleCollectionPageAdapter(getFragmentManager(), productList, getActivity());
//        SampleCollectionPageAdapter pageAdapterObj = new SampleCollectionPageAdapter(getFragmentManager(), productList, beltList, watchList, getActivity());
        ViewPager pager = view.findViewById(R.id.pager);
        TabLayout tab = view.findViewById(R.id.tab);
        tab.setBackgroundColor(Color.parseColor("#2C3039"));
        tab.setSelectedTabIndicatorHeight(10);
        tab.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.tabunderlinecolor));
        tab.setTabTextColors(ContextCompat.getColor(getContext(), R.color.tabunselect), ContextCompat.getColor(getContext(), R.color.tabselect));
        pager.setOffscreenPageLimit(3);
        pager.setPageMargin(-15);
        pager.setAdapter(pageAdapterObj);
        tab.setupWithViewPager(pager);
    }

    private void getJson() {
        try {
            InputStream ip = getActivity().getAssets().open("products.json");
            byte b[] = new byte[ip.available()];
            ip.read(b);
            ip.close();
            String jsonStr = new String(b, "UTF-8");
            JSONObject mainJsonObject = new JSONObject(jsonStr);
            JSONArray mainJsonArray = mainJsonObject.getJSONArray("products");
            Log.d("main obj size", "main obj" + mainJsonArray.length());
            productList = new ArrayList<>();
            // add over all list
            for (int i = 0; i < mainJsonArray.length(); i++) {
                model = new ProductModel();
                JSONObject subJsonObject = mainJsonArray.getJSONObject(i);
                model.setId(subJsonObject.getString("id"));
                model.setName(subJsonObject.getString("Name"));
                model.setDesc(subJsonObject.getString("Description"));
                String price = subJsonObject.getString("Price");
                model.setPrice(price);
                model.setCurrency(subJsonObject.getString("priceCurrency"));
                JSONObject offJsonObj = subJsonObject.getJSONObject("offers");
                model.setIsprice(offJsonObj.getString("isPrice"));
                String bool = offJsonObj.getString("isPrice");
                if (bool.equals("true")) {
                    model.setOffer_Price(offJsonObj.getString("price"));
                } else {
                    int mGetPrice = Integer.parseInt(price);
                    int mGetPercent = Integer.parseInt(offJsonObj.getString("percentage"));
                    int mPercent = (mGetPrice * mGetPercent) / 100;
                    int total = mGetPrice - mPercent;
                    model.setPer(Long.toString(total));
                }
                JSONObject cateJsonObj = subJsonObject.getJSONObject("Category");
                model.setBrand_name(cateJsonObj.getString("brand_name"));
                String name = cateJsonObj.getString("name");
                model.setCate_name(name);
                Log.d("productList.add(name)", "" + cateJsonObj.getString("name"));
                model.setImg(mImg[i]);
                productList.add(model);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
