package com.example.deli.exp6;

import android.content.Context;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public void saveContent(Context context, String fileName, String fileContent) {

        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(fileContent.getBytes()); // 要将字符串转成bytes才能写入
            fos.flush();
            fos.close(); // 关闭文件

            Toast.makeText(context, "Save Content Success", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "Save Content Failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public String getContent(Context context, String fileName) {

        String fileContent = null;
        try {
            FileInputStream fis = context.openFileInput(fileName);
            int len = fis.available();
            byte[] readBytes = new byte[len];
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            while (fis.read(readBytes) != -1) {
                arrayOutputStream.write(readBytes, 0, readBytes.length);
            }
            fis.close();
            arrayOutputStream.close();
            fileContent = new String(arrayOutputStream.toByteArray());

            Toast.makeText(context, "Read Content Success", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Read Content Failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "Read Content Failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return fileContent;
    }

    public void deleteFile(Context context, String fileName) {

        context.deleteFile(fileName);
        Toast.makeText(context, "Delete File Success", Toast.LENGTH_SHORT).show();
    }
}
