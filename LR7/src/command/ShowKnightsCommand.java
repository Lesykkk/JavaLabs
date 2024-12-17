package command;

import database.KnightDatabase;
import knight.Knight;
import java.util.Map;

public class ShowKnightsCommand implements Command {
    @Override
    public void execute() {
        if (KnightDatabase.isEmpty()) {
            System.out.println("Knight database is empty.");
        } else {
            Map<Integer, Knight> knightsMap = KnightDatabase.getKnights();
            for (int id = 1; id <= KnightDatabase.size(); id++) {
                System.out.println("ID [" + id + "] " + knightsMap.get(id));
            }
        }
    }

    @Override
    public String getDesc() {
        return "Show all knights";
    }
}
