package leetCode.Test11二分查找;

/**
 * @program: algorithm
 * @description
 * @author: Mr.Yang
 * @create: 2021-10-02 21:03
 **/
public class Test {
    public static void main(String[] args) {

      /*  int[] nums = {-1,0,3,5,9,12};
        int target = 9;
        int result = BinarySearch(0,nums.length-1,nums,target);
        System.out.println(result);*/

    }

    // 1.给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
    public static int BinarySearch(int low,int high,int[] nums,int target){

        while (low <= high){
            int middle = (high-low) / 2 + low;

            if(nums[middle] == target){
                return middle;
            }else if (nums[middle] > target){
                high = middle - 1;
            }else{
                low = middle + 1;
            }
        }
        return -1;
    }

}





