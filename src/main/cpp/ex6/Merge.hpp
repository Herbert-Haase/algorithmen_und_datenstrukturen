#ifndef MERGE_H
#define MERGE_H

#include "Sortieren.hpp"

namespace ex6
{
class Merge : public Sortieren
{
public:
  using Sortieren::Sortieren;
  void sort() override;

private:
  void impSort(int left, int right);
  void merge(int li, int mi, int re);
};
} // namespace ex6

#endif
