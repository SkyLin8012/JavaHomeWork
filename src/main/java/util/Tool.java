package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Tool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static Object readFile(String Filename)
	{
		Object obj = null;
		File file = new File(Filename);
		try {
			if(!file.exists())
			{	
				file.createNewFile();
				return null;
			}
			if(file.length()>0)
			{
			FileInputStream fis = new FileInputStream(Filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj=ois.readObject();
			ois.close();
			fis.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("【錯誤】找不到檔案，請確認檔案是否有放在 JAR 檔旁邊！");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	public static void saveFile(String fileName,Object object) 
	{
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
