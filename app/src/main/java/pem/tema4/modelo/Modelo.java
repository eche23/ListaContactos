package pem.tema4.modelo;

import android.os.Bundle;
import android.os.Environment;

import java.util.List;

import pem.tema4.AppMediador;

public class Modelo implements IModelo {

	private static Modelo singleton = null;
	private ListaDeContactos listaDeContactos;
	private AppMediador appMediador;

	private Modelo() {
		listaDeContactos = ListaDeContactos.getInstance();
		appMediador = AppMediador.getInstance();
	}

	public static Modelo getInstance() {
		if (singleton == null)
			singleton = new Modelo();
		return singleton;
	}


	@Override
	public void obtenerDatos() {
		Bundle extras = new Bundle();
		extras.putSerializable(AppMediador.CLAVE_LISTA_CONTACTOS, listaDeContactos.getListaDeContactos());
		appMediador.sendBroadcast(AppMediador.AVISO_DATOS_LISTOS, extras);
	}

	@Override
	public void obtenerDetalle(int posicion) {
		Contacto contacto = listaDeContactos.getListaDeContactos().get(posicion);
		String[] datos = new String[4];
		datos[0] = contacto.getNombre();
		datos[1] = contacto.getDireccion();
		datos[2] = contacto.getTelefono();
		datos[3] = contacto.getFechaCumple();

		Bundle extras = new Bundle();
		extras.putStringArray(AppMediador.CLAVE_DETALLE_CONTACTO, datos);
		appMediador.sendBroadcast(AppMediador.AVISO_DETALLE_LISTO, extras);
	}


}

