public class HomeWorkOne {

    public static void main(String[] args) {

        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4L;

        double d = 5.5;
        float f = 2.5F;

        String str = "R2D2";

        expression(2, 4, 6, 8);
        checkSumNumber(5, 15);
        checkSignNumber(-6);
        checkNegativeNumber(-8);
        printName(str);
        checkYear(1700);
        yearCheck(1700);

    }

    private static void yearCheck(int i) {
        if (i % 100 == 0 && i % 400 != 0) {

            System.out.println("Не високосный");
        } else if (i % 4 == 0) {
            System.out.println("Високосный");
        } else System.out.println("Не високосный");
    }


    private static void checkYear(int y) {
        if (y % 25 == 0 && y % 16 != 0) {
            System.out.println("Не високосный");
        } else if (y % 4 == 0) {
            System.out.println("Високосный");
        } else System.out.println("Не високосный");
    }

    private static void printName(String s) {
        System.out.println("Hello, " + s + "!");
    }

    private static boolean checkNegativeNumber(int i) {
        return i < 0 ? true : false;
    }

    private static void checkSignNumber(int i) {
        if (i >= 0) {
            System.out.println("Положительное число " + i);
        } else System.out.println("Отрицательное число " + i);
    }

    private static boolean checkSumNumber(int a, int b) {
        if ((a + b) >= 10 && (a + b) <= 20) {
            return true;
        }
        return false;
    }

    private static int expression(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

}

