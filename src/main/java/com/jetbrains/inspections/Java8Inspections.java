package com.jetbrains.inspections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.lang.System.out;
import static java.util.Arrays.sort;

@SuppressWarnings("unused")
public class Java8Inspections {
    private final Map<Integer, List<String>> integerStringMap = new HashMap<>();

    private final String[] stringArray = new String[]{"IntelliJ IDEA", "AppCode", "CLion", "0xDBE", "Upsource"};

    public void lambdas() {
        //Anonymous function Function<Function, Function>() can be replaced with lambda
        Function<Function, Function> f1 = new Function<Function, Function>() {
            @Override
            public Function apply(Function function) {
                return function.compose(function);
            }
        };

        //Expand lambda expression body to {...}
        Function<Function, Function> f2 = function -> function.compose(function);

        //Remove redundant types
        Function<Function, Function> f3 = (Function function) -> function.compose(function);

        //Statement lambda can be replaced with expression lambda
        Function<Function, Function> f4 = (Function function) -> {
            return function.compose(function);
        };

        //Lambda can be replaced with method reference
        sort(stringArray, (s1, s2) -> s1.compareToIgnoreCase(s2));

        //Replace with forEach on foo
        ArrayList<String> foo = new ArrayList<>();
        for (String s : foo) {
            if (s != null) {
                out.println(s);
            }
        }
    }

    private int replaceWithCountSimple() {
        int count = 0;

        for (String s : stringArray) {
            count++;
        }

        return count;
    }

    private int replaceWithCountNested() {
        int count = 0;

        for (List<String> list : integerStringMap.values()) {
            if (list != null) {
                for (String stringVal : list) {
                    if (stringVal.contains("error")) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private int replaceWithSum() {
        int count = 0;

        for (String s : stringArray) {
            count += s.length();
        }

        return count;
    }

    private int replaceWithMapToInt() {
        int count = 0;

        for (List<String> list : integerStringMap.values()) {
            if (list != null) {
                for (String stringVal : list) {
                    if (stringVal.contains("error")) {
                        count += stringVal.length();
                    }
                }
            }
        }

        return count;
    }

    private List<String> replaceWithCollect() {
        List<String> result = new ArrayList<>();

        for (String line : stringArray) {
            if (line != null) {
                for (String word : line.split(" ")) {
                    result.add(word);
                }
            }
        }
        return result;
    }

    private List<String> replaceWithCollectAndMap() {
        List<String> result = new ArrayList<>();

        for (String line : stringArray) {
            if (line != null) {
                for (String word : line.split(" ")) {
                    result.add(word.substring(0, 3));
                }
            }
        }
        return result;
    }

    // missing an example for "continue" as it looked a bit complicated to type

    private List<String> getListOfAllNonEmptyStringValues(Map<String, List<String>> map) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey().isEmpty()) {
                continue;
            }
            List<String> list = entry.getValue();
            if (list == null) {
                continue;
            }
            for (String str : list) {
                String trimmed = str.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                result.add(trimmed);
            }
        }
        return result;
    }

    private boolean hasEmptyString(String[][] data) {
        for (String[] row : data) {
            for (String str : row) {
                if (str.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

}
