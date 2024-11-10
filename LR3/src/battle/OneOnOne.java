package battle;

import droid.Droid;
import game.Logger;

public class OneOnOne {
    private Droid droid1;
    private Droid droid2;

    public OneOnOne(Droid droid1, Droid droid2) {
        this.droid1 = droid1;
        this.droid2 = droid2;
    }

    public void startBattle(Logger logger) {
        int round = 1;
        logger.log("Починається бій між " + droid1.getName() + " та " + droid2.getName());

        while (droid1.isAlive() && droid2.isAlive()) {
            logger.log("Раунд " + round);
            logger.log(droid1.getName() + " атакував " + droid2.getName()
                    + " і завдав " + droid2.takeDamage(droid1.attack()) + " шкоди.");

            logger.log(droid2.getName() + " атакував " + droid1.getName()
                    + " і завдав " + droid1.takeDamage(droid2.attack()) + " шкоди.");

            logger.log("\u001B[31m" + droid1.toString());
            logger.log("\u001B[34m" + droid2.toString() + "\u001B[0m");
            round++;
        }

        if (droid1.isAlive()) {
            logger.log(droid1.getName() + " переміг!");
        } else if (droid2.isAlive()) {
            logger.log(droid2.getName() + " переміг!");
        } else {
            logger.log("Нічия! Обидва дроїди загинули.");
        }
    }
}