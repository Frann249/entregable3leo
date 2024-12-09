package Comparators;

import java.util.Comparator;

import appModels.Activo;

public class CompNMCTActivo implements Comparator<Activo> {

	@Override
	public int compare(Activo o1, Activo o2) {
		return o1.getNomenclatura().compareTo(o2.getNomenclatura());
	}
}
