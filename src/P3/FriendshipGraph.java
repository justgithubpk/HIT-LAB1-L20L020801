package P3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FriendshipGraph {
	public List<Person> allpeople;  //storing all users' object
	public List<String> allname;    //storing existing names
	
	public FriendshipGraph() {   //creating method
		allpeople = new ArrayList<Person>();
		allname  =new ArrayList<String>();
	}
	
	public void addVertex(Person newpeople) {  //add new person
		String name1=newpeople.getmyname();
		if(allname.contains(name1)) {    //check for existing names
			System.out.println("This username already exists");
			System.exit(0);
		}
		else {
			allname.add(name1);
			allpeople.add(newpeople);
		}
	}
	
	public void addEdge(Person a, Person b) {  //user a adds user b as friends
		a.addnewfriend(b);
	}
	
	public int getDistance(Person c1, Person c2) {  //acquire the distance of these two users
		if(c1 == c2) {
			return 0;
		}
		Map<Person,Integer> theway = new HashMap<>();
		Queue<Person> myqueue=new LinkedList<>();
		myqueue.offer(c1);
		theway.put(c1,0);
		int distance;
		while(!myqueue.isEmpty()) {  //in case the queue is not empty
			Person top=myqueue.poll();
			distance = theway.get(top);
			List<Person> allfriend = top.gethisfriend();
			for(Person m:allfriend) {
				if(!theway.containsKey(m)) {
					theway.put(m,distance+1);  //mark friends of the user as stored
					myqueue.offer(m);
					if(m==c2) {
						return theway.get(c2);
					}
				}
			}
		}
		return -1;
	}
	
	public static void main(String args[]) {
		System.out.println("Results:");
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println("Distance between (Rachel, Ross) is "+graph.getDistance(rachel, ross));
		// result should be 1
		System.out.println("Distance between (Rachel, Ben) is "+graph.getDistance(rachel, ben));
		// result should be 2
		System.out.println("Distance between (Rachel, Rachel) is "+graph.getDistance(rachel, rachel));
		// result should be 0
		System.out.println("Distance between (Rachel, Kramer) is "+graph.getDistance(rachel, kramer));
		// result should be -1
		
		System.out.println("\nAfter changing from Ross to Rachael, the result should be:");
		ross = new Person("Rachel");
	}
}
