/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakebattle;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Wyatt
 */
public class Snake {  
   private ArrayList<Point> body;
   private Direction direction;
    private Direction setDirection;
    private int delay;
   {
        body = new ArrayList<>();
    }
   
   public void move() {
       // create a new location for the head, using the direction
       int x = 0;
       int y = 0;
       
       switch (getDirection())  {
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
   }
   
   

   public Point getHead(){
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

    void setBody(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
