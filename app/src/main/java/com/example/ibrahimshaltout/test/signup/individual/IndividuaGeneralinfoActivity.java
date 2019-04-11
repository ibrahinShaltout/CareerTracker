package com.example.ibrahimshaltout.test.signup.individual;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ibrahimshaltout.test.MainActivity;
import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.signup.YourLocation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class IndividuaGeneralinfoActivity extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();
    private Button continuegeneral ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individua_generalinfo);

        continuegeneral = (Button)findViewById(R.id.countinue_generalIndividualInfo) ;

        EditText as12 = (EditText) findViewById(R.id.City);
        final EditText dateEditText = findViewById(R.id.date);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd/MM/yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        dateEditText.setText(sdf.format(myCalendar.getTime()));
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(IndividuaGeneralinfoActivity.this, dateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        continuegeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (IndividuaGeneralinfoActivity.this, IndividualCareerinfoActivity.class );
                startActivity(intent);
                finish();
            }
        });


    }

}