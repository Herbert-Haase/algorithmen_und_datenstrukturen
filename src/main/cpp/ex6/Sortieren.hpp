#ifndef SORTIEREN_H
#define SORTIEREN_H

#include <iostream>
#include <memory>
#include <span>

namespace ex6
{
class Sortieren
{
protected:
  int count;
  std::unique_ptr<int[]> a;
  size_t size;

public:
  Sortieren(std::span<int> arr);
  virtual ~Sortieren() = default;

  Sortieren(const Sortieren &) = delete;
  Sortieren &operator=(const Sortieren &) = delete;
  Sortieren(Sortieren &&) = delete;
  Sortieren &operator=(Sortieren &&) = delete;

  friend std::ostream &operator<<(std::ostream &os, const Sortieren &s);
  int &operator[](int index);

  void countAndReset();
  
  bool less(int lhs, int rhs);
  
  void swap(int i, int j);

  virtual void sort() = 0;
};

} // namespace ex6

#endif
