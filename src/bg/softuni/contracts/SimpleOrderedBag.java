package bg.softuni.contracts;

import java.util.Collection;

/**
 * Created by Niki on 22.7.2016 г..
 */
public interface SimpleOrderedBag<E extends Comparable<E>> extends Iterable<E> {

    void add(E element);

    void addAll(Collection<E> elements);

    int size();

    String joinWith(String joiner);

}
