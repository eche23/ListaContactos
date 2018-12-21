package pem.tema4.presentador;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import pem.tema4.AppMediador;
import pem.tema4.modelo.Contacto;
import pem.tema4.modelo.IModelo;
import pem.tema4.modelo.Modelo;

public class PresentadorPrincipal implements IPresentadorPrincipal {
    private AppMediador appMediador;

	private IModelo modelo;

    public PresentadorPrincipal() {
        appMediador = AppMediador.getInstance();
        modelo = Modelo.getInstance();
    }

    private BroadcastReceiver receptorAvisos = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppMediador.AVISO_DATOS_LISTOS)){
                ArrayList<Contacto> listaContactos = (ArrayList<Contacto>) intent.getSerializableExtra(AppMediador.CLAVE_LISTA_CONTACTOS);
                String[] datos = new String[listaContactos.size()];
                for (int i = 0; i < listaContactos.size(); i++){
                    datos[i] = listaContactos.get(i).getNombre();
                }
                appMediador.getVistaPrincipal().actualizarMaestro(datos);
            } else if (intent.getAction().equals(AppMediador.AVISO_DETALLE_LISTO)){
                String[] datosDetalle = intent.getStringArrayExtra(AppMediador.CLAVE_DETALLE_CONTACTO);
//                Object[] datos = new Object[4];
//                datos[0] = datosDetalle[0];
//                datos[1] = datosDetalle[1];
//                datos[2] = datosDetalle[2];
//                datos[3] = datosDetalle[3];
                appMediador.getVistaPrincipal().actualizarDetalle(datosDetalle);
            }
            appMediador.unRegisterReceiver(this);
        }
    };

    @Override
    public void odtenerDatos() {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_DATOS_LISTOS);
        modelo.obtenerDatos();
    }

    @Override
    public void obtenerDetalle(int posicion) {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_DETALLE_LISTO);
        modelo.obtenerDetalle(posicion);
    }
}
