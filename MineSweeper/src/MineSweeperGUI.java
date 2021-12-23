import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        
        btn.setMaxHeight(50);
        btn.setMinHeight(50);
        for (int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++){
                btn = new Button();
                btn.setPrefSize(30, 30);
                grid.add(btn,r,c);
            }
        }
        
        grid.setGridLinesVisible(false);
                
        btn.setOnAction(event ->
        {
            
        });
        
        primaryStage.setTitle("Mine Sweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String args[]){
        launch(args);
     }
}
