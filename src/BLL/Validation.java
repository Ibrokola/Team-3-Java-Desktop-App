package BLL;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Validation {
    /*
    * Purpose: To Add validation that is usable across all classes
    * Author: Brent Ward
    * Module:
    * Date: May 23, 2019
    * */

    //checks that a textfield is not empty
    public static boolean isProvided(TextField text, String name){
        if(text.getText() != null){
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, name + " must be filled in!");
            alert.show();
            return false;
        }
    }

    public static boolean isInteger(int num, String name) {
        if (num - Math.floor(num) == 0 && num >= 0) {
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, name + " must be a valid number!");
            alert.show();
            return false;
        }
    }
}
