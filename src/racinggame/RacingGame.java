/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racinggame;

import environment.ApplicationStarter;
import java.awt.Dimension;

/**
 *
 * @author LK
 */
public class RacingGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationStarter.run(args, "Change this later", new Dimension(1500, 900), new RaceTrack());
        
    }
    
}
