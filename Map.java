// This is a simplified version of the java.util.Map interface.
// The assumptions about Map we can make are:
// 1.) A Map cannot contain duplicate keys, and
// 2.) each key can map to at most one value.
// CREDIT to UMN Java 1933

public interface Map<K,V> {
    // This method removes all mappings from the Map.
    // Should be more efficient that calling remove() on every element.
    void clear();

    // This method returns true if the Map contains a mapping for the specified key,
    // and false otherwise.
    boolean containsKey(K key);

    // This method returns true if the Map contains a mapping for the specified value,
    // and false otherwise.
    boolean containsValue(V value);

    // This method returns the value to which the specified key is mapped.
    // If the Map does not contain the specified key, the method instead returns null.
    V get(K key);

    // This method returns true if the Map contains no key-value mappings,
    //and false otherwise.
    boolean isEmpty();

    // This method associates the specified key with the specified value in the Map
    // and returns the newly associated value,
    // if the specified key is not already associated with a non-null value.
    // If the specified key is already associated with a non-null value,
    // the association is left unchanged, and the current value is returned instead.
    V put(K key, V value);

    // This method removes and returns the mapping for the specified key in the Map
    // if it is present, and returns null otherwise.
    V remove(K key);

    // This method replaces and returns the mapping for the specified key only
    // if it is currently mapped to to some value, and returns null otherwise.
    V replace(K key, V value);

    // This method returns the number of key-value mappings in the Map.
}