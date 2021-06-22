package leetCode.Test10贪心算法;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description
 * @author: Mr.Yang
 * @create: 2021-03-07 11:53
 **/
public class Test {
    public static void main(String[] args) {

    }

    //5. 零钱兑换
    //    给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
    //
    //    你可以认为每种硬币的数量是无限的。


//    public int coinChange(int[] coins, int amount) {
//
//
//
//
//    }




    //4. 模拟行走机器人
    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0;
        int y = 0;
        int direction = 0;  //方向为0  向东  为1 向北
        for (int i = 0; i < commands.length; i++) {
            if(commands[i]==-1){
                direction = 0;
            }
            if(commands[i]==-2){
                direction = 1;
            }
            while(direction==0){
                x+=i;
            }

        }


        return 0;
    }


    //3. 分发饼干
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0,j=0;
        while(i<g.length && j<s.length){
            if(s[j]<g[i]){
                j++;
            }else{
                i++;
                j++;
            }
        }
        return i;
    }


   //2. 买卖股票的最佳时机 II
    public int maxProfit(int[] prices) {
        int profit=0;//利润
        for (int i = 0; i < prices.length; i++) {
            if(prices[i+1]>prices[i]){
                int temp = prices[i+1]-prices[i];
                profit+=temp;
            }

        }
        return profit;
    }

    //1. 柠檬水找零
    public boolean lemonadeChange(int[] bills) {
        if(bills==null||bills.length==0){
            return false;
        }
        int count_5 = 0;  //用来记录5元的张数
        int count_10 = 0;
        for (int i = 0; i < bills.length; i++) {

            if(bills[i]==5){
                count_5++;
            }
            if(bills[i]==10){
                if(count_5<1){
                    return false;
                }else{
                    count_5--;
                    count_10++;
                }
            }
            if(bills[i]==20){
                if(count_10>=1&&count_5>=1){
                    count_10--;
                    count_5--;
                }else if(count_5>=3){
                    count_5-=3;
                }else{
                    return  false;
                }
            }
        }
        return true;
    }
}
