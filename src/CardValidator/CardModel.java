/*
    CardModel.java

    File structure :
            CreditCardValidator/
                    |---src/
                            |--- CardValidator/
                                    |--- *.java
                                    |--- CardModel.java
                    |---images/
                            |--- *.png
*/

package CardValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardModel {
    // static int iCard;        // 1 for visa; 2 for MaterCard; 3 for AMX; 4 for JCB
    public int iCard;        // 1 for visa; 2 for MaterCard; 3 for AMX; 4 for JCB

    public  CardModel()
    {

    }

    /*
    public static boolean isValidDate(String strDate)
    {
        // Check if date is 'null'
        if (strDate.trim().equals(""))
        {
            return true;
        }
        // Date is not 'null'
        else
        {
            //
            // Set preferred date format,
            // For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/yyyy");
            sdfrmt.setLenient(false);
            // Create Date object parse the string into date
            try
            {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate + " is valid date format");
            }
            // Date format is invalid
            catch (ParseException e)
            {
                System.out.println(strDate + " is Invalid Date format");
                return false;
            }
            // Return true if date format is valid
            return true;
        }
    }
    */



    // Return true if the expiration date is valid
    public static boolean isValidDate(String strDate)
    {
        // Regex patten
        //String regex = "^[0-1]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        String regex = "(1[0-2]|0[1-9]|[1-9])/(?:[0-9]{2})?[0-9]{2}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // Find match between given string and regular expression using Pattern.matcher()
        Matcher m = p.matcher(strDate);

        // Return if the string matched the ReGex
        if (m.matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isValidCVV(String cvvNumber)
    {
        int strLength = cvvNumber.length();

        if(strLength == 3)
        {
            if (iCard == 1 || iCard == 2 || iCard == 4)
            {
                System.out.println("CVV 3 digits of Visa, master, or JCB");
                return true;
            }
            else
            {
                System.out.println("CVV 3 digits but not Visa, master, or JCB");
                return false;
            }
        }
        else if(strLength == 4)
        {
            if (iCard == 3)
            {
                System.out.println("CVV 4 digits of Amex");
                return true;
            }
            else
            {
                System.out.println("CVV 4 digits not Amex");
                return false;
            }
        }
        else
        {
            System.out.println("CVV too short or too long");
            return false;
        }
    }


    // Return true if the card number is valid
    public boolean isValidNumber(String strNumber)
    {
        if (strNumber.length() < 13)
        {
            System.out.println("Too Short");
            return false;
        }
        if (strNumber.length() > 16)
        {
            System.out.println("Too Long");
            return false;
        }

        if (!onlyDigits(strNumber))
        {
            System.out.println("Contain no digit");
            return false;
        }

        if (!isCreditCard(strNumber))
        {
            System.out.println("Not a Credit Card");
            return false;
        }
        else
        {
            if (iCard == 1)
            {
                System.out.println("Visa");
                return true;
            }
            else if (iCard == 2)
            {
                System.out.println("Mastercard");
                return true;
            }
            else if (iCard == 3) {
                System.out.println("AMX");
                return true;
            }
            else if (iCard == 4)
            {
                System.out.println("JCB");
                return true;
            }
            else
            {
                System.out.println("Not a Credit Card");
                return false;
            }
        }
    }

    public static boolean onlyDigits(String strNum)
    {
        // Regex to check string contains only digits
        String regex = "[0-9]+";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // Find match between given string and regular expression using Pattern.matcher()
        Matcher m = p.matcher(strNum);

        // Return if the string matched the ReGex
        return m.matches();
    }


    public boolean isCreditCard(String strNum)
    {
        // Regex patten
        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
                "(?<mastercard>5[0-9]{15})|" +
                "(?<amx>3[47][0-9]{13})|" +
                "(?<jcb>35[0-9]{14}))$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // Find match between given string and regular expression using Pattern.matcher()
        Matcher m = p.matcher(strNum);

        // Return if the string matched the ReGex
        if (m.matches())
        {
            if (m.group("visa") != null)
            {
                iCard = 1;
                return true;
            }
            else if (m.group("mastercard") != null)
            {
                iCard = 2;
                return true;
            }
            else if(m.group("amx") != null)
            {
                iCard = 3;
                return true;
            }
            else if (m.group("jcb") !=null)
            {
                iCard = 4;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}