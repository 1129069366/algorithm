package leetCode.Test08_分治回溯;

import java.util.*;

/**
 * @program: Design_partterns
 * @description
 * @author: Mr.Yang
 * @create: 2021-02-22 09:49
 **/
public class Test {

    public static void main(String[] args) {
        int[] nums = {1,2,3,3};
        int result = majorityElement(nums);
        System.out.println(result);

    }

   //4. N 皇后

    //3 . 求众数 II
    //    给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
    //
    //    进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
    //
    public List<Integer> majorityElement4(int[] nums) {

        List<Integer> res =new ArrayList<>();
        int cand1 = nums[0],count1 = 0;
        int cand2 =  nums[0],count2 = 0;
        //配对阶段
        for (int num : nums) {


            if(cand1==num){
                count1++;
                continue;
            }
            if(cand2==num){
                count2++;
                continue;
            }

            //第一个候选人配对
            if(count1==0){
                cand1 = num;
                count1++;
                continue;
            }
            //第二个候选人配对
            if(count2==0){
                cand2 = num;
                count2++;
                continue;
            }

            count1--;
            count2--;

        }
        //计数阶段
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if(cand1==num){
                count1++;
            }else if(cand2==num){
                count2++;
            }
        }

        if(count1>nums.length/3){
            res.add(cand1);
        }
        if(count2>nums.length/3){
            res.add(cand2);
        }
        return res;
    }


    //2. 电话号码的字母组合
    //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。  答案可以按任意顺序返回。
    //给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。


    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        int len = digits.length();
        StringBuilder sb = new StringBuilder();
        letterCombinations(phoneMap,res,digits,0,sb,len);

        return res;
    }

    private void letterCombinations(Map<Character, String> phoneMap,List<String> res, String digits, int index,StringBuilder sb, int len) {
        if(sb.length()==len){
            res.add(sb.toString());
        }else{
            char c = digits.charAt(index);
            String letters = phoneMap.get(c);  //获取字符对应的字符串  如获取4对应的ghi
            int length = letters.length();
            for (int i = 0; i < length; i++) {
                sb.append(letters.charAt(i));
                letterCombinations(phoneMap,res,digits,index+1,sb,len);
                //回溯
                sb.deleteCharAt(index);
            }

        }
    }


    //1. 多数元素
    //给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
    //你可以假设数组是非空的，并且给定的数组总是存在多数元素。
    //(4) 摩尔投票法
    public int majorityElement3(int[] nums) {

        //摩尔投票法
        int can_num = nums[0];
        int count = 1;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (can_num == nums[i]) {
                count++;
            } else if (--count == 0) {
                can_num = nums[i];  //投票给自己
                count = 1;
            }
        }

        return can_num;
    }

    //多数元素
    //给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
    //你可以假设数组是非空的，并且给定的数组总是存在多数元素。
    //(3) 排序解法
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        return nums[length/2];
    }


    //2. 多数元素
    //给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
    //你可以假设数组是非空的，并且给定的数组总是存在多数元素。
    //(2) 分治解法

    public int majorityElement1(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }


    //给一个数组,一个数字,求得该数字在数组指定范围内出现的次数
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }
    //分治 递归
    private int majorityElementRec(int[] nums, int lo, int hi) {
        //给定一个数组  若左右指针相同说明只有一个数字,最多的数字就是它本身
        if (lo == hi) {
            return nums[lo];
        }

        // 递归到下一层  分别找左右两个数组内的最多的数字
        int mid = (hi - lo) / 2 + lo;              //(hi + lo)有可能越界  超出int的表示范围 所以这样写挺好
        int left = majorityElementRec(nums, lo, mid);    //left为左边数组出现最多次的
        int right = majorityElementRec(nums, mid + 1, hi);  //right为左边数组出现最多次的


        //处理当前层
        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }
        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }


    //1. 多数元素
    //给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
    //你可以假设数组是非空的，并且给定的数组总是存在多数元素。
    //(1) 哈希表解法

    public static int majorityElement(int[] nums) {      //时间复杂度和空间复杂度都是O(n)
        int len = nums.length;
        Map<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(hashMap.get(nums[i])==null){
                hashMap.put(nums[i],1);
            }else{
                int value = hashMap.get(nums[i]);
                hashMap.put(nums[i],++value);
            }
        }

        Set<Integer> keySet = hashMap.keySet();
        for (Integer key : keySet) {
            if(hashMap.get(key)>=(len/2)){
                return key;
            }
        }
        return 0;
       //代码优化
   /*     public int majorityElement(int[] nums) {
            Map<Integer, Integer> counter = new HashMap<>();
            // 遍历每个数统计次数
            for (int num: nums) {
                counter.put(num, counter.getOrDefault(num, 0) + 1);
                // 如果某个数次数超过了n/2就返回
                if (counter.get(num) > nums.length / 2) {
                    return num;
                }
            }
            return -1;
        }*/

    }


    //2.子集   给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
    //解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。    //深度优先搜索算法

    public static List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        subsets(res,0,nums,path,len);
        return res;
    }

    private static void subsets(List<List<Integer>> res,int level, int[] nums, List<Integer> path, int len) {

        if(level==len){
            res.add(new ArrayList<>(path));
            return;
        }

        //选择当前数字
        path.add(nums[level]);
        subsets(res,level+1,nums,path,len);

        //不选择当前数字
        path.remove(path.size()-1);
        subsets(res,level+1,nums,path,len);

    }


    //1. 实现 pow(x, n) ，即计算x的n次幂函数(即，x^n)。
    //(2) 分治   比如2^5 = 2^2*2^2*2   2^6 = 2^3*2^3
    public double myPow1(double x, int n) {

        return n>0?_myPow1(x,n):1.0/_myPow1(x,-n);

    }

    private double _myPow1(double x, int n) {
        if(n==0){
            return 1.0;
        }
        double y = _myPow1(x,n/2);     //这里用y记录递归一一半值   不用调用两次,否则没有意义,时间复杂度依旧很高
        return n%2==0?y*y:y*y*x;
    }



    //1. 实现 pow(x, n) ，即计算x的n次幂函数(即，x^n)。
    //(1) 暴力递归
    public static double myPow(double x, int n) {

        return n>0?_myPow(x,n):1/_myPow(x,-n);

    }
    //这里当n大于0
    public static double _myPow(double x, int n) {
        if(n==0){
            return 1.0;
        }
        return x*(_myPow(x,n-1));
    }

}
