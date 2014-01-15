/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakebattle;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Wyatt
 */
public class Snake {

    {
        body = new ArrayList<>();
    }

    public void move() {
        if (delay <= 0) {
            delay = moveDelay;

            //randomly change direction
            if ((isRandomDirectionChange()) && (Math.random() <= .20)) {
                setDirection(Direction.getRandomDirection());
            }

            // create a new location for the head, using the direction
            int x = 0;
            int y = 0;

            switch (getDirection()) {
                case UP:
                    x = 0;
                    y = -1;
                    break;

                case RIGHT:
                    x = 1;
                    y = 0;
                    break;

                case DOWN:
                    x = 0;
                    y = 1;
                    break;

                case LEFT:
                    x = -1;
                    y = 0;
            }
            body.add(0, new Point(getHead().x + x, getHead().y + y));
            body.remove(body.size() - 1);
        } else {
            delay--;
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Point> body;
    private Direction direction = Direction.RIGHT;
    private Color color = Color.RED;
    private boolean randomDirectionChange = false;
    private int moveDelay = 5;
    private int delay;

    public Point getHead() {
        return body.get(0);
    }

    /**
     * @return the body
     */
    public ArrayList<Point> getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(ArrayList<Point> body) {
        this.body = body;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
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
     * @return the randomDirectionChange
     */
    public boolean isRandomDirectionChange() {
        return randomDirectionChange;
    }

    /**
     * @param randomDirectionChange the randomDirectionChange to set
     */
    public void setRandomDirectionChange(boolean randomDirectionChange) {
        this.randomDirectionChange = randomDirectionChange;
    }
    //</editor-fold>
}
