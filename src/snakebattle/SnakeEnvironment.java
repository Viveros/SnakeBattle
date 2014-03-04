/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakebattle;

import audio.AudioPlayer;
import environment.Environment;
import static environment.Environment.DEFAULT_TIMER_DELAY_MS;
import environment.Grid;
import image.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;

/**
 *
 * @author Wyatt
 */
class SnakeEnvironment extends Environment {

    private GameState gameState = GameState.BACKGROUNDMUSIC;
    private int score = 0;
    private Grid grid;
    private Snake snake;
    private ArrayList<Point> apples;
    private ArrayList<Point> bomb;
    private ArrayList<Snake> enemySnakes;
    private int moveDelay = 1;
    private int moveCounter = moveDelay;
    private int growDelay = 100;
    private int growCounter = growDelay;
    private int myGrowDelay = 100;
    private int myGrowCounter = myGrowDelay;
    private Image mainSnakeHead;
    private ArrayList<Point> mainSnakehead;
    private int deathcount = 0;

    private Point getRandomGridLocation() {
        return new Point((int) (Math.random() * this.grid.getColumns()), (int) (Math.random() * this.grid.getRows()));

    }

//    private boolean areAllSnakesDead(){
//        for (Snake enemy : enemySnakes) {
//            if (enemy.isAlive()) {
//                return false;
//            }
//        }
//        
//        return true;
//    }
//
//    public int getLiveEnemySnakes(){
//        int count = 0;
//        
//        for (Snake enemy : enemySnakes) {
//            if (enemy.isAlive()) {
//                count++ ;
//            }
//        }
//        return count;
//    }
    
    
    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/neon_background_6.jpg"));






        this.setGrid(new Grid());
        this.getGrid().setColor(Color.BLACK);
        this.getGrid().setColumns(94);
        this.getGrid().setRows(46);
        this.getGrid().setCellHeight(20);
        this.getGrid().setCellWidth(20);
        this.getGrid().setPosition(new Point(20, 60));

        this.mainSnakehead = new ArrayList<Point>();

        //<editor-fold defaultstate="collapsed" desc="apples">
        this.apples = new ArrayList<Point>();
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());

        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="bombs">
        this.bomb = new ArrayList<Point>();
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        //</editor-fold>


        this.enemySnakes = new ArrayList<Snake>();


        //<editor-fold defaultstate="collapsed" desc="mySnake4">
        Snake mySnake4 = new Snake();
        mySnake4.getBody().add(new Point(84, 36));
        mySnake4.getBody().add(new Point(84, 35));
        mySnake4.getBody().add(new Point(84, 34));
        mySnake4.getBody().add(new Point(84, 33));
        mySnake4.getBody().add(new Point(83, 33));
        mySnake4.getBody().add(new Point(83, 34));
        mySnake4.getBody().add(new Point(83, 35));
        mySnake4.getBody().add(new Point(83, 36));
        mySnake4.setDirection(Direction.DOWN);
        mySnake4.setColor(Color.GREEN);
        mySnake4.setRandomDirectionChange(true);
        if (mySnake4.getHead().equals(apples)) {
            mySnake4.grow();
        }
        if (mySnake4.getHead().equals(bomb)) {
            mySnake4.shrink();
        }
        this.enemySnakes.add(mySnake4);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake2">
        Snake mySnake2 = new Snake();
        mySnake2.getBody().add(new Point(84, 10));
        mySnake2.getBody().add(new Point(84, 9));
        mySnake2.getBody().add(new Point(84, 8));
        mySnake2.getBody().add(new Point(84, 7));
        mySnake2.getBody().add(new Point(84, 10));
        mySnake2.getBody().add(new Point(84, 9));
        mySnake2.getBody().add(new Point(84, 8));
        mySnake2.getBody().add(new Point(84, 7));
        mySnake2.setDirection(Direction.LEFT);
        mySnake2.setColor(Color.MAGENTA);
        mySnake2.setRandomDirectionChange(true);
        if (mySnake2.getHead().equals(apples)) {
            mySnake2.grow();
        }
        if (mySnake2.getHead().equals(bomb)) {
            mySnake2.shrink();
        }
        this.enemySnakes.add(mySnake2);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake3">
        Snake mySnake3 = new Snake();
        mySnake3.getBody().add(new Point(10, 36));
        mySnake3.getBody().add(new Point(10, 35));
        mySnake3.getBody().add(new Point(10, 34));
        mySnake3.getBody().add(new Point(10, 33));
        mySnake3.getBody().add(new Point(10, 36));
        mySnake3.getBody().add(new Point(10, 35));
        mySnake3.getBody().add(new Point(10, 34));
        mySnake3.getBody().add(new Point(10, 33));
        mySnake3.setDirection(Direction.UP);
        mySnake3.setColor(Color.CYAN);
        mySnake3.setRandomDirectionChange(true);
        if (mySnake3.getHead().equals(apples)) {
            mySnake3.grow();
        }
        if (mySnake3.getHead().equals(bomb)) {
            mySnake3.shrink();
        }
        this.enemySnakes.add(mySnake3);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake5">
        Snake mySnake5 = new Snake();
        mySnake5.getBody().add(new Point(42, 10));
        mySnake5.getBody().add(new Point(42, 9));
        mySnake5.getBody().add(new Point(42, 8));
        mySnake5.getBody().add(new Point(42, 7));
        mySnake5.getBody().add(new Point(42, 10));
        mySnake5.getBody().add(new Point(42, 9));
        mySnake5.getBody().add(new Point(42, 8));
        mySnake5.getBody().add(new Point(42, 7));
        mySnake5.setDirection(Direction.LEFT);
        mySnake5.setColor(Color.YELLOW);
        mySnake5.setRandomDirectionChange(true);
        if (mySnake5.getHead().equals(apples)) {
            mySnake5.grow();
        }
        if (mySnake5.getHead().equals(bomb)) {
            mySnake5.shrink();
        }
        this.enemySnakes.add(mySnake5);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake6">
        Snake mySnake6 = new Snake();
        mySnake6.getBody().add(new Point(42, 36));
        mySnake6.getBody().add(new Point(42, 35));
        mySnake6.getBody().add(new Point(42, 34));
        mySnake6.getBody().add(new Point(42, 33));
        mySnake6.getBody().add(new Point(42, 33));
        mySnake6.getBody().add(new Point(42, 34));
        mySnake6.getBody().add(new Point(42, 35));
        mySnake6.getBody().add(new Point(42, 36));
        mySnake6.setDirection(Direction.LEFT);
        mySnake6.setColor(Color.RED);
        mySnake6.setRandomDirectionChange(true);
        if (mySnake6.getHead().equals(apples)) {
            mySnake6.grow();
        }
        if (mySnake6.getHead().equals(bomb)) {
            mySnake6.shrink();
        }
        this.enemySnakes.add(mySnake6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Snake">
        this.snake = new Snake();
        this.snake.setDirection(Direction.RIGHT);
        this.snake.getBody().add(new Point(10, 10));
        this.snake.getBody().add(new Point(10, 9));
        this.snake.getBody().add(new Point(10, 8));
        this.snake.getBody().add(new Point(10, 7));
        this.snake.getBody().add(new Point(9, 7));
        this.snake.getBody().add(new Point(9, 8));
        this.snake.getBody().add(new Point(9, 9));
        this.snake.getBody().add(new Point(9, 10));
        this.snake.setColor(Color.ORANGE);
        if (snake.getBody().size() == 0) {
            this.snake = new Snake();
        }

        //</editor-fold>


    }

    @Override
    public void timerTaskHandler() {
        if (this.gameState == GameState.RUNNING) {

            if (snake != null) {
                if (this.myGrowCounter > 0) {
                    this.myGrowCounter--;
                } else {
                    snake.grow();
                    this.myGrowCounter = this.myGrowDelay;
                }
                if (this.getMoveCounter() > 0) {
                    this.setMoveCounter(this.getMoveCounter() - 1);
                } else {
                    snake.move();
                    this.setMoveCounter(this.getMoveDelay());
                    for (int i = 0; i < this.enemySnakes.size(); i++) {
                        this.enemySnakes.get(i).move();

                        if (Snake.intersects(this.snake.getHead(), this.enemySnakes.get(i).getBody())) {
                            System.out.println("You're hitting a snake!!");
                            this.snake.shrink();
                            gameState = GameState.CRASH;

                            if (this.snake.getBody().size() <= 1) {
                                this.snake.respawn(new Point(-2000, -1000), new Point(-1000, -1000));
                                this.gameState = GameState.ENDED;
                            }
                        }

                        if (Snake.intersects(this.enemySnakes.get(i).getHead(), this.snake.getBody())) {
                            System.out.println("Another snake is hitting you!!");
                            this.enemySnakes.get(i).shrink();
                            gameState = GameState.CRASH;

                            if (this.enemySnakes.get(i).getBody().size() <= 1) {
                                this.enemySnakes.get(i).respawn(new Point(-1000, -1000), new Point(-1000, -1000));
                                gameState = GameState.KILLED;
                            }
                        }

                    }
                }
            } else if (this.gameState == GameState.PAUSED) {
                AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Casino_Royale.wav"));
            } else if (this.gameState == GameState.START) {
            }

            for (int i = 0; i < apples.size(); i++) {
                if (snake.getHead().equals(apples.get(i))) {
                    //add to score and move apple
                    gameState = GameState.EATAPPLE;
                    this.snake.grow();
                    this.score += 50;
                    this.apples.get(i).x = (int) (Math.random() * this.grid.getColumns());
                    this.apples.get(i).y = (int) (Math.random() * this.grid.getRows());
                }
            }


            for (int i = 0; i < apples.size(); i++) {
                if (enemySnakes.equals(apples.get(i))) {
                    this.apples.get(i).x = (int) (Math.random() * this.grid.getColumns());
                    this.apples.get(i).y = (int) (Math.random() * this.grid.getRows());
                    this.enemySnakes.get(i).grow();
                }
            }
            for (int i = 0; i < bomb.size(); i++) {
                if (snake.getHead().equals(bomb.get(i))) {
                    gameState = GameState.HITBOMB;
                    this.snake.shrink();
                    this.score -= 100;
                    this.bomb.get(i).x = (int) (Math.random() * this.grid.getColumns());
                    this.bomb.get(i).y = (int) (Math.random() * this.grid.getRows());
                }
            }
            for (int i = 0; i < bomb.size(); i++) {
                if (enemySnakes.equals(bomb.get(i))) {
                    this.bomb.get(i).x = (int) (Math.random() * this.grid.getColumns());
                    this.bomb.get(i).y = (int) (Math.random() * this.grid.getRows());
                    this.enemySnakes.get(i).shrink();
                }
            }

            if (snake.getHead().x == grid.getColumns() - 1) {
                snake.shrink();
                snake.setDirection(Direction.LEFT);
                AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Bounce.wav"));
            }
            if (snake.getHead().y == grid.getRows() - 1) {
                snake.shrink();
                snake.setDirection(Direction.UP);
                AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Bounce.wav"));
            }
            if (snake.getHead().x == 0) {
                snake.shrink();
                snake.setDirection(Direction.RIGHT);
                AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Bounce.wav"));
            }
            if (snake.getHead().y == 0) {
                snake.shrink();
                snake.setDirection(Direction.DOWN);
                AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Bounce.wav"));
            }
        }

        if (snake.getBody().isEmpty()) {
            this.gameState = GameState.ENDED;
        }

        if (deathcount == 5) {
            gameState = GameState.LEVELTWO;
            deathcount = 6;
        }
        
        if (deathcount == 11) {
            gameState = GameState.LEVELTHREE;
            deathcount = 12;
        }
        
        if (deathcount == 17) {
            gameState = GameState.LEVELFOUR;
            deathcount = 18;
        }
        
        if (deathcount == 23) {
            gameState = GameState.LEVELFIVE;
            deathcount = 24;
        }
        
        if (this.growCounter > 0) {
            this.growCounter--;
        } else {
            for (int i = 0; i < this.enemySnakes.size(); i++) {

                this.enemySnakes.get(i).grow();
            }

            this.growCounter = this.growDelay;
        }
        if (this.gameState == GameState.PAUSEMUSIC) {
            AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Casino_Royale.wav"));
            gameState = GameState.PAUSED;
        }
        
        //<editor-fold defaultstate="collapsed" desc="Level Two">
        if (gameState == GameState.LEVELTWO) {
        this.setBackground(ResourceTools.loadImageFromResource("resources/neon_background_7.jpg"));
        
         //<editor-fold defaultstate="collapsed" desc="mySnake4">
        Snake mySnake4 = new Snake();
        mySnake4.getBody().add(new Point(84, 36));
        mySnake4.getBody().add(new Point(84, 35));
        mySnake4.getBody().add(new Point(84, 34));
        mySnake4.getBody().add(new Point(84, 33));
        mySnake4.getBody().add(new Point(83, 33));
        mySnake4.getBody().add(new Point(83, 34));
        mySnake4.getBody().add(new Point(83, 35));
        mySnake4.getBody().add(new Point(83, 36));
        mySnake4.setDirection(Direction.DOWN);
        mySnake4.setColor(Color.GREEN);
        mySnake4.setRandomDirectionChange(true);
        if (mySnake4.getHead().equals(apples)) {
            mySnake4.grow();
        }
        if (mySnake4.getHead().equals(bomb)) {
            mySnake4.shrink();
        }
        this.enemySnakes.add(mySnake4);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake2">
        Snake mySnake2 = new Snake();
        mySnake2.getBody().add(new Point(84, 10));
        mySnake2.getBody().add(new Point(84, 9));
        mySnake2.getBody().add(new Point(84, 8));
        mySnake2.getBody().add(new Point(84, 7));
        mySnake2.getBody().add(new Point(84, 10));
        mySnake2.getBody().add(new Point(84, 9));
        mySnake2.getBody().add(new Point(84, 8));
        mySnake2.getBody().add(new Point(84, 7));
        mySnake2.setDirection(Direction.LEFT);
        mySnake2.setColor(Color.MAGENTA);
        mySnake2.setRandomDirectionChange(true);
        if (mySnake2.getHead().equals(apples)) {
            mySnake2.grow();
        }
        if (mySnake2.getHead().equals(bomb)) {
            mySnake2.shrink();
        }
        this.enemySnakes.add(mySnake2);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake3">
        Snake mySnake3 = new Snake();
        mySnake3.getBody().add(new Point(10, 36));
        mySnake3.getBody().add(new Point(10, 35));
        mySnake3.getBody().add(new Point(10, 34));
        mySnake3.getBody().add(new Point(10, 33));
        mySnake3.getBody().add(new Point(10, 36));
        mySnake3.getBody().add(new Point(10, 35));
        mySnake3.getBody().add(new Point(10, 34));
        mySnake3.getBody().add(new Point(10, 33));
        mySnake3.setDirection(Direction.UP);
        mySnake3.setColor(Color.CYAN);
        mySnake3.setRandomDirectionChange(true);
        if (mySnake3.getHead().equals(apples)) {
            mySnake3.grow();
        }
        if (mySnake3.getHead().equals(bomb)) {
            mySnake3.shrink();
        }
        this.enemySnakes.add(mySnake3);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake5">
        Snake mySnake5 = new Snake();
        mySnake5.getBody().add(new Point(42, 10));
        mySnake5.getBody().add(new Point(42, 9));
        mySnake5.getBody().add(new Point(42, 8));
        mySnake5.getBody().add(new Point(42, 7));
        mySnake5.getBody().add(new Point(42, 10));
        mySnake5.getBody().add(new Point(42, 9));
        mySnake5.getBody().add(new Point(42, 8));
        mySnake5.getBody().add(new Point(42, 7));
        mySnake5.setDirection(Direction.LEFT);
        mySnake5.setColor(Color.YELLOW);
        mySnake5.setRandomDirectionChange(true);
        if (mySnake5.getHead().equals(apples)) {
            mySnake5.grow();
        }
        if (mySnake5.getHead().equals(bomb)) {
            mySnake5.shrink();
        }
        this.enemySnakes.add(mySnake5);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake6">
        Snake mySnake6 = new Snake();
        mySnake6.getBody().add(new Point(42, 36));
        mySnake6.getBody().add(new Point(42, 35));
        mySnake6.getBody().add(new Point(42, 34));
        mySnake6.getBody().add(new Point(42, 33));
        mySnake6.getBody().add(new Point(42, 33));
        mySnake6.getBody().add(new Point(42, 34));
        mySnake6.getBody().add(new Point(42, 35));
        mySnake6.getBody().add(new Point(42, 36));
        mySnake6.setDirection(Direction.LEFT);
        mySnake6.setColor(Color.RED);
        mySnake6.setRandomDirectionChange(true);
        if (mySnake6.getHead().equals(apples)) {
            mySnake6.grow();
        }
        if (mySnake6.getHead().equals(bomb)) {
            mySnake6.shrink();
        }
        this.enemySnakes.add(mySnake6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Snake">
        this.snake = new Snake();
        this.snake.setDirection(Direction.RIGHT);
        this.snake.getBody().add(new Point(10, 10));
        this.snake.getBody().add(new Point(10, 9));
        this.snake.getBody().add(new Point(10, 8));
        this.snake.getBody().add(new Point(10, 7));
        this.snake.getBody().add(new Point(9, 7));
        this.snake.getBody().add(new Point(9, 8));
        this.snake.getBody().add(new Point(9, 9));
        this.snake.getBody().add(new Point(9, 10));
        this.snake.setColor(Color.ORANGE);
        if (snake.getBody().size() == 0) {
            this.snake = new Snake();
        }

        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="apples">
        this.apples.removeAll(apples);
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        
         //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Bomb">
        this.bomb.removeAll(bomb);
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        gameState = GameState.RUNNING;
        
         //</editor-fold>
        
        score += 500;
        }
         //</editor-fold>
        
        
        //<editor-fold defaultstate="collapsed" desc="Level Three">
        if (gameState == GameState.LEVELTHREE) {
        this.setBackground(ResourceTools.loadImageFromResource("resources/neon_background_5.jpg"));
        
         //<editor-fold defaultstate="collapsed" desc="mySnake4">
        Snake mySnake4 = new Snake();
        mySnake4.getBody().add(new Point(84, 36));
        mySnake4.getBody().add(new Point(84, 35));
        mySnake4.getBody().add(new Point(84, 34));
        mySnake4.getBody().add(new Point(84, 33));
        mySnake4.getBody().add(new Point(83, 33));
        mySnake4.getBody().add(new Point(83, 34));
        mySnake4.getBody().add(new Point(83, 35));
        mySnake4.getBody().add(new Point(83, 36));
        mySnake4.setDirection(Direction.DOWN);
        mySnake4.setColor(Color.GREEN);
        mySnake4.setRandomDirectionChange(true);
        if (mySnake4.getHead().equals(apples)) {
            mySnake4.grow();
        }
        if (mySnake4.getHead().equals(bomb)) {
            mySnake4.shrink();
        }
        this.enemySnakes.add(mySnake4);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake2">
        Snake mySnake2 = new Snake();
        mySnake2.getBody().add(new Point(84, 10));
        mySnake2.getBody().add(new Point(84, 9));
        mySnake2.getBody().add(new Point(84, 8));
        mySnake2.getBody().add(new Point(84, 7));
        mySnake2.getBody().add(new Point(84, 10));
        mySnake2.getBody().add(new Point(84, 9));
        mySnake2.getBody().add(new Point(84, 8));
        mySnake2.getBody().add(new Point(84, 7));
        mySnake2.setDirection(Direction.LEFT);
        mySnake2.setColor(Color.MAGENTA);
        mySnake2.setRandomDirectionChange(true);
        if (mySnake2.getHead().equals(apples)) {
            mySnake2.grow();
        }
        if (mySnake2.getHead().equals(bomb)) {
            mySnake2.shrink();
        }
        this.enemySnakes.add(mySnake2);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake3">
        Snake mySnake3 = new Snake();
        mySnake3.getBody().add(new Point(10, 36));
        mySnake3.getBody().add(new Point(10, 35));
        mySnake3.getBody().add(new Point(10, 34));
        mySnake3.getBody().add(new Point(10, 33));
        mySnake3.getBody().add(new Point(10, 36));
        mySnake3.getBody().add(new Point(10, 35));
        mySnake3.getBody().add(new Point(10, 34));
        mySnake3.getBody().add(new Point(10, 33));
        mySnake3.setDirection(Direction.UP);
        mySnake3.setColor(Color.CYAN);
        mySnake3.setRandomDirectionChange(true);
        if (mySnake3.getHead().equals(apples)) {
            mySnake3.grow();
        }
        if (mySnake3.getHead().equals(bomb)) {
            mySnake3.shrink();
        }
        this.enemySnakes.add(mySnake3);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake5">
        Snake mySnake5 = new Snake();
        mySnake5.getBody().add(new Point(42, 10));
        mySnake5.getBody().add(new Point(42, 9));
        mySnake5.getBody().add(new Point(42, 8));
        mySnake5.getBody().add(new Point(42, 7));
        mySnake5.getBody().add(new Point(42, 10));
        mySnake5.getBody().add(new Point(42, 9));
        mySnake5.getBody().add(new Point(42, 8));
        mySnake5.getBody().add(new Point(42, 7));
        mySnake5.setDirection(Direction.LEFT);
        mySnake5.setColor(Color.YELLOW);
        mySnake5.setRandomDirectionChange(true);
        if (mySnake5.getHead().equals(apples)) {
            mySnake5.grow();
        }
        if (mySnake5.getHead().equals(bomb)) {
            mySnake5.shrink();
        }
        this.enemySnakes.add(mySnake5);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake6">
        Snake mySnake6 = new Snake();
        mySnake6.getBody().add(new Point(42, 36));
        mySnake6.getBody().add(new Point(42, 35));
        mySnake6.getBody().add(new Point(42, 34));
        mySnake6.getBody().add(new Point(42, 33));
        mySnake6.getBody().add(new Point(42, 33));
        mySnake6.getBody().add(new Point(42, 34));
        mySnake6.getBody().add(new Point(42, 35));
        mySnake6.getBody().add(new Point(42, 36));
        mySnake6.setDirection(Direction.LEFT);
        mySnake6.setColor(Color.RED);
        mySnake6.setRandomDirectionChange(true);
        if (mySnake6.getHead().equals(apples)) {
            mySnake6.grow();
        }
        if (mySnake6.getHead().equals(bomb)) {
            mySnake6.shrink();
        }
        this.enemySnakes.add(mySnake6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Snake">
        this.snake = new Snake();
        this.snake.setDirection(Direction.RIGHT);
        this.snake.getBody().add(new Point(10, 10));
        this.snake.getBody().add(new Point(10, 9));
        this.snake.getBody().add(new Point(10, 8));
        this.snake.getBody().add(new Point(10, 7));
        this.snake.getBody().add(new Point(9, 7));
        this.snake.getBody().add(new Point(9, 8));
        this.snake.getBody().add(new Point(9, 9));
        this.snake.getBody().add(new Point(9, 10));
        this.snake.setColor(Color.ORANGE);
        if (snake.getBody().size() == 0) {
            this.snake = new Snake();
        }

        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="apples">
        this.apples.removeAll(apples);
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Bomb">
        this.bomb.removeAll(bomb);
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        
        
        score += 500;
         //</editor-fold>
        
        gameState = GameState.RUNNING;
        }
        
        //</editor-fold>
       
        
        //<editor-fold defaultstate="collapsed" desc="Level Four">
        if (gameState == GameState.LEVELFOUR) {
        this.setBackground(ResourceTools.loadImageFromResource("resources/background_neon_1.jpg"));
        
         //<editor-fold defaultstate="collapsed" desc="mySnake4">
        Snake mySnake4 = new Snake();
        mySnake4.getBody().add(new Point(84, 36));
        mySnake4.getBody().add(new Point(84, 35));
        mySnake4.getBody().add(new Point(84, 34));
        mySnake4.getBody().add(new Point(84, 33));
        mySnake4.getBody().add(new Point(83, 33));
        mySnake4.getBody().add(new Point(83, 34));
        mySnake4.getBody().add(new Point(83, 35));
        mySnake4.getBody().add(new Point(83, 36));
        mySnake4.setDirection(Direction.DOWN);
        mySnake4.setColor(Color.GREEN);
        mySnake4.setRandomDirectionChange(true);
        if (mySnake4.getHead().equals(apples)) {
            mySnake4.grow();
        }
        if (mySnake4.getHead().equals(bomb)) {
            mySnake4.shrink();
        }
        this.enemySnakes.add(mySnake4);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake2">
        Snake mySnake2 = new Snake();
        mySnake2.getBody().add(new Point(84, 10));
        mySnake2.getBody().add(new Point(84, 9));
        mySnake2.getBody().add(new Point(84, 8));
        mySnake2.getBody().add(new Point(84, 7));
        mySnake2.getBody().add(new Point(84, 10));
        mySnake2.getBody().add(new Point(84, 9));
        mySnake2.getBody().add(new Point(84, 8));
        mySnake2.getBody().add(new Point(84, 7));
        mySnake2.setDirection(Direction.LEFT);
        mySnake2.setColor(Color.MAGENTA);
        mySnake2.setRandomDirectionChange(true);
        if (mySnake2.getHead().equals(apples)) {
            mySnake2.grow();
        }
        if (mySnake2.getHead().equals(bomb)) {
            mySnake2.shrink();
        }
        this.enemySnakes.add(mySnake2);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake3">
        Snake mySnake3 = new Snake();
        mySnake3.getBody().add(new Point(10, 36));
        mySnake3.getBody().add(new Point(10, 35));
        mySnake3.getBody().add(new Point(10, 34));
        mySnake3.getBody().add(new Point(10, 33));
        mySnake3.getBody().add(new Point(10, 36));
        mySnake3.getBody().add(new Point(10, 35));
        mySnake3.getBody().add(new Point(10, 34));
        mySnake3.getBody().add(new Point(10, 33));
        mySnake3.setDirection(Direction.UP);
        mySnake3.setColor(Color.CYAN);
        mySnake3.setRandomDirectionChange(true);
        if (mySnake3.getHead().equals(apples)) {
            mySnake3.grow();
        }
        if (mySnake3.getHead().equals(bomb)) {
            mySnake3.shrink();
        }
        this.enemySnakes.add(mySnake3);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake5">
        Snake mySnake5 = new Snake();
        mySnake5.getBody().add(new Point(42, 10));
        mySnake5.getBody().add(new Point(42, 9));
        mySnake5.getBody().add(new Point(42, 8));
        mySnake5.getBody().add(new Point(42, 7));
        mySnake5.getBody().add(new Point(42, 10));
        mySnake5.getBody().add(new Point(42, 9));
        mySnake5.getBody().add(new Point(42, 8));
        mySnake5.getBody().add(new Point(42, 7));
        mySnake5.setDirection(Direction.LEFT);
        mySnake5.setColor(Color.YELLOW);
        mySnake5.setRandomDirectionChange(true);
        if (mySnake5.getHead().equals(apples)) {
            mySnake5.grow();
        }
        if (mySnake5.getHead().equals(bomb)) {
            mySnake5.shrink();
        }
        this.enemySnakes.add(mySnake5);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake6">
        Snake mySnake6 = new Snake();
        mySnake6.getBody().add(new Point(42, 36));
        mySnake6.getBody().add(new Point(42, 35));
        mySnake6.getBody().add(new Point(42, 34));
        mySnake6.getBody().add(new Point(42, 33));
        mySnake6.getBody().add(new Point(42, 33));
        mySnake6.getBody().add(new Point(42, 34));
        mySnake6.getBody().add(new Point(42, 35));
        mySnake6.getBody().add(new Point(42, 36));
        mySnake6.setDirection(Direction.LEFT);
        mySnake6.setColor(Color.RED);
        mySnake6.setRandomDirectionChange(true);
        if (mySnake6.getHead().equals(apples)) {
            mySnake6.grow();
        }
        if (mySnake6.getHead().equals(bomb)) {
            mySnake6.shrink();
        }
        this.enemySnakes.add(mySnake6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Snake">
        this.snake = new Snake();
        this.snake.setDirection(Direction.RIGHT);
        this.snake.getBody().add(new Point(10, 10));
        this.snake.getBody().add(new Point(10, 9));
        this.snake.getBody().add(new Point(10, 8));
        this.snake.getBody().add(new Point(10, 7));
        this.snake.getBody().add(new Point(9, 7));
        this.snake.getBody().add(new Point(9, 8));
        this.snake.getBody().add(new Point(9, 9));
        this.snake.getBody().add(new Point(9, 10));
        this.snake.setColor(Color.ORANGE);
        if (snake.getBody().size() == 0) {
            this.snake = new Snake();
        }

        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="apples">
        this.apples.removeAll(apples);
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Bomb">
        this.bomb.removeAll(bomb);
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
         //</editor-fold>
        
        score += 500;
        
        gameState = GameState.RUNNING;
        }
        
        //</editor-fold>
        
        
        //<editor-fold defaultstate="collapsed" desc="Level Five">
        if (gameState == GameState.LEVELFIVE) {
        this.setBackground(ResourceTools.loadImageFromResource("resources/background_neon_4.jpg"));
        
         //<editor-fold defaultstate="collapsed" desc="mySnake4">
        Snake mySnake4 = new Snake();
        mySnake4.getBody().add(new Point(84, 36));
        mySnake4.getBody().add(new Point(84, 35));
        mySnake4.getBody().add(new Point(84, 34));
        mySnake4.getBody().add(new Point(84, 33));
        mySnake4.getBody().add(new Point(83, 33));
        mySnake4.getBody().add(new Point(83, 34));
        mySnake4.getBody().add(new Point(83, 35));
        mySnake4.getBody().add(new Point(83, 36));
        mySnake4.setDirection(Direction.DOWN);
        mySnake4.setColor(Color.GREEN);
        mySnake4.setRandomDirectionChange(true);
        if (mySnake4.getHead().equals(apples)) {
            mySnake4.grow();
        }
        if (mySnake4.getHead().equals(bomb)) {
            mySnake4.shrink();
        }
        this.enemySnakes.add(mySnake4);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake2">
        Snake mySnake2 = new Snake();
        mySnake2.getBody().add(new Point(84, 10));
        mySnake2.getBody().add(new Point(84, 9));
        mySnake2.getBody().add(new Point(84, 8));
        mySnake2.getBody().add(new Point(84, 7));
        mySnake2.getBody().add(new Point(84, 10));
        mySnake2.getBody().add(new Point(84, 9));
        mySnake2.getBody().add(new Point(84, 8));
        mySnake2.getBody().add(new Point(84, 7));
        mySnake2.setDirection(Direction.LEFT);
        mySnake2.setColor(Color.MAGENTA);
        mySnake2.setRandomDirectionChange(true);
        if (mySnake2.getHead().equals(apples)) {
            mySnake2.grow();
        }
        if (mySnake2.getHead().equals(bomb)) {
            mySnake2.shrink();
        }
        this.enemySnakes.add(mySnake2);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake3">
        Snake mySnake3 = new Snake();
        mySnake3.getBody().add(new Point(10, 36));
        mySnake3.getBody().add(new Point(10, 35));
        mySnake3.getBody().add(new Point(10, 34));
        mySnake3.getBody().add(new Point(10, 33));
        mySnake3.getBody().add(new Point(10, 36));
        mySnake3.getBody().add(new Point(10, 35));
        mySnake3.getBody().add(new Point(10, 34));
        mySnake3.getBody().add(new Point(10, 33));
        mySnake3.setDirection(Direction.UP);
        mySnake3.setColor(Color.CYAN);
        mySnake3.setRandomDirectionChange(true);
        if (mySnake3.getHead().equals(apples)) {
            mySnake3.grow();
        }
        if (mySnake3.getHead().equals(bomb)) {
            mySnake3.shrink();
        }
        this.enemySnakes.add(mySnake3);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake5">
        Snake mySnake5 = new Snake();
        mySnake5.getBody().add(new Point(42, 10));
        mySnake5.getBody().add(new Point(42, 9));
        mySnake5.getBody().add(new Point(42, 8));
        mySnake5.getBody().add(new Point(42, 7));
        mySnake5.getBody().add(new Point(42, 10));
        mySnake5.getBody().add(new Point(42, 9));
        mySnake5.getBody().add(new Point(42, 8));
        mySnake5.getBody().add(new Point(42, 7));
        mySnake5.setDirection(Direction.LEFT);
        mySnake5.setColor(Color.YELLOW);
        mySnake5.setRandomDirectionChange(true);
        if (mySnake5.getHead().equals(apples)) {
            mySnake5.grow();
        }
        if (mySnake5.getHead().equals(bomb)) {
            mySnake5.shrink();
        }
        this.enemySnakes.add(mySnake5);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="mySnake6">
        Snake mySnake6 = new Snake();
        mySnake6.getBody().add(new Point(42, 36));
        mySnake6.getBody().add(new Point(42, 35));
        mySnake6.getBody().add(new Point(42, 34));
        mySnake6.getBody().add(new Point(42, 33));
        mySnake6.getBody().add(new Point(42, 33));
        mySnake6.getBody().add(new Point(42, 34));
        mySnake6.getBody().add(new Point(42, 35));
        mySnake6.getBody().add(new Point(42, 36));
        mySnake6.setDirection(Direction.LEFT);
        mySnake6.setColor(Color.RED);
        mySnake6.setRandomDirectionChange(true);
        if (mySnake6.getHead().equals(apples)) {
            mySnake6.grow();
        }
        if (mySnake6.getHead().equals(bomb)) {
            mySnake6.shrink();
        }
        this.enemySnakes.add(mySnake6);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="mySnake7">
        Snake mySnake7 = new Snake();
        mySnake7.getBody().add(new Point(42, 36));
        mySnake7.getBody().add(new Point(42, 35));
        mySnake7.getBody().add(new Point(42, 34));
        mySnake7.getBody().add(new Point(42, 33));
        mySnake7.getBody().add(new Point(42, 33));
        mySnake7.getBody().add(new Point(42, 34));
        mySnake7.getBody().add(new Point(42, 35));
        mySnake7.getBody().add(new Point(42, 36));
        mySnake7.setDirection(Direction.LEFT);
        mySnake7.setColor(Color.WHITE);
        mySnake7.setRandomDirectionChange(true);
        if (mySnake7.getHead().equals(apples)) {
            mySnake7.grow();
        }
        if (mySnake7.getHead().equals(bomb)) {
            mySnake7.shrink();
        }
        this.enemySnakes.add(mySnake7);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Snake">
        this.snake = new Snake();
        this.snake.setDirection(Direction.RIGHT);
        this.snake.getBody().add(new Point(10, 10));
        this.snake.getBody().add(new Point(10, 9));
        this.snake.getBody().add(new Point(10, 8));
        this.snake.getBody().add(new Point(10, 7));
        this.snake.getBody().add(new Point(9, 7));
        this.snake.getBody().add(new Point(9, 8));
        this.snake.getBody().add(new Point(9, 9));
        this.snake.getBody().add(new Point(9, 10));
        this.snake.setColor(Color.ORANGE);
        if (snake.getBody().size() == 0) {
            this.snake = new Snake();
        }

        //</editor-fold>5
        
        //<editor-fold defaultstate="collapsed" desc="apples">
        this.apples.removeAll(apples);
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Bomb">
        this.bomb.removeAll(bomb);
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
        this.bomb.add(getRandomGridLocation());
         //</editor-fold>
        
        score += 500;
        
        gameState = GameState.RUNNING;
        }
        
        //</editor-fold>

        if (gameState == GameState.EATAPPLE) {
            AudioPlayer.play(ResourceTools.getResourceAsStream("resources/eat_apple.wav"));
            gameState = GameState.RUNNING;
        }
        if (gameState == GameState.HITBOMB) {
            AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Explosion.wav"));
            gameState = GameState.RUNNING;
        }
        if (gameState == GameState.BACKGROUNDMUSIC) {
            AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Fuzion_Frenzy.wav"));
            gameState = GameState.RUNNING;
        }

        if (gameState == GameState.CRASH) {
            AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Crashing.wav"));
            gameState = GameState.RUNNING;
        }
        if (gameState == GameState.KILLED) {
            AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Dying.wav"));
            deathcount += 1;
            score += 100;
            gameState = GameState.RUNNING;
        }
        if (gameState == GameState.ENDED) {
            AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Game_Over.wav"));
            gameState = GameState.GAMEOVER;
        }
        if (gameState == GameState.GAMEOVER) {
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //toggle the paused / running state
            if (gameState == GameState.RUNNING) {
                gameState = GameState.PAUSEMUSIC;
            } else if (gameState == GameState.PAUSED) {
                gameState = GameState.RUNNING;
            }


        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            getSnake().setDirection(Direction.UP);
            getSnake().move();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            getSnake().setDirection(Direction.RIGHT);
            getSnake().move();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            getSnake().setDirection(Direction.DOWN);
            getSnake().move();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            getSnake().setDirection(Direction.LEFT);
            getSnake().move();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameState = GameState.ENDED;
        }

    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (this.getGrid() != null) {

            for (int i = 0; i < 10; i++) {
            }

            if (this.apples != null) {
                for (int i = 0; i < this.apples.size(); i++) {
                    this.apples.get(i);
                    GraphicsPalette.drawApple(graphics, this.grid.getCellPosition(this.apples.get(i)), this.grid.getCellSize());
                }
            }

            if (this.bomb != null) {
                for (int i = 0; i < this.bomb.size(); i++) {
                    this.bomb.get(i);
                    GraphicsPalette.drawBomb(graphics, this.grid.getCellPosition(this.bomb.get(i)), this.grid.getCellSize(), Color.yellow);
                }
            }

            Point cellLocation;
            if (getSnake() != null) {
                graphics.setColor(getSnake().getColor());
                for (int i = 0; i < getSnake().getBody().size(); i++) {
                    cellLocation = getGrid().getCellPosition(getSnake().getBody().get(i));
                    graphics.fillOval(cellLocation.x, cellLocation.y, getGrid().getCellWidth(), getGrid().getCellHeight());
                }

                for (int j = 0; j < this.enemySnakes.size(); j++) {
                    graphics.setColor(this.enemySnakes.get(j).getColor());
                    for (int i = 0; i < this.enemySnakes.get(j).getBody().size(); i++) {
                        cellLocation = getGrid().getCellPosition(this.enemySnakes.get(j).getBody().get(i));
                        graphics.fillOval(cellLocation.x, cellLocation.y, getGrid().getCellWidth(), getGrid().getCellHeight());
                    }

                }
            }
            graphics.setColor(Color.ORANGE);
            graphics.setFont(new Font("Space Age", Font.BOLD, 40));
            graphics.drawString("Score: " + this.getScore(), 20, 50);
            graphics.drawString("Snake Battle", 750, 50);

            

            if (gameState == GameState.PAUSED) {
                graphics.setColor(Color.WHITE);
                graphics.setFont(new Font("Space Age", Font.BOLD, 40));
                graphics.drawString("Game Paused", 750, 400);
            }

            if (gameState == GameState.GAMEOVER) {
                graphics.setColor(Color.WHITE);
                graphics.setFont(new Font("Space Age", Font.BOLD, 40));
                graphics.drawString("Game Over", 750, 400);
            }
            if (gameState == GameState.START) {
                graphics.setColor(Color.WHITE);
                graphics.setFont(new Font("Space Age", Font.BOLD, 40));
                graphics.drawString("You are the orange snake", 750, 400);
                graphics.drawString("Press enter to start", 750, 600);
            }
        }
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * @return the snake
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * @param snake the snake to set
     */
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    /**
     * @return the moveDelay
     */
    public int getMoveDelay() {
        return moveDelay;
    }

    /**
     * @param moveDelay the moveDelay to set
     */
    public void setMoveDelay(int moveDelay) {
        this.moveDelay = moveDelay;
    }

    /**
     * @return the moveCounter
     */
    public int getMoveCounter() {
        return moveCounter;
    }

    /**
     * @param moveCounter the moveCounter to set
     */
    public void setMoveCounter(int moveCounter) {
        this.moveCounter = moveCounter;
    }
}
