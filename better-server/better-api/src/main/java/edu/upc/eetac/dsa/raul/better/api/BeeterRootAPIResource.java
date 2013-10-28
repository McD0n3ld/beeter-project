package edu.upc.eetac.dsa.raul.better.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import edu.upc.eetac.dsa.raul.better.api.links.BeeterAPILinkBuilder;
import edu.upc.eetac.dsa.raul.better.api.model.BeeterRootAPI;

@Path("/")
public class BeeterRootAPIResource {

	@Context
	private UriInfo uriInfo;

	// TODO: Return links

	@GET
	@Produces(MediaType.BEETER_API_LINK_COLLECTION)
	public BeeterRootAPI getLinkResources() {
		BeeterRootAPI bra = new BeeterRootAPI();
		bra.addLink(BeeterAPILinkBuilder.buildURIRootAPI(uriInfo));
		bra.addLink(BeeterAPILinkBuilder.buildTemplatedURIStings(uriInfo,"stings",true)); //rel = que es este enlace
		bra.addLink(BeeterAPILinkBuilder.buildTemplatedURIStings(uriInfo,"stings",false)); //rel = que es este enlace
		bra.addLink(BeeterAPILinkBuilder.buildURIStings(uriInfo, "0", "5", "manolo", "sting"));
		return bra;		
	}
}
