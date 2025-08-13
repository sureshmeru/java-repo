package com.primesoft.javatraining.Training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ListExample {

    // Bubble Sort implementation
    public static void bubbleSort(List<String> stringList){
        int n = stringList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare strings lexicographically
                if (stringList.get(j).compareTo(stringList.get(j + 1)) > 0) {
                    // Swap elements
                    String temp = stringList.get(j);
                    stringList.set(j, stringList.get(j + 1));
                    stringList.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Dog");
        fruits.add("Cat");

        // Accessing elements
        System.out.println("First fruit: " + fruits.get(0));  // Apple
        // Removing an element
        // fruits.remove("Banana");  // Remove by value
        // fruits.remove(1);     // Remove by index
        // Replacing an element
        fruits.set(1, "Orange"); // Replace Mango with Orange 
        // Size of the list
        System.out.println("Total No. of fruits: " + fruits.size());
        // Final list
        System.out.println("Fruits: " + fruits);
        
        // List of Animals
        List<String> Animals = new ArrayList<>();
        Animals.add("Dog");
        Animals.add("Cat");
        Animals.add("Fish");
        Animals.add("Elephant");
        Animals.add("Lion");
        Animals.add("Tiger");
        System.out.println("Animals: " + Animals);

        // Animals.remove(2);
        // System.out.println("Animals: cat removed " + Animals);
        System.out.println("Animals: " + Animals.get(2));
        Animals.set(2, "Rabbit");
        System.out.println("Animals: " + Animals);
        System.out.println("Animals: " + Animals.size());

        List<String> MerageList = new ArrayList<>();
        List<String> FilteredList = new ArrayList<>();
        MerageList.addAll(fruits);
        MerageList.addAll(Animals);
        // System.out.println("MerageList: " + MerageList);

        for(int i = 0; i < MerageList.size(); i++){
            // boolean hasApple = FilteredList.contains("Apple");
            if(!FilteredList.contains(MerageList.get(i))){
                FilteredList.add(MerageList.get(i));
            }
        }
        System.out.println("FilteredList: " + FilteredList);

        bubbleSort(FilteredList);
        System.out.println("bubbleSort-FilteredList: " + FilteredList);

        // MerageList.removeAll(fruits);
        System.out.println("MerageList: " + MerageList);
        // Sorting the list in descending order

        // HashSet list
        HashSet<String> Vegetables = new HashSet<>();
        Vegetables.add("Arugula");
        Vegetables.add("Beetroot");
        Vegetables.add("Carrot");
        Vegetables.add("Carrot");
        Vegetables.add("Turnip");
        Vegetables.add("Radish");
        Vegetables.add("Celeriac");
        Vegetables.add("Rutabaga/Swede");
        Vegetables.add("Parsnip");
        Vegetables.add("Horseradish");
        Vegetables.add("Potatoes");
        Vegetables.add("Potatoes");
        Vegetables.add("Sweet Potatoes");
        System.out.println(Vegetables.size() + " < list of veg with hashSet: " + Vegetables);
        
        // HashMap
        HashMap<String, String> countries = new HashMap<>();
        countries.put("India", "New Delhi");
        countries.put("USA", "Washington, D.C.");
        countries.put("France", "Paris");
        countries.put("Germany", "Berlin");
        countries.put("Japan", "Tokyo");

        countries.remove("France");
        countries.put("India", "Mumbai");
        countries.put("China", "Beijing");
        countries.put("Russia", "Moscow");
        countries.put("Brazil", "Brasilia");
        countries.put("Argentina", "Buenos Aires");
        countries.put("Canada", "Ottawa");
        countries.put("Australia", "Canberra");
        countries.put("South Africa", "Pretoria");
        countries.put("Egypt", "Cairo");
        countries.put("Nigeria", "Abuja");

        System.out.println(countries.size() + " < = list of countries with hashMap: " + countries);

        HashSet<String> countriesSet = new HashSet<>(countries.keySet());
        System.out.println(countriesSet.size() + " < = list of countries with hashSet: " + countriesSet);

        HashSet<String> countriesValues = new HashSet<>(countries.values());
        System.out.println(countriesValues.size() + " < = list of countries with hashSet: " + countriesValues);

        HashMap<String, String> countriesMap = new HashMap<>(countries);
        System.out.println(countriesMap.size() + " < = list of countries with hashMap: " + countriesMap);

        Hashtable<String, String> countriesTable = new Hashtable<>(countries);
        System.out.println(countriesTable.size() + " < = list of countries with hashTable: " + countriesTable);

        // TreeMap
        TreeMap<String, String> countriesTreeMap = new TreeMap<>(countries);
        System.out.println(countriesTreeMap.size() + " < = list of countries with treeMap: " + countriesTreeMap);

        // TreeSet
        TreeSet<String> countriesTreeSet = new TreeSet<>(countries.keySet());
        System.out.println(countriesTreeSet.size() + " < = list of countries with treeSet: " + countriesTreeSet);

        // Iterator
        HashSet<String> cities = new HashSet<>();
        cities.add("Hyderabad");
        cities.add("Bangalore");
        cities.add("Mumbai");
        cities.add("Delhi");
        cities.add("Chennai");
        cities.add("Kolkata");
        cities.add("Pune");
        cities.add("Jaipur");
        cities.add("Ahmedabad");
        cities.add("Surat");

        Iterator<String> iterator = cities.iterator();

        while (iterator.hasNext()) {
            String city = iterator.next();
            System.out.println(iterator.hasNext() + " < = " + city);
        }
        Iterator<String> citiesIterator = cities.iterator();
        while (citiesIterator.hasNext()) {
            if (citiesIterator.next().equals("Mumbai")) {
                citiesIterator.remove(); // ✅ Safe
            }
        }
        System.out.println(cities.size() + " < = list of cities with hashSet: " + cities);

        // LinkedHashSet
        LinkedHashSet<String> citiesLinkedHashSet = new LinkedHashSet<>(cities);
        System.out.println(citiesLinkedHashSet.size() + " < = list of cities with linkedHashSet: " + citiesLinkedHashSet);

        Stream<String> citiesStream = cities.stream();
        // citiesStream.forEach(System.out::println);
        citiesStream
        .filter(city -> city.startsWith("B"))
        .map(String::toUpperCase)
        .forEach(System.out::println);
        // citiesStream.filter(city -> city.startsWith("B")).forEach(System.out::println);
        // citiesStream.map(String::toUpperCase).forEach(System.out::println);

        

        System.out.println("--------------- END ------------------------");
        // try (FileInputStream fis = new FileInputStream("./data.txt")) {
        //     int data;
        //     while ((data = fis.read()) != -1) {
        //         System.out.print((char) data);
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // try (FileWriter writer = new FileWriter("output.txt")) {
        //     writer.write("Hello, Java I/O!");
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        Scanner scanner = new Scanner(System.in);

        // Step 1: Read from console
        System.out.print("Enter your message: ");
        String userInput = scanner.nextLine();

        // Step 2: Write to a file
        try (FileWriter writer = new FileWriter("message.txt")) {
            writer.write(userInput);
            System.out.println("✅ Message saved to message.txt");
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }

        // Step 3: Read from the file
        System.out.println("\nReading from file:");
        try (BufferedReader reader = new BufferedReader(new FileReader("message.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Step 4: Display on console
                System.out.println("📜 " + line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }

        scanner.close();

        
    }
}
    
