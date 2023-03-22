//Wienczyslaw Wlodyga
package uj.edu.pl.s1192410;
import java.util.Scanner;
class Source {
    static int BinSearchFirst(int x, int[] array) {
        int lower = 0, upper = array.length - 1, current, index = -1; // ograniczenie dolne tablicy, ograniczenie gorne tablicy, obecny indeks, zwracany indeks
        //inicjujemy pojedyncze zmienne wiec zlozonosc pamieciowa pozostaje jako O(1)
        while (lower <= upper) {
            current = (lower + upper) / 2;
            //jezeli znajdziemy szukany element, to moze byc to pierwsze wystapienie, zapisujemy indeks w zmiennej index
            //ale kontynuujemy szukanie na lewo od elementu, poniewaz tylko tam moze wystapic domniemane pierwsze wystapienie
            //na koncu zwracamy indeks
            if (array[current] == x) {
                index = current;
                upper = current - 1;
            } else {
                if (x < array[current])
                    upper = current - 1;
                else
                    lower = current + 1;
            }
        }
        //jesli nie ma elementu zwracamy 0
        if(index < 0)
            index = 0;
        return index;
    }
    //analogiczna metoda szukajaca ostatniego wystapienia danego elementu
    static int BinSearchLast(int x, int[] array){

        int lower = 0, upper = array.length - 1, current, index = -1; // ograniczenie dolne tablicy, ograniczenie gorne tablicy, obecny indeks, zwracany indeks
        //inicjujemy pojedyncze zmienne wiec zlozonosc pamieciowa pozostaje jako O(1)
        while (lower <= upper) {
            current = (lower + upper) / 2;
            //jezeli znajdziemy szukany element, to moze byc to pierwsze wystapienie, zapisujemy indeks w zmiennej index
            //ale kontynuujemy szukanie na lewo od elementu, poniewaz tylko tam moze wystapic domniemane pierwsze wystapienie
            //na koncu zwracamy indeks
            if (array[current] == x) {
                index = current;
                lower = current + 1;
            } else {
                if (x < array[current])
                    upper = current - 1;
                else
                    lower = current + 1;
            }
        }
        //jesli nie ma elementu zwracamy 0
        if(index < 0)
            index = 0;
        return index;
    }
    static int FindDistinctElements(int[] array){
        //inicjujemy licznik rozroznialnych elementow
        int distinctCounter = 0;
        //przechodzimy przez cala tablice
        for(int i = 0; i < array.length; i++){
                distinctCounter++;
                //jesli element pod indeksem i jest taki sam jak ten pod indeksem i+1 to dodajemy do i az dojdziemy do innego elementu
                //jesli to bedzie ostatni rozny element to wyjdziemy z petli while a nastepnie for , bo jej warunek rowniez nie bedzie juz spelniony
                //zatem w najgorszym przypadku pierwsza petla wykona sie n razy, (n to dl. tablicy), a druga petla w najgorszym przypadku rowniez wykona sie n razy, ale wtedy pierwsza petla wykona sie tylko 1 raz, czyli zlozonosc w obu przypadkach jest O(n)
                while(i < array.length-1 && array[i] == array[i+1])
                    i++;
        }
        return distinctCounter;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //zmienne
        int dataSets;
        int arraySize;
        int[] taxArray;
        int inquiryAmount;
        int leftBound;
        int rightBound;
        int people;
        int indexMax;
        int indexMin;
        //wczytywanie wejscia
        dataSets = scanner.nextInt();
        while(dataSets > 0){
            arraySize = scanner.nextInt();
            taxArray = new int[arraySize];
            for(int i = 0; i < taxArray.length ; i++)
                taxArray[i] = scanner.nextInt();
            inquiryAmount = scanner.nextInt();
            for(int i = 0; i < inquiryAmount ; i++){
                leftBound = scanner.nextInt();
                rightBound = scanner.nextInt();
                if(leftBound > rightBound)
                    System.out.println(0);
                else{
                    if(leftBound < taxArray[0] && rightBound < taxArray[0]) {
                        System.out.println(0);
                        continue;
                    }
                    else if(leftBound <= taxArray[0] && rightBound >= taxArray[0])
                        leftBound = taxArray[0];
                    else if(leftBound >= taxArray[taxArray.length-1] && rightBound >= taxArray[taxArray.length-1]){
                        System.out.println(0);
                        continue;
                    }
                    else if(leftBound <= taxArray[taxArray.length-1] && rightBound >= taxArray[taxArray.length-1])
                        rightBound = taxArray[taxArray.length-1];
                    for(int j = 0; j < taxArray.length; j++){
                        if(taxArray[j] >= rightBound){
                            rightBound = taxArray[j];
                            break;
                        }
                    }
                    for(int j = 0; j < taxArray.length; j++){
                        if(taxArray[j] > leftBound){
                            leftBound = taxArray[j];
                            break;
                        }
                    }
                    people = BinSearchFirst(rightBound, taxArray) - BinSearchFirst(leftBound, taxArray);
                    System.out.println(people);
                }
            }
            System.out.println(FindDistinctElements(taxArray));
            dataSets--;
        }

    }
}
/* TESTY
testy:
// z tresci:
2
12
12
-1 1 2 2 2 3 5 5 7 7 9 9
12
1 2
4
2 2
3
3 6
3
2 1
0
-1 10
12
1 0
0
4 6
2
4 3
0
-1 9
12
1 1
1
4 4
0
0 9
11
7
10
10
1 1 1 1 1 1 1 1 1 1
7
1 2
10
0 1
10
1 1
10
0 0
0
2 2
0
3 1
0
-1 -1
0
1
// wlasne:
10
10
 1 2 3 3 3 4 5 6 7 8
12
0 10
 1 2
 3 4
 12 13
 12 10
 1 5
 3 3
 2 2
 -1 20
 0 5
 8 8
 4 4
15
 -1 0 1 15 20 21 21 21 21 30 32 104 100 100 1015
10
 1 100
 -1 1000
 -2 2
 -3 -2
 -4 20
 21 21
 30 34
 100 2000
 1000 1000
 200 1111
8
 -40 -31 -20 -15 20 49 312 599
5
 1 100
 2 10
-10 600
 15 10
 100 600
30
 1 2 3 4 5 6 7 8 9 10 11 11 11 11 11 11 11 11 11 11 20 21 21 23 23 23 28 29 30 31
6
 1 20
 11 11
 30 31
 31 31
 21 21
 6 11
























 */