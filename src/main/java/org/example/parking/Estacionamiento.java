package org.example.parking;

import java.util.*;

public class Estacionamiento {
    private final int capacidadMaxima = 50;
    private final Map<String, Ticket> vehiculosEstacionados = new HashMap<>();
    private final Map<String, Cliente> clientesRegistrados = new HashMap<>();

    public boolean ingresarVehiculo(String dni, String nombre, Vehiculo vehiculo) {
        // TODO implementar la logica para registrar el ingreso de un nuevo vehiculo en el parking
       if(vehiculosEstacionados.size() >= capacidadMaxima) {
           return false;
       }// se lleno
        // la capacidad maxima del estacionamiento es de 50 vehiculos, si supera esta cap[acidad retornar FALSE
        // validar que no exista otro vehiculo con la misma patente, es un caso de error, retornar FALSE
        // validar si existe el cliente registrado, agregar el nuevo vehiculo en la lista del cliente existente, caso contrario crear un nuevo registro
        // si el proceso es exitoso retornar TRUE
        Cliente cliente = clientesRegistrados.get(dni);
        if (cliente == null) {
            cliente = new Cliente(dni, nombre);
            clientesRegistrados.put(dni, cliente);
        }
        // Verificar que el cliente no tenga un vehículo con esa patente
        if (cliente.buscarVehiculoPorPatente(vehiculo.getPatente()) != null) {
            return false; // Ya tiene registrado un vehículo con esa patente
        }

        cliente.agregarVehiculo(vehiculo);
        Ticket ticket = new Ticket(cliente, vehiculo);
        vehiculosEstacionados.put(vehiculo.getPatente(), ticket);
        return true;

    }

    public Ticket retirarVehiculo(String patente) throws Exception {
        // TODO implementar la lógica para retirar un vehiculo del parking
        Ticket tiket = vehiculosEstacionados.get(patente);
        if (tiket == null) {
            throw new Exception("NO SE ENCONTRO VEHICULO " );
        }
        // validar que exista la patente, caso contrario arrojar la exception "Vehiculo no encontrado"
        // calcular y retornar el ticket del vehiculoEstacionado (ver Ticket.marcarSalida())
        tiket.marcarSalida();
        vehiculosEstacionados.remove(patente);
        return tiket;
    }

    public List<Ticket> listarVehiculosEstacionados() {
        return new ArrayList<>(vehiculosEstacionados.values());
    }
}
