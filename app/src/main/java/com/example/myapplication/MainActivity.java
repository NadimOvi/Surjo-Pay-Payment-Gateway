package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sm.shurjopaysdk.listener.PaymentResultListener;
import com.sm.shurjopaysdk.model.RequiredDataModel;
import com.sm.shurjopaysdk.model.TransactionInfo;
import com.sm.shurjopaysdk.payment.ShurjoPaySDK;
import com.sm.shurjopaysdk.utils.SPayConstants;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button payNowButton;
    int price=0;
    EditText priceEditText;
    String tempPrice = "10";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        payNowButton = findViewById(R.id.paynow);

        priceEditText = findViewById(R.id.amount);




        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                price = (int) Double.parseDouble(priceEditText.getText().toString());
                paymentRequest(price);


            }
        });
    }

    public void paymentRequest(int price){


        String UniqId = "NOK"+getRandomUniqIdString();
        RequiredDataModel requiredDataModel = new RequiredDataModel("spaytest",  "JehPNXF58rXs", UniqId, price,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJzcGF5dGVzdCIsImlhdCI6MTU5ODM2MTI1Nn0.cwkvdTDI6_K430xq7Iqapaknbqjm9J3Th1EiXePIEcY");


        ShurjoPaySDK.getInstance().makePayment(MainActivity.this, SPayConstants.SdkType.TEST, requiredDataModel, new PaymentResultListener() {

            @Override
            public void onSuccess(TransactionInfo transactionInfo) {
                Log.d(TAG, "onSuccess: transactionInfo = " + transactionInfo);
                Toast.makeText(MainActivity.this, "onSuccess: transactionInfo = " +
                        transactionInfo, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(String message) {
                Log.d(TAG, "onFailed: message = " + message);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static String getRandomUniqIdString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);


        return String.format("%06d", number);
    }

}