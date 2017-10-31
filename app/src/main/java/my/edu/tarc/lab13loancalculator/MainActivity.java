package my.edu.tarc.lab13loancalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editTextPrice, editTextDown, editTextInterest, editTextRepayment, editTextSalary;
    public static final String LOAN_STATUS = "status";
    public static final String LOAN_REPAYMENT = "repayment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextDown = (EditText) findViewById(R.id.editTextDown);
        editTextInterest = (EditText) findViewById(R.id.editTextInterest);
        editTextRepayment = (EditText) findViewById(R.id.editTextRepayment);
        editTextSalary = (EditText) findViewById(R.id.editTextSalary);
    }

    public void calculateRepayment(View view) {
        //Create explicit intent object
        Intent intent = new Intent (this, ResultActivity.class);

        double repayment=0,totalInterest,totalLoan,vehiclePrice,interestRate,downpayment,salary,monthpayment,percent;
        vehiclePrice=Double.parseDouble(editTextPrice.getText().toString());
        downpayment=Double.parseDouble(editTextDown.getText().toString());

        interestRate=Double.parseDouble(editTextInterest.getText().toString());
        interestRate=(interestRate/100);
        monthpayment=Integer.parseInt(editTextRepayment.getText().toString());
        salary=Double.parseDouble(editTextSalary.getText().toString());
        totalInterest=(vehiclePrice-downpayment)*interestRate*(monthpayment/12);
        totalLoan=(vehiclePrice-downpayment)+interestRate;
        repayment=totalLoan/monthpayment;

        percent = monthpayment/salary;


        if(percent>0.3){
            intent.putExtra(LOAN_STATUS,"Rejected");
            intent.putExtra(LOAN_REPAYMENT,0);

        }
        else if (percent <=0.3) {
            intent.putExtra(LOAN_STATUS,"Accepted");
            intent.putExtra(LOAN_REPAYMENT,repayment);
        }
        startActivity(intent);
        }


        // Passing data with intent using putExtra method
        //putExtra has two parameters (Tag,Value)



    }


