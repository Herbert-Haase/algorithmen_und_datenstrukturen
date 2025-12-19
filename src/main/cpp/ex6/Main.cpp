#include "Selection.hpp"
#include "Insertion.hpp"
#include "Quick.hpp"
#include "Merge.hpp"

#include <cstdlib>
#include <ctime>
#include <iostream>
#include <array>

#define N 1000

using namespace ex6;

int main()
{
  srand(time(nullptr));
  std::array<int, N> unsorted{};
  
  // std::cout << "[";
  for (size_t i = 0; i < unsorted.size(); i++)
  {
    unsorted[i] = rand();
  //   std::cout << unsorted[i];
  //   if (i < unsorted.size() - 1) std::cout << ", ";
  }
  // std::cout << "]" << "\n\n";

  // Selection
  Selection selection{unsorted};
  selection.sort();
  std::cout << "Selection: " << '\n';
  selection.countAndReset();

  // Insertion
  Insertion insertion{unsorted};
  insertion.sort();
  std::cout << "Insertion: " << '\n';
  insertion.countAndReset();

  // Quick
  Quick quick{unsorted};
  quick.sort();
  std::cout << "Quick: " << '\n';
  quick.countAndReset();

  // Merge
  Merge merge{unsorted};
  merge.sort();
  std::cout << "Merge: " << '\n';
  merge.countAndReset();

  return 0;
}
