/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerInterface;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author styma01
 */
public class BarInterfaceHandler {
    private BarWrapperBottom barWrapperBottom;
    private BarWrapperTop barWrapperTop;
    private GraphicsContext graphicsContext;

    public BarInterfaceHandler(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        barWrapperBottom = new BarWrapperBottom(graphicsContext);
        barWrapperTop = new BarWrapperTop(graphicsContext);
    }
    
    public void paintInterface(){
        barWrapperBottom.paintBottomWrapper();
        barWrapperTop.paintTopWrapper();
    }
    
}
