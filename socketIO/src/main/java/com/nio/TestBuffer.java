package com.nio;

import java.nio.IntBuffer;

/**
 * Created by Yuanp on 2018/4/23.
 */
public class TestBuffer {
    public static void main(String[] args) {
        //基本操作
        //创建指定长度的缓冲区
        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(13);
        intBuffer.put(21);
        intBuffer.put(35);
        //把位置复位为0，也就是position位置：3 -> 0
        System.out.println(intBuffer);

        intBuffer.flip(); //复位
        System.out.println("使用flip复位:" + intBuffer);
        System.out.println("容量：" + intBuffer.capacity());
        System.out.println("限制为:" + intBuffer.limit());

        System.out.println("获取下标为1的元素:" + intBuffer.get(1));
        System.out.println("get(index)方法，position位置不改变：" + intBuffer);

        intBuffer.put(1, 4);
        System.out.println("put(index,change)方法，position位置不变:" + intBuffer);

        for (int i = 0; i < intBuffer.limit(); i++) {
            System.out.println(intBuffer.get() + "\t");
        }
        System.out.println("intBuffer对象遍历之后为:" + intBuffer);

        // wrap方法使用
        // wrap方法会包裹一个数组：一般这种用法不会先初始化对象的长度，因为没有意义
        // 最后还会被wrap所包裹的数组覆盖掉

        int[] arr = new int[]{1, 2, 4};
        IntBuffer buffer1 = IntBuffer.wrap(arr);

        System.out.println(buffer1);

        IntBuffer buffer2 = IntBuffer.wrap(arr, 0, 2);
        //这样使用表示容量为数组arr的长度，但是可操作的元素只有实际进入缓冲区的元素长度
        System.out.println(buffer2);

        //其他方法
        IntBuffer buffer3 = IntBuffer.allocate(10);
        int[] arr2 = new int[]{1, 23, 3};
        buffer3.put(arr2);
        System.out.println("放入一个数组:" + buffer3);

        IntBuffer buffer4 = buffer3.duplicate();
        System.out.println("从buffer3复制:" + buffer4);

        buffer3.flip();
        System.out.println(buffer3);
        System.out.println("可读数据为:" + buffer3.remaining());

        int[] arr3 = new int[buffer3.remaining()];
        buffer3.get(arr3);
        for (int i : arr3) {
            System.out.print(Integer.toString(i) + ",");
        }
    }
}
















