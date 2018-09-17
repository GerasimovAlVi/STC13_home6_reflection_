package ru.innopolis.homework6;

public class MyClass {
    private int privateIntField;
    private String publicStringField;
    private boolean protectedBooleanField;

    public MyClass() {
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "privateIntField=" + privateIntField +
                ", publicStringField='" + publicStringField + '\'' +
                ", protectedBooleanField=" + protectedBooleanField +
                '}';
    }

    public void setPrivateIntField(int privateIntField) {
        this.privateIntField = privateIntField;
    }

    public void setPublicStringField(String publicStringField) {
        this.publicStringField = publicStringField;
    }

    public void setProtectedBooleanField(boolean protectedBooleanField) {
        this.protectedBooleanField = protectedBooleanField;
    }

    public int getPrivateIntField() {
        return privateIntField;
    }

    public String getPublicStringField() {
        return publicStringField;
    }

    public boolean isProtectedBooleanField() {
        return protectedBooleanField;
    }

    public MyClass(int privateIntField, String publicStringField, boolean protectedBooleanField) {
        this.privateIntField = privateIntField;
        this.publicStringField = publicStringField;
        this.protectedBooleanField = protectedBooleanField;
    }
}
