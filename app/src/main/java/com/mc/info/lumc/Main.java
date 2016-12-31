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

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*DBHandler dbHandler = new DBHandler(this,null,null,1);

        String[][] doctors = {
                {"Abdallah","Adra","Obstetrics and Gynecology, Maternal Fetal Medicine","City Complex","Meena",
                        "Tripoli",
                        "+961-1-350000 ext. 5620 / +961-6-438437",
                        "aa107@aub.edu.lb"},
                {"Abdallah","Rebeiz",
                        "Cardiology",
                        "AUBMC, Phase 1, 3rd floor",
                        "Maamari Street",
                        "Beirut",
                        "+961-1-350000 ext. 5800",
                        "ar20@aub.edu.lb"},
                {"Abdul Latif","Hamdan",
                        "Otolaryngology - H&N Surgery - Voice",
                        "AUBMC, Pierre Y. Abou Khater (Fahed) Building, (Bldg. 23), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 7480",
                        "Email: ah77@aub.edu.lb"},
                {"Abdel Rahman","Bizri",
                        "Infectious Diseases",
                        "AUBMC, Pierre Y. Abou Khater (Fahed) Building, (Bldg. 23), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: arbizri@aub.edu.lb"},
                {"Abdul-Ghani","Kibbi",
                        "Dermatology",
                        "AUBMC, Pierre Y. Abou Khater (Fahed) Building, (Bldg. 23), 3rd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 7695",
                        "Email: agkibbi@aub.edu.lb"},
                {"Adel","Berbari",
                        "Nephrology",
                        "AUBMC, Pierre Y. Abou Khater (Fahed) Building, (Bldg. 23), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: ab01@aub.edu.lb"},
                {"Adel","Birbari",
                        "Physiology",
                        "AUBMC, DTS, 2nd floor",
                        "John Kennedy Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 4800/5378",
                        "Email: ab01@aub.edu.lb"},
                {"Adnan","Mroueh",
                        "Obstetrics and Gynecology, Infertility/ Endocrinology",
                        "Daouk Bldg, 4th floor",
                        "864 Kennedy St",
                        "Beirut",
                        "Phone: +961 1 369161",
                        "Email: am36@aub.edu.lb"},
                {"Afif","Mufarrij",
                        "Emergency Medicine",
                        "AUBMC, Phase 1, Ground floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 6636",
                        "Email: am66@aub.edu.lb"},
                {"Aghiad","Al-Kutoubi",
                        "Body Imaging, Diagnostic Radiology, and Interventional and Cross Sectional Radiology",
                        "AUBMC, Phase 2, B floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5020/5060",
                        "Email: mk00@aub.edu.lb"},
                {"Ahmad","Beydoun",
                        "Neurology/ Epilepsy",
                        "AUBMC, Phase 1, 3rd floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: ab29@aub.edu.lb"},
                {"Ahmad","Husari",
                        "Pulmonary, Critical Care and Sleep Medicine",
                        "AUBMC, Phase 1, 3rd floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: ah51@aub.edu.lb"},
                {"Ahmad","Mansour",
                        "Ophthalmology, Vitreoretinal",
                        "Blue Bldg., 3rd floor",
                        "Abdel Aziz Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 7694",
                        "Email: am37@aub.edu.lb"},
                {"Ahmad","Tayim",
                        "Orthopedic Surgery",
                        "AUBMC, Phase 1, 3rd floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: at01@aub.edu.lb"},
                {"Alaa","Sharara",
                        "Gastroenterology",
                        "AUBMC, Pierre Y. Abou Khater (Fahed) Building, (Bldg. 23), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: as08@aub.edu.lb"},
                {"Ali","Abu-Alfa",
                        "Nephrology",
                        "AUBMC, Pierre Y. Abou Khater (Fahed) Building, (Bldg. 23), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: abualfa@aub.edu.lb"},
                {"Ali","Bazarbachi",
                        "Hematology/Oncology",
                        "AUBMC, Naef K. Basile Cancer Institute, (Bldg.56), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: bazarbac@aub.edu.lb"},
                {"Ali","Hallal",
                        "General Surgery, Trauma and Critical Care, Upper Gastrointestinal surgery",
                        "AUBMC, Phase 1, 4th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: ah05@aub.edu.lb"},
                {"Ali","Haydar",
                        "Body Imaging, Interventional and Cross Sectional Radiology",
                        "AUBMC, Phase 2, B floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5020/5060",
                        "Email: ah24@aub.edu.lb"},
                {"Ali","Khalil",
                        "Obstetrics and Gynecology, Gynecologic Oncology",
                        "Zakka Bldg., 1st floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: 961-1-343609 / 961-1-747434",
                        "Email: ak27@aub.edu.lb"},
                {"Ali","Shamseddine",
                        "Hematology/Oncology",
                        "AUBMC, Naef K. Basile Cancer Institute, (Bldg.56), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: as04@aub.edu.lb"},
                {"Ali","Taher",
                        "Hematology/Oncology",
                        "AUBMC, Naef K. Basile Cancer Institute, (Bldg.56), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: ataher@aub.edu.lb"},
                {"Mohammad Ali","Natout",
                        "Otolaryngology - H&N Surgery",
                        "AUBMC, Phase 1, 6th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5470",
                        "Email: mn47@aub.edu.lb"},
                {"Alia","Dabbous",
                        "Anesthesiology",
                        "AUBMC, Phase 2, B floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 6380",
                        "Email: ad00@aub.edu.lb"},
                {"Antoine","Abchee",
                        "Cardiology",
                        "AUBMC, Phase 1, 3rd floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: aa14@aub.edu.lb"},
                {"Antoine","Abu-Musa",
                        "Obstetrics and Gynecology, Reproductive Endocrinology and Infertility",
                        "AUBMC, Phase 1, 7th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-759619 / +961-1-350000 ext. 5840",
                        "Email: aa06@aub.edu.lb"},
                {"Antoine","Hannoun",
                        "Obstetrics and Gynecology, Reproductive Endocrinology and Infertility",
                        "AUBMC, Phase 1, 7th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-759619 / +961-1-350000 ext. 5840",
                        "Email: ahannoun@aub.edu.lb"},
                {"Anwar","Nassar",
                        "Obstetrics and Gynecology, Maternal Fetal Medicine",
                        "AUBMC, Phase 1, 7th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-759619 / +961-1-350000 ext. 5840",
                        "Email: an21@aub.edu.lb"},
                {"Arafat","Tfayli",
                        "Hematology/Oncology",
                        "AUBMC, Naef K. Basile Cancer Institute, (Bldg.56), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: at35@aub.edu.lb"},
                {"Asad","Zeidan",
                        "Physiology",
                        "AUBMC, DTS, 2nd floor",
                        "John Kennedy Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 4818",
                        "Email: az29@aub.edu.lb"},
                {"Asma","Arabi",
                        "Endocrinology",
                        "AUBMC, Pierre Y. Abou Khater (Fahed) Building, (Bldg. 23), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: aa22@aub.edu.lb"},
                {"Assaad","Eid",
                        "Physiology",
                        "AUBMC, DTS, 1st floor",
                        "John Kennedy Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 4781",
                        "Email: ae49@aub.edu.lb"},
                {"Assaad","Soweid",
                        "Gastroenterology",
                        "AUBMC, Pierre Y. Abou Khater (Fahed) Building, (Bldg. 23), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: as25@aub.edu.lb"},
                {"Assad","Taha",
                        "Orthopedic Surgery",
                        "AUBMC, Phase 1, 4th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-344397",
                        "Email: at25@aub.edu.lb"},
                {"Ayman","Tawil",
                        "Anatomic Pathology & Cytology",
                        "AUBMC, Phase 2, 3rd floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5176",
                        "Email: at04@aub.edu.lb"},
                {"Baha","Noureddin",
                        "Ophthalmology, Glaucoma/Cataract",
                        "AUBMC, Phase 1, 7th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5581/2",
                        "Email: bndean@aub.edu.lb"},
                {"Bassem","Saab",
                        "Family Medicine",
                        "AUBMC, Building 56, 1st floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 3013",
                        "Email: brsaab@aub.edu.lb"},
                {"Bassem","Safadi",
                        "General Surgery, Bariatric Surgery",
                        "AUBMC, Phase 1, 3rd floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: bs21@aub.edu.lb"},
                {"Bassem","Yamout",
                        "Neurology",
                        "Doctor Center, 3rd floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-344042",
                        "Email: by02@aub.edu.lb"},
                {"Beatrice","Khater",
                        "Family Medicine",
                        "AUBMC, Building 56, 1st floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 3013",
                        "Email: bk01@aub.edu.lb"},
                {"Bernard","Abi-Saleh",
                        "Cardiology",
                        "AUBMC, Phase 1, 3rd floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: ba47@aub.edu.lb"},
                {"Bernard","Sagherian",
                        "Orthopedic Surgery",
                        "AUBMC, Phase 1, 4th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: bs05@aub.edu.lb"},
                {"Bishara","Atiyeh",
                        "Plastic Surgery",
                        "Marignan, 6th floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-746136",
                        "Email: ba24@aub.edu.lb"},
                {"Brigitte","Khoury",
                        "Psychology, Clinical Psychology, Adult",
                        "AUBMC, Building 56, 3rd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5650",
                        "Email: bk03@aub.edu.lb"},
                {"Carine","Zeeni",
                        "Anesthesiology",
                        "AUBMC, Phase 2, B floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 6380",
                        "Email: cz07@aub.edu.lb"},
                {"Chakib","Ayoub",
                        "Anesthesiology",
                        "AUBMC, Phase 2, B floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 6380",
                        "Email: ca04@aub.edu.lb"},
                {"Chantal","Farra",
                        "Medical Genetics",
                        "AUBMC, Phase 2, 3rd floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5247",
                        "Email: cf07@aub.edu.lb"},
                {"Christiane","Haddad",
                        "Ophthalmology, Pediatric Ophthalmology and Strabismus",
                        "AUBMC, Phase 1, 7th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5581/2",
                        "Email: ca12@aub.edu.lb"},
                {"Diana","Rahme",
                        "Family Medicine",
                        "AUBMC, Building 56, 1st floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 3013",
                        "Email: ds07@aub.edu.lb"},
                {"Durriyah","Sinno",
                        "General Pediatrics",
                        "AUBMC, (Bldg.56), 6th floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 7521/7524",
                        "Email: ds03@aub.edu.lb"},
                {"Edward","Khawam",
                        "Ophthalmology, Pediatric Ophthalmology and Strabismus",
                        "Baalbaki Bldg., 4th floor",
                        "Abdel Aziz Street",
                        "Beirut",
                        "Phone: +961-1-341950",
                        "Email: ek16@aub.edu.lb"},
                {"Elias","Rahal",
                        "Microbiologist/Immunologist",
                        "Decco and Allam Bldg., 2nd floor",
                        "Pherdaws Street",
                        "Sabtieh",
                        "Phone: +961-1-350000 ext. 5135",
                        "Email: er00@aub.edu.lb"},
                {"Elizabeth","Baz",
                        "Blood Banking & Transfusion Medicine",
                        "AUBMC, Phase 2, 3rd floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5230",
                        "Email: ek01@aub.edu.lb"},
                {"Eveline","Hitti",
                        "Emergency Medicine",
                        "AUBMC, Phase 1, Ground floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 6639",
                        "Email: eh16@aub.edu.lb"},
                {"Fadi","Bitar",
                        "Pediatric Cardiology",
                        "AUBMC, Phase 1, 6th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5881",
                        "Email: saad@aub.edu.lb"},
                {"Fadi","El-Merhi",
                        "Body Imaging, Interventional and Cross Sectional Radiology",
                        "AUBMC, Phase 2, B floor",
                        "Cairo Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5020/5060",
                        "Email: fe19@aub.edu.lb"},
                {"Fadi","Mirza",
                        "Obstetrics and Gynecology, Maternal Fetal Medicine",
                        "AUBMC, Phase 1, 7th floor",
                        "Maamari Street",
                        "Beirut",
                        "Phone: +961-1-759619 / +961-1-350000 ext. 5840",
                        "Email: fmirza@aub.edu.lb"},
                {"Fadi","Mourad",
                        "Gastroenterology",
                        "AUBMC, Pierre Y. Abou Khater (Fahed) Building, (Bldg. 23), 2nd floor",
                        "Clémenceau Street",
                        "Beirut",
                        "Phone: +961-1-350000 ext. 5800",
                        "Email: fmourad@aub.edu.lb"}
        };
        for(String[] d : doctors ){
            Doctor doc= new Doctor(0,d[0],d[1],d[6],d[7],new Address(d[5],d[4],d[3]),d[2],0);
            dbHandler.addDoctor(doc);
        }
        //String phone, String email, Address address, String specialty, int experienceYears

        Patient p = new Patient(0,"Mariam","Hakim","70427114","mariam@hotmail.com",new Address("Beirut","Radouf","BlockF"));
        Patient p1 = new Patient(0,"Fatima","Fneish","70580923","fatima@hotmail.com",new Address("Beirut","BeerAlAbed","..."));
        Patient p2 = new Patient(0,"Mariana","Termos","70705925","mariana@hotmail.com",new Address("Beirut","Rwes","..."));
        Patient p3 = new Patient(0,"Mahdi","Saab","70886986","mahdi@hotmail.com",new Address("Beirut","Tohwita","..."));
        Patient p4 = new Patient(0,"Majed","Jaber","76313392","majed@hotmail.com",new Address("Beirut","BorojAlBarajneh","..."));
        Patient p5 = new Patient(0,"Jaafar","Hijazi","76688388","jaafar@hotmail.com",new Address("Beirut","CoCoDi","Hijazi"));
        Patient p6 = new Patient(0,"Haidar","Mansour","71342330","haidar@hotmail.com",new Address("Beirut","Khalde","..."));
        Patient p7 = new Patient(0,"Mostafa","Hammoud","70197610","mostafa@hotmail.com",new Address("Beirut","Ouzai","..."));
        Patient p8 = new Patient(0,"Rania","Taleb","70923800","rania@hotmail.com",new Address("Beirut","BeerHasan","Taleb"));
        dbHandler.addPatient(p);
        dbHandler.addPatient(p1);
        dbHandler.addPatient(p2);
        dbHandler.addPatient(p3);
        dbHandler.addPatient(p4);
        dbHandler.addPatient(p5);
        dbHandler.addPatient(p6);
        dbHandler.addPatient(p7);
        dbHandler.addPatient(p8);
*/
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.main_nav) ;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                unCheckAllMenuItems(navigationView);
                item.setChecked(true);
                if(item.getItemId()==R.id.drwrViewDoctors)
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

    public void signIn(View view){
        startActivity(new Intent(this,LoginActivity.class));
    }
}
