package com.example.demo;

import com.example.demo.entities.Entity;
import com.example.demo.entities.animated.Character.Bomber;
import com.example.demo.entities.animated.Character.Enemy.Balloon;
import com.example.demo.entities.animated.Character.Enemy.Kondoria;
import com.example.demo.entities.animated.Character.Enemy.Minvo;
import com.example.demo.entities.animated.Character.Enemy.Oneal;
import com.example.demo.entities.freeze.tile.Grass;
import com.example.demo.entities.freeze.tile.items.Portal;
import com.example.demo.entities.freeze.tile.Wall;
import com.example.demo.entities.animated.destroyable.Brick;
import com.example.demo.entities.freeze.LayerEntity;
import com.example.demo.entities.freeze.tile.items.BoomItem;
import com.example.demo.entities.freeze.tile.items.FlameItem;
import com.example.demo.entities.freeze.tile.items.SpeedItem;
import com.example.demo.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuTest extends Game {
    private Stage stage;

    protected boolean bool= true;

    protected boolean bool1= true;
    protected int count=1;

    protected javafx.event.ActionEvent test1;
    public void Test(javafx.event.ActionEvent test) throws FileNotFoundException {
//        nextLevel();
        stage = (Stage) ((Node) test.getSource()).getScene().getWindow();
        canvas = new Canvas(Sprite.SCALED_SIZE * Game.WIDTH, Sprite.SCALED_SIZE * Game.HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);



//        Sound.background.setCycleCount(MediaPlayer.INDEFINITE);
//        Sound.background.play();
//        System.out.println(new File("Bomberman/src/main/resources/sound/4 - Track 4.mp3").exists());
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        createMap();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(Game.getBomber() == null && bool == true){
                    bool = false;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameOver.fxml"));
                    Parent root2 = null;
                    try {
                        root2 = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(root2);
                    stage.setScene(scene);
                    stage.show();
                    this.stop();
                }
                if(count==2 && bool1==true) {
                    if (entities.size()==1 && entities.get(0) instanceof Bomber) {
                        bool1 = false;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/YouWin.fxml"));
                        Parent root3 = null;
                        try {
                            root3 = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Scene scene = new Scene(root3);
                        stage.setScene(scene);
                        stage.show();
                        this.stop();
                    }
                }
                if (!Portal.isStepOn) {
                    Bomber.getControl(scene);
                    render();
                    update();
                    for (int i = 0; i < entities.size(); i++) {
                        if (entities.get(i).isRemoved()) entities.remove(i);
                    }
                    for (int i = 0; i < extraEntity.size(); i++) {
                        entities.add(extraEntity.get(i));
                        extraEntity.remove(i);
                    }
                    ;} else{
                    int i = count+1;
                    if(i<=2){
                        count++;
                        try {
                            nextLevel();
                            createMap();
//                            System.out.println(count);
//                            this.stop();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                }
        };
        timer.start();
    }
//    public void Over(javafx.event.ActionEvent over) throws IOException {
//        stage = (Stage) ((Node) over.getSource()).getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    public void nextLevel(){
        stillObjects.clear();
        entities.clear();
        Portal.isStepOn=false;
    }
    public void Control(javafx.event.ActionEvent controlEvent) throws IOException{
            stage = (Stage) ((Node) controlEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/getControl.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    public void Exit(javafx.event.ActionEvent actionEvent){
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void createMap() throws FileNotFoundException {
        List<String> str = new ArrayList<>();

        try {
            FileReader fr = new FileReader("Bomberman/src/main/resources/levels/Level"+count+".txt");
            BufferedReader br = new BufferedReader(fr);

            String line = "";

            while (true) {
                line = br.readLine();
                if (line == null) break;
                str.add(line);
            }
            Entity obj;
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    switch (str.get(i + 1).charAt(j)) {
                        case 'p':
                            obj = new Grass(j, i, Sprite.grass);
                            stillObjects.add(obj);
                            obj= new Bomber(j,i,Sprite.player_right);
                            entities.add(obj);
                            break;
                        case '#':
                            obj = new Wall(j, i, Sprite.wall);
                            stillObjects.add(obj);
                            break;
                        case ' ':
                            obj = new Grass(j, i, Sprite.grass);
                            stillObjects.add(obj);
                            break;
                        case '*':
                            obj = new LayerEntity(j,i, new Brick(j, i, Sprite.brick), new Grass(j, i, Sprite.grass));
                            stillObjects.add(obj);
                            break;
                        case 'b' :
                            obj = new LayerEntity(j,i, new Brick(j, i, Sprite.brick), new BoomItem(j, i, Sprite.powerup_bombs), new Grass(j, i, Sprite.grass));
                            stillObjects.add(obj);
                            break;
                        case 'f' :
                            obj = new LayerEntity(j,i, new Brick(j, i, Sprite.brick), new FlameItem(j, i, Sprite.powerup_flames), new Grass(j, i, Sprite.grass));
                            stillObjects.add(obj);
                            break;
                        case 's' :
                            obj = new LayerEntity(j,i, new Brick(j, i, Sprite.brick), new SpeedItem(j, i, Sprite.powerup_speed), new Grass(j, i, Sprite.grass));
                            stillObjects.add(obj);
                            break;
                        case 'x' :
                            obj = new LayerEntity(j,i,new Brick(j,i,Sprite.brick), new Portal(j,i,Sprite.portal), new Grass(j,i,Sprite.grass));
                            stillObjects.add(obj);
                            break;
                        case '1':
                            obj = new Grass(j, i, Sprite.grass);
                            stillObjects.add(obj);
                            obj = new Balloon(j ,i, Sprite.balloom_right1,1,100);
                            entities.add(obj);
                            break;
                        case '2':
                            obj = new Grass(j, i, Sprite.grass);
                            stillObjects.add(obj);
                            obj = new Oneal(j ,i, Sprite.oneal_left1,1,100);
                            entities.add(obj);
                            break;
                        case '3':
                            obj = new Grass(j, i, Sprite.grass);
                            stillObjects.add(obj);
                            obj = new Minvo(j ,i, Sprite.minvo_dead,2,100);
                            entities.add(obj);
                            break;
                        case '4':
                            obj = new Grass(j, i, Sprite.grass);
                            stillObjects.add(obj);
                            obj = new Kondoria(j ,i, Sprite.kondoria_dead,1,100);
                            entities.add(obj);
                            break;
                    }
                }
            }
        } catch (Exception e){
            System.out.println("Can't load file");
        }
    }
}
