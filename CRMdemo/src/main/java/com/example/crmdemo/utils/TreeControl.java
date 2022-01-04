package com.example.crmdemo.utils;

import java.util.List;

public interface TreeControl<T> {
     String getId();

     String getPid();

     void setChildList(List<T> childList);

     List<T> getChildList();

}
