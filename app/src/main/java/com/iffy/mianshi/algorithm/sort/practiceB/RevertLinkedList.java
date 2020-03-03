package com.iffy.mianshi.algorithm.sort.practiceB;

/**
 * <pre>
 *     author : iffy
 *     time   : 2020/03/03
 * </pre>
 */


public class RevertLinkedList {
    static class TestNode {
        public TestNode(String s) {
            this.content = s;
        }

        String content;
        TestNode next;
    }

    public static void main(String[] args) {
        TestNode a = new TestNode("1");
        a.next = new TestNode("2");
        a.next.next = new TestNode("3");
        printTestNode(a);
        printTestNode(revertNode(a));
        printTestNode(a);
    }


    private static void printTestNode(TestNode t) {
        TestNode temp = t;
        while (temp != null) {
            System.out.print(temp.content + ",");
            temp = temp.next;
        }
        System.out.println();
    }

    public static TestNode revertNode(TestNode node) {
        TestNode result = null;
        TestNode pointer = node;
        while (pointer != null) {
            TestNode temp = pointer.next;
            pointer.next = result;
            result = pointer;
            pointer = temp;
        }
        return result;
    }

}

