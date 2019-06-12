package HW.seaBattle;

import java.util.Scanner;


public class GameUtil {

    private static String[] letters = {"0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

    public static void printBattleField(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");

    }

    public static void createNewBattleField(String[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                if (i == 0 && j == 0) {
                    arr[i][j] = " ";
                }
                if (i == 0 && j != 0) {
                    count++;
                    String s = Integer.toString(count);
                    arr[i][j] = s;
                }

                if (i != 0 && j == 0) {
                    arr[i][j] = letters[i];

                }
                if (i > 0 && j > 0) {
                    arr[i][j] = "~";
                }
            }
        }
    }

    public static int readUserInputForColumn() {
        int userInput = 0;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input >= 1 && input <= 10) {
                    userInput = input;
                    break;
                } else {
                    System.out.println("Please provide column number from 1 to 10");
                }

            }

        }
        return userInput;
    }

    public static int readUserInputForRow() {
        int userInput = 0;
        outer:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            for (int i = 0; i < letters.length; i++) {
                if (input.equals(letters[i])) {
                    userInput = i;
                    break outer;
                }
            }
            System.out.println(" Please, choose letter from a to j");
        }
        return userInput;
    }

    public static void placeUserShipMark(String arr[][], int row, int column) {
        arr[row][column] = "#";
    }

    public static void placeXSailingShip(String arr[][], int X) {
        int sailsNumber = X + 1;
        int rowStart;
        int columnStart;
        int rowEnd;
        int columnEnd;

        System.out.println("•Please, select where to place " + sailsNumber + " Sailing Ship. ");
        System.out.println("••Choose start point:");
        while (true) {
            System.out.println("•Choose row");
            rowStart = GameUtil.readUserInputForRow();

            System.out.println("•Choose column");
            columnStart = GameUtil.readUserInputForColumn();

            if (GameUtil.cellIsNotFree(arr, rowStart, columnStart)) {
                GameUtil.placeUserShipMark(arr, rowStart, columnStart);
                GameUtil.printBattleField(arr);
                break;
            } else System.out.println("There is already a ship at this place. Choose another cell");
        }

        while (true) {
            if (X == 0) {
                break;
            }
            System.out.println("••Choose end point:");
            System.out.println("•Choose row");
            rowEnd = GameUtil.readUserInputForRow();

            System.out.println("•Choose column");
            columnEnd = GameUtil.readUserInputForColumn();

            if (rowStart == rowEnd && columnStart != columnEnd) {
                if (columnEnd == columnStart + X || columnEnd == columnStart - X) {
                    if (columnEnd == columnStart + X) {
                        if (GameUtil.horizontalRightlCellsAreFree(arr, rowStart, columnStart, X)) {
                            for (int i = 1; i < sailsNumber; i++) {
                                GameUtil.placeUserShipMark(arr, rowStart, columnStart + i);
                            }
                            break;
                        } else System.out.println("There is already a ship at this place. Choose another cell");

                    }
                    if (columnEnd == columnStart - X) {
                        if (GameUtil.horizontaLeftlCellsAreFree(arr, rowStart, columnEnd, X)) {
                            for (int i = 1; i < sailsNumber; i++) {
                                GameUtil.placeUserShipMark(arr, rowStart, columnStart - i);
                            }
                            break;
                        } else System.out.println("There is already a ship at this place. Choose another cell");
                    }

                } else
                    System.out.println("Ship must be aligned. End point should be placed after " + X + " cells to Start point");
            }
            if (rowStart != rowEnd && columnStart == columnEnd) {
                if (rowEnd == rowStart + X || rowEnd == rowStart - X) {
                    if (rowEnd == rowStart + X) {
                        if (GameUtil.verticalDownCellsAreFree(arr, rowStart, columnStart, X)) {
                            for (int i = 1; i < sailsNumber; i++) {
                                GameUtil.placeUserShipMark(arr, rowStart + i, columnStart);
                            }
                            break;
                        } else System.out.println("There is already a ship at this place. Choose another cell");
                    }
                    if (rowEnd == rowStart - X) {
                        if (GameUtil.verticalUpCellsAreFree(arr, rowEnd, columnStart, X)) {
                            for (int i = 1; i < sailsNumber; i++) {
                                GameUtil.placeUserShipMark(arr, rowStart - i, columnStart);
                            }
                            break;
                        } else System.out.println("There is already a ship at this place. Choose another cell");
                    }

                } else
                    System.out.println("Ship must be aligned. End point should be placed after " + X + " cells to Start point");
            } else
                System.out.println("Ship must be aligned. End point should be placed after " + X + " cells to Start point");
        }

    }

    static void placeComputerShips(String arr[][], int X) {
        int sailsNumber = X + 1;
        int rowStart;
        int columnStart;
        int rowEnd;
        int columnEnd;

        while (true) {

            //Generate start point
            rowStart = (int) (Math.random() * 10 + 1);
            columnStart = (int) (Math.random() * 10 + 1);

            if (GameUtil.cellIsNotFree(arr, rowStart, columnStart)) {
                GameUtil.placeUserShipMark(arr, rowStart, columnStart);
                break;
            }
        }

        while (true) {
            if (X == 0) {
                break;
            }

            //Generate end point
            rowEnd = (int) (Math.random() * 10 + 1);
            columnEnd = (int) (Math.random() * 10 + 1);

            if (rowStart == rowEnd && columnStart != columnEnd) {
                if (columnEnd == columnStart + X || columnEnd == columnStart - X) {
                    if (columnEnd == columnStart + X) {
                        if (GameUtil.horizontalRightlCellsAreFree(arr, rowStart, columnStart, X)) {
                            for (int i = 1; i < sailsNumber; i++) {
                                GameUtil.placeUserShipMark(arr, rowStart, columnStart + i);
                            }
                            break;
                        }

                    }
                    if (columnEnd == columnStart - X) {
                        if (GameUtil.horizontaLeftlCellsAreFree(arr, rowStart, columnEnd, X)) {
                            for (int i = 1; i < sailsNumber; i++) {
                                GameUtil.placeUserShipMark(arr, rowStart, columnStart - i);
                            }
                            break;
                        }
                    }

                }
            }
            if (rowStart != rowEnd && columnStart == columnEnd) {
                if (rowEnd == rowStart + X || rowEnd == rowStart - X) {
                    if (rowEnd == rowStart + X) {
                        if (GameUtil.verticalDownCellsAreFree(arr, rowStart, columnStart, X)) {
                            for (int i = 1; i < sailsNumber; i++) {
                                GameUtil.placeUserShipMark(arr, rowStart + i, columnStart);
                            }
                            break;
                        }
                    }
                    if (rowEnd == rowStart - X) {
                        if (GameUtil.verticalUpCellsAreFree(arr, rowEnd, columnStart, X)) {
                            for (int i = 1; i < sailsNumber; i++) {
                                GameUtil.placeUserShipMark(arr, rowStart - i, columnStart);
                            }
                            break;
                        }
                    }

                }
            }
        }
    }

    static private boolean cellIsNotFree(String arr[][], int row, int column) {
        if ((arr[row][column]).equals("#")) {
            return false;
        } else return true;
    }

    static private boolean horizontalRightlCellsAreFree(String arr[][], int row, int column, int increment) {
        boolean cellsAreFree = true;
        for (int i = 1; i <= increment; i++) {
            if ((arr[row][column + i]).equals("#")) {
                cellsAreFree = false;
            } else return cellsAreFree;
        }
        return cellsAreFree;
    }

    static private boolean horizontaLeftlCellsAreFree(String arr[][], int row, int column, int increment) {
        boolean cellsAreFree = true;
        for (int i = 0; i < increment; i++) {
            if ((arr[row][column + i]).equals("#")) {
                cellsAreFree = false;
            } else return cellsAreFree;
        }
        return cellsAreFree;
    }

    static private boolean verticalDownCellsAreFree(String arr[][], int row, int column, int increment) {
        boolean cellsAreFree = true;
        for (int i = 1; i <= increment; i++) {
            if ((arr[row + i][column]).equals("#")) {
                cellsAreFree = false;
            } else return cellsAreFree;
        }
        return cellsAreFree;
    }

    static private boolean verticalUpCellsAreFree(String arr[][], int row, int column, int increment) {
        boolean cellsAreFree = true;
        for (int i = 0; i < increment; i++) {
            if ((arr[row + i][column]).equals("#")) {
                cellsAreFree = false;
            } else return cellsAreFree;
        }
        return cellsAreFree;
    }

    public static int userShot(String[][] userTarget, String[][] computerBattleField) {
        int row;
        int column;
        int hit = 0;

        System.out.println("••Please make a shot");

        while (true) {
            System.out.println("•Choose row");
            row = GameUtil.readUserInputForRow();

            System.out.println("•Choose column");
            column = GameUtil.readUserInputForColumn();

            if ((userTarget[row][column]).equals("~")) {
                if ((computerBattleField[row][column]).equals("~")) {
                    userTarget[row][column] = "•";
                    computerBattleField[row][column] = "•";
                    break;
                }
            }

            if ((userTarget[row][column]).equals("~")) {
                if ((computerBattleField[row][column]).equals("#")) {
                    userTarget[row][column] = "*";
                    computerBattleField[row][column] = "*";
                    hit = 1;
                    break;
                }
            }

            if ((userTarget[row][column]).equals("*") || (userTarget[row][column]).equals("•")) {
                System.out.println("You have already shoot here. Choose another place.");
            }
        }
        return hit;
    }

    public static int computerShot(String[][] userBattleField) {
        int row;
        int column;
        int hit = 0;

        while (true) {
            row = (int) (Math.random() * 10 + 1);
            column = (int) (Math.random() * 10 + 1);

            if ((userBattleField[row][column]).equals("~")) {
                userBattleField[row][column] = "•";
                break;

            }

            if ((userBattleField[row][column]).equals("#")) {
                userBattleField[row][column] = "*";
                hit = 1;
                break;

            }
        }
        return hit;
    }

    public static int playerWon(String[][] computerBattleField) {
        int winner = 0;
        int userHitCount = 0;

        //User win conditions. Number 2
        for (int i = 0; i < computerBattleField.length; i++) {
            for (int j = 0; j < computerBattleField[i].length; j++) {
                if ((computerBattleField[i][j]).equals("*")) {
                    userHitCount++;
                }
            }
        }
        if (userHitCount == 20) {
            winner = 2;
        }

        return winner;
    }

    public static int computerWon (String [][] userBattleField){
        int winner = 0;
        int computerHitCount = 0;
        //Computer win conditions. Number 1
        for (int i = 0; i < userBattleField.length; i++) {
            for (int j = 0; j < userBattleField[i].length; j++) {
                if ((userBattleField[i][j]).equals("*")) {
                    computerHitCount++;
                }
            }
        }
        if (computerHitCount == 20) {
            winner = 1;
        }
        return winner;
    }


}

