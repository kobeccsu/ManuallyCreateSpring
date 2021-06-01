import com.MyMap;

import java.util.Map;

public class MyHashMap<K, V> implements MyMap<K, V> {
    Entry<K, V>[] table = null;
    int size = 0;

    public MyHashMap(){
        table = new Entry[16];
    }

    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (null == entry){
            table[index] = new Entry<>(k, v, index, null);
            size++;
        }else{
            table[index] = new Entry<>(k, v, index, entry);
        }


        return table[index].getValue();
    }

    private int hash(K k) {
        int i = k.hashCode() % 16;
        return i>=0 ? i : -i;
    }

    @Override
    public V get(K k) {
        int index = hash(k);
        Entry<K, V> entry = findValue(table[index], k);
        return entry == null ? null : entry.getValue();
    }

    private Entry<K,V> findValue(Entry<K,V> kvEntry, K k) {
        if (k.equals(kvEntry.getKey()) || k == kvEntry.getKey()){
            return kvEntry;
        } else{
            if (kvEntry.next != null){
                return findValue(kvEntry.next, k);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements MyMap.Entry<K, V>{
        K k; V v; int index; Entry<K, V> next;

        public Entry(K k, V v, int index, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.index = index;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
