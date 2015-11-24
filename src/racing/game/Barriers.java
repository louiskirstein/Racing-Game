/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racing.game;

import java.awt.Color;


/**
 *
 * @author LK
 */
public class Barriers {
    
    public Barriers(int x, int y, Color color, boolean breakable){
        this.x = x;
        this.y = y;
        this.color = color;
        this.breakable = breakable;
    }
    
    int x, y;
    Color color;
    boolean breakable = false;
}
