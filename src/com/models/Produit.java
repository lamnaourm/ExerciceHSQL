package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="produit")
@NamedQueries( {
                       @NamedQuery(name="q1", query="from Produit"),
                       @NamedQuery(name="q2", query="from Produit where prix_achat > :prix and prd_nom like :nom"),
                       @NamedQuery(name="q3", query="from Produit where prix_vente between 100 and 1000")
			}) 
public class Produit {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="nom")
	private String prd_nom;
	
	@Column
	private String famille;
	
	@Column
	private double prix_achat;
	
	@Column
	private double prix_vente;
	
	public Produit() {
		super();
	}
	public Produit(int id, String nom, String famille, double prix_achat, double prix_vente) {
		super();
		this.id = id;
		this.prd_nom = nom;
		this.famille = famille;
		this.prix_achat = prix_achat;
		this.prix_vente = prix_vente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrd_nom() {
		return prd_nom;
	}
	public void setPrd_nom(String prd_nom) {
		this.prd_nom = prd_nom;
	}
	public String getFamille() {
		return famille;
	}
	public void setFamille(String famille) {
		this.famille = famille;
	}
	public double getPrix_achat() {
		return prix_achat;
	}
	public void setPrix_achat(double prix_achat) {
		this.prix_achat = prix_achat;
	}
	public double getPrix_vente() {
		return prix_vente;
	}
	public void setPrix_vente(double prix_vente) {
		this.prix_vente = prix_vente;
	}
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + prd_nom + ", famille=" + famille + ", prix_achat=" + prix_achat
				+ ", prix_vente=" + prix_vente + "]";
	}
	
	
}
