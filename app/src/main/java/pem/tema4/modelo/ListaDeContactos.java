package pem.tema4.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaDeContactos {
    private static ListaDeContactos singleton = null;
    private ArrayList<Contacto> listaDeContactos;

    private ListaDeContactos() {
        listaDeContactos = new ArrayList<Contacto>();
        listaDeContactos.add(new Contacto("Mart�n Rodr�guez, Pedr�n", "Pio XII", "1235456","01/01/1969" ));
        listaDeContactos.add(new Contacto("Rodr�guez Rodr�guez, Kevin", "Avda. Mesa y L�pez", "12345456","01/01/1999" ));
        listaDeContactos.add(new Contacto("Garcias Padin, Echedey", "Mi Casa", "660328981","23/12/1995" ));
        listaDeContactos.add(new Contacto("Martel Jordan, Ernestina", "Su Casa", "12345456","01/01/1999" ));

    }

    public static ListaDeContactos getInstance() {
        if (singleton == null)
            singleton = new ListaDeContactos();
        return singleton;
    }

    public ArrayList<Contacto> getListaDeContactos() {
        return listaDeContactos;
    }

    public void setListaDeContatos(ArrayList<Contacto> listaDeContactos) {
        this.listaDeContactos = listaDeContactos;
    }

    public void agregarItem(Contacto nuevo) {
        listaDeContactos.add(nuevo);
        Collections.sort(listaDeContactos, new Comparator<Contacto>() {
            @Override
            public int compare(Contacto contacto1, Contacto contacto2) {
                return  contacto1.getNombre().compareTo(contacto2.getNombre());
            }
        });
    }
}
