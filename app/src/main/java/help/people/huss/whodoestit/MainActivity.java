package help.people.huss.whodoestit;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.Manifest.permission.SEND_SMS;

public class MainActivity extends AppCompatActivity {

// testing if first time the app has been installed
private boolean isFirstTime()
{
    SharedPreferences preferences = getPreferences(MODE_PRIVATE);
    boolean ranBefore = preferences.getBoolean("RanBefore", false);
    if (!ranBefore) {
        // first time
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("RanBefore", true);
        editor.commit();
    }
    return !ranBefore;
}
    boolean displayed_directions = false;

    // permission request codes
    private static final int REQUEST_SMS = 0;
    private static final int REQ_PICK_CONTACT = 2 ;
    // we can immediatlty test if the vector was changed from startup
    // just two now for testing purposes
    // 8 contacts
    String [] numbers = new String []{"nothing","nothing","nothing","nothing","nothing","nothing","nothing","nothing"};
    boolean [] reset_flags = new boolean []{false,false,false,false,false,false,false,false};


    // function for sending the sms messages
    // we can generate our random phone number just have to figure how to send the sms
    public void sendSMS(String phoneNo, String msg) {
        // creating a instance of the sms mananger
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, msg, null, null);
        // adding the permission
        ActivityCompat.requestPermissions(this,new String[]{SEND_SMS},1);
    }

    // function takes input of phone numbers and chooses one
    public String  choose_random_number(String nums[]) {
        // what we are going to do is get the array and generate a random number for it
        // declared out here because of scope issues
        String new_number;
        // using the do while so no zero
        // add a counter here to see how long the array has value to chppse numbers
        //INCLUDE BACK UP WHAT IF THE USER CLICKS THE SEND BUTTON FIRST
        do {
            // choosing the random number
            // randomizing algorthimn is the problem
            Random rand = new Random();
                // plus is the minimum and the inside of the paranthesis is the maximum
            int random = rand.nextInt(7) + 0;

            // selecting the number
            new_number = nums[random];
            // replace later stupid error
            // u need something with variiable size
            // exits loop when number doesnt equal sero.
        } while (new_number.equals("nothing"));
        // add no numbers have been selected toast because it could just sit there forever

        return new_number;
// the main problem seems to be the random number algorthmnn
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // DIRECTIONS
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

// inserting the tutorial in here also
                String message = "Click each button to add a contact, hold down each button to delete, spin the wheel to send the message!";
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Directions")
                        .setMessage(message)
                        .setPositiveButton("ok", null)
                        .show();

            }

        });
        // if the user opnened up the application for the first time



        // testing here if the array was changed
        if (getIntent().getStringArrayExtra("Phonenumbers_changed") != null) {
            // changing
            numbers = getIntent().getStringArrayExtra("Phonenumbers_changed");
            // just submit it to true because the user allready saw ir
            displayed_directions = true;

        }
        // seperate if statement for the

        // getting the view of the widgets
        final EditText message_collect = (EditText) findViewById(R.id.textView);
        final Button firstcontact = (Button) findViewById(R.id.button);
        final Button secondcontact = (Button) findViewById(R.id.button2);
        final Button thirdcontact = (Button) findViewById(R.id.button3);
        // new ones today
        final Button fourthcontact = (Button) findViewById(R.id.button4);
        final Button fifthcontact = (Button) findViewById(R.id.button5);
        final Button sixthcontact = (Button) findViewById(R.id.button6);
        final ImageButton mainbutton = (ImageButton) findViewById(R.id.StartAction);
        final Button seventhcontact = (Button) findViewById(R.id.button7);
        final Button eighthcontact = (Button) findViewById(R.id.button8);
        // now we must connect the text view so we can save the text we wrote to it
        // have to just bounce the text around every button press
        final TextView task_window = (TextView) findViewById(R.id.textView);
        // creating the function to delete contacts



            //creating the directions
        // creating flag to delete the dialoge
            if (isFirstTime()) {
                String message = "Click each button to add a contact, hold down each button to delete, spin the wheel to send the message!";
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Directions")
                        .setMessage(message)
                        .setPositiveButton("ok", null)
                        .show();
            }



        firstcontact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent changing = new Intent(view.getContext(), ContactSelect.class);
                        // storing extra values in this bundle
                        changing.putExtra("ha", 1);
                        // so if the button name was changed start sending it back and forth
                        // cycling the data no storage
                        if (getIntent().getExtras() != null) {
                            // setting the flags for the contacts
                            // intercepting so we can add the nothing if the user deletes because the flag array should reset every activity starr up
                            if (reset_flags[0]==false)
                                changing.putExtra("FirstContact", getIntent().getExtras().getString("FirstContactName", "nothing"));
                            else
                                changing.putExtra("FirstContact","nothing");

                            if(reset_flags[1]==false)
                                changing.putExtra("SecondContact", getIntent().getExtras().getString("SecondContactName", "nothing"));
                            else
                                changing.putExtra("SecondContact","nothing");

                            if (reset_flags[2]==false)
                                changing.putExtra("ThirdContact", getIntent().getExtras().getString("ThirdContactName", "nothing"));
                            else
                                changing.putExtra("ThirdContact","nothing");

                            if (reset_flags[3]==false)
                                changing.putExtra("FourthContact", getIntent().getExtras().getString("FourthContactName", "nothing"));
                            else
                                changing.putExtra("FourthContact","nothing");

                            if (reset_flags[4]==false)
                                changing.putExtra("FifthContact", getIntent().getExtras().getString("FifthContactName", "nothing"));
                            else
                                changing.putExtra("FifthContact","nothing");

                            if(reset_flags[5]==false)
                                changing.putExtra("SixthContact", getIntent().getExtras().getString("SixthContactName", "nothing"));
                            else
                                changing.putExtra("SixthContact","nothing");

                            if(reset_flags[6]==false)
                                changing.putExtra("SeventhContact", getIntent().getExtras().getString("SeventhContactName", "nothing"));
                            else
                                changing.putExtra("SeventhContact","nothing");

                            if(reset_flags[7]==false)
                                changing.putExtra("EighthContact", getIntent().getExtras().getString("EighthContactName", "nothing"));
                            else
                                changing.putExtra("EighthContact","nothing");
                            // cycling the phone number array
                            // we can just insert the variable because if it was chagned the array should be changed to
                            changing.putExtra("Directions_submited",displayed_directions);


                        }
                        changing.putExtra("PhoneNumbers", numbers);
                        changing.putExtra("Task", task_window.getText().toString());
                        // starting the activity
                        startActivity(changing);

                    }
                }
        );
        // delete the contact with the long press
        firstcontact.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // deleting the number in the array
                numbers[0]="nothing";
                // updating the button name
                firstcontact.setText("nothing");
                // using an array to hold flags
                reset_flags[0]=true;


                return true;
            }
        });
        secondcontact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent changing = new Intent(view.getContext(), ContactSelect.class);
                        // storing extra values in this bundle
                        changing.putExtra("ha", 2);
                        // have to send all of the current extras
                        // keep bouncing between activity changes
                        if (getIntent().getExtras() != null) {
                            // setting the flags for the contacts
                            // intercepting so we can add the nothing if the user deletes because the flag array should reset every activity starr up
                            if (reset_flags[0]==false)
                                changing.putExtra("FirstContact", getIntent().getExtras().getString("FirstContactName", "nothing"));
                            else
                                changing.putExtra("FirstContact","nothing");

                            if(reset_flags[1]==false)
                                changing.putExtra("SecondContact", getIntent().getExtras().getString("SecondContactName", "nothing"));
                            else
                                changing.putExtra("SecondContact","nothing");

                            if (reset_flags[2]==false)
                                changing.putExtra("ThirdContact", getIntent().getExtras().getString("ThirdContactName", "nothing"));
                            else
                                changing.putExtra("ThirdContact","nothing");

                            if (reset_flags[3]==false)
                                changing.putExtra("FourthContact", getIntent().getExtras().getString("FourthContactName", "nothing"));
                            else
                                changing.putExtra("FourthContact","nothing");

                            if (reset_flags[4]==false)
                                changing.putExtra("FifthContact", getIntent().getExtras().getString("FifthContactName", "nothing"));
                            else
                                changing.putExtra("FifthContact","nothing");

                            if(reset_flags[5]==false)
                                changing.putExtra("SixthContact", getIntent().getExtras().getString("SixthContactName", "nothing"));
                            else
                                changing.putExtra("SixthContact","nothing");

                            if(reset_flags[6]==false)
                                changing.putExtra("SeventhContact", getIntent().getExtras().getString("SeventhContactName", "nothing"));
                            else
                                changing.putExtra("SeventhContact","nothing");

                            if(reset_flags[7]==false)
                                changing.putExtra("EighthContact", getIntent().getExtras().getString("EighthContactName", "nothing"));
                            else
                                changing.putExtra("EighthContact","nothing");
                            // cycling the phone number array
                            // we can just insert the variable because if it was chagned the array should be changed to
                            // saving the text for the user

                        }
                        // sending over the phonenumber list
                        // should all ready be modified if that if statement up top
                        changing.putExtra("Task", task_window.getText().toString());
                        changing.putExtra("PhoneNumbers", numbers);
                        // just to change and get the contact
                        startActivity(changing);

                    }
                }

        );
        secondcontact.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // deleting the number in the array
                numbers[1]="nothing";
                // updating the button name
                secondcontact.setText("nothing");
                // updating the data object
                reset_flags[1]=true;

                return true;
            }
        });
        // third contact button
       thirdcontact.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent changing = new Intent(view.getContext(), ContactSelect.class);
                       // storing extra values in this bundle
                       changing.putExtra("ha", 3);
                       // have to send all of the current extras
                       // keep bouncing between activity changes
                       if (getIntent().getExtras() != null) {
                           // setting the flags for the contacts
                           // intercepting so we can add the nothing if the user deletes because the flag array should reset every activity starr up
                           if (reset_flags[0]==false)
                               changing.putExtra("FirstContact", getIntent().getExtras().getString("FirstContactName", "nothing"));
                           else
                               changing.putExtra("FirstContact","nothing");

                           if(reset_flags[1]==false)
                               changing.putExtra("SecondContact", getIntent().getExtras().getString("SecondContactName", "nothing"));
                           else
                               changing.putExtra("SecondContact","nothing");

                           if (reset_flags[2]==false)
                               changing.putExtra("ThirdContact", getIntent().getExtras().getString("ThirdContactName", "nothing"));
                           else
                               changing.putExtra("ThirdContact","nothing");

                           if (reset_flags[3]==false)
                               changing.putExtra("FourthContact", getIntent().getExtras().getString("FourthContactName", "nothing"));
                           else
                               changing.putExtra("FourthContact","nothing");

                           if (reset_flags[4]==false)
                               changing.putExtra("FifthContact", getIntent().getExtras().getString("FifthContactName", "nothing"));
                           else
                               changing.putExtra("FifthContact","nothing");

                           if(reset_flags[5]==false)
                               changing.putExtra("SixthContact", getIntent().getExtras().getString("SixthContactName", "nothing"));
                           else
                               changing.putExtra("SixthContact","nothing");

                           if(reset_flags[6]==false)
                               changing.putExtra("SeventhContact", getIntent().getExtras().getString("SeventhContactName", "nothing"));
                           else
                               changing.putExtra("SeventhContact","nothing");

                           if(reset_flags[7]==false)
                               changing.putExtra("EighthContact", getIntent().getExtras().getString("EighthContactName", "nothing"));
                           else
                               changing.putExtra("EighthContact","nothing");
                           // cycling the phone number array
                           // we can just insert the variable because if it was chagned the array should be changed to
                           // saving the text for the user

                       }
                       // sending over the phonenumber list
                       // should all ready be modified if that if statement up top
                       changing.putExtra("Task", task_window.getText().toString());
                       changing.putExtra("PhoneNumbers", numbers);
                       // just to change and get the contact
                       startActivity(changing);

                   }
               }
       );
       thirdcontact.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               // TODO Auto-generated method stub
               // deleting the number in the array
               numbers[2]="nothing";
               // updating the button name
               thirdcontact.setText("nothing");
               // updating the data object
               reset_flags[2]=true;

               return true;
           }
       });
       fourthcontact.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent changing = new Intent(view.getContext(), ContactSelect.class);
                       // storing extra values in this bundle
                       changing.putExtra("ha", 4);
                       // have to send all of the current extras
                       // keep bouncing between activity changes
                       if (getIntent().getExtras() != null) {
                           // setting the flags for the contacts
                           // intercepting so we can add the nothing if the user deletes because the flag array should reset every activity starr up
                           if (reset_flags[0]==false)
                               changing.putExtra("FirstContact", getIntent().getExtras().getString("FirstContactName", "nothing"));
                           else
                               changing.putExtra("FirstContact","nothing");

                           if(reset_flags[1]==false)
                               changing.putExtra("SecondContact", getIntent().getExtras().getString("SecondContactName", "nothing"));
                           else
                               changing.putExtra("SecondContact","nothing");

                           if (reset_flags[2]==false)
                               changing.putExtra("ThirdContact", getIntent().getExtras().getString("ThirdContactName", "nothing"));
                           else
                               changing.putExtra("ThirdContact","nothing");

                           if (reset_flags[3]==false)
                               changing.putExtra("FourthContact", getIntent().getExtras().getString("FourthContactName", "nothing"));
                           else
                               changing.putExtra("FourthContact","nothing");

                           if (reset_flags[4]==false)
                               changing.putExtra("FifthContact", getIntent().getExtras().getString("FifthContactName", "nothing"));
                           else
                               changing.putExtra("FifthContact","nothing");

                           if(reset_flags[5]==false)
                               changing.putExtra("SixthContact", getIntent().getExtras().getString("SixthContactName", "nothing"));
                           else
                               changing.putExtra("SixthContact","nothing");

                           if(reset_flags[6]==false)
                               changing.putExtra("SeventhContact", getIntent().getExtras().getString("SeventhContactName", "nothing"));
                           else
                               changing.putExtra("SeventhContact","nothing");

                           if(reset_flags[7]==false)
                               changing.putExtra("EighthContact", getIntent().getExtras().getString("EighthContactName", "nothing"));
                           else
                               changing.putExtra("EighthContact","nothing");
                           // cycling the phone number array
                           // we can just insert the variable because if it was chagned the array should be changed to
                           // saving the text for the user

                       }
                       // sending over the phonenumber list
                       // should all ready be modified if that if statement up top
                       changing.putExtra("Task", task_window.getText().toString());
                       changing.putExtra("PhoneNumbers", numbers);
                       // just to change and get the contact
                       startActivity(changing);

                   }
               }
       );
       fourthcontact.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               // TODO Auto-generated method stub
               // deleting the number in the array
               numbers[3]="nothing";
               // updating the button name
               fourthcontact.setText("nothing");
               // updating the data object
               reset_flags[3]=true;

               return true;
           }
       });
        mainbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // for now leave the random number selection alone
                        // int selected_number = choose_random_number(numbers);
                        // converting back to a string for the sms function
                        // String converted_phonenumber = String.valueOf(selected_number);
                        // sending into the sms function with the call to the text view
                       // JUST TESTING THE FUNCTION
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            int hasSMSPermission = checkSelfPermission(Manifest.permission.SEND_SMS);
                            if (hasSMSPermission != PackageManager.PERMISSION_GRANTED) {
                                if (!shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                                    showMessageOKCancel("You need to allow access to Send SMS",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                        requestPermissions(new String[] {Manifest.permission.SEND_SMS},
                                                                REQUEST_SMS);
                                                    }
                                                }
                                            });
                                    return;
                                }
                                requestPermissions(new String[] {Manifest.permission.SEND_SMS},
                                        REQUEST_SMS);
                                return;
                            }
                            // creating a flag to see if any contacts have been entered
                            boolean any_contacts_entered = false;
                            for (int x = 0; x <8;x++)
                            {
                                // spinning through the contacts to see if any entered
                                // no nothing entered to be true
                                if (!(numbers[x].equals("nothing")))
                                {
                                    any_contacts_entered = true;
                                }
                            }
                            if (any_contacts_entered) {
                                // passing in the numbers to send the integer out
                                // test if the phonumbers are being selected
                                String phonenumber = choose_random_number(numbers);
                                // function to sent the actually text message
                                // creating the rotation object directions in the xml file
                                Animation startRotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.android_rotate_animation);
                                mainbutton.startAnimation(startRotation);

                                sendSMS(phonenumber, "You have be selected to perform :\n" +
                                        "" + message_collect.getText());
                            }
                            // if not it wont spin and a toast should pop up
                            // using not because non have to be entered
                            if (!any_contacts_entered)
                            Toast.makeText(getApplicationContext(),"You must add phone numbers from contacts first ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        fifthcontact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent changing = new Intent(view.getContext(), ContactSelect.class);
                        // storing extra values in this bundle
                        changing.putExtra("ha", 5);
                        // have to send all of the current extras
                        // keep bouncing between activity changes
                        if (getIntent().getExtras() != null) {
                            // setting the flags for the contacts
                            // intercepting so we can add the nothing if the user deletes because the flag array should reset every activity starr up
                            if (reset_flags[0]==false)
                                changing.putExtra("FirstContact", getIntent().getExtras().getString("FirstContactName", "nothing"));
                            else
                                changing.putExtra("FirstContact","nothing");

                            if(reset_flags[1]==false)
                                changing.putExtra("SecondContact", getIntent().getExtras().getString("SecondContactName", "nothing"));
                            else
                                changing.putExtra("SecondContact","nothing");

                            if (reset_flags[2]==false)
                                changing.putExtra("ThirdContact", getIntent().getExtras().getString("ThirdContactName", "nothing"));
                            else
                                changing.putExtra("ThirdContact","nothing");

                            if (reset_flags[3]==false)
                                changing.putExtra("FourthContact", getIntent().getExtras().getString("FourthContactName", "nothing"));
                            else
                                changing.putExtra("FourthContact","nothing");

                            if (reset_flags[4]==false)
                                changing.putExtra("FifthContact", getIntent().getExtras().getString("FifthContactName", "nothing"));
                            else
                                changing.putExtra("FifthContact","nothing");

                            if(reset_flags[5]==false)
                                changing.putExtra("SixthContact", getIntent().getExtras().getString("SixthContactName", "nothing"));
                            else
                                changing.putExtra("SixthContact","nothing");

                            if(reset_flags[6]==false)
                                changing.putExtra("SeventhContact", getIntent().getExtras().getString("SeventhContactName", "nothing"));
                            else
                                changing.putExtra("SeventhContact","nothing");

                            if(reset_flags[7]==false)
                                changing.putExtra("EighthContact", getIntent().getExtras().getString("EighthContactName", "nothing"));
                            else
                                changing.putExtra("EighthContact","nothing");
                            // cycling the phone number array
                            // we can just insert the variable because if it was chagned the array should be changed to
                            // saving the text for the user

                        }
                        // sending over the phonenumber list
                        // should all ready be modified if that if statement up top
                        changing.putExtra("Task", task_window.getText().toString());
                        changing.putExtra("PhoneNumbers", numbers);
                        // just to change and get the contact
                        startActivity(changing);

                    }
                });
        fifthcontact.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // deleting the number in the array
                numbers[4]="nothing";
                // updating the button name
                fifthcontact.setText("nothing");
                // updating the data object
                reset_flags[4]=true;

                return true;
            }
        });
        sixthcontact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent changing = new Intent(view.getContext(), ContactSelect.class);
                        // storing extra values in this bundle
                        changing.putExtra("ha", 6);
                        // have to send all of the current extras
                        // keep bouncing between activity changes
                        if (getIntent().getExtras() != null) {
                            // setting the flags for the contacts
                            // intercepting so we can add the nothing if the user deletes because the flag array should reset every activity starr up
                            if (reset_flags[0]==false)
                                changing.putExtra("FirstContact", getIntent().getExtras().getString("FirstContactName", "nothing"));
                            else
                                changing.putExtra("FirstContact","nothing");

                            if(reset_flags[1]==false)
                                changing.putExtra("SecondContact", getIntent().getExtras().getString("SecondContactName", "nothing"));
                            else
                                changing.putExtra("SecondContact","nothing");

                            if (reset_flags[2]==false)
                                changing.putExtra("ThirdContact", getIntent().getExtras().getString("ThirdContactName", "nothing"));
                            else
                                changing.putExtra("ThirdContact","nothing");

                            if (reset_flags[3]==false)
                                changing.putExtra("FourthContact", getIntent().getExtras().getString("FourthContactName", "nothing"));
                            else
                                changing.putExtra("FourthContact","nothing");

                            if (reset_flags[4]==false)
                                changing.putExtra("FifthContact", getIntent().getExtras().getString("FifthContactName", "nothing"));
                            else
                                changing.putExtra("FifthContact","nothing");

                            if(reset_flags[5]==false)
                                changing.putExtra("SixthContact", getIntent().getExtras().getString("SixthContactName", "nothing"));
                            else
                                changing.putExtra("SixthContact","nothing");

                            if(reset_flags[6]==false)
                                changing.putExtra("SeventhContact", getIntent().getExtras().getString("SeventhContactName", "nothing"));
                            else
                                changing.putExtra("SeventhContact","nothing");

                            if(reset_flags[7]==false)
                                changing.putExtra("EighthContact", getIntent().getExtras().getString("EighthContactName", "nothing"));
                            else
                                changing.putExtra("EighthContact","nothing");
                            // cycling the phone number array
                            // we can just insert the variable because if it was chagned the array should be changed to
                            // saving the text for the user
                        }
                        // sending over the phonenumber list
                        // should all ready be modified if that if statement up top
                        changing.putExtra("Task", task_window.getText().toString());
                        changing.putExtra("PhoneNumbers", numbers);
                        // just to change and get the contact
                        startActivity(changing);

                    }
        });
        sixthcontact.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // deleting the number in the array
                numbers[5]="nothing";
                // updating the button name
                sixthcontact.setText("nothing");
                // updating the data object
                reset_flags[5]=true;

                return true;
            }
        });
        seventhcontact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent changing = new Intent(view.getContext(), ContactSelect.class);
                        // storing extra values in this bundle
                        changing.putExtra("ha", 7);
                        // have to send all of the current extras
                        // keep bouncing between activity changes
                        if (getIntent().getExtras() != null) {
                            // setting the flags for the contacts
                            // intercepting so we can add the nothing if the user deletes because the flag array should reset every activity starr up
                            if (reset_flags[0]==false)
                                changing.putExtra("FirstContact", getIntent().getExtras().getString("FirstContactName", "nothing"));
                            else
                                changing.putExtra("FirstContact","nothing");

                            if(reset_flags[1]==false)
                                changing.putExtra("SecondContact", getIntent().getExtras().getString("SecondContactName", "nothing"));
                            else
                                changing.putExtra("SecondContact","nothing");

                            if (reset_flags[2]==false)
                                changing.putExtra("ThirdContact", getIntent().getExtras().getString("ThirdContactName", "nothing"));
                            else
                                changing.putExtra("ThirdContact","nothing");

                            if (reset_flags[3]==false)
                                changing.putExtra("FourthContact", getIntent().getExtras().getString("FourthContactName", "nothing"));
                            else
                                changing.putExtra("FourthContact","nothing");

                            if (reset_flags[4]==false)
                                changing.putExtra("FifthContact", getIntent().getExtras().getString("FifthContactName", "nothing"));
                            else
                                changing.putExtra("FifthContact","nothing");

                            if(reset_flags[5]==false)
                                changing.putExtra("SixthContact", getIntent().getExtras().getString("SixthContactName", "nothing"));
                            else
                                changing.putExtra("SixthContact","nothing");

                            if(reset_flags[6]==false)
                                changing.putExtra("SeventhContact", getIntent().getExtras().getString("SeventhContactName", "nothing"));
                            else
                                changing.putExtra("SeventhContact","nothing");

                            if(reset_flags[7]==false)
                                changing.putExtra("EighthContact", getIntent().getExtras().getString("EighthContactName", "nothing"));
                            else
                                changing.putExtra("EighthContact","nothing");
                            // cycling the phone number array
                            // we can just insert the variable because if it was chagned the array should be changed to
                            // saving the text for the user

                        }
                        // sending over the phonenumber list
                        // should all ready be modified if that if statement up top
                        changing.putExtra("Task", task_window.getText().toString());
                        changing.putExtra("PhoneNumbers", numbers);
                        // just to change and get the contact
                        startActivity(changing);

                    }
                }
        );
        seventhcontact.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // deleting the number in the array
                numbers[6]="nothing";
                // updating the button name
                seventhcontact.setText("nothing");
                // updating the data object
                reset_flags[6]=true;

                return true;
            }
        });
        eighthcontact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent changing = new Intent(view.getContext(), ContactSelect.class);
                        // storing extra values in this bundle
                        changing.putExtra("ha", 8);
                        // have to send all of the current extras
                        // keep bouncing between activity changes
                        if (getIntent().getExtras() != null) {
                            // setting the flags for the contacts
                            // intercepting so we can add the nothing if the user deletes because the flag array should reset every activity starr up
                            if (reset_flags[0]==false)
                                changing.putExtra("FirstContact", getIntent().getExtras().getString("FirstContactName", "nothing"));
                            else
                                changing.putExtra("FirstContact","nothing");

                            if(reset_flags[1]==false)
                                changing.putExtra("SecondContact", getIntent().getExtras().getString("SecondContactName", "nothing"));
                            else
                                changing.putExtra("SecondContact","nothing");

                            if (reset_flags[2]==false)
                                changing.putExtra("ThirdContact", getIntent().getExtras().getString("ThirdContactName", "nothing"));
                            else
                                changing.putExtra("ThirdContact","nothing");

                            if (reset_flags[3]==false)
                                changing.putExtra("FourthContact", getIntent().getExtras().getString("FourthContactName", "nothing"));
                            else
                                changing.putExtra("FourthContact","nothing");

                            if (reset_flags[4]==false)
                                changing.putExtra("FifthContact", getIntent().getExtras().getString("FifthContactName", "nothing"));
                            else
                                changing.putExtra("FifthContact","nothing");

                            if(reset_flags[5]==false)
                                changing.putExtra("SixthContact", getIntent().getExtras().getString("SixthContactName", "nothing"));
                            else
                                changing.putExtra("SixthContact","nothing");

                            if(reset_flags[6]==false)
                                changing.putExtra("SeventhContact", getIntent().getExtras().getString("SeventhContactName", "nothing"));
                            else
                                changing.putExtra("SeventhContact","nothing");

                            if(reset_flags[7]==false)
                                changing.putExtra("EighthContact", getIntent().getExtras().getString("EighthContactName", "nothing"));
                            else
                                changing.putExtra("EighthContact","nothing");
                            // cycling the phone number array
                            // we can just insert the variable because if it was chagned the array should be changed to
                            // saving the text for the user


                        }
                        // sending over the phonenumber list
                        // should all ready be modified if that if statement up top
                        // have to put it out here because it always transfers
                        changing.putExtra("Task", task_window.getText().toString());
                        changing.putExtra("PhoneNumbers", numbers);
                        // just to change and get the contact
                        startActivity(changing);

                    }
                }
        );
        eighthcontact.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // deleting the number in the array
                numbers[7]="nothing";
                // updating the button name
                eighthcontact.setText("nothing");
                // updating the data object

                reset_flags[7]=true;
                return true;
            }
        });





        // testing to see if the intent has data

        if (getIntent().getExtras() != null) {
            // receiving the user data
            int Button_Identification = getIntent().getExtras().getInt("ButtonIdentification", 0);
            // IF THE PHONE NUMBER HASNOT CHANGED THEN DONT
            // changing the names of the buttons to reflect the contact changes
            firstcontact.setText(getIntent().getExtras().getString("FirstContactName", "nothing"));
            secondcontact.setText(getIntent().getExtras().getString("SecondContactName", "nothing"));
            thirdcontact.setText(getIntent().getExtras().getString("ThirdContactName", "nothing"));
            fourthcontact.setText(getIntent().getExtras().getString("FourthContactName", "nothing"));
            fifthcontact.setText(getIntent().getExtras().getString("FifthContactName", "nothing"));
            sixthcontact.setText(getIntent().getExtras().getString("SixthContactName", "nothing"));
            seventhcontact.setText(getIntent().getExtras().getString("SeventhContactName", "nothing"));
            eighthcontact.setText(getIntent().getExtras().getString("EighthContactName", "nothing"));
            // collecting the user message
            task_window.setText(getIntent().getExtras().getString("Task", ""));





        }


    }
    // now lets save the data for the buttons


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean checkPermission() {
        return ( ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS ) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{SEND_SMS}, REQUEST_SMS);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SMS:
                if (grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access sms", Toast.LENGTH_SHORT).show();
                    sendSMS("8323495045","shit");

                }else {
                    Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and sms", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(SEND_SMS)) {
                            showMessageOKCancel("You need to allow access to both the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{SEND_SMS},
                                                        REQUEST_SMS);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    // saving state just for the boolean variable


}
