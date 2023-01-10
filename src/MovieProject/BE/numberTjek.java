package MovieProject.BE;

import java.util.regex.Pattern;

public class numberTjek {

    public boolean tjekNumberIsOK(String tjekNumberIsOK)
    {
        boolean isItANumber=false;

        String decimalPattern = "([0-9]*)\\.([0-9]*)";
        String decimalPattern1 = "([0-9]*)";

        boolean match = Pattern.matches(decimalPattern, tjekNumberIsOK);
        boolean match1 = Pattern.matches(decimalPattern1, tjekNumberIsOK);

        if (match || match1)
            isItANumber=true;

return isItANumber;


    }






}
