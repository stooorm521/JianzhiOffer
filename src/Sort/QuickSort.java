package Sort;

import java.security.InvalidParameterException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    int partition(int[] data, int length, int start, int end){
        if(data==null||length<0||start<0||end>length){
            throw new InvalidParameterException();
        }
        int randNum= ThreadLocalRandom.current().nextInt(start, end+1);
    }
}
