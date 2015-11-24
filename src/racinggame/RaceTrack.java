/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racinggame;

import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


/**
 *
 * @author LK
 */
class RaceTrack extends Environment implements CellDataProviderIntf {

    private Grid grid;
    private Car R8;
    

    public RaceTrack() {
        this.setBackground(Color.red);
        this.setBackground(ResourceTools.loadImageFromResource("racinggame/racing-game.jpg").getScaledInstance(1500, 900, Image.SCALE_SMOOTH));

        grid = new Grid(70, 36, 20, 20, new Point(20, 50), new Color(100, 100, 100, 100));
    }

    @Override
    public void initializeEnvironment() {

    }

    @Override
    public void timerTaskHandler() {
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("right");
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("left");
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            System.out.println("up");
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            System.out.println("down");
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
        if (grid != null) {
            grid.paintComponent(graphics);

        }
    }

    
    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }

    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }

    @Override
    public int getSystemCoordX(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).x;
    }

    @Override
    public int getSystemCoordY(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).y;
        
    }

}
