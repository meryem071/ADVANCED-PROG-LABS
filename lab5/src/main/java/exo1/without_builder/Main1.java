package exo1.without_builder;


import java.util.Map;

public class Main1 {

	public static void main(String[] args) {

		//creation de la map
		Map<String,String> params = Map.of("limit","10");
		
		HttpRequest getRequest = new HttpRequest("GET", "/users", null,null,params,null,true,true,2000);
		//pour le paramètre soit on le passe via Map dans queryParams soit dans l'url /users?limit=10


		//consigne demande d'avoir un body json, un exemple : "{\"user\":\"alice\",\"password\":\"1234\"}"
		HttpRequest postRequest = new HttpRequest("POST", "/login", null,Map.of("Content-Type", "application/json"),null,"{\"user\":\"alice\",\"password\":\"1234\"}",true,true,2000);

		System.out.println(getRequest);
		System.out.println(postRequest);

		try{
			valider(postRequest);
			System.out.println("requête valide");
		}catch(IllegalArgumentException e){
			System.out.println("requete refusee : " + e.getMessage());
		}
		
	}

	// Commentaire : le constructeur à 9 paramètres est peu lisible. On ne sait pas
	// à quoi correspondent les null, true, true, 2000 sans relire la signature.
	// Plusieurs paramètres ont le même type (url, host, body sont des String) :
	// on peut les inverser sans erreur de compilation. On doit aussi tout
	// renseigner, même ce qui est inutile (body pour un GET), et rien ne vérifie
	// la cohérence (un POST avec Content-Type mais sans body passe sans problème).
		
	public static void valider(HttpRequest r){

		if (r.getMethod().equals("GET") && r.getBody() != null) {
    		throw new IllegalArgumentException("Un GET ne peut pas avoir de body");
		}	
		
		//ici on peut rajouter r.getHost().isBlank() au cas où c'est " "
		if(r.getHost() == null){
			throw new IllegalArgumentException("Host ne peut pas être null");
		}

		if(r.isKeepAlive() && r.isFollowRedirects()){
			throw new IllegalArgumentException("keepAlive et followRedirects ne peuvent pas être true tous les 2");

		}
	}

	// Pourquoi cette approche est difficile à maintenir :
	// - la validation est séparée de la construction : rien n'oblige à appeler
	//   valider() après new HttpRequest(...), une requête incohérente peut exister.
	// - les règles sont dans Main1, pas dans la classe : une autre classe qui crée
	//   une HttpRequest ne les applique pas.
	// - chaque nouvelle règle ou nouvelle option oblige à modifier valider() et à
	//   penser à l'appeler partout.

}
