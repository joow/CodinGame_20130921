package org.joow.codingame1;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SolutionTest {
    private Solution solution;

    @BeforeMethod
    private void initializeClassUnderTest() {
        solution = new Solution();
    }

    private char[][] createTownMap(final int rows, final int columns, final String... lines) {
        final char[][] townMap = new char[rows][columns];
        for (int row = 0; row < rows; row++) {
            townMap[row] = createTownMapLine(columns, lines[row]);
        }

        return townMap;
    }

    private char[] createTownMapLine(int columns, String line) {
        final char[] townMapLine = new char[columns];
        for (int column = 0; column < columns; column++) {
            townMapLine[column] = line.charAt(column);
        }

        return townMapLine;
    }

    @Test
    public void test1() {
        final char[][] townMap = createTownMap(5, 5, "#####", "#@  #", "#   #", "#  $#", "#####");
        assertEquals("SOUTH\nSOUTH\nEAST\nEAST\n", solution.solve(townMap));
    }

    @Test
    public void test2() {
        final char[][] townMap = createTownMap(8, 8, "########", "# @    #", "#     X#", "# XXX  #", "#   XX #", "#   XX #", "#     $#", "########");

        assertEquals("SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "SOUTH\n" +
                "EAST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n", solution.solve(townMap));
    }

    @Test
    public void test3() {
        final char[][] townMap = createTownMap(8, 8, "########", "#     $#", "#      #", "#      #", "#  @   #", "#      #", "#      #", "########");

        assertEquals("SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n", solution.solve(townMap));
    }

    @Test
    public void test4() {
        final char[][] townMap = createTownMap(8, 8, "########", "#      #", "# @    #", "# XX   #", "#  XX  #", "#   XX #", "#     $#", "########");

        assertEquals("EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n", solution.solve(townMap));
    }

    @Test
    public void test5() {
        final char[][] townMap = createTownMap(10, 10, "##########", "#        #", "#  S   W #", "#        #", "#  $     #", "#        #", "#@       #", "#        #", "#E     N #", "##########");

        assertEquals("SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n", solution.solve(townMap));
    }

    @Test
    public void test6() {
        final char[][] townMap = createTownMap(10, 10, "##########", "# @      #", "# B      #", "#XXX     #", "# B      #", "#    BXX$#", "#XXXXXXXX#", "#        #", "#        #", "##########");

        assertEquals("SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n", solution.solve(townMap));
    }

    @Test
    public void test7() {
        final char[][] townMap = createTownMap(10, 10, "##########", "#    I   #", "#        #", "#       $#", "#       @#", "#        #", "#       I#", "#        #", "#        #", "##########");

        assertEquals("SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "SOUTH\n" +
                "SOUTH\n", solution.solve(townMap));
    }

    @Test
    public void testTeleporter() {
        final char[][] townMap = createTownMap(10, 10, "##########", "#    T   #", "#        #",  "#        #", "#        #", "#@       #", "#        #", "#        #", "#    T  $#", "##########");

        assertEquals("SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n", solution.solve(townMap));
    }

    @Test
    public void testBrokenWall() {
        final char[][] townMap = createTownMap(10, 10, "##########", "#        #", "#  @     #", "#  B     #", "#  S   W #", "# XXX    #", "#  B   N #", "# XXXXXXX#", "#       $#", "##########");

        assertEquals("SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n", solution.solve(townMap));
    }

    @Test
    public void testAllTogether() {
        final char[][] townMap = createTownMap(15, 15, "###############", "#      IXXXXX #", "#  @          #", "#             #", "#             #", "#  I          #", "#  B          #", "#  B   S     W#", "#  B   T      #", "#             #", "#         T   #", "#         B   #", "#            $#", "#        XXXX #", "###############");

        assertEquals("SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "WEST\n" +
                "WEST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n", solution.solve(townMap));
    }

    @Test
    public void testInfiniteLoop() {
        final char[][] townMap = createTownMap(15, 15, "###############", "#      IXXXXX #", "#  @          #", "#E S          #", "#             #", "#  I          #", "#  B          #", "#  B   S     W#", "#  B   T      #", "#             #", "#         T   #", "#         B   #", "#N          W$#", "#        XXXX #", "###############");

        assertEquals("LOOP", solution.solve(townMap));
    }

    @Test
    public void testMulitpleLoops() {
        final char[][] townMap = createTownMap(30, 15, "###############", "#  #@#I  T$#  #", "#  #    IB #  #", "#  #     W #  #", "#  #      ##  #", "#  #B XBN# #  #", "#  ##      #  #", "#  #       #  #", "#  #     W #  #", "#  #      ##  #", "#  #B XBN# #  #", "#  ##      #  #", "#  #       #  #", "#  #     W #  #", "#  #      ##  #", "#  #B XBN# #  #", "#  ##      #  #", "#  #       #  #", "#  #       #  #", "#  #      ##  #", "#  #  XBIT #  #", "#  #########  #", "#             #", "# ##### ##### #", "# #     #     #", "# #     #  ## #", "# #     #   # #", "# ##### ##### #", "#             #", "###############");

        assertEquals("SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n", solution.solve(townMap));
    }
}
