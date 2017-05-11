package es.esy.raghavwahi.bookshelf;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            Toast.makeText(getContext(),"You Clicked on Logout",Toast.LENGTH_LONG).show();
        }else if (id==R.id.buttonEditProfile){
            Toast.makeText(getContext(),"You Clicked on Edit Profile",Toast.LENGTH_LONG).show();
        }else if (id==R.id.buttonSubmitUserBio){
            Toast.makeText(getContext(),"You Clicked on Submit Bio",Toast.LENGTH_LONG).show();
        }else if (id==R.id.imageButtonEdit){
            Toast.makeText(getContext(),"You Clicked on Edit Photo",Toast.LENGTH_LONG).show();
        }else if (id==R.id.imageProPhoto){
            Toast.makeText(getContext(),"You Clicked on Photo",Toast.LENGTH_LONG).show();
        }

    }
}
