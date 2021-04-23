/*
Pedram Maleki CMSC 403
Professor: Zack Whitten

RaceTrack class will be a game using multi-threading
*
* */

//imports
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicBoolean;

//RaceTrack class will do all the work and extends Application to use
//JavaFx
public class RaceTrack extends Application {
    //Creating the 3 buttons
    Button StartButton;
    Button PauseButton;
    Button ResetButton;

    //these flags will hold the place for the X
    //value of the car image
    double flagX1=20;
    double flagX2=20;
    double flagX3=20;

    //using imageView to show the lilCar
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;

    //creating a boolean value to stop, start and pause the game
    AtomicBoolean run= new AtomicBoolean(true);
    //Creating an Alert object to declare a winner
    Alert alert=new Alert(Alert.AlertType.INFORMATION);

    //main only calls the launch method of JavaFx
    public static void main(String [] args){

        launch(args);

    }

    /*
    * This method will create a thread for car and move the image
    * */
    public void moveCar1(){
        //creating a new thread
        new Thread(() -> {
            try {
                //we keep moving the image as long as run is true
                while (run.get()) {
                    //moving the image by a random number
                    flagX1=flagX1+Math.random()*10;
                    //using runLater to update
                    Platform.runLater(() -> imageView.setX(flagX1));
                    //checking to see if the has won
                    if(flagX1>415){
                        run.set(false);
                        //display winner alert
                        Platform.runLater(() -> {
                            alert.setContentText("Car One Wins!");
                            alert.show();
                        });
                    }
                    //sleeping for 50 ms per instructions
                    Thread.sleep(50);

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        ).start();

    }
    /*
     * This method will create a thread for car and move the image
     * */
    public void moveCar2(){
        //new thread
        new Thread(() -> {
            try {
                //we move as long as run is true
                while (run.get()) {
                    //getting random number
                    flagX2=flagX2+Math.random()*10;
                    Platform.runLater(() -> imageView2.setX(flagX2));
                    //checking for winner and display winner
                    if(flagX2>415){
                        run.set(false);
                        Platform.runLater(() -> {
                            alert.setContentText("Car Two Wins!");
                            alert.show();
                        });
                    }
                    Thread.sleep(50);

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        ).start();

    }
    //This is identical to the last two methods
    public void moveCar3(){

        new Thread(() -> {
            try {
                while (run.get()) {
                    flagX3=flagX3+Math.random()*10;
                    Platform.runLater(() -> imageView3.setX(flagX3));
                    if(flagX3>415){
                        run.set(false);
                        Platform.runLater(() -> {
                            alert.setContentText("Car Three Wins!");
                            alert.show();
                        });

                    }
                    Thread.sleep(50);

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        ).start();

    }
    //this method is override because of JavaFx
    @Override
    public void start(Stage primaryStage) throws Exception {
        //set primary stage title
        primaryStage.setTitle("Richmond Raceway");

        //creating and placing the start button
        StartButton = new Button();
        StartButton.setText("Start");
        StartButton.setLayoutX(125);
        StartButton.setLayoutY(15);

        //what happens if you click the button
        StartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if run is true we call the move methods
                if(run.get()==true){
                    moveCar1();
                    moveCar2();
                    moveCar3();
                }
                //if run is false, we set it true and move the cars
                if(run.get()==false){
                    run.set(true);
                    moveCar1();
                    moveCar2();
                    moveCar3();
                }

            }
        });
        //creating and placingt the pause button
        PauseButton = new Button();
        PauseButton.setText("Pause");
        PauseButton.setLayoutX(225);
        PauseButton.setLayoutY(15);
        //what happens if you click pause
        PauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //we set the run bool to false to stop the motion
                run.set(false);

            }
        });

        //creating and placing the reset button
        ResetButton = new Button();
        ResetButton.setText("Reset");
        ResetButton.setLayoutX(325);
        ResetButton.setLayoutY(15);
        //what happens if you click
        ResetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //we set the run to false to stop motion
                //then we reset the X positions and move the
                //images back to starting position
                run.set(false);
                flagX1=20;
                flagX2=20;
                flagX3=20;
                imageView.setX(flagX1);
                imageView2.setX(flagX2);
                imageView3.setX(flagX3);
                //run.set(true);
            }
        });

        //Creating lines to simulate a track
        Line track1 = new Line(60,80,440,80);
        track1.setStroke(Color.LIGHTGRAY);
        track1.setStrokeWidth(15);

        Line track2 = new Line(60,120,440,120);
        track2.setStroke(Color.LIGHTGRAY);
        track2.setStrokeWidth(15);

        Line track3 = new Line(60,160,440,160);
        track3.setStroke(Color.LIGHTGRAY);
        track3.setStrokeWidth(15);


        //creating 3 image objects to show the 3 cars
        Image lilCar1 = new Image("file:sportive-car.png");
        imageView=new ImageView(lilCar1);
        imageView.setX(flagX1);
        imageView.setY(62);


        Image lilCar2 = new Image("file:sportive-car.png");
        imageView2=new ImageView(lilCar2);
        imageView2.setX(flagX2);
        imageView2.setY(102);


        Image lilCar3 = new Image("file:sportive-car.png");
        imageView3=new ImageView(lilCar3);
        imageView3.setX(flagX3);
        imageView3.setY(142);



        //making the Pane object
        Pane layout = new Pane();
        //placing the objects on the pane layout
        layout.getChildren().addAll(StartButton,PauseButton,ResetButton,track1,track2,track3,imageView,imageView2,imageView3);

        //Creating a scene--Literally! :D
        Scene scene = new Scene(layout,500,200);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}