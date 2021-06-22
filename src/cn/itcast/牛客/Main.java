package 牛客;

import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        while(sc.hasNext()){
            int N = sc.nextInt();
            sheXing(N);
        }

    }

    /**
     * 蛇形矩阵
     */
    public static void  sheXing(int N){



        for (int i = 1; i <= N ; i++) {
            int x1 = i*(i-1)/2+1;
            System.out.print(x1+" ");
            for (int j = 2; j <= N-i+1; j++) {
                x1 = x1+(j+i-1);
                System.out.print(x1+" ");
            }
            System.out.println();
        }
    }



    /**
     * 提取不重复的整数
     */
    public static int getSingle(int num){
        Map<Character,Integer> map = new HashMap<>();
        String chars = String.valueOf(num);
        String res = "";

        for (int i = chars.length()-1; i >=0; i--) {
            if(!res.contains(chars.charAt(i)+"")){
                res+=chars.charAt(i);
            }
        }

        return Integer.parseInt(res);

    }


    /**
     * 挑7
     */
    public static int chooseSeven(int N){
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if(i==7){
                count++;
                continue;
            }
            Integer I = i;
            if(I.toString().contains("7")||(i%7)==0){
                count++;
            }


        }
        return count;

    }





    /**
     * 截取字符串
     */
    public static String subString(String s,int k){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < k; i++) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    /**
     * 图片整理   (快速排序的应用)
     */
    public static void quickSort(char[] arr,int low,int high){

        if(low >= high){
            return;
        }

        int pivot = parititon(arr,low,high);
        quickSort(arr,low,pivot-1);
        quickSort(arr,pivot+1,high);

    }

    private static int parititon(char[] arr, int low, int high) {
        int pivot = high;
        int count = low;

        for (int i = low; i < high; i++) {
            if(arr[i]<arr[pivot]){
                char temp = arr[i];
                arr[i] = arr[count];
                arr[count++] = temp;
            }
        }

        char temp = arr[count];
        arr[count] = arr[pivot];
        arr[high] = temp;
        return count;
    }


    /**
     * 放苹果
     * @param apple
     * @param n
     * @return
     */
    public static int putApple(int apple,int n){

        if(apple<=0||n<=0){
            return 0;
        }else if(apple ==1 || n ==1){
            return 1;
        }else{
            return putApple(apple,n-1)+putApple(apple-n,n);
        }
    }

    /**
     * 取近似值
     */
    public static int getSimilar(Double num){
        String s  = num.toString();
        String[] split = s.split("\\.");
        double v = Double.parseDouble("0."+split[1]);
        if(v < 0.5){
            return num.intValue();
        }else{
            return num.intValue()+1;
        }
    }
    /**
     * 字符个数统计
     */
    public static int countChar(String s){
        int res = 0;
        Map<Character,Integer> map = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if(map.get(s.charAt(i))==null){
                map.put(s.charAt(i),1);
            }else{
                map.put(s.charAt(i),map.get(s.charAt(i)+1));
            }

        }
        return map.keySet().size();
    }


    /**
     * 表达式求值
     */
    



    /**
     * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
     */
    public static int countOne(int a){
        int count = 0;
        while((a/2)!=0){
            if(a%2==1){
                count++;
            }
            a/=2;
        }
        return count+1;
    }
    /**
     * 字符串颠倒
     */
    public static String reverseString(String s){
        int i =0,j = s.length()-1;
        char[] chars = s.toCharArray();
        while (i<=j){
            char temp = chars[i];
            chars[i++] = chars[j];
            chars[j--] = temp;
        }
        return new String(chars);
    }
    /**
     * 数字颠倒
     * @param num
     * @return
     */
    public static String reverse(int num){
        String s = num+"";
        int i =0,j = s.length()-1;
        char[] chars = s.toCharArray();
        while (i<=j){
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }

        return new String(chars);
    }

}
