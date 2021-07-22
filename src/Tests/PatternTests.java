package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Matrix;
import Models.Patterns.Gradient;
import Models.Patterns.Pattern;
import Models.Patterns.Ring;
import Models.Patterns.Checker;
import Models.Patterns.Stripe;
import Models.Tuples.Color;
import Models.Tuples.Point;

import Models.Shapes.Shape;
import Models.Shapes.Sphere;

public class PatternTests {

        @Test
        @DisplayName("constructor")
        public void constructor() {

                Stripe s = new Stripe(new Color[] { Color.WHITE, Color.BLACK });

                assertEquals(true, s.getFirstColor().equals(Color.WHITE), "Pattern Colors are not set correctly");
                assertEquals(true, s.getSecondColor().equals(Color.BLACK), "Pattern Colors are not set correctly");

        }

        @Test
        @DisplayName("A stripe pattern is constant in y")
        public void patternConstantY() {

                Stripe s = new Stripe(new Color[] { Color.WHITE, Color.BLACK });

                assertEquals(true, s.colorAt(new Point(0, 0, 0)).equals(Color.WHITE),
                                "Pattern Colors are not set correctly");

                assertEquals(true, s.colorAt(new Point(0, 1, 0)).equals(Color.WHITE),
                                "Pattern Colors are not set correctly");

                assertEquals(true, s.colorAt(new Point(0, 2, 0)).equals(Color.WHITE),
                                "Pattern Colors are not set correctly");

        }

        @Test
        @DisplayName("A stripe pattern is constant in z")
        public void patternConstantZ() {

                Stripe s = new Stripe(new Color[] { Color.WHITE, Color.BLACK });

                assertEquals(true, s.colorAt(new Point(0, 0, 0)).equals(Color.WHITE),
                                "Pattern Colors are not set correctly");

                assertEquals(true, s.colorAt(new Point(0, 0, 1)).equals(Color.WHITE),
                                "Pattern Colors are not set correctly");

                assertEquals(true, s.colorAt(new Point(0, 0, 2)).equals(Color.WHITE),
                                "Pattern Colors are not set correctly");

        }

        @Test
        @DisplayName("A stripe pattern is constant in x")
        public void patternConstantX() {

                Stripe s = new Stripe(new Color[] { Color.WHITE, Color.BLACK });

                assertEquals(true, s.colorAt(new Point(0, 0, 0)).equals(Color.WHITE),
                                "Pattern Colors are not set correctly");
                assertEquals(true, s.colorAt(new Point(0.9, 0, 0)).equals(Color.WHITE),
                                "Pattern Colors are not set correctly");

                assertEquals(true, s.colorAt(new Point(1, 0, 0)).equals(Color.BLACK),
                                "Pattern Colors are not set correctly");
                assertEquals(true, s.colorAt(new Point(-0.1, 0, 0)).equals(Color.BLACK),
                                "Pattern Colors are not set correctly");
                assertEquals(true, s.colorAt(new Point(-1, 0, 0)).equals(Color.BLACK),
                                "Pattern Colors are not set correctly");

                assertEquals(true, s.colorAt(new Point(-1.1, 0, 0)).equals(Color.WHITE),
                                "Pattern Colors are not set correctly");
        }

        @Test
        @DisplayName("Lighting with the surface in shadow")
        public void lightingInShadow() {

                Shape s = new Sphere();
                s.setTransform(Matrix.IDENTITY.scale(2, 2, 2));
                Stripe p = new Stripe();
                s.getMaterial().setPattern(p);

                assertEquals(true, p.colorAt(s, new Point(1.5, 0, 0)).equals(Color.WHITE),
                                "Strip colorAt method not implemented correctly");

                p.setTransform(Matrix.IDENTITY.scale(2, 2, 2));

                assertEquals(true, p.colorAt(s, new Point(1.5, 0, 0)).equals(Color.WHITE),
                                "Strip colorAt method not implemented correctly");

                p.setTransform(Matrix.IDENTITY.translate(0.5, 0, 0));
                s.setTransform(Matrix.IDENTITY.scale(2, 2, 2));

                assertEquals(true, p.colorAt(s, new Point(2.5, 0, 0)).equals(Color.WHITE),
                                "Strip colorAt method not implemented correctly");

        }

        @Test
        @DisplayName("Lighting with the surface in shadow")
        public void gradientPattern() {

                Shape s = new Sphere();
                Pattern p = new Gradient();
                s.getMaterial().setPattern(p);

                assertEquals(true, p.colorAt(s, new Point(0, 0, 0)).equals(Color.WHITE),
                                "Gradient colorAt method not implemented correctly");

                assertEquals(true, p.colorAt(s, new Point(0.25, 0, 0)).equals(new Color(0.75, 0.75, 0.75)),
                                "Gradient colorAt method not implemented correctly");

                assertEquals(true, p.colorAt(s, new Point(0.5, 0, 0)).equals(new Color(0.5, 0.5, 0.5)),
                                "Gradient colorAt method not implemented correctly");

                assertEquals(true, p.colorAt(s, new Point(0.75, 0, 0)).equals(new Color(0.25, 0.25, 0.25)),
                                "Gradient colorAt method not implemented correctly");

        }

        @Test
        @DisplayName("Lighting with the surface in shadow")
        public void ringPattern() {

                Shape s = new Sphere();
                Pattern p = new Ring();
                s.getMaterial().setPattern(p);

                assertEquals(true, p.colorAt(s, new Point(0, 0, 0)).equals(Color.WHITE),
                                "Ring colorAt method not implemented correctly");

                assertEquals(true, p.colorAt(s, new Point(1, 0, 0)).equals(Color.BLACK),
                                "Ring colorAt method not implemented correctly");

                assertEquals(true, p.colorAt(s, new Point(0, 0, 1)).equals(Color.BLACK),
                                "Ring colorAt method not implemented correctly");

                assertEquals(true, p.colorAt(s, new Point(0.708, 0, 0.708)).equals(Color.BLACK),
                                "Ring colorAt method not implemented correctly");

        }

        @Test
        @DisplayName("Lighting with the surface in shadow")
        public void ring3DPattern() {

                Shape s = new Sphere();
                Pattern p = new Checker();
                s.getMaterial().setPattern(p);

                assertEquals(true, p.colorAt(s, new Point(0, 0, 0)).equals(Color.WHITE),
                                "Ring colorAt method not implemented correctly");

                assertEquals(true, p.colorAt(s, new Point(0, 0.99, 0)).equals(Color.WHITE),
                                "Ring colorAt method not implemented correctly");

                assertEquals(true, p.colorAt(s, new Point(0, 1.1, 0)).equals(Color.BLACK),
                                "Ring colorAt method not implemented correctly");

        }
}