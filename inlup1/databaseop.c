#include "databaseop.h"


FILE *database = NULL;
Goods *list = NULL;


/*************************************************
**                Print messages                **
*************************************************/

// Warning message for database = NULL
void print_noDB_warning(){
    puts("****************************************");
    puts("****************************************");
    puts("********* W A R N I N G ! ! ! **********");
    puts("****** NO DATABASE FILE IS OPEN! *******");
    puts("** LOAD/CREATE A DATABASE FILE BEFORE **");
    puts("************ YOU CONTINUE! *************");
    puts("****************************************");
    puts("****************************************");
}

// Print entry
void print_cursor_entry(Goods *cursor){
  puts("***********************");
  printf("Name: %s\n", cursor->name);
  printf("Description: %s\n", cursor->description);
  printf("Price: %d kr\n", cursor->price);
  printf("Location: %c%d\n", cursor->shelf, cursor->location);
  printf("Amount: %d\n", cursor->amount);
  puts("***********************");
}


/*************************************************
**               Helper functions               **
*************************************************/

// Input line reader
void readLine(char *dest, int n, FILE *database){
  fgets(dest, n, database);
  int len = strlen(dest);
  if(dest[len-1] == '\n'){
    dest[len-1] = '\0';
  }
  else if(dest[len-2] == '\r'){
    dest[len-2] = '\0';
  }
}

// Check if string contains numbers
int string_isalpha(char *buffer){
  int count;
  // Return 1 if string contains less than 1 characters
  if(strlen(buffer) < 1){
    return 1;
  }
  // Return 1 if there are no numbers present
  for (count = 0; buffer[count] != '\0'; ++count){
    if(isdigit(buffer[count]) != 0){
      return 1;
    }
  }
  return 0;
}

// Check if string contains alphabetical chars
int string_isdigit(char *buffer){
  int count;
  // Return 1 if string contains less than 1 characters
  if(strlen(buffer) < 1){
    return 1;
  }
  // Return 1 if there are no alphabetical chars present
  for (count = 0; buffer[count] != '\0'; ++count){
    if(isdigit(buffer[count]) == 0){
      return 1;
    }
  }
  return 0;
}

void enter_name(char *buffer){
  int count;
  printf("\nEnter name: ");
  readLine(buffer, 128, stdin);
  while(string_isalpha(buffer) == 1){
    puts("\n\"Name\" must be longer than 1 char and alphabetical.\n");
    printf("Enter name again: ");
    readLine(buffer, 128, stdin);
  }
  for(count = 0; count < strlen(buffer); ++count){
    buffer[count] = tolower(buffer[count]);
  }
}

void enter_description(char *buffer){
  printf("Enter description: ");
  readLine(buffer, 128, stdin);
  while(string_isalpha(buffer) == 1){
    puts("\n\"Description\" must be longer than 0 chars and alphabetical.\n");
    printf("Enter description again: ");
    readLine(buffer, 128, stdin);
  }
}

void enter_price(char *buffer){
  printf("Enter price: ");
  readLine(buffer, 128, stdin);
  while(string_isdigit(buffer) == 1){
    puts("\n\"Location\" must be longer than 0 chars and numeric.\n");
    printf("Enter price again: ");
    readLine(buffer, 128, stdin);
  }
}

void enter_shelf(char *buffer){
  printf("Enter shelf: ");
  readLine(buffer, 128, stdin);
  while(strlen(buffer) > 1 || isalpha(buffer[0]) == 0){
    puts("\n\"Shelf\" must be a single alphabetical character.\n");
    printf("Enter shelf again: ");
    readLine(buffer, 128, stdin);
  }
  buffer[0] = toupper(buffer[0]);
}

void enter_location(char *buffer){
  printf("Enter location: ");
  readLine(buffer, 128, stdin);
  while(string_isdigit(buffer) == 1){
    puts("\n\"Location\" must be longer than 0 chars and numeric.\n");
    printf("Enter location again: ");
    readLine(buffer, 128, stdin);
  }
}

void enter_amount(char *buffer){
  printf("Enter amount: ");
  readLine(buffer, 128, stdin);
  while(string_isdigit(buffer) == 1){
    puts("\n\"Location\" must be longer than 0 chars and numeric.\n");
    printf("Enter location again: ");
    readLine(buffer, 128, stdin);
  }
}

// Compare latest entrys names and shelf/location with database
int entry_compare(Goods *newGoods){
  // Comparison bits for different compare patterns
  int compareName;
  int compareShelf;
  int compareLocation;
  Goods *cursor;
  cursor = list;

  // If no list (database) is present, return 0 (exit)
  if(list == NULL){
    return 0;
  }

  while(cursor != NULL){
    compareName = strcmp(newGoods->name, cursor->name);
    if(newGoods->shelf == cursor->shelf){
      compareShelf = 0;
    }
    else{
      compareShelf = 1;
    }
    if(newGoods->location == cursor->location){
      compareLocation = 0;
    }
    else{
      compareLocation = 1;
    }
    // If different name is found on same shelf/location, return 1
    if(compareName != 0 && compareShelf == 0 && compareLocation == 0){
      return 1;
    }
    if(compareName == 0 && compareShelf == 0 && compareLocation == 0){
      return 2;
    }
    cursor = cursor->next;
  }
  return 0;
}

void undoGoods_copy(Goods *cursor, Action_t undoGoods){
  if(undoGoods != NULL){
    undoGoods->copy = NULL;
    undoGoods->copy = malloc(sizeof(struct goods));
    memcpy(undoGoods->copy, cursor, sizeof(struct goods));
  }
}

// Updates the amount on an entry
void entry_update_amount(Goods *newGoods, Action_t undoGoods){
  // Comparison bits for different compare patterns
  int compareName;
  int compareShelf;
  int compareLocation;
  Goods *cursor;
  int i = 0;

  cursor = list;

  undoGoods->type = 3;

  while(i != 1){
    compareName = strcmp(newGoods->name, cursor->name);
    if(newGoods->shelf == cursor->shelf){
      compareShelf = 0;
    }
    else{
      compareShelf = 1;
    }
    if(newGoods->location == cursor->location){
      compareLocation = 0;
    }
    else{
      compareLocation = 1;
    }
    if(compareName == 0 && compareShelf == 0 && compareLocation == 0){
      undoGoods_copy(cursor, undoGoods);
      cursor->amount = cursor->amount + newGoods->amount;
      i = 1;
    }
    cursor = cursor->next;
  }
  undoGoods->merch = newGoods;
}

// Removes item from list
void delete_entry(Goods *cursor, Goods *prev){
  if(prev == NULL){
    // Delete first item
    list = cursor->next;
    free(cursor);
  }
  else{
    prev->next = cursor->next;
  }
}


/*************************************************
**     External database-related functions      **
*************************************************/

// Open user specific DB file given on program init
void openUserDB(char *argv[]){
  // Read external DB file
  char *filename = argv[1];
  puts("\n=======================================");
  printf("\nLoading database \"%s\"...\n", filename);
  puts("=======================================\n");
  database = fopen(filename, "r+");
  // Reading saved DB file into memory
  readDB();
  // Fulshing saved DB file
  freopen(filename, "w", database);
}

// Gives choice between loading a file or creating a new file
void chooseOpUI(){
  int choice = -1;
  while(choice != 0){
    puts("1. Load database");
    puts("2. New database");
    puts("0. Exit program");
    printf("\nTime to choose: ");
    scanf("%d", &choice);
    // Clear stdin
    while(getchar() != '\n');
    switch(choice){

    case 1:
      // Load existing DB file
      if(openDB() != 1){
	// Reset choice
	choice = -1;
      }
      else{
	// Exit while
	choice = 0;
      }
      break;

    case 2:
      // Create a new DB file
      if(createDB() != 1){
	// Reset choice
	choice = -1;
      }
      else{
	// Exit while
	choice = 0;
      }
      break;

    case 0:
      // Exit inventory manager
      exit_program();
      break;

    default:
      // Misc. error, try again
      puts("\nCould not parse choice, please try again.\n");
    }
  }
}

// Creates new external DB file
int createDB(){
  char newDB[128];
  printf("Create new database file: ");
  // Read users input for new DB file name
  scanf("%s", &newDB[0]);

  // Check if DB file exists in the directory
  if(access(newDB, F_OK) != -1){
    database = NULL;
    print_noDB_warning();
    puts("\n=======================================");
    printf("Database file \"%s\" already exists!\n", newDB);
    puts("=======================================\n");
    return 0;
  }
  else{
    // Create new DB with users input name
    puts("\n=======================================");
    printf("Loading database \"%s\"...\n", newDB);
    puts("=======================================");
    database = fopen(newDB, "w+");
    return 1;
  }
}

// Opens existing external DB file
int openDB(){
  char savedDB[128];
  printf("Open database file: ");
  // Read users input for new DB file name
  scanf("%s", &savedDB[0]);

  // Check if DB file exists in the directory
  if(access(savedDB, F_OK) != -1){
    // Load existing database file
    puts("\n=======================================");
    printf("Loading database \"%s\"...\n", savedDB);
    puts("=======================================\n\n");
    // Opening saved DB file
    database = fopen(savedDB, "r+");
    // Reading saved DB file into memory
    readDB();
    // Fulshing saved DB file
    freopen(savedDB, "w", database);
    return 1;
  }
  else{
    database = NULL;
    print_noDB_warning();
    puts("\n=======================================");
    printf("Database file \"%s\" does not exist!\n", savedDB);
    puts("=======================================\n");

    return 0;
  }
}

// Read external database into memory
void readDB(){
  char buffer[128];
  while(!(feof(database))){
    Goods *newGoods = malloc(sizeof(struct goods));
    readLine(buffer, 128, database);
    newGoods->name = malloc(strlen(buffer) + 1);
    strcpy(newGoods->name, buffer);

    readLine(buffer, 128, database);
    newGoods->description = malloc(strlen(buffer) + 1);
    strcpy(newGoods->description, buffer);

    readLine(buffer, 128, database);
    newGoods->price = atoi(buffer);

    readLine(buffer, 128, database);
    newGoods->shelf = buffer[0];

    readLine(buffer, 128, database);
    newGoods->location = atoi(buffer);

    readLine(buffer, 128, database);
    newGoods->amount = atoi(buffer);

    newGoods->next = list;
    list = newGoods;
    //free(newGoods);
  }
}

// Copies current list in memory to external DB file
void saveToFile(){
  Goods *cursor;
  // setBit for file writing protocol
  int saveSetBit = 1;
  cursor = list;
  if(cursor != NULL){
    puts("\nSaving database...\n");
    while(cursor != NULL){
      // Newly created DB file saving profile
      if(saveSetBit == 1){
	fprintf(database, "%s\n%s\n%d\n%c\n%d\n%d",
		cursor->name,
		cursor->description,
		cursor->price,
		cursor->shelf,
		cursor->location,
		cursor->amount);
	saveSetBit = 0;
      }
      // Old DB file saving profile 
      else{
	fprintf(database, "\n%s\n%s\n%d\n%c\n%d\n%d",
		cursor->name,
		cursor->description,
		cursor->price,
		cursor->shelf,
		cursor->location,
		cursor->amount);
      }
      cursor = cursor->next;
    }
  }
  // Check if a database is loaded
  if(database != NULL){
    // Close external DB file
    fclose(database);
    // Reset list
    list = NULL;
  }
}


/*************************************************
**   Main internal database-related functions   **
*************************************************/

// Adds a new entry in the struct and copies to global list
void new_entry(Action_t undoGoods){
  char buffer[128];
  Goods *newGoods = malloc(sizeof(struct goods));

  enter_name(buffer);
  newGoods->name = malloc(strlen(buffer) + 1);
  strcpy(newGoods->name, buffer);

  enter_description(buffer);
  newGoods->description = malloc(strlen(buffer) + 1);
  strcpy(newGoods->description, buffer);

  enter_price(buffer);
  newGoods->price = atoi(buffer);

  enter_shelf(buffer);
  newGoods->shelf = buffer[0];

  enter_location(buffer);
  newGoods->location = atoi(buffer);

  enter_amount(buffer);
  newGoods->amount = atoi(buffer);

  // User interface
  new_entryUI(newGoods, undoGoods);
}

 // User interface for saving/discarding/editing entry
void new_entryUI(Goods *newGoods, Action_t undoGoods){
  int choice = -1;
  Goods *cursor;

  cursor = newGoods;
  // Receipt
  print_cursor_entry(cursor);
  cursor = NULL;

  while(choice != 0){
    // Check if theres a different name on the same shelf/location
    if(entry_compare(newGoods) == 1){
      puts("===========================================================");
      printf("Theres already a different type of item on location \"%c%d\"!\n",
	     newGoods->shelf,
	     newGoods->location);
      puts("===========================================================");
    }
    // UI starts
    puts("___________________________");
    puts("What would you like to do?:");
    puts("1. Save entry");
    puts("2. Edit entry");
    puts("3. Discard entry");
    printf("\nTime to choose: ");
    scanf("%d", &choice);
    switch(choice){
     
      // Save entry
    case 1:
      // Check if theres a different name on the same shelf/location
      if(entry_compare(newGoods) == 1){
	choice = -1;
	break;
      }
      if(entry_compare(newGoods) == 2){
	entry_update_amount(newGoods, undoGoods);

	puts("==============");
	puts("Saving item...");
	puts("==============");
	// Exit while loop
	choice = 0;
	break;
      }
      else{
	puts("==============");
	puts("Saving item...");
	puts("==============");
	newGoods->next = list;
	list = newGoods;

	undoGoods->type = 1;
	undoGoods->merch = newGoods;
	// Exit while loop
	choice = 0;
	break;
      }

      // Edit entry
      int i = 0;
    case 2:
      while(i != 1){
	Goods *cursor;  
	Action_t undoGoods = NULL;

	cursor = newGoods;
	edit_entryUI(cursor, undoGoods);
	newGoods = cursor;
	i = 1;
      }
      newGoods->next = list;
      list = newGoods;
      choice = 0;
      break;

      // Discard entry
    case 3:
      free(newGoods);
      puts("==================");
      puts("Discarding item...");
      puts("==================");
      // Exit while loop
      choice = 0;
      break;

    default:
      // Misc. error, try again
      puts("\nCould not parse choice, please try again.\n");
      // Reset choice
      choice = -1;
    }
  }
}

// Lists the inventory
void list_entry(int menuChoice, Action_t undoGoods){
  char choice[128];
  int pageCount = 0;
  int enumerate = 1;
  Goods *cursor;

  cursor = list;

  puts("============================");
  ++pageCount;
  while(cursor != NULL && enumerate <= 20){
    printf("%d. %s\n", enumerate, cursor->name);
    ++enumerate;
    cursor = cursor->next;
  }
  puts("============================");  
  
  // User interface
  while(choice != 0){
    puts("___________________________");
    puts("Please choose an operation:\n");
    puts("N/n. Next page");

    if(menuChoice == 1){
      printf("1-%d. Choose entry for deletion\n", enumerate - 1);
    }
    if(menuChoice == 2){
      printf("1-%d. Choose entry for editing\n", enumerate - 1);
    }
    if(menuChoice == 3){
      printf("1-%d. Choose entry for detailed view\n", enumerate - 1);
    }

    puts("0. Exit menu");
    printf("\nTime to choose: ");
    scanf("%s", choice);
    // Clear stdin
    while(getchar() != '\n');

    if(choice[0] == '0'){
      break;
    }

    // Prompt error message if receiving longer than 2 chars
    if(strlen(choice) > 2){
      puts("\nYou may only use two characters!");
    }

    // Select item in list through function show_item()
    if(isdigit(choice[0])){
      // If choice is bigger than actual listed items, prompt error message
      if(atoi(choice) > enumerate - 1){
	printf("\nItem number must be between \"1\" and \"%d\"!\n", enumerate - 1);
      }
      else{
	entry_selectUI(menuChoice, choice, pageCount, undoGoods);
	break;
      }
    }
    // Reset cursor and page counter when reaching NULL in list
    else if(cursor == NULL){
      list_entry(menuChoice, NULL);
      break;
    }
    // Go to next page of items
    else if(choice[0] == 'n' || choice[0] == 'N'){
      ++pageCount;
      enumerate = 1;
      puts("============================");
      while(cursor != NULL && enumerate <= 20){
	printf("%d. %s\n", enumerate, cursor->name);
	++enumerate;
	cursor = cursor->next;
      }
      puts("============================");  
    }
    else{
      // Misc. error, try again
      puts("\nCould not parse choice, please try again.\n");
    }
  }
}

// Prints the user specified entry in inventory, and sends to correct destination
void entry_selectUI(int menuChoice, char *choice, int pageCount, Action_t undoGoods){
  int item;
  Goods *cursor;
  Goods *prev = NULL;

  cursor = list;

  item = pageCount - 1;
  // Pages times items per page
  item = item * 20;
  // Result plus item chosen by user
  item = item + atoi(choice);

  while(item > 1){
    --item;
    prev = cursor;
    cursor = cursor->next;
  }

  if(menuChoice == 1){
  puts("\n\n\n\n***********************");
  puts("***** Item chosen *****");
  puts("**** for  deletion ****");
  }
  if(menuChoice == 2){
  puts("\n\n\n\n***********************");
  puts("***** Item chosen *****");
  puts("***** for editing *****");
  }
  if(menuChoice == 3){
  puts("\n\n\n\n***********************");
  puts("***** Item listed *****");
  }
  print_cursor_entry(cursor);

  if(menuChoice == 1){
    delete_entryUI(cursor, prev, undoGoods);
  }
  if(menuChoice == 2){
    edit_entryUI(cursor, undoGoods);
  }
}

// Deletes user specified entry
void delete_entryUI(Goods *cursor, Goods *prev, Action_t undoGoods){
  int delChoice = -1;

  // User interface
  while(delChoice != 0){
    puts("_____________________________________");
    puts("Would you like to delete this entry?:\n");
    puts("1. Yes");
    puts("2. No");
    printf("\nTime to choose: ");
    scanf("%d", &delChoice);
    // Clear stdin
    while(getchar() != '\n');
    switch(delChoice){

    case 1:
      // Delete entry
      if(undoGoods != NULL){
	undoGoods->copy = malloc(sizeof(struct goods));
	memcpy(undoGoods->copy, cursor, sizeof(struct goods));
	undoGoods->type = 2;
      }
      delete_entry(cursor, prev);
      puts("===============");
      puts("Item deleted...");
      puts("===============");
      // Return to main menu
      delChoice = 0;
      break;

    case 2:
      // Return to main menu
      delChoice = 0;
      break;

    default:
      // Misc. error, try again
      puts("\nCould not parse choice, please try again.\n");
    }
  }
}

// User interface for editing of an entry
void edit_entryUI(Goods *cursor, Action_t undoGoods){
  char buffer[128];
  int editChoice;
  Goods *prev = NULL;
  Goods *newGoods = malloc(sizeof(struct goods));

  undoGoods_copy(cursor, undoGoods);

  while(editChoice != 0){
    puts("___________________________");
    puts("What would you like to edit?\n");
    puts("1. Name");
    puts("2. Description");
    puts("3. Price");
    puts("4. Shelf");
    puts("5. Location");
    puts("6. Amount");
    puts("0. Exit menu");
    printf("\nTime to choose: ");
    scanf("%d", &editChoice);
    // Clear stdin
    while(getchar() != '\n');
    switch(editChoice){

      // Name
    case 1:
      printf("\nName: %s\n", cursor->name);
      puts("____________________________");
      enter_name(buffer);
      strcpy(cursor->name, buffer);
      // Return main menu
      editChoice = 0;
      break;

      // Description
    case 2:
      printf("\nDescription: %s\n", cursor->description);
      puts("____________________________");
      enter_description(buffer);
      strcpy(cursor->description, buffer);
      // Return main menu
      editChoice = 0;
      break;

      // Price
    case 3:
      printf("\nPrice: %d\n", cursor->price);
      puts("____________________________");
      enter_price(buffer);
      cursor->price = atoi(buffer);
      // Return to main menu
      editChoice = 0;
      break;

      // Shelf
    case 4:
      printf("\nShelf: %c\n", cursor->shelf);
      puts("____________________________");
      enter_shelf(buffer);

      // Copy data for comparison with database entries
      newGoods->name = cursor->name;
      newGoods->description = cursor->description;
      newGoods->price = cursor->price;
      newGoods->shelf = buffer[0];
      newGoods->location = cursor->location;
      newGoods->amount = cursor->amount;

      if(entry_compare(newGoods) == 2){
	entry_update_amount(newGoods, undoGoods);
	delete_entry(cursor, prev);
      }
      while(entry_compare(newGoods) == 1){
	puts("===========================================================");
	printf("Theres already a different type of item on location \"%c%d\"!\n",
	       newGoods->shelf,
	       newGoods->location);
	puts("===========================================================");
	enter_shelf(buffer);
	newGoods->shelf = buffer[0];
	entry_compare(newGoods);
      }
      cursor->shelf = buffer[0];
      // Return to main menu
      editChoice = 0;
      break;

      // Location
    case 5:
      printf("\nLocation: %d\n", cursor->location);
      puts("____________________________");
      enter_location(buffer);

      // Copy data for comparison with database entries
      newGoods->name = cursor->name;
      newGoods->description = cursor->description;
      newGoods->price = cursor->price;
      newGoods->shelf = cursor->shelf;
      newGoods->location = atoi(buffer);
      newGoods->amount = cursor->amount;

      if(entry_compare(newGoods) == 2){
	entry_update_amount(newGoods, undoGoods);
	delete_entry(cursor, prev);
      }

      while(entry_compare(newGoods) == 1){
	puts("===========================================================");
	printf("Theres already a different type of item on location \"%c%d\"!\n",
	       newGoods->shelf,
	       newGoods->location);
	puts("===========================================================");
	enter_location(buffer);
	newGoods->location = atoi(buffer);
	entry_compare(newGoods);
      }
      cursor->location = atoi(buffer);
      // Return to main menu
      editChoice = 0;
      break;

      // Amount
    case 6:
      printf("\nAmount: %d\n", cursor->amount);
      puts("____________________________");
      enter_amount(buffer);
      cursor->amount = atoi(buffer);
      // Return to main menu
      editChoice = 0;
      break;

    default:
      // Misc. error, try again
      puts("\nCould not parse choice, please try again.\n");
    }
    puts("\n\n\n\n***********************");
    puts("*** Item after edit ***");
    print_cursor_entry(cursor);
  }
  if(undoGoods != NULL){
    undoGoods->merch = cursor;
    undoGoods->type = 3;
  }
}

void undo_entry(Action_t undoGoods){
  Goods *prev = NULL;
  // Comparison bits for different compare patterns
  int compareName;
  int compareShelf;
  int compareLocation;
  Goods *cursor;
  int i = 0;

  switch(undoGoods->type){

    // Undo last new entry
  case 1:
    delete_entry(undoGoods->merch, prev);
    puts("\n*************************");
    puts("Latest \"new entry\" undone");
    puts("*************************");
    break;

    // Undo last entry deletion
  case 2:
    undoGoods->copy->next = list;
    list = undoGoods->copy;
    puts("\n****************************");
    puts("Latest \"delete entry\" undone");
    puts("****************************");
    break;

    // Undo last edited entry
  case 3:
  cursor = list;

  while(i != 1){
    compareName = strcmp(undoGoods->merch->name, cursor->name);
    if(undoGoods->merch->shelf == cursor->shelf){
      compareShelf = 0;
    }
    else{
      compareShelf = 1;
    }
    if(undoGoods->merch->location == cursor->location){
      compareLocation = 0;
    }
    else{
      compareLocation = 1;
    }
    if(compareName == 0 && compareShelf == 0 && compareLocation == 0){
      prev->next = cursor->next;
      i = 1;
    }
    prev = cursor;
    cursor = cursor->next;
  }
    undoGoods->copy->next = list;
    list = undoGoods->copy;
    puts("\n**************************");
    puts("Latest \"edit entry\" undone");
    puts("**************************");
    break;

    // Base case - nothing to undo
  case 0:
    puts("\n**************************");
    puts("There is no action to undo");
    puts("**************************");
    break;
  }
  undoGoods->type = 0;
}

// Shuts down the program
int exit_program(){
  puts("\nExiting...");
  puts("Have a pleasant day!\n");
  exit(1);
  return 0;
}
