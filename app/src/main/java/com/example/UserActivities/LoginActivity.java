package com.example.UserActivities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MapActivities.MapActivity;
import com.example.soilsamplemanager.R;

public class LoginActivity extends AppCompatActivity {

    LoginValidation user;

    private EditText etEmail;
    private EditText etPassword;
    private View progressView;
    private View loginFormView;
    private TextView logInTextView;
    private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Set up the login form.
        etEmail = findViewById(R.id.emailEditText);
        logInTextView = findViewById(R.id.loadingTextView);
        TextView signUpTextView = findViewById(R.id.signUpTextView);
        etEmail.requestFocus();
        etPassword = findViewById(R.id.passwordEditText);
        etPassword.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        Button manualLoginButton = findViewById(R.id.manualLoginButton);
        manualLoginButton.setOnClickListener(v -> attemptLogin());
        signUpTextView.setOnClickListener(view -> goToSignUpActivity());
        loginFormView = findViewById(R.id.loginActivityConstrainLayout);
        progressView = findViewById(R.id.progressBar);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void attemptLogin() {
        hideKeyboard();
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        etEmail.setError(null);
        etPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        user = new LoginValidation(email, password);
        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.

        if (!user.isEmailNotEmpty()) {
            etEmail.setError("This field is required");
            focusView = etEmail;
            cancel = true;
        } else if (!user.isEmailValid()) {
            etEmail.setError("Please enter a valid email.");
            focusView = etEmail;
            cancel = true;
        }


        // Check for a valid password, if the user entered one.
        if (!user.isPasswordNotEmpty()) {
            etPassword.setError("This field is required");
            focusView = etPassword;
            cancel = true;
        } else if (!user.isPasswordLengthEnough()) {
            etPassword.setError("Your password should be at least 6 characters");
            focusView = etPassword;
            cancel = true;
        }
        //TODO check database for user validation

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);


        }
    }


    /**
     * Represents an asynchronous login task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            loginFormView.setVisibility(View.INVISIBLE);
            progressView.setVisibility(View.VISIBLE);
            logInTextView.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... params) {


            try {
                LoginController loginController = new LoginController();
                if (loginController.emailExists(mEmail)) {
                    if (loginController.emailMatchesPassword(mEmail, mPassword)) {
                        //TODO: Login

                    } else {
                        Toast.makeText(LoginActivity.this, "Your email and password do not match.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "The email " + mEmail + " do not exist.", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {

                goToMapsActivity();

            } else {
                Toast.makeText(LoginActivity.this, "Something went wrong! Please try again", Toast.LENGTH_SHORT).show();
                loginFormView.setVisibility(View.VISIBLE);
                progressView.setVisibility(View.INVISIBLE);
                logInTextView.setVisibility(View.INVISIBLE);
                mAuthTask = null;
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    public void goToSignUpActivity() {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
        finish();

    }

    public void goToMapsActivity() {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
        finish();
    }
}