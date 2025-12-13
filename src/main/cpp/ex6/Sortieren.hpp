#ifndef SORTIEREN_H
#define SORTIEREN_H

#include <cstdlib>
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
  Sortieren(const Sortieren &) = delete;
  Sortieren &operator=(const Sortieren &) = delete;
  Sortieren(Sortieren &&) = delete;
  Sortieren &operator=(Sortieren &&) = delete;
  virtual ~Sortieren() = default;
  friend std::ostream &operator<<(std::ostream &os, const Sortieren &s);
  int &operator[](int index);

  virtual void countAndReset();
  int get(unsigned idx);
  void swap(int i, int j);

  virtual void sort() = 0;
};

} // namespace ex6

#endif
