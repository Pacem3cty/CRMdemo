package com.example.crmdemo.util;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface TreeTable<T> {
    String getId();

    String getParentId();

    void setChildList(List<T> childList);

    List<T> getChildList();
}
