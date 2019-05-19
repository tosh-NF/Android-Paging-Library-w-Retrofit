package com.cbwit.genius_challenge.Features.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cbwit.genius_challenge.Features.Adapter.RecyclerViewAdapter;
import com.cbwit.genius_challenge.Features.Model.POJO.NewUserResponse;
import com.cbwit.genius_challenge.Features.Model.POJO.User;
import com.cbwit.genius_challenge.Features.ViewModel.UserViewModel;
import com.cbwit.genius_challenge.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String TAG = "MAIN_ACTIVITY";
    private final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
    private UserViewModel userViewModel;
    private android.app.AlertDialog.Builder alert,alert2 ;
    private TextView name, job;
    private String nameInput, jobInput;
    private AlertDialog newUserForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        alert2 = new AlertDialog.Builder(MainActivity.this);
        alert = new AlertDialog.Builder(MainActivity.this);


        userViewModel.getUserPagedList().observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                /*
                submit response to pagedlist adapter
                 */
                if(users!=null)
                    if(!users.isEmpty())
                    {
                        adapter.submitList(users);
                    }
            }
        });

        userViewModel.getmResponse().observe(this, new Observer<Response<NewUserResponse>>() {
            @Override
            public void onChanged(Response<NewUserResponse> response) {
                /*
                set API response to dialog message
                 */
                userViewModel.responseMessage(response,alert2);
                alert2.create().show();
                alert2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                }
            });

        FloatingActionButton Fab =findViewById(R.id.floatingActionButton);
        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Ask user for call parameters on click
                 */

              alert.setTitle("Create A New User");
              alert.setView(R.layout.newuserform);
              alert.setPositiveButton("CREATE", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      /*
                      create a new user response object
                     */
                      name = newUserForm.findViewById(R.id.nameField);
                      nameInput = name.getText().toString();

                      job = newUserForm.findViewById(R.id.jobField);
                      jobInput = job.getText().toString();

                      NewUserResponse post = new NewUserResponse(nameInput, jobInput);

                      /*
                      enque
                       */
                      userViewModel.addUsers(post);
                  }
              });

            newUserForm  = alert.create();
            newUserForm.show();

            }
        });

        recyclerView.setAdapter(adapter);
}
}
