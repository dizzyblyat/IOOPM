#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#ifndef __main_h__
#define __main_h__

typedef struct node Node;

// Helper functions
void read_line(char *dest, int n, FILE *source);
void create_node(Node **root, char *buffer1, int buffer2);
void index_insert(Node **root, char *buffer1, int buffer2);
void count_spaces(Node *n);
// Main functions
void index_new(FILE *database, Node **root);
void index_print(Node *cursor);
void index_add_word(Node *cursor);
void index_free(Node *cursor);
int bad_input(int argc, char *argv[]);
int file_read(int argc, char *argv[], Node **root);

#endif
