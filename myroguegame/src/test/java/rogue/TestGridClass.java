package rogue;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestGridClass {

    private Grid<Character> grid;

    @Before
    public void setup(){
        grid = new Grid<Character>(new Dimension(5,5),'.');
    }


    @Test
    public void testGrid(){
        for (int i = 0 ; i < 5 ; i++){
            grid.set(i,i,'*');
        }


    }

    @Test
    public void testSetOccupyWidthLarger(){
        grid.occupy(new Coordination(1,1),new Dimension(4,2),'+');

    }

    @Test
    public void testSetOccupyHeightLarger(){
        grid.occupy(new Coordination(1,1),new Dimension(2,4),'+');

    }


    @Test
    public void testIsOccupy(){
        grid.occupy(new Coordination(0,0),new Dimension(3,3),'+');

        boolean response = grid.isAvailable(new Coordination(0,0),new Dimension(2,2),'+');
        Assert.assertFalse(response);

        response = grid.isAvailable(new Coordination(1,1),new Dimension(2,2),'+');
        Assert.assertFalse(response);

        response = grid.isAvailable(new Coordination(2,2),new Dimension(2,2),'+');
        Assert.assertFalse(response);

        response = grid.isAvailable(new Coordination(0,2),new Dimension(2,2),'+');
        Assert.assertFalse(response);

        response = grid.isAvailable(new Coordination(2,0),new Dimension(2,2),'+');
        Assert.assertFalse(response);

        response = grid.isAvailable(new Coordination(4,0),new Dimension(2,2),'+');
        Assert.assertFalse(response);

        response = grid.isAvailable(new Coordination(0,4),new Dimension(2,2),'+');
        Assert.assertFalse(response);


        response = grid.isAvailable(new Coordination(3,0),new Dimension(2,2),'+');
        Assert.assertTrue(response);

        response = grid.isAvailable(new Coordination(0,3),new Dimension(2,2),'+');
        Assert.assertTrue(response);

        response = grid.isAvailable(new Coordination(3,3),new Dimension(2,2),'+');
        Assert.assertTrue(response);

    }

    @Test
    public void testIsOccupyWithLargeSize(){
        boolean response = grid.isAvailable(new Coordination(2,2), new Dimension(10,10),'x');
        Assert.assertFalse(response);
    }


    @Test
    public void testIterator(){
        List<Coordination> coordList = new ArrayList<>();
        while (grid.hasNext()){
            Coordination coords = grid.next();
            coordList.add(coords);
        }
        coordList.forEach(System.out::println);
        Assert.assertEquals(25,coordList.size());
    }


    @After
    public void teardown(){
        System.out.println("");
        System.out.println("Printing the grid");
        grid.print(new StdoutGridPrinter<Character>());
    }



}
