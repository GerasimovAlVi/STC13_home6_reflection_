package ru.innopolis.homework6;

public class Main {
    public static void main(String[] args) {

        MyClass myClass = new MyClass(555, "qwert", true);
        System.out.println(myClass);
        ReflectionUtils.serialize(myClass, "D://file.xml");
        MyClass myClass2 = (MyClass) ReflectionUtils.deserialize("D://file.xml");
        System.out.println(myClass2);
        System.out.println("Equals: " + myClass.equals(myClass2));
    }
}
