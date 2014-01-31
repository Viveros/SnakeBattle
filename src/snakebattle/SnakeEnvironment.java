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

    private GameState gameState = GameState.RUNNING;
    private int score = 0;
    private Grid grid;
    private Snake snake;
    private ArrayList<Point> apples;
    private ArrayList<Point> poisonbottle;
    private ArrayList<Snake> snakes;
    private int moveDelay = 1;
    private int moveCounter = moveDelay;
    private int growDelay = 100;
    private int growCounter = growDelay;
    private int myGrowDelay = 100;
    private int myGrowCounter = myGrowDelay;
    private Image mainSnakeHead;
    private ArrayList<Point> mainSnakehead;

    private Point getRandomGridLocation() {
        return new Point((int) (Math.random() * this.grid.getColumns()), (int) (Math.random() * this.grid.getRows()));

    }

    @Override
    public void initializeEnvironment() {
        if (score < 1500) {
            this.setBackground(ResourceTools.loadImageFromResource("resources/background_neon_1.jpg"));
        }
        if (this.score == 1500) {
            this.setBackground(ResourceTools.loadImageFromResource("resources/background_neon_7.jpg"));
        }

        this.mainSnakeHead = ResourceTools.loadImageFromResource("resources/mainSnakeHead.gif");

        this.setGrid(new Grid());
        this.getGrid().setColor(Color.YELLOW);
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
        
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="poisonbottles">
        this.poisonbottle = new ArrayList<Point>();
        this.poisonbottle.add(getRandomGridLocation());
        this.poisonbottle.add(getRandomGridLocation());
        this.poisonbottle.add(getRandomGridLocation());
        this.poisonbottle.add(getRandomGridLocation());
        this.poisonbottle.add(getRandomGridLocation());
        this.poisonbottle.add(getRandomGridLocation());
        this.poisonbottle.add(getRandomGridLocation());
        this.poisonbottle.add(getRandomGridLocation());
        //</editor-fold>


        this.snakes = new ArrayList<Snake>();


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
        if (mySnake4.getHead().equals(poisonbottle)) {
            mySnake4.shrink();
        }
        this.snakes.add(mySnake4);
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
        if (mySnake2.getHead().equals(poisonbottle)) {
            mySnake2.shrink();
        }
        this.snakes.add(mySnake2);
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
        if (mySnake3.getHead().equals(poisonbottle)) {
            mySnake3.shrink();
        }
        this.snakes.add(mySnake3);
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
        if (mySnake5.getHead().equals(poisonbottle)) {
            mySnake5.shrink();
        }
        this.snakes.add(mySnake5);
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
        if (mySnake6.getHead().equals(poisonbottle)) {
            mySnake6.shrink();
        }
        this.snakes.add(mySnake6);
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
                for (int i = 0; i < this.snakes.size(); i++) {
                    this.snakes.get(i).move();
                    if (Snake.intersects(this.snake.getHead(), this.snakes.get(i).getBody())) {
                        System.out.println("You're hitting a snake!!");
                        this.snake.kill();
                    }
                    if (Snake.intersects(this.snakes.get(i).getHead(), this.snake.getBody())) {
                        System.out.println("Another snake is hitting you!!");
                        this.snakes.get(i).kill();
                    }

                }
            }  
        }
        
            if (this.score >= 1500) {
                gameState = GameState.ENDED;
            }


            for (int i = 0; i < apples.size(); i++) {
                if (snake.getHead().equals(apples.get(i))) {
                    //add to score and move apple
                    this.snake.grow();
                    this.score += 50;
                    this.apples.get(i).x = (int) (Math.random() * this.grid.getColumns());
                    this.apples.get(i).y = (int) (Math.random() * this.grid.getRows());
                }
            }


            for (int i = 0; i < apples.size(); i++) {
                if (snakes.equals(apples.get(i))) {
                    this.apples.get(i).x = (int) (Math.random() * this.grid.getColumns());
                    this.apples.get(i).y = (int) (Math.random() * this.grid.getRows());
                    this.snakes.get(i).grow();
                }
            }
            for (int i = 0; i < poisonbottle.size(); i++) {
                if (snake.getHead().equals(poisonbottle.get(i))) {
                    this.snake.shrink();
                    this.score -= 100;
                    this.poisonbottle.get(i).x = (int) (Math.random() * this.grid.getColumns());
                    this.poisonbottle.get(i).y = (int) (Math.random() * this.grid.getRows());
                }
            }
            for (int i = 0; i < poisonbottle.size(); i++) {
                if (snakes.equals(poisonbottle.get(i))) {
                    this.poisonbottle.get(i).x = (int) (Math.random() * this.grid.getColumns());
                    this.poisonbottle.get(i).y = (int) (Math.random() * this.grid.getRows());
                    this.snakes.get(i).shrink();
                }
            }

            if (snake.getHead().x == grid.getColumns()) {
                snake.shrink();
            }
            if (snake.getHead().y == grid.getRows()) {
                snake.shrink();
            }
            if (snake.getHead().x == 0) {
                snake.shrink();
            }
            if (snake.getHead().y == 0) {
                snake.shrink();
            }
        }




        if (this.growCounter > 0) {
            this.growCounter--;
        } else {
            for (int i = 0; i < this.snakes.size(); i++) {

                this.snakes.get(i).grow();
            }

            this.growCounter = this.growDelay;
        }









    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //toggle the paused / running state
            if (gameState == GameState.RUNNING)  {
                gameState = GameState.PAUSED;
            }
            else if (gameState == GameState.PAUSED)  {
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
            this.getGrid().paintComponent(graphics);

            for (int i = 0; i < 10; i++) {
            }

            if (this.apples != null) {
                for (int i = 0; i < this.apples.size(); i++) {
                    this.apples.get(i);
                    GraphicsPalette.drawApple(graphics, this.grid.getCellPosition(this.apples.get(i)), this.grid.getCellSize());
                }
            }

            if (this.poisonbottle != null) {
                for (int i = 0; i < this.poisonbottle.size(); i++) {
                    this.poisonbottle.get(i);
                    GraphicsPalette.drawPoisonBottle(graphics, this.grid.getCellPosition(this.poisonbottle.get(i)), this.grid.getCellSize(), Color.yellow);
                }
            }

            Point cellLocation;
            if (getSnake() != null) {
                graphics.setColor(getSnake().getColor());
                for (int i = 0; i < getSnake().getBody().size(); i++) {
                    cellLocation = getGrid().getCellPosition(getSnake().getBody().get(i));
                    graphics.fillOval(cellLocation.x, cellLocation.y, getGrid().getCellWidth(), getGrid().getCellHeight());
                }
            }

            for (int j = 0; j < this.snakes.size(); j++) {
                graphics.setColor(this.snakes.get(j).getColor());
                for (int i = 0; i < this.snakes.get(j).getBody().size(); i++) {
                    cellLocation = getGrid().getCellPosition(this.snakes.get(j).getBody().get(i));
                    graphics.fillOval(cellLocation.x, cellLocation.y, getGrid().getCellWidth(), getGrid().getCellHeight());
                    if (snakes.get(j).getBody().size() == 0) {
                        
            }
                }
                
            }
        }
        graphics.setColor(Color.ORANGE);
        graphics.setFont(new Font("Space Age", Font.BOLD, 40));
        graphics.drawString("Score: " + this.getScore(), 20, 50);
        graphics.drawString("Snake Battle", 750, 50);
        graphics.drawImage(mainSnakeHead, snake.getHead().x, snake.getHead().y, grid.getCellHeight(), grid.getCellWidth(), this);
        
        if  (gameState == GameState.ENDED)  {
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Space Age", Font.BOLD, 40));
            graphics.drawString("Game Over", 750, 400);
            if (this.score >= 1500) {
                graphics.drawString("You win!!",750, 600);
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
