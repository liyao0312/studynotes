package bishi.xiehcneg;

class leo50 {
    public int schedule(int[] array, int m) {
        long long1 = 0;
        long long2 = 0;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            long2 += array[i];
            if (long1 < array[i]) {
                long1 = array[i];
            }
        }
        long result = long2;
        while (long1 <= long2) {
            long mid = (long1 + long2) >> 1;
            long sum = 0;
            int cnt = 1;
            for (int i = 0; i < length; i++) {
                if (sum + array[i] > mid) {
                    cnt ++;
                    sum = array[i];
                } else {
                    sum += array[i];
                }
            }
            if (cnt > m) {
                long1 = mid + 1;
            } else {
                result = Math.min(result, mid);
                long2 = mid - 1;

            }
        }
        return (int)result;
    }
}