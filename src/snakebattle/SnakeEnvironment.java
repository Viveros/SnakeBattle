/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakebattle;

import audio.AudioPlayer;
import environment.Environment;
import environment.Grid;
import image.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Wyatt
 */
class SnakeEnvironment extends Environment {

    private int score = 0;
    private Grid grid;
    private Snake snake;
    private ArrayList<Point> apples;
    private ArrayList<Point> poisonbottle;
    private ArrayList<Snake> snakes;
    private int delay = 5;
    private int moveCounter = delay;

    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/background_neon_1.jpg"));

        this.setGrid(new Grid());
        this.getGrid().setColor(Color.YELLOW);
        this.getGrid().setColumns(47);
        this.getGrid().setRows(23);
        this.getGrid().setCellHeight(20);
        this.getGrid().setCellWidth(20);
        this.getGrid().setPosition(new Point(20, 60));

        this.apples = new ArrayList<Point>();
        this.apples.add(new Point(10, 12));
        this.apples.add(new Point(30, 5));
        this.apples.add(new Point(5, 3));
        this.apples.add(new Point(11, 1));

        this.poisonbottle = new ArrayList<Point>();
        this.poisonbottle.add(new Point(10, 16));
        this.poisonbottle.add(new Point(20, 13));


        this.snakes = new ArrayList<Snake>();

        Snake mySnake1 = new Snake();
        mySnake1.getBody().add(new Point(10, 5));
        mySnake1.getBody().add(new Point(10, 6));
        mySnake1.getBody().add(new Point(9, 6));
        mySnake1.setDirection(Direction.RIGHT);
        mySnake1.setColor(Color.GREEN);
        mySnake1.setRandomDirectionChange(true);
        this.snakes.add(mySnake1);

        Snake mySnake2 = new Snake();
        mySnake2.getBody().add(new Point(10, 15));
        mySnake2.getBody().add(new Point(10, 16));
        mySnake2.getBody().add(new Point(9, 16));
        mySnake2.setDirection(Direction.UP);
        mySnake2.setColor(Color.MAGENTA);
        mySnake2.setRandomDirectionChange(true);
        this.snakes.add(mySnake2);

        this.setSnake(new Snake());
        this.getSnake().getBody().add(new Point(5, 5));
        this.getSnake().getBody().add(new Point(5, 4));
        this.getSnake().getBody().add(new Point(5, 3));
        this.getSnake().getBody().add(new Point(4, 3));


    }

    @Override
    public void timerTaskHandler() {
        if (snake != null) {
            if (moveCounter <= 0) {
                System.out.println("MOVE");
                snake.move();

                for (int i = 0; i < this.snakes.size(); i++) {
                    this.snakes.get(i).move();
                }

                moveCounter = delay;
            } else {
                moveCounter--;
            }
        }

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.setScore(this.getScore() + 50);
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
//                this.snakes.get(i).move();
                graphics.setColor(this.snakes.get(j).getColor());
                for (int i = 0; i < this.snakes.get(j).getBody().size(); i++) {
                    cellLocation = getGrid().getCellPosition(this.snakes.get(j).getBody().get(i));
                    graphics.fillOval(cellLocation.x, cellLocation.y, getGrid().getCellWidth(), getGrid().getCellHeight());
                }
            }


        }




        graphics.setColor(Color.ORANGE);
        graphics.setFont(new Font("Space Age", Font.BOLD, 40));
        graphics.drawString("Score: " + this.getScore(), 20, 50);
        graphics.drawString("Snake Battle", 750, 50);


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
}
