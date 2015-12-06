# Makefile for Inventory Manager

.SILENT:
goods: main.o databaseop.o
	gcc -g -D_GNU_SOURCE -std=c11 main.c databaseop.c databaseop.h -o goods
	echo Done!
main.o: main.c databaseop.h
	gcc -g -c -D_GNU_SOURCE -std=c11 -Wall main.c
	echo main.c compilation complete.
databaseop.o: databaseop.c databaseop.h
	gcc -g -c -D_GNU_SOURCE -std=c11 -Wall databaseop.c
	echo databaseop.c compilation complete.

clean:
	rm *.o
	rm goods
