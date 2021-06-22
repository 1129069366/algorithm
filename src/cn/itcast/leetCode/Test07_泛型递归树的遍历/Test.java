package leetCode.Test07_泛型递归树的遍历;

import java.util.*;

/**
 * @program: Design_partterns
 * @description
 * @author: Mr.Yang
 * @create: 2021-02-19 21:49
 **/
public class Test {

    //用来做生成括号题目
    private static List<String> result;


    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        generateParenthesis(3);
        System.out.println(result);
    }

    //组合问题  给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        combine(res,path,1,n,k);

        return res;
    }

    private void combine(List<List<Integer>> res, List<Integer> path, int begin, int n, int k) {
        if(path.size()==k){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin ; i <= n ; i++) {
            path.add(i);

            //进入下一层
            combine(res,path,i+1,n,k);

            //回溯
            path.remove(path.size()-1);

        }

    }




    //全排列2   给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列
    //  先给nums排序  再全遍历  中间剪枝
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();
        permuteUnique(res, nums, path, used, len);
        return res;

    }

    private void permuteUnique(List<List<Integer>> res, int[] nums, List<Integer> path, boolean[] used, int len) {
        if (path.size() == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;

            permuteUnique(res, nums, path, used, len);
            //回溯
            path.remove(path.size() - 1);
            used[i] = false;

        }

    }





    //全排列1  给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    public static List<List<Integer>> permute(int[] nums) {
       int len = nums.length;
       boolean[] used = new boolean[len];
       List<List<Integer>> res = new ArrayList<>();
       List<Integer> path = new ArrayList<>();

       permute(res,nums,used,path,len);

       return res;
    }

    private static void permute(List<List<Integer>> res,int[] nums, boolean[] used, List<Integer> path, int len) {
        if(path.size()==len){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
              path.add(nums[i]);
              used[i] = true;

             //进入下一层
             permute(res,nums,used,path,len);

             //回溯
             used[i] = false;
             path.remove(path.size()-1);

            }
        }

    }







    // 从前序与中序遍历序列构造二叉树  (1)递归实现
    private Map<Integer,Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i],i);
        }
        int n = preorder.length;
        return buildTree(preorder,inorder,0,n-1,0,n-1);
    }

    private TreeNode buildTree(int[] preOrder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if(preLeft > preRight){
            return null;
        }

        int preOrder_root = preLeft;
        int inOrder_root = indexMap.get(preOrder[preLeft]);//把前序遍历第一个节点去对应一下在中序遍历中的位置
        //把根节点建立出来
        TreeNode root = new TreeNode(inorder[inOrder_root]);

        int left_size =  inOrder_root-inLeft;   //左子树大小
        root.left = buildTree(preOrder,inorder,preLeft+1,preLeft+left_size,inLeft,inOrder_root-1);
        root.right = buildTree(preOrder,inorder,preLeft+left_size+1,preRight,inOrder_root+1,inRight);

        return root;
    }


    // 二叉树的最近公共祖先 (2) 一次遍历求解
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true){
            if(p.val<ancestor.val && q.val<ancestor.val){
                ancestor = ancestor.left;
            }else if(p.val>ancestor.val &&q.val>ancestor.val){
                ancestor = ancestor.right;
            }else{
                break;
            }

        }
        return ancestor;

    }

    // 二叉树的最近公共祖先 (1) 二次遍历求解
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> path1 = findRoute(root, p);
        List<TreeNode> path2 = findRoute(root,q);
        TreeNode ancestor = null;
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            if(path1.get(i)==path2.get(i)){
                ancestor = path1.get(i);
            }else{
                break;
            }
        }
        return ancestor;
    }

    public List<TreeNode> findRoute(TreeNode root,TreeNode target){
        //没有考虑节点不在树里面的情况
        List<TreeNode> path = new ArrayList<>();
        while (root!=target){
            path.add(root);
            if(root.val<target.val){
                root = root.right;
            }else if(root.val>target.val){
                root = root.left;
            }
        }
        path.add(root);  //找到这个节点了
        return path;

    }


    //6. 二叉树的最小深度  1. 递归实现
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        //处理当前层并且进入下一层
        int min = minDepth(root.left)<maxDepth(root.right)?maxDepth(root.left)+1:maxDepth(root.right)+1;

        return min;

    }





    //5. 二叉树的最大深度  1. 递归实现
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        //处理当前层并且进入下一层
        int max = maxDepth(root.left)>maxDepth(root.right)?maxDepth(root.left)+1:maxDepth(root.right)+1;

        return max;

    }

    //4. 验证二叉搜索树  (2)中序遍历一遍如果是  小到大说明是   并且用一个栈来保存遍历过的数字
    public boolean isValidBST1(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;

            root = root.right;
        }
        return true;


    }



    //4. 验证二叉搜索树  (1)递归实现
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    public  boolean isValidBST(TreeNode root,long min,long max) {
        if(root==null){
            return true;
        }
        //正难则反
        if(root.val > max || root.val < min ){
            return false;
        }
        return isValidBST(root.left,root.val,max) && isValidBST(root.right,min,root.val);
    }


      //3. 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        if(root.left==null && root.right==null){
            return root;
        }

        //递归进入下一层
        invertTree(root.left);
        invertTree(root.right);

        //处理当前层
        TreeNode temp = new TreeNode();
        temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;

    }

    //定义一个二叉树
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //2. 括号生成
    public static List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        _generate(0,0,3,"");
        return result;
    }

    public static void  _generate(int left, int right ,int max,String s) {
        if(left == max && right == max){
            result.add(s);
            return;
        }
        //递归调用自身可以不止一次
        if(left<max)
           _generate(left+1,right,max,s+"(");
        if(right<left)
            _generate(left,right+1,max,s+")");


    }

    /**
     * 分析过程代码
     * @param n
     * @return
     */
 /*   public static List<String> generateParenthesis(int n) {
//        result = new ArrayList<>();
        _generate(0,2 * n,"");
//        return result;
        return null;
    }

    public static void  _generate(int level, int max, String s) {
        if(level>=max){
            System.out.println(s);
            return;
        }
        //递归调用自身可以不止一次
        _generate(level+1,max,s+="(");
        _generate(level+1,max,s+=")");


    }*/

    //1. 爬楼梯
//    假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
//    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    public int climbStairs(int n) {
       if(n<=2){
           return n;
       }
       return climbStairs(n-1)+climbStairs(n-2);
    }


}
