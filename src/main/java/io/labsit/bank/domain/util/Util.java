package io.labsit.bank.domain.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public static String formataCPF(String str) {
		return str.replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})", "$1.$2.$3-$4");
	}
	
	public static boolean validaCPF(String cpf) {
		
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		List<String> cpfs =  cpfSequencial();
		String nDigResult;

		
		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		try {
		     Long.parseLong(cpf);
		} catch (Exception e) {
		  return false;
		}
		
		if ((cpf != null) && (cpf.trim().length() != 11)) {  
			return false;
		}
		
		if((cpf != null) && (cpfs.contains(cpf))) {
			return false;
		}
		
		for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();
			d1 = d1 + (11 - nCount) * digitoCPF;
			d2 = d2 + (12 - nCount) * digitoCPF;
		}

		resto = (d1 % 11);

		if (resto < 2)
			digito1 = 0;
		else
			digito1 = 11 - resto;

		d2 += 2 * digito1;

		resto = (d2 % 11);

		if (resto < 2)
			digito2 = 0;
		else
			digito2 = 11 - resto;

		String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		if (!nDigVerific.equals(nDigResult)) {
			return false;
		}
		
		return true;
	}
	
	private static List<String> cpfSequencial() {
		List<String> cpfs = new ArrayList<String>();
		StringBuffer c = null;
		for(int i = 2; i <10; i++) {
			c = new StringBuffer();
			for(int n = 0; n < 11;n++) {
				c.append(i);
			}
			cpfs.add(c.toString());
		}
		return cpfs;
	}
	
	public static String formataCNPJ(String str) {
		return str.replaceAll("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})","$1.$2.$3/$4-$5");
	}
	
	public static boolean validaCNPJ(String cnpj) {
		try {
		     Long.parseLong(cnpj);
		} catch (Exception e){
		  return false;
		}
		
		// Verifica se o campo tem menos de 14 dígitos
		if (cnpj.length() < 14) {
			return false;
		} else {

			int m1;
			int m2 = 2;
			int soma1 = 0;
			int soma2 = 0;
			int val, d1, d2;
			for (int i = 11; i >= 0; i--) {
				val = Integer.parseInt(cnpj.substring(i, i + 1));
				m1 = m2;
				if (m2 < 9)
					m2 = m2 + 1;
				else
					m2 = 2;
				soma1 = soma1 + (val * m1);
				soma2 = soma2 + (val * m2);
			}
			soma1 = soma1 % 11;
			if (soma1 < 2)
				d1 = 0;
			else
				d1 = 11 - soma1;
			soma2 = (soma2 + (2 * d1)) % 11;
			if (soma2 < 2)
				d2 = 0;
			else
				d2 = 11 - soma2;
			if (d1 != Integer.parseInt(cnpj.substring(12, 13)) || d2 != Integer.parseInt(cnpj.substring(13, 14))) {
				return false;
			}
		}
		return true;
	}
	
	public static String removeCaracteresEspeciais(String texto) {
		texto = texto.replaceAll("[^0-9A-Za-z]*", "");  
		return texto;
	}

}
