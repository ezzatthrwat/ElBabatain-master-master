package app.ideal.family.elbabatain.ui.contactus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import javax.inject.Inject;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.di.appcomponent.DaggerAppComponent;
import app.ideal.family.elbabatain.network.contact_us.ContactUsApi;
import app.ideal.family.elbabatain.network.contact_us.ContactUs_Model;
import app.ideal.family.elbabatain.network.contact_us.ContactUs_Send_Body;
import dagger.android.support.DaggerAppCompatActivity;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.ideal.family.elbabatain.util.BubbleAnimation.DoAnimation;

public class ContactUs extends DaggerAppCompatActivity {

    private static final String TAG = "ContactUs";
    TextInputLayout username_text_input , email_text_input ,userPhone_text_input , comment_text_input;

    ImageView Contactus_Logo  , ContactUs_Back_arrow ;

    @Inject
    ContactUsApi contactUsApi ;

    @Inject
    ContactUs_Send_Body contactUs_send_body ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        initWidgetWithThierAnimations();


    }

    private void initWidgetWithThierAnimations(){

        username_text_input =findViewById(R.id.username_text_input);
        email_text_input =findViewById(R.id.email_text_input);
        userPhone_text_input =findViewById(R.id.userPhone_text_input);
        comment_text_input =findViewById(R.id.comment_text_input);


        Contactus_Logo = findViewById(R.id.Contactus_Logo);

        ContactUs_Back_arrow = findViewById(R.id.ContactUs_Back_arrow);
        ContactUs_Back_arrow.setOnClickListener(view -> {

            this.onBackPressed();
        });
    }

    public void SendToEmail(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info@familyalbabtain.com"});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactUs.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void SendByWhatsApp(View view) {

        boolean installed = appInstalledOrNot();
        if(installed) {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW );
            sendIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone=+966555229193"));
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);


        } else {
            Toast.makeText(this, "WhatsApp Not Installed !!", Toast.LENGTH_LONG).show();
        }

    }

    private boolean appInstalledOrNot() {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


    public void sendToControllPanel(View view) {


      AlertDialog spotsDialog =   new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Sending...")
                .setCancelable(true)
                .build();


        if (TextUtils.isEmpty(username_text_input.getEditText().getText())){
            username_text_input.setError("يجب ادخال الاسم");
        }else if (TextUtils.isEmpty(email_text_input.getEditText().getText())){
            email_text_input.setError("يجب ادخال البريدالالكترونى");
        }else if (TextUtils.isEmpty(userPhone_text_input.getEditText().getText())){
            userPhone_text_input.setError("يجب ادخال رقم الهادف");
        }else if (TextUtils.isEmpty(comment_text_input.getEditText().getText())){
            comment_text_input.setError("يجب ادخال الرسالة");
        }else {

            spotsDialog.show();

            contactUs_send_body.setContactUsBody(username_text_input.getEditText().getText().toString()
                    , email_text_input.getEditText().getText().toString()
                    , userPhone_text_input.getEditText().getText().toString()
                    , comment_text_input.getEditText().getText().toString());

            contactUsApi.sendMsgToControllPanel(contactUs_send_body).enqueue(new Callback<ContactUs_Model>() {
                @Override
                public void onResponse(Call<ContactUs_Model> call, Response<ContactUs_Model> response) {

                    if (response.isSuccessful()){

                        if (response.body() != null && response.body().getMsg().equals("Message created")) {

                            spotsDialog.dismiss();

                            username_text_input.getEditText().setText("");
                            email_text_input.getEditText().setText("");
                            userPhone_text_input.getEditText().setText("");
                            comment_text_input.getEditText().setText("");

                            Toast.makeText(ContactUs.this, "تم الارسال بنجاح", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        spotsDialog.dismiss();

                        Toast.makeText(ContactUs.this, "يجب ادخال بريد ألكترونى صحيح .", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ContactUs_Model> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage() );
                }
            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        username_text_input.setAnimation(AnimationUtils.loadAnimation(this , R.anim.fade_transition_animation_right));
        email_text_input.setAnimation(AnimationUtils.loadAnimation(this , R.anim.fade_transition_animation_from_left));
        userPhone_text_input.setAnimation(AnimationUtils.loadAnimation(this , R.anim.fade_transition_animation_right));
        comment_text_input.setAnimation(AnimationUtils.loadAnimation(this , R.anim.fade_transition_animation_from_left));


        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            runOnUiThread(() -> DoAnimation(Contactus_Logo , getApplicationContext()));
            //Do something after 100ms
        }, 100);

    }
}
