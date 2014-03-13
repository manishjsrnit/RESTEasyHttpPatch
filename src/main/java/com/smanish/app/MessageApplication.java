package com.smanish.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.smanish.rest.services.JSONService;

public class MessageApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public MessageApplication() {
		singletons.add(new JSONService());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
