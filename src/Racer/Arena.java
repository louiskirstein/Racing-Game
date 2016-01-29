/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Racer;

import audio.AudioPlayer;
import components.HealthBar;
import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
    private Car car;
    private ArrayList<Item> items;
    private GameState state = GameState.STOPPED;
    private HealthBar fuelBar;
    
    
    public Arena() {

        this.setBackground(ResourceTools.loadImageFromResource("Racer/needforspeed.jpg").getScaledInstance(1500, 900, Image.SCALE_SMOOTH));

        grid = new Grid(70, 36, 20, 20, new Point(20, 50), new Color(100, 100, 100, 100));

        ArrayList<Point> body = new ArrayList<>();
        body.add(new Point(35, 10));
        body.add(new Point(35, 11));
        body.add(new Point(35, 12));
        body.add(new Point(35, 13));
        

        car = new Car(body, Direction.LEFT, this);

        barriers = new Barriers();
        //edges
        barriers.addBarrierRange(0, 0, 0, 35, Color.black, this);
        barriers.addBarrierRange(0, 0, 69, 0, Color.black, this);
        barriers.addBarrierRange(69, 0, 69, 35, Color.black, this);
        barriers.addBarrierRange(0, 35, 69, 35, Color.black, this);
        
        
        
        items = new ArrayList<>();
        items.add(new Item(random(68)+1,random(34)+1, "FUEL", ResourceTools.loadImageFromResource("Racer/gas_station.png"), this));
        items.add(new Item(random(68)+1,random(34)+1, "FUEL", ResourceTools.loadImageFromResource("Racer/gas_station.png"), this));
        items.add(new Item(random(68)+1,random(34)+1, "FUEL", ResourceTools.loadImageFromResource("Racer/gas_station.png"), this));
        items.add(new Item(random(68)+1,random(34)+1, "POWER_UP", ResourceTools.loadImageFromResource("Racer/electro_car.png"), this));

        
        fuelBar = new HealthBar(new Point (10, 10), new Dimension(100, 25), car);
        
    }
    
        
    

    public void checkIntersections() {

        for (Barrier barrier : barriers.getBarriers()) {
            if (barrier.getLocation().equals(car.getHead())) {
                car.addHealth(-1000);
            }
        }

        //check if car crashes itself
        if ((car != null) && car.selfHit()) {
            state = GameState.CRASHED;
            System.out.println("booooo");
            car.addHealth(-1000);
        }
        
        if (car != null){
            for (Item item : items) {
                if (item.getLocation().equals(car.getHead())) {
                    if (item.getType().equals("FUEL")) {
                        car.addFuel(100);
                    }
                }
            }
        }
    }
    public int random(int value)    {
    return (int) (Math.random() * value);
    
}
    

    @Override
    public void initializeEnvironment() {

    }

    private int moveDelay = 0;
    private int moveDelayLimit = 3;

    @Override
    public void timerTaskHandler() {
        if (car != null) {
            if (moveDelay >= moveDelayLimit) {
                car.move();
                car.addFuel(-1);
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
            car.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            car.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            car.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            car.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_G) {
            car.addGrowthCounter(1);
            
        }else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            AudioPlayer.play("/Racer/car_sound.wav");

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

        if (car != null) {
            car.draw(graphics);
        }

        if (barriers != null) {
            barriers.draw(graphics);
        }
        if (items!= null){
            for (int i = 0; i < items.size(); i++) {
                items.get(i).draw(graphics);
                
            }
        }
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Calibri", Font.BOLD, 30));
        graphics.drawString("Change Later", 600, 35);
        
        if (fuelBar != null) {
            fuelBar.draw(graphics);
            
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
