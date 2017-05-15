package es.esy.raghavwahi.bookshelf;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    String ProfilePhotoPath;

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
                            try{
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, Util.REQUEST_IMAGE_CAPTURE);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            break;

                        case 1:
                            //Image from gallery
                            break;

                        case 2:
                            //to remove the image. condition(if the image is other than drawable resourced image) only then we can have this option.
                            // and upon removing the image the source would be the drawable one.
                            break;
                    }
                }
            });
            builder.create().show();
        }else if (id==R.id.imageProPhoto){
            Intent intent= new Intent(getActivity(),ProfilePhoto.class);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode ==Util.REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK){
            Bundle bundle = data.getExtras();
            Util.PHOTO_URI = data.getData();
            performCrop();
            Bitmap userImage = (Bitmap) bundle.get("data");
            imgProfilePhoto.setImageBitmap(userImage);
        }
    }

    private void performCrop(){
        try{
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(Util.PHOTO_URI,"image/*");
            cropIntent.putExtra("crop","true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("scale", true);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outPutY", 256);
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, Util.REQUEST_IMAGE_CROP);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
