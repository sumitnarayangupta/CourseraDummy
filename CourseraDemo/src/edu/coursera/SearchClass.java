package edu.coursera;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/getCourses")
public class SearchClass 
{
	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET)
	public String getCourses(@RequestParam("query") String req,ModelMap modal) throws Exception
	{
		System.out.println("Hello from Servlet");		
		
		
		//URL for courses partners and instructors
		String courses = "https://api.coursera.org/api/courses.v1";
		String partners = "https://api.coursera.org/api/partners.v1";
		String instructors = "https://api.coursera.org/api/instructors.v1";
		
		
		//encoding Query keyword for search
		req = req.replaceAll("\\s+","+");
		String searchURL =  courses + "?q=search&query=" + req + "&fields=instructorIds,partnerIds";
		
		
		
		//Creating Rest Client using Jeresy framework
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(searchURL);
		String searchResult = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		
		//Parsing JSON request using JACKSON library and storing the course detail in an Arraylist 
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> searchResultInMap = mapper.readValue(searchResult,Map.class);
		ArrayList< Map<String,Object> > elements = (ArrayList<Map<String, Object>>)searchResultInMap.get("elements");
		
		
		//An array of data Required in view for displaying purpose
		ArrayList<CourseDetail> requiredData = new ArrayList<>();
		
		//Adding Course id , course name , instructor id and partners id in Array
		for(int i=0;i<elements.size();i++)
		{
			CourseDetail temp = new CourseDetail();
			temp.setCourseId((String)elements.get(i).get("id"));
			temp.setCourseName((String)elements.get(i).get("name"));
			temp.setInstructorsId((ArrayList<String>)elements.get(i).get("instructorIds"));
			temp.setPartnersId(((ArrayList<String>)elements.get(i).get("partnerIds")));
			requiredData.add(temp);
		}
		
		
		//Accesing instructors detail and partners detail from the ids saved in Array
		for(int i=0;i<requiredData.size();i++)
		{
			ArrayList<String> temp;
			
			//Adding all the corresponding instructors name in the required data list
			
			
			//Preparing URL for instructor Detail
			StringBuilder sb = new StringBuilder(requiredData.get(i).getInstructorsId().get(0));
			for(int j=1;j<requiredData.get(i).getInstructorsId().size();j++)
				sb.append(","+requiredData.get(i).getInstructorsId().get(j));
			String instructorsURL = instructors + "?ids="+new String(sb);
			

			//Accesing instructor Detail using rest Client
			target = client.target(instructorsURL);
			searchResult = target.request(MediaType.APPLICATION_JSON).get(String.class);
			
			//Retriving data from JSON and adding the name of instructor in the Array previously decleared for view purpose
			searchResultInMap = mapper.readValue(searchResult,Map.class);
			elements = (ArrayList<Map<String, Object>>)searchResultInMap.get("elements");
			temp = new ArrayList<>();
			for(int j=0;j<elements.size();j++)
				temp.add((String)elements.get(j).get("fullName"));
			requiredData.get(i).setInstructorsName(temp);
			
			
			
			//Adding all the partner name and partner image URL in the required list
			
			//Preparing URL for accessing partner name and partners logo
			sb = new StringBuilder((String)requiredData.get(i).getPartnersId().get(0));
			for(int j=1;j<requiredData.get(i).getPartnersId().size();j++)
				sb.append("," + requiredData.get(i).getPartnersId().get(j));
			String partnersURL = partners + "?ids=" +new String(sb) +"&fields=logo";
			
			
			//Accesing partner name and partners logo
			target = client.target(partnersURL);
			searchResult = target.request(MediaType.APPLICATION_JSON).get(String.class);
			
			
			//Setting partner name and partners logo in the array fro view purpose 
			searchResultInMap = mapper.readValue(searchResult,Map.class);
			elements = (ArrayList<Map<String, Object>>)searchResultInMap.get("elements");
			
			//Setting partners name
			temp = new ArrayList<>();
			for(int j=0;j<elements.size();j++)
				temp.add((String)elements.get(j).get("name"));
			requiredData.get(i).setPartnersName(temp);
			
			//Setting partners logo URL
			temp = new ArrayList<>();
			for(int j=0;j<elements.size();j++)
				temp.add((String)elements.get(j).get("logo"));
			requiredData.get(i).setPartnersImageUrl(temp);
			
		}
		
		//Adding Array in modal map
		modal.addAttribute("courseInfo", requiredData);
		return "result";
	}
}
