package es.esy.raghavwahi.bookshelf;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @InjectView(R.id.editTextNameLogin)
    EditText txtLogin;

    @InjectView(R.id.editTextPasswordLogin)
    EditText txtPassword;

    @InjectView(R.id.buttonLogin)
    Button btnLogin;

    @InjectView(R.id.imageViewRegisterLink)
    ImageView imgRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        btnLogin.setOnClickListener(this);
        imgRegisterLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id  = v.getId();
        if (id == R.id.imageViewRegisterLink){
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
            }else if (id == R.id.buttonLogin){

        }
        }
    }
