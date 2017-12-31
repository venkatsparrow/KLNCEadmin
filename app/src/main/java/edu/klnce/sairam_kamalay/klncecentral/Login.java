package edu.klnce.sairam_kamalay.klncecentral;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText uemail;
    private EditText upassword;
    private Button Loginbtn;
    private ProgressDialog progressDialog;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        TextView signuptext;

        uemail = (EditText) findViewById(R.id.id_email);
        upassword =(EditText) findViewById(R.id.id_password);
        Loginbtn = (Button) findViewById(R.id.idloginbtn);




        Loginbtn.setOnClickListener(this);
    }

    public void userLogin()
    {
        String myemail = uemail.getText().toString().trim();
        String mypassword = upassword.getText().toString().trim();

        progressDialog = new ProgressDialog(this);


        if(TextUtils.isEmpty(myemail)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(mypassword)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(myemail,mypassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view==Loginbtn){
            userLogin();
        }
    }

}
