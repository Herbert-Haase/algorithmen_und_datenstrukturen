#ifndef INSERTION_H
#define INSERTION_H

#include "Sortieren.hpp"

namespace ex6
{
class Insertion : public Sortieren
{
public:
  using Sortieren::Sortieren;
  void sort() override;
};
}

#endif
