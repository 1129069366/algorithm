package leetCode.Test09深度优先搜索和广度优先搜索;


import java.util.*;


public class Test {

    private static List<String> res;
    public static void main(String[] args) {

    }





    //课后作业
    //4. 扫雷游戏




    //3. 岛屿数量   (1)深度优先搜索
    // 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    //
    //岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    //
    //此外，你可以假设该网格的四条边均被水包围。
    public int numIslands(char[][] grid) {

        if(grid==null||grid.length==0){
            return 0;
        }

        int length = grid.length;  //岛屿长度 该二维数组有多少个一维数组
        int width  = grid[0].length;  //岛屿宽度  每个以为数组的长度
        int islandNum = 0; //记录岛屿数量

        for(int i = 0;i<length;i++){
            for(int j = 0;j < width;j++){
                if(grid[i][j]=='1'){
                    islandNum++;
                    dfs(grid,i,j);
                }
            }
        }

        return islandNum;

    }

    private void dfs(char[][] grid, int i, int j) {
        int length = grid.length;  //岛屿长度 该二维数组有多少个一维数组
        int width  = grid[0].length;  //岛屿宽度  每个以为数组的长度
        if(i<0||j<0||i>length||j>width||grid[i][j]!='1'){
            return;
        }

        grid[i][j] = 2;

        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);

    }

    //1. 单词接龙

    int minStepCount = Integer.MAX_VALUE;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        DFS(new HashSet<String>(),0,beginWord,endWord,wordList);
        return minStepCount==Integer.MAX_VALUE?-1:minStepCount;
    }

    private void DFS(HashSet<String> set, int stepCount, String currentWord, String endWord, List<String> wordList) {
        if(currentWord.equals(endWord)){
            minStepCount = stepCount;
        }

        wordList.forEach(word->{
            int size = word.length();
            int diff = 0;
            for(int i = 0;i < size;i++){

                if(currentWord.charAt(i)!=endWord.charAt(i)){
                    if(++diff>1){
                        break;
                    }
                }
            }
            if(diff==1 && !set.contains(word)){
                set.add(word);
                DFS(set,stepCount+1,word,endWord,wordList);
                set.remove(word);
            }
        });
    }


    //3. 在每个树行中找最大值
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null){
            return null;
        }
        Queue<TreeNode> queue  =new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                max = Math.max(max,curr.val);
                if(curr.left!=null){
                    queue.add(curr.left);
                }
                if(curr.right!=null){
                    queue.add(curr.right);
                }

            }
            res.add(max);
        }
        return res;
    }



//2. 最小基因变化
//    一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
//
//    假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
//
//    例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
//
//    与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
//
//    现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
//
//    注意：
//    起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
//    如果一个起始基因序列需要多次变化，那么它每一次变化之后的基因序列都必须是合法的。
//    假定起始基因序列与目标基因序列是不一样的。

    public int minMutation(String start, String end, String[] bank) {
        dfs(new HashSet<String>(),0,start,end,bank);
        return minStepCount==Integer.MAX_VALUE?-1:minStepCount;
    }

    private void dfs(HashSet<String> set,int stepCount, String current, String end, String[] bank) {
        if(current.equals(end)){
            minStepCount =  stepCount;
        }

        for (String s : bank) {
            int diff=0;
            int len = s.length();
            for(int i =0;i<len;i++){
                if(s.charAt(i)!=current.charAt(i)){
                    if(++diff>1){
                        break;
                    }
                }
            }
            if(diff==1&&!set.contains(s)){
                set.add(s);
                dfs(set,stepCount+1,s,end,bank);
                set.remove(s);
            }

        }

    }


    //1. 二叉树的层序遍历  给你一个二叉树，请你返回其按层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = deque.poll();
                list.add(curr.val);
                if(curr.left!=null){
                    deque.add(curr.left);
                }

                if(curr.right!=null){
                    deque.add(curr.right);
                }
            }
            res.add(list);
        }

        return res;

    }

}
