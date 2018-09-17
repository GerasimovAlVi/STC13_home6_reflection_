package ru.innopolis.homework6;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import java.lang.reflect.Field;

public class ReflectionUtils {

    private ReflectionUtils(){

    }

    public static void serialize(Object o, String fileName) {
        Class clazz = o.getClass();
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            byte[] buffer = ("<" + o.getClass().getName() + ">" + "\r\n" +
                    fieldIterator(clazz.getDeclaredFields(), o) +
                    "</"+ o.getClass().getName() + ">").getBytes();
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

    public static Object deserialize(String fileName, Object o){
        XStream xStream = new XStream(new DomDriver());
        try {
            FileInputStream fileInputStream = new FileInputStream("D://file.xml");
            xStream.fromXML(fileInputStream, o);
            return o;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String fieldIterator(Field[] fields, Object o) throws IllegalAccessException {
        String string = "";
        for(Field field:fields){
            field.setAccessible(true);
            string += "\t<" + field.getName() + ">" + field.get(o) + "</" + field.getName() + ">" + "\r\n";
        }
        return string;
    }

    /*public static void serialize(Object o, String fileName) {
        Class clazz = o.getClass();
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
            byte[] buffer = (header + "\r\n" +
                    "<Object class=\"" + o.getClass().getName() + "\">" + "\r\n" +
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
    }*/

    /*public static String deserialize(String fileName){
        String string = "";
        try(FileInputStream fileInputStream = new FileInputStream(fileName)){
            int i = -1;
            while((i = fileInputStream.read()) != -1){
                string += (char) i;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }*/

    /*private static String fieldIterator(Field[] fields, Object o) throws IllegalAccessException {
        String string = "";
        for(Field field:fields){
            field.setAccessible(true);
            string += "\t<field name=\"" + field.getName() +
                    "\" modifier=\"" + field.getModifiers() +
                    "\">" + "\r\n" + "\t\t<" + field.getType().getName() + ">" +
                    field.get(o) + "</" + field.getType().getName() + ">" + "\r\n" +
                    "\t</field>" + "\r\n";
        }
        return string;
    }*/
}
