package com.zybooks.pizzaparty;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    public final int SLICES_PER_PIZZA = 8;

    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;
    private RadioGroup mHowHungryRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate was called");

        mNumAttendEditText = findViewById(R.id.num_attend_edit_text);
        mNumPizzasTextView = findViewById(R.id.num_pizzas_text_view);
        mHowHungryRadioGroup = findViewById(R.id.hungry_radio_group);
    }

    public void calculateClick(View view) {
        String numAttendStr = mNumAttendEditText.getText().toString();
        Log.d(TAG, "Number is " + numAttendStr);
        int numAttend;
        try {
            numAttend = Integer.parseInt(numAttendStr);
        } catch (NumberFormatException e){
            numAttend = 0;
        }

        // Determine how many slices on average each person will eat
        int slicesPerPerson;
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.light_radio_button) {
            slicesPerPerson = 2;
        } else if (checkedId == R.id.medium_radio_button) {
            slicesPerPerson = 3;
        } else if (checkedId == R.id.ravenous_radio_button) {
            slicesPerPerson = 4;
        } else {
            mNumPizzasTextView.setText(getString(R.string.no_selection_error)); // Please let us know how hungry they are!
            return;
        }

        int totalPizzas = (int) Math.ceil(numAttend * slicesPerPerson /
                (double) SLICES_PER_PIZZA);
        Log.d(TAG, getString(R.string.total, totalPizzas));
        mNumPizzasTextView.setText(getString(R.string.total, totalPizzas));
    }
}