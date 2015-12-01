/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author LK
 */
class Snake {
    
    
    public void move(){
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
        body.remove(body.size() - 1);
        
    }
    
    public void draw(Graphics graphics){
        for (Point location : body) {
            graphics.setColor(Color.GREEN);
            graphics.fillOval(cellData.getSystemCoordX(location.x, location.y), 
                              cellData.getSystemCoordY(location.x, location.y), 
                              cellData.getCellWidth(), cellData.getCellHeight());
        }
    }
    
    public Snake(ArrayList<Point> body, Direction direction, CellDataProviderIntf cellData){
        this.body = body;
        this.direction = direction;
        this.cellData = cellData;
    }
    
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Point> body;
    private Direction direction;
    private CellDataProviderIntf cellData;
    
    private static final int HEAD_POSITION = 0;
    
    private Point getHead() {
        return body.get(HEAD_POSITION);
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
    
//</editor-fold>

}
