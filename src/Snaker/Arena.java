/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snaker;

import audio.AudioPlayer;
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

        this.setBackground(ResourceTools.loadImageFromResource("Snaker/racing-game.jpg").getScaledInstance(1500, 900, Image.SCALE_SMOOTH));

        grid = new Grid(70, 36, 20, 20, new Point(20, 50), new Color(100, 100, 100, 100));

        ArrayList<Point> body = new ArrayList<>();
        body.add(new Point(35, 10));
        body.add(new Point(35, 11));
        body.add(new Point(35, 12));
        body.add(new Point(35, 13));
        

        cars = new Snake(body, Direction.LEFT, this);

        barriers = new Barriers();
        //edges
        barriers.addBarrierRange(0, 0, 0, 35, Color.GRAY, this);
        barriers.addBarrierRange(0, 0, 69, 0, Color.GRAY, this);
        barriers.addBarrierRange(69, 0, 69, 35, Color.GRAY, this);
        barriers.addBarrierRange(0, 35, 69, 35, Color.GRAY, this);
        //Path 
        barriers.addBarrierRange(20, 4, 30, 4, Color.GRAY, this);
        barriers.addBarrierRange(20, 4, 20, 10, Color.GRAY, this);
        barriers.addBarrierRange(5, 10, 20, 10, Color.GRAY, this);
        barriers.addBarrierRange(16, 0, 16, 5, Color.GRAY, this);
        barriers.addBarrierRange(0, 35, 69, 35, Color.GRAY, this);

    }

    public void checkIntersections() {

        for (Barrier barrier : barriers.getBarriers()) {
            if (barrier.getLocation().equals(cars.getHead())) {
                
                cars.addHealth(-1000);

            }
        }

    }

    @Override
    public void initializeEnvironment() {

    }

    private int moveDelay = 0;
    private int moveDelayLimit = 3;

    @Override
    public void timerTaskHandler() {
        if (cars != null) {
            if (moveDelay >= moveDelayLimit) {
                cars.move();
                moveDelay = 0;
            } else {
                moveDelay++;
            }
            checkIntersections();
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_D) {
            cars.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            cars.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            cars.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            cars.setDirection(Direction.DOWN);
            
        }else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            AudioPlayer.play("/Snaker/car_sound.wav");

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
