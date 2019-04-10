package tech.fnplus.enrollme;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import org.aviran.cookiebar2.CookieBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressdialog;
    LinearLayout cardView;
    FirebaseFirestore db;
    DatePickerDialog.OnDateSetListener datePickerDialogListener;
    GoogleSignInClient mGoogleSignInClient;
    public static int RC_SIGN_IN = 1;
    public static String TAG = "Register Activity";
    Button createAccountBtn;
    EditText mobileNumberET, personalWebET, linkedinET, cityET, territoryET, addressET, q1ET, q2ET, q3ET, q4ET;
    CircleImageView avatarImage;
    ConstraintLayout registerLayout;
    ScrollView registerDetailsLayout;
    TextView userEmailTV, greetingsTV, dateOfBirthET, dateContainer;
    String userEmail, displayName, mobileNumber, date, genderTV, personalSite, linkedIn, city, territory, address, q1, q2, q3, q4;
    String googleToken = "";
    Uri userImage;
    ImageButton closeButtonDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_login);
        registerDetailsLayout = findViewById(R.id.registerDetails_layout);
        registerLayout = findViewById(R.id.register_layout);
        userEmailTV = findViewById(R.id.userEmail_TV);
        avatarImage = findViewById(R.id.imageButton);
        greetingsTV = findViewById(R.id.username_TV);
        dateOfBirthET = findViewById(R.id.date_text);
        dateContainer = findViewById(R.id.date_container);
        personalWebET = findViewById(R.id.personalSite_text);
        linkedinET = findViewById(R.id.linkedin_text);
        cityET = findViewById(R.id.city_text);
        territoryET = findViewById(R.id.territory_text);
        addressET = findViewById(R.id.address_text);
        q1ET = findViewById(R.id.why_join_text);
        q2ET = findViewById(R.id.inspiration_text);
        q3ET = findViewById(R.id.technologies_text);
        q4ET = findViewById(R.id.project_text);
        createAccountBtn = findViewById(R.id.button);
        registerLayout.setVisibility(View.VISIBLE);
        registerDetailsLayout.setVisibility(View.GONE);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        closeButtonDetails = findViewById(R.id.close_btn_registerDetails);
        cardView = findViewById(R.id.frameLayout);


        final Spinner gender = findViewById(R.id.gender_text);

        // Spinner click listener
        gender.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("Select your gender:");
        categories.add("Male");
        categories.add("Female");
        categories.add("Others");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        gender.setAdapter(dataAdapter);


        signInButton.setSize(SignInButton.SIZE_WIDE);


        progressdialog = new ProgressDialog(LoginActivity.this);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressdialog.setMessage("Signing you in ...");
                progressdialog.setIndeterminate(true);
                progressdialog.show();
                signIn();
            }
        });


        closeButtonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerLayout.setVisibility(View.VISIBLE);
                registerDetailsLayout.setVisibility(View.GONE);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(LoginActivity.this,
                        datePickerDialogListener,
                        year, month, day);
                dialog.getWindow();
                dialog.show();
                dateContainer.setVisibility(View.VISIBLE);
            }
        });
        datePickerDialogListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date = day + "/" + month + "/" + year;
                dateOfBirthET.setText(date);
            }
        };
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser = mAuth.getCurrentUser();
                mobileNumber = mobileNumberET.getText().toString();
                personalSite = personalWebET.getText().toString();
                linkedIn = linkedinET.getText().toString();
                city = cityET.getText().toString();
                territory = territoryET.getText().toString();
                address = addressET.getText().toString();
                q1 = q1ET.getText().toString();
                q2 = q2ET.getText().toString();
                q3 = q3ET.getText().toString();
                q4 = q4ET.getText().toString();
                date = dateOfBirthET.getText().toString();
                userEmail = userEmailTV.getText().toString();
                if (q1.length() > 0 ||
                        q2.length() > 0 ||
                        q3.length() > 0 ||
                        q4.length() > 0 ||
                        mobileNumber.length() > 0 ||
                        personalSite.length() > 0 ||
                        linkedIn.length() > 0 ||
                        city.length() > 0 ||
                        territory.length() > 0 ||
                        address.length() > 0 ||
                        date.length() > 0 ||
                        genderTV.length() > 0
                ) {
                    progressdialog.setMessage("Creating account, please wait..");
                    progressdialog.setIndeterminate(true);
                    progressdialog.show();
                    addDataToDB();
                } else {
                    CookieBar.build(LoginActivity.this)
                            .setTitle("Cannot register")
                            .setMessage("Please fill all the required fields and try again.")
                            .setBackgroundColor(R.color.colorPrimary)
                            .setCookiePosition(CookieBar.TOP)
                            .show();
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        genderTV = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void addDataToDB() {
        Map<String, Object> user = new HashMap<>();

        user.put("mobileNumber", mobileNumber);
        user.put("dateOfBirth", date);
        user.put("email", userEmail);
        user.put("verified", "false");
        user.put("userId", mUser.getUid());
        user.put("displayName", displayName);
        user.put("displayImage", userImage.toString());

        db.collection("users").document(mUser.getUid())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        progressdialog.dismiss();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }


    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(<Enter Google Sign In Token Here>)//)
            .requestEmail()
            .build();


    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                mAuth = FirebaseAuth.getInstance();
                firebaseAuthWithGoogle(account);
                userEmail = account.getEmail();
                displayName = account.getDisplayName();
                userImage = account.getPhotoUrl();


            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                progressdialog.dismiss();
                CookieBar.build(LoginActivity.this)
                        .setTitle("Google sign in failed")
                        .setMessage("Please check your internet connection and try again.")
                        .setBackgroundColor(R.color.colorPrimary)
                        .setCookiePosition(CookieBar.TOP)
                        .show();
                Log.w(TAG, "Google sign in failed", e);

            }
        }

    }

    private boolean checkUser() {
        final boolean[] isNew = {false};
        db.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {

                                isNew[0] = false;

                            }


                        } else {
                            isNew[0] = true;
                        }
                    }
                });

        return isNew[0];
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        googleToken = acct.getIdToken();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();
                            mUser = mAuth.getCurrentUser();
                            if (isNew || !checkUser()) {
                                registerLayout.setVisibility(View.GONE);
                                registerDetailsLayout.setVisibility(View.VISIBLE);
                                userEmailTV.setText(userEmail);
                                greetingsTV.setText("Welcome, " + displayName);
                                Picasso.get()
                                        .load(userImage)
                                        .into(avatarImage);
                            } else if (checkUser()) {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }

                            progressdialog.dismiss();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuth.getCurrentUser() != null && checkUser()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

        }

    }
}
