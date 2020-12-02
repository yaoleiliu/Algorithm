package String;

public class RuleJuge {

    /**
     * 9.思路
     * 1）初始化整形变量num，代表'('和')'出现次数的差值
     * 2）遍历过程中遇到'('，则num++；遇到')'，则num--
     * 3）遍历过程中若num<0，则直接返回false
     * 4）如果一直没有出现情况3），则一直遍历下去
     * 5）遍历完成后，如果num=0，则返回true；否则，返回false
     */
    public static void isValidBracket(){
        String str = "((())())";

        boolean flag = true;
        int num = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='('){
                num++;
            } else if(str.charAt(i)==')'){
                num--;
            }

            if(num<0){
                flag = false;
                break;
            }
        }

        if(num==0){
            flag=true;
        } else {
            flag = false;
        }

        if(flag){
            System.out.println("this str is an valid bracket!!");
        } else {
            System.out.println("this str is not an valid bracket!!");
        }
    }
}
