package trainingproject.tridentnets.com.shoppingtask.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import trainingproject.tridentnets.com.shoppingtask.R;

public  class SetOpenScreenImageFragment extends Fragment {
  private   ImageView imgElement;


    public static SetOpenScreenImageFragment getInstance(int i) {
        SetOpenScreenImageFragment pageObj = new SetOpenScreenImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key", i);
        pageObj.setArguments(bundle);
        return pageObj;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.singleimage, container, false);
        imgElement =  view.findViewById(R.id.singleimage);
        //  Toast.makeText(getActivity(),Integer.toString(getArguments().getInt("key")),Toast.LENGTH_LONG).show();
//        Log.d("valueeeeee",Integer.toString(getArguments().getInt("key")));

        int k = getArguments().getInt("key");
        switch (k) {
            case 0:
                display(R.drawable.intro1);
                break;
            case 1:
                display(R.drawable.intro2);
                break;
            case 2:
                display(R.drawable.intro3);
                break;
            case 3:
                display(R.drawable.intro1);
                break;
        }
        return view;
    }

    void display(int s) {
        imgElement.setImageResource(s);
    }
}

