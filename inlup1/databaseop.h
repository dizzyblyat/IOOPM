#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>

#ifndef __databaseop_h__
#define __databaseop_h__

/*************************************************
**                   Datatypes                  **
*************************************************/
struct goods{
  char *name;
  char *description;
  int price;
  char shelf;
  int location;
  int amount;
  struct goods *next;
};

typedef struct goods Goods;

struct action_t{
  int type; // nothing = 0, add = 1, remove = 2, edit = 3
  Goods *merch;
  Goods *copy;
};

typedef struct action_t *Action_t;

// Print messages
void print_noDB_warning();
void print_cursor_entry();
// Helper functions
void readLine(char *dest, int n, FILE *database);
int string_isalpha(char *buffer);
int string_isdigit(char *buffer);
void enter_name(char *buffer);
void enter_description(char *buffer);
void enter_price(char *buffer);
void enter_shelf(char *buffer);
void enter_location(char *buffer);
void enter_amount(char *buffer);
int entry_compare(Goods *newGoods);
void undoGoods_copy(Goods *cursor, Goods undoGoods);
void entry_update_amount(Goods *newGoods, Action_t undoGoods);
void delete_entry(Goods *cursor, Goods *prev);
// External DB functions
void openUserDB(char *argv[]);
void chooseOpUI();
int createDB();
int openDB();
void readDB();
void saveToFile();
// Internal DB functions
void new_entry(Action_t undoGoods);
void new_entryUI(Goods *newGoods, Action_t undoGoods);
void list_entry(int menuChoice, Action_t undoGoods);
void entry_selectUI(int menuChoice, char *choice, int pageCount, Action_t undoGoods);
void delete_entryUI(Goods *cursor, Goods *prev, Action_t undoGoods);
void edit_entryUI(Goods *cursor, Action_t undoGoods);
void undo_entry(Action_t undoGoods);
int exit_program();

#endif
