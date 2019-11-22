import java.util.Arrays;

public class Main {


    public static void main(String args[]) {
        //定义一个数组
        int nums[] = {2, 4, 1, 5, 3, 10, 9, 88, 77};
        for (int j = 0; j < nums.length - 1; j++) {
            for (int i = 0; i < nums.length - j - 1; i++) {
                if (nums[i] < nums[i + 1]) {
                    int tem;
                    tem = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = tem;
                }
            }
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
    }

}


