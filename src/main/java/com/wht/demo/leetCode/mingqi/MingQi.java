package com.wht.demo.leetCode.mingqi;

/**
 * desc.
 *
 * @author wht
 */
public class MingQi {


    public static void main(String[] args) {

        System.out.println("0123".substring(0,1));
        System.out.println("0123".substring(0,2));
        System.out.println("0123".substring(0,3));
        System.out.println("0123".substring(0,4));

        //Scanner in = new Scanner(System.in);
        //while(in.hasNextInt()) {
        //    int i = in.nextInt();
        //    StringBuilder sb = new StringBuilder();
        //    while(i > 0){
        //        int mod = i & 1;
        //        if (mod == 0){
        //            sb.append(3);
        //            i = (i-2) >> 1;
        //        } else {
        //            sb.append(2);
        //            i = (i-1) >> 1;
        //        }
        //
        //    }
        //    System.out.print(sb.reverse().toString());
        //}
        //InputStreamReader reader = new InputStreamReader(System.in);
        //int i;
        //try {
        //    //读到'<'等'>'，中间的字母不入栈，读到'='，下一个必须是'"'，将引号入栈，中间再有任意内容都不入栈，直到出现'"'，这时下一个字符必须是空格或者'>'
        //    while (true){
        //        i = reader.read();
        //        if (i == -1){
        //
        //        }
        //    }
        //
        //    if (s == null || s.length() == 0){
        //        return ;
        //    }
        //    Stack<String> stack = new Stack<>();
        //    stack.
        //
        //
        //
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int index = m + n - 1;
        int temp;
        m--;
        n--;
        while (index >= 0) {
            if (m < 0) {
                while (n >= 0) {
                    nums1[index--] = nums2[n--];
                }
                return;
            } else if (n < 0) {
                return;
            }
            if (nums1[m] > nums2[n]) {
                temp = nums1[m];
                m--;
            } else {
                temp = nums2[n];
                n--;
            }
            nums1[index--] = temp;
        }
    }

    public boolean isPalindrome(String s) {
        if(s == null || s.length() <= 1){
            return true;
        }

        //两个指针，分别从前到后和从后到前扫描，遇到非字母和数字则跳过，扫描下一个，如果两个指针指向的元素不相等，则直接返回false，如果两元素相等，继续扫描下一个，如果两指针交叉，则通过，返回true
        int left = 0,right = s.length() - 1;
        int temp = 'a' - 'A';
        while(left < right){
            char a = s.charAt(left);
            char b = s.charAt(right);
            //先检查当前字符是否合法
            if(!isLegal(a)){
                left++;
                continue;
            } else if (!isLegal(b)){
                right--;
                continue;
            }

            if(a == b || (Math.abs(a-b) == temp && a > '9' && b > '9')){
                left ++;
                right --;
                continue;
            } else {
                return false;
            }

        }
        return true;

    }

    private boolean isLegal(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
}

