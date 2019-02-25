package com.example.slyangsecurity;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class TreeUtil {

    /**
     * 将带有父子关系的数组转为树形结构
     *
     * @param rootNodeId      根节点ID
     * @param data        父子关系的数组
     * @param nodeIdKey   节点ID KEY
     * @param nodePIdKey  父节点ID KEY
     * @param childrenKey 子节点 KEY
     * @return
     */
    public static Map<String, Object> convertArrayToTree(String rootNodeId, List<Map<String, Object>> data, String nodeIdKey, String nodePIdKey, String childrenKey) {

        Map<String, Object> root;
        Optional<Map<String, Object>> optional = data.stream().filter(t -> rootNodeId.equals(String.valueOf(t.get(nodeIdKey)))).findFirst();
        if (!optional.isPresent()) return null;
        root = optional.get();

        List<Map<String, Object>> children = data.stream().filter(t -> rootNodeId.equals(String.valueOf(t.get(nodePIdKey)))).collect(Collectors.toList());
        root.put(childrenKey, children);

        if (children.size() > 0) {
            for (Map<String, Object> child : children) {
                child = convertArrayToTree(String.valueOf(child.get(nodeIdKey)), data, nodeIdKey, nodePIdKey, childrenKey);
            }
        }

        return root;
    }

    /**
     * 将带有父子关系的数组转为树形结构
     *
     * @param rootNodeIds     根节点ID
     * @param nodeIdKey   节点ID KEY
     * @param nodePIdKey  父节点ID KEY
     * @param childrenKey 子节点 KEY
     * @return
     */
    public static List<Map<String, Object>> convertArrayToTreeList(Set<String> rootNodeIds, List<Map<String, Object>> data, String nodeIdKey, String nodePIdKey, String childrenKey) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (String nodeId : rootNodeIds) {
            mapList.add(convertArrayToTree(nodeId, data, nodeIdKey, nodePIdKey, childrenKey));
        }
        return mapList;
    }

    /**
     * 将带有父子关系的数组转为树形结构
     * 把父节点 为空的当成 根结点
     * @param nodeIdKey   节点ID KEY
     * @param nodePIdKey  父节点ID KEY
     * @param childrenKey 子节点 KEY
     * @return
     */
    public static List<Map<String, Object>> convertArrayToTreeList(List<Map<String, Object>> data, String nodeIdKey, String nodePIdKey, String childrenKey) {
        Set<String> rootNodeIds = data.stream().filter(i -> i.get(nodePIdKey) == null ).map( i -> String.valueOf(i.get(nodeIdKey)) ).collect(Collectors.toSet());
        return convertArrayToTreeList(rootNodeIds,data,nodeIdKey,nodePIdKey,childrenKey);
    }

    @Test
    public void hello() {

        byte[]  testB="哈罗".getBytes(Charset.forName("UTF-8"));

        StringBuilder stringBuilderHex = new StringBuilder();
        StringBuilder stringBuilderHex2 = new StringBuilder();
//        StringBuilder stringBuilderBinary = new StringBuilder();
        for(int i=0;i<testB.length;i++){
            stringBuilderHex.append( Integer.toHexString(testB[i] & 0xFF ) ) ;
            stringBuilderHex2.append( Integer.toHexString(testB[i]      ) ) ;
//            stringBuilderBinary.append( Integer.toBinaryString(testB[i] & 0xFF ) ) ;
        }
        System.out.println(  stringBuilderHex.toString() );
        System.out.println(  stringBuilderHex2.toString() );
//        System.out.println(  stringBuilderBinary.toString() );
    }

    @Test
    public void hello2() {

//        byte t1= (byte) (-25 & 0xFF);
//        int t2= (-25 & 0xFF);
        
        //     11111111111111111111111111100111
        //                     1111111111111111
        //     00000000000000001111111111100111
        //     int      231
        //     byte     -25

        System.out.println(   -25 & 0xFF );
        System.out.println(   -25  );

        System.out.println(   Integer.toHexString( -25 & 0xFF  ) );
        System.out.println(   Integer.toHexString( -25 )         );

        
//        System.out.println(   Integer.toHexString( -25  ) );
//        System.out.println(   Integer.toHexString( -109  ) );
//        System.out.println(   Integer.toHexString( -120  ) );
//        System.out.println(   Integer.toHexString( -25  ) );
//        System.out.println(   Integer.toHexString( -67  ) );
//        System.out.println(   Integer.toHexString( -105  ) );
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        stringBuilder.append(  Integer.toHexString( -25  )  )
//                .append(  Integer.toHexString( -109  )  )
//                .append(  Integer.toHexString( -120  )  )
//                .append(  Integer.toHexString( -25  )  )
//                .append(  Integer.toHexString( -67  )  )
//                .append(  Integer.toHexString( -105  )  );
//
//        System.out.println(   stringBuilder.toString() );

    }


}
