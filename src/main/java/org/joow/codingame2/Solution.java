package org.joow.codingame2;

import java.util.*;

class Solution {
    class Room {
        private static final String EXIT = "E";

        private final int number;

        private final int moneyAmount;

        private final String nextRoom1;

        private final String nextRoom2;

        private boolean visited;

        Room(final String room) {
            final StringTokenizer stringTokenizer = new StringTokenizer(room);

            number = Integer.parseInt(stringTokenizer.nextToken());
            moneyAmount = Integer.parseInt(stringTokenizer.nextToken());
            nextRoom1 = stringTokenizer.nextToken();
            nextRoom2 = stringTokenizer.nextToken();
        }

        int getNumber() {
            return number;
        }

        int getMoneyAmount() {
            return moneyAmount;
        }

        Integer getNextRoom1() {
            return Integer.parseInt(nextRoom1);
        }

        Integer getNextRoom2() {
            return Integer.parseInt(nextRoom2);
        }

        boolean isNextRoom1ARoom() {
            return isNotExit(nextRoom1);
        }

        boolean isNextRoom2ARoom() {
            return isNotExit(nextRoom2);
        }

        private boolean isNotExit(String nextRoom) {
            return !EXIT.equals(nextRoom);
        }

        void visit() {
            visited = true;
        }

        void unvisit() {
            visited = false;
        }

        boolean isVisited() {
            return visited;
        }
    }

    public static void main(String args[]) {
        final Scanner in = new Scanner(System.in);
        final int nbRooms = in.nextInt();
        in.nextLine();
        final String[] rooms = new String[nbRooms];

        for (int i = 0; i < nbRooms; i++) {
            rooms[i] = in.nextLine();
        }

        final Solution solution = new Solution();

        System.out.println(solution.solve(nbRooms, rooms));
    }

    private Map<Integer, Room> createBuilding(int nbRooms, String... rooms) {
        final Map<Integer, Room> building = new HashMap<>();

        for (int i = 0; i < nbRooms; i++) {
            final Room room = new Room(rooms[i]);
            building.put(room.getNumber(), room);
        }

        return building;
    }

    public int solve(int nbRooms, String... rooms) {
        final Map<Integer, Room> building = createBuilding(nbRooms, rooms);
        return solve(building.get(0), building);
    }

    private int solve(Room currentRoom, Map<Integer, Room> building) {
        if (currentRoom.isVisited()) {
            return 0;
        }

        currentRoom.visit();
        int moneyCollected = currentRoom.getMoneyAmount();
        int moneyCollectedInNextRoom1 = 0;
        int moneyCollectedInNextRoom2 = 0;

        if (currentRoom.isNextRoom1ARoom()) {
            moneyCollectedInNextRoom1 = visitNextRoom(currentRoom.getNextRoom1(), building);
        }

        if (currentRoom.isNextRoom2ARoom()) {
            moneyCollectedInNextRoom2 = visitNextRoom(currentRoom.getNextRoom2(), building);
        }

        moneyCollected += Math.max(moneyCollectedInNextRoom1, moneyCollectedInNextRoom2);

        return moneyCollected;
    }

    private int visitNextRoom(final Integer nextRoomNumber, Map<Integer, Room> building) {
        final Room nextRoom = building.get(nextRoomNumber);
        final int moneyCollectedInNextRoom = solve(nextRoom, building);
        nextRoom.unvisit();

        return moneyCollectedInNextRoom;
    }
}
