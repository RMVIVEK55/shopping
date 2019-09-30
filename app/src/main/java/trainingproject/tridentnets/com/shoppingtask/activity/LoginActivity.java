package trainingproject.tridentnets.com.shoppingtask.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;
import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.utils.AppPreference;
import trainingproject.tridentnets.com.shoppingtask.utils.Keys;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public String mGetUserFromDb;
    private ProgressBar pbarLogin;
    private EditText edtMail, edtPwd;
    private SqliteDBHelper sqlDbHelper;
    private String mStrMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        // calling initializing obj method

        initializingView();

    }


    @Override
    public void onClick(View view) {
        int v = view.getId();
        switch (v) {
            case R.id.txt_sign:
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                try {
                    mStrMail = edtMail.getText().toString();
                    String mStrPwd = edtPwd.getText().toString();
                    if (mStrMail.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Kindly please enter username ", Toast.LENGTH_SHORT).show();
                    } else if (mStrPwd.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Kindly please enter password ", Toast.LENGTH_SHORT).show();
                    } else {
                        String mEmailFormat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        if (mStrMail.matches(mEmailFormat)) {
                            edtMail.setText("");
                            edtPwd.setText("");
                            new BackgroundProcess().execute(mStrMail, mStrPwd);
                        } else {
                            Toast.makeText(getApplicationContext(), "Kindly please enter valid email address", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    private class BackgroundProcess extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbarLogin = (ProgressBar) findViewById(R.id.pbar_login);
            pbarLogin.setVisibility(View.VISIBLE);


        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                Boolean mIsUser = sqlDbHelper.getCredential(params[0], params[1]);
                Log.d("IS USER", mIsUser + "");

                return mIsUser;
            } catch (SQLException r) {

                return false;

            }
        }

        @Override
        protected void onPostExecute(final Boolean result) {
            super.onPostExecute(result);
            Log.v("TAG", "boolean db result is  :" + result);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //   progress.dismiss();
                    pbarLogin.setVisibility(View.GONE);
                    if (result) {
                        //  alert(Boolean.toString(result));
                        AppPreference.getInstance(LoginActivity.this).putBoolean(AppPreference.BooleanKeys.LOGGED_IN, true);
                        Intent i=new Intent(LoginActivity.this,NavigationListActivity.class);
                        startActivity(i);
                        finish();
                        edtMail.setText("");
                        edtPwd.setText("");
                    } else {
                        alert("Invalid credentials.user not registered");
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            }, 300);

        }
    }

    private void alert(String data) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        alertDialogBuilder.setMessage(data);
        alertDialogBuilder.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     *
     */
    private void initializingView() {
        //collection of view obj and initializing obj id

        sqlDbHelper = new SqliteDBHelper(this);
        AppPreference.getInstance(this).putBoolean(AppPreference.BooleanKeys.IS_SECOND_TIME, true);
        Typeface fontBold = Typeface.createFromAsset(getAssets(), getString(R.string.fontbold));
        Typeface fontNovaRegular = Typeface.createFromAsset(getAssets(), getString(R.string.fontreg));
        TextView txtRegister = (TextView) findViewById(R.id.txt_sign);
        edtMail = (EditText) findViewById(R.id.txt_username);
        //  TextView txtsign = (TextView) findViewById(R.id.txt_sign);
        edtPwd = (EditText) findViewById(R.id.txt_password);
        Button btnLogin = (Button) findViewById(R.id.btn_login);

        edtMail.setTypeface(fontNovaRegular);
        edtPwd.setTypeface(fontNovaRegular);
        btnLogin.setTypeface(fontBold);
        txtRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


    }
}
