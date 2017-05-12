package es.esy.raghavwahi.bookshelf;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserProfile extends Fragment implements View.OnClickListener{

    @InjectView(R.id.imageProPhoto)
    ImageView imgProfilePhoto;

    @InjectView(R.id.imageButtonEdit)
    ImageButton btnEditPhoto;

    @InjectView(R.id.editTextUserbio)
    EditText txtuserBio;

    @InjectView(R.id.buttonSubmitUserBio)
    Button btnSubmitUserBio;

    @InjectView(R.id.buttonEditProfile)
    Button btnEditProfile;

    @InjectView(R.id.buttonLogout)
    Button btnLogout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_profile,container,false);

        ButterKnife.inject(this, view);

        imgProfilePhoto.setOnClickListener(this);
        btnEditPhoto.setOnClickListener(this);
        btnSubmitUserBio.setOnClickListener(this);
        btnEditProfile.setOnClickListener(this);
        btnLogout.setOnClickListener(this);


        return view;

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.buttonLogout){

        }else if (id==R.id.buttonEditProfile){

        }else if (id==R.id.buttonSubmitUserBio){

        }else if (id==R.id.imageButtonEdit){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.Theme_MyDialog);
            builder.setTitle("Profile photo from");
            String[] options = {"Camera","Gallery","Remove photo"};
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    switch (which){
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                    }
                }
            });
            builder.create().show();
        }else if (id==R.id.imageProPhoto){
            Intent i= new Intent(getActivity(),ProfilePhoto.class);
            startActivity(i);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
