/*
    CardController.java

    File structure :
            CreditCardValidator/
                    |---src/
                            |--- CardValidator/
                                    |--- *.java
                                    |--- CardController.java
                    |---images/
                            |--- *.png
 */

package CardValidator;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.HBox;


public class CardController
{
    String temp;
    String tempD;
    String tempC;

    @FXML
    private TextField numberDisplay;

    @FXML
    private TextField dateField;

    @FXML
    private TextField cvvField;

    @FXML
    private HBox hbox;

    @FXML
    private ImageView leftView;

    @FXML
    private ImageView rightView;


    public CardModel model;

    public CardController()
    {
        this.model = new CardModel();
    }


    public void initialize()
    {

        temp = numberDisplay.getText();
        tempD = dateField.getText();
        tempC = cvvField.getText();

        Image checkImage = new Image("file:images/checkmark.png");
        Image crossImage = new Image("file:images/crossmark.png");
        Image visaImage = new Image("file:images/visa.png");
        Image masterImage = new Image("file:images/mastercard.png");
        Image amxImage = new Image("file:images/amx.png");
        Image jcbImage = new Image("file:images/jcb.png");

        //hbox.setStyle("-fx-border-color: red;");


        numberDisplay.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVaule, Boolean newValue)
            {
                // Focus in
                if (newValue)
                {
                    temp = numberDisplay.getText();
                    hbox.setStyle("-fx-border-color: #0093ff;");
                    System.out.println("Force In Card = " + temp);
                }

                // Focus out
                if (oldVaule)
                {
                    String cardNumber = numberDisplay.getText();
                    hbox.setStyle("-fx-border-color: gray;");
                    System.out.println("Force Out Card = " + cardNumber + " temp = " + temp);

                    if (!(temp.equals(cardNumber)))
                    {
                        System.out.println("New String");

                        if (!model.isValidNumber((cardNumber)))
                        {
                            System.out.println("Invalid Number");
                            rightView.setImage(crossImage);
                        }
                        else
                        {
                            System.out.println("Valid Number");
                            rightView.setImage(checkImage);

                            switch (model.iCard) {
                                case 1:
                                    leftView.setImage(visaImage);
                                    break;
                                case 2:
                                    leftView.setImage(masterImage);
                                    break;
                                case 3:
                                    leftView.setImage(amxImage);
                                    break;
                                case 4:
                                    leftView.setImage(jcbImage);
                                    break;
                                default:
                                    break;
                            }       // switch end
                        }       // else end
                    }       // if not equal end
                }       // if oldValue end
            }       // end of void change
        });


        dateField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue)
            {
                // Focus in
                if (newValue)
                {
                    //tempD = dateField.getText();
                    System.out.println("Force In Date = " + tempD);
                    if (dateField.getText().equals("Invalid Date"))
                    {
                        System.out.println("Equal Invalid Date" + tempD);
                        dateField.setText(tempD);
                    }
                    else
                    {
                        System.out.println("Not Equal Invalid Date " + tempD);
                        tempD = dateField.getText();
                    }
                    System.out.println("Force In Date 1 = " + tempD);
                }

                // Focus out
                if (oldValue)
                {
                    String dateString = dateField.getText();
                    System.out.println("Force Out Date = " + dateString + " tempD = " + tempD);

                    if (!(tempD.equals(dateString)))
                    {
                        System.out.println("New Date");
                        if (model.isValidDate(dateString))
                        {
                            System.out.println("Valid Date");
                        }
                        else
                        {
                            System.out.println("Invalid Date");
                            tempD = dateField.getText();
                            dateField.setText("Invalid Date");
                        }
                    }
                }
            }
        });


        cvvField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue)
            {
                // Focus in
                if (newValue)
                {
                    //tempC = cvvField.getText();
                    System.out.println("Force In CVV = " + tempC);
                    if (cvvField.getText().equals("Invalid CVV"))
                    {
                        System.out.println("Equal Invalid CVV " + tempC);
                        cvvField.setText(tempC);
                    }
                    else
                    {
                        System.out.println("Not Equal Invalid CVV " + tempC);
                        tempC = cvvField.getText();
                    }
                    System.out.println("Force In CVV 1 = " + tempC);
                }

                // Focus out
                if (oldValue)
                {
                    String cvvNumber = cvvField.getText();
                    System.out.println("Force Out Date = " + cvvNumber + " tempC = " + tempC);

                    if (!(tempC.equals(cvvNumber)))
                    {
                        System.out.println("New CVV");

                        if (model.isValidCVV(cvvNumber))
                        {
                            System.out.println("Valid CVV");
                        }
                        else
                        {
                            System.out.println("Invalid CVV");
                            tempC = cvvField.getText();
                            cvvField.setText("Invalid CVV");
                        }
                    }
                }
            }
        });


        //cvvField.textProperty().addListener((obs, oldText, newText) -> {
        //    System.out.println("Old Text = " + oldText + "  New Text = " + newText);
        //});

        //dateField.textProperty().addListener((obs, oldText, newText) -> {
        //    System.out.println("Old Text = " + oldText + "  New Text = " + newText);
        //});
    }
}