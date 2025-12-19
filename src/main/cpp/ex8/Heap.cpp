#include "Heap.hpp"
#include <cstddef>
#include <iostream>

namespace ex8
{

Heap::Heap() : size(0), a(nullptr), count(0) {}

void Heap::countAndReset()
{
  std::cout << "Anzahl Elementvergleiche: " << count << '\n';
  count = 0;
}

bool Heap::less(std::span<int> a, size_t i, size_t i2)
{
  count++;
  return a[i] < a[i2];
}

void Heap::build_heap(std::span<int> a)
{
  if (size == 0)
    return;

  for (size_t i = size / 2;; i--)
  {
    heapifyDown(a, i);
    if (i == 0)
      break;
  }
}

void Heap::heapifyDown(std::span<int> a, size_t i)
{
  size_t max = i;
  size_t li = 2 * i + 1;
  size_t re = li + 1;
  if (li < size && less(a, max, li))
    max = li;
  if (re < size && less(a, max, re))
    max = re;
  if (i != max)
  {
    std::swap(a[i], a[max]);
    heapifyDown(a, max);
  }
}
void Heap::heap_sort(std::span<int> a)
{
  size = a.size();
  build_heap(a);
  for (size_t i = a.size(); i > 1; i--)
  {
    std::swap(a[0], a[i - 1]);
    decrease_heap_size();
    heapifyDown(a, 0);
  }
}

void Heap::decrease_heap_size(void) { size--; }

} // namespace ex8
