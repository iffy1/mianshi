package com.iffy.mianshi.algorithm.leecode.lianbiao;

public class JavaTest {
    public static void main(String[] args) {
        JavaNodeList head = initNodeList();
        printLianBiao(head);
        insert(head, 10);
//        insert(head, 11);
//        insert(head, 12);
        printLianBiao(head);
    }

    public static void insert(JavaNodeList head, int value) {
        JavaNodeList temp = head;
        //地址一样
        System.out.println(head);
        System.out.println(temp);

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new JavaNodeList(value);
        System.out.println(temp);
    }

    public static void printLianBiao(JavaNodeList head) {
        while (head != null) {
            System.out.print(head.value);
            head = head.next;
        }
        System.out.println("");
    }

    public static JavaNodeList initNodeList() {
        JavaNodeList head = new JavaNodeList(-1);
        JavaNodeList temp = head;
        for (int i = 0; i < 10; i++) {
            temp.next = new JavaNodeList(i);
            temp = temp.next;
        }
        return head;
    }

}

