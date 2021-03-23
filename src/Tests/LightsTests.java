package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Color;
import Models.PointLight;
import Models.Point;

public class LightsTests {

    @Test
    @DisplayName("Light construction")
    public void constructor() {

        PointLight light = new PointLight(new Color(1, 1, 1), new Point());

        assertEquals(true, light.getIntensity().equals(new Color(1, 1, 1)), "light constructor is not valid");
        assertEquals(true, light.getPosition().equals(new Point()), "light constructor is not valid");
    }

}
