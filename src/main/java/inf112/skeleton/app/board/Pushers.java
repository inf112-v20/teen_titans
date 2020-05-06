package inf112.skeleton.app.board;

public class Pushers {

    Board board;
    boolean even;
    int x;
    int y;
    Pos pos;
    Direction dir;

    public Pushers(Pos pos, Board board, boolean even, Direction dir){
        x = pos.getPosX();
        y = pos.getPosY();
        this.pos = pos;
        this.board = board;
        this.dir = dir;
    }

    public void push(int round) {
        for (Robot robot: board.getListOfRobots()){
            if (robot.getPos().getPosX() == x && robot.getPos().getPosY() == y) {
                if (even && round % 2 == 0 || !even && round % 2 == 1) {

                    robot.push(dir);
                    //System.out.println(pos);
                    board.updatePlayer(pos, robot);

                }
            }
        }
    }
}
