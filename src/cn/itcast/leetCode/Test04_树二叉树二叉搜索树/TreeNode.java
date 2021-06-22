package leetCode.Test04_树二叉树二叉搜索树;

/**
 * @program: Design_partterns
 * @description 二叉树节点
 * @author: Mr.Yang
 * @create: 2021-02-18 10:33
 **/
public class TreeNode {
    //Definition for a binary tree node.

        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val){
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

}
