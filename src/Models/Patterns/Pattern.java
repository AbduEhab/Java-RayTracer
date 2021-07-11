package Models.Patterns;

import java.util.ArrayList;

import Models.Tuples.Color;

abstract public class Pattern {

    private ArrayList<Color> colors;

    
    public Pattern(Color[] colors) {

        this.colors = new ArrayList<Color>();

        for (Color color : colors) {
            this.colors.add(color);
        }

    }

    public ArrayList<Color> getColors() {
        return colors;
    }

}
