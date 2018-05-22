package HomeWork2.MyException;


import java.util.Random;

public class Main {


    public static void main(String[] args) {

        String[][] strings = new String[4][4];

        // рандом для добавления строкового символа в массив
        Random rd = new Random();
        int rand = rd.nextInt(strings[0].length + 1);

        //заполняем массив
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[0].length; j++) {
                // если i равно rand, то записываем строку в ячейку [i][rand]
                if (i == rand) {
                    strings[i][rand] = "str";
                } else {
                    strings[i][j] = Integer.toString(i + j);
                }
            }
        }

        //запускаем метод проверки массива
        try {
            checkArray(strings);
            System.out.println("все прошло успешно...");
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    static void checkArray(String[][] strings) throws MyArraySizeException, MyArrayDataException {

        int sum = 0;
        int elem;

        //если размер массива отличается от 4x4, то выбрасываем иключение MyArraySizeException
        if (strings.length != 4 || strings[0].length != 4) {
            throw new MyArraySizeException("Размер массива отличается от 4х4");
        }

        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[0].length; j++) {
                try {
                    //преобразуем строки в массиве в int, если не удается перехватываем NumberFormatException
                    elem = Integer.parseInt(strings[i][j]);
                    //суммируем элементы массива
                    sum += elem;
                } catch (NumberFormatException e) {
                    //если перехватилось исключение NumberFormatException, то бросаем вместо него свое исключение
                    throw new MyArrayDataException("В ячейке - " + i + " " + j + " неверный тип записи");
                }

            }

        }

        System.out.println(sum);
    }


}
