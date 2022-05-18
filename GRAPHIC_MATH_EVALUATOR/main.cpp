#include <iostream>
#include <cstring>
#include <graphics.h>
#include <cmath>
#define inf INT_MAX
#define eps 0.0001
#define MAX 100
#define MAX1 50
#include <stack>

using namespace std;

const int max_stiva=100;
const float pi=3.1415926;
int poz=0;

int top1,top2; // Varfurile stivelor
stack <char> Oper; // Stiva operanzilor
char Op[max_stiva][5]; // Stive operatorilor

char OpBinare[100]= "+-*/^<>=#";
char OpUnare[100]= "scle";
char Operatii[100]= "+-*/^<>=#scle";

struct arbore
{
    char info[10];
    arbore *stg, *dr;
};

struct functie
{
    char expresie[300];
    char vect[MAX+1][MAX];
    int lungime;
    float a,b; // interval
    int n;
} sir;

int Prioritate(char c) // Prioritatea operatorilor
{
    if(c=='(' || c==')')
        return 0;
    if(c=='+' || c=='-')
        return 1;
    if(c=='*' || c=='/')
        return 2;
    if(c=='^')
        return 3;
    if(c=='=')
        return 4;
    if(c=='s' || c=='c' || c=='l' || c=='t')
        return 5;
}

bool DifInf(float x)
{
    if(abs(inf-abs(x))>inf/2.0)
        return true;
    else
        return false;

}

float Logaritm(float x)
{
    if(x>eps && DifInf(x))
        return log(x);
    else
        return inf;
}

float Exponential(float x)  // Functie exponentiala
{
    if(DifInf(x))
        return exp(x);
    else
        return inf;
}

float Inmultit(float x, float y)  // Functie inmultire
{
    if(abs(x)<eps || abs(y)<eps)
        return 0;
    else if(DifInf(x) && DifInf(y))
        return x*y;
    else
        return inf;
}

float Putere(float x, float y)  // Functie putere
{
    if(x==0)
        return 0;
    else if(y==0)
        return 1;
    else if(x==inf || y==inf)
        return inf;
    else
        return pow(x,y);
}

bool Egal(float x,float y)  // Functie egalitate
{
    if(x==y)
        return true;
    else
        return false;
}

bool Diferit(float x,float y)  // Functie diferit
{
    if(x!=y)
        return true;
    else
        return false;
}

bool MaiMic(float x, float y) // Functie mai mic
{
    if(x<y)
        return true;
    else
        return false;
}

bool MaiMare(float x, float y) // Functie mai mare
{
    if(x>y)
        return true;
    else
        return false;
}

float Adun(float x, float y) // Functie adunare
{
    if(DifInf(x) && DifInf(y))
        return x+y;
    else
        return inf;
}

float Scad(float x,float y) // Functie scadere
{
    if(DifInf(x) && DifInf(y))
        return x-y;
    else
        return inf;
}

float Impart(float x,float y)
{
    if(abs(y)>eps)
        return x/y;
    else
        return inf;
}

float Sinus(float x)
{
    if(DifInf(x))
        return sin(x);
    else
        return inf;
}

float Cosinus(float x)
{
    if(DifInf(x))
        return cos(x);
    else
        return inf;
}

float Modul(float x)
{
    if(DifInf(x))
        return abs(x);
    else
        return inf;
}

float Radical(float x)
{
    if(DifInf(x)&& x>eps)
        return sqrt(x);
    else
        return inf;
}

void EvaluareExpresie(functie sir)
{
    sir.lungime=strlen(sir.expresie);
    if(sir.lungime==0)
        cout<<"Eroare:Nu a fost introdusa nicio ecuatie.";
    else if(sir.expresie[0]=='0')
        cout<<"Eroare:Ecuatia nu poate incepe cu 0.";
    else if(strchr(")*^/+",sir.expresie[0]))
    {
        cout<<"Eroare:Ecuatia nu poate incepe cu ";
        if(strchr(")",sir.expresie[0]))
            cout<<").";
        else if(strchr("*",sir.expresie[0]))
            cout<<"*.";
        else if(strchr("/",sir.expresie[0]))
            cout<<"/.";
        else if(strchr("+",sir.expresie[0]))
            cout<<"+.";
        else if(strchr("^",sir.expresie[0]))
            cout<<"^.";
    }
    else
    {
        int k=0;
        int i;
        for(i=0; i<sir.lungime; i++)
        {
            if(strchr("+-*/^",sir.expresie[i])&& strchr("+-*/^",sir.expresie[i+1]))
            {
                cout<<"Eroare:Pe pozitiile "<<i<<" si "<<i+1<<" au fost introduse 2 dintre semnele: +, -, /, *, ^, consecutive.";
                i=sir.lungime;
            }
            else if(strchr("x0123456789",sir.expresie[i]) && sir.expresie[i+1]=='(' && sir.expresie[i-1]!='g' && sir.expresie[i]!='o')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(sir.expresie[i]==')' && strchr("x0123456789",sir.expresie[i+1]) && i+1<sir.lungime)
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(strchr("x0123456789",sir.expresie[i]) && sir.expresie[i+1]=='s' && sir.expresie[i+2]=='i')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(strchr("x0123456789",sir.expresie[i]) && sir.expresie[i+1]=='c' && sir.expresie[i+2]=='o')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(strchr("x0123456789",sir.expresie[i]) && sir.expresie[i+1]=='t' && sir.expresie[i+2]=='a')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(strchr("x0123456789",sir.expresie[i]) && sir.expresie[i+1]=='c' && sir.expresie[i+2]=='t')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(strchr("x0123456789",sir.expresie[i]) && sir.expresie[i+1]=='l' && sir.expresie[i+2]=='o')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(strchr("x0123456789",sir.expresie[i]) && sir.expresie[i+1]=='r' && sir.expresie[i+2]=='a')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(sir.expresie[i]=='r' && sir.expresie[i+1]=='a' && strchr("x0123456789+-/*^",sir.expresie[i+3]))
            {
                cout<<"Eroare:Dupa pozitia "<<i+2<<", nu a fost folosita: ( ";
                i=sir.lungime;
            }
            else if(strchr("x0123456789",sir.expresie[i]) && sir.expresie[i+1]=='r' && sir.expresie[i+2]=='a')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(strchr("x0123456789",sir.expresie[i]) && sir.expresie[i+1]=='x')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(sir.expresie[i]=='(' && sir.expresie[i+1]==')')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<" nu a fost introdua nicio valoare intre paranteze.";
                i=sir.lungime;
            }
            else if(sir.expresie[i]==')' && sir.expresie[i+1]=='(')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(sir.expresie[i]=='(' && strchr("*/+^",sir.expresie[i+1]))
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", paranteza nu poate incepe cu: ";
                if(sir.expresie[i+1]=='*')
                    cout<<"*";
                else if(sir.expresie[i+1]=='/')
                    cout<<"/";
                else if(sir.expresie[i+1]=='+')
                    cout<<"+";
                else if(sir.expresie[i+1]=='^')
                    cout<<"^";
                i=sir.lungime;
            }
            else if(sir.expresie[i]==')' && sir.expresie[i+1]=='s' && sir.expresie[i+2]=='i')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(sir.expresie[i]==')' && sir.expresie[i+1]=='c' && sir.expresie[i+2]=='o')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(sir.expresie[i]==')' && sir.expresie[i+1]=='t' && sir.expresie[i+2]=='a')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(sir.expresie[i]==')' && sir.expresie[i+1]=='c' && sir.expresie[i+2]=='t')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            else if(sir.expresie[i]==')' && sir.expresie[i+1]=='l' && sir.expresie[i+2]=='o')
            {
                cout<<"Eroare:Dupa pozitia "<<i<<", nu a fost folosit unul din semnele: +, -, *, /.";
                i=sir.lungime;
            }
            k++;
        }
        if(k==i)cout<<"Ecuatia este corecta";
    }
}

char sir_prelucrat[300][5];
void Transformare_Expresie(char expresie[300])
{
    int k;
    for( int i = 0; i<strlen(expresie); i++)
    {
        k=0;
        if(strchr("0123456789",expresie[i]))
        {
            k=0;
            while(strchr("0123456789",expresie[i]))
            {
                sir_prelucrat[poz][k]=expresie[i];
                i++;
                k++;
            }
            i--;
            poz++;
        }
        else if(expresie[i]=='s' && expresie[i+1]=='i')
        {
            sir_prelucrat[poz][0]=expresie[i];
            sir_prelucrat[poz][1]=expresie[i+1];
            sir_prelucrat[poz][2]=expresie[i+2];
            i+=2;
            poz++;
        }
        else if(expresie[i]=='c' && expresie[i+1]=='o')
        {
            sir_prelucrat[poz][0]=expresie[i];
            sir_prelucrat[poz][1]=expresie[i+1];
            sir_prelucrat[poz][2]=expresie[i+2];
            i+=2;
            poz++;
        }
        else if(expresie[i]=='t' && expresie[i+1]=='a')
        {
            sir_prelucrat[poz][0]=expresie[i];
            sir_prelucrat[poz][1]=expresie[i+1];
            sir_prelucrat[poz][2]=expresie[i+2];
            i+=2;
            poz++;
        }
        else if(expresie[i]=='c' && expresie[i+1]=='t')
        {
            sir_prelucrat[poz][0]=expresie[i];
            sir_prelucrat[poz][1]=expresie[i+1];
            sir_prelucrat[poz][2]=expresie[i+2];
            i+=2;
            poz++;
        }
        else if ( expresie[i]=='(' )
        {
            sir_prelucrat[poz][0]=expresie[i];
            poz++;
        }
        else if((expresie[i]=='x') || (expresie[i]=='p' && expresie[i+1]=='i') || expresie[i]=='e')
        {
            if(expresie[i]=='x')
            {
                sir_prelucrat[poz][0]=expresie[i];
                poz++;
            }
            else if(expresie[i]=='p' && expresie[i+1]=='i')
            {
                sir_prelucrat[poz][0]=expresie[i];
                sir_prelucrat[poz][1]=expresie[i+1];
                poz++;
            }
            else if(expresie[i]=='e')
            {
                sir_prelucrat[poz][0]=expresie[i];
                poz++;
            }
        }
        else if(strchr("+-*/^",expresie[i]))
        {
            sir_prelucrat[poz][0]=expresie[i];
            poz++;
        }
        else if ( expresie[i]==')' )
        {
            sir_prelucrat[poz][0]=expresie[i];
            poz++;
        }
        else if(expresie[i]=='l' && expresie[i+1]=='o')
        {
            sir_prelucrat[poz][0]=expresie[i];
            sir_prelucrat[poz][1]=expresie[i+1];
            sir_prelucrat[poz][2]=expresie[i+2];
            i+=2;
            poz++;
        }
        else if(expresie[i]=='l' && expresie[i+1]=='n')
        {
            sir_prelucrat[poz][0]=expresie[i];
            sir_prelucrat[poz][1]=expresie[i+1];
            i++;
            poz++;
        }
    }
}

void Stive(char sir_prelucrat[300][5])
{

    //for(int i=0; i<poz; i++)
        //if(strchr("0123456789",sir_prelucrat[i]) || sir_prelucrat[i]=="x")
            //Oper.push(sir_prelucrat[i]);


}


void CreareArbore(char expresie[100][5],arbore *&arb)
{
    int i=0;
    top1=0;
    top2=1;
    while(i<strlen(expresie[i]) && top2>=0)
    {
        if(strchr("x0123456789",expresie[i][0]))
        {
            arb= new arbore;
            strcpy(arb->info,expresie[i]);
            arb->stg = NULL;
            arb->dr = NULL;


        }
    }
}

int main()
{
    /*functie sir;
    cin.get(sir.expresie,300);*/
    char sir[300];
    cin.get(sir,300);
    Transformare_Expresie(sir);
    Stive(sir_prelucrat);
    //for(int i=0; i<=poz; i++)
    //cout<<sir_prelucrat[i]<<endl;
    //functie sir;
    //cin.get(sir.expresie,300);
    //for(int i=0; i<=strlen(sir.expresie); i++)
    //cout<<Op[i]<<' '<<Oper[i]<<endl;
    //EvaluareExpresie(sir);

    return 0;
}
