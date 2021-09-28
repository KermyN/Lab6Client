package com.Kermy.Lab6.commands;

import com.Kermy.Lab6.collection.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * класс для ввода полей элемента.
 */
public class FieldsScanner {
    private static Scanner sc;
    private static FieldsScanner fs;
    /**
     * Instantiates a new Input helper.
     *
     * @param scanner the scanner
     */
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private FieldsScanner(Scanner scanner){
        sc = scanner;
    }

    public void configureScanner(Scanner scanner){
        sc = scanner;
    }
    public static FieldsScanner getInstance(){
        if(fs==null) {
            fs = new FieldsScanner(new Scanner(System.in));
        }
        return fs;
    }

    private String scanLine(String userInput){
        System.out.println("введите " + userInput);
        return sc.nextLine().trim();
    }

    private String scanLine(){
        return sc.nextLine().trim();
    }


    private String scanNotEmptyLine(String Input){
        String res = scanLine(Input);
        while(res.trim().isEmpty()) {
            System.out.println("Строка не должна быть пустой или состоять только из пробелов.");
            System.out.println("Введите " + Input + " заново.");
            res = sc.nextLine();
        }
        return res.trim();
    }

    public String scanStringArg(String in){
        String str = scanLine(in);
        while(str==null || str.equals("")){
            System.out.println("Не может быть пустой. Введите " + in + " еще раз.");
            str = sc.nextLine();
        }
        return str;
    }

    /**
     * метод для сканирования любого Enum. проверяет, является ли введенная
     * пользователем строка элементом enum'а, который передается во втором аргументе.
     *
     * @param canBeNull
     * @param enumType
     * @return enum
     */
    public Enum<?> scanEnum(boolean canBeNull, Class<? extends Enum> enumType){

        while(true) {
            String str = scanLine();
            try {
                if (str.equals("") && canBeNull) return null;
                else if (str.equals("")){
                    throw new NullPointerException();
                }
                return Enum.valueOf(enumType, str);
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("Пожалуйста, введите одно из значений enum'а.");
            }
        }
    }

    public int scanInteger(String in, boolean positiveOnly){
        while(true) {
            String input = scanNotEmptyLine(in);
            int res;
            try{
                res = Integer.parseInt(input);
                if(positiveOnly && (res<=0)){
                    System.out.println("необходимо ввести число большее нуля");
                }else   {
                    return res;
                }
            }catch (Exception e){
                System.out.println("введите целое число");
            }
        }
    }


    public float scanFloat(String in, boolean positiveOnly){
        while(true) {
            String input = scanNotEmptyLine(in);
            float res;
            try{
                res = Float.parseFloat(input);
                if(positiveOnly && (res<=0)){
                    System.out.println("необходимо ввести число большее нуля");
                }else{
                    return res;
                }
            }catch (Exception e){
                System.out.println("введите число");
            }
        }
    }

    /**
     * @return дракон dragon
     */
    public Dragon scanDragon(){
        String name = scanStringArg("имя дракона");
        System.out.println("Координаты.");
        int x = scanInteger("Х", false);
        int y = scanInteger("Y", false);
        Coordinates coordinates = new Coordinates(x, y);
        int age = scanInteger("возраст дракона", true);
        System.out.println("Введите тип дракона. Доступные типы: ");
        for(DragonType t : DragonType.values()){
            System.out.print(t + " ");
        }
        System.out.println();
        DragonType type = (DragonType) scanEnum( true, DragonType.class);
        System.out.println("Введите характер дракона. Доступные типы: ");
        for(DragonCharacter t : DragonCharacter.values()){
            System.out.print(t + " ");
        }
        System.out.println();
        DragonCharacter character = (DragonCharacter) scanEnum(false, DragonCharacter.class);
        float treasure = scanFloat("Количество сокоровищь (это число)", true);

        DragonCave cave = new DragonCave(treasure);
        System.out.println("Введите Цвет дракона. Доступные типы: ");

        for(Color с : Color.values()){
            System.out.print(с + " ");
        }
        System.out.println();
        Color color = (Color) scanEnum(true,Color.class);

        return new Dragon(12,name, coordinates, ZonedDateTime.now(), age,color,type, character,cave);
    }

}