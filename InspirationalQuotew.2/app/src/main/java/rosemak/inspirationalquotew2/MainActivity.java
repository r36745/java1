package rosemak.inspirationalquotew2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
//Steven Roseman II
//Java I
//Week Two
//10/8/14


public class MainActivity extends ActionBarActivity {

    public final static String TAG = "Inspirational Quotes";
    public int motivationLength;
    public int motivationTotal;
    public int counter = 0;
    public String motivationString, quoteCollection;
    public InputMethodManager imm;
    public ArrayList<String> motivationList;
    public TextView avgLengthValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText motivationText = (EditText) findViewById(R.id.motivationEditText);
        final EditText motivationIndexEditText = (EditText) findViewById(R.id.motivationIndexEditText);
        final TextView motivationNumberText = (TextView) findViewById(R.id.quoteNumTextView);
        final Button motivationButton = (Button) findViewById(R.id.motivationButton);
        final Button grabMotivationButton = (Button) findViewById(R.id.grabMotivation);
        final Button collectionButton = (Button) findViewById(R.id.collectionButton);
        final TextView motivationWorldText = (TextView) findViewById(R.id.motivationWordTextView);
        final TextView indexMotivation = (TextView) findViewById(R.id.indexMotivation);



        avgLengthValue = (TextView) findViewById(R.id.avgLengthValue);
        motivationList = new ArrayList<String>();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motivationString = String.valueOf(motivationText.getText());
                motivationTotal = motivationString.length();


                if (motivationList.contains(motivationString)) {
                    Toast.makeText(MainActivity.this, "Duplicate", Toast.LENGTH_LONG).show();
                } else if (motivationTotal != 0) {
                    motivationList.add(motivationString);
                    motivationLength = motivationList.size();
                    Log.i(TAG, "list= " + motivationList);

                    String quoteLength = motivationLength + "";

                    motivationNumberText.setText(quoteLength);
                    motivationWorldText.setText(motivationString);
                    avgLength();


                } else if (motivationTotal == 0) {

                    String noResponse = "Enter your Motivational Thought";
                    Toast.makeText(MainActivity.this, noResponse, Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(MainActivity.this, "You Have an Error", Toast.LENGTH_LONG).show();
                }


                motivationText.setText("");
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);




            }
        };

        motivationButton.setOnClickListener(listener);



        View.OnClickListener grabListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String motivationText = String.valueOf(motivationIndexEditText.getText());
                int motivationTextLength = motivationText.length();
                final int intMotivationText = Integer.parseInt(motivationText);
                String grabMotivationText = motivationList.get(intMotivationText);
                final String finalMotivationText = grabMotivationText + "";



                if (motivationTextLength != 0) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Alert Window");
                    alertDialog.setIcon(R.drawable.ic_launcher);
                    alertDialog.setMessage("Inspiration Selected");
                    final TextView inspirationName = new TextView(MainActivity.this);
                    inspirationName.setText(finalMotivationText);
                    alertDialog.setView(inspirationName);
                    alertDialog.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                             motivationList.remove(intMotivationText);
                            Log.i(TAG, "Remove" + motivationList);

                        }
                    });

                    alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    });
                    alertDialog.show();

                }



                motivationIndexEditText.setText("");

                indexMotivation.setText(finalMotivationText);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);


            }
        };
        grabMotivationButton.setOnClickListener(grabListener);


        View.OnClickListener collectionListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String finalCollection = String.valueOf(motivationList);


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Alert Window");
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.setMessage("Inspiration Selected");
                final TextView inspirationName = new TextView(MainActivity.this);
                inspirationName.setText(finalCollection);
                alertDialog.setView(inspirationName);
                alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                alertDialog.show();

            }
        };

        collectionButton.setOnClickListener(collectionListener);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }







    private void avgLength() {

        int entryCollection = counter += motivationTotal;
        int totalCollection = entryCollection / motivationLength;

        quoteCollection = totalCollection + "";
        avgLengthValue.setText(quoteCollection);

    }




}
