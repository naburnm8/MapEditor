import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Testing {
    @Test
    public void mapCreationTest() throws IOException {
        String input = "-create testing.map\n10 10\n-save\n";
        InputStream newStream = new ByteArrayInputStream(input.getBytes());
        MapEditor.mainStream(null, newStream);
        MapData expected = new MapData(10,10);
        MapReader rd = new MapReader("testing.map");
        MapData actual = rd.read();
        assertEquals(expected,actual);
        File test = new File("testing.map");
        test.delete();
    }
    @Test
    public void newObstaclesTest() throws IOException {
        String input = "-create testing.map\n10 10\n-addObstacle J 2.0 2.5 2.3\n-point 0 0 5\n-save\n";
        InputStream newStream = new ByteArrayInputStream(input.getBytes());
        MapEditor.mainStream(null, newStream);
        MapReader rd_expected = new MapReader("testingResources/junitNewObstaclesTest.map");
        MapReader rd_actual = new MapReader("testing.map");
        assertEquals(rd_expected.read(), rd_actual.read());
        File test = new File("testing.map");
        test.delete();
    }
    @Test
    public void editingTest() throws IOException {
        String input = "-load testingResources/junitEditingTestBase.map\n-point 6 0 4\n-save\ntesting.map\n";
        InputStream newStream = new ByteArrayInputStream(input.getBytes());
        MapEditor.mainStream(null, newStream);
        MapReader rd_expected = new MapReader("testingResources/junitEditingTest.map");
        MapReader rd_actual = new MapReader("testing.map");
        assertEquals(rd_expected.read(), rd_actual.read());
        File test = new File("testing.map");
        test.delete();
    }
    @Test
    public void loadTest() throws IOException {
        ArrayList<Obstacle> obstacles = Obstacle.generateDefaultObstacles();
        char[][] mapLayout = {{'░','▒','▒','▒','▒'},
                              {'▒','▒','▒','▒','▒'},
                              {'▒','▒','▒','▒','▒'},
                              {'▒','▒','▒','▒','▒'},
                              {'▒','▒','▒','▒','▒'}};
        MapData expected = new MapData(5,5,mapLayout,obstacles);
        MapReader rd_actual = new MapReader("testingResources/junitLoadTest.map");
        assertEquals(expected, rd_actual.read());
    }
}
