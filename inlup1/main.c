#include "databaseop.h"


int main(int argc, char *argv[]){
  Action_t undoGoods = malloc(sizeof(struct action_t));
  undoGoods->type = 0;

  // Welcome message
  void welcome(){
    puts("Welcome to the Inventory Manager");
    puts("================================\n");
  }
 
  // Check if external DB file was included
  if(argc < 2){
    puts("==============================================");
    puts("No database file included (./goods *filename*)");
    puts("==============================================\n");
    // Create new DB file
    chooseOpUI();
  }
  else{
    // Check if external DB file exists in directory
    if(access(argv[1], F_OK) != -1){
      // Open existing DB file
      openUserDB(argv);
    }
    // If DB file does not exist, create new DB file
    else{
      puts("\n=======================================");
      puts("Included database file does not exist!");
      puts("=======================================\n");
      // Create new DB file
      chooseOpUI();
    }
  }

  int choice = -1;
  // User interface
  while(choice != 0){
    puts("___________________________");
    puts("Please choose an operation:");
    puts("1. New entry");
    puts("2. Delete entry");
    puts("3. Edit entry");
    puts("4. Undo last edit");
    puts("5. List database");
    puts("----------------");
    puts("6. Load database");
    puts("7. New database");
    puts("0. Exit program");
    printf("\nTime to choose: ");
    scanf("%d", &choice);
    // Clear stdin
    while(getchar() != '\n');
    switch(choice){
	
    case 1:
      // New entry
      new_entry(undoGoods);
      // Reset choice bit
      choice = -1;
      break;

    case 2:
      // Delete entry
      list_entry(1, undoGoods);
      // Reset choice bit
      choice = -1;
      break;

    case 3:
      // Edit entry
      list_entry(2, undoGoods);
      // Reset choice bit
      choice = -1;
      break;

    case 4:
      // Undo last edit
      undo_entry(undoGoods);
      // Reset choice bit
      choice = -1;
      break;

    case 5:
      // List DB
      list_entry(3, undoGoods);
      // Reset choice bit
      choice = -1;
      break;

    case 6:
      // Save progress to external DB file
      saveToFile();
      // Open existing DB file
      // Reset choice bit
      choice = -1;
      openDB();
      break;

    case 7:
      // Save progress to external DB file
      saveToFile();
      // Create new DB file
      // Reset choice bit
      choice = -1;
      createDB();
      break;

    case 0:
      // Save progress to external DB file
      saveToFile();
      // Exit inventory manager
      exit_program();
      break;

    default:
      // Misc. error, try again
      puts("\nCould not parse choice, please try again.\n");
    }
  }
  return 0;
}
