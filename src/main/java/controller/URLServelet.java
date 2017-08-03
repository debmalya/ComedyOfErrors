package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.Form;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class URLServelet
 */
public class URLServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This will maintain short code (as key) and actual URL (as value). Should
	 * be some LRU cache.
	 */
	private Map<String, String> urlMapper = new HashMap<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public URLServelet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("INIT called");
		// Here get the urlMapper
		try {
			ClientResource dayActivityClient = new ClientResource("http://localhost:8112/api/v1/url");
			String dayList = dayActivityClient.get().getText();
			dayActivityClient.release();

			JsonParser parser = new JsonParser();
			JsonArray urlListArray = parser.parse(dayList).getAsJsonArray();
			for (int i = 0; i < urlListArray.size(); i++) {
				JsonElement eachRow = urlListArray.get(i);
				if (eachRow.isJsonArray()) {
					JsonArray eachRowArray = eachRow.getAsJsonArray();
					String actualURL = eachRowArray.get(0).toString();
					String shortCode = eachRowArray.get(1).toString();
					urlMapper.put(shortCode, actualURL);
				}
			}
			System.out.println("urlMapper 2:" + urlMapper);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 * 
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// Here get the urlMapper
		System.out.println("INIT called again");
		try {
			ClientResource dayActivityClient = new ClientResource("http://localhost:8112/api/v1/url");
			String dayList = dayActivityClient.get().getText();
			dayActivityClient.release();

			JsonParser parser = new JsonParser();
			JsonArray urlListArray = parser.parse(dayList).getAsJsonArray();
			for (int i = 0; i < urlListArray.size(); i++) {
				JsonElement eachRow = urlListArray.get(i);
				if (eachRow.isJsonArray()) {
					JsonArray eachRowArray = eachRow.getAsJsonArray();
					String actualURL = eachRowArray.get(0).toString();
					String shortCode = eachRowArray.get(1).toString();
					urlMapper.put(shortCode, actualURL);
				}
			}

			System.out.println("urlMapper 3 :" + urlMapper);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * Frontend page to type URL to be shortened and alias.
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestAction = request.getParameter("operation");

		String actualURL = request.getRequestURL().toString();
		if (requestAction == null) {
			// have to redirect the url.
			String requestedURL = request.getRequestURL().toString();

			String shortCode = request.getRequestURL().substring(requestedURL.lastIndexOf("/") + 1);
			System.out.println("lastPart :" + shortCode);

			System.out.println("Mapper before getting : " + urlMapper);
			actualURL = urlMapper.get(shortCode);
			System.out.println("Actual URL :" + actualURL);
			if (actualURL == null) {
				// query api
				System.out.println("Calling api for "+ shortCode);
				ClientResource urlClient = new ClientResource("http://localhost:8112/api/v1/url/" + shortCode);
				Representation result = urlClient.get();
				String resultDesription = result.getText();
				System.out.println("Parsing " + resultDesription);
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(resultDesription);
				actualURL = "/";
				if (element != null && !element.isJsonNull() && element.isJsonArray()) {
					JsonArray actualURLArray = element.getAsJsonArray();
					actualURL = actualURLArray.get(0).getAsString();
					System.out.println("actualURL from API " + actualURL);
					urlMapper.put(shortCode, actualURL);
				}
				urlClient.release();
				
			}

			System.out.println("actualURL :" + actualURL);
			response.sendRedirect(actualURL);
		} else if ("ADD".equals(requestAction)) {
			actualURL = request.getParameter("actualURL");
			System.out.println("Actual URL :" + actualURL);
			String alias = request.getParameter("alias");
			System.out.println("alias :" + alias);

			Client client = new Client(new Context(), Protocol.HTTP);
			client.getContext().getParameters().add("use ForwardedForHeader", "false");

			ClientResource urlClient = new ClientResource("http://localhost:8112/api/v1/url");
			Form form = new Form();
			form.add("actualURL", actualURL);
			form.add("alias", alias);

			Representation result = urlClient.post(form);

			String resultDesription = result.getText();
			int executionCode = urlClient.getStatus().getCode();
			StringBuilder status = new StringBuilder();

			System.out.println(" resultDesription :" + resultDesription);

			if (!resultDesription.contains("ERROR")) {

				// Got short code back
				System.out.println("SUCCESS");
				String shortCode = resultDesription;
				urlMapper.put(shortCode, actualURL);
				status.append("http://localhost:8080/" + resultDesription);

			} else {
				status.append(resultDesription);
				// This should be a hyper link
				System.out.println("ERROR");

			}

			if (executionCode != 200) {
				status.append(" .Error from API server");
			}

			String finalStatus = status.toString();
			System.out.println("Status :" + finalStatus);
			urlClient.release();

			request.setAttribute("status", finalStatus);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} else if ("DELETE".equals(requestAction)) {

		} else if ("UPDATE".equals(requestAction)) {

		}
	}

}
