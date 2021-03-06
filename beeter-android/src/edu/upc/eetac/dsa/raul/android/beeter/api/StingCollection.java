package edu.upc.eetac.dsa.raul.android.beeter.api;

import java.util.ArrayList;
import java.util.List;

import edu.upc.eetac.dsa.raul.android.beeter.api.Link;

public class StingCollection {
	
	//private java.util.List<Sting> stings = new java.util.ArrayList<Sting>();
	private List<Sting> stings = new ArrayList<Sting>();
	private ArrayList<Link> links;
	
	public StingCollection() {
		super();
		links = new ArrayList<Link>();
	}

	public void add(Sting sting) {
		stings.add(sting);
	}

	public List<Sting> getStings() {
		return stings;
	}

	public void setStings(List<Sting> stings) {
		this.stings = stings;
	}

	public ArrayList<Link> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}	
	
	public void addLink(Link link) {
		links.add(link);
		return;
	}
	
}
