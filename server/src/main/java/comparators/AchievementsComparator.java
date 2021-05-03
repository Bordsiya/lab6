package comparators;

import data.SpaceMarine;

import java.util.Comparator;

/**
 * Comparator for sorting collection in descending order, with objects {@link SpaceMarine} type, by achievements field
 * @author NastyaBordun
 * @version 1.1
 */

public class AchievementsComparator implements Comparator<SpaceMarine> {

    public int compare(SpaceMarine sp1, SpaceMarine sp2){
        if(sp1.getAchievements().length() > sp2.getAchievements().length()){
            return -1;
        }
        else if (sp1.getAchievements().length() == sp2.getAchievements().length()){
            return 0;
        }
        else{
            return 1;
        }
    }

}
