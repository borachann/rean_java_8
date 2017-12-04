import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
