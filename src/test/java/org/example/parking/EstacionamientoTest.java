package org.example.parking;

import org.junit.Test;
import static org.junit.Assert.*;

public class EstacionamientoTest {

    @Test
    public void testRetirarVehiculo() throws Exception {
        Estacionamiento estacionamiento = new Estacionamiento();
        Vehiculo vehiculo = new Vehiculo("FER123", "peugeot 307", Vehiculo.Tipo.AUTO);
        boolean ingresado = estacionamiento.ingresarVehiculo("31053983", "fer", vehiculo);
        assertTrue(ingresado);

        Ticket ticket = estacionamiento.retirarVehiculo("FER123");
        assertNotNull(ticket);
        assertEquals("FER123", ticket.getVehiculo().getPatente());
        assertNotNull(ticket.getHoraSalida());
    }

    @Test
    public void testCalcularPrecio() {
        Cliente cliente = new Cliente("31053983", "fer");
        Vehiculo vehiculo = new Vehiculo("FER123", "FIAT 600", Vehiculo.Tipo.PICKUP);
        Ticket ticket = new Ticket(cliente, vehiculo);

        // Simular salida con 80 minutos después de la entrada
        ticket.setHoraSalida(ticket.getHoraEntrada().plusMinutes(80));

        long minutos = ticket.calcularMinutos();
        assertEquals(80, minutos);

        double precio = ticket.calcularPrecio();
        assertEquals(180 * 2, precio, 0.01); // 2 horas redondeado
    }
}

