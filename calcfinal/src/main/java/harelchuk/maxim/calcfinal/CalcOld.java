package harelchuk.maxim.calcfinal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/*
public class CalcOld extends AppCompatActivity {

    private TextView inputTextView;
    String inputString = "";
    int count_open_br = 0, count_close_br = 0;
    double result = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        inputTextView = findViewById(R.id.calcTextView);


        //     Button declaration

        Button bone = findViewById(R.id.button1);
        Button btwo = findViewById(R.id.button2);
        Button bthree = findViewById(R.id.button3);
        Button bfour = findViewById(R.id.button4);
        Button bfive = findViewById(R.id.button5);
        Button bsix = findViewById(R.id.button6);
        Button bseven = findViewById(R.id.button7);
        Button beight = findViewById(R.id.button8);
        Button bnine = findViewById(R.id.button9);
        Button bzero = findViewById(R.id.button0);
        Button bac = findViewById(R.id.buttonAC);
        Button bpoint = findViewById(R.id.buttonP);
        Button badd = findViewById(R.id.buttonADD);
        Button bminus = findViewById(R.id.buttonMINUS);
        Button bmult = findViewById(R.id.buttonMULT);
        Button bdev = findViewById(R.id.buttonDEV);
        Button bcalc = findViewById(R.id.buttonCALC);
        Button bundo = findViewById(R.id.buttonUNDO);
        Button bsin = findViewById(R.id.buttonSIN);
        Button bcos = findViewById(R.id.buttonCOS);
        Button btan = findViewById(R.id.buttonTAN);
        Button blog = findViewById(R.id.buttonLOG);
        Button bsqrt = findViewById(R.id.buttonSQRT);
        Button bsqr = findViewById(R.id.buttonSQR);
        Button bpow = findViewById(R.id.buttonPOW);
        Button bpi = findViewById(R.id.buttonPI);
        Button be = findViewById(R.id.buttonE);
        Button bopenbr = findViewById(R.id.buttonOPENBR);
        Button bclosebr = findViewById(R.id.buttonCLOSEBR);
        Button bmod = findViewById(R.id.buttonMOD);


        final View.OnClickListener short_click_on_button= new View.OnClickListener() {
            @Override
            final public void onClick(View scienceButtonsView) {

                switch (scienceButtonsView.getId()){

                    case R.id.button1:
                        inputString +=getString(R.string._1);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.button2:
                        inputString +=getString(R.string._2);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.button3:
                        inputString +=getString(R.string._3);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.button4:
                        inputString +=getString(R.string._4);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.button5:
                        inputString +=getString(R.string._5);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.button6:
                        inputString +=getString(R.string._6);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.button7:
                        inputString +=getString(R.string._7);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.button8:
                        inputString +=getString(R.string._8);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.button9:
                        inputString +=getString(R.string._9);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.button0:
                        inputString +=getString(R.string._0);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonAC:
                        inputString ="";
                        inputTextView.setText(inputString);
                        count_open_br = 0;
                        count_close_br = 0;
                        break;

                    case R.id.buttonP:
                        //check if empty
                        if(inputString.length()==0){
                            onClick(findViewById(R.id.button0));
                        }
                        //check if first at number
                        if(     (inputString.charAt(inputString.length()-1)!= ('1')) &&
                                (inputString.charAt(inputString.length()-1)!= ('2')) &&
                                (inputString.charAt(inputString.length()-1)!= ('3')) &&
                                (inputString.charAt(inputString.length()-1)!= ('4')) &&
                                (inputString.charAt(inputString.length()-1)!= ('5')) &&
                                (inputString.charAt(inputString.length()-1)!= ('6')) &&
                                (inputString.charAt(inputString.length()-1)!= ('7')) &&
                                (inputString.charAt(inputString.length()-1)!= ('8')) &&
                                (inputString.charAt(inputString.length()-1)!= ('9')) &&
                                (inputString.charAt(inputString.length()-1)!= ('0')) ) {
                            onClick(findViewById(R.id.button0));
                        }
                        inputString +=getString(R.string.p);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonADD:
                        inputString +=getString(R.string.add);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonMINUS:
                        inputString +=getString(R.string.mns);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonMULT:
                        inputString +=getString(R.string.mult);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonDEV:
                        inputString +=getString(R.string.dev);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonCALC:
                        if(inputString.length()!=0) {
                            try {
                                while (count_open_br > count_close_br){
                                    onClick(findViewById(R.id.buttonCLOSEBR));
                                }
                                Expression e = new ExpressionBuilder(inputString).build();
                                result = e.evaluate();
                                if (result >= Double.MAX_VALUE) {
                                    Toast.makeText(getApplicationContext(), "Too large number", Toast.LENGTH_LONG).show();
                                    onClick(findViewById(R.id.buttonAC));
                                    break;
                                }
                                //inputString = new DecimalFormat("#0.00000").format(result);

                                if((result%1)==0){
                                    int temp = (int) result;
                                    inputString = String.valueOf(temp);
                                }
                                else{
                                    result = Math.round(result * 100000000.0) / 100000000.0;
                                    inputString = String.valueOf(result);
                                }

                                inputTextView.setText(inputString);
                            }
                            catch (Exception e){
                                Toast.makeText(getApplicationContext(), "Something get wrong, check input", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Nothing to CALCULATE", Toast.LENGTH_LONG).show();
                        }
                        break;

                    case R.id.buttonUNDO:
                        if(inputString.length()!=0){

                            if(inputString.charAt(inputString.length()-1)=='('){
                                count_open_br--;
                            }

                            if(inputString.charAt(inputString.length()-1)==')'){
                                count_close_br--;
                            }

                            inputString = inputString.substring(0, inputString.length()-1);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Nothing to UNDO", Toast.LENGTH_LONG).show();
                        }

                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonSIN:
                        inputString +=getString(R.string.sin);
                        onClick(findViewById(R.id.buttonOPENBR));
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonCOS:
                        inputString +=getString(R.string.cos);
                        onClick(findViewById(R.id.buttonOPENBR));
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonTAN:
                        inputString +=getString(R.string.tan);
                        onClick(findViewById(R.id.buttonOPENBR));
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonLOG: // logarithm base e
                        inputString +=getString(R.string.log);
                        onClick(findViewById(R.id.buttonOPENBR));
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonSQRT:
                        inputString +=getString(R.string.sqrt);
                        onClick(findViewById(R.id.buttonOPENBR));
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonSQR:
                        inputString +=getString(R.string.sqr);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonPOW:
                        inputString +=getString(R.string.pow);
                        onClick(findViewById(R.id.buttonOPENBR));
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonPI:
                        inputString +=Math.PI;
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonE:
                        inputString +=Math.E;
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonOPENBR:
                        inputString +=getString(R.string.openbracket);
                        inputTextView.setText(inputString);
                        count_open_br++;
                        break;

                    case R.id.buttonCLOSEBR:
                        inputString +=getString(R.string.closebracket);
                        count_close_br++;
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonMOD:
                        inputString +=getString(R.string.mod);
                        onClick(findViewById(R.id.buttonOPENBR));
                        inputTextView.setText(inputString);
                        break;

                }

            }
        };

        View.OnLongClickListener long_click_on_button= new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View scienceButtonsView) {
                switch (scienceButtonsView.getId()) {
                    case R.id.buttonSIN:
                        inputString += getString(R.string.sin);
                        short_click_on_button.onClick(findViewById(R.id.buttonOPENBR));
                        inputString += getString(R.string.todegree);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonCOS:
                        inputString += getString(R.string.cos);
                        short_click_on_button.onClick(findViewById(R.id.buttonOPENBR));
                        inputString += getString(R.string.todegree);
                        inputTextView.setText(inputString);
                        break;

                    case R.id.buttonTAN:
                        inputString += getString(R.string.tan);
                        short_click_on_button.onClick(findViewById(R.id.buttonOPENBR));
                        inputString += getString(R.string.todegree);
                        inputTextView.setText(inputString);
                        break;

                }
                return true;
            }
        };



        //  onclick functions

        bone.setOnClickListener(short_click_on_button);
        btwo.setOnClickListener(short_click_on_button);
        bthree.setOnClickListener(short_click_on_button);
        bfour.setOnClickListener(short_click_on_button);
        bfive.setOnClickListener(short_click_on_button);
        bsix.setOnClickListener(short_click_on_button);
        bseven.setOnClickListener(short_click_on_button);
        beight.setOnClickListener(short_click_on_button);
        bnine.setOnClickListener(short_click_on_button);
        bzero.setOnClickListener(short_click_on_button);
        bac.setOnClickListener(short_click_on_button);
        bpoint.setOnClickListener(short_click_on_button);
        badd.setOnClickListener(short_click_on_button);
        bminus.setOnClickListener(short_click_on_button);
        bmult.setOnClickListener(short_click_on_button);
        bdev.setOnClickListener(short_click_on_button);
        bcalc.setOnClickListener(short_click_on_button);
        bundo.setOnClickListener(short_click_on_button);
        bsin.setOnClickListener(short_click_on_button);
        bcos.setOnClickListener(short_click_on_button);
        btan.setOnClickListener(short_click_on_button);
        blog.setOnClickListener(short_click_on_button);
        bsqrt.setOnClickListener(short_click_on_button);
        bsqr.setOnClickListener(short_click_on_button);
        bpow.setOnClickListener(short_click_on_button);
        bpi.setOnClickListener(short_click_on_button);
        be.setOnClickListener(short_click_on_button);
        bopenbr.setOnClickListener(short_click_on_button);
        bclosebr.setOnClickListener(short_click_on_button);
        bmod.setOnClickListener(short_click_on_button);
        bsin.setOnLongClickListener(long_click_on_button);
        bcos.setOnLongClickListener(long_click_on_button);
        btan.setOnLongClickListener(long_click_on_button);
    }
}
*/