import exceptions.DuplicateKeyException;
import exceptions.NotFoundException;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.function.Function;

public class HM<K,V> {
    private int capacity, size;
    private PList<Pair<K,V>>[] table;
    private Function<K,Integer> hashFunction;

    HM () {
        this(11);
    }
    @SuppressWarnings("unchecked")
    HM (int capacity) {
        this.capacity = capacity;
        this.table = (PList<Pair<K,V>>[]) Array.newInstance(PList.class, capacity);
        for (int i = 0; i < capacity; i++) table[i] = new EmptyPList<>();
        this.hashFunction = k -> k.hashCode() % capacity;
        this.size = 0;
    }

    /**
     * Returns the number of key-value mappings in this map.
     * O(1) operation
     */
    public int size () {
        return this.size;
    }

    /**
     * Returns the ratio of size / capacity
     * O(1) operation
     */
    public double loadFactor () {
        return (size() / (double) capacity);
    }

    /**
     * Removes all the key-value pairs from this map.
     * O(n) operation where n is the capacity of the map
     */
    public void clear () {
        for (int i = 0; i < capacity; i++) {
            table[i] = new EmptyPList<>();
        }
        size = 0;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     * O(1) operation
     */
    public boolean isEmpty () {
        return size == 0;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or throws NotFoundException if this map contains no mapping
     * for the key.
     * <p>
     * The complexity depends on the number of collisions for this
     * key in the hash table.
     */
    public V get (K key) throws NotFoundException {
        int index = hashFunction.apply(key);
        for (Pair<K,V> pair : table[index]) {
            if (pair.key().equals(key)) return pair.value();
        }
        throw new NotFoundException();
    }

    /**
     * Checks if the map contains the given key.
     * <p>
     * The complexity depends on the number of collisions for this
     * key in the hash table.
     */
    boolean containsKey (K key) {
        for (int i = 0; i <= size; i++) {
            for (Pair<K,V> pair : table[i]) {
                if (pair.key().equals(key)) return true;
            }
        }
        return false;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, an exception
     * is thrown.
     * If the load factor exceeds 0.75, the map is rehashed.
     * <p>
     * The complexity depends on the number of collisions for this
     * key in the hash table.
     */
    void put (K key, V value) throws DuplicateKeyException {
        if (containsKey(key)) throw new DuplicateKeyException();

        if (0.75 < loadFactor()) {
            rehash();
            put(key, value);
        }
        else {
            int index = hashFunction.apply(key);
            table[index] = table[index].addFront(new Pair<>(key, value));
            size++;
        }
    }

    /**
     * Checks if the map contains the given value.
     * <p>
     * O(n) operation where n depends on both the capacity of the
     * table and the total number of key-value pairs in the map.
     */
    boolean containsValue (V value) {
        for (int i = 0; i <= size; i++) {
            for (Pair<K,V> pair : table[i]) {
                if (pair.value().equals(value)) return true;
            }
        }
        return false;
    }

    /**
     * Allocates a new table of approximately twice the capacity (the next
     * prime after twice the current capacity) and rehashes the key-value
     * pairs from the old table to the new table.
     * <p>
     * O(n) operation where n depends on both the capacity of the
     * table and the total number of key-value pairs in the map.
     */
    @SuppressWarnings("unchecked")
    void rehash () {
        int newCapacity = nextPrime(capacity * 2);

        PList<Pair<K,V>>[] newTable = (PList<Pair<K,V>>[]) Array.newInstance(PList.class, newCapacity);
        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new EmptyPList<>();
        }

        for (int i = 0; i < capacity; i++) {
            PList<Pair<K, V>> hash = table[i];

            for (Pair<K, V> pair : hash) {
                int newIndex = hashFunction.apply(pair.key());
                newTable[newIndex] = newTable[newIndex].addFront(pair);
            }
        table = newTable;
        capacity = newCapacity;
        }
    }


    /**
     * For convenience
     */
    public String toString () {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            sb.append(String.format("table[%d]: ", i));
            for (Pair<K,V> pair : table[i]) {
                sb.append(String.format("{%s , %s} ", pair.key(), pair.value()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    int nextPrime(int n) {
        boolean isPrime = false;
        while (!isPrime) {
            n++;
            for (int i = 2; i < n; i++) {
                if (n % i == 0) break;
                else if (i == n - 1) {
                    isPrime = true;
                }
            }
        }
        return n;
}
        public int getCapacity () {
            return capacity;
        }
}
