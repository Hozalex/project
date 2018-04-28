import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HomeWorkThree {


    public static void main(String[] args) {

        words();
        randomNumber();


    }

    private static void words() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear",
                "pepper", "pineapple", "pumpkin", "potato"};

        Scanner sc = new Scanner(System.in);
        int random = new Random().nextInt(words.length);
        String secretWord = words[random];
        String inputWord;
        System.out.println(Arrays.toString(words));
//        System.out.println(secretWord);

        do {
            System.out.println("Угадайте загаданное слово - ");
            inputWord = sc.nextLine().toLowerCase();
            int quantityLetter;

            if (secretWord.length() > inputWord.length()) {
                quantityLetter = inputWord.length();
            } else quantityLetter = secretWord.length();

            if (!inputWord.equals(secretWord)) {
                System.out.println("Неверно. совпадения - ");
                for (int i = 0; i < quantityLetter; i++) {
                    char a = inputWord.charAt(i);
                    char b = secretWord.charAt(i);
                    if (a == b) {
                        System.out.print(a);
                    } else System.out.print("#");
                }
                System.out.println("########");
            } else System.out.println("Вы угадали!!!" + "\n");

        } while (!inputWord.equals(secretWord));

    }


    public static void randomNumber() {
        Scanner sc = new Scanner(System.in);
        int number;
        int secretNumber = new Random().nextInt(10);
        System.out.println("Введите число от 0 до 9 - ");
        for (int i = 0; i < 3; i++) {
            number = sc.nextInt();
            if (number < 0 || number > 9) {
                System.out.println("Вы ввели не верное число...");
                randomNumber();
            } else if (number > secretNumber) {
                System.out.println("Ваше число больше");
            } else if (number < secretNumber) {
                System.out.println("Ваше число меньше");
            } else if (number == secretNumber) {
                System.out.println("Вы выиграли!!!");
                break;
            }

        }

        System.out.println("Повторить игру еще раз? 1-да / 0-нет");

        switch (sc.nextInt()) {

            case (1):
                randomNumber();
            case (0):
                break;
            default:
                break;

        }

        System.out.println();

    }


}
