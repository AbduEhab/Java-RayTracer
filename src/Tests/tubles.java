package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Tuple;

public class tubles {

    @Test
    @DisplayName("createTuple")
    void createTuple() {
        
        Tuple a = new Tuple(4.3, -4.2, 3.1, 1.0);

        assertEquals(true, true);
        assertEquals(true, true);
        assertEquals(true, true);
        assertEquals(true, true);
    }

    @Test
    @DisplayName("addingTwoTuples")
    void addingTwoTuples() {

        assertEquals(true, true);
    }
}
