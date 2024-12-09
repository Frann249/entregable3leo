package appDAO;
import java.util.List;

import appModels.Cripto;
import appModels.Moneda;

public interface MonedaDAO {
	public boolean guardarMoneda(Moneda moneda);
	public Moneda existeMoneda(String nomenclatura);
	public Moneda existeMoneda(int id);
	public List<Moneda> listarMonedas();
	public List<Moneda> listarCripto();
	public List<Moneda> listarFiat();
    public boolean actualizarStock(Cripto cripto);
    public boolean isEmpty();
}