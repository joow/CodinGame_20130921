package org.joow.codingame1;

import java.util.*;

class Solution {
    private static final char START_CHAR = '@';

    private static final char FRONTIER_CHAR = '#';

    private static final char GOAL_CHAR = '$';

    private static final char BARRIER_CHAR = 'X';

    private static final char[] DIRECTIONS_MODIFIER = {'S', 'E', 'N', 'W'};

    private static final char BEER_CHAR = 'B';

    private static final char INVERTER_CHAR = 'I';

    private static final char TELEPORTER_CHAR = 'T';

    private static final int SOUTH_DIRECTION = 0;

    private static final int EAST_DIRECTION = 1;

    private static final int NORTH_DIRECTION = 2;

    private static final int WEST_DIRECTION = 3;

    private static final String INFINITE_LOOP = "LOOP";

    private int[] DIRECTIONS = {SOUTH_DIRECTION, EAST_DIRECTION, NORTH_DIRECTION, WEST_DIRECTION};

    private static final String[] TEXT_DIRECTIONS = {"SOUTH", "EAST", "NORTH", "WEST"};

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final int MOVES_LIMIT = 500;

    private final StringBuilder path = new StringBuilder();

    private int currentRow;

    private int currentColumn;

    private int currentDirection = SOUTH_DIRECTION;

    private boolean breakerMode;

    private boolean invertedMode;

    private int teleporter1Row;

    private int teleporter1Column;

    private int teleporter2Row;

    private int teleporter2Column;

    private int moves;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        final int rows = in.nextInt();
        final int columns = in.nextInt();
        in.nextLine();
        final String[] lines = new String[rows];

        for (int i = 0; i < rows; i++) {
            lines[i] = in.nextLine();
        }

        final Solution solution = new Solution();
        final char[][] townMap = solution.createTownMap(rows, columns, lines);

        System.out.println(solution.solve(townMap));
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

    public String solve(char[][] townMap) {
        findStartingPosition(townMap);
        findTeleportersPosition(townMap);

        while (!isSolved(townMap)) {
            nextMove(townMap);
            moves++;
        }

        if (moves > MOVES_LIMIT) {
            return INFINITE_LOOP;
        }

        return path.toString();
    }

    private boolean isSolved(char[][] townMap) {
        if (getCharacterAtCurrentPosition(currentRow, currentColumn, townMap) == GOAL_CHAR) {
            return true;
        }

        return moves > MOVES_LIMIT;
    }

    private void nextMove(char[][] townMap) {
        final char charAtCurrentPosition = getCharacterAtCurrentPosition(currentRow, currentColumn, townMap);
        if (isDirectionModifier(charAtCurrentPosition)) {
            currentDirection = DIRECTIONS[indexOfCharInArray(charAtCurrentPosition, DIRECTIONS_MODIFIER)];
        } else if (isBeer(charAtCurrentPosition)) {
            breakerMode = !breakerMode;
        } else if (isInverter(charAtCurrentPosition)) {
            invertedMode = !invertedMode;
        } else if (isTeleporter(charAtCurrentPosition)) {
            gotoNextTeleporter();
        }

        int nextRow;
        int nextColumn;
        boolean firstBlock = true;
        boolean isBlocked;

        do {
            nextRow = determineNextRow();
            nextColumn = determineNextColumn();
            isBlocked = isBlocked(nextRow, nextColumn, townMap);

            if (isBlocked) {
                currentDirection = nextDirection(currentDirection, firstBlock);

                if (firstBlock) {
                    firstBlock = false;
                }
            }
        } while (isBlocked);

        currentRow = nextRow;
        currentColumn = nextColumn;
        path.append(TEXT_DIRECTIONS[currentDirection]).append(LINE_SEPARATOR);
    }

    private void gotoNextTeleporter() {
        if (currentRow == teleporter1Row && currentColumn == teleporter1Column) {
            currentRow = teleporter2Row;
            currentColumn = teleporter2Column;
        } else {
            currentRow = teleporter1Row;
            currentColumn = teleporter1Column;
        }
    }

    private boolean isTeleporter(char charAtCurrentPosition) {
        return charAtCurrentPosition == TELEPORTER_CHAR;
    }

    private int nextDirection(int currentDirection, boolean firstBlock) {
        if (firstBlock) {
            if (invertedMode) {
                return DIRECTIONS[DIRECTIONS.length - 1];
            } else {
                return DIRECTIONS[0];
            }
        } else {
            if (invertedMode) {
                return currentDirection - 1;
            } else {
                return currentDirection + 1;
            }
        }
    }

    private boolean isInverter(char charAtCurrentPosition) {
        return charAtCurrentPosition == INVERTER_CHAR;
    }

    private boolean isBeer(char charAtCurrentPosition) {
        return charAtCurrentPosition == BEER_CHAR;
    }

    private boolean isDirectionModifier(char charAtCurrentPosition) {
        return indexOfCharInArray(charAtCurrentPosition, DIRECTIONS_MODIFIER) > -1;
    }

    private int indexOfCharInArray(char character, char[] array) {
        for (int i = 0; i < DIRECTIONS_MODIFIER.length; i++) {
            if (character == DIRECTIONS_MODIFIER[i]) {
                return i;
            }
        }

        return -1;
    }

    private boolean isBlocked(int row, int column, char[][] townMap) {
        final char charAtPosition = getCharacterAtCurrentPosition(row, column, townMap);

        if (charAtPosition == BARRIER_CHAR && breakerMode) {
            townMap[row][column] = ' ';
            return false;
        }

        return charAtPosition == FRONTIER_CHAR || charAtPosition == BARRIER_CHAR;
    }

    private int determineNextRow() {
        switch (currentDirection) {
            case NORTH_DIRECTION:
                return currentRow - 1;
            case SOUTH_DIRECTION:
                return currentRow + 1;
            case WEST_DIRECTION:
            case EAST_DIRECTION:
                return currentRow;
            default:
                return currentRow;
        }
    }

    private int determineNextColumn() {
        switch (currentDirection) {
            case NORTH_DIRECTION:
            case SOUTH_DIRECTION:
                return currentColumn;
            case WEST_DIRECTION:
                return currentColumn - 1;
            case EAST_DIRECTION:
                return currentColumn + 1;
            default:
                return currentColumn;
        }
    }

    private char getCharacterAtCurrentPosition(int nextRow, int nextColumn, char[][] townMap) {
        return townMap[nextRow][nextColumn];
    }

    private void findStartingPosition(char[][] townMap) {
        for (int row = 0; row < townMap.length; row++) {
            for (int column = 0; column < townMap[row].length; column++) {
                if (START_CHAR == townMap[row][column]) {
                    currentRow = row;
                    currentColumn = column;
                    return;
                }
            }
        }

        throw new IllegalArgumentException("The given town map doesn't contain a start point.");
    }

    private void findTeleportersPosition(char[][] townMap) {
        int teleporterCount = 1;
        for (int row = 0; row < townMap.length; row++) {
            for (int column = 0; column < townMap[row].length; column++) {
                if (TELEPORTER_CHAR == townMap[row][column]) {
                    switch (teleporterCount) {
                        case 1:
                            teleporter1Row = row;
                            teleporter1Column = column;
                            teleporterCount++;
                            break;
                        case 2:
                            teleporter2Row = row;
                            teleporter2Column = column;
                            return;
                        default:
                            break;
                    }
                }
            }
        }
    }
}