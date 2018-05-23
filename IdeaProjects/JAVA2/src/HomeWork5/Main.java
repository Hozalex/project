package HomeWork5;


public class Main {


    static final int SIZE = 10000000;
    static final int HALFSIZE = SIZE / 2;
    static float[] arr;


    public static void main(String[] args) {


        methodOne();
        methodTwo();

    }

    private static void methodOne() {
        arr = new float[SIZE];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }


        long t = System.currentTimeMillis();

        for (int j = 0; j < arr.length; j++) {

            arr[j] = (float) (arr[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));

        }

        System.out.println("Method One - " + (float) (System.currentTimeMillis() - t) / 1000 + " сек");

    }

    private static void methodTwo() {
        arr = new float[SIZE];
        float[] a1 = new float[HALFSIZE];
        float[] a2 = new float[HALFSIZE];

        for (int i = 0; i < arr.length; i++) {

            arr[i] = 1;

        }


        long t = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, HALFSIZE);
        System.arraycopy(arr, HALFSIZE, a2, 0, HALFSIZE);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a1.length; i++) {

                    a1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a2.length; i++) {

                    a2[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        t1.start();
        t2.start();


        System.arraycopy(a1, 0, arr, 0, HALFSIZE);
        System.arraycopy(a2, 0, arr, HALFSIZE, HALFSIZE);

        System.out.println("Method Two - " + (float) (System.currentTimeMillis() - t) / 1000 + " сек");

    }

}
