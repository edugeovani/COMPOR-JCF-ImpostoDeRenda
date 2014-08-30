package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.ExceptionList;

import br.ufcg.ppgcc.compor.ir.Dependente;
import br.ufcg.ppgcc.compor.ir.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.FontePagadora;
import br.ufcg.ppgcc.compor.ir.Titular;

public class ImpostoDeRenda implements FachadaExperimento {

	private String situaCpf;
	private Map<Titular, List<FontePagadora>> mapaFontes = new HashMap<Titular, List<FontePagadora>>();
	private Map<Titular, List<Dependente>> mapaDepen = new HashMap<Titular, List<Dependente>>();
	//private Map<List<Titular>, List<FontePagadora>> mapaFontes = new HashMap<List<Titular>, List<FontePagadora>>();
	private List<Titular> titulares = new ArrayList<Titular>();
	private List<Dependente> dependentes = new ArrayList<Dependente>();
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
		    fontes = new ArrayList<FontePagadora>();
			titulares.add(titular);
			mapaFontes.put(titular, fontes);
					

	}

	public List<Titular> listarTitulares() {
		return titulares;
	}


	public List<FontePagadora> listarFontes(Titular titular) {
		// TODO Auto-generated method stub
		return mapaFontes.get(titular);
	}


	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		
		if(fonte.getNome() == null){
			throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
		}
		if(fonte.getCpfCnpj() == null){
			throw new ExcecaoImpostoDeRenda("O campo CPF/CNPJ é obrigatório");
		}
		if(fonte.getRendimentoRecebidos() == 0){
			throw new ExcecaoImpostoDeRenda("O campo rendimentos recebidos é obrigatório");
		}
		if(fonte.getRendimentoRecebidos() < 0){
			throw new ExcecaoImpostoDeRenda("O campo rendimentos recebidos deve ser maior que zero");
		}
		/* Primeira coisa, eu preciso pegar a fonte do titular, se já existir */
		List<FontePagadora> fontesDoTitular = mapaFontes.get(titular);
		
		if((fonte.getCpfCnpj() != null) && (fonte.getCpfCnpj().length()!= 18)  ){
			throw new ExcecaoImpostoDeRenda("O campo CPF/CNPJ é inválido");
		}
		/* Verifico se existe, ou se é null */
		if (fontesDoTitular == null) {
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
			/* Se não existe, cria nova lista */
			//fontesDoTitular = new ArrayList<FontePagadora>();
		}
		/* Adiciona a nova fonte na nova lista, ou na lista que já existia */
		
		fontesDoTitular.add(fonte);
		//fontes.add(fonte);
		
		
		
			/* Devolvo ao mapa */
		//mapaFontes.put(titular, fontes);
		
		
		
//		fontes.add(fonte);
		
	}

	public void criarDependente(Titular titular, Dependente dependente) {
		dependentes = new ArrayList<Dependente>();
		dependentes.add(dependente);
		List<Dependente> dependentes1 = mapaDepen.get(titular);
		if(dependentes1 != null){
			mapaDepen.get(titular).add(dependente);
		}
		else{
		mapaDepen.put(titular, dependentes);
		}
		
		
	}

	public List<Dependente> listarDependentes(Titular titular) {

		return mapaDepen.get(titular);
	}

	

}
