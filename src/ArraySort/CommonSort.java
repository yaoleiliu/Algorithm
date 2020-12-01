package ArraySort;

/**
 * 1) 冒泡排序；2）选择排序；3）插入排序；4）归并排序；5）快速排序；6）堆排序
 */

public class CommonSort {
    public static void main(String[] args){

        int[] arrBubble = {3, 6, 5, 7, 0, 4, 1, 2};
        System.out.println("---开始冒泡排序---");
        bubbleSort(arrBubble);
        for(Integer e: arrBubble){
            System.out.print(e);
        }
        System.out.println("");
        System.out.println("---完成冒泡排序---");

        int[] arrChoose = {3, 6, 5, 7, 0, 4, 1, 2};
        System.out.println("---开始选择排序---");
        chooseSort(arrChoose);
        for(Integer e: arrChoose){
            System.out.print(e);
        }
        System.out.println("");
        System.out.println("---完成选择排序---");

        int[] arrInsert = {3, 6, 5, 7, 0, 4, 1, 2};
        System.out.println("---开始插入排序---");
        insertSort(arrInsert);
        for(Integer e: arrInsert){
            System.out.print(e);
        }
        System.out.println("");
        System.out.println("---完成插入排序---");

        int[] arrMerge = {3, 6, 5, 7, 0, 4, 1, 2};
        System.out.println("---开始归并排序---");
        mergeSort(arrMerge);
        for(Integer e: arrMerge){
            System.out.print(e);
        }
        System.out.println("");
        System.out.println("---完成归并排序---");

        int[] arrQuick = {3, 6, 5, 7, 0, 4, 1, 2};
        System.out.println("---开始快速排序---");
        quickSort(arrQuick);
        for(Integer e: arrQuick){
            System.out.print(e);
        }
        System.out.println("");
        System.out.println("---完成快速排序---");

        int[] arrHead = {3, 6, 5, 7, 0, 4, 1, 2};
        System.out.println("---开始堆排序---");
        headSort(arrHead);
        for(Integer e: arrHead){
            System.out.print(e);
        }
        System.out.println("");
        System.out.println("---完成堆排序---");
    }

    /**
     * 冒泡排序, 时间复杂度：O(n^2)
     * 思路：
     * 1）第一轮，0~N-1，依次比较两个数，哪个大，哪个放在后面。这样一轮下来，最大的数会放在最后一个位置
     * 2）第二轮，0~N-2，...，倒数第二大的数会放在倒数第二个位置
     */
    public static void bubbleSort(int[] arr){
        if(arr.length<=1){
            return;
        }

        for(int j=1; j<=arr.length-1; j++){
            for(int i=0; i<arr.length-j; i++){
                if(arr[i]>arr[i+1]){
                    swap(arr, i, i+1);
                }
            }
        }
    }

    /**
     * 选择排序, 时间复杂度：O(n^2)
     * 思路：
     * 1）范围0~N-1，找出最小值，放在第一个位置
     * 2）范围1~N-1，找出最小值，放在第二个位置
     * ......
     */
    public static void chooseSort(int[] arr){
        if(arr.length<=1){
            return;
        }

        int minIndex = 0;
        for(int i=0; i<arr.length-1; i++){
            minIndex = i;
            for(int j=i; j<arr.length; j++){
                if(arr[j]<arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 插入排序，O(N^2)
     * 思路：每个位置的数都和前面位置的数作比较，将其插入到合适位置
     */
    public static void insertSort(int[] arr){
        if(arr==null || arr.length<=1){
            return;
        }

        int index = 0;
        for(int i=0; i<arr.length; i++){
            index = i;
            while(index>0){
                if(arr[index-1]>arr[index]){
                    swap(arr, index-1, index);
                    index--;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 归并排序，时间复杂度 O（N * logN）
     * 思路：
     * 1）数组中每个数成为长度为1的有序区间
     * 2）将长度为1的有序区间和相邻有序区间合并，得到长度为2的有序区间
     * 3）合并长度为2的有序区间，得到长度为4的有序区间......
     * 4）直到整个数组合并为一个有序区间
     */
    public static void mergeSort(int[] arr){
        if(arr==null || arr.length<=1){
            return;
        }

        process(arr, 0, arr.length-1);
    }

    public static void process(int[] arr, int left, int right){
        if(left==right){
            return;
        }

        int mid = (left + right) / 2;
        process(arr, left, mid);
        process(arr, mid+1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right){
        int[] helpArr = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int index = 0;
        while(l<=mid && r<=right){
            if(arr[l]<=arr[r]){
                helpArr[index++] = arr[l++];
            } else {
                helpArr[index++] = arr[r++];
            }
        }

        while(l<=mid){
            helpArr[index++] = arr[l++];
        }

        while(r<=right){
            helpArr[index++] = arr[r++];
        }

        for(int i=0; i<helpArr.length; i++){
            arr[left + i] = helpArr[i];
        }
    }

    /**
     * 快速排序，时间复杂度：O(N * logN)
     * 思路：
     * 1）随机选中一个数，小于等于这个数的放在它左边，大于这个数的放在它右边
     * 2）在选中的这个数的左边和右边，递归地进行第一步骤
     * 划分过程：O(N)
     * 1）把划分值放在数组的最后一个位置
     * 2）设置一个小于等于区间，初始时长度为0，放在数组左边
     * 3）从左到右遍历数组：
     *    a）如果当前元素大于划分值，就继续遍历下一个元素
     *    b）如果当前元素小于等于划分值，把当前元素和小于等于区间的下一个元素进行交换，小于等于区间向右扩一个位置
     */
    public static void quickSort(int[] arr){
        if(arr==null || arr.length<=1){
            return;
        }

        quickHelper(arr, 0, arr.length-1);
    }

    public static void quickHelper(int[] arr, int left, int right){
        if(right<=left){
            return;
        }

        int index = quickPartition(arr, left, right);
        quickPartition(arr, left, index-1);
        quickHelper(arr, index+1, right);
    }

    //默认每次都使用最右边位置的数作为划分值
    public static int quickPartition(int[] arr, int left, int right){
        if(right<=left){
            return left;
        }

        int rIndex = left;
        for(int i=0; i<(right-left); i++){
            if(arr[left+i]<=arr[right]){
                swap(arr, rIndex, left+i);
                rIndex++;
            }
        }

        if(rIndex<right){
            swap(arr, rIndex, right);
            return rIndex;
        }

        return right;
    }

    /**
     * 堆排序
     * 思路：
     * 1）把整个数组构造成一个大根堆
     * 2）把堆顶元素和堆最后一个元素交换位置，调整0~N-2个元素使其保持大根堆性质
     * 3）重复上述步骤，调整完成整个数组
     */
    public static void headSort(int[] arr){
        if(arr==null || arr.length<=2){
            return;
        }

        int length = arr.length - 1;
        for(int j=length/2; j>=0; j--){
            adjustHead(arr, j, length);
        }

        for(int i=1; i<arr.length; i++){
            swap(arr, 0, length);
            length--;
            adjustHead(arr, 0, length);
        }
    }

    public static void adjustHead(int[] arr, int start, int end){
        if(start>=end){
            return;
        }

        int child = 2 * start + 1;
        while(child<=end){
            if((child+1)<=end && arr[child+1]>arr[child]){
                child++;
            }

            if(arr[child]>arr[start]){
                swap(arr, start, child);
                start = child;
                child = 2 * start + 1;
            } else {
                return;
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
