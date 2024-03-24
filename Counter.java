package org.example;

//class Counter {
//    private int value = 0;
//
//    public void increment() {
//        int temp = value;
//        temp++;
//        value = temp;
//    }
//
//    public void decrement() {
//        int temp = value;
//        temp--;
//        value = temp;
//    }
//
//    public int getValue() {
//        return value;
//    }
//}
class Counter {
    private int value = 0;

    public synchronized void increment() {
        int temp = value;
        temp++;
        value = temp;
    }

    public synchronized void decrement() {
        int temp = value;
        temp--;
        value = temp;
    }

    public synchronized int getValue() {
        return value;
    }
}