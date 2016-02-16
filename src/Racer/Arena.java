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
    private MySoundManager soundManager;
    


    public Arena() {

        this.setBackground(ResourceTools.loadImageFromResource("Racer/needforspeed.jpg").getScaledInstance(1500, 900, Image.SCALE_SMOOTH));

        grid = new Grid(70, 36, 20, 20, new Point(20, 50), new Color(100, 100, 100, 100));

        ArrayList<Point> body = new ArrayList<>();
        body.add(new Point(35, 10));
        body.add(new Point(35, 11));
        body.add(new Point(35, 12));
        body.add(new Point(35, 13));

        car = new Car(body, Direction.LEFT, this);
        car.setFuel(200);

        barriers = new Barriers();
        //edges
        barriers.addBarrierRange(0, 0, 0, 35, Color.black, this);
        barriers.addBarrierRange(0, 0, 69, 0, Color.black, this);
        barriers.addBarrierRange(69, 0, 69, 35, Color.black, this);
        barriers.addBarrierRange(0, 35, 69, 35, Color.black, this);

        items = new ArrayList<>();
//        items.add(new Item(random(68) + 1, random(34) + 1, "FUEL", ResourceTools.loadImageFromResource("Racer/gas_station.png"), this));
//        items.add(new Item(random(68) + 1, random(34) + 1, "FUEL", ResourceTools.loadImageFromResource("Racer/gas_station.png"), this));
//        items.add(new Item(random(68) + 1, random(34) + 1, "FUEL", ResourceTools.loadImageFromResource("Racer/gas_station.png"), this));

        items.add(new Item(randomGridLocation(), Item.ITEM_TYPE_POWER_UP, ResourceTools.loadImageFromResource("Racer/gas_station.png"), this));
        
        fuelBar = new HealthBar(new Point(60, 9), new Dimension(200, 30), car);

        setState(GameState.RUNNING);
        
        //setUpSound
        soundManager = MySoundManager.getSoundManager();

    }
    
    public int randomInt(int min, int max) {
        return (int) (min + (Math.random() * (max - min + 1)));
    }
    
    public Point randomGridLocation() {
        return new Point(randomInt(1, grid.getColumns()-2), randomInt(1, grid.getRows()-2));
    }

    public void checkIntersections() {

        for (Barrier barrier : barriers.getBarriers()) {
            if (barrier.getLocation().equals(car.getHead())) {
                car.addHealth(-1000);
            }
        }

        //check if car crashes itself
        if (car != null) {
            for (Item item : items) {
                if (item.getLocation().equals(car.getHead())) {
                    if (item.getType().equals(Item.ITEM_TYPE_POWER_UP)) {
                        car.addFuel(50);
                        item.setLocation(randomGridLocation());
                    }
                }
            }

            for (Barrier barrier : barriers.getBarriers()) {
                if (barrier.getLocation().equals(car.getHead())) {

                }
            }

            if (car.selfHit()) {
                setState(GameState.CRASHED);
                car.addHealth(-1000);
            }
        }
    }

    public int random(int value) {
        return (int) (Math.random() * value);
    }

    @Override
    public void initializeEnvironment() {

    }

    private int moveDelay = 0;
    private int moveDelayLimit = 3;

    @Override
    public void timerTaskHandler() {
        if ((state == GameState.RUNNING) && (car.getFuel() > 0) && car.isAlive()) {
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
            soundManager.play(MySoundManager.RACING_NOISE);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            car.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_G) {
            car.addGrowthCounter(1);

        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            AudioPlayer.play("/Racer/car_sound.wav");
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (getState() == GameState.STOPPED) {
                setState(GameState.RUNNING);

            } else if (state == GameState.RUNNING) {
                setState(GameState.STOPPED);
            } else if (state == GameState.STOPPED) {
                setState(GameState.RUNNING);
            }

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
            if (car.getFuel() < 0 ) {
                graphics.setFont(new Font("Calibri", Font.BOLD, 80));
                graphics.drawString("OUT OF FUEL", 500, 400);
                
            }
            if (car.getHealth() < 0 ) {
                graphics.setFont(new Font("Calibri", Font.BOLD, 80));
                graphics.drawString("WASTED", 550, 400);
            }
        }

        if (barriers != null) {
            barriers.draw(graphics);
        }

        if (items != null) {
            for (Item item : items) {
                item.draw(graphics);
            }
        }

        graphics.setColor(Color.red);
        graphics.setFont(new Font("Calibri", Font.BOLD, 30));
        graphics.drawString("Change Later", 600, 35);

        if (fuelBar != null) {
            fuelBar.draw(graphics);

        }
        if (state == GameState.STOPPED) {
            graphics.setFont(new Font("Calibri", Font.BOLD, 100));
            graphics.drawString("PAUSE", 550, 400);
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

    /**
     * @return the state
     */
    public GameState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(GameState state) {
        this.state = state;

    }

}
