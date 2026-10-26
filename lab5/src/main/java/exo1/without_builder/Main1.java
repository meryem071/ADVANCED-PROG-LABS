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

	}

	// Commentaire : le constructeur à 9 paramètres est peu lisible. On ne sait pas
	// à quoi correspondent les null, true, true, 2000 sans relire la signature.
	// Plusieurs paramètres ont le même type (url, host, body sont des String) :
	// on peut les inverser sans erreur de compilation. On doit aussi tout
	// renseigner, même ce qui est inutile (body pour un GET), et rien ne vérifie
	// la cohérence (un POST avec Content-Type mais sans body passe sans problème).
		

}
