package trainingproject.tridentnets.com.shoppingtask.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin on 11/3/2017.
 */

public class SqliteDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 15;
    private static final String DATABASE_NAME = "productdb";
    //common _id auto increment
    public static final String ID = "_id";
    //user fields
    public static final String TABLE = "usettab";
    public static final String USER = "user";
    public static final String PWD = "password";
    public static final String DOMAIN = "domain";

    //cart fields
    public static final String PID = "pid";
    public static final String CART_TABLE = "carttable";
    public static final String CART_IMG = "cartimg";
    public static final String CART_NAME = "cartname";
    public static final String CART_COST = "cartcost";
    public static final String CART_DESC = "cartdesc";
    public static final String CART_QUANTITY = "cartquantity";
    //fav field names
    public static final String FAV_TABLE = "favtable";
    public static final String FAV_IMG = "favimg";
    public static final String FAV_NAME = "favname";
    public static final String FAV_COST = "favcost";
    public static final String FAV_QUANTITY = "favquantity";
    public static final String FAV_CHK_BOOL = "favchk";
    //home fields name
    private static final String HOME_TABLE = "hometable";
    private static final String HOME_NAME = "homeName";
    private static final String HOME_SUB_TEXT = "homeSub";
    private static final String HOME_IMG = "homeImg";

    private SQLiteDatabase db;

    public SqliteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE + "(" + DOMAIN + "  VARCHAR(20)," + USER + " VARCHAR(20)," + PWD + " VARCHAR(20));";
        sqLiteDatabase.execSQL(query);


        String homequery = "CREATE TABLE " + HOME_TABLE + "(" + HOME_NAME + " VARCHAR(20)," + HOME_SUB_TEXT + " VARCHAR(20)," + HOME_IMG + " BLOB);";
        sqLiteDatabase.execSQL(homequery);

        String cartQuery = "CREATE TABLE " + CART_TABLE + "(" + ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " + PID + " VARCHAR(20)," + CART_IMG + " BLOB, " + CART_NAME + " VARCHAR(20)," + CART_COST + " VARCHAR(20), " + CART_DESC + " VARCHAR(20)," + CART_QUANTITY + "  VARCHAR(20), " + FAV_CHK_BOOL + " BOOLEAN);";
        sqLiteDatabase.execSQL(cartQuery);

        String favQuery = "CREATE TABLE " + FAV_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PID + " VARCHAR(20)," + FAV_IMG + " BLOB, " + FAV_NAME + " VARCHAR(20), " + FAV_COST + " VARCHAR(20), " + FAV_CHK_BOOL + " BOOLEAN);";
        sqLiteDatabase.execSQL(favQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }


    /**
     * * insert username and password to db
     *
     * @param username
     * @param password
     * @return bool
     */
    public boolean getCredential(String username, String password) {
        String str_query = "SELECT " + USER + "," + PWD + " FROM " + TABLE + " WHERE "+/*DOMAIN+" = '"+username+"' OR "+*/ USER + " = '" + username + "' AND " + PWD + " = '" + password + "'";
        Boolean flag = false;
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(str_query, null);
        flag = c.getCount() > 0;

        c.close();
        return flag;
    }


    public long insertHomeData(String name, String subText, byte[] img) {
         SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ins = new ContentValues();
        ins.put(HOME_NAME, name);
        ins.put(HOME_SUB_TEXT, subText);
        ins.put(HOME_IMG, img);
        return db.insert(HOME_TABLE, null, ins);
    }

    /**
     * select imgage from HOME_TABLE
     *
     * @return cursor
     */
    public Cursor showSingleImage() {
        String[] col = {HOME_IMG};
        db = this.getReadableDatabase();
        Cursor cursor = db.query(HOME_TABLE, col, null, null, null, null, null);
        cursor.close();
        return cursor;
    }

    /**
     * @param mail count no of user from producttab table
     * @return
     */
    public boolean getMail(String mail) {

        try {

            String strQuery = "SELECT " + USER + " FROM " + TABLE + " WHERE " + USER + " = '" + mail + "'";

            db = this.getReadableDatabase();
            Cursor c = db.rawQuery(strQuery, null);
            if (c.getCount() <= 0) {
                Log.d("countttttttttttttttttt", "000000000000");
                return true;
            } else {
                String ss = c.getString(c.getColumnIndex(USER));
                Log.d("countttttttttttttttttt", ss);
                c.close();
                return false;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * select username from producttab table
     *
     * @param username
     * @param password
     * @return string
     */
    public String getUSer(String username, String password) {

        try {

            String str_query = "SELECT " + DOMAIN + " FROM " + TABLE + " WHERE " + USER + " = '" + username + "' AND " + PWD + " = '" + password + "'";
            db = this.getReadableDatabase();
            Cursor c = db.rawQuery(str_query, null);
            c.moveToNext();
            String ss = c.getString(c.getColumnIndex(DOMAIN));
            c.close();
            if (ss != null) return ss;
            else return "unknow user";

        } catch (Exception e) {
            return "unknow user";
        }
    }


    /**
     * insert all values to cart table *
     *
     * @param img
     * @param name
     * @param cost
     * @param quantity
     * @return long
     */
    public long addCart(String pid, byte[] img, String name, String desc, String cost, String quantity) {
        Log.d("cart sql db", name + " " + cost + " " + quantity);
        long l;

        try {
            db = this.getWritableDatabase();
            ContentValues ins = new ContentValues();
            ins.put(PID, pid);
            ins.put(CART_IMG, img);
            ins.put(CART_NAME, name);
            ins.put(CART_COST, cost);
            ins.put(CART_DESC, desc);
            ins.put(CART_QUANTITY, quantity);
            l = db.insert(CART_TABLE, null, ins);
            return l;


        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }


    }

    /**
     * select all values from cart table
     *
     * @return cursor
     */
    public Cursor getCartDetails() {
        try {
            String selectAll = "Select * from " + CART_TABLE;
            db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectAll, null);
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * insert all fields to fav tab
     *
     * @param img
     * @param name
     * @param cost
     * @param favChk
     * @return long value
     */
    public long addFav(String pid, byte[] img, String name, String cost, boolean favChk)

    {
        try {
            db = this.getWritableDatabase();
            ContentValues ins = new ContentValues();
            ins.put(PID, pid);
            ins.put(FAV_IMG, img);
            ins.put(FAV_NAME, name);
            ins.put(FAV_COST, cost);
            ins.put(FAV_CHK_BOOL, favChk);
            Log.d("FAV Db", name + " " + cost + " ");
            return db.insert(FAV_TABLE, null, ins);

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean deleteCartDetail(String pid) {
        //delete cart id
        try {
            String delete = "delete from " + CART_TABLE + " where " + PID + "='" + pid + "'";
            db = this.getWritableDatabase();
          db.execSQL(delete);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor getFavDetails() {
        //retrive all fields from fav table
        try {
            db = this.getReadableDatabase();
            String selFav = "select * from " + FAV_TABLE;
            Cursor favCursor = db.rawQuery(selFav, null);

            return favCursor;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long getCountCartItems() {
        try {
            db = this.getReadableDatabase();
           long l=DatabaseUtils.queryNumEntries(db, CART_TABLE);
            return l;
        } catch (SQLException s) {
            s.printStackTrace();
            return 0;
        }

    }

    /**
     * @param pid
     * @return
     */
    public boolean isChkAlreadyAddedChartId(String pid) {
        //
        try {

            String selPid = "select " + PID + " from " + CART_TABLE + " WHERE " + PID + " = '" + pid + "'";
            db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selPid, null);
            return c.getCount() <= 0;

        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public boolean isChkAlreadyAddedFavId(String pid) {
        try {
            String selPid = "select " + PID + " from " + FAV_TABLE + " where " + PID + " = '" + pid + "'";
            db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selPid, null);
            return c.getCount() <= 0;

        } catch (SQLException se) {
            se.printStackTrace();
            return false;

        }

    }

    public void delFav(String pid, boolean isFav) {
        //delete fav id from fav table
        try {
            db = this.getWritableDatabase();
            String delFav = "delete from " + FAV_TABLE + " where " + PID + " = '" + pid + "'";
            db.execSQL(delFav);
            db.close();

        } catch (SQLException s) {
            s.printStackTrace();

        }
    }

    public boolean updateCartQuantity(String pid, String quantity) {
        try {
            db = this.getWritableDatabase();
            String updateCartQuantity = "update " + CART_TABLE + " set " + CART_QUANTITY + " = " + quantity + " where " + PID + " = '" + pid + "'";
            db.execSQL(updateCartQuantity);
            db.close();
            return true;
        } catch (SQLException s) {
            s.printStackTrace();
            return false;
        }

    }

}
