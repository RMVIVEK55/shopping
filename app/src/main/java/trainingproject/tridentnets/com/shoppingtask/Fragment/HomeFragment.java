package trainingproject.tridentnets.com.shoppingtask.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import trainingproject.tridentnets.com.shoppingtask.Adapter.HomeRecycleviewAdapter;
import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;
import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.model.HomeModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private SqliteDBHelper dbHelper;
    private JSONArray jsonArray = null;
    private int img[] = {R.drawable.slidertea, R.drawable.slider3, R.drawable.slider2, R.drawable.slider3};
    private String mHomeName, mHomeSub;
    private ArrayList<HomeModel> homeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view_home, container, false);
        getJson();

        initView(view);


        return view;


    }

    private void getJson() {
        //assing values from json
        try {
            dbHelper = new SqliteDBHelper(getActivity());
            InputStream is = getActivity().getAssets().open("category");
            int size = is.available();
            Log.d("size", Integer.toString(size));
            byte[] bytedata = new byte[size];
            is.read(bytedata);
            is.close();
            String jsonvalue = new String(bytedata, "UTF-8");
            JSONObject jsonobj = new JSONObject(jsonvalue);
            jsonArray = jsonobj.getJSONArray("categories");
            homeList = new ArrayList<>();
            new SaveHomeData().execute();


        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void initView(View view) {
        //initializing view obj
        RecyclerView recycleHome = view.findViewById(R.id.recycler_view_home);
        recycleHome.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleHome.setAdapter(new HomeRecycleviewAdapter(getActivity(), homeList));
        recycleHome.setLayoutManager(layoutManager);
    }

    private class SaveHomeData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                int i;
                for (i = 0; i < jsonArray.length(); i++) {
                    HomeModel modelObj = new HomeModel();
                    JSONObject inJsonObj = jsonArray.getJSONObject(i);
                    mHomeName = inJsonObj.getString("Name");
                    modelObj.setName(mHomeName);
                    mHomeSub = inJsonObj.getString("Sub_text");
                    modelObj.setSub_text(mHomeSub);
                    modelObj.setImg(img[i]);
                    homeList.add(modelObj);
                }
                Bitmap bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.slidertea);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] byteArray = baos.toByteArray();
                long l = dbHelper.insertHomeData(mHomeName, mHomeSub, byteArray);
                bitMap.recycle();
                Log.d("database value", "" + l);


                return null;
            } catch (Exception e) {
                return "error";
            }
        }
    }


}
