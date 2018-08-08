package com.store.util;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    
    public static String join(long[] array, String separator) {
        if(array == null || array.length == 0) {
                return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(array[0]);
        for(int i=1; i<array.length; i++) {
                sb.append(separator).append(array[i]);
        }
        return sb.toString();
    }

    public static String join(int[] array, String separator) {
        if(array == null || array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(array[0]);
        for(int i=1; i<array.length; i++) {
            sb.append(separator).append(array[i]);
        }
        return sb.toString();
    }

    public static <T> String join(T[] array, String separator) {
        if(array == null || array.length == 0) {
                return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(array[0]);
        for(int i=1; i<array.length; i++) {
            sb.append(separator).append(array[i]);
        }
        return sb.toString();
    }

    public static <T> String join(Collection<T> collection, String separator) {
        if (collection == null || collection.isEmpty()) {			
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = collection.iterator();
        sb.append(iterator.next());
        while(iterator.hasNext()) {
            sb.append(separator).append(iterator.next());
        }
        return sb.toString();
    }

    public static <T> String join(Collection<T> collection, String separator, String left, String right) {
        if (collection.isEmpty()) {			
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = collection.iterator();
        sb.append(left).append(iterator.next()).append(right);
        while(iterator.hasNext()) {
            sb.append(separator).append(left).append(iterator.next()).append(right);
        }
        return sb.toString();
    }

    public static <K, V> String join(Map<K, V> map, String separatorO, String separatorI) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        map.entrySet().stream().forEach((entry) -> {
            sb.append(entry.getKey()).append(separatorI).append(entry.getValue()).append(separatorO);
        });
        sb.delete(sb.length() - separatorO.length(), sb.length());
        return sb.toString();
    }

    public static short[] parseToShortArray(String parser, String separator) {
            String[] ss = parser.split(separator);

            short[] r = new short[ss.length];
            for(int i=0; i<r.length; i++) {
                    r[i] = Short.parseShort(ss[i]);
            }

            return r;
    }

    public static int[] parseToIntArray(String parser, String separator) {
            if(isNullOrEmpty(parser)){
                    return new int[0];
            }
            String[] ss = parser.split(separator);

            int[] r = new int[ss.length];
            for(int i=0; i<r.length; i++) {
                    r[i] = Integer.parseInt(ss[i].trim());
            }

            return r;
    }

    public static float[] parseToFloatArray(String parser, String separator) {
            String[] ss = parser.split(separator);
            float[] r = new float[ss.length];
            for(int i=0; i<r.length; i++) {
                    r[i] = Float.parseFloat(ss[i].trim());
            }

            return r;
    }

    /**
     * 字符串转成int[][]
     * @param str 目标字符串
     * @param fst 一级分隔符
     * @param sst 二级分隔符
     * @return
     */
    public static int[][] parseToIntArray(String str, String fst, String sst){
        if( str == null || str.equals("") ){
              return null;
        }
        String []strs = str.split(fst);
        int arr[][] = new int[strs.length][];
        int i = 0;
        for(String s : strs){
            arr[i] = parseToIntArray(s, sst);
            i++;
        }
        return arr;
    }

    public static String[][] parseToStrArray(String str, String fst, String sst){
       if( str == null || str.equals("") ){
             return null;
        }
        String []strs = str.split(fst);
        String arr[][] = new String[strs.length][];
        int i = 0;
        for(String s : strs){
            arr[i] = s.split(sst);
            i++;
        }
        return arr;
    }

    public static Integer[] parseToIntegerArray(String parser, String separator) {
        String[] ss = parser.split(separator);
        Integer[] r = new Integer[ss.length];
        for(int i=0; i<r.length; i++) {
            r[i] = Integer.parseInt(ss[i]);
        }
        return r;
    }

    public static long[] parseToLongArray(String parser, String separator) {
        String[] ss = parser.split(separator);
        long[] r = new long[ss.length];
        for(int i=0; i<r.length; i++) {
            r[i] = Integer.parseInt(ss[i]);
        }
        return r;
    }

    public static List<Short> partToShortList(String parser, String separator) {
        List<Short> shortList = new ArrayList<>();
        if(!isNullOrEmpty(parser)) {
            for(String p : parser.split(separator)) {
                shortList.add(Short.parseShort(p));
            }
        }
        return shortList;
    }

    public static List<Integer>	partToIntegerList(String parser, String separator) {
        List<Integer> intList = new ArrayList<>();
        if(!isNullOrEmpty(parser)) {
            for(String p : parser.split(separator)) {
                intList.add(Integer.parseInt(p));
            }
        }
        return intList;
    }

    public static HashMap<Byte, Integer> parseByte2Int(String parser, String separatorO, String separatorI) {
        HashMap<Byte, Integer> map = new HashMap<>();
        if(!isNullOrEmpty(parser)) {
            for(String p : parser.split(separatorO)) {
                String[] s = p.split(separatorI);
                Integer value = s.length > 1 ? Integer.parseInt(s[1]) : 0;
                map.put(Byte.parseByte(s[0]), value);
            }
        }
        return map;
    }

    public static Map<Integer, Integer>	parseInt2Int(String parser, String separatorO, String separatorI) {
        Map<Integer, Integer> map = null;
        if(isNullOrEmpty(parser)) {
            map = new HashMap<>(0);
        }
        else{
            String[] arrayS = parser.split(separatorO);
            map = new HashMap<>(arrayS.length);
            for(String p : arrayS) {
                String[] s = p.split(separatorI);
                Integer value = s.length > 1 ? Integer.parseInt(s[1]) : 0;
                map.put(Integer.parseInt(s[0]), value);
            }
        }
        return map;
    }

    public static String concludeInt2Int(Map<Integer, Integer> map, String separatorO, String separatorI) {
        StringBuilder sb = new StringBuilder();
        for (Entry<Integer, Integer> e : map.entrySet()) {
            sb.append(e.getKey()).append(separatorI).append(e.getValue()).append(separatorO);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - separatorO.length());
        }
        return sb.toString();
    }

    public static Map<String, String> parseStr2Str(String parser, String separatorO, String separatorI) {
        Map<String, String> map = null;
        if(!isNullOrEmpty(parser)) {
            String[] arrayS = parser.split(separatorO);
            map = new HashMap<>(arrayS.length);
            for(String p : arrayS) {
                String[] s = p.split(separatorI, 2);
                map.put(s[0], s[1]);
            }
        }
        else{
                map = new HashMap<String, String>(0);
        }
        return map;
    }

    public static Set<Integer> parseIntSet(String extData, String separator ) {
        if (StringUtil.isNullOrEmpty(extData)) {
                return new HashSet<>(0);
        }
        String[] arrS0 = extData.split(separator);
        HashSet<Integer> intSet = new HashSet<>(arrS0.length);
        for (String s0 : arrS0) {
                intSet.add(Integer.parseInt(s0));
        }
        return intSet;
    }

    public static Set<Long> parseLongSet(String extData, String separator) {
        if (StringUtil.isNullOrEmpty(extData)) {
            return new HashSet<>(0);
        }
        String[] arrS0 = extData.split(separator);
        HashSet<Long> set = new HashSet<>(arrS0.length);
        for (String s0 : arrS0) {
            set.add(Long.parseLong(s0));
        }
        return set;
    }

    public static String concludeIntSet(Set<Integer> intSet, String separator ) {
        StringBuilder sb = new StringBuilder();
        for(Integer id : intSet){
            sb.append(id).append(separator);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - separator.length());
        }
        return sb.toString();
    }

    public static List<Long> partToLongList(String parser, String separator) {
        List<Long> longList = new ArrayList<>();
        if(!isNullOrEmpty(parser)) {
            for(String p : parser.split(separator)) {
                longList.add(Long.parseLong(p));
            }
        }
        return longList;
    }

    public static Double[] partToDoubleArray(String parser, String separator) {
        String[] ss = parser.split(separator);
        Double[] r = new Double[ss.length];
        for(int i=0; i<r.length; i++) {
            r[i] = Double.parseDouble(ss[i]);
        }
        return r;
    }

    public static List<Double> partToDoubleList(String parser, String separator) {
        List<Double> doubleList = new ArrayList<>();
        if(!isNullOrEmpty(parser)) {
            for(String p : parser.split(separator)) {
                doubleList.add(Double.parseDouble(p));
            }
        }
        return doubleList;
    }

    public static double add(double v1, double v2) { 
        BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
        BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
        return b1.add(b2).doubleValue(); 
    } 

    public static float add(float v1, float v2) { 
        BigDecimal b1 = new BigDecimal(Float.toString(v1)); 
        BigDecimal b2 = new BigDecimal(Float.toString(v2)); 
        return b1.add(b2).floatValue(); 
    } 

    public static double mutiply(double v1, double v2) { 
        BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
        BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
        return b1.multiply(b2).doubleValue(); 
    } 

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNumer(String str){ 
        Pattern pattern = Pattern.compile("[0-9]*"); 
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static String longArrayToString(long[] array, String separator){
        if(array == null || array.length == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(long element : array){
            sb.append(element).append(separator);
        }
        return sb.delete(sb.length() - separator.length() , sb.length()).toString();
    }
}
