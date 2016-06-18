package com.example.user.projectse;

public class Student{
    public String name;
    public String university;
    public String deparpment;
    public int age;
    public String emailAdd;
    public int hasDiagnose;
    public int hasRitalin;

    //public String AcademicStatus; // Maybe a spiner list
    //public String address;

    public Student(String name, String university, String deparpment, int age, String emailAdd){ //initilze builder -> add parametrs
        this.name = name;
        this.university = university;
        this.deparpment=deparpment;
        this.age=age;
        this.emailAdd=emailAdd;
        this.hasRitalin = 0;
        this.hasDiagnose = 0;
    }

    public String getName() {return name;}

    public String getUniversity() {return university;}

    public String getDeparpment() {return deparpment;}

    public int getAge() {return age;}

    public String getEmailAdd() {return emailAdd;}

    public int getHasRitalin() {return hasRitalin;}

    public int getHasDiagnose() {return hasDiagnose;}
}