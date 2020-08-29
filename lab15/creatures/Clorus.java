package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

/** An implementation of a creature called Clorus.
 *  @author Adnan H. Mohamed
 */
public class Clorus extends Creature {

    /** red color. */
    private static final int R = 34;
    /** green color. */
    private static final int G = 0;
    /** blue color. */
    private static final int B = 231;
    /** Loss of energy due to moving. */
    private static final double MOVE_LOSS = 0.03;
    /** Loss of energy due to staying. */
    private static final double STAY_LOSS = 0.01;
    /** energy portion to give offsprings */
    private static final double ENERGY_LOSS = 0.5;

    /** creates clorus with energy equal to E. */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    /** Returns the color of the clorus. */
    public Color color() {
        return color(R, G, B);
    }

    /** Eat C whenever C is a plip and gain its energy. */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /** Clorus should lose 0.03 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = Math.max(energy - MOVE_LOSS, 0);
    }

    /** Plips gain 0.2 energy when staying due to photosynthesis. */
    public void stay() {
        energy = Math.max(energy - STAY_LOSS, 0);
    }

    /** Cloruses and their offspring each get (ENERGY_LOSS*100)% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Clorus.
     */
    public Clorus replicate() {
        Clorus new_baby = new Clorus(energy * ENERGY_LOSS);
        energy = energy * (1 - ENERGY_LOSS);
        return new_baby;
    }

    /** Cloruses take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, if any Plips are seen, ATTACK.
     *  3. Otherwise, if energy >= 1, REPLICATE.
     *  4. Otherwise, MOVE
     *
     *  Returns an object of type Action. See Action.java for the
     *  scoop on how Actions work. See SampleCreature.chooseAction()
     *  for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (getNeighborsOfType(neighbors, "plip").size() > 0) {
            List<Direction> plips = getNeighborsOfType(neighbors, "plip");
            Direction attackDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, attackDir);
        } else if (energy >= 1) {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        }
        Direction moveDir = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, moveDir);
    }
}