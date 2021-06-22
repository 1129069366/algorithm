package leetCode.Test04_树二叉树二叉搜索树;

import jdk.nashorn.internal.ir.ReturnNode;

import javax.swing.*;
import java.util.*;

/**
 * @program: Design_partterns
 * @description
 * @author: Mr.Yang
 * @create: 2021-02-18 10:23
 **/
public class Test {
    public static void main(String[] args) {

    }

    LinkedList<Integer> list = new LinkedList<>();

    //3.N叉树的后序遍历  (2)利用栈求解
    public List<Integer> postorder1(Node root) {
        Stack<Node> stack = new Stack<>();
        if(root!=null){
            stack.push(root);
        }
        while(!stack.isEmpty()){
            Node node = stack.pop();
            list.addFirst(node.val);
            node.children.forEach(children->{
                stack.push(children);
            });
        }
        return list;

    }



    //3.N叉树的后序遍历  (1)递归求解
    public List<Integer> postorder(Node root) {
        if(root!=null){
            List<Node> children = root.children;
            children.forEach(node->{
                postorder(node);
            });
            list.add(root.val);
        }
        return list;
    }
    //二叉树的后序遍历  利用栈迭代实现
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p!=null){
            while (p!=null){
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            stack.push(p);
            p = p.right;
        }
        while (!stack.isEmpty()){
            list.add(stack.pop().val);
        }

        return list;
    }




    //2.二叉树的前序遍历  (2)利用栈迭代实现
    public List<Integer> preorderTraversal1(TreeNode root) {
        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<>();
        while (p!=null||!stack.isEmpty()){
            while (p!=null){
                list.add(p.val);
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            p = p.right;
        }
        return list;
    }
   //2.二叉树的前序遍历  (2)利用递归实现
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root!=null){
            list.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return list;
    }


    //1.二叉树的中序遍历  (2)利用栈迭代实现
    public List<Integer> inorderTraversal1(TreeNode root) {
        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<>();
        while (p!=null||!stack.isEmpty()){
            if(p!=null){
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            list.add(p.val);
            p = p.right;
        }
        return list;
    }


    //1.二叉树的中序遍历  (1)递归实现
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root!=null){
            inorderTraversal(root.left);
            list.add(root.val);
            inorderTraversal(root.right);
        }
        return list;
    }

}



