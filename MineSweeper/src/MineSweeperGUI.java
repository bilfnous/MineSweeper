import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.text.DecimalFormat;

import javax.sound.sampled.SourceDataLine;

public class MineSweeperGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    
        GridPane grid = new GridPane();
        Button btn = new Button();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(1);
        grid.setVgap(1);
        //grid.setPadding(new Insets());
        
        Scene scene = new Scene(grid, 290, 350);
        
        Label mines = new Label("010");

        // Label main_clock_lb = new Label();
        // long startStopWatch = System.currentTimeMillis();
        // Thread timerThread = new Thread(() -> {
        //     while (true) {
        //         try {
        //             Thread.sleep(1000); // 1 second
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //         final double time = System.currentTimeMillis();
        //         double timeE = (time - startStopWatch) / 10000;
        //         Platform.runLater(() -> {
        //             main_clock_lb.setText(new DecimalFormat("##.##").format(timeE));
        //             grid.add(main_clock_lb, 8, 0);
        //         });
        //     }
        // });   timerThread.start();


        Image smilePNG = new Image("pics/smile.png");
        ImageView smileIV = new ImageView(smilePNG);
        smileIV.setFitHeight(30);
        smileIV.setFitWidth(30);
        Image coolPNG = new Image("pics/cool.png");
        ImageView coolIV = new ImageView(coolPNG);
        coolIV.setFitHeight(30);
        coolIV.setFitWidth(30);
        Image deadPNG = new Image("pics/dead.png");
        ImageView deadIV = new ImageView(deadPNG);
        deadIV.setFitHeight(30);
        deadIV.setFitWidth(30);
        Image minePNG = new Image("pics/mine.png");
        ImageView mineIV = new ImageView(minePNG);
        mineIV.setFitHeight(25);
        mineIV.setFitWidth(25);
        Image explosionPNG = new Image("pics/explosion.png");
        ImageView explosionIV = new ImageView(explosionPNG);
        explosionIV.setFitHeight(25);
        explosionIV.setFitWidth(25);
        Image flagPNG = new Image("pics/flag.png");
        ImageView flagIV = new ImageView(flagPNG);
        flagIV.setFitHeight(25);
        flagIV.setFitWidth(25);

        grid.add(mines, 2, 0);
        grid.add(smileIV, 5, 0);

        btn.setMaxHeight(50);
        btn.setMinHeight(50);

        int btnID = 0;
        for (int r = 1; r < 10; r++){
            for (int c = 1; c < 10; c++){
                btn = new Button();
                btn.setId(String.valueOf(btnID));
                btn.setOnAction(event -> {
                    Node node = (Node) event.getTarget();
                    System.out.println(  node.getId() ); 
                });
                btnID++;
                btn.setPrefSize(30, 30);
                grid.add(btn,r,c);
            }
        }
        
        grid.setGridLinesVisible(false);
               
        primaryStage.setTitle("Mine Sweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String args[]){
        launch(args);
     }
}
