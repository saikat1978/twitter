package techathon.core.centrality;


import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.graph.DirectedGraph;
import java.io.File;
import techathon.core.graph.RetweetEdge;
import techathon.core.graph.UserNode;
import techathon.core.utils.TweetFileToGraph;

public class BetweennessCentralityExample {
	public static void main(String[] args){
		
		File tweetFile;
		
		if(args.length > 0){
			tweetFile = new File(args[0]);
		}
		else{
			tweetFile = new File("synthetic_retweet_network.json");
		}
		
		DirectedGraph<UserNode, RetweetEdge> retweetGraph = TweetFileToGraph.getRetweetNetwork(tweetFile);
		
		//calculate the betweenness centrality
		BetweennessCentrality<UserNode, RetweetEdge> betweenness = new BetweennessCentrality<UserNode, RetweetEdge>(retweetGraph);
		
		betweenness.evaluate();
		betweenness.printRankings(true, true);
		
	}
}
