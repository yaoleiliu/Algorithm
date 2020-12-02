package TreePrint;

/**
 * 使用递归须有两个条件：1）函数参数类型相同；2）递归必须有出口
 */
public class recursivePrint {
    public static void main(String[] args){
        //初始化树结构
        TreeNode a = new TreeNode("7", null, null);
        TreeNode b = new TreeNode("8", null, null);
        TreeNode c = new TreeNode("4", null, null);
        TreeNode d = new TreeNode("6", null, null);
        TreeNode e = new TreeNode("5", a, b);
        TreeNode f = new TreeNode("3", e, d);
        TreeNode g = new TreeNode("2", c, null);
        TreeNode root = new TreeNode("1", g, f);

        System.out.println("---递归前序遍历打印二叉树---");
        preOrderPrint(root);
        System.out.println("---完成递归前序遍历打印二叉树---");

        System.out.println("---递归中序遍历打印二叉树---");
        middleOrderPrint(root);
        System.out.println("---完成递归中序遍历打印二叉树---");

        System.out.println("---递归后序遍历打印二叉树---");
        aftOrderPrint(root);
        System.out.println("---完成递归后序遍历打印二叉树---");
    }

    /**
     *递归先序遍历二叉树
     */
    public static void preOrderPrint(TreeNode root){
        if(root==null){
            return;
        }

        System.out.println(root.data);
        preOrderPrint(root.leftChild);
        preOrderPrint(root.rightChild);
    }

    /**
     * 递归中序遍历二叉树
     */
    public static void middleOrderPrint(TreeNode root){
        if(root==null){
            return;
        }

        middleOrderPrint(root.leftChild);
        System.out.println(root.data);
        middleOrderPrint(root.rightChild);
    }

    /**
     * 递归后序遍历二叉树
     */
    public static void aftOrderPrint(TreeNode root){
        if(root==null){
            return;
        }

        aftOrderPrint(root.leftChild);
        aftOrderPrint(root.rightChild);
        System.out.println(root.data);
    }
}
