package register.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.text.SimpleDateFormat;

public class Util {
	public boolean validaData(String inDate) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    try {
	      dateFormat.parse(inDate.trim());
	    } catch (ParseException pe) {
	      return false;
	    }
	    return true;
	  }
	
	public Date transformaData(String date) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		dt.setLenient(false);
		Date data = new Date();
		if (!date.equals("")) {
			try {
				data = dt.parse(date);
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		}
		return data;
	}
	
	public String dateToString(Date data) {
		SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
		 
		String result = out.format(data);
		//String strDate = dateFormat.format(data);
		return result;
	}
	
	public String convertStringToDate(Date indate)
	{
	   String dateString = null;
	   DateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
	   /*you can also use DateFormat reference instead of SimpleDateFormat 
	    * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
	    */
	   try{
		dateString = sdfr.format( indate );
	   }catch (Exception ex ){
		System.out.println(ex);
	   }
	   return dateString;
	}
	
	public boolean isInteger(String inInt) {
	    try {
	    Long.parseLong(inInt);
	    } catch (Exception e) {
	      return false;
	    }
	    return true;
	  }
	
	public String mascaraCpf(String cpf) {
		if (cpf.contains(".")) {
			cpf = cpf.replaceAll("[-.]", "");
			return cpf;
		} else {
			cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
			return cpf;
		}
	}
	
	public String mascaraTelefone(String telefone) {
		if (telefone.contains("(") || telefone.contains(")") || telefone.contains("-") || telefone.contains(" ")) {
			telefone = telefone.replaceAll("[()-]", "");
			telefone = telefone.replace(" ", "");
			return telefone;
		} else {
			telefone = telefone.substring(0, 0) + "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-" + telefone.substring(6, 10);
			return telefone;
		}
	}
	
	public double mascaraPagamento(String dinheiro) {
		double money = 0;
		if (dinheiro.contains(",") || dinheiro.contains(".")) {
			dinheiro.replaceAll("[.]", "");
			try {
				money = Double.parseDouble(dinheiro.replace(",","."));
			} catch(Exception e) {
				System.out.println("Erro no método mascaraPagamento.");
			}
		}
		return money;
	}
	
	public String converteStatus(String status) {
		if (status.equals("1")) {
			status = "ATIVO";
		} else if (status.equals("2")) {
			status = "SUSPENSO";
		} else if (status.equals("3")) {
			status = "REPROVADO";
		} else if (status.equals("4")) {
			status = "INATIVO";
		} else {
			if (status.equals("ATIVO")) {
				status = "1";
			} else if (status.equals("SUSPENSO")) {
				status = "2";
			} else if (status.equals("REPROVADO")) {
				status = "3";
			} else if (status.equals("INATIVO")) {
				status = "4";
			}
		}
		return status;
	}
	
	public boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
		CPF = CPF.replaceAll("[-.]", "");
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);
          
        char dig10, dig11;
        int sm, i, r, num, peso;
          
        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {              
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0         
        // (48 eh a posicao de '0' na tabela ASCII)         
            num = (int)(CPF.charAt(i) - 48); 
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
          
        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);
          
        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }
}