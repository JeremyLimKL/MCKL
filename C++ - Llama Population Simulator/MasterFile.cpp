// JLKL
// Date: 3-005-24

// MasterFile.cpp
// Includes the functions to write the texts in 'output.txt'.

#include <iostream> // allows program to output data to the screen.
#include <string>	// include the string library.
#include <fstream>	// allows file reading/writing.
#include <cstdlib>	// For rand() and srand().
#include <ctime>	// For time() to ensure better unpredictability.
#include <vector>
using namespace std;
#include "Mammal.h"
#include "Llama.h"
#include "GameMechanics.h"
#include "GameMessages.h"

int year;						// takes note of which year it is.
vector<Llama> llamaVector;		// stores the Llama Community.

// Write the Win or Lose Graphics/Data in the 'output.txt'.
void bigDescribe(vector<Llama> llamaVector, string wonLostTop, int year, ofstream& MyFile) {
	// Header
	MyFile << "--------------------" <<endl<< wonLostTop <<endl<< "--------------------" << endl;
	if (wonLostTop == "CONGRATS, you have won!") {
		displayFaceGUI(2, MyFile);	// Llama Graphics change depending on win or lost.
	} else {
		displayFaceGUI(1, MyFile);
	}
	MyFile << "The Llama Community" << endl << "--------------------" << endl;
	MyFile << yearMessage(year) << endl;											// Which Year of the Llama Display.
	MyFile << "Population number: " << llamaVector.size() << " Llamas." << endl;	// Llama Population Display.
	vector<string> color;
	vector<int> gender = maleFemaleUpdater(llamaVector, color);
	MyFile << "Fertile Male - Female : " << gender[0] << " - " << gender[1] << endl;// Llama Fertiel Male & Female Display.
	marvelNonmarvelDisplay(llamaVector, MyFile);									// Llama Marvel Non-marvel Display.
}


int main() {
	srand(time(nullptr));
	cout << "game loading..." << endl;
	for (int start=0; start < 5; start++) {		// create first 5 Llamas.
		llamaVector.push_back(Llama());
		llamaVector[start].age = -1;
	}
	year = -1;
	bool flag = true;
	vector<string> deadPlagueLlamas;	// stores names of dead Llamas from the PLAGUE.
	vector<int> marvelNonMarvelNumber;	// stores the numbers of Marvel and Non-marvel Llamas.
	vector<string> deadLlamas;			// stores names of dead Llamas from old age.
	vector<string> newbornLlamas;		// stores names of newborn baby Llama.
	char input;			// user input for PLAGUE or not.
	while (flag) {
		year += 1;		// add one to each year.
		cout << "Year "+to_string(year)+": Summon a Curse and kill half the Llamas? (K): ";
		cin >> input;	// does user wants to summon the PLAGUE?
		cout << "" << endl;
		ofstream MyFile("output.txt");	// for re-writing in 'output.txt' everytime.
		// let the Game Mechanics work.
		if (input == 'K') {						// if user wants the PLAGUE.
			deadPlagueLlamas = plagueUpdater(llamaVector);	// 1. calls the Plague.
			if (llamaVector.size() == 0) {		// if no more llama due to PLAGUE, exit loop.
				break;
			}
		}
		marvelUpdater(llamaVector);							// 2. spread the marvel infection.
		deadLlamas = ageUpdater(llamaVector);				// 3. updates the age and kill the older ones.
		newbornLlamas = babyUpdater(llamaVector);			// 4. let the Llama reproduce.
		// let the Game Messages write in the file 'output.txt'.
		displayCommunity(llamaVector,year, MyFile);			// 1. displays the Community of Llamas.
		marvelNonmarvelDisplay(llamaVector, MyFile);		// 2. displays the Marvel Nonmarvel Ratio.
		displayDeath(deadLlamas, MyFile);					// 3. displays the Deaths in the community.
		displayBirth(newbornLlamas, MyFile);				// 4. displays the Births in the community.
		if (input == 'K') {
			displayPlague(deadPlagueLlamas, MyFile);		// 5. displays the Plague.
		}
		if (llamaVector.size() == 0 || llamaVector.size() >= 200) {	// if user wins or lose, exit while loop.
			flag = false;
		}
	}
	ofstream MyFile("output.txt");	// for re-writing in 'output.txt'.
	if (llamaVector.size() == 0) {		// if user loses.
		cout << "User has lost." << endl;
		bigDescribe(llamaVector, "RIP, you lost all your Llamas...", year, MyFile);	// display lost in 'output.txt'.
		
	} else {							// if user wins.
		cout << "User has won." << endl;
		bigDescribe(llamaVector, "CONGRATS, you have won!", year, MyFile);			// display win in 'output.txt'.
	}
	cout << "game exiting..." << endl;
	return(0);
}
