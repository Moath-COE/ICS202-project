public class testClass {
    public static void main(String[] args) {

    }

    public static int getMax(int[] arr) {
        return getMax(arr, arr[0], 0);
    }

    public static int getMax(int[] arr, int num, int idx) {
        if (idx + 1 == arr.length)
            return num;
        else {
            num = Math.max(num, arr[idx + 1]);
            return getMax(arr, num, idx+1);
        }
    }
}
