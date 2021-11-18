package com.example.crmdemo.util;

import java.util.List;

public interface TreeControl<T> {
     String getId();

     String getPid();

     void setChildList(List<T> childList);

     List<T> getChildList();

}
