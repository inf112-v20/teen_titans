package inf112.skeleton.app.board;

class Grills {

    private final Board board;
    private boolean even;
    private final int x;
    private final int y;

    public Grills(Pos pos, Board board, boolean even) {
        x = pos.getPosX();
        y = pos.getPosY();
        this.board = board;
        this.even = even;
    }

    public void burn(int round) {
        for (Robot robot : board.getListOfRobots()){
            if (robot.getPos().getPosY() == y && robot.getPos().getPosX() == x){
                if (even && round % 2 == 0 || !even && round % 2 == 1) {
                    robot.takeDamage(2);
                }
            }
        }
    }
}
