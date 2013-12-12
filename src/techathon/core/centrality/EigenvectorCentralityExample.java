package techathon.core.centrality;


import edu.uci.ics.jung.algorithms.scoring.EigenvectorCentrality;
import edu.uci.ics.jung.graph.DirectedGraph;
import java.io.File;
import techathon.core.graph.RetweetEdge;
import techathon.core.graph.UserNode;
import techathon.core.utils.TweetFileToGraph;

public class EigenvectorCentralityExample {
	public static void main(String[] args){
		
		File tweetFile;
		
		if(args.length > 0){
			tweetFile = new File(args[0]);
		}
		else{
			tweetFile = new File("synthetic_retweet_network.json");
		}
		
		DirectedGraph<UserNode, RetweetEdge> retweetGraph = TweetFileToGraph.getRetweetNetwork(tweetFile);
		
//		EigenVectorScorer scorer = new EigenVectorScorer(retweetGraph);
//		for(UserNode node : retweetGraph.getVertices()){
//			System.out.println(node + " - " + scorer.getVertexScore(node));
//		}
		
		EigenvectorCentrality<UserNode, RetweetEdge> eig = new EigenvectorCentrality<UserNode, RetweetEdge>(retweetGraph);
		eig.evaluate();
		
		for(UserNode node : retweetGraph.getVertices()){
			System.out.println(node + " - " + eig.getVertexScore(node));
		}
	}
}
