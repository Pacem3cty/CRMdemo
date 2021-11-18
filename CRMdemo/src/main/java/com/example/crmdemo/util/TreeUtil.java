package com.example.crmdemo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取树型工具类
 *
 */
public class TreeUtil {

    //获取顶层节点
    public static <T extends TreeControl<T>> List<T> getTreeList(String topId, List<T> entityList){//输出树形控件数据格式List
        List<T> resultList = new ArrayList<>();//存储顶层的数据

        Map<Object, T> treeMap = new HashMap<>();
        T itemTree;

        for(int i=0;i<entityList.size()&&!entityList.isEmpty();i++) {
            itemTree = entityList.get(i);
            treeMap.put(itemTree.getId(),itemTree);//把所有的数据放到map当中，id为key
            if(topId.equals(itemTree.getPid()) || itemTree.getPid() == null) {//把顶层数据放到集合中
                resultList.add(itemTree);
            }
        }

        //循环数据，把数据放到上一级的childen属性中
        for(int i = 0; i< entityList.size()&&!entityList.isEmpty();i++) {
            itemTree = entityList.get(i);
            T data = treeMap.get(itemTree.getPid());//在map集合中寻找父亲
            if(data != null) {//判断父亲有没有
                if(data.getChildList() == null) {
                    data.setChildList(new ArrayList<>());
                }
                data.getChildList().add(itemTree);//把子节点 放到父节点childList当中
                treeMap.put(itemTree.getPid(), data);//把放好的数据放回map当中
            }
        }

        return resultList;
    }

    //获取顶层节点
    public static <T extends TreeTable<T>> List<T> getTreeTable(String topId, List<T> entityList){//输出树形表格数据格式List
        List<T> resultList = new ArrayList<>();//存储顶层的数据

        Map<Object, T> treeMap = new HashMap<>();
        T itemTree;

        for(int i=0;i<entityList.size()&&!entityList.isEmpty();i++) {
            itemTree = entityList.get(i);
            treeMap.put(itemTree.getId(),itemTree);//把所有的数据放到map当中，id为key
            if(topId.equals(itemTree.getParentId()) || itemTree.getParentId() == null) {//把顶层数据放到集合中
                resultList.add(itemTree);
            }
        }

        //循环数据，把数据放到上一级的childen属性中
        for(int i = 0; i< entityList.size()&&!entityList.isEmpty();i++) {
            itemTree = entityList.get(i);
            T data = treeMap.get(itemTree.getParentId());//在map集合中寻找父亲
            if(data != null) {//判断父亲有没有
                if(data.getChildList() == null) {
                    data.setChildList(new ArrayList<>());
                }
                data.getChildList().add(itemTree);//把子节点 放到父节点childList当中
                treeMap.put(itemTree.getParentId(), data);//把放好的数据放回map当中
            }
        }

        return resultList;
    }

}

