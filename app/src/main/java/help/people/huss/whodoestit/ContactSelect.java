package help.people.huss.whodoestit;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.pm.PackageManager;

import java.util.ArrayList;

public class ContactSelect extends MainActivity{
 String user_selected_phonenumber;

    // creating the list view Object
    ListView contacts;
    Cursor cursor;
    ArrayList<String> StoreContacts;
    String name, phonenumber;
    public static final int RequestPermissionCode = 1;
    // getting numbers from a string
    public static String stripNonDigits(
            final CharSequence input /* inspired by seh's comment */){
        final StringBuilder sb = new StringBuilder(
                input.length() /* also inspired by seh's comment */);
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }
    // add a function to long press jus


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_select);
    //PLAN GET THE BUTTONS TO WORK FROM THE NEWBOSTON TUTORIAL
        // THEN FIND THE ALGORITHMN TO UPLOAD THE CONTACTS TO THE ADAPTOR
        // THEN BUILD THE FUNCTIONS OF THE ON CLICK EVENTS
        // new string to hold the contacts


       StoreContacts = new ArrayList<String>();
       // collecting the contacts
        EnableRuntimePermission();
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            StoreContacts.add(name + " "  + ":" + " " + phonenumber);
        }

        cursor.close();

        ListAdapter Contact_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,StoreContacts);
        ListView contacts_list =(ListView)findViewById(R.id.list);
        contacts_list.setAdapter(Contact_Adapter);
        // now we have to setup the onclick events
        // probably have to send as a bundle off intents
        // send a bundle across


        contacts_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // gives you information about the what item was clicked on
                        // i = postion of the key press
                        // the name is the value stored in the array
                        String name = String.valueOf(adapterView.getItemAtPosition(i));
                        // testing if I can capture the number coming from the button
                        int value = getIntent().getExtras().getInt("ha");
                        // Storing actual name
                        // modifying before the substring
                        // this deletes everything after
                        String Contact_name =  name.replaceAll(":.*", "");
                        // this pulls the number
                        String number  = stripNonDigits(name.substring(name.indexOf(":"),name.length()));
                        user_selected_phonenumber = number;
                        // just to keep it in a string fashion
                        String [] Incoming_numbers;
                        // getting all the numbers from the class
                        Incoming_numbers = getIntent().getStringArrayExtra("PhoneNumbers");
                        Intent switchback = new Intent(view.getContext(), MainActivity.class);

                        // converting the string to a number
                        // STILL MISTAKE FIGURE HOW TO CONVERT TO INTEGER CORRECLY

                        // testing to see what button it came from for names
                        // getting the contact

                        String first_Contact = getIntent().getStringExtra("FirstContact");
                        String second_Contact = getIntent().getStringExtra("SecondContact");
                        String third_contact = getIntent().getStringExtra("ThirdContact");
                        String fourth_contact = getIntent().getStringExtra("FourthContact");
                        String fifth_contact = getIntent().getStringExtra("FifthContact");
                        String sixth_contact = getIntent().getStringExtra("SixthContact");
                        String seventh_contact = getIntent().getStringExtra("SeventhContact");
                        String eighth_contact = getIntent().getStringExtra("EighthContact");
                        // if you got a low A or even a B STOP PROGRAMMING
                        // BIG YOU ALSO KNOW IF ANY CONTACT CHANGES THEN THE NUMBER SHOULD TOO


                        // just collecting the user saved tasked
                        String task =  getIntent().getStringExtra("Task");

                        // SETTING ONE DUMB ASS IF STATEMENT BECAUSE YOU PROLLY DID BAD ON YOU TEST
                        if (first_Contact != Contact_name && value == 1) {
                            switchback.putExtra("FirstContactName", Contact_name);
                            Incoming_numbers[value-1] = user_selected_phonenumber;
                        } else
                            switchback.putExtra("FirstContactName", first_Contact);
                        // u want the if else for each instance because it can send back if need be
                        if (second_Contact != Contact_name && value == 2) {
                            switchback.putExtra("SecondContactName", Contact_name);
                            // modifying the array of phonnumbers
                            Incoming_numbers[value-1] = user_selected_phonenumber;
                        } else
                            switchback.putExtra("SecondContactName", second_Contact);
                        if(third_contact!=Contact_name&&value==3){
                            switchback.putExtra("ThirdContactName", Contact_name);
                            // modifying the array of phonnumbers
                            Incoming_numbers[value-1] = user_selected_phonenumber;
                        }else
                            switchback.putExtra("ThirdContactName", third_contact);

                        if(fourth_contact!=Contact_name&&value==4){
                            switchback.putExtra("FourthContactName", Contact_name);
                            // modifying the array of phonnumbers
                            Incoming_numbers[value-1] = user_selected_phonenumber;
                        }else
                            switchback.putExtra("FourthContactName", fourth_contact);

                        if(fifth_contact!=Contact_name&&value==5){
                            switchback.putExtra("FifthContactName", Contact_name);
                            // modifying the array of phonnumbers
                            Incoming_numbers[value-1] = user_selected_phonenumber;
                        }else
                            switchback.putExtra("FifthContactName", fifth_contact);

                        if(sixth_contact!=Contact_name&&value==6){
                            switchback.putExtra("SixthContactName", Contact_name);
                            // modifying the array of phonnumbers
                            Incoming_numbers[value-1] = user_selected_phonenumber;
                        }else
                            switchback.putExtra("SixthContactName", sixth_contact);
                        if(seventh_contact!=Contact_name&&value==7){
                            switchback.putExtra("SeventhContactName", Contact_name);
                            // modifying the array of phonnumbers
                            Incoming_numbers[value-1] = user_selected_phonenumber;
                        }else
                            switchback.putExtra("SeventhContactName", seventh_contact);

                        if(eighth_contact!=Contact_name&&value==8){
                            switchback.putExtra("EighthContactName", Contact_name);
                            // modifying the array of phonnumbers
                            Incoming_numbers[value-1] = user_selected_phonenumber;
                        }else
                            switchback.putExtra("EighthContactName", eighth_contact);

                        // after the end of the if/else statements we send back the modified array
                        switchback.putExtra("Phonenumbers_changed", Incoming_numbers);
                        switchback.putExtra("Number", number);
                        switchback.putExtra("ButtonIdentification", value);
                        // just sending back the user task
                        switchback.putExtra("Task",task);

                        startActivity(switchback);



                    }
                }
        );

            }


    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                ContactSelect.this,
                Manifest.permission.READ_CONTACTS))
        {
                // u may need to un comment this if problems with permission
            //Toast.makeText(ContactSelect.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(ContactSelect.this,new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                   // Toast.makeText(ContactSelect.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(ContactSelect.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }



}



