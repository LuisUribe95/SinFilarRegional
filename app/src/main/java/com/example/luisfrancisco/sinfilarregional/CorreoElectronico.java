package com.example.luisfrancisco.sinfilarregional;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
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
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                {
                    startActivity(new Intent(CorreoElectronico.this, Localizacion.class));
                }else
                {

                }
            }
        };

        //click listeners
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                userEmail = email.getText().toString().trim();
                userPass = password.getText().toString().trim();

                if(!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPass))
                {
                    mAuth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                                Toast.makeText(CorreoElectronico.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
                               // startActivity(new Intent(CorreoElectronico.this,Localizacion.class));
                            }
                            else
                            {
                                Toast.makeText(CorreoElectronico.this, "Error al Crear el Usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });

        //move to login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(CorreoElectronico.this,Localizacion.class));

                userEmail = email.getText().toString().trim();
                userPass = password.getText().toString().trim();

                if(!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPass))
                {
                    mAuth.signInWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(CorreoElectronico.this,Localizacion.class));
                                Toast.makeText(CorreoElectronico.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(CorreoElectronico.this, "Fallo al Ingresar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
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