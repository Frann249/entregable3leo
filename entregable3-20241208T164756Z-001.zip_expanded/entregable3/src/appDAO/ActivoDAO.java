package appDAO;

import java.util.List;

import appModels.Activo;
import appModels.Moneda;

public interface ActivoDAO {
	public List<Activo> listarActivos();
	public List<Activo> listarActivosFiat();
	public List<Activo> listarActivosCripto();
	public boolean crearActivo(Activo activo, Moneda moneda);
	public boolean actualizarActivo(Activo activo, Moneda moneda);
	public Activo existeActivo(Moneda moneda);
}
