package org.dmr.domain.impl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.html.HTMLImageElement;

import org.dmr.domain.UnityKnowledgeType;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */

/**
 * <T> is a Generic Type
 * It can be any Object: String, Boolean, Number, Date, Object, null, undefined
 */
public class UnityKnowledge <T> implements UnityKnowledgeType<T> {
    
    // concepte que estable el coneixement
    T concept;
    // Necessito fer un mecanisme que extregui informacio d'una imatge, per associar-la a unitat de coneixement
    // La imatge associada al concepte estable el coneixement
    HTMLImageElement image;
    // conclusions que fa la mente humana al llarg del temps
    String description;
    // Criteris pels que pots buscar la unitat de coneixement
    List<String> tags;
    // Relacions amb altres unitats de coneixement
    List<UnityKnowledgeType<T>> relations;

    // la creacio d'una unitat de coneixement es la relacio entre un concepte i una imatge
	public UnityKnowledge(T concept, HTMLImageElement image) {
        //definicio unitat de coneixement
        this.concept = concept;
        this.image = image;
        // info complementaria
        this.relations = new ArrayList<UnityKnowledgeType<T>>();
        this.tags = new ArrayList<String>();
        this.description = "";
    }
	
	@Override
    public String getValue() {

        return this.concept.toString();
    
    }
    
    @Override
    public void modifyDescription(String description) {
        
        this.description = description;

    }
    
    @Override
    public void addTag(String tag) {

        this.tags.add(tag);
    
    }
    
    @Override
    public void createRelation(UnityKnowledgeType<T> unity) {
        
        this.relations.add(unity);

        this.addTag(unity.getValue());
    
    }
    
    @Override
    public String toString() {
    	
    	return this.concept.toString();
    
    }
}
