package trainingproject.tridentnets.com.shoppingtask.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;
import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.utils.AppPreference;
import trainingproject.tridentnets.com.shoppingtask.utils.Keys;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private String mStrDomain, mStrMail, mStrPwd;
    private EditText edtDomain, edtMail, EditPwd;
    private SqliteDBHelper sqlHelperObj;
    private ProgressBar pBarReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

    }

    private void initView() {
        //collection view obj and view initizing values
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pBarReg = (ProgressBar) findViewById(R.id.pbar_register);
        Typeface fontNovaRegular = Typeface.createFromAsset(getAssets(), getString(R.string.fontreg));
        sqlHelperObj = new SqliteDBHelper(this);
        Button btnSignUp = (Button) findViewById(R.id.btn_reg);
        Typeface fontBold = Typeface.createFromAsset(getAssets(), getString(R.string.fontbold));
        btnSignUp.setTypeface(fontBold);
        edtDomain = (EditText) findViewById(R.id.edt_regdomin);
        edtMail = (EditText) findViewById(R.id.edt_reguser);
        EditPwd = (EditText) findViewById(R.id.edt_regpwd);
        edtDomain.setTypeface(fontNovaRegular);
        edtMail.setTypeface(fontNovaRegular);
        EditPwd.setTypeface(fontNovaRegular);
        btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int v = view.getId();
        switch (v) {
            case R.id.btn_reg:
                mStrDomain = edtDomain.getText().toString();
                mStrMail = edtMail.getText().toString().trim();
                mStrPwd = EditPwd.getText().toString();
                if (mStrDomain.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Kindly please enter edtDomain fields", Toast.LENGTH_SHORT).show();
                } else if (mStrMail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Kindly please enter email fields", Toast.LENGTH_SHORT).show();

                } else if (mStrPwd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Kindly please enter password fields", Toast.LENGTH_SHORT).show();

                } else {
                    String mEmailFormat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (mStrMail.matches(mEmailFormat)) {
                        boolean count = sqlHelperObj.getMail(mStrMail);
                        if (count) {
                            new AsynInsertRegQuery().execute();
                        } else {
                            setAlert("this emil address already used");
                        }
                    } else {
                        setAlert("please enter valid email address");
                    }
                    break;

                }

        }
    }

    private class AsynInsertRegQuery extends AsyncTask<String, Void, Long> {
        //inserting  register values  to db

        SQLiteDatabase db;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pBarReg.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(final Long aVoid) {
            super.onPostExecute(aVoid);
            //        Toast.makeText(getApplicationContext(), Long.toString(aVoid), Toast.LENGTH_LONG).show();
            if (aVoid <= 0) {
                setAlert("cannot effected database");
            } else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pBarReg.setVisibility(View.GONE);
                        AppPreference.getInstance(RegisterActivity.this).putString(AppPreference.StringKeys.DOMAIN, mStrDomain);
                        AppPreference.getInstance(RegisterActivity.this).putString(AppPreference.StringKeys.MAILID, mStrMail);
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        //    setAlert("one row addded");
                        // Toast.makeText(getApplicationContext(),aVoid,Toast.LENGTH_LONG).show();
                    }
                }, 300);
            }

        }

        @Override
        protected Long doInBackground(String... params) {
            db = sqlHelperObj.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(SqliteDBHelper.DOMAIN, mStrDomain);
            cv.put(SqliteDBHelper.USER, mStrMail);
            cv.put(SqliteDBHelper.PWD, mStrPwd);
            return db.insert(SqliteDBHelper.TABLE, null, cv);
        }
    }

    void setAlert(String data) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterActivity.this);
        alertDialogBuilder.setMessage(data);
        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
