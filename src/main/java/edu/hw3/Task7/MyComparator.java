package edu.hw3.Task7;

import java.util.Comparator;

//@Override
//public Comparator<Map.Entry<K, V>> getComparator() {
// Adapt or create a key-based comparator
//    if (tree.comparator != null) {
//    return Map.Entry.comparingByKey(tree.comparator);
//    }
//    else {
//    return (Comparator<Map.Entry<K, V>> & Serializable) (e1, e2) -> {
@SuppressWarnings("unchecked")
//                    Comparable<? super K> k1 = (Comparable<? super K>) e1.getKey();
//    return k1.compareTo(e2.getKey());
//    };
//    }
//    }

public class MyComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 != null && o2 == null) {
            return 1;
        }
        if (o1 == null && o2 != null) {
            return -1;
        }
        return o1.compareTo(o2);
    }
}
