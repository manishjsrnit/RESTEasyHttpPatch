package com.smanish.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.smanish.rest.annotations.PATCH;
import com.smanish.rest.managers.JSONPatchManagerImpl;
import com.smanish.rest.model.Track;

/**
 * @author manishs
 * This is Rest service class. It provides Http PATCH method support alogn with GET and POST.
 * The annotated method with Patch verb will be called for Http Patch requests.
 *
 */
@Path("/json")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONService {

	/**
	 * @param msg
	 * @return Response
	 */
	@GET
	@Path("/get/{param}")
	public Response printMessage(@PathParam("param") String msg) {
		String result = "Restful example : " + msg;
		return Response.status(200).entity(result).build();
	}
	
	/**
	 * @param track
	 * @return Response
	 * @throws JSONException
	 */
	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createProductInJSON(String track) throws JSONException {
		JSONObject json = new JSONObject(track);
		String result = "Patch received : " + json;
		return Response.status(201).entity(result).build();
	}
	
	/**
	 * Patch verb annotated method. It gets called for Http Patch requests. It triggers the applying of patch to
	 * current state of a system object.
	 * @param patchObject
	 * @return Response
	 * @throws JSONException
	 */
	@PATCH
	@Path("/applyPatch")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateJSON(String patchObject) throws JSONException {
		JSONObject json = new JSONObject(patchObject);
		JSONPatchManagerImpl patchManager = new JSONPatchManagerImpl();
		System.out.println("Inside Patch. Object received : "+patchObject);
		Track track = new Track();
		track.setTitle("glee");
		track.setSinger("David gilmor");
		System.out.println("Track before Update : "+track);
		Track updatedTrack = patchManager.applyPatch(track, json);
		String result = "Track after update : " + updatedTrack;
		return Response.status(201).entity(result).build();
	}

}