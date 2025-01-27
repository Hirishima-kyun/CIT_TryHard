public class MyArrayList {
    private int arr[] = new int[5], size = 0;

    private void invalidUserIndex(int idx) {
        if (idx <= 0 || idx > size) {
            throw new InvalidPositionException(size);
        }
    }

    public void add(int num) throws ArrayFullException {
        try {
            arr[size++] = num;
        } catch (ArrayIndexOutOfBoundsException e) {
            size--;
            throw new ArrayFullException(num);
        }
    }

    public int size() {
        return size;
    }

    public int get(int pos) {
        invalidUserIndex(pos);
        return arr[pos-1];
    }

    public void addAt(int num, int pos) throws ArrayFullException {
        if (pos <= 0 || pos > size + 1) {
            throw new InvalidPositionException(size);
        }

        try {
            if (size != 0) arr[size] = arr[size - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayFullException(num);
        }

        for (int i = size++ - 1; i >= pos; i--) {
            arr[i] = arr[i - 1];
        }
        arr[pos - 1] = num;
    }

    public boolean remove(int num) {
        for (int idx = 0; idx < size; idx++) {
            if (arr[idx] == num) {
                removeAt(idx + 1);
                return true;
            }
        }

        return false;
    }

    public int removeAt(int pos) {
        invalidUserIndex(pos);
        int buff = arr[pos - 1];
        for (int i = pos; i < size; i++) {
            arr[i - 1] = arr[i];
        }
        size--;
        return buff;
    }

    public boolean contains(int num) {
        for (int idx = 0; idx < size; idx++) {
            if (arr[idx] == num) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int set(int pos, int num) {
        invalidUserIndex(pos);
        int buff = arr[pos - 1];
        arr[pos - 1] = num;
        return buff;
    }
}
