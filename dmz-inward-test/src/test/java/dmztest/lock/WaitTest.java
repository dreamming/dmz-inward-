package dmztest.lock;

public class WaitTest {

    public static void main(String[] args) {

        ThreadA ta = new ThreadA("ta");

        synchronized (ta) { // 通过synchronized(ta)获取“对象ta的同步锁”
            try {
                System.out.println(Thread.currentThread().getName() + " start ta");
                ta.start();

                System.out.println(Thread.currentThread().getName() + " block");
                // 主线程等待
                ta.wait();

                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            synchronized (this) { // 通过synchronized(this)获取“当前对象的同步锁”
                System.out.println(Thread.currentThread().getName() + " wakup others");
//                notify();    // 唤醒“当前对象上的等待线程”
                // 1.notify()喚醒等待當前 的綫程 2.當前綫程任務結束，同樣會喚醒等待當前 的綫程
            }
        }
    }
}