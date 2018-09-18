package ru.innopolis.homework6;

import java.util.Objects;

public class MyClass {
    private int privateIntField;
    private String publicStringField;
    private boolean protectedBooleanField;

    public MyClass() {
    }

    public MyClass(int privateIntField, String publicStringField, boolean protectedBooleanField) {
        this.privateIntField = privateIntField;
        this.publicStringField = publicStringField;
        this.protectedBooleanField = protectedBooleanField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return privateIntField == myClass.privateIntField &&
                protectedBooleanField == myClass.protectedBooleanField &&
                Objects.equals(publicStringField, myClass.publicStringField);
    }

    @Override
    public int hashCode() {

        return Objects.hash(privateIntField, publicStringField, protectedBooleanField);
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "privateIntField=" + privateIntField +
                ", publicStringField='" + publicStringField + '\'' +
                ", protectedBooleanField=" + protectedBooleanField +
                '}';
    }
}
