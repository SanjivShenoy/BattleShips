import java.util.Scanner;
import java.util.Random;

public class BattleShips
{

   static void ShowOcean(char a[][], int length,int breadth)
  {

        System.out.print("  ");
        for(int i=0;i<breadth;i++)
        {
          System.out.print(i);
        }
        System.out.println("  ");

        for(int i=0;i<length;i++)
        {
          System.out.print(i+"|");
          for(int j=0;j<breadth;j++)
          {
            if(a[i][j]!='@')
            System.out.print(a[i][j]);
            else
            System.out.print(' ');
          }
          System.out.println("|"+i);
        }
        System.out.print("  ");
        for(int i=0;i<breadth;i++)
        {
          System.out.print(i);
        }
        System.out.println("  ");

  }

	public static void main(String args[]) throws java.io.IOException
	{
    Scanner scan = new Scanner(System.in);
    int length,breadth,NumShips,flag=0;
    System.out.println();
    System.out.println("**** Welcome to the BattleShips Game ****");
    System.out.println();

    System.out.println("Rules: Deploy your ships in the Ocean.\n\tThe Computer will also Deploy ships in the Ocean.\n\tThen you attack each other's ships.\n\tThe winner destroys all the opponent's ships.");
    System.out.println();

    do {
      System.out.print("Enter the number of rows and columns of the Ocean Grid separated by space:");
       length = scan.nextInt();
       breadth = scan.nextInt();

      if(length*breadth < 2)
      {
        System.out.println("Area must be greater than 1");
        System.out.println();
      }
    } while (length*breadth < 2);

    System.out.println();

    do {
      System.out.print("Enter the number of ships to play with:");
       NumShips = scan.nextInt();

       if(NumShips > ((length*breadth)/2))
       {
         System.out.println("Number of Ships must be <= Area/2");
         System.out.println();
       }

    }while (NumShips > ((length*breadth)/2));


    System.out.println();
    System.out.println("Right now, the sea is empty.");
    System.out.println();

    char a[][] = new char[length][breadth];

    for(int i=0;i<length;i++)
      for(int j=0;j<breadth;j++)
      {
        a[i][j]=' ';
      }

    ShowOcean(a,length,breadth);



    //char c = (char)System.in.read();
    System.out.println();
    System.out.println("Let the Game Begin:");
    System.out.println("Deploy your Ships:");
    System.out.println();
    for(int i=0;i<NumShips;i++)
    {
    int X = -1,Y = -1;
    while(X<0 || X>=length)
    {
     if(X!=-1)
     {
       System.out.println("Invalid Input");
     }
     System.out.print("Enter the X Co-ordinate of Ship" + (i+1) +":");
     X = scan.nextInt();
    }
    while(Y<0 || Y>=breadth)
    {
      if(Y!=-1)
    {
      System.out.println("Invalid Input");
    }
    System.out.print("Enter the Y Co-ordinate of Ship" + (i+1)+ ":");
    Y = scan.nextInt();
    System.out.println();
    }

    if(a[X][Y] == ' ')
    {
      a[X][Y] = '$';
    }
    else
    {
      i--;
    }

    ShowOcean(a,length,breadth);
    System.out.println("-----------------------");
    System.out.println();
    }


    System.out.println("The Computer is now deploying ships:");
    System.out.println();

    Random rand = new Random();
    for(int i=0;i<NumShips;i++)
    {
      int X,Y;
      X = rand.nextInt(length);
      if(X<0 || X>=length)
      System.out.println("Error in generating random no.");

      Y = rand.nextInt(breadth);
      if(Y<0 || Y>=breadth)
      System.out.println("Error in generating random no.");

      if(a[X][Y] == ' ')
      {
        flag = 1;
        a[X][Y] = '@';
      }
      else
      {
        i--;
      }
      if(flag == 1)
      System.out.println("Ship"+(i+1)+"Deployed");
      flag = 0;

    }
    System.out.println();
    System.out.println("So Let's Play:");
    System.out.println();
    ShowOcean(a,length,breadth);
    System.out.println();

    int UserShips = NumShips, CompShips = NumShips;
  outer: while(UserShips>0 && CompShips>0)
    {
      System.out.println("*** Your Turn ***");
      System.out.println();

      int X = -1,Y = -1;
      while(X<0 || X>=length)
      {
       if(X!=-1)
       {
         System.out.println("Invalid Input");
       }
       System.out.print("Guess X co-ordinate of Computer's ships:");
       X = scan.nextInt();
      }
      while(Y<0 || Y>=breadth)
      {
        if(Y!=-1)
      {
        System.out.println("Invalid Input");
      }
      System.out.print("Guess Y co-ordinate of Computer's ships:");
      Y = scan.nextInt();
      System.out.println();
      }

      if(a[X][Y] == ' ')
      {
        a[X][Y] = 'x';
        System.out.println("Sorry, you missed  {:|");
      }

      else if(a[X][Y] == '$')
      {
        a[X][Y] = 'x';
        System.out.println("Oh no, you sunk your own ship  {:(");
        UserShips--;
        if(UserShips == 0)
        {
          ShowOcean(a,length,breadth);
          System.out.println("--------------------------------");
          System.out.println();
           break outer;
        }
      }

      else if(a[X][Y] == '@')
      {
        a[X][Y] = '!';
        System.out.println("Boom! You sunk the ship!  {:)");
        CompShips--;
        if(CompShips == 0)
        {
          ShowOcean(a,length,breadth);
          System.out.println("----------------------------------");
          System.out.println();
          break outer;
        }
      }

      else if(a[X][Y] == 'x' || a[X][Y]=='!')
      {
        System.out.println("This co-ordinate has already been attacked !");
        continue outer;
      }

      System.out.println();
      System.out.println("*** Computer's Turn ***");


      X = rand.nextInt(length);
      Y = rand.nextInt(breadth);

      while(a[X][Y]=='x' || a[X][Y] == '!')
      {
        X = rand.nextInt(length);
        Y = rand.nextInt(breadth);
      }

      if(a[X][Y] == '$')
      {
        System.out.println("The Computer sunk one of your ships!");
        System.out.println();
        a[X][Y] = 'x';
        UserShips--;
      }

      else if(a[X][Y] == '@')
      {
        System.out.println("The Computer sunk one of its own ships");
        System.out.println();
        a[X][Y] = '!';
        CompShips--;
      }

      else if(a[X][Y] == ' ')
      {
        System.out.println("Computer missed");
        System.out.println();
        a[X][Y] = 'x';
      }

      ShowOcean(a,length,breadth);
      System.out.println("-------------------------------");
      System.out.println();


    }

    if(UserShips == 0)
    System.out.println("Oops! You lost this one. Better luck next time.");

    else if(CompShips == 0)
    System.out.println("Hooray! You win the Battle.");



	}
}
