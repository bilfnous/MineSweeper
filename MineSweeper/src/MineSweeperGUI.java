import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MineSweeperGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
    
        GridPane grid = new GridPane();
        Button btn = new Button();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(1);
        grid.setVgap(1);
        
        Scene scene = new Scene(grid, 290, 350);
        
        Label mines = new Label("00");

        Label main_clock_lb = new Label();
        long startStopWatch = System.currentTimeMillis();
        Thread timerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final double time = System.currentTimeMillis();
                double timeE = (time - startStopWatch);
                int second = (int)TimeUnit.MILLISECONDS.toSeconds((long) timeE);
                int seconds = second % 60;
                int minute = second / 60;
                Platform.runLater(() -> {
                    main_clock_lb.setText(String.valueOf(minute + ":" + seconds));
                    grid.add(main_clock_lb, 8, 0);
                });
            }
        });   timerThread.start();


        Image smilePNG = new Image("pics/smile.png");
        ImageView smileIV = new ImageView(smilePNG);
        smileIV.setFitHeight(30);
        smileIV.setFitWidth(30);
        Image coolPNG = new Image("pics/cool.png");
        ImageView coolIV = new ImageView(coolPNG);
        coolIV.setFitHeight(30);
        coolIV.setFitWidth(30);
        Image deadPNG = new Image("pics/dead.png");
        Image minePNG = new Image("pics/mine.png");
        ImageView mineIV = new ImageView(minePNG);
        mineIV.setFitHeight(25);
        mineIV.setFitWidth(25);
        Image explosionPNG = new Image("pics/explosion.png");

        Image flagPNG = new Image("pics/flag.png");

        grid.add(mines, 2, 0);
        grid.add(smileIV, 5, 0);

        btn.setMaxHeight(50);
        btn.setMinHeight(50);


        int [] mineValue = new int[81];
        int [] flaggedButtons = new int[81];

        int btnID = 0;
        MineSweeper mine = new MineSweeper();
        mine.toString();
        for (int r = 1; r < 10; r++){
            for (int c = 1; c < 10; c++){
                mineValue[btnID] = mine.getBlock(r-1, c-1);
                btn = new Button();
                btn.setId(String.valueOf(btnID));
                btn.setOnMouseClicked(event -> {
                    Node node = (Node) event.getTarget();
                    int x = (int) node.getLayoutX() / 30 + 1;
                    int y = ((int) node.getLayoutY() / 30);
                    if (y == 10) { y = 9; }
                    if(event.getButton() == MouseButton.SECONDARY){
                        int marked = mine.remainingFlags();
                        ImageView flagIV = new ImageView(flagPNG);
                        flagIV.setFitHeight(25);
                        flagIV.setFitWidth(25);
                        
                        flagIV.setOnMouseClicked( flagEvent -> {
                            if(flagEvent.getButton() == MouseButton.SECONDARY){
                                grid.getChildren().remove(flagIV);
                                int marked1 = mine.incFlags();
                                flaggedButtons[Integer.valueOf(node.getId())] = 0;
                                mines.setText(Integer.toString(marked1 - 1));
                            }
                        });

                        if( ( marked  > 0)  && (flaggedButtons[Integer.valueOf(node.getId())] == 0) ){
                            mine.decFlags();
                            flaggedButtons[Integer.valueOf(node.getId())] = 1;
                            mines.setText(Integer.toString(marked - 1));
                            grid.add(flagIV, x, y);
                        }
                    }
                    else if(event.getButton() == MouseButton.PRIMARY) {
                    
                        if(flaggedButtons[Integer.valueOf(node.getId())] == 1){
                            // If a button is flagged, do nothing
                        } 
                        else {
                            if(mineValue[Integer.valueOf(node.getId())] == -1){
                                // check for mine
                                ImageView deadIV = new ImageView(deadPNG);
                                deadIV.setFitHeight(30);
                                deadIV.setFitWidth(30);
                                grid.getChildren().remove(smileIV);
                                grid.add(deadIV, 5, 0);
                                ImageView explosionIV = new ImageView(explosionPNG);
                                explosionIV.setFitHeight(25);
                                explosionIV.setFitWidth(25);
                                grid.add(explosionIV, x, y);
                                
                                //timerThread.stop();
                                
                            }
                            else {
                                // no mine, clear squares, show numbers to indicate mines
                                node.setStyle("-fx-background-color:808080;");
                                if(mineValue[Integer.valueOf(node.getId())] >= 0){
                                    Label minesIndicator = new Label(String.valueOf(  mineValue[Integer.valueOf(node.getId())] ) );
                                }                                
                            }
                        }

                    }
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
