#ifndef QUICK_H
#define QUICK_H

#include "Sortieren.hpp"

namespace ex6
{
class Quick : public Sortieren
{
public:
  using Sortieren::Sortieren;
  void sort() override;

private:
  void impSort(int left, int right);
  int partition(int left, int right);
};
}

#endif
