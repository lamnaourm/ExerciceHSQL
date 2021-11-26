import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.utils.HibernateUtils;
import com.models.Produit;

public class Test {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		
		// Q1 : Afficher tous les produits qui se trouve dans la table PRODUIT.
		System.out.println("1- Afficher tous les produits qui se trouve dans la table PRODUIT");
		List<Produit> prds = s.createNamedQuery("q1").list();
		for(Produit p : prds)
			System.out.println(p);
		// Q2 :Afficher tous les produits qui ont un prix d’achat > 200 et qui ont un nom qui commence par A.
		System.out.println("2- Afficher tous les produits qui ont un prix d’achat > 12 et qui ont un nom qui commence par A");
		prds = s.createNamedQuery("q2")
				.setParameter("prix", 12.0)
				.setParameter("nom", "N%")
				.list();
		for(Produit p : prds)
			System.out.println(p);
		// Q3 :Afficher tous les produit qui ont un prix de vente entre 100 et 1000. 
		System.out.println("3- Afficher tous les produit qui ont un prix de vente entre 100 et 1000");
		prds = s.createNamedQuery("q3").list();
		for(Produit p : prds)
			System.out.println(p);
		// Q4 :Afficher la somme des prix de vente et prix d’achat par famille.
		System.out.println("4- Afficher la somme des prix de vente et prix d’achat par famille");
		List<Object[]> res = s.createQuery("select famille, sum(prix_achat),sum(prix_vente) from Produit group by famille").list();
		for(Object[] p : res)
			System.out.println(p[0] + " - " + p[1] + " - " + p[2]);
		// Q5 :Demander à l’utilisateur de saisir une famille, puis afficher tous les produit de cette famille.
		System.out.println("5- Demander à l’utilisateur de saisir une famille, puis afficher tous les produit de cette famille");
		prds = s.createQuery("from Produit where famille = :famille")
				.setParameter("famille", sc.nextLine())
				.list();
		for(Produit p : prds)
			System.out.println(p);
		// Q6 :Demander à l’utilisateur de saisir les informations d’un produit, puis ajouter ce produit dans la base de données.
		System.out.println("5- Demander à l’utilisateur de saisir les informations d’un produit, puis ajouter ce produit dans la base de données");
		System.out.println("Donner les informations du prod : ");
		Produit p = new Produit();
		p.setPrd_nom(sc.nextLine());
		p.setFamille(sc.nextLine());
		p.setPrix_achat(sc.nextDouble());
		p.setPrix_vente(sc.nextDouble());
		s.save(p);
		// Q7 :Demande à l’utilisateur d’augmenter le prix d’achat et le prix de vente par 10% des produit qui appartiennent à une famille donnée par l’utilisateur.  Afficher le nombre de produit modifiés
		String req = "update Produit set prix_achat = prix_achat * 1.1, prix_vente = prix_vente * 1.1 where famille=:famille";
		s.createQuery(req)
		.setParameter("famille", sc.nextLine())
		.executeUpdate();
		// Q8 :Supprimer tous les produits qui ont un nom qui commence par A. afficher le nombre de produit supprimé. 
		req = "delete from Produit where prd_nom like 'A%'";
		s.createQuery(req).executeUpdate();
		
		t.commit();
		s.close();

	}

}
