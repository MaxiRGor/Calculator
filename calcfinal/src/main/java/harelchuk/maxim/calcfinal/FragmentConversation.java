package harelchuk.maxim.calcfinal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.digidemic.unitof.UnitOf;

import java.util.ArrayList;
import java.util.List;

public class FragmentConversation extends Fragment {

    TextView inputTextView = null;
    TextView historyTextView = null;

    String inputString = "";
    String historyString = "";

    Boolean pointPressedBool = false;

    Spinner whatSpinner=null;
    Spinner toSpinner=null;

    private int checkMenuPressed=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View conversationButtonsView = inflater.inflate(R.layout.fragment_convers_buttons, container, false);

        inputTextView = ((TextView)getActivity().findViewById(R.id.inputTextViewID));
        historyTextView = ((TextView)getActivity().findViewById(R.id.historyTextViewID));

        inputString =  inputTextView.getText().toString();
        historyString=historyTextView.getText().toString();

        fillSpinners(conversationButtonsView);

        List<Button> buttonList = getListOfButtons(conversationButtonsView);
        ImageButton buttonCalcIB = conversationButtonsView.findViewById(R.id.calcim);

        View.OnClickListener shortClickListener = setOnShortClickListener();
        View.OnLongClickListener longClickListener = setOnLongClickListener();

        setButtonListener(buttonList, shortClickListener,longClickListener);
        buttonCalcIB.setOnClickListener(shortClickListener);

        return conversationButtonsView;
    }

    private View.OnClickListener setOnShortClickListener() {

        return new View.OnClickListener() {
            @Override
            final public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.button1:
                        addLetter("1");
                        break;
                    case R.id.button2:
                        addLetter("2");
                        break;
                    case R.id.button3:
                        addLetter("3");
                        break;
                    case R.id.button4:
                        addLetter("4");
                        break;
                    case R.id.button5:
                        addLetter("5");
                        break;
                    case R.id.button6:
                        addLetter("6");
                        break;
                    case R.id.button7:
                        addLetter("7");
                        break;
                    case R.id.button8:
                        addLetter("8");
                        break;
                    case R.id.button9:
                        addLetter("9");
                        break;

                    case R.id.button0:
                        if (inputString.length() == 0) {
                            addLetter("0.");
                            pointPressedBool = true;
                        } else {
                            addLetter("0");
                        }
                        break;

                    case R.id.buttonPoint:
                        if (!pointPressedBool) {
                            if (inputString.length() == 0) {
                                addLetter("0.");
                            } else {
                                addLetter(".");
                            }
                            pointPressedBool = true;
                        }
                        break;

                    case R.id.buttonUNDO:
                        clearLetter();
                        break;

                    case R.id.buttonAC:
                        clearInput();
                        break;

                    case R.id.buttonCALC:
                        historyString+="\n";
                        whatToCalculate();
                        break;

                    case R.id.calcim:
                        historyString+="\n";
                        whatToCalculate();
                        break;
                }
            }
        };
    }

    private void fillSpinners(View conversationButtonsView) {

        whatSpinner = conversationButtonsView.findViewById(R.id.spinner_what);
        toSpinner = conversationButtonsView.findViewById(R.id.spinner_to);

        String title = (String)getActivity().getTitle();

        String length = (String) getContext().getString(R.string.lengthName);
        String  square= (String) getContext().getString(R.string.squareName);
        String volume = (String) getContext().getString(R.string.volumeName);
        String temperature= (String) getContext().getString(R.string.temperatureName);
        String speed= (String) getContext().getString(R.string.speedName);
        String time= (String) getContext().getString(R.string.timeName);
        String mass= (String) getContext().getString(R.string.massName);

        //int

        if(title.equals(length)) checkMenuPressed = 1;
        if(title.equals(square)) checkMenuPressed = 2;
        if(title.equals(volume)) checkMenuPressed = 3;
        if(title.equals(temperature)) checkMenuPressed = 4;
        if(title.equals(speed)) checkMenuPressed = 5;
        if(title.equals(time)) checkMenuPressed = 6;
        if(title.equals(mass)) checkMenuPressed = 7;

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = null;

        switch (checkMenuPressed){
            case 1:
                adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.length_items, android.R.layout.simple_spinner_item);
                break;
            case 2:
                adapter =ArrayAdapter.createFromResource(this.getActivity(), R.array.square_items, android.R.layout.simple_spinner_item);
                break;
            case 3:
                adapter =ArrayAdapter.createFromResource(this.getActivity(), R.array.volume_items, android.R.layout.simple_spinner_item);
                break;
            case 4:
                adapter =ArrayAdapter.createFromResource(this.getActivity(), R.array.temperature_items, android.R.layout.simple_spinner_item);
                break;
            case 5:
                adapter =ArrayAdapter.createFromResource(this.getActivity(), R.array.speed_items, android.R.layout.simple_spinner_item);
                break;
            case 6:
                adapter =ArrayAdapter.createFromResource(this.getActivity(), R.array.time_items, android.R.layout.simple_spinner_item);
                break;
            case 7:
                adapter =ArrayAdapter.createFromResource(this.getActivity(), R.array.mass_items, android.R.layout.simple_spinner_item);
                break;
        }

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        try {
            whatSpinner.setAdapter(adapter);
            toSpinner.setAdapter(adapter);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "Mistake", Toast.LENGTH_LONG).show();
        }
    }

    private View.OnLongClickListener setOnLongClickListener() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonAC: clearALL(); break;
                    case R.id.buttonUNDO: clearInput(); break;
                }
                return false;
            }
        };
    }

    private void setButtonListener(List<Button> buttonList, View.OnClickListener shortListener, View.OnLongClickListener longListener) {
        for ( Button button : buttonList ){
            button.setOnClickListener(shortListener);
            button.setOnLongClickListener(longListener);
        }
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
        Button bac = view.findViewById(R.id.buttonAC);
        Button bundo = view.findViewById(R.id.buttonUNDO);
        Button bcalc = view.findViewById(R.id.buttonCALC);

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
        buttons.add(bcalc);

        return buttons;
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

    public void clearALL(){
        pointPressedBool = false;
        inputString="";
        historyString="";
        inputTextView.setText(inputString);
        historyTextView.setText(historyString);
    }

    private void whatToCalculate() {

        //String title = (String)getActivity().getTitle();

        try {
            switch (checkMenuPressed){
                case(1):
                    convertLength();
                    break;
                case(2):
                    convertSquare();
                    break;
                case(3):
                    convertVolume();
                    break;
                case(4):
                    convertTemperature();
                    break;
                case(5):
                    convertSpeed();
                    break;
                case(6):
                    convertTime();
                    break;
                case(7):
                    convertMass();
                    break;
            }
        } catch (Exception E){
            Toast.makeText(getContext(), "Input correct data", Toast.LENGTH_LONG).show();
        }
    }

    private void convertLength() {

        String selectedWhat = whatSpinner.getSelectedItem().toString();
        String selectedTo = toSpinner.getSelectedItem().toString();
        String tempString = "";
        double result = 0;

        UnitOf.Length unit = new UnitOf.Length();

        Object object = null;

        double value = Double.parseDouble(inputString);

        if(selectedWhat.equals(getContext().getString(R.string.kilometers)))          object = unit.fromKilometers(value);
        if(selectedWhat.equals(getContext().getString(R.string.meters)))             object = unit.fromMeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.decimeters)))             object = unit.fromDecimeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.centimeters)))             object = unit.fromCentimeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.millimeters)))             object = unit.fromMillimeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.micrometers)))             object = unit.fromMicrometers(value);
        if(selectedWhat.equals(getContext().getString(R.string.nanometers)))             object = unit.fromNanometers(value);
        if(selectedWhat.equals(getContext().getString(R.string.picometers)))          object = unit.fromPicometers(value);
        if(selectedWhat.equals(getContext().getString(R.string.feet)))                  object = unit.fromFeet(value);
        if(selectedWhat.equals(getContext().getString(R.string.miles)))             object = unit.fromMiles(value);
        if(selectedWhat.equals(getContext().getString(R.string.inches)))             object = unit.fromInches(value);
        if(selectedWhat.equals(getContext().getString(R.string.yards)))             object = unit.fromYards(value);

        if(selectedTo.equals(getContext().getString(R.string.kilometers))) result = ((UnitOf.Length) object).toKilometers();
        if(selectedTo.equals(getContext().getString(R.string.meters))) result = ((UnitOf.Length) object).toMeters();
        if(selectedTo.equals(getContext().getString(R.string.decimeters))) result = ((UnitOf.Length) object).toDecimeters();
        if(selectedTo.equals(getContext().getString(R.string.centimeters))) result = ((UnitOf.Length) object).toCentimeters();
        if(selectedTo.equals(getContext().getString(R.string.millimeters))) result = ((UnitOf.Length) object).toMillimeters();
        if(selectedTo.equals(getContext().getString(R.string.micrometers))) result = ((UnitOf.Length) object).toMicrometers();
        if(selectedTo.equals(getContext().getString(R.string.nanometers))) result = ((UnitOf.Length) object).toNanometers();
        if(selectedTo.equals(getContext().getString(R.string.picometers))) result = ((UnitOf.Length) object).toPicometers();
        if(selectedTo.equals(getContext().getString(R.string.miles))) result = ((UnitOf.Length) object).toMiles();
        if(selectedTo.equals(getContext().getString(R.string.feet))) result = ((UnitOf.Length) object).toFeet();
        if(selectedTo.equals(getContext().getString(R.string.yards))) result = ((UnitOf.Length) object).toYards();
        if(selectedTo.equals(getContext().getString(R.string.inches))) result = ((UnitOf.Length) object).toInches();


        if(result>2147483647){
            Toast.makeText(getContext(), "Too large answer", Toast.LENGTH_LONG).show();
        }
        else{
            if ((result % 1) == 0) {
                int temp = (int) result;
                tempString = String.valueOf(temp);

            } else {
                result = Math.round(result * 100000000.0) / 100000000.0;
                tempString = String.valueOf(result);
            }

            historyString += inputString + " " + selectedWhat + " = " + tempString + " " + selectedTo;
            historyTextView.setText(historyString);

            inputString = tempString;
            inputTextView.setText(inputString);
        }
    }

    private void convertSquare() {
/*
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this.getActivity(), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerWhat = (Spinner) findViewById(R.id.spinner_what);

        spinnerWhat.setAdapter(adapter);
*/

        final String selectedWhat = whatSpinner.getSelectedItem().toString();
        final String selectedTo = toSpinner.getSelectedItem().toString();

        String tempString = "";
        double result = 0;

        UnitOf.Area unit = new UnitOf.Area();

        Object object = null;

        double value = Double.parseDouble(inputString);


        if(selectedWhat.equals(getContext().getString(R.string.sqkilometers)))          object = unit.fromSquareKilometers(value);
        if(selectedWhat.equals(getContext().getString(R.string.sqmeters)))             object = unit.fromSquareMeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.sqdecimeters)))             object = unit.fromSquareDecimeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.sqcentimeters)))             object = unit.fromSquareCentimeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.sqmillimeters)))             object = unit.fromSquareMillimeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.ha)))          object = unit.fromHectares(value);
        if(selectedWhat.equals(getContext().getString(R.string.are)))                  object = unit.fromAres(value);
        if(selectedWhat.equals(getContext().getString(R.string.acre)))                  object = unit.fromAcres(value);
        if(selectedWhat.equals(getContext().getString(R.string.sqmiles)))             object = unit.fromSquareMiles(value);
        if(selectedWhat.equals(getContext().getString(R.string.sqfeet)))             object = unit.fromSquareFeet(value);
        if(selectedWhat.equals(getContext().getString(R.string.sqinches)))             object = unit.fromSquareInches(value);
        if(selectedWhat.equals(getContext().getString(R.string.sqyards)))             object = unit.fromSquareYards(value);

        if(selectedTo.equals(getContext().getString(R.string.sqkilometers))) result = ((UnitOf.Area) object).toSquareKilometers();
        if(selectedTo.equals(getContext().getString(R.string.sqmeters))) result = ((UnitOf.Area) object).toSquareMeters();
        if(selectedTo.equals(getContext().getString(R.string.sqdecimeters))) result = ((UnitOf.Area) object).toSquareDecimeters();
        if(selectedTo.equals(getContext().getString(R.string.sqcentimeters))) result = ((UnitOf.Area) object).toSquareCentimeters();
        if(selectedTo.equals(getContext().getString(R.string.sqmillimeters))) result = ((UnitOf.Area) object).toSquareMillimeters();
        if(selectedTo.equals(getContext().getString(R.string.ha))) result = ((UnitOf.Area) object).toHectares();
        if(selectedTo.equals(getContext().getString(R.string.are))) result = ((UnitOf.Area) object).toAres();
        if(selectedTo.equals(getContext().getString(R.string.acre))) result = ((UnitOf.Area) object).toAcres();
        if(selectedTo.equals(getContext().getString(R.string.sqmiles))) result = ((UnitOf.Area) object).toSquareMiles();
        if(selectedTo.equals(getContext().getString(R.string.sqfeet))) result = ((UnitOf.Area) object).toSquareFeet();
        if(selectedTo.equals(getContext().getString(R.string.sqyards))) result = ((UnitOf.Area) object).toSquareYards();
        if(selectedTo.equals(getContext().getString(R.string.sqinches))) result = ((UnitOf.Area) object).toSquareInches();


        if(result>2147483647){
            Toast.makeText(getContext(), "Too large answer", Toast.LENGTH_LONG).show();
        }
        else{
            if ((result % 1) == 0) {
                int temp = (int) result;
                tempString = String.valueOf(temp);

            } else {
                result = Math.round(result * 100000000.0) / 100000000.0;
                tempString = String.valueOf(result);
            }

            historyString += inputString + " " + selectedWhat + " = " + tempString + " " + selectedTo;
            historyTextView.setText(historyString);

            inputString = tempString;
            inputTextView.setText(inputString);
        }
    }

    private void convertVolume() {

        String selectedWhat = whatSpinner.getSelectedItem().toString();
        String selectedTo = toSpinner.getSelectedItem().toString();
        String tempString = "";
        double result = 0;

        UnitOf.Volume unit = new UnitOf.Volume();

        Object object = null;

        double value = Double.parseDouble(inputString);


        if(selectedWhat.equals(getContext().getString(R.string.volmeters)))             object = unit.fromCubicMeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.voldecimeters)))             object = unit.fromCubicDecimeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.volcentimeters)))             object = unit.fromCubicCentimeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.volmillimeters)))             object = unit.fromCubicMillimeters(value);
        if(selectedWhat.equals(getContext().getString(R.string.litres)))          object = unit.fromLiters(value);
        if(selectedWhat.equals(getContext().getString(R.string.millilitres)))                  object = unit.fromMilliliters(value);
        if(selectedWhat.equals(getContext().getString(R.string.volfeet)))             object = unit.fromCubicFeet(value);
        if(selectedWhat.equals(getContext().getString(R.string.volinches)))             object = unit.fromCubicInches(value);
        if(selectedWhat.equals(getContext().getString(R.string.volyards)))             object = unit.fromCubicYards(value);

        if(selectedTo.equals(getContext().getString(R.string.volmeters))) result = ((UnitOf.Volume) object).toCubicMeters();
        if(selectedTo.equals(getContext().getString(R.string.voldecimeters))) result = ((UnitOf.Volume) object).toCubicDecimeters();
        if(selectedTo.equals(getContext().getString(R.string.volcentimeters))) result = ((UnitOf.Volume) object).toCubicCentimeters();
        if(selectedTo.equals(getContext().getString(R.string.volmillimeters))) result = ((UnitOf.Volume) object).toCubicMillimeters();
        if(selectedTo.equals(getContext().getString(R.string.litres))) result = ((UnitOf.Volume) object).toLiters();
        if(selectedTo.equals(getContext().getString(R.string.millilitres))) result = ((UnitOf.Volume) object).toMilliliters();
        if(selectedTo.equals(getContext().getString(R.string.volfeet))) result = ((UnitOf.Volume) object).toCubicFeet();
        if(selectedTo.equals(getContext().getString(R.string.volyards))) result = ((UnitOf.Volume) object).toCubicYards();
        if(selectedTo.equals(getContext().getString(R.string.volinches))) result = ((UnitOf.Volume) object).toCubicInches();


        if(result>2147483647){
            Toast.makeText(getContext(), "Too large answer", Toast.LENGTH_LONG).show();
        }
        else{
            if ((result % 1) == 0) {
                int temp = (int) result;
                tempString = String.valueOf(temp);

            } else {
                result = Math.round(result * 100000000.0) / 100000000.0;
                tempString = String.valueOf(result);
            }

            historyString += inputString + " " + selectedWhat + " = " + tempString + " " + selectedTo;
            historyTextView.setText(historyString);

            inputString = tempString;
            inputTextView.setText(inputString);
        }
    }

    private void convertTemperature() {

        String selectedWhat = whatSpinner.getSelectedItem().toString();
        String selectedTo = toSpinner.getSelectedItem().toString();
        String tempString = "";
        double result = 0;

        UnitOf.Temperature unit = new UnitOf.Temperature();

        Object object = null;

        double value = Double.parseDouble(inputString);

        switch (selectedWhat){

            case ("C\u00B0"): object = unit.fromCelsius(value); break;
            case ("F\u00B0"): object = unit.fromFahrenheit(value); break;
            case ("K\u00B0"): object = unit.fromKelvin(value); break;

        }

        switch (selectedTo){
            case ("C\u00B0"): result = ((UnitOf.Temperature) object).toCelsius(); break;
            case ("F\u00B0"): result = ((UnitOf.Temperature) object).toFahrenheit(); break;
            case ("K\u00B0"): result = ((UnitOf.Temperature) object).toKelvin(); break;

        }

        if(result>2147483647){
            Toast.makeText(getContext(), "Too large answer", Toast.LENGTH_LONG).show();
        }
        else{
            if ((result % 1) == 0) {
                int temp = (int) result;
                tempString = String.valueOf(temp);

            } else {
                result = Math.round(result * 100000000.0) / 100000000.0;
                tempString = String.valueOf(result);
            }

            historyString += inputString + " " + selectedWhat + " = " + tempString + " " + selectedTo;
            historyTextView.setText(historyString);

            inputString = tempString;
            inputTextView.setText(inputString);
        }
    }

    private void convertSpeed() {


        String selectedWhat = whatSpinner.getSelectedItem().toString();
        String selectedTo = toSpinner.getSelectedItem().toString();
        String tempString = "";
        double result = 0;

        UnitOf.Speed unit = new UnitOf.Speed();

        Object object = null;

        double value = Double.parseDouble(inputString);

        if(selectedWhat.equals(getContext().getString(R.string.metresps)))             object = unit.fromMetersPerSecond(value);
        if(selectedWhat.equals(getContext().getString(R.string.kmph)))             object = unit.fromKilometersPerHour(value);
        if(selectedWhat.equals(getContext().getString(R.string.kmps)))             object = unit.fromKilometersPerSecond(value);
        if(selectedWhat.equals(getContext().getString(R.string.c)))             object = unit.fromLight(value);
        if(selectedWhat.equals(getContext().getString(R.string.kn)))          object = unit.fromKnots(value);
        if(selectedWhat.equals(getContext().getString(R.string.mph)))                  object = unit.fromMilesPerHour(value);
        if(selectedWhat.equals(getContext().getString(R.string.fps)))             object = unit.fromFeetPerSecond(value);
        if(selectedWhat.equals(getContext().getString(R.string.ips)))             object = unit.fromInchesPerSecond(value);

        if(selectedTo.equals(getContext().getString(R.string.metresps))) result = ((UnitOf.Speed) object).toMetersPerSecond();
        if(selectedTo.equals(getContext().getString(R.string.kmph))) result = ((UnitOf.Speed) object).toKilometersPerHour();
        if(selectedTo.equals(getContext().getString(R.string.kmps))) result = ((UnitOf.Speed) object).toKilometersPerSecond();
        if(selectedTo.equals(getContext().getString(R.string.c))) result = ((UnitOf.Speed) object).toLight();
        if(selectedTo.equals(getContext().getString(R.string.kn))) result = ((UnitOf.Speed) object).toKnots();
        if(selectedTo.equals(getContext().getString(R.string.mph))) result = ((UnitOf.Speed) object).toMilesPerHour();
        if(selectedTo.equals(getContext().getString(R.string.fps))) result = ((UnitOf.Speed) object).toFeetPerSecond();
        if(selectedTo.equals(getContext().getString(R.string.ips))) result = ((UnitOf.Speed) object).toInchesPerSecond();


        if(result>2147483647){
            Toast.makeText(getContext(), "Too large answer", Toast.LENGTH_LONG).show();
        }
        else{
            if ((result % 1) == 0) {
                int temp = (int) result;
                tempString = String.valueOf(temp);

            } else {
                result = Math.round(result * 100000000.0) / 100000000.0;
                tempString = String.valueOf(result);
            }

            historyString += inputString + " " + selectedWhat + " = " + tempString + " " + selectedTo;
            historyTextView.setText(historyString);

            inputString = tempString;
            inputTextView.setText(inputString);
        }
    }

    private void convertTime() {

        String selectedWhat = whatSpinner.getSelectedItem().toString();
        String selectedTo = toSpinner.getSelectedItem().toString();
        String tempString = "";
        double result = 0;

        UnitOf.Time unit = new UnitOf.Time();

        Object object = null;

        double value = Double.parseDouble(inputString);

        if(selectedWhat.equals(getContext().getString(R.string.year)))             object = unit.fromYears(value);
        if(selectedWhat.equals(getContext().getString(R.string.week)))             object = unit.fromWeeks(value);
        if(selectedWhat.equals(getContext().getString(R.string.day)))             object = unit.fromDays(value);
        if(selectedWhat.equals(getContext().getString(R.string.hour)))             object = unit.fromHours(value);
        if(selectedWhat.equals(getContext().getString(R.string.min)))          object = unit.fromMinutes(value);
        if(selectedWhat.equals(getContext().getString(R.string.sec)))                  object = unit.fromSeconds(value);
        if(selectedWhat.equals(getContext().getString(R.string.ms)))             object = unit.fromMilliseconds(value);
        if(selectedWhat.equals(getContext().getString(R.string.mcs)))             object = unit.fromMicroseconds(value);

        if(selectedTo.equals(getContext().getString(R.string.year))) result = ((UnitOf.Time) object).toYears();
        if(selectedTo.equals(getContext().getString(R.string.week))) result = ((UnitOf.Time) object).toWeeks();
        if(selectedTo.equals(getContext().getString(R.string.day))) result = ((UnitOf.Time) object).toDays();
        if(selectedTo.equals(getContext().getString(R.string.hour))) result = ((UnitOf.Time) object).toHours();
        if(selectedTo.equals(getContext().getString(R.string.min))) result = ((UnitOf.Time) object).toMinutes();
        if(selectedTo.equals(getContext().getString(R.string.sec))) result = ((UnitOf.Time) object).toSeconds();
        if(selectedTo.equals(getContext().getString(R.string.ms))) result = ((UnitOf.Time) object).toMilliseconds();
        if(selectedTo.equals(getContext().getString(R.string.mcs))) result = ((UnitOf.Time) object).toMicroseconds();


        if(result>2147483647){
            Toast.makeText(getContext(), "Too large answer", Toast.LENGTH_LONG).show();
        }
        else{
            if ((result % 1) == 0) {
                int temp = (int) result;
                tempString = String.valueOf(temp);

            } else {
                result = Math.round(result * 100000000.0) / 100000000.0;
                tempString = String.valueOf(result);
            }

            historyString += inputString + " " + selectedWhat + " = " + tempString + " " + selectedTo;
            historyTextView.setText(historyString);

            inputString = tempString;
            inputTextView.setText(inputString);
        }
    }

    private void convertMass() {

        String selectedWhat = whatSpinner.getSelectedItem().toString();
        String selectedTo = toSpinner.getSelectedItem().toString();
        String tempString = "";
        double result = 0;

        UnitOf.Mass unit = new UnitOf.Mass();

        Object object = null;

        double value = Double.parseDouble(inputString);

        if(selectedWhat.equals(getContext().getString(R.string.t)))             object = unit.fromTonsMetric(value);
        if(selectedWhat.equals(getContext().getString(R.string.kg)))             object = unit.fromKilograms(value);
        if(selectedWhat.equals(getContext().getString(R.string.g)))             object = unit.fromGrams(value);
        if(selectedWhat.equals(getContext().getString(R.string.mg)))             object = unit.fromMilligrams(value);
        if(selectedWhat.equals(getContext().getString(R.string.q)))          object = unit.fromQuintals(value);
        if(selectedWhat.equals(getContext().getString(R.string.lb)))                  object = unit.fromPounds(value);
        if(selectedWhat.equals(getContext().getString(R.string.oz)))             object = unit.fromOuncesMetric(value);
        if(selectedWhat.equals(getContext().getString(R.string.ct)))             object = unit.fromCarats(value);

        if(selectedTo.equals(getContext().getString(R.string.t))) result = ((UnitOf.Mass) object).toTonsMetric();
        if(selectedTo.equals(getContext().getString(R.string.kg))) result = ((UnitOf.Mass) object).toKilograms();
        if(selectedTo.equals(getContext().getString(R.string.g))) result = ((UnitOf.Mass) object).toGrams();
        if(selectedTo.equals(getContext().getString(R.string.mg))) result = ((UnitOf.Mass) object).toMilligrams();
        if(selectedTo.equals(getContext().getString(R.string.q))) result = ((UnitOf.Mass) object).toQuintals();
        if(selectedTo.equals(getContext().getString(R.string.lb))) result = ((UnitOf.Mass) object).toPounds();
        if(selectedTo.equals(getContext().getString(R.string.oz))) result = ((UnitOf.Mass) object).toOuncesMetric();
        if(selectedTo.equals(getContext().getString(R.string.ct))) result = ((UnitOf.Mass) object).toCarats();

        if(result>2147483647){
            Toast.makeText(getContext(), "Too large answer", Toast.LENGTH_LONG).show();
        }
        else{
            if ((result % 1) == 0) {
                int temp = (int) result;
                tempString = String.valueOf(temp);

            } else {
                result = Math.round(result * 100000000.0) / 100000000.0;
                tempString = String.valueOf(result);
            }

            historyString += inputString + " " + selectedWhat + " = " + tempString + " " + selectedTo;
            historyTextView.setText(historyString);

            inputString = tempString;
            inputTextView.setText(inputString);
        }
    }
}