package ru.innopolis.homework6;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class ReflectionUtils {

    private ReflectionUtils(){

    }

    public static void serialize(Object o, String fileName) {
        Class clazz = o.getClass();
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            byte[] buffer = ("<Object class=\"" + o.getClass().getName() + "\">" + "\r\n" +
                    fieldIterator(clazz.getDeclaredFields(), o) +
                    "</Object>").getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserialize(String fileName){
        String string = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(fileName);
            int i = -1;
            while((i = fileInputStream.read()) != -1){
                string += (char) i;
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String i : string.split("\r\n")) {
            arrayList.add(i);
        }

        Object o = null;

        try {
            String className = arrayList.get(0).substring(arrayList.get(0).indexOf("\"") + 1, arrayList.get(0).indexOf(">") - 1);
            Class clazz = Class.forName(className);
            o = clazz.newInstance();
            for (int i = 1; i < arrayList.size()-1; i++) {
                Field field = clazz.getDeclaredField(arrayList.get(i).substring(arrayList.get(i).indexOf("\"") + 1, arrayList.get(i).indexOf("type") - 2));
                String value = arrayList.get(i).substring(arrayList.get(i).indexOf(">") + 1, arrayList.get(i).lastIndexOf("<"));
                field.setAccessible(true);
                switch (arrayList.get(i).substring(arrayList.get(i).indexOf("type") + 6, arrayList.get(i).indexOf(">") - 1)) {
                    case "boolean": {
                        field.setBoolean(o, Boolean.valueOf(value));
                        break;
                    }
                    case "char": {
                        field.setChar(o, value.charAt(0));
                        break;
                    }
                    case "byte": {
                        field.setByte(o, Byte.valueOf(value));
                        break;
                    }
                    case "short": {
                        field.setShort(o, Short.valueOf(value));
                        break;
                    }
                    case "int": {
                        field.setInt(o, Integer.valueOf(value));
                        break;
                    }
                    case "long": {
                        field.setLong(o, Long.valueOf(value));
                        break;
                    }
                    case "float": {
                        field.setFloat(o, Float.valueOf(value));
                        break;
                    }
                    case "double": {
                        field.setDouble(o, Double.valueOf(value));
                        break;
                    }
                    case "java.lang.String": {
                        field.set(o, value);
                        break;
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return o;
    }

    private static String fieldIterator(Field[] fields, Object o) throws IllegalAccessException {
        String string = "";
        for(Field field:fields){
            field.setAccessible(true);
            string += "<field name=\"" + field.getName() +
                    "\" type=\"" + field.getType().getName() +
                    "\">" + field.get(o) +"</field>" + "\r\n";
        }
        return string;
    }
}
