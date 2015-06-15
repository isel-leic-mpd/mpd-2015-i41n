import java.util.function.*;
import java.util.stream.*;
import java.util.*;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Created by mcarvalho on 15-06-2015.
 */
public class App {

    public static void questao1(){
        Runnable r = () -> {};
        Consumer<String> c1 = arg -> { System.out.println(arg); };
        Consumer<String> c2 = (arg) -> System.out.println();
        Consumer<String> c3 = arg -> System.out.println(arg);
        IntFunction<Integer> f1 = (int i) -> i + 1;
        IntConsumer f2 = (int i) -> { int n = i + 1; };
    }

    public static void questao2(){
        BiConsumer<String, Integer> c1 = (String s, Integer b) -> System.out.println(s + ": " + b);
        ObjIntConsumer<String> c2 = (String s, int b) -> System.out.println(s + ": " + b);
        Predicate<String> p1 = (s) -> s.length() == 0;
        Function<String, Boolean> p2 = (s) -> s.length() == 0;
        BiPredicate<Integer, Integer> p3 = (a, b) -> a - b == 0;
        BiFunction<Integer, Integer, Boolean> p4 = (a, b) -> a - b == 0;
    }

    public static Stream<Classroom> overcrowdedClass(Stream<Classroom> crs, int maxNrOfStudents) {
        return crs.filter(c -> c.students.size() > maxNrOfStudents);
    }

    public static Classroom biggestClass(Stream<Classroom> crs) {
        return crs
                .max(Comparator.comparingInt(c -> c.students.size()))
                .get();
        /*
        return crs
                .sorted(Comparator.comparing(c -> c.students.size()))
                .findFirst()
                .get();
                */
    }

    public static String youngestStudent(Stream<Classroom> crs) {
        Comparator<Student> youngestCmp = comparingLong(s -> s.birthTimestamp);
        /*
        return crs
                .map(c -> c.students.stream().min(youngestCmp).get()) // -> Stream<Student>>
                .min(youngestCmp)
                .get()
                .name;
        */
        return crs
                .flatMap(c -> c.students.stream()) // -> Stream<Student>
                .min(youngestCmp)
                .get()
                .name;
    }

    public static Map<Integer, List<Classroom>> classesByNrOfStudents(Stream<Classroom> crs) {
        return crs.collect(groupingBy(c -> c.students.size()));
    }

    public static Map<Integer, List<String>> classesIdByNrOfStudents(Stream<Classroom> crs) {
        return crs.collect(groupingBy(
                c -> c.students.size(),
                mapping(c -> c.id, toList())));
    }

    public static Map<Integer, List<String>> studentsNameByBirthYear(Stream<Classroom> crs) {
        return crs
                .flatMap(c -> c.students.stream()) // -> Stream<Student>
                .collect(
                        groupingBy(
                                s -> s.birthYear,
                                mapping(s -> s.name, toList())));
    }



}


class Classroom {
    public  String id;
    public  List<Student> students;
}

class Student {
    public  int nr;
    public  String name;
    public  long birthTimestamp;
    public  int birthYear;
}
