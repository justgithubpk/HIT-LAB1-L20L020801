package P3;

import java.util.List;
import java.util.ArrayList;

public class Person {
	private String myname;  //name of the user
	private List<Person> friendofmyname;   //friends to this user
	private static ArrayList<String> hisallperson = new ArrayList<String>(); //defining global variable to store the user's friends' names

	public Person(String name) {     //creating method
		if(hisallperson.contains(name)) {       //if name already exists
			System.out.println("Username already exists");
			System.exit(0);
		}
		else {
			this.myname = name;
			friendofmyname = new ArrayList<>();
			hisallperson.add(name);
		}
	}
	
	public void addnewfriend(Person one) {  //add new friend
		this.friendofmyname.add(one);
	}
	
	public String getmyname() {   //obtain user's name
		return this.myname;
	}
	
	public List<Person> gethisfriend(){  //�õ����˵������б�
		return this.friendofmyname;
	}
}
