package com.hackacode.gestionPaqSer.utilities;

import java.text.DecimalFormat;

public class Utilidad {

    public static Double redondear(Double cantidad){
        // Crear un objeto DecimalFormat para redondear a dos cifras decimales
        DecimalFormat df = new DecimalFormat("#.##");

        // Aplicar el formato al número
        String numeroRedondeado = df.format(cantidad);
        return Double.valueOf(numeroRedondeado);
    }

}
