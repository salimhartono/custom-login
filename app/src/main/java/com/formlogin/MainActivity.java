package com.formlogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //bind edit text untuk email dan cek email
    @BindView(R.id.login_email)
    EditText etEmail;
    @BindView(R.id.cekEmail)
    TextView tvCekEmail;

    //bind edit text untuk pasword dan cek pasword
    @BindView(R.id.login_pass)
    EditText etPass;
    @BindView(R.id.cekPassword)
    TextView tvCekPass;

    //bind tombol show dan hide pasword
    @BindView(R.id.showPaswrod)
    ImageView ivShowPass;
    @BindView(R.id.hidePassword)
    ImageView ivHidePass;


    //bind button
    @BindView(R.id.btLogin)
    Button btLogin;

    //string
    private String getEmail;
    private String getPassword;

    //int pengecekan email
    private int statusEmail = 1;
    private int statusPass = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //listener untuk form email
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getEmail = etEmail.getText().toString().trim();
                if (isValidEmailId(getEmail)) {
                    statusEmail = 2;
                    tvCekEmail.setText(getString(R.string.emailValid));
                    tvCekEmail.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    statusEmail = 1;
                    tvCekEmail.setText(getString(R.string.emailInvalid));
                    tvCekEmail.setTextColor(getResources().getColor(R.color.colorAccent));
                }

            }
        });

        //listener untuk form password
        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getPassword = etPass.getText().toString();
                if (getPassword.length() < 8) {
                    statusPass = 1;
                    tvCekPass.setText(getString(R.string.paswordInvalid));
                    tvCekPass.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    statusPass = 2;
                    tvCekPass.setText(getString(R.string.passwordValid));
                    tvCekPass.setTextColor(getResources().getColor(R.color.colorPrimary));
                }

            }
        });

        //listener untuk form tombol login
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (statusEmail == 1 || statusPass == 1) {
                    Toast.makeText(getApplicationContext(), "pastikan email dan password yang dimasukan sudah valid", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Perfect, Good Job!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //listener untuk show hide password
        ivShowPass.setOnClickListener(this);
        ivHidePass.setOnClickListener(this);

    }

    //methods untuk pengecekan validasi email
    private boolean isValidEmailId(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    //methods untuk show hide password
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.showPaswrod:
                etPass.setInputType(InputType.TYPE_CLASS_TEXT);
                etPass.setSelection(etPass.getText().length());
                ivShowPass.setVisibility(View.GONE);
                ivHidePass.setVisibility(View.VISIBLE);
                break;

            case R.id.hidePassword:
                etPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                etPass.setSelection(etPass.getText().length());
                ivHidePass.setVisibility(View.GONE);
                ivShowPass.setVisibility(View.VISIBLE);
                break;
        }

    }
}
