package kosta.cafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Util {

	public static String toJSON(List<Customer> customers) {
		return net.sf.json.JSONArray.fromObject(customers).toString();
	}

	public static List<Customer> toList(String json) {
		List<Customer> customers = new ArrayList<Customer>();

		JSONParser parser = new JSONParser();
		Object object = null;

		try {
			object = parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (object instanceof JSONArray) {
			JSONArray arr = (JSONArray)object;
			Iterator iterator = arr.iterator();
			while(iterator.hasNext()) {
				JSONObject jo = (JSONObject)iterator.next();
				String name = (String)jo.get("name");
				String number = (String)jo.get("number");
				String id = (String)jo.get("id");
				String password = (String)jo.get("pw");
				String nickName = (String)jo.get("nickName");
				int coupon = ((Long)jo.get("coupon")).intValue();
				JSONObject obj = (JSONObject)jo.get("liked");
				if(obj != null) {
					String coffeeName = (String)obj.get("coffeeName");
					int price = ((Long)obj.get("price")).intValue();
					customers.add(new Customer(name, number, id, password, nickName, coupon, new Coffee(coffeeName, price)));
				}
				else {
					customers.add(new Customer(name, number, id, password, nickName, coupon, null));
				}
			}
		}

		return customers;
	}

	public static void saveAsObject(List<Customer> customers) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("customerInfo.ser"));
			oos.writeObject(customers);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Customer> loadObject() {
		List<Customer> loadedData = new ArrayList<Customer>();
		ObjectInputStream ols = null;
		File file = new File("customerInfo.ser");
		if(file.exists()) {
			try {
				ols = new ObjectInputStream(new FileInputStream("customerInfo.ser"));
				loadedData = (ArrayList<Customer>)ols.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ols.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return loadedData;
	}

}