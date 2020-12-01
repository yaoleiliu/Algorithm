package TreePrint;

import com.sun.source.tree.Tree;

import java.util.Stack;
import java.util.LinkedList;

public class noRecursivePrint {
    public static void main(String[] agrs){

        //初始化树结构
        TreeNode a = new TreeNode("7", null, null);
        TreeNode b = new TreeNode("8", null, null);
        TreeNode c = new TreeNode("4", null, null);
        TreeNode d = new TreeNode("6", null, null);
        TreeNode e = new TreeNode("5", a, b);
        TreeNode f = new TreeNode("3", e, d);
        TreeNode g = new TreeNode("2", c, null);
        TreeNode root = new TreeNode("1", g, f);

        System.out.println("---先序打印二叉树---");
        preOrderPrint(root);
        System.out.println("---先序打印二叉树完成---");

        System.out.println("---中序打印二叉树---");
        middleOrderPrint(root);
        System.out.println("---中序打印二叉树完成---");

        System.out.println("---后序打印二叉树---");
        aftOrderPrint(root);
        System.out.println("---后序打印二叉树完成---");

        System.out.println("---层次打印二叉树---");
        levelOrderPrint(root);
        System.out.println("---层次打印二叉树完成---");
    }

    /**
     * 先序遍历打印二叉树
     * 思路：1）设置两层循环；2）外层循环终止条件，节点不为空或栈不为空；3）内层循环，子循环一：当节点不为空时，打印节点值并把该节点放到栈里
     * 然后将节点指向节点的左孩子；子循环二：当节点为空且栈不为空时，节点等于栈的弹出节点，并将节点指向节点的右孩子
     */
    public static void preOrderPrint(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;

        while(treeNode!=null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                System.out.println(treeNode.data);
                treeNode = treeNode.leftChild;
            }

            while (treeNode==null && !stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 中序遍历打印二叉树
     * 思路：仿照前序遍历，把打印环节放在第二个子循环内即可
     */
    public static void middleOrderPrint(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;

        while(treeNode!=null || !stack.isEmpty()){
            while(treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }

            while(treeNode==null && !stack.isEmpty()){
                treeNode = stack.pop();
                System.out.println(treeNode.data);
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 后序遍历二叉树
     * 思路：设定节点lastVisit，记录上次打印过的节点
     */
    public static void aftOrderPrint(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        TreeNode lastVisit = null;

        while(treeNode!=null || !stack.isEmpty()){
            while(treeNode!=null){
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }

            while(treeNode==null && !stack.isEmpty()){
                treeNode = stack.pop();
                if(treeNode.rightChild==null || treeNode.rightChild==lastVisit){
                    System.out.println(treeNode.data);
                    lastVisit = treeNode;
                    treeNode = null;
                } else {
                    stack.push(treeNode);
                    treeNode = treeNode.rightChild;
                }
            }
        }
    }

    /**
     * 层次遍历二叉树, 且按照行序打印
     * 设置两个变量，last:表示正在打印的当前行最右节点；nlast:表示下一行的最右节点。
     * 如何更新两个变量：1）nlast一直跟踪记录宽度优先遍历队列中加入的最新节点；2）弹出的节点与last相等，说明该换行了，让last与nlast相等
     */
    public static void levelOrderPrint(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode last  = root;
        TreeNode nlast = null;

        while(!queue.isEmpty()){
            TreeNode treeNode = queue.pop();
            System.out.print(treeNode.data);

            if(treeNode.leftChild!=null) {
                queue.add(treeNode.leftChild);
                nlast = treeNode.leftChild;
            }

            if(treeNode.rightChild!=null) {
                queue.add(treeNode.rightChild);
                nlast = treeNode.rightChild;
            }

            if(treeNode==last){
                System.out.print("\n");
                last = nlast;
            }
        }
    }
}
