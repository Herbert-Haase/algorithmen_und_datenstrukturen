# Paths
EX6_DIR = ../ex6
CXX = g++
CXXFLAGS = -std=c++20 -Wall -Wextra -I$(EX6_DIR)

# 1. The Sorting Logic (from ex6)
# We compile these into the current directory
LIB_SOURCES = $(EX6_DIR)/Sortieren.cpp \
              $(EX6_DIR)/Selection.cpp \
              $(EX6_DIR)/Insertion.cpp \
              $(EX6_DIR)/Quick.cpp \
              $(EX6_DIR)/Merge.cpp

# Convert the ex6 paths into local object file names
LIB_OBJS = Sortieren.o Selection.o Insertion.o Quick.o Merge.o

# 2. Local ex8 Files
APP_OBJS = Main.o Heap.o

# Combine everything for the final executable
ALL_OBJS = $(LIB_OBJS) $(APP_OBJS)
TARGET = $(notdir $(CURDIR))

# Final Linker Step
$(TARGET): $(ALL_OBJS)
	$(CXX) $(CXXFLAGS) -o $(TARGET) $(ALL_OBJS)

# Rule for building ex6 files (re-points to the correct source)
Sortieren.o: $(EX6_DIR)/Sortieren.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@
Selection.o: $(EX6_DIR)/Selection.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@
Insertion.o: $(EX6_DIR)/Insertion.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@
Quick.o: $(EX6_DIR)/Quick.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@
Merge.o: $(EX6_DIR)/Merge.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@

# Rule for building the local ex8 Main.cpp
Main.o: Main.cpp
	$(CXX) $(CXXFLAGS) -c Main.cpp -o Main.o

# Rule for building Heap.o (Make sure the header name matches your file!)
Heap.o: Heap.cpp Heap.hpp
	$(CXX) $(CXXFLAGS) -c Heap.cpp -o Heap.o

clean:
	rm -f *.o $(TARGET)
