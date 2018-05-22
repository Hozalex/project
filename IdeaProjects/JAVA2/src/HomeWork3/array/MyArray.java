package HomeWork3.array;

import java.util.ArrayList;


public class MyArray {


    public static void main(String[] args) {
        boolean same = false;
        int count = 0;
        ArrayList<String> words = new ArrayList();
        words.add("котлета"); //1
        words.add("комета");
        words.add("дверь"); //2
        words.add("котлета"); //1
        words.add("ноутбук");
        words.add("стол");
        words.add("стул");
        words.add("дверь");//2
        words.add("монитор");
        words.add("кресло");
        words.add("окно");//3
        words.add("окно");//3
        words.add("кружка");



        for (int i = 0; i < words.size(); i++) {
            String s = words.get(i);

            for (int j = i + 1; j < words.size(); j++) {

                if (s.equals(words.get(j))) {
                    same = true;
                    count++;
                }

            }

            if (!same) {
                System.out.println(s);
            }
            same = false;

        }


        System.out.println("совпадений - " + count);

    }


}
