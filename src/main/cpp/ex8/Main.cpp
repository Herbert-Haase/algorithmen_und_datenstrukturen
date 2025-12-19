#include "Heap.hpp"
#include <iostream>
#include "../ex6/Insertion.hpp"
#include "../ex6/Merge.hpp"
#include "../ex6/Quick.hpp"
#include "../ex6/Selection.hpp"

#define N 1000

using namespace ex8;
using namespace ex6;

int main(void)
{
  srand(time(nullptr));
  std::array<int, N> unsorted{};
  // int unsorted[] = {10, 5, 20, 3, 9, 15, 25, 4, 13, 18, 26, 17, 19};
  // size_t aSize = sizeof(a) / sizeof(*a);
  
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
  // std::cout << selection << '\n';
  selection.countAndReset();

  // Insertion
  Insertion insertion{unsorted};
  insertion.sort();
  std::cout << "Insertion: " << '\n';
  // std::cout << insertion << '\n';
  insertion.countAndReset();

  // Quick
  Quick quick{unsorted};
  quick.sort();
  std::cout << "Quick: " << '\n';
  // std::cout << quick << '\n';
  quick.countAndReset();

  // Merge
  Merge merge{unsorted};
  merge.sort();
  std::cout << "Merge: " << '\n';
  // std::cout << merge << '\n';
  merge.countAndReset();

  // Heap
  Heap heap{};
  heap.heap_sort(unsorted);
  std::cout << "Heap: " << '\n';
  // for(size_t i = 0; i < sizeof(unsorted)/sizeof(*unsorted);i++) std::cout << unsorted[i] << ", ";
  // std::cout << '\n';
  heap.countAndReset();


  return 0;
}
