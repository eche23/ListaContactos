package pem.tema4.vista;

import pem.tema4.AppMediador;
import pem.tema4.presentador.IPresentadorPrincipal;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class VistaPrincipal extends AppCompatActivity implements IVistaPrincipal,
		FragmentoMaestro.EscuchaFragmento {

	private AppMediador appMediador;
	private IPresentadorPrincipal presentadorPrincipal;
	private FragmentoMaestro fragmentoMaestro;
	private FragmentoDetalle fragmentoDetalle;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_principal);
		appMediador = (AppMediador) this.getApplication();
		presentadorPrincipal = appMediador.getPresentadorPrincipal();
		appMediador.setVistaPrincipal(this);
		// Se comprueba si la actividad est� usando una versi�n de layout con un contenedor de fragmentos
		// de tipo FrameLayout (si es as�, es un smartphone y no permite m�s de un fragmento en pantalla),
		// por tanto, s�lo se a�ade el primero
		if (findViewById(R.id.contenedorDeFragmentos) != null) {
			// se crea el fragmento maestro y se a�ade al contenedor de fragmentos
			fragmentoMaestro = new FragmentoMaestro();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.contenedorDeFragmentos, fragmentoMaestro)
					.commit();
		} else {
			// Si el layout no es de panel �nico (es una tableta) se permiten m�s de un fragmento
			// por tanto, se crean los dos fragmentos y se a�aden a sus layouts seg�n el xml sw600dp
			fragmentoMaestro = new FragmentoMaestro();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.contenedor_lista, fragmentoMaestro)
					.commit();

			fragmentoDetalle = new FragmentoDetalle();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.contenedor_detalle, fragmentoDetalle)
					.commit();
		}

	}


	@Override
	protected void onStart() {
		super.onStart();
		presentadorPrincipal.odtenerDatos();

	}

	@Override
	public void onBackPressed() {
		if (findViewById(R.id.contenedorDeFragmentos) != null) {
			//es panel �nico
			FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
			transaccion.replace(R.id.contenedorDeFragmentos, fragmentoMaestro);
			transaccion.addToBackStack(null);
			transaccion.commit();
			presentadorPrincipal.odtenerDatos();


		} else {
			//no es panel �nico
			finish();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		appMediador.removePresentadorPrincipal();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.vista_principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_salir:
				int id = android.os.Process.myPid();
				android.os.Process.killProcess(id);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void alSeleccionarItem(int posicion) {
		// Si no hay fragmento detalle, se crea la vista detalle (esto ocurre si es panel �nico)
		if (fragmentoDetalle == null)
			fragmentoDetalle = new FragmentoDetalle();

		if (findViewById(R.id.contenedorDeFragmentos) != null) {
			// si es de panel �nico, se reemplaza, en el contenedor de fragmentos
			// el fragmento que est� visible por el de la vista detalle
			FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
			transaccion.replace(R.id.contenedorDeFragmentos, fragmentoDetalle);
			transaccion.addToBackStack(null);
			transaccion.commit();

			// realiza la transacci�n
			getSupportFragmentManager().executePendingTransactions();
		}
		presentadorPrincipal.obtenerDetalle(posicion);

	}

	@Override
	public void actualizarMaestro(Object[] datos) {
		fragmentoMaestro.crearLista((String[]) datos);
	}

	@Override
	public void actualizarDetalle(Object[] datos) {
		fragmentoDetalle.actualizarNombre((String) datos[0]);
		fragmentoDetalle.actualizarDireccion((String) datos[1]);
		fragmentoDetalle.actualizarTelefono((String) datos[2]);
		fragmentoDetalle.actualizarFechaCumple((String) datos[3]);
	}
}
