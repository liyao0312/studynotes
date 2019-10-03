package wanmeishijie;

import java.util.Scanner;
import java.util.Stack;

public class leo10{
    public static void main(String[] args) {
        MyStack ms = new MyStack();
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        for(int i=0;i<n;i++){
            ms.push(sc.nextInt());
        }

        ms.pop();
        System.out.println(ms.getMax()+","+ms.getMin());
        sc.close();
    }
}


class MyStack {
    private Stack<Integer> data;
    private Stack<Integer> minStack;
    private Stack<Integer> maxStack;
    public  MyStack() {
        data = new Stack<>();
        minStack = new Stack<>();
        maxStack = new Stack<>();
    }


    public void push(int x) {
        data.add(x);
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.add(x);
        } else {
            minStack.add(minStack.peek());
        }
        if (maxStack.isEmpty() || maxStack.peek() <= x) {
            maxStack.add(x);
        } else {
            maxStack.add(maxStack.peek());
        }

    }

    public void pop() {
        if (!data.isEmpty()) {
            minStack.pop();
            maxStack.pop();
            data.pop();
        }
    }

    public int top() {
        if(!data.isEmpty()){
            return data.peek();
        }
        throw new RuntimeException("栈中元素为空");
    }

    public int getMin() {
        if(!minStack.isEmpty()){
            return minStack.peek();
        }
        throw new RuntimeException("栈中元素为空");
    }
    public int getMax() {
        if(!maxStack.isEmpty()){
            return maxStack.peek();
        }
        throw new RuntimeException("栈中元素为空");
    }
}
