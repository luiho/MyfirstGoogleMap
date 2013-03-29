package com.example.myfirstgooglemap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class MyInfoWindow implements InfoWindowAdapter{
	
	public View root;
	public ImageView image;
	public TextView name;
	public LayoutInflater inflater;
	private Context myContext;
	
	public MyInfoWindow(Context context) {
		myContext = context;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		root = inflater.inflate(R.layout.window, null);
		image = (ImageView)root.findViewById(R.id.imageView1);
		name = (TextView) root.findViewById(R.id.textView1);
	}

	@Override
	public View getInfoContents(Marker arg0) {
		// TODO Auto-generated method stub
		
		name.setText("oh my god");
		
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Toast.makeText(myContext, "my info window", Toast.LENGTH_LONG).show();
			}
		});
		return root;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		// TODO Auto-generated method stub
		return root;
	}

}
