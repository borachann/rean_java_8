import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang3.ArrayUtils;
import sun.awt.geom.AreaOp;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Chann Bora (b.chann@gl-f.com) on 12/4/2017.
 */
public class MyClass {
  public static void main(String[] args){
    System.out.println(">>>>>>>>>>>>> Lesson 1 Comparator >>>>>>>>>>>>>>>>>>>>");
    
    List<Developer> developerList = getDevelopers();
    System.out.println("- before sort: ");
    developerList.forEach(e-> System.out.println(e.toString()));
  
    System.out.println("- after sort by name (Collections): ");
    Collections.sort(developerList, new Comparator<Developer>() {
      @Override
      public int compare(Developer o1, Developer o2) {
        return o1.getDeveloperName().compareTo(o2.getDeveloperName());
      }
    });
    developerList.forEach(e-> System.out.println(e.toString()));
  
    System.out.println("- after sort by age (lambda in java 8): ");
    developerList.sort(new Comparator<Developer>() {
      @Override
      public int compare(Developer o1, Developer o2) {
        return o1.getDeveloperAge() - o2.getDeveloperAge();
      }
    });
    developerList.forEach(e-> System.out.println(e.toString()));
  
    System.out.println("- after sort by name (lambda in java 8 of list): ");
    developerList.sort((Developer o1, Developer o2) -> o1.getDeveloperName().compareTo(o2.getDeveloperName()));
    developerList.forEach(e-> System.out.println(e.toString()));
  
    System.out.println("");
    System.out.println(">>>>>>>>>>>>> Lesson 2 forEach and Map >>>>>>>>>>>>>>>>>>>>");
    Map<String, Integer> map = new HashMap<>();
    map.put("A", 20);
    map.put("B", 21);
    map.put("C", 22);
    
    for(Map.Entry<String, Integer> entry : map.entrySet()){
      System.out.println("map : " + entry.getKey() + " Count : " + entry.getValue());
    }
  
    System.out.println("- Using foreach (Map) : ");
    map.forEach((k,v) -> System.out.println("key : " + k + " value : " + v));
    
    List<String> items = new ArrayList<>();
    items.add("A");
    items.add("B");
    items.add("C");
    System.out.println("- Using foreach (list) : ");
    items.forEach(item-> System.out.println(item));
    
    // stream to check if the list contains and convert to string
    items.stream().filter(e-> e.contains("B")).forEach(System.out::println);
    // stream to check and convert to list;
    items.stream().filter(e-> !"C".equals(e)).collect(Collectors.toList()).forEach(System.out::println);
    // find any or orelse
    String search = items.stream().filter(e-> e.equals("d")).findAny().orElse("Search not found.");
    System.out.println("result : " + search);
    
    // convert Object to string by Map (convert stream to string)
    System.out.println("- Convert Object to string by map : ");
    String result = developerList.stream().filter(e-> "chann".equals(e.getDeveloperName())).map(Developer::getDeveloperName).findAny().orElse("Search not found.");
  
    System.out.println("result : " + result);
    
    // group by in collector
    Map<String, Long> longMap = items.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    System.out.println("Long Map count by grouping: " + longMap);
    
    // sort a map and add to final map
    Map<String, Long> finalMap = new LinkedHashMap<>();
    longMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).forEachOrdered(e-> finalMap.put(e.getKey(),e.getValue()));
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 3 filter null from stream >>>>>>>>>>>>>>>>>>>>>>>>");
    Stream<String> language = Stream.of("chann", null, "alvin", "jason", null);
    List<String> resultFilter = language.filter(x-> x != null).collect(Collectors.toList());
    resultFilter.forEach(System.out::println);
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 4 convert array to stream >>>>>>>>>>>>>>>>>>>>>");
    String[] array = {"a", "b", "c", "d"};
    Stream<String> resultArray = Arrays.stream(array);
    resultArray.forEach(System.out::println);
    //resultArray.forEach(System.out::println); java.lang.IllegalStateException: stream has already been operated upon or closed
    
    Stream<String> stringStream = Stream.of(array);
    stringStream.forEach(System.out::println);
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 5 using Supplier<Stream<String>> >>>>>>>>>>>>>>>>>>>>>");
    Supplier<Stream<String>> streamSupplier = () -> Stream.of(array);
    streamSupplier.get().forEach(System.out::println);
    streamSupplier.get().forEach(System.out::println);
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 6 using optional >>>>>>>>>>>>>>>>>>>>>");
    Optional<String> gender = Optional.of("Male");
    String answer1 = "bora";
    String answer2 = null;
    System.out.println("gender : " + gender);
    System.out.println("gender.get() : " + gender.get());
    System.out.println("Optional.empty() : " + Optional.empty());
    System.out.println("Optional.ofNullable(answer1) : " + Optional.ofNullable(answer1));
    System.out.println("Optional.ofNullable(answer2) : " + Optional.ofNullable(answer2));
    gender.ifPresent(s -> System.out.println("Optional.ifPresent : do an action."));
    System.out.println("Optional.isPresent : " + gender.isPresent());
    System.out.println("Optional.isPresent : " + gender.orElse("N/A"));
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 7 random next int >>>>>>>>>>>>>>>>>>>>>");
    Random random = new Random();
    for (int i =0; i<10; i++){
      System.out.println("Random min and max : from 0 to max(10) : " + (random.nextInt(10 - 1) + 1));
    }
    for (int i =0; i<10; i++) {
      System.out.println("Random min and max : from min to max(2,10) : " + (random.nextInt((10 - 2) + 1) + 2));
    }
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 8 String joiner >>>>>>>>>>>>>>>>>>>>>");
    StringJoiner stringJoiner = new StringJoiner(", ");
    stringJoiner.add("bora");
    stringJoiner.add("sith");
    stringJoiner.add("kimsong");
    stringJoiner.add("thanak");
    stringJoiner.add("kuylim");
    System.out.println("Result : " + stringJoiner.toString());
  
    System.out.println("String.join : " + String.join("-", "2015","01","2017"));
    System.out.println("String.join : " + String.join("-", array));
    System.out.println("String.join one in object : " + developerList.stream().map(x-> x.getDeveloperName())
            .collect(Collectors.joining(", " , "{", "}")));
    
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 9 Read file + stream >>>>>>>>>>>>>>>>>>>>>");
    String fileName = "D://User_Profile//Desktop//lines.txt";
    
    try(Stream<String> stream = Files.lines((Paths.get(fileName)))){
      stream.forEach(System.out::println);
    }catch (IOException e){
      e.printStackTrace();
    }
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 9.1 Read file + BufferedRead >>>>>>>>>>>>>>>>>>>>>");
    List<String> list = new ArrayList<>();
    try(BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
      list = br.lines().collect(Collectors.toList());
    }catch (IOException e){
      e.printStackTrace();
    }
    list.forEach(System.out::println);
  
    /*System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 10 join array >>>>>>>>>>>>>>>>>>>>>");
    List<String> array1 = developerList.stream().map(e-> e.getDeveloperName()).collect(Collectors.toList());
    System.out.println("join 2 array " + ArrayUtils.addAll(array,array1.toArray().toString()));*/
  
    String password = "mypassword";
    char[] temp = password.toCharArray();
    for(char arrays : temp){
      System.out.println("Arrays : " + arrays);
    }
    password.chars().mapToObj(x-> (char) x).forEach(System.out::println);
    
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 10 conver array to list >>>>>>>>>>>>>>>>>>>>>");
    Arrays.stream(array).collect(Collectors.toList()).forEach(System.out::println);
    Arrays.asList(array).forEach(System.out::println);
    int[] arrayInt = {1,2,3,4,5};
    Arrays.stream(arrayInt).boxed().collect(Collectors.toList()).forEach(System.out::println);
    Arrays.asList(arrayInt).forEach(System.out::println); // no value return
    // we can user IntStream.of(array)
    IntStream.of(arrayInt).boxed().collect(Collectors.toList());
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 11 anyMatch >>>>>>>>>>>>>>>>>>>>>");
    boolean resultMatch = Arrays.stream(array).anyMatch("a"::equals);
    System.out.println("result : " + resultMatch);
    
    boolean resultIntMatch = IntStream.of(arrayInt).anyMatch(x-> x==4);
    System.out.println("result : " + resultIntMatch);
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 12 Date >>>>>>>>>>>>>>>>>>>>>");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
    //formatter Date to string
    String date = sdf.format(new Date());
    System.out.println("today : " + date);
    
    // convert string to date
    Date newDate = new Date();
    try {
      newDate = sdf.parse(date);
      System.out.println("new Date : " + newDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 12.1 Calender >>>>>>>>>>>>>>>>>>>>>");
    //Calendar calendar = new GregorianCalendar(2013,3,3);
    Calendar calendar = new GregorianCalendar();
    System.out.println("calender : " + sdf.format(calendar.getTime()));
    System.out.println("Year : " + calendar.get(Calendar.YEAR));
    System.out.println("Month : " + calendar.get(Calendar.MONTH));
    System.out.println("DayofMonth : " + calendar.get(Calendar.DAY_OF_MONTH));
    System.out.println("DayofWeek : " + calendar.get(Calendar.DAY_OF_WEEK));
    System.out.println("WeekofYear : " + calendar.get(Calendar.WEEK_OF_YEAR));
    System.out.println("WeekofMonth : " + calendar.get(Calendar.WEEK_OF_MONTH));
  
    System.out.println("Hour : " + calendar.get(Calendar.HOUR));
    System.out.println("HourofDay : " + calendar.get(Calendar.HOUR_OF_DAY));
    System.out.println("Minute : " + calendar.get(Calendar.MINUTE));
    System.out.println("Time : " + calendar.getTime());
    
    calendar.set(Calendar.YEAR, 2018);
    System.out.println("after set : " + calendar.get(Calendar.YEAR));
    calendar.add(Calendar.YEAR, 1); // to subtract year, we use negative amount
    System.out.println("after add 1 more year : " + calendar.get(Calendar.YEAR));
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 12.2 convert Date to Calendar using Calendar.getInstance() >>>>>>>>>>>>>>>>>>>>>");
    Calendar calendarInstance = Calendar.getInstance();
    calendarInstance.setTime(newDate);
  
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date date1 = sdfDate.parse("2009-12-31");
      Date date2 = sdfDate.parse("2010-12-31");
      
      if(date1.compareTo(date2) > 0)
        System.out.println("date 1 is greater than date 2");
      else if(date1.before(date2))
        System.out.println("date 1 is happen before date 2");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  
    System.out.println(">>>>>>>>>>>>>>>>>>>>> Lesson 12.3 LocalDate >>>>>>>>>>>>>>>>>>>>>");
    DateTimeFormatter sdtDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date1 = LocalDate.of(2009,12,31);
    LocalDate date2 = LocalDate.of(2010, 12, 31);
    
    // we can compareTo , isBefore, is equal, like in Calendar and Date
    if(date1.compareTo(date2) < 0) {
      System.out.println("it equal to : date1.isBefore(date2).");
      System.out.println("date 1 : " + sdtDateTime.format(date1) + " < date 2 : " + sdtDateTime.format(date2));
      
    }
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    System.out.println("System.currentTimeMillis() : " + timestamp);
    System.out.println("Timestamp of formatter : " + new Timestamp(newDate.getTime()));
    
    long lStartTime = System.nanoTime();
    
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    long lEndTime = System.nanoTime();
    System.out.println("Start Nano Time : " + lStartTime + "End Nano Time : " + lEndTime + "it was delay : " +
            (lEndTime - lStartTime )/1000000);
  }
  
  private static List<Developer> getDevelopers(){
    List<Developer> result = new ArrayList<Developer>();
    
    result.add(new Developer("chann", "phnom penh", LocalDate.now().minusYears(20), 24));
    result.add(new Developer("alvin", "phnom penh", LocalDate.now().minusYears(20), 22));
    result.add(new Developer("jason", "phnom penh", LocalDate.now().minusYears(20), 23));
    result.add(new Developer("iris", "phnom penh", LocalDate.now().minusYears(20), 25));
    
    return result;
  }
}
