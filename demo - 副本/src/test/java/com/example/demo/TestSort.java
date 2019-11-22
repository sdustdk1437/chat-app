package com.example.demo;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestSort {
@org.junit.Test
    public void main(){
    int nums[] = {1,5,4,2,3};
      maoPao(nums);
      selectSort(nums);
    }

    public void maoPao(int nums[]){

        for (int i=0;i<nums.length-1;i++){
            for (int j=0;j<nums.length-i-1;j++){
                if (nums[j]>nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }

        for (int k=0;k<nums.length;k++){
            System.out.println(nums[k]);
        }
    }

    public void selectSort(int nums[]){

    for (int i=0;i<nums.length;i++){
        int k = i;
        for (int j=nums.length-1;j>i;j--){
            if (nums[j]<nums[k]){
                k=j;
            }
        }
        int temp = nums[i];
        nums[i] = nums[k];
        nums[k] =  temp;
    }
        for (int k=0;k<nums.length;k++){
            System.out.println(nums[k]);
        }

    }
}
