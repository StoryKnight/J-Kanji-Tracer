// CREDIT to UMN java-1933

public class Entry <K,V> {
    private K key;
    private V value;
    private Entry next;
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public Entry(K key,V value,Entry next){
        this.key =  key;
        this.value = value;
        this.next = next;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }
    public void setKey(K key){
        this.key = key;
    }
    public void setValue(V value){
        this.value = value;
    }
    public Entry getNext(){
        return next;
    }
    public void setNext(Entry next){
        this.next = next;
    }
}