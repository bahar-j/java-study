package kosta.phone;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Util {

	static final Scanner SCANNER = new Scanner(System.in);
	
	static String objectToJSONString (List<PhoneInfo> data) {
		return net.sf.json.JSONArray.fromObject(data).toString();
	}
	
	static List<PhoneInfo> JSONtoObject (String data){
		List<PhoneInfo> datas = new LinkedList<PhoneInfo>();
		
		JSONParser parser = new JSONParser();
		Object obj = null;
		
		try {
			obj = parser.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(obj instanceof JSONArray) {
			JSONArray array = (JSONArray)obj; // Object -> JSONArray 형변환
			Iterator iter = array.iterator();
			while(iter.hasNext()) {
				JSONObject jo = (JSONObject)iter.next(); // Object -> JSONObject
				String name = (String)jo.get("name");
				String phoneNumber = (String)jo.get("phoneNumber");
				String birth = (String)jo.get("birth"); // long을 리턴함
				datas.add(new PhoneInfo(name, phoneNumber, birth));
			}
		}
		
		return datas;
	}
}
