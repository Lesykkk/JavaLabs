package battle;

import droid.Droid;
import game.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamOnTeam {
    private List<Droid> redTeam;
    private List<Droid> blueTeam;

    public TeamOnTeam(List<Droid> redTeam, List<Droid> blueTeam) {
        this.redTeam = redTeam;
        this.blueTeam = blueTeam;
    }

    public void startBattle(Logger logger) {
        int round = 1;
        logger.log("Починається бій між командою синіх та червоних");

        while (!isTeamDefeated(redTeam) && !isTeamDefeated(blueTeam)) {
            logger.log("Раунд " + round);

            for (Droid attacker : redTeam) {
                if (attacker.isAlive()) {
                    Droid target = chooseRandomTarget(blueTeam);
                    if (target != null) {
                        logger.log(attacker.getName() + " атакував " + target.getName() +
                                " і завдав " + target.takeDamage(attacker.attack()) + " шкоди.");
                        if (!target.isAlive()) {
                            logger.log(target.getName() + " знищений!");
                        }
                    }
                }
            }

            for (Droid attacker : blueTeam) {
                if (attacker.isAlive()) {
                    Droid target = chooseRandomTarget(redTeam);
                    if (target != null) {
                        logger.log(attacker.getName() + " атакував " + target.getName() +
                                " і завдав " + target.takeDamage(attacker.attack()) + " шкоди.");
                        if (!target.isAlive()) {
                            logger.log(target.getName() + " знищений!");
                        }
                    }

                }
            }
            for (Droid droid: redTeam) {
                logger.log("\u001B[31m" + droid.toString());
            }
            for (Droid droid: blueTeam) {
                logger.log("\u001B[34m" + droid.toString() + "\u001B[0m");
            }
            round++;
        }

        if (isTeamDefeated(blueTeam)) {
            logger.log("Червона команда перемогла!");
        } else {
            logger.log("Синя команда перемогла!");
        }
    }

    private Droid chooseRandomTarget(List<Droid> team) {
        Random random = new Random();
        List<Droid> aliveDroids = new ArrayList<>();
        for (Droid droid : team) {
            if (droid.isAlive()) {
                aliveDroids.add(droid);
            }
        }
        if (aliveDroids.isEmpty()) {
            return null;
        }
        return aliveDroids.get(random.nextInt(aliveDroids.size()));
    }

    private boolean isTeamDefeated(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return false;
            }
        }
        return true;
    }
}
