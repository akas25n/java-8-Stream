package com.example.java.javastream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStream {
    public static void main(String[] args) throws IOException {

         // Integer Stream with skip
        System.out.println("\n1.Ex. IntStream with skip()");
        IntStream
                .range(1, 10)
                .skip(5)
                .forEach(System.out::println);

        System.out.println("\n2.Ex. IntStream with sum()");
        System.out.println(
                IntStream
                .range(1, 5)
                .sum());

        System.out.println("\n3.Ex. Stream.of, sorted and findFirst");
        Stream.of("Khal", "Akash", "Batash")
                .sorted()
                //.findFirst() --will find the first item
                //.ifPresent(System.out::println); --will print the first item
                .forEach(System.out::println);

        System.out.println("\n4.Ex. Stream from array, sort, filter and print-");
        String[] names = {"Ak", "Bk","Ab", "Bc"};
        Arrays.stream(names) // same as Stream.of(names)
                .filter(x -> x.startsWith("A"))
                .sorted()
                .forEach(System.out::println);


        System.out.println("\n5.Ex. average of an int array-");
        Arrays.stream(new int[] {2,4,6,8,10})
                .map(X -> X * X)
                .average()
                .ifPresent(System.out::println);

        System.out.println("\n6.Ex. Stream from List, filter and print-");
        List<String> people = Arrays.asList("Ak", "Bk","Ab", "Bc");

        people.stream()
                .map(String::toUpperCase)
                .filter( X -> X.startsWith("B"))
                .forEach(System.out::println);

        System.out.println("\n\n7.Ex. Stream rows from text file and save to List-");
        Stream<String> bands = Files.lines(Paths.get("bands.txt"));
        bands
                .sorted()
                .filter(X -> X.length() > 13)
                .forEach(System.out::println);
        bands.close();

        System.out.println("\n8.Ex. Stream rows from text file and save to List-");
        List<String> bands2 = Files.lines(Paths.get("bands.txt"))
                .filter(X -> X.contains("and"))
                .collect(Collectors.toList());
        bands2.forEach(System.out::println);

        System.out.println("\n9.Ex. Stream rows from CSV file and count-");
        Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
        int rowCount = (int) rows1
                .map(X -> X.split(","))
                .filter(X -> X.length== 3)
                .count();
        System.out.println(rowCount + " rows.");
        rows1.close();

        System.out.println("\n10.Ex. Stream rows from CSV file and parse data from rows-");
        Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
        rows2
                .map(X -> X.split(","))
                .filter(X -> X.length == 3)
                .filter(X -> Integer.parseInt(X[1]) > 15)
                .forEach(X -> System.out.println(X[0] + " " + X[1] + " " + X[2]));
        rows2.close();

        System.out.println("\n11.Ex. Stream rows from CSV file and store fields in Hashmap-");
        Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
        Map<String, Integer> map;
        map = rows3
                .map(X -> X.split(","))
                .filter(X -> X.length == 3)
                .filter(X -> Integer.parseInt(X[1]) > 15)
                .collect(Collectors.toMap(
                        X -> X[0],
                        X -> Integer.parseInt(X[1])));
        rows3.close();
        for (String key: map.keySet()){
            System.out.println(key + " " + map.get(key));
        }

        System.out.println("\n12.Ex. Reduction - sum -");
        double total = Stream.of(7.3,1.5,4.8)
                .reduce(0.0, Double::sum);
        System.out.println("Total = " + total);

        System.out.println("\n123.Ex. Reduction - summary statistics -");
        IntSummaryStatistics summaryStatistics = IntStream.of(5, 7, 2, 19, 88, 73, 4)
                .summaryStatistics();
        System.out.println(summaryStatistics);


























































































































    }// end of main method
}
