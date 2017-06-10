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

## Version 2.2.0-SNAPSHOT

Done the addRobot, listRobots, addUnity and listUnities.

## Version 2.3.0-SNAPSHOT

Resolve the problem of create relations of unities.
Now you can do the CRUD on Robots and on Unities (create, read, update and delete).
Also done addTag, removeTag and addRelation and removeRelation.

## Version 2.4.0-SNAPSHOT

Modify the method that find unityknowledge in robot.
Make the list of unities and robots in the method getlist scope.

## Version 2.5.0-SNAPSHOT

Implements the methods createTag and removeTag with it's wrapper object.

## Version 2.6.0-SNAPSHOT

Convert the remove methods to GET request, because them have request parameters (removeRobot, removeUnity, removeRelation, removeTag).
Change the postman calls with the changes of the controller service methods.

## Version 2.7.0-SNAPSHOT

Change the list methods passing the idRobot parameter.
Simplify the calls in the services for using only one unity get call and unify it.

## Version 2.8.0-SNAPSHOT

Convert tag to an Object Tag with it's mongodb id.
Convert all model Objects to mongodb documents.
Simplify the parametritzed calls and restrict them only to query methods where are usefull only.
Implements and prove the remove methods for tag and unity using repositories delete function.

## Version 2.9.0-SNAPSHOT

Skip the name parameter and puts the id mongodb parameter in the calls.
Make works the createRobot, createUnity, createTag and createRelation.
Create the methods that get unity and robot by id and by name.
Resolve some other issues.
