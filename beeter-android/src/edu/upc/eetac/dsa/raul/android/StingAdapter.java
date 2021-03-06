package edu.upc.eetac.dsa.raul.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.upc.eetac.dsa.raul.android.beeter.api.Sting;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StingAdapter extends BaseAdapter {

	private ArrayList<Sting> data;
	private LayoutInflater inflater;
	
	public StingAdapter(Context context, ArrayList<Sting> data) {
		super();
		inflater = LayoutInflater.from(context);
		this.data = data;
	}
	
	private static class ViewHolder {	//elementos del View del elemento	
		TextView tvSubject;
		TextView tvUsername;
		TextView tvDate;
	}
	
		 //Metodos explicados en el PDF
	@Override
	public int getCount() {
		return data.size();
	}
	 
	@Override
	public Object getItem(int position) {
		return data.get(position);
	}
	 
	@Override
	public long getItemId(int position) {
		return Long.parseLong(((Sting)getItem(position)).getStingId());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) { //cuando no hay nada que reciclar
			convertView = inflater.inflate(R.layout.list_row_sting, null); //inflamos
			viewHolder = new ViewHolder();
			viewHolder.tvSubject = (TextView) convertView.findViewById(R.id.tvSubject);
			viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
			viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
			convertView.setTag(viewHolder);	//le pone el tag a nivel de vista
		} else {
			viewHolder = (ViewHolder) convertView.getTag();	//recupera de la vista qe toca
		}
		String subject = data.get(position).getSubject();	//le coloca los datos que se visualizan
		String username = data.get(position).getUsername();
		String date = SimpleDateFormat.getInstance().format(data.get(position).getCreationTimestamp());
		viewHolder.tvSubject.setText(subject); //representar los datos
		viewHolder.tvUsername.setText(username);
		viewHolder.tvDate.setText(date);
		return convertView;
	}
	
}
