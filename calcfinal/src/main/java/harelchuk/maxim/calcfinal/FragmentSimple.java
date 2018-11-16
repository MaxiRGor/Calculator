package harelchuk.maxim.calcfinal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class FragmentSimple extends Fragment{

    View simpleButtonsView = null;
    TextView inputTextView = null;
    TextView historyTextView = null;
    String inputString = "";
    String historyString="";
    Boolean pointPressedBool = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        simpleButtonsView = inflater.inflate(R.layout.fragment_simple_buttons, container, false);

        inputTextView = ((TextView)getActivity().findViewById(R.id.inputTextViewID));
        historyTextView = ((TextView)getActivity().findViewById(R.id.historyTextViewID));

        inputString =  inputTextView.getText().toString();
        historyString = historyTextView.getText().toString();


        Button bone = simpleButtonsView.findViewById(R.id.button1);
        Button btwo = simpleButtonsView.findViewById(R.id.button2);
        Button bthree = simpleButtonsView.findViewById(R.id.button3);
        Button bfour = simpleButtonsView.findViewById(R.id.button4);
        Button bfive = simpleButtonsView.findViewById(R.id.button5);
        Button bsix = simpleButtonsView.findViewById(R.id.button6);
        Button bseven = simpleButtonsView.findViewById(R.id.button7);
        Button beight = simpleButtonsView.findViewById(R.id.button8);
        Button bnine = simpleButtonsView.findViewById(R.id.button9);
        Button bzero = simpleButtonsView.findViewById(R.id.button0);
        Button bpoint = simpleButtonsView.findViewById(R.id.buttonPoint);
        final Button bac = simpleButtonsView.findViewById(R.id.buttonAC);
        Button bundo = simpleButtonsView.findViewById(R.id.buttonUNDO);
        Button badd = simpleButtonsView.findViewById(R.id.buttonADD);
        Button bminus = simpleButtonsView.findViewById(R.id.buttonMINUS);
        Button bmult = simpleButtonsView.findViewById(R.id.buttonMULT);
        Button bdev = simpleButtonsView.findViewById(R.id.buttonDEV);
        Button bcalc = simpleButtonsView.findViewById(R.id.buttonCALC);


        final View.OnClickListener shortClickListener= new View.OnClickListener() {
            @Override
            final public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.button1: addLetter("1"); break;
                    case R.id.button2: addLetter("2"); break;
                    case R.id.button3: addLetter("3"); break;
                    case R.id.button4: addLetter("4"); break;
                    case R.id.button5: addLetter("5"); break;
                    case R.id.button6: addLetter("6"); break;
                    case R.id.button7: addLetter("7"); break;
                    case R.id.button8: addLetter("8"); break;
                    case R.id.button9: addLetter("9"); break;

                    case R.id.button0:
                        if(inputString.length()==0){
                            addLetter("0.");
                            pointPressedBool = true;
                        }
                        else {
                            if( inputString.charAt(inputString.length()-1)=='+'||
                                    inputString.charAt(inputString.length()-1)=='-'||
                                    inputString.charAt(inputString.length()-1)=='/'||
                                    inputString.charAt(inputString.length()-1)=='*'){
                                    addLetter("0.");
                                    pointPressedBool = true;
                            }
                            else{
                                addLetter("0");
                            }
                        }

                        break;

                    case R.id.buttonPoint:
                        if(!pointPressedBool) {
                            if (inputString.length() == 0) {
                                addLetter("0.");
                            }
                            else{
                                if( inputString.charAt(inputString.length()-1)=='+'||
                                    inputString.charAt(inputString.length()-1)=='-'||
                                    inputString.charAt(inputString.length()-1)=='/'||
                                    inputString.charAt(inputString.length()-1)=='*'){
                                        addLetter("0.");
                                }
                                else {
                                    addLetter(".");
                                }
                            }
                            pointPressedBool=true;
                        }
                        break;

                    case R.id.buttonUNDO:
                        clearLetter();
                        break;

                    case R.id.buttonAC: clearInput(); break;
                    case R.id.buttonADD: addSign("+"); break;
                    case R.id.buttonMINUS: addSign("-"); break;
                    case R.id.buttonMULT: addSign("*"); break;
                    case R.id.buttonDEV: addSign("/"); break;
                    case R.id.buttonCALC: solve(); break;

                }

            }
        };

        final View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonAC: clearALL(); break;
                    case R.id.buttonUNDO: clearInput(); break;
                }
                return false;
            }
        };

        bone.setOnClickListener(shortClickListener);
        btwo.setOnClickListener(shortClickListener);
        bthree.setOnClickListener(shortClickListener);
        bfour.setOnClickListener(shortClickListener);
        bfive.setOnClickListener(shortClickListener);
        bsix.setOnClickListener(shortClickListener);
        bseven.setOnClickListener(shortClickListener);
        beight.setOnClickListener(shortClickListener);
        bnine.setOnClickListener(shortClickListener);
        bzero.setOnClickListener(shortClickListener);
        bpoint.setOnClickListener(shortClickListener);

        bundo.setOnClickListener(shortClickListener);
        bac.setOnClickListener(shortClickListener);

        badd.setOnClickListener(shortClickListener);
        bminus.setOnClickListener(shortClickListener);
        bmult.setOnClickListener(shortClickListener);
        bdev.setOnClickListener(shortClickListener);

        bcalc.setOnClickListener(shortClickListener);

        bac.setOnLongClickListener(longClickListener);
        bundo.setOnLongClickListener(longClickListener);

        return simpleButtonsView;
    }

    public void addLetter(String letter){
        inputString+=letter;
        inputTextView.setText(inputString);
    }

    public void clearLetter(){
        try {
            if (inputString.length() > 0) {

                if(inputString.charAt(inputString.length()-1)=='.' ||
                        inputString.charAt(inputString.length()-1)=='+' ||
                        inputString.charAt(inputString.length()-1)=='-' ||
                        inputString.charAt(inputString.length()-1)=='*' ||
                        inputString.charAt(inputString.length()-1)=='/'){
                    pointPressedBool = false;
                }

                inputString = inputString.substring(0, inputString.length() - 1);
            }
            inputTextView.setText(inputString);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "Nothing to UNDO", Toast.LENGTH_LONG).show();
        }
    }

    public void clearInput(){
        pointPressedBool = false;
        inputString="";
        inputTextView.setText(inputString);
    }

    public void addSign(String sign){

        if(inputString.length()>0) {
            if (inputString.charAt(inputString.length() - 1) == '.' ||
                    inputString.charAt(inputString.length() - 1) == '+' ||
                    inputString.charAt(inputString.length() - 1) == '-' ||
                    inputString.charAt(inputString.length() - 1) == '*' ||
                    inputString.charAt(inputString.length() - 1) == '/') {
                clearLetter();
            }
            inputString+=sign;
        }
        pointPressedBool=false;

        inputTextView.setText(inputString);
    }

    public void solve(){

        try {
            double result;

            Expression e = new ExpressionBuilder(inputString).build();
            result = e.evaluate();
            historyString+="\n";
            historyString+=inputString;
            historyTextView.setText(historyString + "=");

            if ((result % 1) == 0) {
                int temp = (int) result;
                inputString = String.valueOf(temp);
                historyString+=" = " + inputString;
            } else {
                result = Math.round(result * 100000000.0) / 100000000.0;
                inputString = String.valueOf(result);
                historyString+=" = " + inputString;
                pointPressedBool=true;
            }
            inputTextView.setText(inputString);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "Solve error", Toast.LENGTH_LONG).show();
        }
    }

    public void clearALL(){
        pointPressedBool = false;
        inputString="";
        historyString="";
        inputTextView.setText(inputString);
        historyTextView.setText(historyString);
    }


}
