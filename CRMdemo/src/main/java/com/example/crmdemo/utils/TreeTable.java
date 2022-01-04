package com.example.crmdemo.utils;

import java.util.List;

public interface TreeTable<T> {
    String getId();

    String getParentId();

    void setChildList(List<T> childList);

    List<T> getChildList();
}
