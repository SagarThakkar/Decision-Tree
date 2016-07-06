import java.util.*;
import java.lang.*;
import java.io.*;
public class TreeNode {
	int count;
	String element;
	ArrayList <TreeNode> ParentList = new ArrayList<TreeNode>();
	ArrayList <TreeNode> ChildList =  new ArrayList<TreeNode>();
	
	public TreeNode(String element){
		this.element =element;
		this.count =1;
	}
	public String toString(){
		return element;
	}
	public ArrayList<TreeNode> getchild(){
		if (ChildList!=null){
			return ChildList;
		}
		return null;
	}
}
