import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        long start_time = System.currentTimeMillis();
        String filename = "array_cost_direction.txt";
        if (args.length > 0) {
            filename = args[0];
        }
        File f1 = new File(filename);
        System.out.println(f1.getAbsolutePath());
        BufferedReader input = new BufferedReader(new FileReader(f1));
        BufferedReader input1 = new BufferedReader(new FileReader(f1));
        int NUMBER_CITIES = 0;
        try {
            while (input1.readLine() != null) {
                NUMBER_CITIES++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Количество городов: " + NUMBER_CITIES);
        int[][] array = new int[NUMBER_CITIES][NUMBER_CITIES];
        int i = 0;
        try {
            String line;
            while ((line = input.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                int j = 0;
                while (st.hasMoreTokens()) {
                    array[i][j] = Integer.parseInt(st.nextToken());
                    j++;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int x = 0;
        x = reduce(array, x, 9999, 9999);
        lobject l1 = new lobject(NUMBER_CITIES);
        l1.city = 0;
        l1.cost = x;
        l1.matrix = array;
        l1.st.push(0);
        l1.remainingcity = new int[NUMBER_CITIES - 1];
        l1.city_left_to_expand = NUMBER_CITIES - 1;
        for (int o = 0; o < NUMBER_CITIES - 1; o++) {
            l1.remainingcity[o] = o + 1;
        }
        int count = 0;
        Stack<lobject> s = new Stack<>();
        s.push(l1);
        lobject temp_best_solution = new lobject(NUMBER_CITIES);
        int current_best_cost = 100000;
        while (!s.empty()) {
            List main = new LinkedList();
            main = new ArrayList();
            lobject hell = s.pop();
            if (hell.city_left_to_expand == 0) {
                if (hell.cost <= current_best_cost) {
                    temp_best_solution = hell;
                    current_best_cost = temp_best_solution.cost;
                }
            } else {
                if (hell.cost <= current_best_cost) {
                    count++;
                    expand(main, hell);
                    int[] arrow = new int[main.size()];
                    for (int pi = 0; pi < main.size(); pi++) {
                        lobject help = (lobject) main.get(pi);
                        arrow[pi] = help.cost;
                    }
                    int[] tempppp = decreasing_sort(arrow);
                    for (int pi = 0; pi < tempppp.length; pi++) {
                        s.push((lobject) main.get(tempppp[pi]));
                    }
                    for (lobject lobject : s) {
                        output(lobject);
                    }
                }
            }
        }
        long stop_time = System.currentTimeMillis();
        long run_time = stop_time - start_time;
        if (temp_best_solution.st.size() == NUMBER_CITIES & temp_best_solution.cost < 9000) {
            System.out.println();
            System.out.println("cost: " + current_best_cost);
            System.out.println();
            System.out.print("[ ");
            for (int st_i = 0; st_i < temp_best_solution.st.size(); st_i++) {
                Integer k = temp_best_solution.st.get(st_i);
                System.out.print(k + 1);
                System.out.print(" -> ");
            }
            System.out.print("1 ]");
            System.out.println();
            System.out.println();
            System.out.println(run_time + "ms");
            System.out.println();
            System.out.println("nodes expanded: " + count);
            System.out.println();
        } else {
            System.out.println("\nNo Solution.\n");
            System.out.println(run_time+ "ms");
            System.out.println();
        }
    }

    private static void toConsole(Stack<lobject> s) {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (lobject lobject : s) {
            str.append(toString(lobject)).append(",\n");
        }
        str.append("]");
        System.out.println(str);
    }

    private static void toConsole(int[][] array) {
        System.out.println(toString(array));
    }

    private static String toString(int[][] array) {
        StringBuilder str = new StringBuilder();
        str.append("[\n");
        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt > 4500) {
                    str.append(9999-anInt);
                } else {
                    str.append(anInt);
                }
                str.append(",");
            }
            str.append("\n");
        }
        str.append("]");
        return str.toString();
    }

    public static String toString(lobject o) {
        return "lobject{" +
                "city=" + o.city +
                ", cost=" + o.cost +
                ", matrix=" + toString(o.matrix) +
                ", remainingcity=" + Arrays.toString(o.remainingcity) +
                ", city_left_to_expand=" + o.city_left_to_expand +
                ", st=" + o.st +
                '}';
    }

    public static int[] min(int[] array, int min) {
        for (int j = 0; j < array.length; j++) {
            array[j] = array[j] - min;
        }
        return array;
    }

    public static int minimum(int[] array) {
        int min = 9000;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        if (min == 9000) {
            return 0;
        }
        else {
            return min;
        }
    }


    public static class lobject implements Cloneable {
        int city;
        int cost;
        int[][] matrix;
        int[] remainingcity;
        int city_left_to_expand;
        Stack<Integer> st;

        lobject(int number) {
            matrix = new int[number][number];
            st = new Stack<Integer>();
        }
    }

    public static void output(lobject l1) {
        System.out.println("============================================");
        System.out.println("============================================");
        System.out.println("This city is :" + l1.city);
        System.out.println("The node cost function:" + l1.cost);
        System.out.println("The remaining cities to be expanded from this node");
        for (int h = 0; h < l1.remainingcity.length; h++) {
            System.out.print(l1.remainingcity[h]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("The number of possible remaining expansions from this node are: " + l1.city_left_to_expand);
        System.out.println();
        System.out.println("============================================");
        System.out.println("============================================");
    }

    public static void expand(List l, lobject o) {
        int length = o.remainingcity.length;
        for (int i = 0; i < length; i++) {
            if (o.remainingcity[i] == 0) continue;
            int cost;
            cost = o.cost;
            int city = o.city;
            Stack<Integer> st = new Stack<>();
            for (int st_i = 0; st_i < o.st.size(); st_i++) {
                Integer k = o.st.get(st_i);
                st.push(k);
            }
            st.push(o.remainingcity[i]);
            int[][] temparray = new int[o.matrix.length][o.matrix.length];
            for (int i_1 = 0; i_1 < temparray.length; i_1++) {
                for (int i_2 = 0; i_2 < temparray.length; i_2++) {
                    temparray[i_1][i_2] = o.matrix[i_1][i_2];
                }
            }
            cost = cost + temparray[city][o.remainingcity[i]];
            for (int j = 0; j < temparray.length; j++) {
                temparray[city][j] = 9999;
                temparray[j][o.remainingcity[i]] = 9999;
            }
            temparray[o.remainingcity[i]][0] = 9999;
            int cost1 = reduce(temparray, cost, city, o.remainingcity[i]);
            lobject finall = new lobject(o.matrix.length);
            finall.city = o.remainingcity[i];
            finall.cost = cost1;
            finall.matrix = temparray;
            int[] temp_array = new int[o.remainingcity.length];
            for (int i_3 = 0; i_3 < temp_array.length; i_3++) {
                temp_array[i_3] = o.remainingcity[i_3];
            }
            temp_array[i] = 0;
            finall.remainingcity = temp_array;
            finall.city_left_to_expand = o.city_left_to_expand - 1;
            finall.st = st;
            l.add(finall);
        }
    }

    public static int reduce(int[][] array, int cost, int row, int column) {
        int[] array_to_reduce = new int[array.length];
        int[] reduced_array = new int[0];
        int new_cost = cost;
        // Петля для уменьшения рядов
        for (int i = 0; i < array.length; i++) {
            if (i == row) continue;
            for (int j = 0; j < array.length; j++) {
                array_to_reduce[j] = array[i][j];
            }
            if (minimum(array_to_reduce) != 0) {
                new_cost = minimum(array_to_reduce) + new_cost;
                reduced_array = min(array_to_reduce, minimum(array_to_reduce));
                for (int k = 0; k < array.length; k++) {
                    array[i][k] = reduced_array[k];
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (i == column) continue;
            for (int j = 0; j < array.length; j++) {
                array_to_reduce[j] = array[j][i];
            }
            if (minimum(array_to_reduce) != 0) {
                new_cost = minimum(array_to_reduce) + new_cost;
                reduced_array = min(array_to_reduce, minimum(array_to_reduce));
                for (int k = 0; k < array.length; k++) {
                    array[k][i] = reduced_array[k];
                }
            }
        }
        System.out.println("уменьшенные значения: \n" + toString(reduced_array));
        System.out.println("уменьшенная матрица: \n" + toString(array));
        System.out.println();
        return new_cost;
    }

    private static String toString(int[] reduced_array) {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i : reduced_array) {
            if (i > 4500) {
                s.append(9999-i);
            } else {
                s.append(i);
            }
            s.append(",");
        }
        s.append("]");
        return s.toString();
    }

    public static int[] decreasing_sort(int[] temp) {
        int[] y = new int[temp.length];
        // Получение содержимого массива
        for (int j = 0; j < temp.length; j++) {
            y[j] = temp[j];
        }
        int x = 0;
        for (int i = 0; i < temp.length - 1; i++) {
            if (temp[i] < temp[i + 1]) {
                x = temp[i];
                temp[i] = temp[i + 1];
                temp[i + 1] = x;
            }
        }
        int[] to_be_returned = new int[temp.length];
        int f = 0;
        for (int j = 0; j < temp.length; j++) {
            for (int j1 = 0; j1 < temp.length; j1++) {
                if (temp[j] == y[j1]) {
                    to_be_returned[j] = j1;
                }
            }
        }
        return to_be_returned;
    }
}
