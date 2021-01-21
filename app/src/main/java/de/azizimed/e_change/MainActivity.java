package de.azizimed.e_change;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=   (EditText) findViewById(R.id.username);
        password=   (EditText) findViewById(R.id.password);
        repassword= (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.buttonsignup);
        DB= new DBHelper(this);
        signin= (Button) findViewById(R.id.signin);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if (user.equals(" ") || pass.equals(" ") || repass.equals(" "))
                    Toast.makeText(MainActivity.this, "remplir les champs svp", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Enregistrement avec succés", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Enregistrement échoué", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Utilisateur existe déja !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "les mots de passe ne sont pas identique", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
               startActivity(intent);
            }
        });
    }
}
