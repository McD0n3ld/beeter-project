package edu.upc.eetac.dsa.raul.android;

import java.io.IOException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import edu.upc.eetac.dsa.raul.android.beeter.api.BeeterAPI;
import edu.upc.eetac.dsa.raul.android.beeter.api.Sting;
import edu.upc.eetac.dsa.raul.android.beeter.api.StingCollection;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class Beeter extends ListActivity {
	private final static String TAG = Beeter.class.toString();
	//private static final String[] items = { "lorem", "ipsum", "dolor", "sit", "amet", "consectetuer", "adipiscing", "elit", "morbi", "vel", "ligula", "vitae","arcu", "aliquet", "mollis", "etiam", "vel", "erat", "placerat", "ante", "porttitor", "sodales", "pellentesque", "augue", "purus" };

	// Adatador ---> Para unir una lista(View) Datos(Modelo) (adaptador)
	//private ArrayAdapter<String> adapter;
	private ArrayList<Sting> stingList;
	private StingAdapter adapter;
	
	//implementacion del fetchstingstask
	private BeeterAPI api;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");

		String serverAddress = "";
		String serverPort = "";

		AssetManager assetManager = getAssets();
		Properties config = new Properties();
		try {
			config.load(assetManager.open("config.properties"));
			serverAddress = config.getProperty("server.address");
			serverPort = config.getProperty("server.port");

			Log.d(TAG, "Configured server " + serverAddress + ":" + serverPort);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
			finish();
		}

		setContentView(R.layout.beeter_layout);
		
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("alicia", "alicia".toCharArray());
			}
		});

		// instanciamos el contenedor indicando el contexto (actividad), un
		// layout del sistema android que aplica a los items de una lista, en
		// este caso es una linearlayout y una text view, cuyo texto es el
		// elemento que queremos pintar. Por ultimo, le pasamos los datos.
		stingList = new ArrayList<Sting>();
		adapter = new StingAdapter(this, stingList);
		//adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		// Funcion que existe porque heredamos de ListActivity, para decir cual
		// es el adaptador que aplica a la listview
		setListAdapter(adapter);
		
		
		//FetchStingsTask
		api = new BeeterAPI();
		URL url = null;
		try {
			url = new URL("http://" + serverAddress + ":" + serverPort
					+ "/better-api/stings?&offset=0&length=20");
		} catch (MalformedURLException e) {
			Log.d(TAG, e.getMessage(), e);
			finish();
		}
		(new FetchStingsTask()).execute(url);
	}

	// Progresso void (indeterminado), result stingCollection (lo que devuelve)
	private class FetchStingsTask extends AsyncTask<URL, Void, StingCollection> {
		private ProgressDialog pd;
		
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(Beeter.this);
			pd.setTitle("ProgressDialog...");
			pd.setCancelable(false);	//no es cancelable
			pd.setIndeterminate(true);
			pd.show();
		}

		@Override
		protected StingCollection doInBackground(URL... params) {
			StingCollection stings = api.getStings(params[0]);
			return stings;
		}

		@Override
		protected void onPostExecute(StingCollection result) {
			//ArrayList<Sting> stings = new ArrayList<Sting>(result.getStings());
			//for (Sting s : stings) {
			//	Log.d(TAG, s.getStingId() + "-" + s.getSubject());
			//}
			addStings(result);
			if (pd != null) {
				pd.dismiss();
			}
		}
	}
	
	private void addStings(StingCollection stings){
		stingList.addAll(stings.getStings());
		adapter.notifyDataSetChanged();
	}
}
