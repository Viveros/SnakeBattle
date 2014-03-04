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
        if (alive) {
            
        if (delay <= 0) {

            //randomly change direction
            if ((isRandomDirectionChange()) && (Math.random() <= .20)) {
                setDirection(Direction.getRandomDirection());
            }

            // create a new location for the head, using the direction
            int x = 0;
            int y = 0;

            
            if (getHead().x + x >= maxX - 1) {
                this.setDirection(Direction.LEFT);
            }
            if (getHead().y + y >= maxY - 1) {
                this.setDirection(Direction.UP);
            }
            if (getHead().x == minX) {
                this.setDirection(Direction.RIGHT);
            }
            if (getHead().y == minY) {
                this.setDirection(Direction.DOWN);
            }
            
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
    }

    public void grow() {
        body.add(0, new Point(getHead().x, getHead().y));
    }

//    public void shrink() {
//        body.remove(body.size() - 1);
//    }
    public void shrink()  {
        body.remove(0);
    }
    
    public void kill(){
        this.alive = false;
    }

    public void resucitate(){
        this.alive = true;
    }
    
    public void respawn(Point respawnLocation, Point hiddenLocation){
        for (int i = 0; i < body.size(); i++) {
            body.get(i).setLocation(hiddenLocation);
        }
        
        getHead().setLocation(respawnLocation);        
    }
    
    public static boolean intersects(Point location, ArrayList<Point> locations) {
        for (int i = 0; i < locations.size(); i++) {
            if (location.equals(locations.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Point> body;
    private Direction direction = Direction.RIGHT;
    private Color color = Color.RED;
    private boolean randomDirectionChange = false;
    private boolean alive = true;
    private int delay;
    private int maxX = 94;
    private int maxY = 46;
    private int minX = 0;
    private int minY = 0;

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

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    //</editor-fold>
}
