package es.esy.raghavwahi.bookshelf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

    RequestQueue requestQueue;

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

        requestQueue = Volley.newRequestQueue(this);

        sharedPreferences = getSharedPreferences(Util.PREFS_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    void insertIntoCloudDB(){

        final String token = FirebaseInstanceId.getInstance().getToken();
        Log.i("TOKEN",token);

        StringRequest request = new StringRequest(Request.Method.POST, Util.INSERT_USER_PHP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    int success = jsonObject.getInt("success");

                    if (success == 1){
                        Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        //Code when account creation is unsuccessful with the reason.
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Code when account creation is unsuccessful with the reason. We will create a seprate
                // function for that and that function will be mentioned here.
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();

                map.put("name",user.getName());
                map.put("phone",user.getPhone());
                map.put("email",user.getEmail());
                map.put("password",user.getPassword());
                map.put("token",token);
                return map;
            }
        };
        requestQueue.add(request); //execute the request, send it to the server.

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.buttonRegister){
            user.setName(eTxtName.getText().toString().trim());
            user.setPhone(eTxtPhone.getText().toString().trim());
            user.setEmail(eTxtEmail.getText().toString().trim());
            user.setPassword(eTxtPassword.getText().toString().trim());

            //Write Data in SP(XML File)
            editor.putString(Util.KEY_NAME,user.getName());
            editor.putString(Util.KEY_PHONE,user.getPhone());
            editor.putString(Util.KEY_EMAIL,user.getEmail());
            editor.putString(Util.KEY_PASSWORD,user.getPassword());
            editor.commit(); //Save data

            insertIntoCloudDB();

        }else if (id ==R.id.imageViewLoginLink){
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
