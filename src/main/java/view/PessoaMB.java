package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import model.http.Pessoa;

@ManagedBean
public class PessoaMB {

	private Pessoa pessoa;
	private Pessoa pessoaSel;
	private Collection<Pessoa> colecaoPessoa = new ArrayList<>();
	
	public PessoaMB() {
		getPessoas();
	}
	
	private void getPessoas(){
		Pessoa pes;
		URL url;
		try {
			url = new URL("http://localhost:8080/RestFul/rest/servico/usuarios");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(con.getInputStream())));
			
			StringBuilder sb = new StringBuilder();

		    String line;
		    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    while ((line = br.readLine()) != null) {
		        sb.append(line + "\n");
		    }
			
		    JSONArray jsonArray = new JSONArray(sb.toString());
		    for (int i = 0; i < jsonArray.length(); i++) {
		         JSONObject json = jsonArray.getJSONObject(i);
		         pes = new Pessoa();
		         pes.setId(Integer.valueOf(json.get("id").toString()));
		         pes.setNome(json.get("nome").toString());
		         pes.setEmail(json.get("email").toString());
		         pes.setSenha(json.get("senha").toString());
		         pes.setTelefone(json.get("telefone").toString());
		         colecaoPessoa.add(pes);
		    }
		    
			con.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void cadastrar(){
		URL url;
		try {
			url = new URL("http://localhost:8080/RestFul/rest/servico/cadastrar");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			
			OutputStream os = con.getOutputStream();
			os.write(this.pessoa.toString().getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(con.getInputStream())));
			pessoa = new Pessoa();
			con.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remover(){
		URL url;
		try {
			url = new URL("http://localhost:8080/RestFul/rest/servico/excluir/" + 
					String.valueOf(pessoaSel.getId()));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("DELETE");
			con.setRequestProperty("Content-Type", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(con.getInputStream())));
			
			con.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pessoa getPessoa() {
		if (pessoa == null){
			pessoa = new Pessoa();
		}
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoaSel() {
		if (this.pessoaSel == null) {
			pessoaSel = new Pessoa(); 
		}
		return pessoaSel;
	}

	public void setPessoaSel(Pessoa pessoaSel) {
		this.pessoaSel = pessoaSel;
	}

	public Collection<Pessoa> getColecaoPessoa() {
		return colecaoPessoa;
	}

	public void setColecaoPessoa(Collection<Pessoa> colecaoPessoa) {
		this.colecaoPessoa = colecaoPessoa;
	}
}
