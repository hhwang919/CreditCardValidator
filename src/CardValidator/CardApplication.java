/*
    CardApplication.java

    File structure :
            CreditCardValidator/
                    |---src/
                            |--- CardValidator/
                                    |--- *.java
                                    |--- CardApplication.java
                    |---images/
                            |--- *.png
 */

package CardValidator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CardApplication extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("CardView.fxml"));
        primaryStage.setTitle("Credit Card Validator");
        primaryStage.setScene(new Scene(root, 600, 320));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
        System.out.println("Launched!");
    }
}
