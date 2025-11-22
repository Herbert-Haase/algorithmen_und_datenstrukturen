# Compiler and flags
CC = g++
CFLAGS = -g -Wall -Wextra -Werror -std=c++23 -pedantic

# Automatically find all source files and corresponding object files
SRC = $(wildcard *.cpp)
OBJ = $(SRC:.cpp=.o)

# Default target: Name executable based on the directory name
EXEC = $(notdir $(CURDIR))

# Build the default target
all: $(EXEC)

# Rule to create the executable
$(EXEC): $(OBJ)
	$(CC) $(CFLAGS) -o $@ $^

# Rule to create object files from source files
%.o: %.cpp
	$(CC) $(CFLAGS) -c $< -o $@

# Clean up compiled files
clean:
	rm -f $(EXEC) $(OBJ)

# sync with lsp
lsp:
	bear -- make all

# Phony targets to avoid conflicts with files named "all" or "clean"
.PHONY: all clean lsp
