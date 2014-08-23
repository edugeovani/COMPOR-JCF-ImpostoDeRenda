package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.ExceptionList;

import br.ufcg.ppgcc.compor.ir.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.FontePagadora;
import br.ufcg.ppgcc.compor.ir.Titular;

public class ImpostoDeRenda implements FachadaExperimento {

	private String situaCpf;
	private Map<Titular, List<FontePagadora>> mapaFontes = new HashMap<Titular, List<FontePagadora>>();
	private List<Titular> titulares = new ArrayList<Titular>();
	private List<FontePagadora> fontes = new ArrayList<FontePagadora>();
	
	public void criarNovoTitular(Titular titular) {
		if(titular.getNome() == null){		
			throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");	
			
		}
		
		if(titular.getCpf() == null){		
			throw new ExcecaoImpostoDeRenda("O campo CPF é obrigatório");
			
		}
		
		if(titular.getCpf().length() != 14){		
			throw new ExcecaoImpostoDeRenda("O campo CPF está inválido");			
		}
		
			titulares.add(titular);
					

	}

	public List<Titular> listarTitulares() {
		return titulares;
	}


	public List<FontePagadora> listarFontes(Titular titular) {
		// TODO Auto-generated method stub
		return mapaFontes.get(titular);
	}


	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		fontes.add(fonte);
	mapaFontes.put(titular, fontes);
		
	}

	

}
