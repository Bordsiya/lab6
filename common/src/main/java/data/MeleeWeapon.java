package data;

/**
 * Field meleeWeapon for class {@link SpaceMarine}
 * @author NastyaBordun
 * @version 1.1
 */

public enum MeleeWeapon {
    CHAIN_AXE,
    MANREAPER,
    LIGHTING_CLAW,
    POWER_BLADE,
    POWER_FIST;

    /**
     * Valid meleeWeapon field values printing, class {@link SpaceMarine}
     */
    public static String getMeleeWeapon(){
        String str = "";
        str += "Допустимые значения поля meleeWeapon\n";
        for(MeleeWeapon wp : MeleeWeapon.values()){
            str += wp.toString() + '\n';
        }
        return str;
    }

    @Override
    public String toString(){
        switch (this){
            case CHAIN_AXE:
                return "CHAIN_AXE";
            case MANREAPER:
                return "MANREAPER";
            case LIGHTING_CLAW:
                return "LIGHTING_CLAW";
            case POWER_BLADE:
                return "POWER_BLADE";
            case POWER_FIST:
                return "POWER_FIST";
        }
        throw new IllegalArgumentException();
    }

}
