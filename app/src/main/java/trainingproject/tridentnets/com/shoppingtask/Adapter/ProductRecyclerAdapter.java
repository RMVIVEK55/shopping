package trainingproject.tridentnets.com.shoppingtask.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import trainingproject.tridentnets.com.shoppingtask.activity.CartActivity;
import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.model.ProductModel;
import trainingproject.tridentnets.com.shoppingtask.utils.Keys;


public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {
    private ArrayList<ProductModel> productModel=new ArrayList<>();
    private Context context;
    private ProductModel model;
    private String mChkName;

    public ProductRecyclerAdapter(Context c, ArrayList<ProductModel> p) {
        context = c;
        productModel = p;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_list_product, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {




//        mChkName = productModel.get(position).getName();
//    holder.txtTest.setText(mChkName + " init");
        Log.d("condition ", "true");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CartActivity.class);
                i.putExtra(Keys.BUNDEL_FROM_PRODUCT, true);
                i.putExtra(Keys.BUNDEL_PRODUCT_QUANTITY, 1);
                i.putExtra(Keys.BUNDEL_PRODUCT_ID, productModel.get(position).getId());
                i.putExtra(Keys.BUNDEL_PRODUCT_IMG, productModel.get(position).getImg());
                i.putExtra(Keys.BUNDEL_PRODUCT_NAME, productModel.get(position).getName());
                i.putExtra(Keys.BUNDEL_PRODUCT_PRICE, productModel.get(position).getPrice());
                i.putExtra(Keys.BUNDEL_PRODUCT_DESC, productModel.get(position).getDesc());
                context.startActivity(i);
            }
        });

        holder.img.setImageResource(productModel.get(position).getImg());
        String name = productModel.get(position).getName();
        holder.txtName.setText(name);
        String price = productModel.get(position).getPrice();
        holder.txtPrice.setText(price + " RS");
       // holder.txtType.setText("Shoe");
        final String bool = productModel.get(position).getIsprice();
      //  Log.d("list................. ", name + price);
        if (bool.equals("true")) {
            holder.txtOffPrice.setText(productModel.get(position).getOffer_Price() + " (off)");
        } else {
            holder.txtOffPrice.setText(productModel.get(position).getPer() + " (off)");
        }
    }

    @Override
    public int getItemCount() {
        return productModel == null ? 0 : productModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtOffPrice;//, txtType, txtTest;
        ImageView img;

        private ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_productImg);
            txtName = itemView.findViewById(R.id.txt_name);
            txtPrice = itemView.findViewById(R.id.txt_price);
            txtOffPrice = itemView.findViewById(R.id.txt_offprice);



        }
    }
}
