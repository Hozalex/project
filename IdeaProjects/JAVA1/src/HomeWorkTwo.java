import java.util.Arrays;

/**
 * @author hozal
 */
public class HomeWorkTwo {

    public static void main(String[] args) {

        int[] arr = {1, 0, 0, 1, 0, 1, 1, 0};

        // change 0 to 1, 1 to 0
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                arr[i] = 0;
            } else {
                arr[i] = 1;
            }
        }
        System.out.println(Arrays.toString(arr));


        //fill array
        arr = new int[8];
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = c;
            c += 3;
        }
        System.out.println(Arrays.toString(arr));


        //multiply x2
        arr = new int[]{13, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 3};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(arr));


        //fill diagonal by 1
        int[][] arrx = new int[5][5];
        c = 1;
        int count = arrx.length - 1;
        for (int i = 0; i < arrx.length; i++) {
            for (int j = 0; j < arrx.length; j++) {
                if (i == j) {
                    arrx[i][j] = c;
                }
                arrx[i][count] = 1;
                System.out.print(arrx[i][j] + " ");

            }
            count--;
            System.out.println();
        }


        //find min and max
        int max = 0;
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            min = arr[i];
            for (int t : arr) {
                if (t > max) {
                    max = t;
                } else if (t < min) {
                    min = t;
                }
            }
        }

        System.out.println("Min " + min);
        System.out.println("Max " + max);

        //check balance
        int[] x = {2, 3, 5, 7, 10, 7};
        System.out.println(checkBalance(x));


        //offset
        offset(arr, -5);


    }

    private static boolean checkBalance(int[] arr) {
        int a = 0;
        boolean res = false;

        if (arr.length == 0) {
            System.out.println("Array is empty...");
        }

        for (int i = 0; i < arr.length; i++) {
            int b = 0;
            a += arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                b += arr[j];
            }
            if (a == b) {
                res = true;
            }
        }
        return res;
    }

    private static void offset(int[] arr, int i) {

        for (int j = 0; j < arr.length; j++) {
            arr[j] += i;

        }
        System.out.println(Arrays.toString(arr));
    }

}
