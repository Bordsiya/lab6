package data;

/**
 * Field weaponType for class {@link SpaceMarine}
 * @author NastyaBordun
 * @version 1.1
 */

public enum Weapon {
    MELTAGUN,
    BOLT_RIFLE,
    FLAMER,
    MULTI_MELTA,
    MISSILE_LAUNCHER;

    /**
     * Valid weaponType field values printing, class {@link SpaceMarine}
     */
    public static String getWeapon(){
        String str = "";
        str += "Допустимые значения поля weaponType" + '\n';
        for(Weapon wp : Weapon.values()){
            str += wp.toString() + '\n';
        }
        return str;
    }

    @Override
    public String toString(){
        switch (this){
            case MELTAGUN:
                return "MELTAGUN";
            case BOLT_RIFLE:
                return "BOLT_RIFLE";
            case FLAMER:
                return "FLAMER";
            case MULTI_MELTA:
                return "MULTI_MELTA";
            case MISSILE_LAUNCHER:
                return "MISSILE_LAUNCHER";
        }
        throw new IllegalArgumentException();
    }


}
