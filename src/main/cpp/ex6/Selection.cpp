#include "Selection.hpp"

namespace ex6
{
void Selection::sort()
{
  for (size_t i = 0; i < size - 1; i++)
  {
    size_t min = i;
    for (size_t j = i + 1; j < size; j++)
    {
      if (less(a[j], a[min]))
        min = j;
    }
    swap(i, min);
  }
}
} // namespace ex6
