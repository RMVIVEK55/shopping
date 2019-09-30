package trainingproject.tridentnets.com.shoppingtask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import trainingproject.tridentnets.com.shoppingtask.Adapter.ProductRecyclerAdapter;
import trainingproject.tridentnets.com.shoppingtask.model.ProductModel;

public class SampleObjFragment extends Fragment {

    public static SampleObjFragment getInstanceViewList(ArrayList<ProductModel> aList, int position) {
        Bundle bundle = new Bundle();
        SampleObjFragment objFragment = new SampleObjFragment();
        bundle.putInt("key", ++position);
        bundle.putParcelableArrayList("productList", aList);
        objFragment.setArguments(bundle);
        return objFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recycler_view_product, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recycle_priduct);
        recyclerView.setHasFixedSize(false);
        ArrayList<ProductModel> list = getArguments().getParcelableArrayList("productList");
        recyclerView.setAdapter(new ProductRecyclerAdapter(getContext(), list));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
