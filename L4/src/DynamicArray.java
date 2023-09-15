public class DynamicArray {
    private int[] arr;
    private int size;
    private int capacity;
    private final double growthFactor;

    public DynamicArray(double growthFactor) {
        if (growthFactor <= 1.0) {
            throw new IllegalArgumentException("Growth factor should be greater than 1");
        }
        this.capacity = 2;
        this.size = 0;
        this.arr = new int[capacity];
        this.growthFactor = growthFactor;
    }

    public void append(int val) {
        if (size == capacity) {
            resize((int) (capacity * growthFactor));
        }
        arr[size++] = val;
    }

    public int pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Array is empty!");
        }
        int val = arr[--size];
        if (size <= capacity / growthFactor) {
            resize((int) (capacity / growthFactor));
        }
        return val;
    }

    public int get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range!");
        }
        return arr[index];
    }

    private void resize(int newCapacity) {
        int[] newArr = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
        capacity = newCapacity;
    }

    public static void main(String[] args) {

        // Test with a growth factor of 2 (doubling)
        DynamicArray arr = new DynamicArray(2.0);

        // Append numbers 1, 2, and 3
        arr.append(1);
        arr.append(2);
        arr.append(3);
        System.out.println("After appending: Last element = " + arr.get(arr.size - 1));

        // Pop last element
        System.out.println("Popped: " + arr.pop());
        System.out.println("After popping: Last element = " + arr.get(arr.size - 1));
    }
}
