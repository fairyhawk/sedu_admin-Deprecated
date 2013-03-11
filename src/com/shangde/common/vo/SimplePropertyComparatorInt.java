package com.shangde.common.vo;

import java.util.Comparator;

public class SimplePropertyComparatorInt<E extends SimpleProperty> implements Comparator<E> {
    public int compare(E o1, E o2) {
        if (o1.getValue() < o2.getValue())
            return -1;
        else if (o1.getValue() > o2.getValue())
            return 1;
        else
            return 0;
    }
}
