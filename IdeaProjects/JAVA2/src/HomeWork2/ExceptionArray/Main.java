package HomeWork2.ExceptionArray;

import java.io.*;
import java.net.URL;

public class Main {

    static Exception[] exceptions = new Exception[8];
    static int count = 0;
    static Integer integer;

    public static void main(String[] args) throws IOException {

        //вызываем методы для получения исключений
        nullPointerException(integer);
        count++;
        arithmeticException();
        count++;
        classCastException();
        count++;
        numberFormatException();
        count++;
        indexOutOfBoundsException();
        count++;
        unknownHostException();
        count++;
        fileNotFoundException();
        count++;
        classNotFoundException();


        //вывод списка собранных исключений
        for (int i = 0; i < exceptions.length; i++) {
            System.out.println(exceptions[i]);
        }

    }

    static public void nullPointerException(Integer integer) throws NullPointerException {

        try {
            int x = integer + 3;
        } catch (NullPointerException e) {
            exceptions[count] = e;
        }

    }

    static public void arithmeticException() throws ArithmeticException {
        try {
            int i = 5 / 0;
        } catch (ArithmeticException e) {
            exceptions[count] = e;
        }

    }

    static public void classCastException() throws ClassCastException {

        Object x = new Integer(0);
        try {
            System.out.println((String) x);
        } catch (ClassCastException e) {
            exceptions[count] = e;
        }

    }

    static public void numberFormatException() throws NumberFormatException {

        String str = "str";
        try {
            int x = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            exceptions[count] = e;
        }

    }

    static public void indexOutOfBoundsException() throws IndexOutOfBoundsException {

        int[] ints = new int[2];

        try {
            ints[3] = 8;
        } catch (IndexOutOfBoundsException e) {
            exceptions[count] = e;
        }

    }

    static public void unknownHostException() throws IOException {

        URL url = new URL("http://dfhdbfdf.com/");

        try {
            url.getContent();
        } catch (IOException e) {
            exceptions[count] = e;
        }

    }

    static public void fileNotFoundException()  {

        File file = new File("test.txt");

        try {
            FileInputStream str = new FileInputStream(file);

        } catch (FileNotFoundException e) {
            exceptions[count] = e;
        }

    }

    static public void classNotFoundException() {
        ClassLoader cls = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        };

        try {
            cls.loadClass("noneClass");
        } catch (ClassNotFoundException e) {
            exceptions[count] = e;
        }


    }


}
