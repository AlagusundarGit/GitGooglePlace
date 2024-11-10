package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleReqBody;
import pojo.Location;

public class TestDataBuild {

	public GoogleReqBody addPlacePayload() {
		GoogleReqBody g=new GoogleReqBody();
		g.setAccuracy(50);
		g.setName("alagu");
		g.setPhone_number("(+91) 878 968 8766");
		g.setAddress("29, side layout, cohen 09");
		g.setWebsite("http://google.com");
		g.setLanguage("French-AU");
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		g.setTypes(myList);
		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		g.setLocation(l);
		return g;
	}
	
	public GoogleReqBody addPlacePayloadOutline(String name,String language,String address) {
		GoogleReqBody g=new GoogleReqBody();
		g.setAccuracy(50);
		g.setName(name);
		g.setPhone_number("(+91) 878 968 8766");
		g.setAddress(address);
		g.setWebsite("http://google.com");
		g.setLanguage(language);
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		g.setTypes(myList);
		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		g.setLocation(l);
		return g;
	}
	
	public String deletePlacePayload(String placeid) {
		return "{\r\n    \"place_id\": \""+placeid+"\"\r\n}";
	}
}
