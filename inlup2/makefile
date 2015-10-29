# Makefile for Word Counter

CC- gcc -std=c11
CFLAGS= -g -c -Wall
LDFLAGS= -g
OBJECTS= main.o

.SILENT:
wcount:	$(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o wcount
	echo wcount compiled successfully!

main.o: main.c main.h
	$(CC) $(CFLAGS) main.c
	echo main.o compiled successfully!

.PHONY: clean
clean:
	rm main.o
	rm wcount
	echo File cleaning done
