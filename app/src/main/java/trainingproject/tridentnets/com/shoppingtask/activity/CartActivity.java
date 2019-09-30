package trainingproject.tridentnets.com.shoppingtask.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;
import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.utils.AppPreference;
import trainingproject.tridentnets.com.shoppingtask.utils.Keys;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    private int mImg, mItemCount, mCountCartItems;
    private String mName, mPrice, mDesc, mPid, mChkZeros = "^0+";
    private ImageView imgProduct, imgBack, imgCartPicture;
    private EditText edtQuantity;
    private byte[] mImgByte;
    private SqliteDBHelper dbHelper;
    private Bitmap bitmap;
    private ToggleButton tbtn_fav_chk;
    private String name, mCost;
    boolean isChk = false;
    private TextView txtCartName, txtPrice, txtDesc, txtNoOfCartItems;
    private Button btnSum, btnCart, btnSub, btnSave, btnDelete;
    private Intent backIntent;
    Long mCartCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getBundle();
        initializingView();
        setValues();
    }

    private void setValues() {
        //set values
        txtNoOfCartItems.setText(Long.toString(dbHelper.getCountCartItems()));
        txtCartName.setText(mName);
        txtPrice.setText("Rs ".concat(mPrice));
        txtDesc.setText(mDesc);
        edtQuantity.setText(Integer.toString(mItemCount).trim());
        mItemCount = Integer.parseInt(edtQuantity.getText().toString());
        name = txtCartName.getText().toString();
        mCost = txtPrice.getText().toString();
        isChk = dbHelper.isChkAlreadyAddedFavId(mPid);
        tbtn_fav_chk.setChecked(!isChk);
        btnCart.setOnClickListener(this);
        btnSum.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        tbtn_fav_chk.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        txtNoOfCartItems.setOnClickListener(this);
        imgCartPicture.setOnClickListener(this);


        // string selectQuery="select * from "+SqliteDBHelper.CART_TABLE+" where "+SqliteDBHelper.PID+" = '"+mPid+"'";

        if (mImg == 0) {
            //byte array to bitmap
            ByteArrayInputStream bais = new ByteArrayInputStream(mImgByte);
            Bitmap byteToBitmap = BitmapFactory.decodeStream(bais);
            imgProduct.setImageBitmap(byteToBitmap);
            btnDelete.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.VISIBLE);
            btnCart.setVisibility(View.GONE);


        } else {
            imgProduct.setImageResource(mImg);
            //drawable to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap = ((BitmapDrawable) imgProduct.getDrawable()).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            mImgByte = baos.toByteArray();
            btnDelete.setVisibility(View.GONE);
            btnSave.setVisibility(View.GONE);
            btnCart.setVisibility(View.VISIBLE);


        }
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();

        if (bundle.getBoolean(Keys.BUNDEL_FROM_PRODUCT)) {
            mName = bundle.getString(Keys.BUNDEL_PRODUCT_NAME);
            mPrice = bundle.getString(Keys.BUNDEL_PRODUCT_PRICE);
            mDesc = bundle.getString(Keys.BUNDEL_PRODUCT_DESC);
            mImg = bundle.getInt(Keys.BUNDEL_PRODUCT_IMG);
            mPid = bundle.getString(Keys.BUNDEL_PRODUCT_ID);
            mItemCount = bundle.getInt(Keys.BUNDEL_PRODUCT_QUANTITY);
        } else {
            mImg = 0;
            mImgByte = bundle.getByteArray(Keys.BUNDEL_PRODUCT_Byte_IMG);
            mName = bundle.getString(Keys.BUNDEL_PRODUCT_NAME);
            mPrice = bundle.getString(Keys.BUNDEL_PRODUCT_PRICE);
            mDesc = bundle.getString(Keys.BUNDEL_PRODUCT_DESC);
            mPid = bundle.getString(Keys.BUNDEL_PRODUCT_ID);
            Log.d("get bundle mpid", mPid);
            mItemCount = bundle.getInt(Keys.BUNDEL_PRODUCT_QUANTITY);
        }

        Log.d("TAG mpid", mPid + "");
    }


    /**
     * creating initializing view obj
     */
    private void initializingView() {
        dbHelper = new SqliteDBHelper(this);
        txtNoOfCartItems = (TextView) findViewById(R.id.txt_item_count_product_cart);
        imgCartPicture = (ImageView) findViewById(R.id.img_product_cart);
        tbtn_fav_chk = (ToggleButton) findViewById(R.id.tbtn_fav);
        imgProduct = (ImageView) findViewById(R.id.img_product);
        imgBack = (ImageView) findViewById(R.id.img_back);
        txtCartName = (TextView) findViewById(R.id.txt_product_name);
        edtQuantity = (EditText) findViewById(R.id.edt_product_quantity);
        txtDesc = (TextView) findViewById(R.id.txt_product_desc);
        btnSum = (Button) findViewById(R.id.btn_sum);
        btnCart = (Button) findViewById(R.id.btn_product_cart);
        btnCart.setVisibility(View.GONE);
        txtPrice = (TextView) findViewById(R.id.txt_product_price);
        btnSub = (Button) findViewById(R.id.btn_subtract);
        btnDelete = (Button) findViewById(R.id.btn_delete_product);
        btnDelete.setVisibility(View.GONE);
        btnSave = (Button) findViewById(R.id.btn_save_product);
        btnSave.setVisibility(View.GONE);

//set Listencers


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sum:
                mItemCount++;
                if (1 <= mItemCount) {
                    edtQuantity.setText(Integer.toString(mItemCount).trim().replaceAll(mChkZeros, ""));
                }
                break;
            case R.id.btn_subtract:
                mItemCount--;
                if (1 >= mItemCount) {
                    mItemCount = 1;
                    edtQuantity.setText(Integer.toString(mItemCount).trim().replaceAll(mChkZeros, ""));
                } else {
                    edtQuantity.setText(Integer.toString(mItemCount).trim().replaceAll(mChkZeros, ""));
                }
                break;
            case R.id.btn_product_cart:

                if (mItemCount >= 0) {
                    isChk = dbHelper.isChkAlreadyAddedChartId(mPid);
                    if (isChk) {
                        String getQauntity = edtQuantity.getText().toString();
                        long l = dbHelper.addCart(mPid, mImgByte, name, mDesc, mCost, getQauntity.replaceAll(mChkZeros, ""));
                        if (0 < l) {
                            Toast.makeText(CartActivity.this, "One row Added cart db", Toast.LENGTH_SHORT).show();
                            Log.d("CART DATABASE INFO", l + "");
                        }
                    } else {
                        Toast.makeText(CartActivity.this, "Already added this product", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(CartActivity.this, "Please add atleast one qunantity item", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.tbtn_fav:
                if (isChk) {
                    if (tbtn_fav_chk.isChecked()) {
                        long d = dbHelper.addFav(mPid, mImgByte, name, mCost, tbtn_fav_chk.isChecked());
                        Log.d("fav database info", d + "");
                        if (d > 0) {
                            Log.e("fav dab info", "One row Added Fav db");
                        }
                    } else {
                        // "false chECk"
                        dbHelper.delFav(mPid, false);
                    }
                } else {
                    dbHelper.delFav(mPid, false);
                    Log.e("fav dab info", "deleted this fav table");
                }
                break;
            case R.id.btn_delete_product:
                dbHelper.deleteCartDetail(mPid);
                backIntent = new Intent();
                setResult(RESULT_OK, backIntent);
                finish();
                break;
            case R.id.btn_save_product:
                String getQauntity = edtQuantity.getText().toString();
                boolean isUpdateQuantity = dbHelper.updateCartQuantity(mPid, getQauntity.replaceAll(mChkZeros, ""));
                if (isUpdateQuantity)
                    Toast.makeText(CartActivity.this, "Update Quantity", Toast.LENGTH_SHORT).show();
                backIntent = new Intent();
                setResult(RESULT_OK, backIntent);
                finish();
                break;
            case R.id.img_product_cart:
            case R.id.txt_item_count_product_cart:
                Intent intent = new Intent(CartActivity.this, NavigationListActivity.class);
                intent.putExtra(Keys.BUNDEL_Fragment_NAME, "new CartFragment()");
                startActivity(intent);
                break;
        }
    }
}
