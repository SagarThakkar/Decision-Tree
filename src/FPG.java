import java.util.*;
import java.io.*;
import java.lang.*;
public class FPG {
	static ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
	static HashMap<String,Integer> freq = new HashMap<String,Integer>();
	TreeNode root = new TreeNode(null);
	static HashMap<String,ArrayList<TreeNode>> elementobject = new HashMap<String,ArrayList<TreeNode>>();
	double minsuppercent = 0;
	static int minsup = 0;
	// Calculating Minsup
	int calculateminsup(double minsuppercent){
		return (int)Math.floor((minsuppercent/100)*data.size());
	}
	
	int calculateminsup(){
		return calculateminsup(minsuppercent);
	}
	
	// Function to count the frequency of the elements
	void calculatefreq(ArrayList<ArrayList<String>> data){
		
		for(ArrayList<String> l1 : data){
			for(String str: l1){
				//System.out.println(str);
				if (!freq.containsKey(str)){
					freq.put(str, 1);
				}else{
					freq.put(str,freq.get(str)+1);
				}
			}
		}
	}
	
	
	//Remove Elements
	void RemoveElements(){
		for(ArrayList<String> lst : data){
			int length = lst.size();
			for(int i = 0 ; i < length ; i++){
				if(freq.get(lst.get(i)) < minsup){
					//System.out.println("Key " + lst.get(i) + " Value " + freq.get(lst.get(i)) + " Msup " + minsup);
					lst.remove(i);
				}
			}
		}
	}
	
	// Print Function for printing Frequency Map
	void printfreqtable(HashMap<String,Integer> freq){
		for (String key : freq.keySet()) {
		    System.out.println(key+" "+freq.get(key));
		}
	}
	
	
	
	//New Sorted Data according to priority
	void sortdata(ArrayList<ArrayList<String>> data){
		for(ArrayList<String> l1 : data){
			int length = l1.size();
			for(int i = 0 ; i < length - 1 ; i++){
				if(freq.get(l1.get(i)) < freq.get(l1.get(i+1))){
					String temp = l1.get(i);
					l1.set(i, l1.get(i+1));
					l1.set(i+1, temp);
				}
				
			}
		}
	}
	// Function to print updated list
	void printlist(ArrayList<ArrayList<String>> data){
		 for (ArrayList<String> l1 : data ) {
			   for (String n: l1) {
			       System.out.print(n + " "); 
			   }

			   System.out.println();
			}
	}
	
	// Creating a function to send individual list to create tree
	void sendlist(){
		for(ArrayList<String> lst : data){
			createtree(lst);
		}
	}
	
	
	// Creating Tree
	void createtree(ArrayList<String> lst){
		TreeNode cur = root;
		TreeNode dummyelement ;
		for(String st : lst){
			dummyelement = checkchild(cur,st);
			if(dummyelement == null){
				TreeNode x =new TreeNode(st);
				
				if(!elementobject.containsKey(st))
				{
					ArrayList<TreeNode> objectlist = new ArrayList<TreeNode>();
					objectlist.add(x);
					elementobject.put(st,objectlist);
				}else{
					ArrayList<TreeNode> templist =new ArrayList<TreeNode>();
					templist = elementobject.get(st);
					templist.add(x);
					elementobject.put(st,templist);
				}
				
				
				//elementobject.put(st, x);
				cur.ChildList.add(x);
				x.ParentList.addAll(cur.ParentList);
				x.ParentList.add(cur);
				cur = x;
				System.out.println("Child added With Value " + x.element);
				System.out.print("Parent of This Child " + x.element + " is ");
				for(int j=0; j<x.ParentList.size(); j++){
					System.out.print(x.ParentList.get(j).element + " ");
				}
				System.out.println();
			}else{
				dummyelement.count = dummyelement.count+1;
				cur = dummyelement;
				System.out.println("Count of current element has increased to " + cur.count);
			}
		}
		
		
	}
	// Finding of the child already exists to increment value
	TreeNode checkchild(TreeNode parent , String child ){
		ArrayList<TreeNode> temp = parent.getchild();
		TreeNode x;
		if (temp!=null){
			for(int i=0; i<temp.size(); i++){
				x = temp.get(i);
				if(x.element.equals(child)){
					return x;
				}
			}
		}
		
		
		return null;
		
	}
	
	
	void printelementobjects(){
		for (String key : elementobject.keySet()) {
		    ArrayList<TreeNode> temp =new ArrayList<TreeNode>();
			temp = elementobject.get(key);
		    System.out.println("Key----> " + key);
		    for( TreeNode alpha : temp){
		    	System.out.println(alpha.ParentList);
		    }
		}
	}
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException{
		System.out.println("Enter Minimum Support condition ");
		 FPG a =new FPG();
		 Scanner infile = new Scanner(new File("/home/sagar/workspace/FPGrowth/src/input1.txt"));
		 while(infile.hasNextLine()){
			 ArrayList<String> list = new ArrayList<String>(); 
			 String line = infile.nextLine();
			 Collections.addAll(list, line.split(","));
			 
			 data.add(list);
		 }
		 
		 infile.close();
		 
		 Scanner sc = new Scanner(System.in);
		 
		 a.minsuppercent = sc.nextInt();
		 
		 minsup = a.calculateminsup();
		 System.out.println("Minsup " + minsup);
		 
		 
		a.printlist(data);
		 a.calculatefreq(data);
		 //a.printfreqtable(freq);
		 a.RemoveElements();
		// a.printlist(data);
		 a.sortdata(data);
		 a.printlist(data);
		 a.sendlist();
		 a.printelementobjects();
		
	}
}
