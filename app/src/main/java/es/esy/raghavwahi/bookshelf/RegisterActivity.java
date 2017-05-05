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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

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
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.buttonRegister){
            user.setName(eTxtName.getText().toString().trim());
            user.setPhone(eTxtPhone.getText().toString().trim());
            user.setEmail(eTxtEmail.getText().toString().trim());

            if (eTxtPassword.getText().toString().trim().equals(eTxtPasswordConfirm.getText().toString().trim())){
                user.setPassword(eTxtPassword.getText().toString().trim());
            }
            //Write Data in SP(XML File)
            editor.putString("keyName",user.getName());
            editor.putString("keyPhone",user.getPhone());
            editor.putString("keyEmail",user.getEmail());
            editor.putString("keyPassword",user.getPassword());
            editor.commit(); //Save data

        }else if (id ==R.id.imageViewLoginLink){
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    void insertIntoCloud(){

        //Volley String Request
        StringRequest request = new StringRequest(Request.Method.POST, Util.INSERT_USER_PHP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    int success = jsonObject.getInt("success");
                    String message = jsonObject.getString("message");
                    if (success == 1){
                        Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this,"Some Exception",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
    }
}
