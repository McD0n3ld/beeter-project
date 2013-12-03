package edu.upc.eetac.dsa.raul.android.beeter.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class BeeterAPI {
	private final static String TAG = BeeterAPI.class.toString();
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public StingCollection getStings(URL url) {	//URL direccion del recurso --> http://s:p/beeter-api/sings?o=x&&l=Y
		StingCollection stings = new StingCollection();

		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();	//conexion http movil y el server REST

			urlConnection.setRequestProperty("Accept", MediaType.BEETER_API_STING_COLLECTION);
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true); //Puedes leer de la InputString de la conexion que se establezca entre los dos.
			urlConnection.connect(); //se hace la conexion

			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); //lee la respuesta del servidor
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			//sb.toString() es la respuesta JSON
			JSONObject jsonObject = new JSONObject(sb.toString()); //org.json
			JSONArray jsonLinks = jsonObject.getJSONArray("links"); //org.json
			parseLinks(jsonLinks, stings.getLinks()); //pone los elementos del array links en el arraylist links definido en sting collection

			JSONArray jsonStings = jsonObject.getJSONArray("stings");
			for (int i = 0; i < jsonStings.length(); i++) {
				JSONObject jsonSting = jsonStings.getJSONObject(i);
				Sting sting = parseSting(jsonSting); //convertir cada elemento del array en un objeto sting

				stings.add(sting);
			}
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}

		return stings;
	}

	private void parseLinks(JSONArray source, List<Link> links) throws JSONException {
		for (int i = 0; i < source.length(); i++) {
			JSONObject jsonLink = source.getJSONObject(i);
			Link link = new Link();
			link.setRel(jsonLink.getString("rel"));
			link.setTitle(jsonLink.getString("title"));
			link.setType(jsonLink.getString("type"));
			link.setUri(jsonLink.getString("uri"));
			links.add(link);
		}
	}

	private Sting parseSting(JSONObject source) throws JSONException, ParseException {
		Sting sting = new Sting();
		sting.setAuthor(source.getString("author"));
		if (source.has("content"))
			sting.setContent(source.getString("content"));
		String tsLastModified = source.getString("creationTimestamp").replace("T", " ");
		sting.setCreationTimestamp(sdf.parse(tsLastModified));
		sting.setStingId(source.getString("stingId"));
		sting.setSubject(source.getString("subject"));
		sting.setUsername(source.getString("username"));

		JSONArray jsonStingLinks = source.getJSONArray("links");
		parseLinks(jsonStingLinks, sting.getLinks());
		return sting;
	}
}
