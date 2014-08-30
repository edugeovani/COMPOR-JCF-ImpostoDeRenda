package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.Dependente;
import br.ufcg.ppgcc.compor.ir.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.FontePagadora;
import br.ufcg.ppgcc.compor.ir.Resultado;
import br.ufcg.ppgcc.compor.ir.Titular;

public class ImpostoDeRenda implements FachadaExperimento {

	private Map<Titular, List<FontePagadora>> mapaFontes = new HashMap<Titular, List<FontePagadora>>();
	private Map<Titular, List<Dependente>> mapaDepen = new HashMap<Titular, List<Dependente>>();
	private List<Titular> titulares = new ArrayList<Titular>();
	
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
			mapaFontes.put(titular, new ArrayList<FontePagadora>());
			mapaDepen.put(titular, new ArrayList<Dependente>());
					

	}

	public List<Titular> listarTitulares() {
		return titulares;
	}


	public List<FontePagadora> listarFontes(Titular titular) {
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
		}
		/* Adiciona a nova fonte na nova lista, ou na lista que já existia */
		
		fontesDoTitular.add(fonte);
	}

	public void criarDependente(Titular titular, Dependente dependente) {
		
		if(dependente.getCpf() == null){
			throw new ExcecaoImpostoDeRenda("O campo CPF é obrigatório");
		}
		if(dependente.getNome() == null){
			throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
		}
		if(dependente.getTipo() == 0){
			throw new ExcecaoImpostoDeRenda("O campo tipo é obrigatório");
		}
		if(dependente.getTipo() < 0){
			throw new ExcecaoImpostoDeRenda("O campo tipo é inválido");
		}
		if((dependente.getCpf() != null) && (dependente.getCpf().length()!= 14) ){
			throw new ExcecaoImpostoDeRenda("O campo CPF é inválido");
		}
		
		List<Dependente> dependentes1 = mapaDepen.get(titular);
		if(dependentes1 == null){
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
		}

		dependentes1.add(dependente);
	}

	public List<Dependente> listarDependentes(Titular titular) {

		return mapaDepen.get(titular);
	}

	public Resultado declaracaoCompleta(Titular titular) {
		return new Resultado();
	}

	

}
