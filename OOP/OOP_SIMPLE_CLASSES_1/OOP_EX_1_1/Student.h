#pragma once
#include <stdio.h>
#include <string>

class Student
{
	
public:
	std::string name;
	int math_grade;
	int eng_grade;
	int hst_grade;
	float avg;
	void NameSet(std::string x); // Gets and sets the name of the student
	void MathSet(int x); // Gets and sets the grade on Math
	void EngSet(int x);  // Gets and sets the grade on English
	void HstSet(int x);  // Gets and sets the grade on History
	float AvgGrade(int x, int y, int z); // Gets and returns the average grade
};