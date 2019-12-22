import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.InputStreamReader;

public class HashMap<K,V> implements Map<K,V> {
    // HASH FUNCTION:
    // Converts the entire string into a hash i.e
    // Into an ASCII using byte
    // THEN evenly distributes the string (word) % n (length of array)

    // Input will be generics
    // K and V

    // Doesn't matter what the values are
    // Just needs to be hash correctly the keys

    private int size = 0;
    private int bucketsN;
    public static Entry[] array;


    public HashMap(int buckets) {

        Entry[] array = new Entry[buckets];
        bucketsN = buckets;
        this.array = array;
    }

    public HashMap() {
        Entry[] array = new Entry[128];
        bucketsN = 64;
        this.array = array;
    }


    public void clear() {
        // have to initialize before the clear . . .
        Entry[] array = new Entry[bucketsN];
        this.array = array;
        size = 0;
    }

    public boolean containsKey(K key) {
        int index = hashIndex(key);
        if (array[index] == null) return false;
        Entry ptr = array[index];
        // System.out.println("YO.");
        if (ptr.getNext() == null) {
            if (array[index].getKey().equals(key)) return true;
            else return false;
        }

        while(ptr.getNext() != null) {
            ptr = ptr.getNext();
            if (ptr.getKey().equals(key)) return true;
        }
        return false;
    }

    public boolean containsValue(V value) {
        // I assume that this can just return boolean "containsKey()"
        // if the values are the same as the key
        // but I'll check for distinct values anyway

        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].getValue().equals(value)) return true;
                Entry ptr = array[i];
                while (ptr.getNext() != null) {
                    ptr = ptr.getNext();
                    if (ptr.getValue().equals(value)) return true;
                }
            }

        }
        return false;
    }

    public V get(K key) {
        int index = hashIndex(key);
        if (array[index] == null) return null;
        Entry ptr = array[index];
        if (ptr.getNext() == null) {
            if (array[index].getKey().equals(key)) {
                return (V) array[index].getValue();
            }
        }
        while(ptr.getNext() != null) {
            if (ptr.getKey().equals(key)) return (V) ptr.getValue();
            ptr = ptr.getNext();
            if (ptr.getKey().equals(key)) return (V) ptr.getValue();
        }
        return null;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }

    public V put(K key, V value) {
        //System.out.println("Value: " + value);
        int index = hashIndex(key);
        // System.out.println(array[index] + "!!!");
        if (array[index] == null) {
            // System.out.println("NEW VALUE EMPTY");
            Entry temp = new Entry(key,value,null);
            array[index] = temp;
        }

        else {
            Entry ptr = array[index];
            while(ptr.getNext() != null) {
                // System.out.println(ptr.getValue());
                ptr = ptr.getNext();
                // System.out.println("2" + ptr.getValue());
            }
            Entry temp = new Entry(key,value,null);
            //System.out.println("added " + temp.toString() + " to " + ptr.toString());
            ptr.setNext(temp);
        }
        size++;
        return value;
    }

    public V remove(K key) {

        int index = hashIndex(key);

        if (array[index] == null) return null;

        Entry ptr = array[index];

        if (array[index].getNext() == null) {
            if (array[index].getKey().equals(key)) {
                V val = (V) array[index].getValue();
                array[index] = null;
                size--;
                return val;
            }
        } else if (array[index].getNext() != null) {
            while (ptr.getNext() != null) {
                if (ptr.getKey().equals(key)) {
                    V val = (V) ptr.getValue();
                    array[index] = ptr.getNext();
                    size--;
                    return val;
                }

                // check two ahead
                // error check

                try {
                    if (ptr.getNext().getNext() != null) {
                        if (ptr.getNext().getKey().equals(key)) {
                            V val = (V) ptr.getNext().getValue();
                            ptr.setNext(ptr.getNext().getNext());
                            size--;
                            return val;
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("ERROR: no getNext().getNext()");
                }

                if (ptr.getNext().getKey().equals(key)) {
                    V val = (V) ptr.getNext().getValue();
                    ptr.setNext(null);
                    size--;
                    return val;
                }
                ptr = ptr.getNext();
            }

        }
        return null;
    }

    public V replace(K key, V value) {
        // what is this supposed to do
        int index = hashIndex(key);
        if (array[index] == null) return null;

        Entry ptr = array[index];

        if (array[index].getNext() == null)
            if (array[index].getKey().equals(key)) {
                V val = (V) array[index].getValue();
                array[index].setValue(value);
                return val;
            }

        while (ptr.getNext() != null) {
            if (ptr.getKey().equals(key)) {
                V val = (V) ptr.getValue();
                ptr.setValue(value);
                return val;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public String display() {
        // prints the entire HashMap
        String s = "";
        // s+= "{"; (for the purposes of text writing)
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                s+= (String) array[i].getKey() + ":" +
                        (String) array[i].getValue();
                count++;
                if (count != size()) s += "\n";

            }
            // remove trailing comma later!

            if (array[i] != null) {
                Entry ptr = array[i];
                while (ptr.getNext() != null) {
                    ptr = ptr.getNext();
                    s += (String) ptr.getKey() + ":" +
                            (String) ptr.getValue();
                    count++;
                    if (count != size()) s += "\n";
                }
            }
        }
        // s+="}"; (for the purposes of text writing)


        return s;
    }

    public int hash(K key) {
        // we assume keys are Strings
        // equal strings must give equal hashcodes
        // w/e with collisions

        // HASH:

        // adds up the UTF-8 values of each character
        // multiply by 3
        // % by the array.length

        // anticipate a byte[] array

        byte utf8[];
        utf8 = key.toString().getBytes(Charset.forName("UTF-8"));
        String str = new String(utf8, Charset.forName("UTF-8"));
        // System.out.println("length: " + utf8.length);
        int total = 0;

        for (int i = 0; i < utf8.length; i++) {
            // System.out.println("utf[8]: " + utf8[i]);
            byte temp = (byte) utf8[i];
            total += temp;
        }

        total = Math.abs(total * 21);
        // System.out.println("Hash spot: " + total % array.length);
        return total;
    }

    public K[] getKeys() {
        if (size() == 0) return null;

        K[] keys = (K[]) new Object[size()]; // sets size to size()
        int index = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                keys[index] = (K) array[i].getKey();
                index++;

                Entry ptr = array[i];
                while (ptr.getNext() != null) {
                    ptr = ptr.getNext();
                    keys[index] = (K) ptr.getKey();
                    index++;
                }
            }
        }
        return keys;
    }

    public K randomKey() {
        K[] keys = getKeys();
        int rand = (int) (Math.random() * keys.length);
        // System.out.println("Random: " + rand);
        // System.out.println((String) keys[rand]);
        return keys[rand];
        // KEEP IN MIND:
        // Can also reuse this to grab specific keys
        // OUTSIDE OF RANDOM of course
    }

    public int hashIndex(K key) {
        // System.out.println((hash(key) % array.length));
        return (hash(key) % array.length);
    }
}
