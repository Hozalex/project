package HomeWork3.contacts;

import java.util.HashMap;

import java.util.HashSet;
import java.util.Map;


public class Contacts {

    static Map<String, HashSet> contactBook = new HashMap<String, HashSet>();
    static HashSet<String> phones;

    public static void main(String[] args) {


        addContact("Иванов", "86086423", "86976969");
        addContact("Сидоров", "97896976324");
        addContact("Петров", "88670724", "352355", "3264");
        addContact("Пузякин", "76543920");

        getContact("иванов");
        getContact("петров");
        getContact("терминатор");
        getContact("Пузякин");


    }

    static void addContact(String name, String... phoneNumber) {

        phones = new HashSet<String>();
        for (int i = 0; i < phoneNumber.length; i++) {
            phones.add(phoneNumber[i]);
        }

        contactBook.put(name.toLowerCase(), phones);

    }

    static void getContact(String name) {


        if (contactBook.isEmpty()) {

            System.out.println("Телефонная книга пуста...");

        } else {

            HashSet<String> defaultPhones = new HashSet<>();
            defaultPhones.add("Нет номера");
            System.out.println(name + " - " + contactBook.getOrDefault(name.toLowerCase(), defaultPhones));

        }


    }


}
