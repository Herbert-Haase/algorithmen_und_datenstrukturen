#include "Insertion.hpp"

namespace ex6
{
void Insertion::sort()
{
  for (size_t i = 1; i < size; i++)
  {
    int x = a[i];
    int j = i - 1;

    while (j >= 0 && less(x, a[j]))
    {
      a[j + 1] = a[j];
      j--;
    }
    a[j + 1] = x;
  }
}
} // namespace ex6
