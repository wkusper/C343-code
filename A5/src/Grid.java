import exceptions.NoPathE;
import exceptions.OutsideGridException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Grid {
    private final Optional<Cell>[][] grid; // indexed by [x][y]
    private final ArrayList<Cell> start;
    private final ArrayList<Cell> end;
    private final int columns;
    private final int rows;
    private HashMap<Car,PList<Cell>> hm;

    static Grid fromFile (String filename) throws IOException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            int columns = scanner.nextInt();
            int rows = scanner.nextInt();
            ArrayList<Cell> start = new ArrayList<>();
            ArrayList<Cell> end = new ArrayList<>();
            ArrayList<Cell> cells = new ArrayList<>();
            int x = 0, y = 0;
            while (scanner.hasNext()) {
                switch (scanner.next().charAt(0)) {
                    case 'S' -> start.add(new Cell(x,y));
                    case 'E' -> end.add(new Cell(x,y));
                    case '.' -> cells.add(new Cell(x, y));
                }
                x++;
                if (x == columns) {
                    x = 0;
                    y++;
                }
            }
            return new Grid(columns, rows, start, end, cells);
        }
    }
    @SuppressWarnings("unchecked")
    Grid (int columns, int rows, ArrayList<Cell> start, ArrayList<Cell> end, ArrayList<Cell> cells) {
        this.columns = columns; // indexed by x
        this.rows = rows; // indexed y
        this.start = start;
        this.end = end;
        grid = (Optional<Cell>[][]) Array.newInstance(Optional.class, columns, rows);
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                grid[x][y] = Optional.empty();
            }
        }
        for (Cell cell : start) {
            grid[cell.x()][cell.y()] = Optional.of(cell);
        }
        for (Cell cell : end) {
            grid[cell.x()][cell.y()] = Optional.of(cell);
        }
        for (Cell cell : cells) {
            grid[cell.x()][cell.y()] = Optional.of(cell);
        }
    }
    Cell at (int x, int y) throws OutsideGridException {
        if (x < 0 || x >= columns || y < 0 || y >= rows) throw new OutsideGridException(x,y);
        return grid[x][y].orElseThrow(() -> new OutsideGridException(x,y));
    }

    ArrayList<Car> newCars () {
        ArrayList<Car> cars = new ArrayList<>();
        for (Cell cell : start) {
            cars.add(new Car(cell, 0, 0));
        }
        return cars;
    }

    void clearCache () {
        hm = new HashMap<>();
    }

    boolean validMove (int deltax, int deltay) {
        if (deltax == 0 && deltay == 0) return false; // car must move
        return deltax >= 0; // car must move forward
    }
    PList<Cell> computePathH (Car car) throws NoPathE {
        if (hm.containsKey(car)) {
            return hm.get(car);
        }

        for (Cell e : end) {
            if (car.getPosition().equals(e)) {
                return new NEmptyPList<>(e, new EmptyPList<>());
            }
        }

        PList<Cell> shortestPath = null;

        int[] velocityChanges = {0, -1, 1};

        for (int dx : velocityChanges) {
            for (int dy : velocityChanges) {
                int newDeltax = car.getDeltax() + dx;
                int newDeltay = car.getDeltay() + dy;

                if (validMove(newDeltax, newDeltay)) {
                    int newX = car.getPosition().x() + newDeltax;
                    int newY = car.getPosition().y() + newDeltay;

                    try {
                        Cell newCell = at(newX, newY);

                        if (!(newCell.toString().equals(" "))) {
                            Car newCar = new Car(newCell, newDeltax, newDeltay);
                            try {
                                PList<Cell> path = computePathH(newCar);
                                path = new NEmptyPList<>(car.getPosition(), path);

                                if (shortestPath == null || path.size() < shortestPath.size()) {
                                    shortestPath = path;
                                }
                            } catch (NoPathE ignored) {}
                        }
                    } catch (OutsideGridException ignored) {}
                }
            }
        }

        if (shortestPath == null) {
            throw new NoPathE();
        }

        hm.put(car, shortestPath);

        return shortestPath;
    }

    void race () {
        ArrayList<Car> cars = newCars();
        Optional<PList<Cell>> path = Optional.empty();
        for (Car car : cars) {
            try {
                clearCache();
                PList<Cell> p = computePathH(car);
                if (path.isEmpty() || p.size() < path.get().size()) {
                    path = Optional.of(p);
                }
            } catch (NoPathE e) {
            }
        }
        path.ifPresentOrElse(
                this::printPath,
                () -> System.out.println("No path found")
        );
    }

    public void printPath (PList<Cell> path) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                grid[x][y].ifPresentOrElse(
                        cell -> {
                            if (path.contains(cell)) {
                                sb.append('*');
                            } else if (start.contains(cell)) {
                                sb.append('S');
                            } else if (end.contains(cell)) {
                                sb.append('E');
                            } else {
                                sb.append('.');
                            }
                        },
                        () -> sb.append(' ')
                );
                sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d %d\n", columns, rows));
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                grid[x][y].ifPresentOrElse(
                        cell -> {
                            if (start.contains(cell)) {
                                sb.append('S');
                            } else if (end.contains(cell)) {
                                sb.append('E');
                            } else {
                                sb.append('.');
                            }
                        },
                        () -> sb.append(' ')
                );
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
