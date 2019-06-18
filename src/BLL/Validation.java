package BLL;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Validation {
    /*
    * Purpose: To Add validation that is usable across all classes
    * Author: Brent Ward
    * Module: PROJ-207-OSD
    * Date: May 23, 2019
    * */

    //checks that a textfield is not empty
    public static boolean isProvided(TextField text, String name){
        if(text.getText().trim().equals("")){
//            return true;
            Alert alert = new Alert(Alert.AlertType.ERROR, name + " must be filled in!");
            alert.show();
            return false;
        }
        else{
//            Alert alert = new Alert(Alert.AlertType.ERROR, name + " must be filled in!");
//            alert.show();
//            return false;
            return true;
        }
    }

    //checks a number is whole, and is above 0
    public static boolean isInteger(int num, String name) {
        if (num - Math.floor(num) == 0 && num >= 0) {
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, name + " must be a valid number!");
            alert.show();
            return false;
        }
    }

    //checks that a combo box has a current selection
    public static boolean hasSelection(ComboBox combo, String name){
        if(combo.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, name + " must have a selection!");
            alert.show();
            return false;
        }else {
            return true;
        }
    }
}
