/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakebattle;

/**
 *
 * @author Wyatt
 */
public enum Direction {
            RIGHT, LEFT, UP, DOWN;

    public static Direction getRandomDirection() {
        return Direction.values()[(int) (Math.random() * Direction.values().length)];
    }
}
