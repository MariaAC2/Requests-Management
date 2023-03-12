# Requests-Manager
### Chirnogeanu Maria-Andreea, Grupa 323CB

#### Scopul temei
Tema 2 are ca scop implementarea unui sistem de gestionare a cererilor facute de utilizatori, duse la un birou si rezolvate de functionari.

#### Motivare alegere colectii
In cadrul clasei **Utilizator** am folosit un TreeSet care stocheaza elemente de tip **Cerere** si tine cererile in asteptare. Am folosit TreeSet deoarece imi permite sa adaug o cerere o singura data si pentru ca imi pot crea propriul comparator care ma ajuta la afisarea cererilor in asteptare. Pentru cererile finalizate, am folosit ArrayList deoarece eu vreau sa mentin ordinea solutionarii cererilor.

In cadrul clasei **Birou** am folosit un TreeSet pentru cererile in asteptare, deoarece pot ordona cererile dupa prioritate, apoi dupa data. Pentru functionarii publici am folosit un ArrayList ce contine elemente de tip Functionar.

Pentru a adauga cererile in biroul corespunzator tipului de utilizator care creeaza cererea, am creat o clasa numita **CladireBirouri** care contine o colectie de tip HashMap care are drept cheie tipul de utilizator ce corespunde unui birou si drept valoare un birou. Asta imi permite sa adaug, sa retrag sau sa rezolv o cerere in biroul ce corespunde tipului de utilizator dat in comanda.

#### Implementare comenzi:
 * adauga functionar - in functie de tipul utilizatorului, caut in cladirea de birouri si adaug functionarul in biroul corespunzator
 * adauga utilizator - folosind datele date in comanda, este creat un obiect din clasa Utilizator si este adaugat intr-un ArrayList ce contine toti utilizatorii
 * creeza o cerere - prima data caut utilizatorul in colectia cu toti utilizatorii, apoi creez un obiect de tip Cerere. Acesta este adaugat unui functionar daca este valid (daca nu este valid i se arunca o exceptie), apoi verific daca este adaugat. Daca este adaugata, inseamna ca cererea este valida, deci poate fi adaugata in biroul corespunzator tipului de utilizator.
 * retrage o cerere - caut utilizatorul dupa numele sau, apoi sterg cererea ce corespunde datei scrise in comanda, atat din TreeSet-ul corespunzator utilizatorului, cat si cel al biroului cu tipul de utilizator dat
 * solutioneaza o cerere - in biroul pentru tipul de utilizator cu care lucrez caut functionarul dupa numele dat in linia de comanda, apoi iau prima cerere din birou (cea mai urgenta) si o sterg din birou. Scriu datele sale in fisierul care corespunde functionarului, apoi sterg cererea din TreeSet-ul cu cereri in asteptare al utilizatorului caruia ii corespunde cererea si o adaug in ArrayList-ul cu cereri finalizate
 * afisare cereri - caut biroul din care afisez cererile, apoi le afisez.
 * afisare cereri asteptare - caut utilizatorul dupa numele sau, apoi afisez cererile sale
 * afisare cereri finalizate - fac la fel, doar ca pt cererile finalizate

#### Implementare main
In clasa **ManagementPrimarie** exista metoda *main*, in care am initializat 2 String-uri, care reprezinta numele fisierului de input, respectiv output. Apoi, am creat fisierul de output si am implementat ca, in momentul in care se detecteaza ca fisierul exista deja, sa stearga continutul lui anterior pentru a nu face append cu acelasi mesaj dupa o a doua rulare. Am creat o colectie ArrayList cu toti utilizatorii si o cladire de birouri ce contine toate tipurile de birouri. La final, am citit din fisierul de input continutul linie cu linie si am initializat un obiect de clasa **Instructiune**, ce imi trece prin toate tipurile de comenzi.
