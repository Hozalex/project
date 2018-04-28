import java.util.Random;
import java.util.Scanner;

public class HomeWorkFour {
    public static char[][] map;
    public static int size;
    public static int dots_to_win;
    public static int player_steps = 0;
    //координаты для блокировки хода
    public static int x_for_pc;
    public static int y_for_pc;
    //флаг для начала блокировки
    public static boolean block;

    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static Scanner scanner = new Scanner(System.in);
    public static Random rn;

    public static void main(String[] args) {

        initMap();
        printMap();
        game();

    }

    //инициализируем поле
    public static void initMap() {
        int in;
        //устанавливаем размер поля
        System.out.print("Введите размер поля - ");
        in = scanner.nextInt();
        size = in;
        dots_to_win = size;
        //если меньше 3 - перезапускаем еще раз
        if (size < 3) {
            System.out.println("Маловато...");
            initMap();
        }

        map = new char[size][size];
        //выводим пустое поле
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = DOT_EMPTY;
            }

        }

    }

    //рисуем поле
    public static void printMap() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //ход пользователя
    public static void playerStep() {
        int x;
        int y;
        System.out.println("Сделайте ваш ход - ");
        //вводим значения пока числа не положительные
        do {
            System.out.print("X - ");
            x = scanner.nextInt() - 1;
            System.out.print("Y - ");
            y = scanner.nextInt() - 1;

        } while (x > size - 1 || y > size - 1);
        //если ячейка пустая - делаем ход, увеличиваем число ходов, перерисовываем поле
        if (map[y][x] == DOT_EMPTY) {
            map[y][x] = DOT_X;
            player_steps++;
            printMap();
        } else {
            //если ячека занята - повторяем ход
            System.out.println("Ячейка занята...");
            playerStep();
        }
    }

    //ход компа
    public static void pcStep() {
        int x;
        int y;
        rn = new Random();
        System.out.println("Ход Компа... ");
        //получение случайных координат
        x = rn.nextInt(size);
        y = rn.nextInt(size);
        //если флаг для блокировки включен и ячейка пустая - делаем ход в эту ячейку
        if (block && map[y_for_pc][x_for_pc] == DOT_EMPTY) {
            map[y_for_pc][x_for_pc] = DOT_O;
            printMap();
            //если ячейка не пустая, то делаем ход в рандомную ячейку
        } else if (map[y][x] == DOT_EMPTY) {
            map[y][x] = DOT_O;
            printMap();
        } else pcStep();
    }

    //основной ход игры
    public static void game() {
        rn = new Random();
        //определяем, кто будет ходить первый
        if (rn.nextInt(2) == 1) {
            do {
                //проверяем заполненность поля
                isFilled();
                //шаг пользователя
                playerStep();
                //проверка выигрыша
                if (isWin(DOT_X)) {
                    System.out.println("ПОбеда!!");
                    return;
                }
                //проверяем заполненность поля
                isFilled();
                //шаг компа
                pcStep();
                //проверка прогигрыша
                if (isWin(DOT_O)) {
                    System.out.println("проигрыш...");
                    return;
                }
            } while (true);
        } else {
            do {
                isFilled();
                pcStep();
                if (isWin(DOT_O)) {
                    System.out.println("проигрыш...");
                    return;
                }
                isFilled();
                playerStep();
                if (isWin(DOT_X)) {
                    System.out.println("ПОбеда!!");
                    return;
                }
            } while (true);
        }

    }

    //проверка выигрыша
    public static boolean isWin(char symbol) {
        //счетчики заполненности диагоналей
        int countDiagonalRight = 0;
        int countDiagonalBack = 0;
        //счетчик для диагонали
        int count = size - 1;
        //флаг блока выключаем
        block = false;
        for (int i = 0; i < size; i++) {
            //счетчики для строк и столбцов
            int countRow = 0;
            int countColumn = 0;
            //считаем заполненность в обратной диагонали
            if (map[i][count] == symbol) {
                countDiagonalBack++;
                //если после второго шага пользователя по диагонали 2 или больше ячейки подряд заняты X то комп на следующем
                // шаге блокирует следующую ячейку
                if (symbol == DOT_X && player_steps >= 2 && countDiagonalBack >= 2) {
                    if (size - 1 > i) {
                        x_for_pc = count - 1;
                        y_for_pc = i + 1;
                    } else {
                        x_for_pc = count + 1;
                        y_for_pc = i - 1;
                    }
                    //если ячейка для блокировки не занята O включаем блок
                    if (map[x_for_pc][y_for_pc] != DOT_O) {
                        block = true;
                    }
                }
            }
            for (int j = 0; j < size; j++) {
                if (map[i][j] == symbol) {
                    countRow++;
                    //если после второго шага пользователя в строке 2 или больше ячейки подряд заняты X то комп на следующем
                    // шаге блокирует следующую ячейку
                    if (symbol == DOT_X && player_steps >= 2 && countRow >= 2) {
                        if (size - 1 > j) {
                            x_for_pc = j + 1;
                        } else x_for_pc = (size - 1) - countRow;
                        y_for_pc = i;
                        //если ячейка для блокировки не занята O включаем блок
                        if (map[x_for_pc][y_for_pc] != DOT_O) {
                            block = true;
                        }
                    }
                }
                if (map[j][i] == symbol) {
                    countColumn++;
                    //если после второго шага пользователя в столбце 2 или больше ячейки подряд заняты X то комп на следующем
                    // шаге блокирует следующую ячейку
                    if (symbol == DOT_X && player_steps >= 2 && countColumn >= 2) {
                        if (size - 1 > j) {
                            y_for_pc = j + 1;
                        } else y_for_pc = (size - 1) - countColumn;
                        x_for_pc = i;
                        //если ячейка для блокировки не занята O включаем блок
                        if (map[x_for_pc][y_for_pc] != DOT_O) {
                            block = true;
                        }
                    }
                }
                if (map[i] == map[j] && map[i][j] == symbol) {
                    countDiagonalRight++;
                    //если после второго шага пользователя по диагонали 2 или больше ячейки подряд заняты X то комп на следующем
                    // шаге блокирует следующую ячейку
                    if (symbol == DOT_X && player_steps >= 2 && countDiagonalRight >= 2) {
                        if (size - 1 > j) {
                            y_for_pc = j + 1;
                            x_for_pc = i + 1;
                        } else {
                            y_for_pc = (size - 1) - countDiagonalRight;
                            x_for_pc = (size - 1) - countDiagonalRight;
                        }
                        //если ячейка для блокировки не занята O включаем блок
                        if (map[x_for_pc][y_for_pc] != DOT_O) {
                            block = true;
                        }
                    }
                }
                //если счетчики диагоналей или столбцов, или строк равны колличеству точек для выигрыша, то возвращаем true
                if (countRow == dots_to_win || countColumn == dots_to_win || countDiagonalRight == dots_to_win ||
                        countDiagonalBack == dots_to_win) {
                    return true;
                }
            }
            //уменьшаем счетчик для обратной диагонали
            count--;
        }
        return false;
    }

    //проверка заполненности поля
    public static void isFilled() {
        int count = 0;
        //проходим поле
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //если ячейка занята - увеличиваем счетчик
                if (map[i][j] != DOT_EMPTY) {
                    count++;
                }
            }
        }
        //если счетчик равен количеству ячеек - ничья
        if (count == size * size) {
            System.out.println("Все поля заполнены - ничья.");
            System.out.println("Играть снова? 1-да / 0-нет");
            if (scanner.nextInt() == 1) {
                initMap();
            } else return;
        }
    }


}

