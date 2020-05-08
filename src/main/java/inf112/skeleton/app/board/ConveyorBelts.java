package inf112.skeleton.app.board;



@SuppressWarnings("unused")
class ConveyorBelts {
    private final boolean RIGHT = true;
    private final boolean LEFT = false;
    private final int STOP = -1;
    private final Board board;

    public ConveyorBelts(Board board) {
        this.board = board;
    }

    /**
     * The belts surrounding the robot are only
     * relevant for the blue belts.
     * Blue belts only go twice so they return -1/STOP for all neighbouring belts
     * @param robot     The current robot being moved
     * @param belt      The current belt the robot is on
     * @param aboveBelt The belt above the robot
     * @param belowBelt The belt below the robot
     * @param leftBelt  The belt left of the robot
     * @param rightBelt The belt right of the robot
     */
    public void belts(Robot robot, int belt, int aboveBelt, int belowBelt, int leftBelt, int rightBelt) {

        if (belt == 49) robot.push(Direction.NORTH); //UP ARROW
        else if (belt == 50) robot.push(Direction.SOUTH); // DOWN ARROW
        else if (belt == 51) robot.push(Direction.WEST); // LEFT ARROW
        else if (belt == 52) robot.push(Direction.EAST); // RIGHT ARROW
        else if (belt == 35) { //UP TO RIGHT ARROW
            robot.turn(RIGHT);
            robot.push(Direction.EAST);
        }
        else if ((belt == 36)) { //RIGHT TO DOWN ARROW
            robot.turn(RIGHT);
            robot.push(Direction.SOUTH);
        }
        else if ((belt == 43)) { //LEFT TO UP ARROW
            robot.turn(RIGHT);
            robot.push(Direction.NORTH);
        }
        else if ((belt == 44)) { //DOWN TO LEFT ARROW
            robot.turn(RIGHT);
            robot.push(Direction.WEST);
        }
        else if ((belt == 33)) { //LEFT TO DOWN ARROW
            robot.turn(LEFT);
            robot.push(Direction.SOUTH);
        }
        else if ((belt == 34)) { //UP TO LEFT ARROW
            robot.turn(LEFT);
            robot.push(Direction.WEST);
        }
        else if ((belt == 41)) { //DOWN TO RIGHT ARROW
            robot.turn(LEFT);
            robot.push(Direction.EAST);
        }
        else if ((belt == 42)) { //RIGHT TO UP ARROW
            robot.turn(LEFT);
            robot.push(Direction.NORTH);
        }
        //Blue belts here
        else if ((belt == 19)) { //UP TO RIGHT BLUE
            robot.turn(RIGHT);
            robot.push(Direction.EAST);
            if (rightBelt != -1) belts(robot, rightBelt, STOP, STOP, STOP, STOP);
        }
        else if ((belt == 14)) { //RIGHT BLUE
            robot.push(Direction.EAST);
            if (rightBelt != -1) belts(robot, rightBelt, STOP, STOP, STOP, STOP);
        }
        else if ((belt == 26)) { //RIGHT TO UP BLUE
            robot.turn(LEFT);
            robot.push(Direction.NORTH);
            if (aboveBelt != -1) belts(robot, aboveBelt, STOP, STOP, STOP, STOP);
        }
    }
}
