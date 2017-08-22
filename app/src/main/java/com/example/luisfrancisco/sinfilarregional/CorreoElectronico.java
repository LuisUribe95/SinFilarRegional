package com.example.luisfrancisco.sinfilarregional;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CorreoElectronico extends AppCompatActivity {


    Button createUser,login;
    EditText email,password;

    //String
    String userEmail,userPass;

    //Autentificacion firebase
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correo_electronico);

        createUser = (Button)findViewById(R.id.ButtonRegistrar);
        login = (Button)findViewById(R.id.buttonIngresar);

        email = (EditText)findViewById(R.id.editEmail);
        password = (EditText)findViewById(R.id.editPass);

        //assign instance


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user != null)
            {
               // startActivity(new Intent(CorreoElectronico.this, Main2Activity.class));
            }else
            {

            }
        };

        //click listeners
        createUser.setOnClickListener(v -> {




            userEmail = email.getText().toString().trim();
            userPass = password.getText().toString().trim();
            if(userEmail.equals("") || userPass.equals(""))
            {
                Toast.makeText(CorreoElectronico.this, "Se dejaron campos vacios", Toast.LENGTH_SHORT).show();
            }

            if(!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPass))
            {
                mAuth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // startActivity(new Intent(CorreoElectronico.this,Localizacion.class));
                        if(task.isSuccessful())
                            Toast.makeText(CorreoElectronico.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
                        else
                        {
                            Toast.makeText(CorreoElectronico.this, "Error al Crear el Usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        });

        //move to login
        login.setOnClickListener(v -> {
            //startActivity(new Intent(CorreoElectronico.this,Localizacion.class));

            userEmail = email.getText().toString().trim();
            userPass = password.getText().toString().trim();

            if(userEmail.equals("")||userPass.equals(""))
            {
                Toast.makeText(CorreoElectronico.this, "Existen Campos Vacios", Toast.LENGTH_SHORT).show();
            }


            if(!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPass))
                mAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //startActivity(new Intent(CorreoElectronico.this, Main2Activity.class));
                            Toast.makeText(CorreoElectronico.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CorreoElectronico.this, "Fallo al Ingresar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
}