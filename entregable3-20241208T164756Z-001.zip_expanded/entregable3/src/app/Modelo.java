package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Comparators.CompCantActivo;
import Comparators.CompNMCTActivo;
import Comparators.CompNMCTMoneda;
import Comparators.CompStockCripto;
import Comparators.CompValorMoneda;
import appDAO.ActivoDAO;
import appDAO.FactoryDAO;
import appDAO.MonedaDAO;
import appDAO.TransaccionDAO;
import appModels.Activo;
import appModels.Compra;
import appModels.Cripto;
import appModels.Fiat;
import appModels.Moneda;
import appModels.Swap;
import appModels.Transaccion;

public class Modelo {
	private ActivoDAO activoDAO;
	private MonedaDAO monedaDAO;
	private TransaccionDAO transaccionDAO;
	
	private CompNMCTActivo NMCTActivo;
	private CompCantActivo cantActivo;
	
	private CompStockCripto stockCripto;
	private CompValorMoneda valorMoneda;
	private CompNMCTMoneda NMCTMoneda;
	public static int CRIPTO = 0;
	public static int FIAT = 1;
	public Modelo(){
		activoDAO=FactoryDAO.getActivoDAO();
		monedaDAO = FactoryDAO.getMonedaDAO();
		transaccionDAO = FactoryDAO.getTransaccionDAO();
		
		NMCTActivo = new CompNMCTActivo();
		cantActivo = new CompCantActivo();
		
		stockCripto = new CompStockCripto();
		valorMoneda = new CompValorMoneda();
		NMCTMoneda = new CompNMCTMoneda();
	
		if(monedaDAO.isEmpty()) {
			monedaDAO.guardarMoneda (new Cripto(".\\Logos\\Bitcoin_logo.png" , "BITCOIN","BTC",0, 0, 0));
			monedaDAO.guardarMoneda(new Cripto(".\\Logos\\Ethereum_logo.png" , "ETHEREUM","ETH",0, 0, 0));
			monedaDAO.guardarMoneda(new Cripto(".\\Logos\\Dogecoin_Logo.png" , "DOGECOIN","DOGE",0, 0, 0));
			monedaDAO.guardarMoneda(new Cripto(".\\Logos\\Tether_logo.png" , "TETHER","USDT",0, 0, 0));
			monedaDAO.guardarMoneda(new Cripto(".\\Logos\\Usdc_logo.png" , "USD-COIN","USDC",0, 0, 0));
		}
	}	
	
	public int crearMoneda(Moneda moneda) {
    	if(!monedaDAO.guardarMoneda(moneda)) 
    		return 2; //error guardar moneda
    	return 0; //Guardado con exito.
    }
	
	public List<Moneda> listarMonedasTipo(int tipo){
		if(tipo == CRIPTO)
			return monedaDAO.listarCripto();
		if(tipo == FIAT)
			return monedaDAO.listarFiat();
		return null; 
		
	}
	
	public List<Activo> listarActivosTipo(int tipo){
		if(tipo == FIAT)
			return activoDAO.listarActivosFiat();
		if(tipo == CRIPTO)
			return activoDAO.listarActivosCripto();
		return null;
	}
	
	
 	public List<Moneda> listarMonedas(int opcion){
		List<Moneda> lista = monedaDAO.listarMonedas();	
    	switch (opcion){
      	case 1:
        	Collections.sort(lista, valorMoneda);
          	break;
      	case 2:
      		Collections.sort(lista, NMCTMoneda);
        	break;
      	default:
    	}
    	return lista;
	}
	public Moneda getMoneda(String nomenclatura) {
		return monedaDAO.existeMoneda(nomenclatura);
	}
	public List<Moneda> listarCripto(int opcion){
		List<Moneda> lista = monedaDAO.listarCripto();	
		List<Cripto> listaCripto = new LinkedList<Cripto>();
		for(Moneda monedaAct :lista) {
			listaCripto.add((Cripto) monedaAct);
		}
		switch (opcion){
      	case 1:
        	Collections.sort(listaCripto, stockCripto);
          	break;
      	case 2:
      		Collections.sort(listaCripto, NMCTMoneda);
        	break;
      	default:
    	}
		return lista;
	}
	public boolean generarStocks() {
		List<Moneda> monedas = monedaDAO.listarMonedas();
		if(monedas == null)
			return false;
		for(Moneda moneda: monedas) {
			if(moneda instanceof Cripto) {
				Cripto cripto = (Cripto) moneda;
				cripto.setStock(Math.random()*100);
				if(!monedaDAO.actualizarStock(cripto))
					return  false;
			}
		}
		return true;
	}

	public int generarActivo(Activo activo) {
		Moneda moneda = monedaDAO.existeMoneda(activo.getNomenclatura());
		if(moneda == null) 
			return 2; //la nomenclatura del activo no corresponde a una moneda.
		
		Activo activoAux = activoDAO.existeActivo(moneda);
		
		if(activoAux == null) { // si el activo no existe
			if(activoDAO.crearActivo(activo, moneda)) 
				return 0; // el activo se crea correctamente
			else
				return 3; //error al crear el activo
		} 
		else { //si el activo si existe
			if(activoDAO.actualizarActivo(activo, moneda)) 
				return 1; //se ha actualizado con exito la cantidad del activo
			else
				return 3;// ha fallado la actualizacion del activo
		}
		
	}
	
	public List<Activo> listarActivos(int opcion){
		List<Activo> lista = activoDAO.listarActivos();
		switch (opcion){
      	case 1:
      		Collections.sort(lista, cantActivo);
          	break;
      	case 2:
      		Collections.sort(lista, NMCTActivo);
        	break;
      	default:
    	}
		return lista;
	}
	
	public Activo compraCripto (String fiatOrigenNMCT, String criptoDestinoNMCT, double cantidadFiat) {
		Moneda fiat= monedaDAO.existeMoneda(fiatOrigenNMCT);
		if(fiat == null) {
			return (new Activo("_ERROR_", 1));  // La nomenclatura pasada como origen no existe.
		}
		if(!(fiat instanceof Fiat)) 
			return (new Activo("_ERROR_", 2)); //La nomenclatura de origen no corresponde a un fiat.

		Activo activoFiat= activoDAO.existeActivo(fiat);
		
		if(activoFiat == null)
			return (new Activo("_ERROR_", 3)); //El activo fiat aun no existe.
	
		
		Moneda cripto = monedaDAO.existeMoneda(criptoDestinoNMCT);
		if(cripto == null)
			return (new Activo("_ERROR_", 4)); //La moneda destino pasada no existe.
		if(!(cripto instanceof Cripto)) 	
			return (new Activo("_ERROR_", 5)); //La nomenclatura de destino no corresponde a un cripto		
		
		if(activoFiat.getCantidad() < cantidadFiat) {
			return (new Activo("_ERROR_", 10)); //saldo fiat insuficiente al ingresado
		}
		
		double cantidadCripto= fiat.convertir(cantidadFiat, cripto);
		
		Cripto criptoDestino = (Cripto) cripto;
		if(criptoDestino.getStock() < cantidadCripto)
			return (new Activo("_ERROR_", 11)); //stock insuficiente al requerido
		
		criptoDestino.setStock(criptoDestino.getStock() - cantidadCripto);
		if(!monedaDAO.actualizarStock(criptoDestino))
			return (new Activo("_ERROR_", 6));//error al actualizar stock de la criptomoneda.
		
		activoFiat.setCantidad(activoFiat.getCantidad() - cantidadFiat);
		Activo activoCripto= activoDAO.existeActivo(criptoDestino);
		if(activoCripto == null) {
			activoCripto = new Activo(criptoDestino.getNomenclatura(), cantidadCripto);
			if(!(activoDAO.crearActivo(activoCripto, cripto) && activoDAO.actualizarActivo(activoFiat, fiat)))
				return (new Activo("_ERROR_", 7));
		} 
		  else {
			activoCripto.setCantidad(activoCripto.getCantidad() + cantidadCripto);
			if(!(activoDAO.actualizarActivo(activoCripto, cripto) && activoDAO.actualizarActivo(activoFiat, fiat)))
				return (new Activo("_ERROR_", 7)); //error al actualizar los valores en los activos
		}
		LocalDateTime fecha= LocalDateTime.now();
		
		// Define el formato 
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		// Convierte LocalDateTime a String 
		String fechaString = fecha.format(formato);
		
		Transaccion compra = new Compra(fechaString, fiat.getNomenclatura(), criptoDestino.getNomenclatura(), cantidadFiat, cantidadCripto);
		transaccionDAO.guardarTransaccion(compra);
		Activo activoInfo= new Activo(cripto.getNomenclatura(), cantidadCripto);
		return activoInfo; // funcion finalizo con exito
							// devuelve el modificado para la impresion de resultados del mismo
	}
	
	public int realizarSwap(String criptoOrigenNMCT, double cantidadOrigen, String criptoDestinoNMCT) {
		Moneda monedaOrigen = monedaDAO.existeMoneda(criptoOrigenNMCT);
		if(monedaOrigen == null) {
			return 1;
		}
		if(!(monedaOrigen instanceof Cripto)) 
			return 2; //La nomenclatura de origen no corresponde a un cripto.
		Activo activoOrigen = activoDAO.existeActivo(monedaOrigen);
		if(activoOrigen == null)
			return 3; //fallo a la hora de buscar el activo origen.
		
		
		Moneda monedaDestino = monedaDAO.existeMoneda(criptoDestinoNMCT);
		if(monedaDestino == null)
			return 4; //La moneda destino pasada no existe.
		if(!(monedaDestino instanceof Cripto)) 	
			return 5; //La nomenclatura de destino no corresponde a un cripto		
		
		Activo activoDestino = activoDAO.existeActivo(monedaDestino);
		if(activoDestino == null)
			return 6;//fallo a la hora de buscar el activo destino.
		
		if(activoOrigen.getCantidad() < cantidadOrigen) {
			return 10; //saldo insuficiente.
		}
		
		double cantidadDestino = monedaOrigen.convertir(cantidadOrigen, monedaDestino);
		Cripto criptoDestino = (Cripto) monedaDestino;
		
		if(criptoDestino.getStock() < cantidadDestino)
			return 11; //stock insuficiente.
		criptoDestino.setStock(criptoDestino.getStock()-cantidadDestino);
		if(!monedaDAO.actualizarStock(criptoDestino))
			return 7;//error al actualizar stock.
		
		activoOrigen.setCantidad(activoOrigen.getCantidad()- cantidadOrigen);
		activoDestino.setCantidad(activoDestino.getCantidad() + cantidadDestino);
		
		LocalDateTime fecha= LocalDateTime.now();

    	// Define el formato 
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    	// Convierte LocalDateTime a String 
    	String fechaString = fecha.format(formato);
		Swap swap = new Swap(fechaString, criptoOrigenNMCT, criptoDestinoNMCT, cantidadOrigen, cantidadDestino);
		transaccionDAO.guardarTransaccion(swap);
		if(!(activoDAO.actualizarActivo(activoDestino, monedaDestino) && activoDAO.actualizarActivo(activoOrigen, monedaOrigen)))
			return 8; //error al actualizar los valores en los activos
		return 0;
	}
	
}
