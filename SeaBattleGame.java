package HW.seaBattle;


public class SeaBattleGame {
    public static void main(String[] args) {

        System.out.println("•••Welcome to the Sea Battle!•••");
        System.out.println(" ");

        String[][] userShipPlacement = new String[11][11];
        String[][] computerShipPlacement = new String[11][11];
        String[][] userTarget = new String[11][11];

        GameUtil.createNewBattleField(userShipPlacement);
        GameUtil.createNewBattleField(computerShipPlacement);
        GameUtil.createNewBattleField(userTarget);


        GameUtil.printBattleField(userShipPlacement);
        System.out.println(" ");

        GameUtil.placeXSailingShip(userShipPlacement, 3);
        GameUtil.printBattleField(userShipPlacement);

        GameUtil.placeXSailingShip(userShipPlacement, 2);
        GameUtil.printBattleField(userShipPlacement);

        GameUtil.placeXSailingShip(userShipPlacement, 2);
        GameUtil.printBattleField(userShipPlacement);

        GameUtil.placeXSailingShip(userShipPlacement, 1);
        GameUtil.printBattleField(userShipPlacement);

        GameUtil.placeXSailingShip(userShipPlacement, 1);
        GameUtil.printBattleField(userShipPlacement);

        GameUtil.placeXSailingShip(userShipPlacement, 1);
        GameUtil.printBattleField(userShipPlacement);

        GameUtil.placeXSailingShip(userShipPlacement, 0);
        GameUtil.printBattleField(userShipPlacement);

        GameUtil.placeXSailingShip(userShipPlacement, 0);
        GameUtil.printBattleField(userShipPlacement);

        GameUtil.placeXSailingShip(userShipPlacement, 0);
        GameUtil.printBattleField(userShipPlacement);

        GameUtil.placeXSailingShip(userShipPlacement, 0);
        GameUtil.printBattleField(userShipPlacement);

        //Place computer ships

        GameUtil.placeComputerShips(computerShipPlacement, 3);
        GameUtil.placeComputerShips(computerShipPlacement, 2);
        GameUtil.placeComputerShips(computerShipPlacement, 2);
        GameUtil.placeComputerShips(computerShipPlacement, 1);
        GameUtil.placeComputerShips(computerShipPlacement, 1);
        GameUtil.placeComputerShips(computerShipPlacement, 1);
        GameUtil.placeComputerShips(computerShipPlacement, 0);
        GameUtil.placeComputerShips(computerShipPlacement, 0);
        GameUtil.placeComputerShips(computerShipPlacement, 0);
        GameUtil.placeComputerShips(computerShipPlacement, 0);
        GameUtil.printBattleField(computerShipPlacement);

        int winner = 0;
        while(true) {
            int hit;
            do {
                GameUtil.printBattleField(userTarget);
                hit = GameUtil.userShot(userTarget, computerShipPlacement);

            } while (hit == 1);

            winner = GameUtil.playerWon (computerShipPlacement);
            if (winner !=0){
                break;
            }

            System.out.println("You miss! Computer's turn");
            do {
                GameUtil.printBattleField(userShipPlacement);
                hit = GameUtil.computerShot(userShipPlacement);
            } while (hit == 1);

            winner = GameUtil.computerWon (userShipPlacement);
            if (winner !=0){
                break;
            }
            GameUtil.printBattleField(userTarget);
        }

        switch (winner){
            case 1:
                System.out.println("Computer has won!");
            case 2:
                System.out.println("Congratulations! You have won! ");
        }

    }
}

