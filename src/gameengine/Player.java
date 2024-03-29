/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine;

import api.Constant;
import api.IPlayer;
import api.IPlayerModel;
import api.IPlayerView;
import gameui.GridMap;
import gameui.GridMap.GridBox;
import model.Ship;
import javafx.scene.input.MouseButton;
import utils.Tuple;

/**
 * This class is "only" a helper of the controller 
 * to manage the MODEL and VIEW of a player in a single class
 * 
 * This class is not a MODEL nor a VIEW but it contains both.
 * 
 * @author zange
 */
public class Player implements IPlayer{
        
    //public IPlayerModel model;
    //public IPlayerView view;
    
    
    private int gridSize=Constant.gridCellNum;
    private final int totalShips = Constant.totalShips;       
    private Controller mainController;
    
    public final int[] ShipsSizeOrderedList = Constant.SHIPS_SIZE;
    public int iniShipsToPlace = totalShips;

    // Initialize the grid map for player side
    public GridMap playerMapView = new GridMap(false, event -> {
        if (mainController.isPlayer2Turn){
            return;
        }
        GridBox selectedGridBox = (GridBox) event.getSource();

        if (this.playerMapView.TryToPlaceShipOnMap(new Ship(ShipsSizeOrderedList[iniShipsToPlace-1], 
                event.getButton() == MouseButton.PRIMARY), 
                selectedGridBox.pos_x, selectedGridBox.pos_y)) {
            
            if (--iniShipsToPlace == 0) {
                /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                        + "             Player's move             "));
                */
                mainController.mainWindowView.displayMessage(3);
                
                ComputerPlaceShipsAutomatically();
            }
        }

    },gridSize);
    /*
    public Player(IPlayerModel model, IPlayerView view){
        this.model = model;
        this.view = view;
    }
    */
    public Player(Controller injectedController){
        this.mainController = injectedController;
        this.gridSize = injectedController.gridCellNum;  
    }
    
    @Override
    public Tuple NextMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public int GetNumberOfShips() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    /*
    @Override
    public IPlayerModel GetModel() {
        return this.model;
    }

    @Override
    public IPlayerView GetView() {
        return this.view;
    }
    */
    public void ComputerPlaceShipsAutomatically() {
        
        int numOfShips = ShipsSizeOrderedList.length;
        
        while (numOfShips > 0) {

            Tuple possibleCoordinate = mainController.ai.NextMove();
                       
            Ship ship = new Ship(ShipsSizeOrderedList[numOfShips-1] , Math.random() < 0.5 );
            
            if (mainController.computerMapView.TryToPlaceShipOnModel(ship, possibleCoordinate.t1, possibleCoordinate.t2)) {
                numOfShips--;
            }
        }
        
        mainController.computerMapView.ApplyModelToGridMap();
        
        mainController.isPlayer2Turn = true;
        mainController.computerMapView.finishIni = true;
    }
    
}
