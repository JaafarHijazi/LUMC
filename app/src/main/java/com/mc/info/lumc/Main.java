package com.mc.info.lumc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    CircleImageView profile;
    TextView username;
    TextView email;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.main_nav) ;
        View header = navigationView.getHeaderView(0);
        profile = (CircleImageView) header.findViewById(R.id.login_header_profile);
        username = (TextView) header.findViewById(R.id.login_header_username);
        email = (TextView) header.findViewById(R.id.login_header_email);
        loginButton= (Button) header.findViewById(R.id.login_header_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this,LoginActivity.class));
       /*         SmartLoginBuilder loginBuilder = new SmartLoginBuilder();
                Intent intent = loginBuilder.with(getApplicationContext())
                        .setAppLogo(R.mipmap.ic_launcher)
                        .isFacebookLoginEnabled(false)
                        //.isFacebookLoginEnabled(true).withFacebookAppId("APP_ID")
                       // .withFacebookPermissions(PERMISSIONS)
                        .isCustomLoginEnabled(true)
                        .setSmartCustomLoginHelper(new SmartCustomLoginListener() {
                            @Override
                            public boolean customSignin(SmartUser smartUser) {
                                //TODO
                                smartUser.setBirthday("1/1/1993");
                                return true;
                            }

                            @Override
                            public boolean customSignup(SmartUser smartUser) {
                                //TODO
                                return false;
                            }

                            @Override
                            public boolean customUserSignout(SmartUser smartUser) {
                                //TODO
                                return false;
                            }
                        })
                        .isGoogleLoginEnabled(true)
                        .build();
                startActivityForResult(intent, SmartLoginConfig.LOGIN_REQUEST);*/
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                unCheckAllMenuItems(navigationView);
                item.setChecked(true);
                if(item.getItemId()== R.id.drwrViewDoctors)
                    startActivity(new Intent(Main.this, ListDoctors.class));
                else if(item.getItemId()==R.id.drwrViewPatients)
                    startActivity(new Intent(Main.this, ListPatients.class));
                return true;
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void unCheckAllMenuItems(NavigationView navigationView) {
        final Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.hasSubMenu()) {
                SubMenu subMenu = item.getSubMenu();
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    subMenuItem.setChecked(false);
                }
            } else {
                item.setChecked(false);
            }
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        //Intent "data" contains the user object
//        if(resultCode == SmartLoginConfig.FACEBOOK_LOGIN_REQUEST){
//            SmartFacebookUser user;
//            try {
//                user = data.getParcelableExtra(SmartLoginConfig.USER);
//                //use this user object as per your requirement
//            }catch (Exception e){
//                Log.e(getClass().getSimpleName(), e.getMessage());
//            }
//        }else if(resultCode == SmartLoginConfig.GOOGLE_LOGIN_REQUEST){
//            SmartGoogleUser user;
//            try {
//                user = data.getParcelableExtra(SmartLoginConfig.USER);
//                //use this user object as per your requirement
//            }catch (Exception e){
//                Log.e(getClass().getSimpleName(), e.getMessage());
//            }
//        }else if(resultCode == SmartLoginConfig.CUSTOM_LOGIN_REQUEST){
//            SmartUser user = data.getParcelableExtra(SmartLoginConfig.USER);
//            user.describeContents();
//            //use this user object as per your requirement
//        }else if(resultCode == RESULT_CANCELED){
//            //Login Failed
//        }
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    /*public void goToListDoctors(View view){
        Intent i = new Intent(this,ListDoctors.class);
        startActivity(i);
    }

    public void goToListPatients(View view){
        Intent i = new Intent(this,ListPatients.class);
        startActivity(i);
    }*/

}
