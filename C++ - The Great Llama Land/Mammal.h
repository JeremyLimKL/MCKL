// JLKL
// Date: 3-005-24

// Mammal.h
// Includes the parent class Mammal.

#ifndef MAMMAL_H	// set header
#define MAMMAL_H

#include <iostream> // allows program to output data to the screen.
#include <string>	// include the string library.
#include <cstdlib>	// For rand() and srand().
#include <ctime>	// For time() to ensure better unpredictability.

// Parent Class (Mammal).
class Mammal {
	public:
		char gender;
		int age;
		
		Mammal() {	// generates new mammal baby attributes.
			char genderArray[2] = {'M','F'};	// 50/50 for male or female
			gender = genderArray[(rand() % 2)];
			age = 0;	// sets age to 0 (baby)
		}
};
#endif	// end of header