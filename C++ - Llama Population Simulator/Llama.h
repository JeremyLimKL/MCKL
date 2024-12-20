// JLKL
// CSC 2010 Course Project
// Date: 3-005-24

// Llama.h
// Includes the child class Llama.

#ifndef LLAMA_H	// set header
#define LLAMA_H

#include <iostream> // allows program to output data to the screen.
#include <string>	// include the string library.
#include <fstream>	// allows file reading/writing.
#include <cstdlib>	// For rand() and srand().
#include <ctime>	// For time() to ensure better unpredictability.
using namespace std;
# include "Mammal.h"
// Child Class (Llama).
class Llama : public Mammal {
	public:
		string color;
		string name;
		bool marvel;
		
		string nameGenerator() {
			string nameText;
			string nameLlama = "";
			ifstream MyReadFile("nameLlama.txt");	// open
			int randomNum = rand() % 40;// generate random num (0-39) for name.
			int txtNum = 0;				// counter.
			while (getline(MyReadFile, nameText)) {	// iterates text file line by line.
				if (txtNum == randomNum) {	// if randomNum matches txtNum, name is chosen.
					nameLlama = nameText;
					break;
				}
				txtNum += 1;
			}
			MyReadFile.close();
			return(nameLlama);
		}
		
		bool marvelGenerator() {
			if ((rand() %100 + 1) <= 3)	// generate 3% chance of having marvel.
				return true;
			return false;
		}
		
		Llama() {	// generate new llama baby attributes
			string colorArray[5] = {"white","brown","grey","black","blonde"};
			color = colorArray[(rand() % 5)];	// generate random color
			name = nameGenerator();
			marvel = marvelGenerator();
		}
};
#endif	// end header
