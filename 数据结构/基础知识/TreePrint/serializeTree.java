package TreePrint;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import TreePrint.noRecursivePrint;

/**
 * 二叉树 -> 字符串（序列化）
 * 字符串 -> 二叉树 (反序列化)
 */
public class serializeTree {
    public static void main(String[] args){
        //建立一颗二叉树，然后进行序列化
        TreeNode a = new TreeNode("7", null, null);
        TreeNode b = new TreeNode("8", null, null);
        TreeNode c = new TreeNode("4", null, null);
        TreeNode d = new TreeNode("6", null, null);
        TreeNode e = new TreeNode("5", a, b);
        TreeNode f = new TreeNode("3", e, d);
        TreeNode g = new TreeNode("2", c, null);
        TreeNode root = new TreeNode("1", g, f);

        System.out.println("---开始序列化二叉树---");
        String strTree = serilze(root);
        System.out.println(strTree);
        System.out.println("---完成序列化二叉树---");

        System.out.println("---开始递归序列化二叉树---");
        strTree = recursiveSerialize(root);
        System.out.println(strTree);
        System.out.println("---完成递归序列化二叉树---");

        System.out.println("---开始递归反序列化二叉树---");
        root = recursiveReverSerialize(strTree);
        noRecursivePrint.preOrderPrint(root);
        System.out.println("---完成递归反序列化二叉树---");

        System.out.println("---开始非递归反序列化二叉树---");
        root = reverseSerialize(strTree);
        noRecursivePrint.preOrderPrint(root);
        System.out.println("---完成非递归反序列化二叉树---");
    }

    /**
     * 序列化二叉树
     * StringBuffer：线程安全的可变字符序列。一个类似于 String 的字符串缓冲区，但不能修改。虽然在任意时间点上它都包含某种特定的字符序列，
     * 但通过某些方法调用可以改变该序列的长度和内容。
     */
    public static String serilze(TreeNode root){
        StringBuffer stringBuffer = new StringBuffer();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;

        while (treeNode!=null || !stack.isEmpty()){
            while(treeNode!=null){
                stringBuffer.append(treeNode.data);
                stringBuffer.append("!");
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }

            while(treeNode==null && !stack.isEmpty()){
                if(treeNode==null){
                    stringBuffer.append("#");
                    stringBuffer.append("!");
                }

                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }

        return stringBuffer.toString();
    }

    /**
     * 递归实现先序遍历序列化二叉树
     */
    public static String recursiveSerialize(TreeNode root){
        if(root==null){
            return "#!";
        }

        String res = root.data + "!";
        res += recursiveSerialize(root.leftChild);
        res += recursiveSerialize(root.rightChild);

        return res;
    }

    /**
     *字符串反序列化为二叉树
     * 思路：用栈模仿递归
     */
    public static TreeNode reverseSerialize(String strTree){
        String[] values = strTree.split("!");
        Stack<TreeNode> stack = new Stack<>();

        TreeNode root = null;
        TreeNode treeNode = null;


        Integer index = 0;
        while(index<values.length){
            while(index<values.length && !values[index].equals("#")){
                treeNode = new TreeNode(values[index], null, null);
                if(index==0) {
                    root = treeNode;
                    index++;
                } else {
                    stack.peek().leftChild = treeNode;
                    index++;
                }
                stack.push(treeNode);
            }

            while(index<values.length && values[index].equals("#") && !stack.isEmpty()){
                treeNode = stack.pop();
                index++;
                if(values[index].equals("#")){
                    if(index==values.length-1){
                        return root;
                    }
                    continue;
                } else {
                    treeNode.rightChild = new TreeNode(values[index], null, null);
                    stack.push(treeNode.rightChild);
                    index++;
                }
            }
        }

        return root;
    }

    /**
     * 递归实现先序遍历反序列化二叉树
     */
    public static TreeNode recursiveReverSerialize(String strTree){
        String[] values = strTree.split("!");
        Queue<String> queue = new LinkedList<>();

        for(int i=0; i<values.length; i++){
            queue.offer(values[i]);
        }

        return recursiveByQueue(queue);
    }

    public static TreeNode recursiveByQueue(Queue<String> queue){
        String data = queue.poll();
        if(data.equals("#")){
            return null;
        }

        TreeNode root = new TreeNode(data, null, null);
        root.leftChild = recursiveByQueue(queue);
        root.rightChild = recursiveByQueue(queue);

        return root;
    }
}
