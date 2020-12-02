package String;

import java.util.HashSet;
import java.util.Set;
import TreePrint.serializeTree;
import TreePrint.TreeNode;

/**
 * 字符计数问题
 */
public class CharactCount {

    /**
     * 1.给定一个字符串，请你找出其中不含有重复字符的最长子串的长度
     * 思路：
     * 1）模式识别一：一旦涉及出现次数，需要用到散列表
     * 2）模式识别二：涉及子串，考虑使用滑动窗口0
     */
    public static void lengthOfLongestSubstring(){
        String str = "abcabcbb";

        int ans = lengthOfLongestSubstring(str);

        System.out.println("字符串：abcabcbb 的最长无重复子串的长度是：" + ans);
    }

    public static int lengthOfLongestSubstring(String s){
        Set<Character> occ = new HashSet<>();
        int rk = -1, ans=0;

        for(int i=0; i<s.length(); i++){
            if(i!=0){
                occ.remove(s.charAt(i-1));
            }

            while((rk+1)<s.length() && !occ.contains(s.charAt(rk+1))){
                occ.add(s.charAt(rk+1));
                rk++;
            }

            ans = Math.max(ans, rk - i + 1);
        }

        return ans;
    }

    /**
     * 2.给定两个字符串str1和str2，如果str1和str2中出现的字符种类一样且每种字符出现的次数也一样，
     * 那么str1和str2互为变形词，请实现函数判断两个字符串是否互为变形词
     * 思路：使用数组代替map，提高效率
     * 大写字母ASCII值：65~90；小写字母ASCII值：97~122
     */
    public static void isTransform(){
        String str1 = "aabba";
        String str2 = "bbaaa";

        boolean isChange = chkTransform(str1, str2);

        if(isChange){
            System.out.println("aabba 和 bbaaa是变形词！！！");
        } else {
            System.out.println("aabba 和 bbaaa不是变形词！！！");
        }
    }

    public static boolean chkTransform(String str1, String str2){
        if(str1==null || str2==null || str1.length()!=str2.length()){
            return false;
        }

        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();
        int[] map = new int[256];

        for(int i=0; i<str1Arr.length; i++){
            map[str1Arr[i]]++;
        }

        for(int i=0; i<str2Arr.length; i++){
            //先判断是否等于0，如果等于0，加上本次出现，实际出现次数已经比第一个字符串多了一次，直接返回false
            if(map[str2Arr[i]]-- == 0){
                return false;
            }
        }

        return true;
    }

    /**
     * 3.给定彼此独立的两棵树头节点t1和t2，判断t1中是否有与t2树拓扑结构完全一致的子树
     * 思路：
     * 1）序列化二叉树；2）KMP算法进行字符串匹配
     */
    public static void jugeChildTree(){
        //构造二叉树
        TreeNode node4 = new TreeNode("4", null,null);
        TreeNode node5 = new TreeNode("5", null,null);
        TreeNode node3 = new TreeNode("3", null,null);
        TreeNode node2 = new TreeNode("2", node4, node5);
        TreeNode node1 = new TreeNode("1", node2, node3);

        if(isChileTree(node1, node2)){
            System.out.println("node2是node1的子树！");
        } else {
            System.out.println("node2不是node1的子树！");
        }
    }

    public static boolean isChileTree(TreeNode t1, TreeNode t2){
        if(t1==null && t2!=null){
            return false;
        }

        if(t2==null){
            return false;
        }

        String str1 = serializeTree.serilze(t1);
        String str2 = serializeTree.serilze(t2);
        System.out.println("treenode1: " + str1);
        System.out.println("treenode2: " + str2);

        int[] next = getNext(str2);
        int i=0, j=0;

        for(; i<str1.length() && j<str2.length();){
            if(j==-1 || str1.charAt(i)==str2.charAt(j)){
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }

        if(j==str2.length()){
            return true;
        }

        return false;
    }

    public static int[] getNext(String str){
        int[] next = new int[str.length()];
        int i=0, j=-1;
        next[0] = -1;

        for(; i<str.length(); i++){
            if(j==-1 || str.charAt(i)==str.charAt(j)){
                ++i;
                ++j;
                next[i] = j;
            } else {
                j = next[j];
            }
        }

        return next;
    }

    /**
     * 4.思路：
     * 1）判断str1和str2的长度是否相等；
     * 2）如果长度相等，生成str1+str2的大字符串;
     * 3）用KMP算法判断大字符串中是否含有str2。
     */
    public static void rotate(){
        String str1 = "abcde";
        String str2 = "deabc";

        boolean isRotateWord = isRotate(str1, str2);
        if(isRotateWord) {
            System.out.println("abcde 和 deabc是旋转词！！！");
        } else {
            System.out.println("abcde 和 deabc不是旋转词！！！");
        }
    }

    public static boolean isRotate(String str1, String str2){
        if(str1.length()!=str2.length()){
            return false;
        }

        String str = str1 + str2;

        //KMP算法
        int[] next = getNext(str2);
        int i=0, j=0;
        while(i<str.length() && j<str2.length()){
            if(j==-1 || str.charAt(i)==str2.charAt(j)){
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }

        if(j==str2.length()){
            return true;
        }

        return false;
    }

    /**
     * 5.思路
     * 1）实现将字符串中所有字符逆序的函数f;
     * 2) 利用f将字符串中所有字符逆序；
     * 3）找到逆序后字符串的每个单词区域，利用函数f将每个单词区域逆序。
     */
    public static void reverseSentence(){
        String sentence = "pig loves dog";
        String ans      = reverseString(sentence);
        System.out.println("逆序句子是：" + ans);
    }

    public static String reverseString(String str){
        if(str.length()<=1){
            return str;
        }

        String midString = reverseOrder(str);
        String[] strs = midString.split(" ");

        String ans = "";
        for(int i=0; i<strs.length; i++){
            strs[i] = reverseOrder(strs[i]);
            ans += strs[i];
            ans += " ";
        }

        return ans;
    }

    public static String reverseOrder(String str){
        if(str.length()<=1){
            return str;
        }

        char[] chars = str.toCharArray();
        int i=0, j=chars.length-1;
        while(i<j){
            swapChara(chars, i, j);
            ++i;
            --j;
        }

        return new String(chars);
    }

    public static void swapChara(char[] chars, int i, int j){
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    /**
     * 6.思路
     * 1）将str[0,i]部分字符逆序
     * 2）将str[i+1,N-1]部分的字符逆序
     * 3）将str的整体字符逆序
     */
    public static void reverceSubString(){
        String str = "abcdefg";

        String ans = processReverceSub(str, 3);
        System.out.println("abcdefg 的 旋转字符串是：" + ans);
    }

    public static String processReverceSub(String str, int i){
        if(str.length()<=1 || i>=str.length()){
            return str;
        }

        String str1 = str.substring(0, i);
        String str2 = str.substring(i);
        str1 = reverseOrder(str1);
        str2 = reverseOrder(str2);

        return reverseOrder(str1+str2);
    }
}
