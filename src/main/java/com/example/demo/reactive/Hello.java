package com.example.demo.reactive;

import java.util.*;
import java.util.stream.Stream;

public class Hello {
    public List<List<Integer>> find(int[] a) {
        Set<Integer> s1 = new HashSet<>(Arrays.stream(a).boxed().toList()), s1Copy = new HashSet<>(s1);
        Set<Integer> s2 = new HashSet<>(Arrays.stream(b).boxed().toList()), s2Copy = new HashSet<>(s2);

        s1.retainAll(s2Copy);
        s2.retainAll(s1Copy);

        return List.of(new ArrayList<>(s1), new ArrayList<>(s2));
    }

    public boolean finder(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : a) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        Set<Integer> set1 = new HashSet<>();
        for (int i : a) {
            if (set1.contains(i)) {
                return false;
            }
            set1.add(i);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i : a) {
            stringBuilder.append(i);
        }
        String str = new String();
        Math.abs
        return set1.size() == map.size();
    }
    public List<Boolean> find(int[] a, int e) {
        int n = a.length;
        List<Boolean> r = new ArrayList<>(n);

        int m = 0;
        for (int i : a) if (i > m) m = i;
        for (int i = 0; i < n; i++) {
            r.set(i, (a[i] + e) >= m);
        }

        return r;
    }

    public static void main(String[] args) {
        byte[] b = new byte[10];
        b[0] = 1;

        int x = 10 + b[0];

        char c = Character.forDigit(1, 10);
        Hello hello = new Hello();
        int[] a = {1, 2, 3, 4, 5};
        int e = 2;
        List<Boolean> result = hello.find(a, e);
        System.out.println(result); // Output: [false, false, true, true, true]
    }
}
