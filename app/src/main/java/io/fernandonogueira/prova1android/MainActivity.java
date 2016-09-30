package io.fernandonogueira.prova1android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText brlEdit;
    private EditText usdEdit;
    private Button changeDirectionBtn;
    private Button calculateBtn;

    private ConvertDirection convertDirection;

    private static final double USD_TO_BRL_FACTOR = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertDirection = ConvertDirection.USD_TO_BRL;

        brlEdit = (EditText) findViewById(R.id.BRLEdit);
        usdEdit = (EditText) findViewById(R.id.USD_EDIT);

        changeDirectionBtn = (Button) findViewById(R.id.change_direction_btn);
        changeDirectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDirection();
            }
        });

        calculateBtn = (Button) findViewById(R.id.calculate_btn);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        brlEdit.setEnabled(false);
    }

    private void changeDirection() {
        if (ConvertDirection.USD_TO_BRL.equals(convertDirection)) {
            convertDirection = ConvertDirection.BRL_TO_USD;
            changeDirectionBtn.setText("CHANGE TO USD TO BRL");

            brlEdit.setEnabled(true);
            usdEdit.setEnabled(false);

        } else {
            convertDirection = ConvertDirection.USD_TO_BRL;
            changeDirectionBtn.setText("CHANGE TO BRL TO USD");

            brlEdit.setEnabled(false);
            usdEdit.setEnabled(true);

        }
        calculate();
    }

    public boolean calculate() {

        if (ConvertDirection.USD_TO_BRL.equals(convertDirection)) {
            String fromValueText = usdEdit.getText().toString();

            if (!fromValueText.isEmpty()) {
                Double fromValue = Double.valueOf(fromValueText);
                double toValue = fromValue * USD_TO_BRL_FACTOR;
                brlEdit.setText(String.valueOf(toValue));
            } else {
                brlEdit.setText("");
            }

        } else {
            String fromValueText = brlEdit.getText().toString();

            if (!fromValueText.isEmpty()) {
                Double fromValue = Double.valueOf(fromValueText);
                double toValue = fromValue / USD_TO_BRL_FACTOR;
                usdEdit.setText(String.valueOf(toValue));
            } else {
                usdEdit.setText("");
            }
        }

        return true;
    }
}
