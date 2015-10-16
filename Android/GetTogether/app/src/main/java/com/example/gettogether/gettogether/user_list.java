package com.example.gettogether.gettogether;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;


public class user_list extends Activity {

    private ListView users_listview;
    private ArrayList<ParseUser> userList;
    public static ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_list);

        View usersList = findViewById(R.id.users_list);
        View root = usersList.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));

        user = ParseUser.getCurrentUser();

        users_listview = (ListView) findViewById(R.id.users_list);

        loadUserList();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //user.logOut();
    }

    private void loadUserList() {
        final ProgressDialog prog_dia = ProgressDialog.show(this, null, "Loading Friends...");

        //Query parse for friends of user.
        //MUST streamline, VERY inefficient query,
        //SHOULD query users out of current user's friends array,
        //NOT query ALL users and pull ones that have current user in each query's friends array
        ParseUser.getQuery().whereNotEqualTo("username", user.getUsername()).whereEqualTo("friends", user.getUsername()).findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                prog_dia.dismiss();
                if (list != null) {
                    if (list.size() == 0) {
                        Toast.makeText(user_list.this, "Welp, looks like you have no friends!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(user_list.this, "Friends found", Toast.LENGTH_SHORT).show();
                        userList = new ArrayList<ParseUser>(list);
                        users_listview.setAdapter(new userAdapter());
                        users_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Start chat activity with extra data with user that was clicked
                                Intent intent = new Intent(user_list.this, chat.class);
                                intent.putExtra("EXTRA_DATA", userList.get(position).getUsername());
                                startActivity(intent);
                            }
                        });
                    }
                } else {
                    Toast.makeText(user_list.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class userAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return userList.size();
        }
        @Override
        public String getItem(int arg0) {
            return userList.get(arg0).getUsername();
        }
        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int pos, View v, ViewGroup arg2) {
            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.userlist_item, null);
                String c = getItem(pos);
                TextView lbl = (TextView) v;
                lbl.setText(c);

            }
            return v;
        }

    }

}
