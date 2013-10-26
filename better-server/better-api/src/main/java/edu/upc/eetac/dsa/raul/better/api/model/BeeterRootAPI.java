package edu.upc.eetac.dsa.raul.better.api.model;

import java.util.ArrayList;

import edu.upc.eetac.dsa.raul.better.api.links.Link;

public class BeeterRootAPI {

	private ArrayList<Link> links;

	public BeeterRootAPI() {
		super();
		links = new ArrayList<Link>();
	}

	public void addLink(Link link) {
		links.add(link);
		return;
	}

	public ArrayList<Link> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}

}
