package trainingproject.tridentnets.com.shoppingtask.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.model.ProductModel;


public class ClothesRecyclerlist extends RecyclerView.Adapter<ClothesRecyclerlist.ViewHolder> {
    private ArrayList<ProductModel> productList;
    private ProductModel productModel;

    public ClothesRecyclerlist(ArrayList<ProductModel> localList) {
        productList = localList;

    }

    @Override
    public ClothesRecyclerlist.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_list_product, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClothesRecyclerlist.ViewHolder holder, int position) {
        productModel = new ProductModel();
        productList.get(position);
       // String item = productList.get(position).getCate_name();
        holder.img.setImageResource(productList.get(position).getImg());
        holder.txtClotheName.setText(productList.get(position).getName());
        holder.txtPrice.setText(productList.get(position).getPrice() + " RS");
        String bool = productList.get(position).getIsprice();
        if (bool.equals("true")) {
            holder.txtOffPrice.setText(productList.get(position).getOffer_Price() + " (off)");
        } else {
            holder.txtOffPrice.setText(productList.get(position).getPer() + " (off)");
            // holder.percent.setText(productList.get(position).getPer());
        }
//            holder.brand.setText(productList.get(position).getBrand_name());
//            holder.catename.setText(productList.get(position).getCate_name());
        //      }

    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtClotheName, txtPrice, txtOffPrice;
        ImageView img;

        private ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_productImg);
            txtClotheName = itemView.findViewById(R.id.txt_name);
            txtPrice = itemView.findViewById(R.id.txt_price);
            txtOffPrice = itemView.findViewById(R.id.txt_offprice);
            //   percent=(TextView)itemView.findViewById(R.id.tv_percent) ;
//            brand=(TextView)itemView.findViewById(R.id.tv_brindname);
//            catename=(TextView)itemView.findViewById(R.id.tv_catename);


        }
    }
}
