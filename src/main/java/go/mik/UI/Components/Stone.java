package go.mik.UI.Components;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Stone {
    private int _x,_y,_width,_height;
    private String _stoneColour;

    public Stone(int x, int y, int width,int height,String colour){
        _x = x;
        _y = y;
        _width = width;
        _height = height;
        _stoneColour = colour;
    }

    public int get_x() {
        return _x;
    }

    public int get_height() {
        return _height;
    }

    public int get_width() {
        return _width;
    }

    public int get_y() {
        return _y;
    }

    public String get_stoneColour() {
        return _stoneColour;
    }
}
