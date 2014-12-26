package com.sharemark.pf;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

public class EsServer {

	public static void main(String[] args) {
		// on startup
		Node node = NodeBuilder.nodeBuilder().node();
		Client c = node.client();
		
//		index(c);
		get(c);
		// on shutdown
		node.close();
	}
	
	private static void index(Client c) {
		
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("name","12306 leak");
		json.put("collectDate",new Date());
		json.put("url","http://www.wooyun.org/bugs/wooyun-2014-088532");
//		ObjectMapper mapper = new ObjectMapper(); // create once, reuse
//		mapper.writeValueAsString(arg0)
		IndexResponse response = c.prepareIndex("sharemark", "bookmark", "1").setSource(json).execute().actionGet();
		
		System.out.println(response.getIndex());
		System.out.println(response.getType());
		System.out.println(response.getVersion());
		System.out.println(response.getId());
	}
	
	private static void get(Client c) {
		GetResponse response = c.prepareGet("sharemark", "bookmark", "1")
		        .execute()
		        .actionGet();
		
		System.out.println(response.getSourceAsString());
	}
}
