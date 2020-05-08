package inf112.skeleton.app.board;

public class Repair {

    Board board;
    Pos pos;

    public Repair(Pos pos, Board board){
        this.board = board;
        this.pos = pos;
    }

    public void heal(Robot robot) {
        int hp = robot.getCurrentHp();
        if (hp < robot.getMaxHp()) {
            robot.heal();
        }
    }

    public Pos getPos() {
        return pos;
    }
}
