package comparators;

import data.SpaceMarine;

import java.util.Comparator;

/**
 * Comparator for sorting collection in ascending order, with objects {@link SpaceMarine} type, by weaponType field
 * @author NastyaBordun
 * @version 1.1
 */

public class WeaponTypeComparator implements Comparator<SpaceMarine> {

    public int compare(SpaceMarine sp1, SpaceMarine sp2){
        if(sp1 .getWeaponType() == null){
            if(sp2.getWeaponType() == null){
                return 0;
            }
            return -1;
        }
        else if(sp2.getWeaponType() == null){
            if(sp1.getWeaponType() == null){
                return 0;
            }
            return 1;
        }
        else{
            if(sp1.getWeaponType().toString().length() > sp2.getWeaponType().toString().length()){
                return 1;
            }
            else if (sp1.getWeaponType().toString().length() == sp2.getWeaponType().toString().length()){
                return 0;
            }
            else{
                return -1;
            }
        }
    }

}
