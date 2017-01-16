#define ROW_COUNT (100)
#define COL_COUNT (100)
#define REPORTING_FREQUENCY	(1000)
#include <stdio.h>
#include <assert.h>

typedef int bool;

int main();
int *runScenario(int countSteps);
int *updateSpace(int iTime);
void updateCell(int *currentCells, int *nextCells, int row, int col);
int computeLiveness(int isCurrentCellAlive, int countSurroundingAlive);
int countSurroundingLiveCells(int *currentCells, int row, int col);
int indexForRowCol(int row, int col);
void determineCurrentAndNextCells(int iTime, int **pArrayCurrentCells, int **pArrayNextCells);
bool isLegalCoord(int row, int col);
void readState(char *strFileName, int *arrayCurrentCells);
void  writeState(char *strFileName, int *arrayCurrentCells);
bool isLegalTextualEncoding(char cEncoded);
int cellValueForTextualEncoding(char cEncoded);
char textualEncodingForCellValue(int cellValue);
void exit(int);

# define CELL_COUNT	(ROW_COUNT * COL_COUNT)

int bufferEven[CELL_COUNT];
int bufferOdd[CELL_COUNT];

int main(int argc, char *argv[])
{
  readState("input.txt", bufferEven); 
  int *finalState = runScenario(999);	
  writeState("output.txt", finalState); 
}


/*	PreConditions  : countSteps is an integer contain how many time we want the array to be updated 
*	PostConditions : return arrayLatestState which contain the result contain only '*' from the input file
*					 print out step on frauency
*/
int *runScenario(int countSteps)
{
    int *arrayLatestState = NULL;

	for (int iTime  = 0; iTime < countSteps; iTime ++)
		{
		arrayLatestState = updateSpace(iTime);
		
		if (iTime % REPORTING_FREQUENCY == 0)
			printf("Step %d\n", iTime);
		}
	return(arrayLatestState);
}


/*	PreConditions  : iTime is an integer
*	PostConditions : return the cells update in the final iteration
*/
*int *updateSpace(int iTime)
{
	int *currentCells;
	int *nextCells;
	determineCurrentAndNextCells(iTime, &currentCells, &nextCells);

	for (int row = 0; row < ROW_COUNT; row++)
		for (int col = 0; col < COL_COUNT; col++)
		  {
		    // update nextCells
			updateCell(currentCells, nextCells, row, col);	// another (less efficient, but still relatively clean) alternative would be to test here if it's an even or odd step, and to call "update cell" with different swapps first two arguments based on the outcome
		  }

        return(nextCells);  // the cells updated in the final iteration
}


/*	PreConditions  : currentCells and nextCells are the pointer for the current cell and next cell
*					 the row and col coordinate is valid
*	PostConditions : nextCells has the value for the next cell for the given currentCells
*/
void updateCell(int *currentCells, int *nextCells, int row, int col)
{
	int countSurroundingAlive = countSurroundingLiveCells(currentCells, row, col);
	int iCurrentCell = indexForRowCol(row, col);
	bool isCurrentCellAlive = currentCells[iCurrentCell];
	
	nextCells[iCurrentCell] = computeLiveness(isCurrentCellAlive, countSurroundingAlive);
}


/*	PreConditions  : None
*	PostConditions : checked if the countSurroundingAlive is 2 or 3 if the current cell is alive
*/
int computeLiveness(int isCurrentCellAlive, int countSurroundingAlive)
{
	if (isCurrentCellAlive)
		return (countSurroundingAlive >= 2 && countSurroundingAlive <= 3);
	else
		return (countSurroundingAlive == 3);
}


/*	PreConditions  : currentCells and row and col must be valid
*	PostConditions : all of the surrounding cell of the given coordinate is return id they are alive
*/
int countSurroundingLiveCells(int *currentCells, int row, int col)
{
	int countSurroundingAlive = 0;
	
	for (int deltaRow = -1; deltaRow <= 1; deltaRow++)
		for (int deltaCol = -1; deltaCol <= 1; deltaCol++)
			{
			  if (deltaRow == 0 && deltaCol == 0)   // don't count the current cell of focus in the count of live neighbors!
			    continue;

			  int checkingRow = row + deltaRow;
			  int checkingCol = col + deltaCol;
  			  if (isLegalCoord(checkingRow, checkingCol))
				{
				if (currentCells[indexForRowCol(checkingRow, checkingCol)] == 1)
					countSurroundingAlive++;
				}
			}
	
	return(countSurroundingAlive);
}

/*	PreConditions  : None
*	PostConditions : checkd if the row and col is valid 
*					 return true if yes, return false otherwise
*/
bool isLegalCoord(int row, int col)
{
	return ((row >= 0 && row < ROW_COUNT) &&
			(col >= 0 && col < COL_COUNT));
}


/*	PreConditions  : None
*	PostConditions : return the value of index
*/
int indexForRowCol(int row, int col)
{
	return row * ROW_COUNT + col;
}


/*	PreConditions  : iTime is an integer
*					 pArrayCurrentCells is an pointer to an integer
*					 pArrayNextCells is an pointer to an integer
*	PostConditions : pArrayCurrentCells has the value for the CurrentCells 
*					 pArrayNextCells has the value for the next Cell 
*/
void determineCurrentAndNextCells(int iTime, int **pArrayCurrentCells, int **pArrayNextCells)
{
	bool isEvenStep = (iTime % 2 == 0);

	// given that c doesn't support multiple return values, perhaps this is the least bad way of doing this
	*pArrayCurrentCells  = isEvenStep ? bufferEven : bufferOdd;
	*pArrayNextCells = isEvenStep ? bufferOdd : bufferEven;
}

/*	PreConditions  : arrayCurrentCells is a array of integer
*					 strFileName can be found in at the cureent working diretory
*	PostConditions : exit the program if the file not found with file name "strFileName" 
*					 all index in the strFileName must been visited and store at the arrayCurrentCells if it is '*'
*/
void readState(char *strFileName, int *arrayCurrentCells)
{
  FILE *file = fopen(strFileName, "r");

  if (file == NULL)
    {
      fprintf(stderr, "Error: could not open file '%s'.  Aborting.\n", strFileName);
      exit(1);
    }

  for (int row = 0; row < ROW_COUNT; row++)
    {
    for (int col = 0; col < COL_COUNT; col++)
      {
        assert(!feof(file));
		char cEncoded = fgetc(file);

		assert(isLegalTextualEncoding(cEncoded));
		arrayCurrentCells[indexForRowCol(row, col)] = cellValueForTextualEncoding(cEncoded);	
      }
      char newLine = fgetc(file);
      assert(newLine == '\n');
    }
  fclose(file);
}


/*	PreConditions  : strFileName is a pointer of char
*					 arrayCurrentCells is a pointer of integer
*	PostConditions : a file with file name is strFileName is created if not existed before, otherwise override it
*					 data from the arrayCurrentCells is wrote in to the strFileName file
*					 the file is closed after this operation 
*/
void  writeState(char *strFileName, int *arrayCurrentCells)
{
  FILE *file = fopen(strFileName, "w");

  for (int row = 0; row < ROW_COUNT; row++)
    {
    for (int col = 0; col < COL_COUNT; col++)
      {
		fputc(textualEncodingForCellValue(arrayCurrentCells[indexForRowCol(row, col)]), file);
      }

    fputc('\n', file);    // row separator
    }
  fclose(file);
}


/*	PreConditions  : cellValue is an integer
*	PostConditions : return ' ' if the cellValue is 0 else return '*'
*/
char textualEncodingForCellValue(int cellValue)
{
  if (cellValue == 0)
    return ' ';
  else
    return '*';
}


/*	PreConditions  : cEncoded is a char
*	PostConditions : the condition of if the cell is encoded is return
*/
bool isLegalTextualEncoding(char cEncoded)
{
  return(cEncoded == ' ' || cEncoded == '*');
}

/*	PreConditions  : cEncoded is a char
*	PostConditions : checked if the cEncoded is a '*'
*/
int cellValueForTextualEncoding(char cEncoded)
{
  return cEncoded == '*' ? 1 : 0;
}	

