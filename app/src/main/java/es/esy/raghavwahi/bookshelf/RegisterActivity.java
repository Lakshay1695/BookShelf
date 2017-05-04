package es.esy.raghavwahi.bookshelf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.editTextName)
    EditText eTxtName;

    @InjectView(R.id.editTextPhone)
    EditText eTxtPhone;

    @InjectView(R.id.editTextEmail)
    EditText eTxtEmail;

    @InjectView(R.id.editTextPassword)
    EditText eTxtPassword;

    @InjectView(R.id.editTextPasswordConfirm)
    EditText eTxtPasswordConfirm;

    @InjectView(R.id.buttonRegister)
    Button btnRegister;

    @InjectView(R.id.imageViewLoginLink)
    ImageView imgLoginLink;

    User user;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);

        btnRegister.setOnClickListener(this);
        imgLoginLink.setOnClickListener(this);

        user = new User();

        sharedPreferences = getSharedPreferences("userPrefs",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String name = sharedPreferences.getString("keyPassword","NA");
        eTxtName.setText(name);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.buttonRegister){
            user.setName(eTxtName.getText().toString().trim());
            user.setPhone(eTxtPhone.getText().toString().trim());
            user.setEmail(eTxtEmail.getText().toString().trim());
            if (eTxtPassword.getText().toString().trim()== eTxtPasswordConfirm.getText().toString().trim()){
                user.setPassword(eTxtPassword.getText().toString().trim());
            }else{
                Toast.makeText(this,"Password is Not Same",Toast.LENGTH_LONG).show();
            }
//Password is not same. has to resolve this issue and do further coding after shared prefrences.
            //Write Data in SP(XML File)
            editor.putString("keyName",user.getName());
            editor.putString("keyPhone",user.getPhone());
            editor.putString("keyEmail",user.getEmail());
            editor.putString("keyPassword",user.getPassword());
            editor.commit(); //Save

        }else if (id ==R.id.imageViewLoginLink){
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
