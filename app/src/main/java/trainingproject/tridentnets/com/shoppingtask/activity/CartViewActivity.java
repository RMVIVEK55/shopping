package trainingproject.tridentnets.com.shoppingtask.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.utils.Keys;

public class CartViewActivity extends AppCompatActivity {
    private int mImg;
    private String mProductName, mPrice, mQunantity;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        initializingView();
        mImg = b.getInt(Keys.BUNDEL_PRODUCT_IMG);
        mProductName = b.getString(Keys.BUNDEL_PRODUCT_NAME);
        mPrice = b.getString(Keys.BUNDEL_PRODUCT_COST);
        mQunantity = b.getString(Keys.BUNDEL_PRODUCT_QUANTITY);

    }

    private void initializingView() {
        //creating initializing view obj
        b = getIntent().getExtras();
        ImageView imgViewCartview = (ImageView) findViewById(R.id.img_cartview);
        TextView textName = (TextView) findViewById(R.id.txt_cartview_name);
        TextView txtQun = (TextView) findViewById(R.id.txt_cartview_Quantity);
        TextView txtCost = (TextView) findViewById(R.id.txt_cartview_cost);
        txtQun.setText("quantity ".concat(mQunantity));

        textName.setText( mProductName);
        txtCost.setText(mPrice);
        imgViewCartview.setImageResource(mImg);

    }
}
