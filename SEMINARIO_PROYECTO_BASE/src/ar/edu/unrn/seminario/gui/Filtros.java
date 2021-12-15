package ar.edu.unrn.seminario.gui;

import java.util.ArrayList;
import java.util.List;

public class Filtros {
	public static <T> List<T> filter(List<T> list, Predicate<T> p){
		List<T> result = new ArrayList<>();
		for(T e : list) {
			if(p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}
}
