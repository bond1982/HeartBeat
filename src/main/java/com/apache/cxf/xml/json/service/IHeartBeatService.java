package com.apache.cxf.xml.json.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/heartbeat")
public interface IHeartBeatService {
	@GET
	@Path("/")
	Response getHeartBeatStatus();
}
