package ru.innopolis.homework6;

public class Main {
    public static void main(String[] args) {

        MyClass myClass = new MyClass(5, "qwert", true);
        ReflectionUtils.serialize(myClass, "D://file.xml");
        MyClass myClass2 = new MyClass();
        String r = "MyClass";
        System.out.println(ReflectionUtils.deserialize("D://file.xml", myClass2));
    }
}
