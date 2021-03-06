/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Racer;

import components.HealthProviderIntf;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author LK
 */
class Car implements HealthProviderIntf {

    public void move() {
        if (isAlive()) {
            // make a copy of the snakes head
            Point newHead = new Point(getHead());

            // move the new head, based on the current direction
            if (direction == Direction.LEFT) {
                newHead.x--;
            } else if (direction == Direction.RIGHT) {
                newHead.x++;
            } else if (direction == Direction.UP) {
                newHead.y--;
            } else if (direction == Direction.DOWN) {
                newHead.y++;
            }

            // add the new head to the snakes body
            body.add(HEAD_POSITION, newHead);

            // remove the tail of snake
            // if the growthCounter is greater than zero
            // do NOT remove the tail
            // subtract one from the growthCounter 
            //else (the growthCounter is less than or equal to zero
            // delete the tail
            if (growthCounter > 0) {
                growthCounter-- ;
                
            }
            else {
                
            
            body.remove(body.size() - 1);
            }
        }
    }

    public void draw(Graphics graphics) {
        for (Point location : body) {
            graphics.setColor(Color.RED);
            graphics.fillRect(cellData.getSystemCoordX(location.x, location.y),
                    cellData.getSystemCoordY(location.x, location.y),
                    cellData.getCellWidth(), cellData.getCellHeight());
        }
    }

    public Car(ArrayList<Point> body, Direction direction, CellDataProviderIntf cellData) {
        this.body = body;
        this.direction = direction;
        this.cellData = cellData;
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Point> body;
    private Direction direction;
    private CellDataProviderIntf cellData;

    private int maxFuel = 200;
    private int fuel;

    private static final int HEAD_POSITION = 0;

    private int health = 100;

    private int growthCounter; 

    public Point getHead() {
        return getBody().get(HEAD_POSITION);
       
    }
    
    public boolean selfHit(){
        return getTail().contains(getHead());
    }

    /**
     * @return the tail
     */
    public ArrayList<Point> getTail() {
        ArrayList<Point> tail = new ArrayList<>();
        for (int i = 1; i < this.body.size(); i++) {
            tail.add(body.get(i));
        }
        return tail;
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

    /**
     * @return the cellData
     */
    public CellDataProviderIntf getCellData() {
        return cellData;
    }

    /**
     * @param cellData the cellData to set
     */
    public void setCellData(CellDataProviderIntf cellData) {
        this.cellData = cellData;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void addHealth(int health) {
        this.health += health;
    }

    public boolean isAlive() {
        return (health > 0);
    }

    /**
     * @return the growthCounter
     */
    public int getGrowthCounter() {
        return growthCounter;
    }

    /**
     * @param growthCounter the growthCounter to set
     */
    public void setGrowthCounter(int growthCounter) {
        this.growthCounter = growthCounter;
    }
    
    //growth to add to the snake
    public void addGrowthCounter(int growth) {
        this.growthCounter += growth;
    }

    /**
     * @return the maxFuel
     */
    public int getMaxFuel() {
        return maxFuel;
    }

    /**
     * @param maxFuel the maxFuel to set
     */
    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }
    

    /**
     * @return the fuel
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * @param fuel the fuel to set
     */
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    /**
     * @param fuel the fuel to set
     */
    public void addFuel(int fuel) {
        this.fuel += fuel;
        if (this.fuel > maxFuel) {
            this.fuel = maxFuel;
        }
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="HealthProviderIntf">
    @Override
    public int getMinimumHealth() {
        return 0;
    }
    
    @Override
    public int getMaximumHealth() {
        return getMaxFuel();
    }
    
    @Override
    public int getValue() {
        return getFuel();
    }
    
    @Override
    public double getFractionalHealth() {
        return fuel / maxFuel;
    }
    
    @Override
    public double getPercentHealth() {
        return 100 * fuel / maxFuel;
    }
//</editor-fold>

}
