
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ковалев Владислав
 */
public class LegaFile {
    
    private static void error(String text){
        JOptionPane.showMessageDialog(null, text, "Ошибка", 0);
    }
    
    private static void success(String text){
        JOptionPane.showMessageDialog(null, text, "Ура, заработало", 1);
    }
    
    public static String readPath(String path){
        path = path.trim();
        if (path.isEmpty()){
            error("Не введён путь");
            return null;
        }else{
            return path;
        }
    }
    
    public static File fileExists(String path){
        path = readPath(path);
        if (path == null) return null;
        File file = new File(path);
        if (file.exists()){
            return file;
        }else{
            error("404 File not Found");
            return null;
        }
    }
    
    public static void createNewFile(String path){
        path = readPath(path);
        if (path == null) return;
        File file = new File(path);
        try {
            file.createNewFile();
            success("Файл успешно создан");
        } catch (IOException ex) {
            error("Ошибка при создании файла");
        }
    }
    
    public static void removeFile(String path){
        File file = fileExists(path);
        if (file == null) return;
        file.delete();
        success("Файл успешно удалён");
    }
    
    public static void renameFile(String path){
        File file = fileExists(path);
        if (file == null) return;
        String new_name = JOptionPane.showInputDialog("Введите новое имя");
        if (new_name == null) return;
        new_name = readPath(new_name);
        if (new_name == null) return;
        File nf = new File(file.getParent() + "\\" + new_name);
        if (file.renameTo(nf)){
            success("Файл успешно переименован");
        }else{
            error("Ошибка при переименовании");
        }
    }
    
    public static String readFile(String path){
        File file = fileExists(path);
        if (file == null) return null;
        try {
            InputStream stream = new FileInputStream(file.getPath());
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            String text = "";
            while (in.ready()) 
                text += (in.readLine()+"\n");
            in.close();
            stream.close();
            return text;
        } catch (Exception ex) {
            error("Ошибка чтения файла");
            return null;
        }
    }
    
    public static void writeFile(String path, String text){
        File file = fileExists(path);
        if (file == null) return;
        try {
            OutputStream stream = new FileOutputStream(file.getPath());
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream));
            out.write(text);
            out.close();
            stream.close();
            success("Успешная запись в файл");
        } catch (Exception ex) {
            error("Ошибка записи файла");
        }
    }
}
