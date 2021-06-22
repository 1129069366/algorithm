package leetCode.Test03_哈希表集合映射;

import java.util.*;

/**
 * @program: algorithm
 * @description
 * @author: Mr.Yang
 * @create: 2021-02-26 09:48
 **/
public class Test {
    public static void main(String[] args) {

    }

    //2  字母异位词分组
    //给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
    //(2)维护哈希表完成    维护频次数组获取键
    /*   输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
         输出:
             [
             ["ate","eat","tea"],
             ["nat","tan"],
             ["bat"]
     ]*/

    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String,List<String>> hashMap = new HashMap<>(); //键就是字母

        for (String str : strs) {
            int[] arr = new int[26];
            int len = str.length();
            for (int i = 0; i < len; i++) {
                arr[str.charAt(i)-'a']++;
            }

            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuilder sb = new StringBuilder();
            for (int i =0;i<26;i++) {
                if(arr[i]!=0){
                    sb.append((char)(i+'a'));
                    sb.append(arr[i]);
                }
            }
            String key = sb.toString();
            List<String> list = hashMap.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            hashMap.put(key,list);

        }

        return new ArrayList<List<String>>(hashMap.values());
    }


    //2  字母异位词分组
    //给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
    //(1)维护哈希表完成   排序获取键
    /*   输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
         输出:
             [
             ["ate","eat","tea"],
             ["nat","tan"],
             ["bat"]
     ]*/

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> hashMap = new HashMap<>(); //键就是字母

        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            if(hashMap.containsKey(s)){                               //优化
                List<String> list = hashMap.get(s);                   //  List<String> list = hashMap.getOrDefault(s,new ArrayList<String>());
                list.add(strs[i]);                                     // list.add(strs[i]);
                hashMap.put(s,list);                                   // hashMap.put(s,list);
            }else{
                ArrayList<String> list = new ArrayList<>();
                list.add(strs[i]);
                hashMap.put(s,list);
            }

        }

         return new ArrayList<List<String>>(hashMap.values());
    }


    //1. 有效的字母异位词:给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    //(3) 维护一个频次数组 实质也是哈希表思想
    public static boolean isAnagram2(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        //维护的频次数组
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i)-'a']++;
        }

        for (int i = 0; i < t.length() ; i++) {
            if(arr[t.charAt(i)-'a']<0){
                return false;
            }
        }

        return true;
    }


    //1. 有效的字母异位词:给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    //(2) 维护一个哈希表  判断字符元素是否出现过
    public static boolean isAnagram1(String s, String t) {
        Map<Character,Integer> hashMap = new HashMap<>();
        if(s.length()!=t.length()){
            return false;
        }
        char[] s_chars = s.toCharArray();
        char[] t_chars = t.toCharArray();

        for (int i = 0; i < s_chars.length; i++) {
            int num = 0;
            if(hashMap.get(s_chars[i])!=null){
                num = hashMap.get(s_chars[i]);
            }
            hashMap.put(s_chars[i],++num);
        }

        for (int i = 0; i < t_chars.length; i++) {
            int num = 0;
            if(hashMap.get(t_chars[i])!=null){
                num = hashMap.get(t_chars[i]);
            }
            hashMap.put(t_chars[i],--num);
            if(hashMap.get(t_chars[i])<0){
                return false;
            }
        }
        return true;
    }


    //1. 有效的字母异位词:给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    //(1) 将字符串转换成字符数组并且排序
     //时间复杂度O(nlogn)   空间复杂度O(n)
    public boolean isAnagram(String s, String t) {
        //两个字符串长度不相等  直接退出
        if(s.length()!=t.length()){
            return false;
        }
        char[] s_char = s.toCharArray();  Arrays.sort(s_char);
        char[] t_char = t.toCharArray();  Arrays.sort(t_char);

        String news = new String(s_char);
        String newt = new String(t_char);
        return news.equals(newt);
    }
}
