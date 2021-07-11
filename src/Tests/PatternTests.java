package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Patterns.Stripe;
import Models.Tuples.Color;
import Models.Tuples.Point;

public class PatternTests {

    @Test
    @DisplayName("constructor")
    public void constructor() {

        Stripe s = new Stripe(new Color[] { Color.WHITE, Color.BLACK });

        assertEquals(true, s.getColors().get(0).equals(Color.WHITE), "Pattern Colors are not set correctly correctly");
        assertEquals(true, s.getColors().get(1).equals(Color.BLACK), "Pattern Colors are not set correctly correctly");

    }

    @Test
    @DisplayName("A stripe pattern is constant in y")
    public void patternConstantY() {

        Stripe s = new Stripe(new Color[] { Color.WHITE, Color.BLACK });

        assertEquals(true, s.stripeAt(new Point(0, 0, 0)).equals(Color.WHITE),
                "Pattern Colors are not set correctly correctly");

        assertEquals(true, s.stripeAt(new Point(0, 1, 0)).equals(Color.WHITE),
                "Pattern Colors are not set correctly correctly");

        assertEquals(true, s.stripeAt(new Point(0, 2, 0)).equals(Color.WHITE),
                "Pattern Colors are not set correctly correctly");

    }

    @Test
    @DisplayName("A stripe pattern is constant in z")
    public void patternConstantZ() {

        Stripe s = new Stripe(new Color[] { Color.WHITE, Color.BLACK });

        assertEquals(true, s.stripeAt(new Point(0, 0, 0)).equals(Color.WHITE),
                "Pattern Colors are not set correctly correctly");

        assertEquals(true, s.stripeAt(new Point(0, 0, 1)).equals(Color.WHITE),
                "Pattern Colors are not set correctly correctly");

        assertEquals(true, s.stripeAt(new Point(0, 0, 2)).equals(Color.WHITE),
                "Pattern Colors are not set correctly correctly");

    }

    @Test
    @DisplayName("A stripe pattern is constant in x")
    public void patternConstantX() {

        Stripe s = new Stripe(new Color[] { Color.WHITE, Color.BLACK });

        assertEquals(true, s.stripeAt(new Point(0, 0, 0)).equals(Color.WHITE),
                "Pattern Colors are not set correctly correctly");
        assertEquals(true, s.stripeAt(new Point(0.9, 0, 0)).equals(Color.WHITE),
                "Pattern Colors are not set correctly correctly");

        assertEquals(true, s.stripeAt(new Point(1, 0, 0)).equals(Color.BLACK),
                "Pattern Colors are not set correctly correctly");
        assertEquals(true, s.stripeAt(new Point(-0.1, 0, 0)).equals(Color.BLACK),
                "Pattern Colors are not set correctly correctly");
        assertEquals(true, s.stripeAt(new Point(-1, 0, 0)).equals(Color.BLACK),
                "Pattern Colors are not set correctly correctly");

        assertEquals(true, s.stripeAt(new Point(-1.1, 0, 0)).equals(Color.WHITE),
                "Pattern Colors are not set correctly correctly");
    }
}