package trainingproject.tridentnets.com.shoppingtask.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import trainingproject.tridentnets.com.shoppingtask.activity.NavigationListActivity;
import trainingproject.tridentnets.com.shoppingtask.Fragment.ProductFragment;
import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.model.HomeModel;



public class HomeRecycleviewAdapter extends RecyclerView.Adapter<HomeRecycleviewAdapter.ViewHolder> {
    private ArrayList<HomeModel> recyArraylist = new ArrayList<>();
    private Context context;
    private HomeModel homeModel;

    public HomeRecycleviewAdapter(Context c, ArrayList<HomeModel> a) {
        recyArraylist = a;
        context = c;
    }

    @Override
    public HomeRecycleviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeRecycleviewAdapter.ViewHolder holder, int pos) {
        homeModel = recyArraylist.get(pos);
      holder.frameLayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              FragmentManager fragmentManager = ((NavigationListActivity) context).getSupportFragmentManager();
              FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
              fragmentTransaction.replace(R.id.container, new ProductFragment());
              fragmentTransaction.commit();
          }
      });
        holder.txtName.setText(recyArraylist.get(pos).getName());
        holder.txtSub.setText(recyArraylist.get(pos).getSub_text());
        holder.imgLayout.setBackgroundResource(recyArraylist.get(pos).getImg());
        holder.txtShop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // movePage();

            }
        });
    }

    @Override
    public int getItemCount() {
        return recyArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtSub, txtShop;
        ImageView imgLayout;
        FrameLayout frameLayout;

        private ViewHolder(View v) {
            super(v);
            Typeface font = Typeface.createFromAsset(v.getContext().getAssets(), "MavenPro-Regular.ttf");
            txtName =  v.findViewById(R.id.txt_home_name);
            txtName.setTypeface(font);
            txtSub =  v.findViewById(R.id.txt_home_sub);
            txtSub.setTypeface(font);
            imgLayout =  v.findViewById(R.id.lay_home);
            frameLayout=v.findViewById(R.id.lay_home_fragment);
            txtShop = v.findViewById(R.id.txt_home_shopnow);
        }
    }


}
