#include "Quick.hpp"

namespace ex6
{
void Quick::sort()
{
  if (size > 0)
  {
    impSort(0, size - 1);
  }
}

void Quick::impSort(int left, int right)
{
  if (right <= left)
    return;
  int i = partition(left, right);
  impSort(left, i - 1);
  impSort(i + 1, right);
}

int Quick::partition(int left, int right)
{
  int i = left - 1, j = right;
  int pivot = a[right];
  while (true)
  {
    while (less(a[++i], pivot))
      ;
    while (less(pivot, a[--j]))
      if (j == left)
        break;
    if (i >= j)
      break;
    swap(i, j);
  }
  swap(i, right);
  return i;
}
} // namespace ex6
