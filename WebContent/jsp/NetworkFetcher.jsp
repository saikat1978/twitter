<%@page import="techathon.core.network.CreateD3Network"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.regex.Pattern"%>
<%@ page import="java.lang.Math" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.lang.NumberFormatException" %>
<%@ page import="org.json.*" %>


<%	
	response.setCharacterEncoding("UTF-8");
	response.setContentType("application/x-javascript;charset=UTF-8");
	int nof_classes = Integer.parseInt(request.getParameter("nclasses"));
	String callbackName = request.getParameter("callback");
	String infilename = request.getSession().getServletContext().getRealPath("/")+"/"+request.getParameter("filename");	
	JSONObject tagjson = new JSONObject(request.getParameter("hashtagjson"));
	int num_nodes = 10;
	try{	
		num_nodes = Integer.parseInt(request.getParameter("num_nodes").toString());
	}catch(NumberFormatException ex)
	{
		ex.printStackTrace();
	}
	CreateD3Network crtnode = new CreateD3Network();
	//Get the tweets and create network nodes and links
	JSONObject result = crtnode.ConvertTweetsToDiffusionPath(infilename,nof_classes,tagjson,num_nodes);	
	
	if(callbackName != null){
		out.write(callbackName + "(" + result.toString() + ");");
	}
	else{
		out.write(result.toString());
	}
	
	out.flush();
	//conn.close();
	
%>