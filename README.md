# HowDoYouLearn

## An Artificial Intelligence Learning Project in Angular.

The objective of this project is to create a mechanisme that learn concepts from the caos of internet (through Google) in difference languages associating to a concept all the information can obtain in internet: images, definitions, opinions, investigations and more; defining a level of information security.

# Entities

## UnityKnowledge

Represents the mininum Unity of a Knowledge. Assuming that a Knowledge is some word, part of a word or some words that the Human Intelligence can associate to an Image.

## Idea

An Idea is an amount of Unity Knowledge that make think a Human Intelligence

## Critery

A Critery is an amount of Idees that are interelationated of any Unity Knowledge and construct the personality of a Human Intelligence.

## Personality

A Personality is an amount of Criteries that this Human Mind arrive to.

# Versions

## Version 1.0.0-SNAPSHOT

Implementation of the Controller, the Service and the LRU Cache.

The data is saved in a LRU Cache.

## Version 2.0.0-SNAPSHOT

The data consists in a UnityKnowledge with concept, description and image and list of tags and list of relations.
The list of tags is a List of String.
The list of relations is a List of UnityKnowledge.

Integration of MongoDB and save the data to it.

## Version 2.1.0-SNAPSHOT

Define the controller like crossOrigin for calling from any web domain.

Add The Robot data object and the relation with the UnityKnowledges.
Add The Service Methods for create, remove, list, edit and consult the Robots and the Unities.