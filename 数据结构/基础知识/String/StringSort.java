package String;

import java.util.*;

/**
 * 与数组操作有关的字符串题型
 */
public class StringSort {
    /**
     * 7.思路：
     * 1）将其看做是数组排序过程；
     * 2）判断大小原则：如果str1+str2<str2+str1，则str1在前面；否则，str2在前面
     */
    public static void getSmallString(){
        String[] strs = {"aa", "cc", "dd", "bb"};

        String str = processSmallestString(strs);

        System.out.println("最小字典序是：" + str);
    }

    public static String processSmallestString(String[] strs){
        if(strs.length<=1){
            return strs[0];
        }

        quickSortStr(strs, 0, strs.length-1);
        String ans = "";
        for(int i=0; i<strs.length; i++){
            ans += strs[i];
        }

        return ans;
    }

    public static void quickSortStr(String[] strs, int left, int right){
        if(right<=left){
            return;
        }

        int index = partition(strs, left, right);
        quickSortStr(strs, left, index-1);
        quickSortStr(strs, index+1, right);
    }

    public static int partition(String[] strs, int left, int right){
        if(right<=left){
            return left;
        }

        int rIndex = left;
        for(int i=0; i<(right-left); i++){
            if(myComparator(strs[left+i], strs[right])<=0){
                swapStr(strs, left+i, rIndex);
                rIndex++;
            }
        }

        if(rIndex<right){
            swapStr(strs, rIndex, right);
            return rIndex;
        }

        return right;
    }

    public static void swapStr(String[] strs, int i, int j){
        String tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }

    public static int myComparator(String a, String b){
        return (a+b).compareTo(b+a);
    }

    /**
     * 8.思路
     * 1）遍历str，计算空格数量为n，则替换后字符串长度应为str.length + 2*n
     * 2）从新的末尾位置倒序copy
     */
    public static void replaceSpace(){
        String str = "a b c d e f";
        int countSpace = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)==' '){
                countSpace++;
            }
        }

        char[] chars = new char[str.length() + 2*countSpace];
        int j = str.length() + 2*countSpace - 1;
        for(int i=str.length()-1; i>=0; i--){
            if(str.charAt(i)==' '){
                chars[j--] = '0';
                chars[j--] = '2';
                chars[j--] = '%';
            } else {
                chars[j--] = str.charAt(i);
            }
        }

        System.out.println("a b c d e f替换空格后的字符串为：" + String.valueOf(chars));
    }
}
