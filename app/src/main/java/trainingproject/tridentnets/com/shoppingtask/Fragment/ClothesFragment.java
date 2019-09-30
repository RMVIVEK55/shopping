//package trainingproject.tridentnets.com.shoppingtask.Fragment;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//
//import trainingproject.tridentnets.com.shoppingtask.Adapter.ClothesRecyclerlist;
//import trainingproject.tridentnets.com.shoppingtask.R;
//import trainingproject.tridentnets.com.shoppingtask.model.ProductModel;
//
///**
// * Created by Admin on 11/21/2017.
// */
//
//public class ClothesFragment extends Fragment {
//    private RecyclerView recyclerViewclothes;
//    private ArrayList<ProductModel> arrayList;
//
//    ClothesFragment(ArrayList a) {
//        arrayList = a;
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_recycler_view_product, container, false);
//        recyclerViewclothes = (RecyclerView) root.findViewById(R.id.recycle_priduct);
//        recyclerViewclothes.setHasFixedSize(true);
//        recyclerViewclothes.setAdapter(new ClothesRecyclerlist(arrayList));
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//        recyclerViewclothes.setLayoutManager(gridLayoutManager);
//
//
//        return root;
//    }
//
//
//}
//
