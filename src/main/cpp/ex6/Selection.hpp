#ifndef SELECTION_H
#define SELECTION_H

#include "Sortieren.hpp"

namespace ex6
{
class Selection : public Sortieren
{
public:
  using Sortieren::Sortieren;
  void sort() override;
};
}

#endif
