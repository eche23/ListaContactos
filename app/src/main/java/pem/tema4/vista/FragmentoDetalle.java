package pem.tema4.vista;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

public class FragmentoDetalle extends Fragment {
	
	private TextView nombre, direccion, telefono, fechaCumple;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_fragmento_detalle,
				container, false); 
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		nombre = (TextView) getView().findViewById(R.id.nombre);
		direccion = (TextView) getView().findViewById(R.id.direccion);
		telefono = (TextView) getView().findViewById(R.id.telefono);
		fechaCumple = (TextView) getView().findViewById(R.id.fechaCumple);

	}
	
	public void actualizarNombre(String nombre)
    {
		this.nombre.setText(nombre);
	}

	public void actualizarDireccion(String direccion) {
		this.direccion.setText(direccion);
	}

	public void actualizarTelefono(String telefono) {
		this.telefono.setText(telefono);
	}

	public void actualizarFechaCumple(String fechaCumple) {
		this.fechaCumple.setText(fechaCumple);
	}

}
