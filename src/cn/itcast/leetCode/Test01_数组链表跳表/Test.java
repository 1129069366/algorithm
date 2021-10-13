package leetCode.Test01_数组链表跳表;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {


    }

    //7.在排序数组中查找元素的第一个和最后一个位置
    

    //6. 搜索插入位置
    public static int searchInsert(int[] nums, int target) {
        int low = 0;int high = nums.length-1;
        int middle = 0;
        while(low <= high){
            middle = (high - low)/2 + low;
            if(nums[middle]==target){
                return middle;
            }else if(nums[middle] > target){
                high = middle -1;
            }else{
                low = middle +1;
            }
        }
        for(int i = 0;i < nums.length;i++){
            if(nums[i] > target){
                return i;
            }
        }
        return nums.length;

    }


    //5.移除元素
     public static int removeElement(int[] nums, int val) {
        int i = 0,j = 0;
        for(;j < nums.length;j++){
            if(nums[j]!=val){
                nums[i] = nums[j];
                nums[j] = 0;
                i++;
            }
        }
        return i;
    }


    //4. 翻转单链表  (2)迭代解法
    public ListNode reverseList1(ListNode head) {
        ListNode pre= null;
        ListNode curr = head;
        while (curr!=null&&curr.next!=null){

            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return curr;

    }

    //4. 翻转单链表  (1)递归解法
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next ==null){
            return head;
        }
        ListNode pre = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return pre;
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val; this.next = next;
        }
  }
    //3.爬楼梯    (2)利用数组迭代求解
    public static int climbStairs1(int n) {
        int[] A = new int[n];
        for(int i = 0;i<n;i++){
            if(i<2){
                A[i] = i+1;
                continue;
            }
            A[i] = A[i-1]+A[i-2];
        }
        return A[n-1];
    }

    //3.爬楼梯    (1)递归求解    时间复杂度巨高
    public static int climbStairs(int n) {
        if(n == 1||n==2){
            return n;
        }
        return climbStairs(n-1)+climbStairs(n-2);
    }


    //2. 盛最多水的容器 (2)双指针解法
    public int maxArea1(int[] height) {
        int max = 0;
        int len = height.length;
        int i = 0,j = len-1;
        while (i<j){
            int minh = height[i]<height[j]?height[i++]:height[j--];
            int area = minh*(j-i+1);
            max = max>area?max:area;
        }

        return max;    //时间复杂度 O(n)
    }

    //2. 盛最多水的容器 (1)暴力解法
    public int maxArea(int[] height) {
        int max = 0;
        int len = height.length;
        for(int i = 0;i<len-1;i++){
            for (int j = i+1;j<len;j++){
                int minh = height[i]<height[j]?height[i]:height[j];
                max = max>minh*(j-i)?max:minh*(j-i);
            }
        }
        return max;    //时间复杂度 O(n^2)
    }



    //1.移动零
    // 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    //
    //示例:
    //
    //输入: [0,1,0,3,12]
    //输出: [1,3,12,0,0]
    //
    //说明:
    //   必须在原数组上操作，不能拷贝额外的数组。
    //   尽量减少操作次数。
    // (2). 双指针解法
    public void MoveZeros(int[] nums){
        int i=0,j=0;
        int len = nums.length;
        for (;j<len;j++){
            if(nums[j]!=0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
    }


    //1.移动零
    // 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    //
    //示例:
    //
    //输入: [0,1,0,3,12]
    //输出: [1,3,12,0,0]
    //
    //说明:
    //   必须在原数组上操作，不能拷贝额外的数组。
    //   尽量减少操作次数。
    // (1).拷贝数组法
    public void MoveZeros1(int[] nums) {
        int len = nums.length;
        int[] copy = new int[len];
        int k = 0;
        for (int i = 0; i < len; i++) {
            if(nums[i]!=0){
                copy[k++]=nums[i];
            }
        }
        for (int i = 0; i < len; i++) {
            nums[i]=copy[i];
        }

    }

}
