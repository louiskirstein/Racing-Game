/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snaker;

import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 *
 * @author LK
 */
class Arena extends Environment implements CellDataProviderIntf {

    private Grid grid;
    
    private Barriers barriers;
    private Snake cars;
    

    public Arena() {
        this.setBackground(Color.red);
        this.setBackground(ResourceTools.loadImageFromResource("Snaker/racing-game.jpg").getScaledInstance(1500, 900, Image.SCALE_SMOOTH));

        grid = new Grid(70, 36, 20, 20, new Point(20, 50), new Color(100, 100, 100, 100));
        
        ArrayList<Point> body = new ArrayList<>();
        body.add(new Point(35, 10));
        body.add(new Point(35, 11));
        body.add(new Point(35, 12));
        
        cars = new Snake(body, Direction.LEFT, this);
        
        barriers = new Barriers();
        barriers.add(new Barrier(10, 15,  Color.BLUE, this));
        barriers.add(new Barrier(11, 15,  Color.BLUE, this));
        barriers.add(new Barrier(12, 15,  Color.BLUE, this));
        barriers.add(new Barrier(13, 15,  Color.BLUE, this));
        barriers.add(new Barrier(14, 15,  Color.BLUE, this));
        barriers.add(new Barrier(15, 15,  Color.BLUE, this));
        barriers.add(new Barrier(16, 15,  Color.BLUE, this));
        barriers.add(new Barrier(17, 15,  Color.BLUE, this));
        barriers.add(new Barrier(18, 15,  Color.BLUE, this));
        barriers.add(new Barrier(19, 15,  Color.BLUE, this));
        barriers.add(new Barrier(20, 15,  Color.BLUE, this));
        barriers.add(new Barrier(21, 15,  Color.BLUE, this));
        barriers.add(new Barrier(22, 15,  Color.BLUE, this));
        barriers.add(new Barrier(23, 15,  Color.BLUE, this));
        barriers.add(new Barrier(24, 15,  Color.BLUE, this));
        barriers.add(new Barrier(25, 15,  Color.BLUE, this));
        barriers.add(new Barrier(26, 15,  Color.BLUE, this));
        barriers.add(new Barrier(27, 15,  Color.BLUE, this));
        barriers.add(new Barrier(27, 16,  Color.BLUE, this));
        barriers.add(new Barrier(27, 17,  Color.BLUE, this));
        barriers.add(new Barrier(27, 18,  Color.BLUE, this));
        barriers.add(new Barrier(27, 19,  Color.BLUE, this));
        barriers.add(new Barrier(27, 20,  Color.BLUE, this));
        
        barriers.addBarrierRange(28, 20, 50, 20, Color.yellow, this);
  
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
        
        if (cars != null) {
            cars.draw(graphics);
        }
        
        if (barriers != null) {
            barriers.draw(graphics);
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
