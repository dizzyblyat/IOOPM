#include "main.h"

// Binary tree struct
struct node{
  char *item;
  int freq;
  Node *left;
  Node *right;
};


/*****************************
***    Helper functions    ***
*****************************/
void read_line(char *dest, int n, FILE *source){
  fgets(dest, n, source);
  int len = strlen(dest);
  if (dest[len-1] == '\n'){
    dest[len-1] = '\0';
  }
  if (dest[len-2] == '\r'){
    dest[len-2] = '\0';
  }
}

// Adds new node to binary tree with two children pointing to NULL
void create_node(Node **root, char *buffer1, int buffer2){
  Node *newNode = malloc(sizeof(struct node));
 
  newNode->item = malloc(strlen(buffer1) + 1);
  strcpy(newNode->item, buffer1);
  newNode->freq = buffer2;
  newNode->left = NULL;
  newNode->right = NULL;

  *root = newNode;
}

void index_insert(Node **root, char *buffer1, int buffer2){
  if(*root == NULL){
    create_node(root, buffer1, buffer2);
  }

  if(strcmp(buffer1, (*root)->item) < 0){
    index_insert(&(*root)->left, buffer1, buffer2);
  }
  else if(strcmp(buffer1, (*root)->item) == 0){
    index_add_word(*root);
  }
  else{
    index_insert(&(*root)->right, buffer1, buffer2); 
  }
}

// Counts spaces needed between item and frequency
void count_spaces(Node *cursor){
  int count = 9;
  char space[10];
  char freq[10];
  sprintf(freq, "%d", cursor->freq);
  size_t len1 = strlen(cursor->item);
  size_t len2 = strlen(freq);
   
  strcpy(space, " ");
  count = count - (len1 + len2);
  while(count > 0){
    strcat(space, " ");
    --count;
  }
  printf("%s", space);
}

/*****************************
***     Main functions     ***
*****************************/
void index_new(FILE *database, Node **root){
  char buffer1[32];
  int buffer2 = 0;
  size_t buffSize = 32;

  while(!(feof(database))){
    read_line(buffer1, buffSize, database);
    index_insert(root, buffer1, buffer2);
  }
}

void index_print(Node *cursor){
  if(cursor != NULL){
    index_print(cursor->left);
    printf("%s", cursor->item);
    count_spaces(cursor);
    printf("%d\n", cursor->freq);
    index_print(cursor->right);
  }
}

void index_add_word(Node *cursor){
  int buff = 1;
  buff = cursor->freq + 1;
  cursor->freq = buff;
}

void index_free(Node *cursor){
  if (cursor == NULL){
    return;
  }

  free(cursor->item);
  index_free(cursor->left);
  cursor->left = NULL;
  index_free(cursor->right);
  cursor->right = NULL;

  free(cursor);
  cursor = NULL;
}

int bad_input(int argc, char *argv[]){
  int i = 0;

  while(i < argc){
    char *filename = argv[i];
    // Error messaging
    if(argc < 2){
      puts("You haven't included any database files.");
      puts("How to: ./wcount file_1 file_2 ... file_n");
      return 1;
    }
    if(access(filename, F_OK) == -1 && argc > 1){
      printf("Database file \"%s\" does not exist.\n", filename);
      //puts("One of the database files you've included does not exist.");
      return 1;
    }
    if(access(filename, R_OK) == -1){
      printf("Database file \"%s\" is read-protected.\n", filename);
      return 1;
    }
    else{
      ++i;
    }
  }
  return 0;
}

int file_read(int argc, char *argv[], Node **root){
  // Read the input file
  int i = 1;

  while(i < argc){
    char *filename = argv[i];
    //printf("Filename: %s\n", argv[i]);
    FILE *database = fopen(filename, "r");
    // Creates database
    index_new(database, root);
    fclose(database);
    ++i;
  }
  return 1;  
}

int main(int argc, char *argv[]){
  Node *root = NULL;

  // Check for bad inputs
  if(bad_input(argc, argv) == 1){
    return -1;
  }

  // Read the input files
  file_read(argc, argv, &root);

  // Prints database
  index_print(root);

  // Free memory
  index_free(root);

  return 0;
}
