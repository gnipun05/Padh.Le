package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LoginRegisterActivity extends AppCompatActivity {
    int AUTHUI_REQ_CODE=10111;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            this.finish();
        }



        List<AuthUI.IdpConfig> provider = Arrays.asList(
//                new  AuthUI.IdpConfig.EmailBuilder().build()
                new AuthUI.IdpConfig.GoogleBuilder().build()
//                new AuthUI.IdpConfig.PhoneBuilder().build()
        );

        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(provider)
                .setTosAndPrivacyPolicyUrls("https://example.com", "https://example.com")
                .setAlwaysShowSignInMethodScreen(true)
                .build();
        startActivityForResult(intent, AUTHUI_REQ_CODE);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==AUTHUI_REQ_CODE){
            if(resultCode==RESULT_OK){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d("tag203","i m here"+user.getUid());

//                DocumentReference docIdRef = db.collection("users").document();
//                docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            DocumentSnapshot document = task.getResult();
//                            Log.d("Tag202", String.valueOf(document.getData()));
//                            if (document.exists()) {
//                                Log.d("TAG202", "Document exists!");
//                            } else {
//                                Log.d("TAG202", "Document does not exist!");
//                            }
//                        } else {
//                            Log.d("TAG", "Failed with: ", task.getException());
//                        }
//                    }
//
//                });
//                DocumentReference docRef = db.collection("users").document(user.getUid());
//                    docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                        @Override
//                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                            Log.d("myval",value.toString());
//                            if (value.exists()) {
//                                //update
//                                Log.d("myuser","exists");
//                            } else {
//                                //Insert
//                                Log.d("myuser","not exists");
//
//                            }
//                        }
//
//                    });
//                FirebaseFirestore firestoreDatabase= FirebaseFirestore.getInstance();
//                firestoreDatabase.collection("users")
//                        .whereEqualTo(user.getUid(), user.getUid())
//                        .get()
//                        .addOnCompleteListener(task -> {
//                            if (task.isSuccessful()) {
//                                if (task.getResult().getDocuments().size() > 0)
//                                // Here is your document with id
//                            }
//                        });

//                mAuth = FirebaseAuth.getInstance();
//                AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//                mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
//                        boolean isNewUser = task.getResult().getAdditionalUserInfo().isNewUser();
//                        if (isNewUser) {
//                            Log.d("TAG", "Is New User!");
//                        } else {
//                            Log.d("TAG", "Is Old User!");
//                        }
//                    }



//                });
//                db.collection("users").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                if(documentSnapshot.getId()==null)
//                                    Log.d("bhavya","yep yep");
//                                else
//                                    Log.d("bhavya",documentSnapshot.getData().toString());
//
//                            }
//
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("bhavya","boo yeah");
//                    }
//                });
                db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document!=null) {
                                Log.d("date28", "Document exists!");
                                Intent i=new Intent(LoginRegisterActivity.this,Form.class);
                                startActivity(i);


//                                Log.d("CatTAG", "onActivityResult: " + category);
//                                category = data.getStringExtra("category");

                                finish();

                            } else {
                                Log.d("date28", "Document does not exist!");
                            }
                        } else {
                            Log.d("date", "Failed with: ", task.getException());
                        }
                    }
                });
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
            }
            else{
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if(response == null)
                    Log.d("LoginActivity", "Login is cancelled by the user" );
                else
                    Log.e("LoginActivity", "Error: ", response.getError() );

            }

        }
    }
}