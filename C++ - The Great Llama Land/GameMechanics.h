// JLKL
// Date: 3-005-24

// GameMechanics.h
// Includes the functions for the basic Mechanics of the Game.

#ifndef GAMEMECHANICS	// set header
#define GAMEMECHANICS

#include <iostream> // allows program to output data to the screen.
#include <string>	// include the string library.
#include <cstdlib>	// For rand() and srand().
#include <ctime>	// For time() to ensure better unpredictability.
#include <vector>
using namespace std;
#include "Mammal.h"
#include "Llama.h"
vector<string> ageUpdater(vector<Llama>& llamaVector) {	// updates all Llamas' age. Returns
	bool flag = true;									//   dead Llamas' names.
	int index = 0;
	vector<string> deadLlamas;
	while(flag) {	// loop through llamas.
		llamaVector[index].age += 1;			// adds 1 to their age.
		if ((llamaVector[index].age == 11 && llamaVector[index].marvel == false) || (llamaVector[index].age == 23 && llamaVector[index].marvel == true)) {
			// if age reached 11 y/o and is a non-marvel Llama OR if age reached 23 and is a marvel Llama.
			deadLlamas.push_back(llamaVector[index].name);	// add names of dead Llama in vector.
			llamaVector.erase(llamaVector.begin()+index);	// remove Llama from Vector index.
		} else {
			index += 1;	// go to the next Llama
		}
		if (index == llamaVector.size()) {	// if loop went through all Llamas, loop stops.
			flag = false;
		}
	}
	return(deadLlamas);
}

vector<int> maleFemaleUpdater(vector<Llama>& llamaVector,vector<string>& color) {// color is for babyUpdater().	
// return number of non-marvel males and females that are age 2 or above
	int male = 0;	// counters
	int female = 0;
	for (int index=0; index < llamaVector.size(); index++) {	// goes through all Llamas.
		if ((llamaVector[index].marvel == false) && (llamaVector[index].age >= 2)) {
			// checks if Llamas are non-marvel and age 2 or above.
			if (llamaVector[index].gender == 'M') {
				male += 1;			// if male, adds 1 in integer male.
			} else {
				female += 1;		// if female, adds 1 in integer female.
				color.push_back(llamaVector[index].color);	// adds color for babyUpdater().
			}
		}
	}
	vector<int> genderVector {male, female};
	return(genderVector);
}
vector<string> babyUpdater(vector<Llama>& llamaVector) {	// creates new baby Llamas and return their names.
	vector<string> babyColor;									// contains colors for newborn baby.
	vector<int> genderVector = maleFemaleUpdater(llamaVector, babyColor);// contains fertile M and F numbers.
	vector<string> newbornLlama;								// contains newborn babies' names
	if (genderVector[0] >= 1) {		// if 1 or more male Llama is present.
		for (int i=0; i < babyColor.size(); i++) {
			llamaVector.push_back(Llama());							// creates newborn Llama.
			llamaVector[llamaVector.size()-1].color = babyColor[i];	// changes newborn Llama's color to mom's.
			newbornLlama.push_back(llamaVector[llamaVector.size()-1].name);	// stores newborn's name.
		}
	}
	return(newbornLlama);
}

vector<string> plagueUpdater(vector<Llama>& llamaVector) {	// kills half the population and return dead Llama's names.
	bool flag = true;
	int index;		// Llama to remove.
	vector<string> deadLlamas;		//   dead Llamas' names.
	int originalSize = llamaVector.size();	// gets original size of llamaVector.
	// if there is only 1 Llama left,
	if (llamaVector.size() == 1) {
		deadLlamas.push_back(llamaVector[0].name);	// add names of dead Llama in vector.
		llamaVector.erase(llamaVector.begin()+0);	// remove chosen Llama from Vector index.
	// if more than 1 Llama left,
	} else {
		while(flag) {	// loop through llamas.
			index = (rand() % llamaVector.size());	// random number from 0 to last llama.
			deadLlamas.push_back(llamaVector[index].name);	// add names of dead Llama in vector.
			llamaVector.erase(llamaVector.begin()+index);	// remove chosen Llama from Vector index.
			if (deadLlamas.size() == (originalSize/2)) {	// if half the Llamas are removed, loop stops.
				flag = false;
			}
		}
	}
	return(deadLlamas);
}

void marvelUpdater(vector<Llama>& llamaVector) {	// spreads the Marvel Infection.
	int marvelNum = 0;
	int nonmarvelNum = 0;
	vector<int> nonmarvelIndexVector;		// for infection later.
	// Finds marvel & non-marvel numbers and gets the elements of the marvel Llamas.
	for (int index=0; index < llamaVector.size(); index++) {	// goes through all Llamas.
		if (llamaVector[index].marvel == true) {	// checks if Llamas are marvel or not.
			marvelNum += 1;		// add to marvel counter.
		} else {
			nonmarvelNum += 1;	// add to non-marvel counter.
			nonmarvelIndexVector.push_back(index);
		}
	}
	int newInfect = 0;				// stores hmany newly infected Llamas.
	if (marvelNum == 0) {
		newInfect = 0;
	} else if (marvelNum >= nonmarvelNum) {	// if there are more marvel Llamas, everyone will be infected.
		for (int index=0; index < llamaVector.size(); index++) {	// goes through all Llamas.
			llamaVector[index].marvel = true;
		}
		newInfect = nonmarvelNum;
	} else {	// if not, proceed as normal.
		bool flag = true;
		int index;
		while(flag) {	// loop through llamas.
			index = (rand() % nonmarvelIndexVector.size());	// random number from 0 to last llama.
			llamaVector[nonmarvelIndexVector[index]].marvel = true;	// infects chosen Llama.
			nonmarvelIndexVector.erase(nonmarvelIndexVector.begin()+index);	// removes the Llama from non-marvel Vector.
			newInfect += 1;
			if (marvelNum == newInfect) {	// if the infected Llama has infected an equilavent proportion, loop stops.
				flag = false;
			}
		}
	}
}
#endif	// end header