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

import java.util.ArrayList;
import java.util.List;


public class FragmentScientific extends Fragment {


    View scienceButtonsView = null;
    TextView inputTextView = null;
    TextView historyTextView = null;
    String inputString = "";
    String historyString="";
    Boolean pointPressedBool = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        scienceButtonsView = inflater.inflate(R.layout.fragment_scientific_buttons, container, false);

        inputTextView = ((TextView)getActivity().findViewById(R.id.inputTextViewID));
        historyTextView = ((TextView)getActivity().findViewById(R.id.historyTextViewID));

        inputString =  inputTextView.getText().toString();
        historyString=historyTextView.getText().toString();

        List<Button> buttonList = getListOfButtons(scienceButtonsView);
        View.OnClickListener shortClickListener = setOnShortClickListener();
        View.OnLongClickListener longClickListener = setOnLongClickListener();
        setBtnListeners(buttonList, shortClickListener,longClickListener);

        return scienceButtonsView;
    }

    private View.OnLongClickListener setOnLongClickListener() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonAC: clearALL(); break;
                    case R.id.buttonUNDO: clearInput(); break;
                    case R.id.buttonMOD:
                        Toast.makeText(getContext(),"Mod == Остаток от деления",Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        };
    }

    private View.OnClickListener setOnShortClickListener() {

        return new View.OnClickListener() {
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

                    case R.id.buttonUNDO: clearLetter(); break;

                    case R.id.buttonAC: clearInput(); break;
                    case R.id.buttonADD: addSign("+"); break;
                    case R.id.buttonMINUS: addSign("-"); break;
                    case R.id.buttonMULT: addSign("*"); break;
                    case R.id.buttonDEV: addSign("/"); break;
                    case R.id.buttonCALC: solve(); break;

                    case R.id.buttonE:
                        if(!pointPressedBool) addLetter(String.valueOf(Math.E));
                        pointPressedBool = true;
                        break;

                    case R.id.buttonPI:
                        if(!pointPressedBool) addLetter(String.valueOf(Math.PI));
                        pointPressedBool = true;
                        break;


                    case R.id.buttonSIN:
                        addSinCosTanLog("sin(");
                        break;

                    case R.id.buttonCOS:
                        addSinCosTanLog("cos(");
                        break;
                    case R.id.buttonTAN:
                        addSinCosTanLog("tan(");
                        break;

                    case R.id.buttonLOG:
                        addSinCosTanLog("log(");
                        break;
                    case R.id.buttonSQR:
                        addPowBracketsMod("^(2)");
                        break;

                    case R.id.buttonSQRT:
                        addPowBracketsMod("^(1/2)");
                        break;
                    case R.id.buttonPOW:
                        addPowBracketsMod("^");
                        break;
                    case R.id.buttonOPENBR:
                        addPowBracketsMod("(");
                        break;
                    case R.id.buttonCLOSEBR:
                        addPowBracketsMod(")");
                        break;
                    case R.id.buttonMOD:
                        addPowBracketsMod("%");
                        break;
                }

            }
        };
    }

    private List<Button> getListOfButtons(View view) {
        Button bone = view.findViewById(R.id.button1);
        Button btwo = view.findViewById(R.id.button2);
        Button bthree = view.findViewById(R.id.button3);
        Button bfour = view.findViewById(R.id.button4);
        Button bfive = view.findViewById(R.id.button5);
        Button bsix = view.findViewById(R.id.button6);
        Button bseven = view.findViewById(R.id.button7);
        Button beight = view.findViewById(R.id.button8);
        Button bnine = view.findViewById(R.id.button9);
        Button bzero = view.findViewById(R.id.button0);
        Button bpoint = view.findViewById(R.id.buttonPoint);
        final Button bac = view.findViewById(R.id.buttonAC);
        Button bundo = view.findViewById(R.id.buttonUNDO);
        Button badd = view.findViewById(R.id.buttonADD);
        Button bminus = view.findViewById(R.id.buttonMINUS);
        Button bmult = view.findViewById(R.id.buttonMULT);
        Button bdev = view.findViewById(R.id.buttonDEV);
        Button bcalc = view.findViewById(R.id.buttonCALC);

        Button bsin = view.findViewById(R.id.buttonSIN);
        Button bcos = view.findViewById(R.id.buttonCOS);
        Button btan = view.findViewById(R.id.buttonTAN);
        Button blog = view.findViewById(R.id.buttonLOG);
        Button bsqrt= view.findViewById(R.id.buttonSQRT);
        Button bsqr= view.findViewById(R.id.buttonSQR);
        Button bpow= view.findViewById(R.id.buttonPOW);
        Button bpi= view.findViewById(R.id.buttonPI);
        Button be= view.findViewById(R.id.buttonE);

        Button bmod= view.findViewById(R.id.buttonMOD);
        Button bopenBr= view.findViewById(R.id.buttonOPENBR);
        Button bcloseBr= view.findViewById(R.id.buttonCLOSEBR);

        List<Button> buttons = new ArrayList<>();
        buttons.add(bone);
        buttons.add(btwo);
        buttons.add(bthree);
        buttons.add(bfour);
        buttons.add(bfive);
        buttons.add(bsix);
        buttons.add(bseven);
        buttons.add(beight);
        buttons.add(bnine);
        buttons.add(bzero);
        buttons.add(bpoint);
        buttons.add(bundo);
        buttons.add(bac);
        buttons.add(badd);
        buttons.add(bminus);
        buttons.add(bmult);
        buttons.add(bdev);
        buttons.add(bcalc);
        buttons.add(bsin);
        buttons.add(bcos);
        buttons.add(btan);
        buttons.add(blog);
        buttons.add(bsqr);
        buttons.add(bsqrt);
        buttons.add(bpow);
        buttons.add(bpi);
        buttons.add(be);
        buttons.add(bmod);
        buttons.add(bopenBr);
        buttons.add(bcloseBr);

        return buttons;
    }

    private void setBtnListeners(List<Button> buttonList, View.OnClickListener shortListener, View.OnLongClickListener longListener) {
        for ( Button button : buttonList ){
            button.setOnClickListener(shortListener);
            button.setOnLongClickListener(longListener);
        }
    }

    public void addLetter(String letter){
        inputString+=letter;
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

    private void addPowBracketsMod(String funcString) {
        if (inputString.length()==0){
            Toast.makeText(getContext(), "Try to input something", Toast.LENGTH_LONG).show();
        }
        else{
            inputString+=funcString;
            inputTextView.setText(inputString);
        }
    }

    private void addSinCosTanLog(String funcString) {
        inputString+=funcString;
        inputTextView.setText(inputString);
    }

    private void closeUnclosedBrackets(){
        int open=0,close=0;
        if(inputString.length()>0){
            for(int i=0;i<inputString.length();i++){
                if(inputString.charAt(i)=='(') open++;
                if(inputString.charAt(i)==')') close++;
            }

            if(open>close){
                for(int j = 0; j<(open-close);j++){
                    inputString+=")";
                }
            }
        }
    }

    public void solve(){

        try {
            closeUnclosedBrackets();
        } catch (Exception e){
            Toast.makeText(getContext(), "Bracket error", Toast.LENGTH_LONG).show();
        }

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

    public void clearALL(){
        pointPressedBool = false;
        inputString="";
        historyString="";
        inputTextView.setText(inputString);
        historyTextView.setText(historyString);
    }
}

