package util;

public class ObjectRepo {
    private static int i;
    public static void waitForLoad(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
