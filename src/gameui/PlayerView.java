/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameui;

import api.IController;

/**
 *
 * @author zange
 */
public class PlayerView extends PlayerViewAbstract{

    public PlayerView(IController controller) {
        super(controller);

    }
    
     public void SimulateUserEnterTheValuesAndClickedStart(){
        
        //example to ilustrate that the player finish positioning the ships
        //and the view generates a matrix with the ships position. The user then
        //clicks start to send this information to the controller.
        
        this.player_map = new int[][]{ 
             {1,0,0,0,0,0,0,0,0,1,1,1},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,1,1},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {1,1,1,1,0,0,0,0,0,1,1,1},
         };
        
        this.OnButtonStartClicked();
    }
     
}
