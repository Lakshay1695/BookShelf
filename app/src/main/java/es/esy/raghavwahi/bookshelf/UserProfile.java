package es.esy.raghavwahi.bookshelf;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserProfile extends Fragment implements View.OnClickListener,View.OnLongClickListener{


    @InjectView(R.id.imageProPhoto)
    ImageView imgProfilePhoto;
    @InjectView(R.id.editTextUserbio)
    EditText txtuserBio;
    @InjectView(R.id.buttonSubmitUserBio)
    Button btnSubmitUserBio;
    @InjectView(R.id.buttonEditProfile)
    Button btnEditProfile;
    @InjectView(R.id.buttonLogout)
    Button btnLogout;

    Uri photoUri, cropUri, intentUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_profile,container,false);

        ButterKnife.inject(this, view);

        imgProfilePhoto.setOnClickListener(this);
        btnSubmitUserBio.setOnClickListener(this);
        btnEditProfile.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        imgProfilePhoto.setOnLongClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if (id==R.id.buttonLogout){

        }else if (id==R.id.buttonEditProfile){

        }else if (id==R.id.buttonSubmitUserBio){

        }
    }

    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        if (id==R.id.imageProPhoto){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.Theme_MyDialog);
            builder.setTitle("Profile photo from");
            String[] options = {"Camera","Gallery","Remove photo"};
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    switch (which){
                        case 0:
                            //Camera
                            try{
                                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                File file = new File(Environment.getExternalStorageDirectory(),"file"+String.valueOf(System.currentTimeMillis()+".jpg"));
                                photoUri = Uri.fromFile(file);
                                camIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                                camIntent.putExtra("return-data",true);
                                startActivityForResult(camIntent, Util.REQUEST_IMAGE_CAPTURE);

                            }catch (ActivityNotFoundException e){
                                e.printStackTrace();
                            }
                            break;

                        case 1:
                            //Gallery
                            try{
                                Intent galIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(Intent.createChooser(galIntent,"Select Image From Gallery"),Util.REQUEST_IMAGE_GALLERY);
                            }catch (ActivityNotFoundException e){
                                e.printStackTrace();
                            }
                            break;

                        case 2:
                            //to remove the image. condition(if the image is other than drawable resourced image) only then we can have this option.
                            // and upon removing the image the source would be the drawable one.
                            break;
                    }
                }
            });
            builder.create().show();

        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Util.REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK){
            cropImage();
        }else if (requestCode == Util.REQUEST_IMAGE_GALLERY ){
            if (data!=null){
                photoUri = data.getData();
                cropImage();
            }
        }else if (requestCode == Util.REQUEST_IMAGE_CROP){
            if (data!=null){
                Bundle bundle = data.getExtras();
                Bitmap userImage = bundle.getParcelable("data");
                imgProfilePhoto.setImageBitmap(userImage);
            }
        }

    }

    private void cropImage(){
        try{
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(photoUri,"image/*");

            cropIntent.putExtra("crop","true");
            cropIntent.putExtra("outputX",320);
            cropIntent.putExtra("outputY",320);
            cropIntent.putExtra("aspectX",1);
            cropIntent.putExtra("aspectY",1);
            cropIntent.putExtra("scaleUpIfNeeded",true);
            cropIntent.putExtra("return-data",true);

            startActivityForResult(cropIntent,Util.REQUEST_IMAGE_CROP);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }

    }
}
