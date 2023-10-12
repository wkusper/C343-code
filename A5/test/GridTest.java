import exceptions.NoPathE;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class GridTest {

    @Test
    void grid1 () throws IOException, NoPathE {
        Grid grid = Grid.fromFile("grid1.txt");

        grid.clearCache();
        Car car = new Car(new Cell(0, 0), 0, 0);
        PList<Cell> path = grid.computePathH(car);
        System.out.println(path);
        grid.printPath(path);
    }

    /*
(0,0), (1,0), (3,0)
* * . *
. . . .
     */

    @Test
    void grid2 () throws IOException, NoPathE {
        Grid grid = Grid.fromFile("grid2.txt");

        grid.clearCache();
        Car car = new Car(new Cell(0,7), 0, 0);
        PList<Cell> path = grid.computePathH(car);
        System.out.println(path);
        grid.printPath(path);
    }


    /*
    (0,7), (1,7), (3,7), (6,7), (10,7), (15,7), (20,7), (25,6), (29,5), (32,3), (34,0)
                                                                E E *
                                                                . . .
                                                                . . .
                                                                * . .
                                                                . . .
S . . . . . . . . . . . . . . . . . . . . . . . . . . . . * . . . . .
S . . . . . . . . . . . . . . . . . . . . . . . . * . . . . . . . . .
* * . * . . * . . . * . . . . * . . . . * . . . . . . . . . . . . . .
S . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
        S . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                S . . . . . . . . . . . . . . . . . . . . . . . . . .
                        S . . . . . . . . . . . . . . . . . . . . . .

     */

    @Test
    void race () throws IOException {
        Grid grid = Grid.fromFile("grid2.txt");
        grid.race();
    }

    /*
                                                                    E E *
                                                                . . .
                                                                . . .
                                                                . . .
                                                                * . .
S . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
S . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
S . . . . . . . . . . . . . . . . . . . . . . . . . . . . * . . . . .
S . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
        S . . . . . . . . . . . . . . . . . . . . * . . . . . . . . .
                S . . . . . . . . . . . . * . . . . . . . . . . . . .
                        * * . * . . * . . . . . . . . . . . . . . . .

     */
    @Test
    void grid3 () throws IOException, NoPathE {
        Grid grid = Grid.fromFile("grid3.txt");

        grid.clearCache();
        Car car = new Car(new Cell(0,0), 0, 0);
        PList<Cell> path = grid.computePathH(car);
        System.out.println(path);
        grid.printPath(path);
    }
    // * * -
    // - - *
    // - - *

    @Test
    void grid4 () throws IOException, NoPathE {
        Grid grid = Grid.fromFile("grid4.txt");

        grid.clearCache();
        Car car = new Car(new Cell(0,0), 0, 0);
        PList<Cell> path = grid.computePathH(car);
        System.out.println(path);
        grid.printPath(path);
        // - - - - -
        // * * * . *
        // - - - - -
    }
}

