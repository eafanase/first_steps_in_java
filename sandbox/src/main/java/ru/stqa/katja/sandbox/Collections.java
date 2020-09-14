package ru.stqa.katja.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args) {
//   String [] langs = new String[4];                    //1. в скобках длина массива, ниже все строки, которые будут в нем
//   langs[0] = "Java";
//   langs[1] = "C#";
//   langs[2] = "Python";
//   langs[3] = "PHP";

//    String [] langs = {"Java", "C#", "Python", "PHP" };    // 2. можно описать весь массив сразу, перечислить все его элементы в скаобках
//    for (int i=0; i < langs.length; i++) {                 //2
//      System.out.println("Я хочу выучить " + langs[i]);    //2

// 3. Специально для коллекций есть особая конструкция цикла для перебора элементов коллекции =>>>>
//    for (String l: langs) {                             // 3. l не индекс, а ссылка на элемент массива
//      System.out.println("Я хочу выучить " + l);       //3.

// 4. C использованием списка, строка 15 больше не нужна
//    List<String> languages = new ArrayList<String>();  // 5. чтобы передать все значения в одну строку, нужно исп-ть метод для преобразования массива в список
//    languages.add("Java");                             //5.
//    languages.add("C#");                               //5.
//    languages.add("Python");                           //5.

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
    for (String l : languages) {     //6. итерация по элементам списка при помощи счетчика
      System.out.println("Я хочу выучить " + l); // 6.

//    for (int i=0; i<languages.size(); i++) {  // 7. не удобно использовать переменную счетчик, лучше использовать 6.
//      System.out.println("Я хочу выучить " + languages.get(i));

    }

  }
}
