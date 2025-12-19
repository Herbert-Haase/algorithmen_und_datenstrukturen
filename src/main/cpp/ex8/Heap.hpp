#ifndef HEAP_H
#define HEAP_H

#include <algorithm>
#include <cstdlib>
#include <span>
#include <iostream>

namespace ex8
{
struct Heap final
{
private:
  size_t size;
  int *a;

public:
  Heap();
  Heap(const Heap &) = delete;
  Heap(const Heap &&) = delete;
  Heap &operator=(Heap &) = delete;
  Heap &operator=(Heap &&) = delete;
  ~Heap() = default;

  size_t count;

  bool less(std::span<int> a, size_t i, size_t i2);
  void build_heap(std::span<int> a);
  void countAndReset();
  void heap_sort(std::span<int> a);
  void heapifyDown(std::span<int> a, size_t i);
  void decrease_heap_size(void);
};
} // namespace ex8

#endif
