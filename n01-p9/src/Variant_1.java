public class Variant_1 {
    public Turn[] sort(int[] arr)
    {
        //Создание переменных и инициализация
        Turn[] turns = new Turn[10];
        for(int i=0;i<10;i++)
            turns[i] = new Turn(10);

        int maxDegree = maxDegr(arr);

        //распределение в очереди по 1-ым разрядам
        int curDich=10;
        Math.pow(curDich,maxDegree);
        for (int i = 0; i < arr.length; i++)
            turns[arr[i] % curDich].addLast(arr[i]);

        //Сортировка по остальным разрядам
        for (int i=2;i<=maxDegree;i++)
            turns = sortWithDeg(turns,i);

        return turns;
    }

    //Максимальный разряд
    public int maxDegr(int[] arr)
    {
        int discharge = 10;
        int maxDis=1;
        boolean flag = true;

        while (flag)
        {
            flag = false;
            for (int i=0;i<arr.length;i++)
                if((arr[i] / discharge)>0)
                    flag = true;

            if(flag)
                maxDis++;
            discharge = discharge*10;
        }
        return maxDis;
    }


    //Разряд числа,начиная с конца. Отсчёт с 1. 123 - 3:1, 2:2, 1:3
    public Turn[] sortWithDeg(Turn[] arr,int degree)
    {
        Turn[] result = new Turn[arr.length];
        degree = (int) Math.pow(10,degree);

        for(int i=0;i<10;i++)
            result[i] = new Turn(10);

        int tekDeg = 0;
        for (int i=0;i<arr.length;i++)
        {
            for(int j=1;j<=arr[i].getCurrentSize();j++)
            {
                tekDeg = arr[i].getElement(j)%degree/(degree/10);
                result[tekDeg].addLast(arr[i].getElement(j));
            }
        }
        return result;
    }
}
