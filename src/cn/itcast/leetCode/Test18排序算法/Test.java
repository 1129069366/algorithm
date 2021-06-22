package leetCode.Test18排序算法;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description
 * @author: Mr.Yang
 * @create: 2021-03-07 09:25
 **/
public class Test {
    public static void main(String[] args) {
        int[] arr = {7,5,8,9,10,1};
        Test t = new Test();
        t.QuickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     * @param arr
     * @param begin
     * @param end
     */
    public void QuickSort(int[] arr,int begin ,int end){
        if(begin>=end){
            return;
        }

        int pivot =  partition(arr,begin,end);
        QuickSort(arr,begin,pivot-1);
        QuickSort(arr,pivot+1,end);

    }

    public int partition(int[] arr,int begin,int end){

        int pivot = end;  //定义标杆
        int count = begin;

        for(int i = begin;i < end;i++){
            if(arr[i]<arr[pivot]){
                int temp = arr[i];arr[i]=arr[count];arr[count]=temp;
                ++count;
            }
        }
        int temp = arr[pivot];
        arr[pivot] = arr[count];
        arr[count] = temp;
        return count;
    }

}
