package trainingproject.tridentnets.com.shoppingtask.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 11/20/2017.
 */

public class ProductModel implements Parcelable

{
    private String id;
    private String name;
    private String desc;
    private String price;
    private String currency;
    private String isprice;
    private String offer_Price;
    private String brand_name;
    private String cate_name, per;
    private int img;

  public ProductModel() {
    }

    private ProductModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        desc = in.readString();
        price = in.readString();
        currency = in.readString();
        isprice = in.readString();
        offer_Price = in.readString();
        brand_name = in.readString();
        cate_name = in.readString();
        per = in.readString();
        img = in.readInt();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIsprice() {
        return isprice;
    }

    public void setIsprice(String isprice) {
        this.isprice = isprice;
    }

    public String getOffer_Price() {
        return offer_Price;
    }

    public void setOffer_Price(String offer_Price) {
        this.offer_Price = offer_Price;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(desc);
        parcel.writeString(price);
        parcel.writeString(currency);
        parcel.writeString(isprice);
        parcel.writeString(offer_Price);
        parcel.writeString(brand_name);
        parcel.writeString(cate_name);
        parcel.writeString(per);
        parcel.writeInt(img);
    }
}
