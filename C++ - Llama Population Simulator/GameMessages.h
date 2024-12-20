// Jeremy Lim Kin Lok
// CSC 2010 Course Project
// Date: 3-005-24

// GameMessages.h
// Includes the functions to write the texts in 'output.txt'.

#ifndef GAMEMESSAGES	// set header
#define GAMEMESSAGES

#include <iostream> // allows program to output data to the screen.
#include <string>	// include the string library.
#include <fstream>	// allows file reading/writing.
#include <cstdlib>	// For rand() and srand().
#include <ctime>	// For time() to ensure better unpredictability.
#include <vector>
#include <array>
using namespace std;
#include "Mammal.h"
#include "Llama.h"

array<string, 5> stillFaceGUI = {"|___|","(._.)"," | |"," (/////)^"," ||   ||"};
array<string, 5> loseFaceGUI = {"|___|","(x_x)"," | |"," (/////)^"," ||   ||"};
array<string, 5> winFaceGUI = {"|___|","(>-<)"," | |"," (/////)^"," ||   ||"};

void displayFaceGUI(int typeOfFace, ofstream& MyFile) {		// display Face GUI
	array<string, 5> typeOfFaceGUI;
	if (typeOfFace == 0)
		typeOfFaceGUI = stillFaceGUI;
	else if (typeOfFace == 1)
		typeOfFaceGUI = loseFaceGUI;
	else
		typeOfFaceGUI = winFaceGUI;
	for (int a=0; a<5; a++) {
		MyFile << typeOfFaceGUI[a] << endl;
	}
}
string yearMessage(int theYear) {			// returns the Era for that Year.
	if (theYear>=0 && theYear<5) {
		return("Gensis (Year "+to_string(theYear)+")");
	} else if (theYear < 10) {
		return("Dawn (Year "+to_string(theYear)+")");
	} else if (theYear < 15) {
		return("Growth (Year "+to_string(theYear)+")");
	} else if (theYear < 20) {
		return("Development (Year "+to_string(theYear)+")");
	} else if (theYear < 25) {
		return("Evolution (Year "+to_string(theYear)+")");
	} else if (theYear < 30) {
		return("Peak (Year "+to_string(theYear)+")");
	} else if (theYear < 35) {
		return("Revelation (Year "+to_string(theYear)+")");
	} else if (theYear < 40) {
		return("Crisis (Year "+to_string(theYear)+")");
	} else if (theYear < 50) {
		return("Apocalypse (Year "+to_string(theYear)+")");
	}
	return("How. Just How? (Year "+to_string(theYear)+")");
}
void displayCommunity(vector<Llama> llamaVector,int theYear, ofstream& MyFile) {	// display Community in 'output.txt'.
	MyFile << "--------------------" <<endl<< yearMessage(theYear) <<endl<< "--------------------" << endl;
	displayFaceGUI(0, MyFile);
	MyFile << "The Llama Community" << endl << "--------------------" << endl;
	// writes all the Llamas as a list in 'output.txt'.
	for (int n=0; n< llamaVector.size(); n++) {
		MyFile << n+1 <<". Name: "<< llamaVector[n].name <<" | Color: "<< llamaVector[n].color <<" | Age: ";
		MyFile << llamaVector[n].age <<" | Marvel: "<< (llamaVector[n].marvel ? "True" : "False") << endl;
	}
	MyFile << "" << endl;
}

void marvelNonmarvelDisplay(vector<Llama> llamaVector, ofstream& MyFile) {
	int marvelNum = 0;
	int nonmarvelNum = 0;
	// Finds marvel & non-marvel numbers.
	for (int index=0; index < llamaVector.size(); index++) {	// goes through all Llamas.
		if (llamaVector[index].marvel == true) {	// checks if Llamas are marvel or not.
			marvelNum += 1;		// add to marvel counter.
		} else {
			nonmarvelNum += 1;	// add to non-marvel counter.
		}
	}
	MyFile << "Marvel Non-Marvel Ratio : " << marvelNum << " - " << nonmarvelNum <<endl;
	MyFile << "" <<endl;
}

string deathMessage(string llamaName) {		// returns a random death message.
	array<string, 8> causeOfDeath = {" died of old age."," licked a mysterious green slime off a wall..."," ALT + F4", " can't pay his medical bills.", " ate a Radioative Snail.", " died of laughter.", " exploded.", " fell off a cliff."};
	int randomNum = (rand() % 8);
	return("Llamy "+llamaName+causeOfDeath[randomNum]);
}
void displayDeath(vector<string> deadLlamas, ofstream& MyFile) {	// display all Deaths in 'output.txt'
	MyFile << "Llamas Death :c" << endl << "--------------------" << endl;
	if (deadLlamas.size() == 0) {
		MyFile << "No Llamas died this year from \"natural\" causes :D" << endl;	// if no Llamas died.
	} else {
		for (int n=0; n<deadLlamas.size(); n++) {			// if have, random death message is displayed in 'output.txt'.
			MyFile << "x-x | " << deathMessage(deadLlamas[n]) << endl;
		}
	}
	MyFile << "" << endl;
}

string birthMessage(string llamaName) {		// returns a random birth message.
	array<string, 8> causeOfBirth = {" is born in a stable!"," cries for the 1st time."," goes whoop whoop!"," has been compiled."," first words are 'Roger Roger'."," has survived 11 months of Darkness."," beep boop."," weeee!"};
	int randomNum = (rand() % 8);
	return("Llamy "+llamaName+causeOfBirth[randomNum]);
}
void displayBirth(vector<string> newbornLlamas, ofstream& MyFile) {	// display all Births in 'output.txt'
	MyFile << "Llamas Birth :O" << endl << "--------------------" << endl;
	if (newbornLlamas.size() == 0) {
		MyFile << "No Llamas were born this year :d" << endl;	// if no newborn Llamas appear.
	} else {
		for (int n=0; n<newbornLlamas.size(); n++) {			// if have, random death message is displayed in 'output.txt'.
			MyFile << "o-o | " << birthMessage(newbornLlamas[n]) << endl;
		}
	}
	MyFile << "" << endl;
}

string plagueMessage(string llamaName, int whichPlague) {	// returns specific plague message.
	if (whichPlague == 0) {
		return("Llamy "+llamaName+" had a heart attack after watching a jumpscare.");
	} else if (whichPlague == 1) {
		return("Llamy "+llamaName+" was unfairly executed.");
	} else if (whichPlague == 2) {
		return("Llamy "+llamaName+" was accidentaly electro-fried.");
	} else if (whichPlague == 3) {
		return("Llamy "+llamaName+" succumbed to the PLAGUE.");
	}
	return("Llamy "+llamaName+" was found as white as snow.");
}
void displayPlague(vector<string> deadLlamas, ofstream& MyFile) {	// display the PLAGUE in 'output.txt'.
	int whichPlague = (rand() % 5);	// chose a random plague from 0 - 4.
	// display plague title.
	if (whichPlague == 0) {
		MyFile << "FRIGHT NIGHT: "<< "Llamy Markiplier was playing 3 Scary Games when..." << endl;
	} else if (whichPlague == 1) {
		MyFile << "TRAITOR: Llamy King Llama IV sus on everyone. He demands half to be snapped away." << endl;
	} else if (whichPlague == 2) {
		MyFile << "ZAP: Llamy God of Thunder goes boom boom. The Sky turns gray and... " << endl;
	} else if (whichPlague == 3) {
		MyFile << "CURSE: An unfortunate illness has arised. Its name is the PLAGUE." << endl;
	} else {
		MyFile << "???: Llamy Dracula is soaked in 'ketchup'. Meanwhile, half the population is gone." << endl;
	}
	MyFile << "--------------------" << "--------------------" << "--------------------" << endl;
	// display dead plague victims.
	for (int n=0; n < deadLlamas.size(); n++) {
		MyFile << "._. | " << plagueMessage(deadLlamas[n], whichPlague) << endl;
	}
	MyFile << "" << endl;
}
#endif	// end header