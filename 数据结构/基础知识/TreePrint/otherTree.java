package TreePrint;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 1）平衡二叉树；2）搜索二叉树；3）满二叉树；4）完全二叉树
 */

public class otherTree {
    public static void main(String[] args){

        //构建二叉树
        TreeNode a = new TreeNode("8", null, null);
        TreeNode b = new TreeNode("4", a, null);
        TreeNode c = new TreeNode("5", null, null);
        TreeNode d = new TreeNode("6", null, null);
        TreeNode e = new TreeNode("7", null, null);
        TreeNode f = new TreeNode("2", b, c);
        TreeNode g = new TreeNode("3", d, e);
        TreeNode root = new TreeNode("1", f, g);

        System.out.println("---递归判断二叉树是否为平衡二叉树---");
        if(isBanlance(root)){
            System.out.println("此二叉树是平衡二叉树！！！");
        } else {
            System.out.println("此二叉树不是平衡二叉树！！！");
        }
        System.out.println("---完成递归判断二叉树是否为平衡二叉树---");

        System.out.println("---非递归判断二叉树是否为平衡二叉树---");
        if(isBanlanceV1(root)){
            System.out.println("此二叉树是平衡二叉树！！！");
        } else {
            System.out.println("此二叉树不是平衡二叉树！！！");
        }
        System.out.println("---完成非递归判断二叉树是否为平衡二叉树---");

        System.out.println("---中序遍历判断二叉树是否为搜索二叉树---");
        if(isSearchTree(root)){
            System.out.println("此二叉树是搜索二叉树！！！");
        } else {
            System.out.println("此二叉树不是搜索二叉树！！！");
        }
        System.out.println("---完成中序遍历判断二叉树是否为搜索二叉树---");

        System.out.println("---层次遍历判断二叉树是否为完全二叉树---");
        if(isEntirelyTree(root)){
            System.out.println("此二叉树是完全二叉树！！！");
        } else {
            System.out.println("此二叉树不是完全二叉树！！！");
        }
        System.out.println("---完成层次遍历判断二叉树是否为完全二叉树---");
    }

    /**
     * 递归判断二叉树是否为平衡二叉树
     * 思路：在递归后续遍历的基础上进行改造
     */
    public static boolean isBanlance(TreeNode root){
        boolean[] res = new boolean[1];
        res[0] = true;
        isBanlanceHelper(root, 1, res);

        return res[0];
    }

    public static int isBanlanceHelper(TreeNode treeNode, int level, boolean[] res){
        if(treeNode==null){
            return level;
        }

        int lH = isBanlanceHelper(treeNode.leftChild, level+1, res);
        if(!res[0]){
            return level;
        }

        int rH = isBanlanceHelper(treeNode.rightChild, level+1, res);
        if(!res[0]){
            return level;
        }

        if(Math.abs(lH - rH)>1){
            res[0] = false;
        }

        return Math.max(lH, rH);
    }

    /**
     * 非递归判断二叉树是否为平衡二叉树
     * 思路：使用非递归方法分别求左右子树的高度
     */
    public static boolean isBanlanceV1(TreeNode root){
        if(root==null) return true;
        int lh = depth(root.leftChild);
        int rh = depth(root.rightChild);
        if(Math.abs(lh-rh)<=1){
            return isBanlanceV1(root.leftChild) && isBanlanceV1(root.rightChild);
        }

        return false;
    }

    public static int depth(TreeNode root){
        if(root==null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth=0;
        int size =0;

        while(!queue.isEmpty()){
            depth++;
            size = queue.size();
            while(size>0){
                TreeNode treeNode = queue.poll();
                if(treeNode.leftChild!=null) queue.add(treeNode.leftChild);
                if(treeNode.rightChild!=null) queue.add(treeNode.rightChild);
                size--;
            }
        }

        return depth;
    }

    /**
     * 判断一棵树是否为搜索二叉树
     * 思路：使用非递归中序遍历，遍历到每个节点的值时，如果一直比上一个遍历到的节点值要大，则是搜索二叉树；
     * 否则，不是搜索二叉树
     */
    public static boolean isSearchTree(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        String lastValue = "";

        while(treeNode!=null || !stack.isEmpty()){
            while(treeNode!=null){
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }

            while(treeNode==null && !stack.isEmpty()){
                treeNode = stack.pop();
                if(lastValue.equals("")){
                    lastValue = treeNode.data;
                } else {
                    if(Integer.valueOf(treeNode.data)<=Integer.valueOf(lastValue)){
                        return false;
                    } else {
                        treeNode = treeNode.rightChild;
                    }
                }
            }
        }

        return true;
    }

    /**
     * 满二叉树：满二叉树是除了最后一层的节点无任何节点外，剩下每一层的节点都有两个子节点
     * 完全二叉树：完全二叉树是指除了最后一层外，其它每一层的节点都是满的。如果最后一层也是满的，是一颗满二叉树，
     *           也是完全二叉树。最后一层如果不满，缺少的节点也全部在右边，那也是一颗完全二叉树
     * 完全二叉树判定方法：
     * 1）采用按层遍历二叉树的方式，从每层的左边向右边依次遍历所有的节点；
     * 2）如果当前节点有右孩子，但没有左孩子，直接返回false;
     * 3) 如果当前节点并不是左右孩子都有，那之后的节点都必须是叶节点，否则返回false；
     * 4）遍历过程中如果不返回false，遍历结束后返回true即可
     */
    public static boolean isEntirelyTree(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode last = root;
        TreeNode nlast = null;
        int level = 0;
        boolean hasTwoChild = true;

        while(!queue.isEmpty()){
            TreeNode treeNode = queue.pop();
            if(level>0 && treeNode.leftChild==null && treeNode.rightChild!=null){
                return false;
            }

            if(!hasTwoChild && (treeNode.leftChild!=null || treeNode.rightChild!=null)){
                return false;
            }

            if(treeNode.leftChild!=null && treeNode.rightChild==null){
                hasTwoChild = false;
            }

            if(treeNode.leftChild!=null) {
                queue.add(treeNode.leftChild);
                nlast = treeNode.leftChild;
            }

            if(treeNode.rightChild!=null){
                queue.add(treeNode.rightChild);
                nlast = treeNode.rightChild;
            }

            if(treeNode==last){
                last = nlast;
                level++;
            }
        }

        return true;
    }
}