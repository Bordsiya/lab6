package utils;

import data.*;
import exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class providing getting correct information from the user
 * @author NastyaBordun
 * @version 1.1
 */

public class AskManager {
    /**
     * Reader for interactive mode
     */
    private BufferedReader bf;
    /**
     * Reader for script mode
     */
    private BufferedReader bf2;
    /**
     * Mode (true - interactive; false - script)
     */
    private boolean interactiveMode;

    /**
     * Creating Ask Manager
     * @param bf {@link AskManager#bf}
     */
    public AskManager(BufferedReader bf){
        this.bf = bf;
        this.interactiveMode = true;
    }

    /**
     * Adding a script reader
     * @param bf2 {@link AskManager#bf2}
     */
    public void addScriptReader(BufferedReader bf2){
        setBf2(bf2);
    }

    /**
     * Setting {@link AskManager#bf2}
     * @param bf2 new Reader
     */
    private void setBf2(BufferedReader bf2){
        this.bf2 = bf2;
    }

    /**
     * Setting {@link AskManager#interactiveMode}
     * @param interactiveMode new mode
     */
    public void setInteractiveMode(boolean interactiveMode){
        this.interactiveMode = interactiveMode;
    }

    /**
     * Getting field value {@link AskManager#bf2}
     * @return Script BufferedReader
     */
    public BufferedReader getBf2(){
        return this.bf2;
    }
    /**
     * Entering the correct name field of class {@link SpaceMarine}
     * @return name
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     */
    public String askName() throws IncorrectScriptInputException {
        boolean flag = false;
        String name = null;
        while(!flag){
            try {
                Printer.println("Введите поле name: ");
                if(interactiveMode){
                    name = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    name = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (name.length() == 0) {
                    throw new EmptyNameException("Поле name не может быть пустым");
                }
                flag = true;
            }
            catch (IOException e){
                Printer.printError("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (EmptyNameException e){
                Printer.printError(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return name;
    }

    /**
     * Entering the correct coordinates field of class {@link SpaceMarine}
     * @return coordinates
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     * @see AskManager#askCoordinateX()
     * @see AskManager#askCoordinateY()
     */
    public Coordinates askCoordinates() throws IncorrectScriptInputException {
        Coordinates coordinates = new Coordinates(askCoordinateX(), askCoordinateY());
        return coordinates;
    }

    /**
     * Entering the correct x field of class {@link Coordinates}
     * @return x
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     * @see AskManager#askCoordinates()
     * @see AskManager#askCoordinateY()
     */
    public long askCoordinateX() throws IncorrectScriptInputException {
        boolean flag = false;
        long coordinateX = 0;
        while(!flag){
            try {
                Printer.println("Введите координату x поля coordinates: ");
                String strX = "";
                if(interactiveMode){
                    strX = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strX = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (Math.abs(Long.parseLong(strX)) > 991) {
                    throw new IncorrectCoordinateXException("Координата x поля coordinates не может быть больше 991 по модулю");
                }
                flag = true;
                coordinateX = Long.parseLong(strX);
            }
            catch (NumberFormatException e){
                Printer.printError("Введено некорректное число");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IOException e){
                Printer.printError("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IncorrectCoordinateXException e){
                Printer.printError(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return coordinateX;
    }

    /**
     * Entering the correct y field of class {@link Coordinates}
     * @return y
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     * @see AskManager#askCoordinates()
     * @see AskManager#askCoordinateX()
     */
    public Double askCoordinateY() throws IncorrectScriptInputException {
        boolean flag = false;
        Double coordinateY = null;
        while(!flag){
            try {
                Printer.println("Введите координату y поля coordinates: ");
                String strY = "";
                if(interactiveMode){
                    strY = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strY = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (strY.length() == 0) {
                    throw new EmptyCoordinateYException("Координата Y поля coordinates не может быть пустой");
                }
                if (Math.abs(Double.parseDouble(strY)) > 767) {
                    throw new IncorrectCoordinateYException("Координата Y поля coordinates не может быть больше 767 по модулю");
                }
                flag = true;
                coordinateY = Double.parseDouble(strY);

            }
            catch (NumberFormatException e){
                Printer.printError("Введено некорректное число");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IOException e){
                Printer.printError("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (EmptyCoordinateYException | IncorrectCoordinateYException e){
                Printer.printError(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (NullPointerException e){
                Printer.printError("Неожиданная ошибка");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return coordinateY;
    }

    /**
     * Entering the correct health field of class {@link SpaceMarine}
     * @return health
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     */
    public Float askHealth() throws IncorrectScriptInputException {
        boolean flag = false;
        Float health = null;
        while(!flag){
            try {
                Printer.println("Введите поле health: ");
                String strHealth = "";
                if(interactiveMode){
                    strHealth = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strHealth = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if(strHealth.length() != 0){
                    if (Float.parseFloat(strHealth) <= 0) {
                        throw new IncorrectHealthException("Поле health должно быть больше 0");
                    }
                    flag = true;
                    health = Float.parseFloat(strHealth);
                }
                else{
                    flag = true;
                    health = null;
                }
            }
            catch (NumberFormatException e){
                Printer.printError("Введено некорректное число");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IOException e){
                Printer.printError("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IncorrectHealthException e){
                Printer.printError(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (NullPointerException e){
                Printer.printError("Неожиданная ошибка");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return health;
    }

    /**
     * Entering the correct achievements field of class {@link SpaceMarine}
     * @return achievements
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     */
    public String askAchievements() throws IncorrectScriptInputException {
        String achievements = null;
        boolean flag = false;
        while(!flag){
            flag = true;
            try{
                Printer.println("Введите поле achievements: ");
                if(interactiveMode){
                    achievements = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    achievements = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
            }
            catch (IOException e){
                Printer.printError("Ошибка ввода");
                flag = false;
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        if(achievements.equals("")) return null;
        else return achievements;
    }

    /**
     * Entering the correct weaponType field of class {@link SpaceMarine}
     * @return weaponType
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     * @see Weapon#getWeapon()
     */
    public Weapon askWeaponType() throws IncorrectScriptInputException {
        boolean flag = false;
        Weapon weaponType = null;
        while(!flag){
            flag = true;
            try{
                Printer.println("Введите поле weaponType: ");
                Printer.print(Weapon.getWeapon());
                String strWeaponType = "";
                if(interactiveMode){
                    strWeaponType = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strWeaponType = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if(strWeaponType.length() == 0){
                    weaponType = null;
                }
                else{
                    weaponType = Weapon.valueOf(strWeaponType);
                }
            }
            catch (IllegalArgumentException e){
                flag = false;
                Printer.printError("Некорректный weaponType");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IOException e){
                flag = false;
                Printer.printError("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return weaponType;
    }

    /**
     * Entering the correct meleeWeapon field of class {@link SpaceMarine}
     * @return meleeWeapon
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     * @see MeleeWeapon#getMeleeWeapon()
     */
    public MeleeWeapon askMeleeWeapon() throws IncorrectScriptInputException {
        boolean flag = false;
        MeleeWeapon meleeWeapon = null;
        while(!flag){
            flag = true;
            try{
                Printer.println("Введите поле meleeWeapon: ");
                Printer.print(MeleeWeapon.getMeleeWeapon());
                String strMeleeWeapon = "";
                if(interactiveMode){
                    strMeleeWeapon = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strMeleeWeapon = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                meleeWeapon = MeleeWeapon.valueOf(strMeleeWeapon);
            }
            catch (IllegalArgumentException e){
                flag = false;
                Printer.printError("Некорректный meleeWeapon");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IOException e){
                flag = false;
                Printer.printError("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return meleeWeapon;
    }

    /**
     * Entering the correct chapter field of class {@link SpaceMarine}
     * @return chapter
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     * @see AskManager#askChapterName()
     * @see AskManager#askChapterWorld()
     */
    public Chapter askChapter() throws IncorrectScriptInputException {
        Chapter chapter = new Chapter(askChapterName(), askChapterWorld());
        return chapter;
    }

    /**
     * Entering the correct name field of class {@link Chapter}
     * @return name
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     * @see AskManager#askChapter()
     * @see AskManager#askChapterWorld()
     */
    public String askChapterName() throws IncorrectScriptInputException {
        boolean flag = false;
        String chapterName = null;
        while(!flag){
            try{
                Printer.println("Введите поле name для Chapter: ");
                if(interactiveMode){
                    chapterName = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    chapterName = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if(chapterName.length() == 0){
                    throw new EmptyChapterNameException("Поле name класса chapter не может быть пустым");
                }
                flag = true;
            }
            catch (IOException e){
                Printer.printError("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (EmptyChapterNameException e){
                Printer.printError(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }

        }
        return chapterName;
    }

    /**
     * Entering the correct world field of class {@link Chapter}
     * @return world
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     * @see AskManager#askChapter()
     * @see AskManager#askChapterName()
     */
    public String askChapterWorld() throws IncorrectScriptInputException {
        boolean flag = false;
        String chapterWorld = null;
        while(!flag){
            try{
                Printer.println("Введите поле world для Chapter: ");
                if(interactiveMode){
                    chapterWorld = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    chapterWorld = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if(chapterWorld.length() == 0){
                    throw new EmptyChapterWorldException("Поле world класса chapter не может быть пустым");
                }
                flag = true;
            }
            catch (IOException e){
                Printer.printError("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (EmptyChapterWorldException e){
                Printer.printError(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return chapterWorld;
    }

    /**
     * Entering the correct filePath field of class
     * @return filePath
     */
    public String askPath(){
        boolean flag = false;
        String path = null;
        while(!flag){
            try{
                Printer.println("Введите путь к файлу (exit - выход из программы)");
                path = bf.readLine().trim().replaceAll("\uFFFD", "");
                if(path.length() == 0){
                    throw new EmptyPathException("Путь к файлу не может быть пустым");
                }
                else if(path.equals("exit")){
                    System.exit(0);
                }
                flag = true;
            }
            catch (IOException e){
                Printer.printError("Ошибка ввода");
            }
            catch (EmptyPathException e){
                Printer.printError(e.getMessage());
            }
        }
        return path;
    }

    /**
     * Deciding which fields user would like to change
     * @param field field for changing
     * @return user's choice
     * @throws IncorrectScriptInputException script cannot be re-asked with incorrect input
     */
    public boolean questionCheck(String field) throws IncorrectScriptInputException {
        while(true){
            try{
                String ans;
                if(interactiveMode) {
                    Printer.println("Хотите ли изменить значение поля " + field + "?\n1 - да\n2 - нет");
                    ans = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    ans = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if(ans.equals("1")){
                    return true;
                }
                else if(ans.equals("2")){
                    return false;
                }
                else{
                    throw new IncorrectAnswerException("Некорректный ответ");
                }
            }
            catch (IOException e){
                Printer.printError("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IncorrectAnswerException e){
                Printer.printError(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }



    }


}
