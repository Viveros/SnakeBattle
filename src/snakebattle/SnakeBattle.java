/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakebattle;

import audio.AudioPlayer;
import environment.ApplicationStarter;
import image.ResourceTools;

/**
 *
 * @author Wyatt
 */
public class SnakeBattle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        ApplicationStarter.run("Snake Battle", new SnakeEnvironment());
        AudioPlayer.play(ResourceTools.getResourceAsStream("resources/Casino_Royale.wav"));
    }
}
