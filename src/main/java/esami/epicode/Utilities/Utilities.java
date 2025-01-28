package esami.epicode.Utilities;

import esami.epicode.Entity.Utente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public interface Utilities {

    static Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static String getString(String msg){
        System.out.println(msg);
        return sc.nextLine();

    }

    public static long getLong(String msg){
        System.out.println(msg);
        return sc.nextLong();
    }

    public static LocalDate getDate(String msg){
        System.out.println(msg);
        String data = sc.nextLine();
        LocalDate dataForm = LocalDate.parse(data, formatter);
        return dataForm;

    }


}
