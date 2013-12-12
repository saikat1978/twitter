package techathon.core.utils;

import java.io.File;


import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import techathon.core.graph.RetweetEdge;
import techathon.core.graph.UserNode;

/**
 *	Some basic functionality to convert files collected 
 *	in Chapter 2 to JUNG graphs.
 */
public class TweetFileToGraph {
	
	public static DirectedGraph<UserNode, RetweetEdge> getRetweetNetwork(File tweetFile){
		
		JSONObject tmp;
		
		TweetFileProcessor tfp = new TweetFileProcessor(tweetFile);
		DirectedSparseGraph<UserNode, RetweetEdge> dsg = new DirectedSparseGraph<UserNode, RetweetEdge>();
		
		while (tfp.hasNext()){
			tmp = tfp.next();
                        if(tmp==null)
                        {
                            continue;
                        }
			//get the author
			String user=null;
                        try {
                            user = tmp.getJSONObject("user").getString("screen_name");
                        } catch (JSONException ex) {
                            Logger.getLogger(TweetFileToGraph.class.getName()).log(Level.SEVERE, null, ex);
                        }
			if(user==null)
                        {
                            continue;
                        }
			//get the retweeted user
			try{
				JSONObject retweet = tmp.getJSONObject("retweeted_status");
				String retweeted_user = retweet.getJSONObject("user").getString("screen_name");
				
				//make an edge or increment the weight if it exists.
				UserNode toUser = new UserNode(retweeted_user);
				UserNode fromUser = new UserNode(user);
				
				dsg.addVertex(toUser);
				dsg.addVertex(fromUser);
			
				RetweetEdge edge = new RetweetEdge(toUser, fromUser); 
				
				if(dsg.containsEdge(edge)){
					dsg.findEdge(fromUser, toUser).incrementRTCount();
				}
				else{
					dsg.addEdge(edge, fromUser, toUser);
				}
				dsg.addEdge(edge, fromUser, toUser, EdgeType.DIRECTED);
			}
			catch(JSONException ex){
				//the tweet is not a retweet. this is not a problem.
			}
			
			
		}
		
		return dsg;
	}
}
